/**
 * Author : rasintha_j
 * Date : 3/20/2024
 * Time : 12:27 PM
 * Project Name : laboratory
 */

package com.laboratory.laboratory.controller;

import com.laboratory.laboratory.model.entity.Client;
import com.laboratory.laboratory.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientService ClientService;

    @PostMapping("/Client/register")
    public Client registerClient(@RequestBody Client client) {
        return ClientService.registerClient(client);
    }

    @PostMapping("/Client/login")
    public Client loginClient(@RequestParam String Clientname, @RequestParam String password) {
        return ClientService.loginClient(Clientname, password);
    }

    @PostMapping("/Client/loginWithRole")
    public Client loginClientWithRole(@RequestParam String clientname, @RequestParam String password, @RequestParam String role) {
        return ClientService.loginClientWithRole(clientname, password, role);
    }

    @PutMapping("/client/{id}")
    public Client updateClient(@PathVariable String id, @RequestBody Client client) {
        return ClientService.updateClient(id, client);
    }

    @DeleteMapping("/client/{id}")
    public void deleteClient(@PathVariable String id) {
        ClientService.deleteClient(id);
    }

    @GetMapping("/client/{id}")
    public Client getClientById(@PathVariable String id) {
        return ClientService.getClientById(id);
    }

    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return ClientService.getAllClients();
    }
}