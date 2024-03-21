/**
 * Author : rasintha_j
 * Date : 3/20/2024
 * Time : 12:27 PM
 * Project Name : laboratory
 */

package com.laboratory.controller;

import com.laboratory.model.entity.Client;
import com.laboratory.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class ClientController {

    @Autowired
    private ClientService ClientService;

    @PostMapping("/Client/register")
    public Client registerClient(@RequestBody Client client) {
        Client client1 = new Client();
        try {
            client1 = ClientService.registerClient(client);
        } catch (Exception e) {
            throw e;
        }
        return client1;
    }

    @PostMapping("/Client/login")
    public Client loginClient(@RequestParam String Clientname, @RequestParam String password) {
        Client client = new Client();
        try {
            client = ClientService.loginClient(Clientname, password);
        } catch (Exception e) {
            throw e;
        }
        return client;
    }

    @PostMapping("/Client/loginWithRole")
    public Client loginClientWithRole(@RequestParam String clientname, @RequestParam String password, @RequestParam String role) {
        Client client = new Client();
        try {
            client = ClientService.loginClientWithRole(clientname, password, role);
        } catch (Exception e) {
            throw e;
        }
        return client;
    }

    @PutMapping("/client/{id}")
    public Client updateClient(@PathVariable String id, @RequestBody Client client) {
        Client client1 = new Client();
        try {
            client1 = ClientService.updateClient(id, client);
        } catch (Exception e) {
            throw e;
        }
        return client1;
    }

    @DeleteMapping("/client/{id}")
    public void deleteClient(@PathVariable String id) {
        try {
            ClientService.deleteClient(id);
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/client/{id}")
    public Client getClientById(@PathVariable String id) {
        Client clientById = new Client();
        try {
            clientById = ClientService.getClientById(id);
        } catch (Exception e) {
            throw e;
        }
        return clientById;
    }

    @GetMapping("/clients")
    public List<Client> getAllClients() {
        List<Client> allClients = new ArrayList<>();
        try {
            allClients = ClientService.getAllClients();
        } catch (Exception e) {
            throw e;
        }
        return allClients;
    }
}