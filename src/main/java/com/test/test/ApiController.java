package com.test.test;

import com.test.test.client.Client;
import com.test.test.client.ClientRepository;
import com.test.test.client.ClientResponse;
import com.test.test.client.CreateClientRequest;
import com.test.test.credit.CreditDecision;
import com.test.test.credit.repository.CreditAgreementRepository;
import com.test.test.credit.service.CreditService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class ApiController {
    @Autowired
    private final ClientRepository clientRepository;
    @Autowired
    private final CreditAgreementRepository creditAgreementRepository;
    @Autowired
    private final CreditService creditService;

    @PostMapping("/api/v1/client")
    public ClientResponse create(@RequestBody CreateClientRequest request){
        Client client = Client.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .middleName(request.getMiddleName())
                .passport(request.getPassport())
                .maritalStatus(request.getMaritalStatus())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .employmentStatus(request.getEmploymentStatus())
                .employmentPeriod(request.getEmploymentPeriod())
                .employmentPosition(request.getEmploymentPosition())
                .employmentOrganization(request.getEmploymentOrganization())
                .requestedCreditAmount(request.getRequestedCreditAmount())
                .build();

        client = clientRepository.save(client);
        return ClientResponse.of(client);
    }

    @GetMapping("/api/v1/client")
    public List<ClientResponse> search(
            @RequestParam(defaultValue = "") String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
                .withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("lastName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("middleName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("passport", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        Example<Client> example = Example.of(
                Client.builder()
                        .firstName(query)
                        .lastName(query)
                        .middleName(query)
                        .passport(query)
                        .build(), exampleMatcher);

        return clientRepository
                .findAll(example, pageable)
                .stream()
                .map(ClientResponse::of)
                .collect(Collectors.toList());

    }

    @PostMapping("/api/v1/client/agreement")
    public ResponseEntity<Client> approveCreditApplication(@PathVariable Long id, @RequestBody CreditDecision creditDecision) {
        Client creditApplication = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Credit application not found"));
        creditService.makeCreditDecision(creditApplication, creditDecision);
        Client updatedCreditApplication = clientRepository.save(creditApplication);
        return new ResponseEntity<>(updatedCreditApplication, HttpStatus.OK);
    }
}
