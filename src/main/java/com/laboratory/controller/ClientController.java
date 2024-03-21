/**
 * Author : rasintha_j
 * Date : 3/20/2024
 * Time : 12:27 PM
 * Project Name : laboratory
 */

package com.laboratory.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laboratory.model.bean.ResponseBean;
import com.laboratory.model.entity.Client;
import com.laboratory.model.entity.User;
import com.laboratory.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ClientController {

    ResponseBean responseBean = new ResponseBean();
    @Autowired
    private ClientService clientService;

    @PostMapping("/client/register")
    public ResponseBean registerClient(@RequestBody JsonNode requestData) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Client client = objectMapper.convertValue(requestData, Client.class);
            Client registeredClient = clientService.registerClient(client);
            if (registeredClient != null) {
                responseBean.setContent(registeredClient);
                responseBean.setResponseCode("200");
                responseBean.setResponseMsg("Client registered successfully");
            } else {
                responseBean.setContent(null);
                responseBean.setResponseCode("300");
                responseBean.setResponseMsg("Client with ID already exists in the database");
            }
        } catch (Exception e) {
            responseBean.setContent(e);
            responseBean.setResponseCode("500");
            responseBean.setResponseMsg("Failed to register Client");
        }
        return responseBean;
    }

    @PutMapping("/client/{id}")
    public ResponseBean updateClient(@PathVariable String id, @RequestBody Client client) {
        try {
            Client updateClient = clientService.updateClient(id, client);
            if (updateClient != null) {
                responseBean.setContent(updateClient);
                responseBean.setResponseCode("200");
                responseBean.setResponseMsg("Client update successfully");
            } else {
                responseBean.setContent(null);
                responseBean.setResponseCode("300");
                responseBean.setResponseMsg("Invalid Client " + id);
            }
        } catch (Exception e) {
            responseBean.setContent(e);
            responseBean.setResponseCode("500");
            responseBean.setResponseMsg("Client update unsuccessfully");
        }
        return responseBean;
    }

    @DeleteMapping("/client/{id}")
    public ResponseBean deleteClient(@PathVariable String id) {
        try {
            int i = clientService.deleteClient(id);
            if (i == 1) {
                responseBean.setContent(id);
                responseBean.setResponseCode("200");
                responseBean.setResponseMsg("User delete successfully");
            } else {
                responseBean.setContent(null);
                responseBean.setResponseCode("300");
                responseBean.setResponseMsg("Client delete unsuccessfully");
            }
        } catch (Exception e) {
            responseBean.setContent(e);
            responseBean.setResponseCode("500");
            responseBean.setResponseMsg("Client delete unsuccessfully");
        }
        return responseBean;
    }

    @GetMapping("/client/{id}")
    public ResponseBean getClientById(@PathVariable String id) {
        try {
            Client clientById = clientService.getClientById(id);
            if (clientById != null) {
                responseBean.setContent(clientById);
                responseBean.setResponseCode("200");
                responseBean.setResponseMsg("Get client " + id + " successfully");
            } else {
                responseBean.setContent(null);
                responseBean.setResponseCode("300");
                responseBean.setResponseMsg("Invalid client " + id);
            }
        } catch (Exception e) {
            responseBean.setContent(null);
            responseBean.setResponseCode("500");
            responseBean.setResponseMsg("Get client " + id + " unsuccessfully");
        }
        return responseBean;
    }

    @GetMapping("/clients")
    public ResponseBean getAllClients() {
        try {
            List<Client> allClients = clientService.getAllClients();
            if (allClients.size() > 0) {
                responseBean.setContent(allClients);
                responseBean.setResponseCode("200");
                responseBean.setResponseMsg("Get all client fetch successfully");
            } else {
                responseBean.setContent(allClients);
                responseBean.setResponseCode("300");
                responseBean.setResponseMsg("No client in the database");
            }
        } catch (Exception e) {
            responseBean.setContent(e);
            responseBean.setResponseCode("500");
            responseBean.setResponseMsg("Get all client fetch unsuccessfully");
        }
        return responseBean;
    }
}