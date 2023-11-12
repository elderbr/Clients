package com.elderbr.clients.services;

import com.elderbr.clients.dto.ClientDTO;
import com.elderbr.clients.entities.Client;
import com.elderbr.clients.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public ClientDTO findById(@PathVariable Long id) {
        return new ClientDTO(clientRepository.findById(id).get());
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


    private void copyDtoToClient(ClientDTO dto, Client client) {
        client.setId(dto.getId());
        client.setName(dto.getName());
        client.setBirthDate(dto.getBirthDate());
        client.setCpf(dto.getCpf());
        client.setIncome(dto.getIncome());
        client.setChildren(dto.getChildren());
    }
}
