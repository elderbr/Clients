package com.elderbr.clients.services;

import com.elderbr.clients.dto.ClientDTO;
import com.elderbr.clients.entities.Client;
import com.elderbr.clients.repositories.ClientRepository;
import com.elderbr.clients.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public ClientDTO findById(@PathVariable Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado!"));
        return new ClientDTO(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        Page<Client> pages = clientRepository.findAll(pageable);
        return pages.map(x -> new ClientDTO(x));
    }

    @Transactional
    public ClientDTO insert(ClientDTO clientDTO) {
        Client client = new Client();
        copyDtoToClient(clientDTO, client);
        client = clientRepository.save(client);
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        try {
            Client client = clientRepository.getReferenceById(id);
            copyDtoToClient(dto, client);
            client = clientRepository.save(client);
            return new ClientDTO(client);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado!");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        clientRepository.deleteById(id);
    }


    private void copyDtoToClient(ClientDTO dto, Client client) {
        client.setName(dto.getName());
        client.setBirthDate(dto.getBirthDate());
        client.setCpf(dto.getCpf());
        client.setIncome(dto.getIncome());
        client.setChildren(dto.getChildren());
    }
}
