package com.project.coches.persistance.repository;

import com.project.coches.persistance.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICustomerCrudRepository extends JpaRepository<CustomerEntity, String> {

    //query method que ahorra hacer @Query("Select * from customer where email = $email")
    Optional<CustomerEntity> findByEmail(String email);
}
