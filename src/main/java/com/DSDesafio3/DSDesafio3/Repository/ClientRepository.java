package com.DSDesafio3.DSDesafio3.Repository;

import com.DSDesafio3.DSDesafio3.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
