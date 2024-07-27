package com.example.demo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Data;

@Data
@Entity
@Table (name = "accounts")
public class Account implements Serializable {
	@Id
	private String username;
	private String password;
	private String email;
	private String fullname;
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Authority> authorities;
}
