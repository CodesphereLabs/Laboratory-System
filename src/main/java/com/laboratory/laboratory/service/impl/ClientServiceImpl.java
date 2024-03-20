/**
 * Author : rasintha_j
 * Date : 3/20/2024
 * Time : 12:26 PM
 * Project Name : laboratory
 */

package com.laboratory.laboratory.service.impl;

import com.laboratory.laboratory.Repository.ClientRepository;
import com.laboratory.laboratory.model.entity.Client;
import com.laboratory.laboratory.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client registerClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client loginClient(String email, String password) {
        return clientRepository.findByUsernameAndPassword(email, password);
    }

    @Override
    public Client loginClientWithRole(String email, String password, String role) {
        return clientRepository.findByUsernameAndRole(email, role);
    }

    @Override
    public Client updateClient(String id, Client client) {
        Client existingClient = clientRepository.findById(id).orElse(null);
        if (existingClient != null) {
            existingClient.setFirstname(client.getFirstname());
            existingClient.setMiddlename(client.getMiddlename());
            existingClient.setLastname(client.getLastname());
            existingClient.setGender(client.getGender());
            existingClient.setContact(client.getContact());
            existingClient.setEmail(client.getEmail());
            existingClient.setPassword(client.getPassword());
            existingClient.setRole("USER");
            existingClient.setDob(client.getDob());
            existingClient.setAddress(client.getAddress());
            // Set other fields as needed
            return clientRepository.save(existingClient);
        }
        return null; // or throw an exception if the user with given id is not found
    }

    @Override
    public void deleteClient(String id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Client getClientById(String id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
}