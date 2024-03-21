/**
 * Author : rasintha_j
 * Date : 3/20/2024
 * Time : 12:26 PM
 * Project Name : laboratory
 */

package com.laboratory.service.impl;

import com.laboratory.Repository.ClientRepository;
import com.laboratory.model.entity.Client;
import com.laboratory.model.entity.User;
import com.laboratory.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client registerClient(Client client) {
        Optional<Client> existingClient = clientRepository.findById(client.getId());
        Client saveClient = null;
        if (existingClient.isPresent()) {
            System.out.println("Client with ID " + client.getId() + " already exists in the database.");
        } else {
            saveClient = clientRepository.save(client);
            System.out.println("User with ID " + client.getId() + " has been added to the database.");
        }
        return saveClient;
    }

    @Override
    public Client loginClient(String email, String password) {
        //return clientRepository.findByUsernameAndPassword(email, password);
        return null;
    }

    @Override
    public Client loginClientWithRole(String email, String password, String role) {
        //return clientRepository.findByUsernameAndRole(email, role);
        return null;
    }

    @Override
    public Client updateClient(String id, Client client) {

        Optional<Client> clientOptional = clientRepository.findById(id);

        if (clientOptional.isPresent()) {
            Client existingClient = clientOptional.get();
            // Update only the fields that are not the ID
            existingClient.setFirstname(client.getFirstname());
            existingClient.setMiddlename(client.getMiddlename());
            existingClient.setLastname(client.getLastname());
            existingClient.setGender(client.getGender());
            existingClient.setContact(client.getContact());
            existingClient.setEmail(client.getEmail());
            existingClient.setPassword(client.getPassword());
            existingClient.setRole(client.getRole());
            existingClient.setDob(client.getDob());
            existingClient.setAddress(client.getAddress());
            // Set other fields as needed
            return clientRepository.save(existingClient);
        } else {
            return null; // or throw an exception if the user with the given id is not found
        }
    }

    @Override
    public int deleteClient(String id) {
        Optional<Client> clientOptional = clientRepository.findById(id);

        if (clientOptional.isPresent()) {
            clientRepository.deleteById(id);
            return 1; // User deleted successfully
        } else {
            return 0; // User does not exist, no deletion performed
        }
    }

    @Override
    public Client getClientById(String id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getUsersByUsernameAndPassword(String email, String password) {
        return clientRepository.findByEmailAndPassword(email, password);
    }
}