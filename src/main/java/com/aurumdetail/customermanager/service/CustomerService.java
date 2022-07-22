package com.aurumdetail.customermanager.service;

import com.aurumdetail.customermanager.exception.UserNotFoundException;
import com.aurumdetail.customermanager.model.Customer;
import com.aurumdetail.customermanager.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.UUID;
@Service
@Transactional
public class CustomerService {
    private final CustomerRepo customerRepo;

    @Autowired
    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public Customer addCustomer(Customer customer){
        customer.setCustomerCode(UUID.randomUUID().toString());
        return customerRepo.save(customer);
    }

    public List<Customer> findAllCustomers(){
        return customerRepo.findAll();
    }

    public Customer updateCustomer(Customer customer){
        return customerRepo.save(customer);
    }

    public Customer findCustomerById(Long id){
        return customerRepo.findCustomerById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public void deleteCustomer(Long id) {
        customerRepo.deleteCustomerById(id);
    }
}
