package org.example.service;

import org.example.dto.CustomerDto;

import java.util.List;


public interface CustomerService {

    List<CustomerDto> getAllCustomers();


    CustomerDto getCustomerById(Integer customerId);


    CustomerDto createCustomer(CustomerDto customerDto);


    CustomerDto updateCustomer(Integer customerId, CustomerDto customerDto);


    void deleteCustomer(Integer customerId);
}
