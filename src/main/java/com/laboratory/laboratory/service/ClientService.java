/**
 * Author : rasintha_j
 * Date : 3/20/2024
 * Time : 12:25 PM
 * Project Name : laboratory
 */

package com.laboratory.laboratory.service;

import com.laboratory.laboratory.model.entity.Client;

import java.util.List;

public interface ClientService {
    Client registerClient(Client client);
    Client loginClient(String email, String password);
    Client loginClientWithRole(String email, String password, String role); // Add method for login with role
    Client updateClient(String id, Client client); // Add updateClient method
    void deleteClient(String id);
    Client getClientById(String id);
    List<Client> getAllClients();
}