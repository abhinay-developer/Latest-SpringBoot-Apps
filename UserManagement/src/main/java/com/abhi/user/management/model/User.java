package com.abhi.user.management.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity(name = "user_details")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;

	private String username;

	private String password;

	@Column(unique = true)
	private String email;

	private int age;

	private String gender;

	private long mobileNo;

	private String currentAddress;

	private String permanentAddress;

	private Date createdDate;

	private Date updatedDate;

}
