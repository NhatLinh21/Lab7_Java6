package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Authority;

public interface AuthorityDAO extends JpaRepository<Authority, Integer> {

}
