package com.macrosoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.macrosoft.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
