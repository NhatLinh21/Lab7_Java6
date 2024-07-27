package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.ManyToAny;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy;


import lombok.Data;

@Data
@Entity
@Table (name = "authorities", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"Username", "Roleid"})
})
public class Authority implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne 
	@JoinColumn(name = "Username")
	private Account account;
	@ManyToOne
	@JoinColumn(name = "Roleid")
	private Role role;
}
