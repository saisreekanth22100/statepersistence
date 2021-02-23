package com.app.challenge.ppf.statepersistence.controller;

import com.app.challenge.ppf.statepersistence.model.Employee;
import com.app.challenge.ppf.statepersistence.model.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeControllerTest {

    RestTemplate restTemplate = new RestTemplate();
    final String baseUrl = "http://localhost:8081/challenge/employees/";

    @Test
    public void testCreateEmployee() throws URISyntaxException
    {
        URI uri = new URI(baseUrl);
        Employee employee = new Employee(10008L,"Eva","Permanent","Operations","manager", Status.ADDED);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<Employee> request = new HttpEntity<>(employee, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

        //Verify request succeed
        Assertions.assertEquals(201, result.getStatusCodeValue());
    }

    @Test
    void testGetAllEmployees() throws URISyntaxException {

        URI uri = new URI(baseUrl);

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        //Verify request succeed
        Assertions.assertEquals(200, result.getStatusCodeValue());
        assertNotNull(result.getBody());
    }
}