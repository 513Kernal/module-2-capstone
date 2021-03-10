package com.techelevator.tenmo.services;

import com.techelevator.tenmo.models.Account;
import com.techelevator.tenmo.models.AuthenticatedUser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.models.Transfer;

import java.util.Scanner;

public class TransferService {


    private final String BASE_SERVICE_URL;
    private RestTemplate restTemplate = new RestTemplate();
    private AuthenticatedUser currentUser;


    public TransferService(String baseUrl) {
        this.BASE_SERVICE_URL = baseUrl + "account/transfers";
    }

    public Transfer createTransfer(Transfer transfer) {
        ResponseEntity<Transfer> response = restTemplate.exchange(BASE_SERVICE_URL, HttpMethod.POST, makeAuthEntity(), Transfer.class);
        return response.getBody();
    }

    public Transfer[] transfersList(String authToken) {
        Transfer[] output = null;
        HttpEntity<?> entity = new HttpEntity<>(authHeaders(authToken));
        output = restTemplate.exchange(BASE_SERVICE_URL + "/all", HttpMethod.GET, entity, Transfer[].class).getBody();
        return output;
    }


    public Transfer transferDetails(String authTokn, int transferId) {
        Transfer transfer = new Transfer();
        HttpEntity<?> entity = new HttpEntity<>(authHeaders(authTokn));
        transfer = restTemplate.exchange(BASE_SERVICE_URL + "/" + transfer.getTransferId() + "/details", HttpMethod.GET, entity, Transfer.class).getBody();
        return transfer;
    }

    //    public Transfer getUsers() {
//        HttpEntity<?> entity = new HttpEntity<>(makeAuthEntity());
//        ResponseEntity<Transfer> response = restTemplate.exchange(BASE_SERVICE_URL, HttpMethod.GET, entity, Transfer.class);
//        return response.getBody();
//    }
    private HttpEntity makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        HttpEntity entity = new HttpEntity(headers);
        return entity;
    }

    private HttpHeaders authHeaders(String authToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return headers;
    }

}