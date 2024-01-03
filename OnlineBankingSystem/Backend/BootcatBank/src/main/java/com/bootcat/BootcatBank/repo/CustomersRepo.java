package com.bootcat.BootcatBank.repo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bootcat.BootcatBank.model.Customers;

@CrossOrigin(origins = "http://localhost:3000")
public interface CustomersRepo extends JpaRepository<Customers, Integer> {

}