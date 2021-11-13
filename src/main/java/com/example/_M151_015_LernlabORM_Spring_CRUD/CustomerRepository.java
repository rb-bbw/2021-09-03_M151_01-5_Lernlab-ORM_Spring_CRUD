package com.example._M151_015_LernlabORM_Spring_CRUD;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findByLastname(String lastname);
}
