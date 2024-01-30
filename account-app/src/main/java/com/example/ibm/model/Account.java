package com.example.ibm.model;

import org.hibernate.validator.constraints.Length;

import jakarta.annotation.Generated;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.Column;

@Entity
@Table(name = "accounts_table")
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Account {


	@Id
	@NotBlank(message = "account number cannot be blank")
	@Column(name = "account_number")
	private String accountNumber;
	
	@NotBlank(message = "account holer name cannot be blank")
	@Length(min =5, max = 16 ,message = "account holder name is wrong")
	@Column(name = "account_holder_name")
	private String accountHolderName;
	
	@Column(name = "account_holder_address")
	private String accountHolderAddress;
	
	@Column(name = "account_holder_email", unique = true)
	@Email(message = "invalid email" ,regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
	private String email;
	
	@Column(name="accoun_holder_amount")
	private Double amount;
	
	



	public Account() {
		super();
	}


//	public String getAccountNumber() {
//		return accountNumber;
//	}
//
//
//	public void setAccountNumber(String accountNumber) {
//		this.accountNumber = accountNumber;
//	}
//
//
//	public String getAccountHolderName() {
//		return accountHolderName;
//	}
//
//
//	public void setAccountHolderName(String accountHolderName) {
//		this.accountHolderName = accountHolderName;
//	}
//
//
//	public String getAccountHolderAddress() {
//		return accountHolderAddress;
//	}
//
//
//	public void setAccountHolderAddress(String accountHolderAddress) {
//		this.accountHolderAddress = accountHolderAddress;
//	}
//
//
//	public String getEmail() {
//		return email;
//	}
//
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//


	
	
	
	
	
	
}
