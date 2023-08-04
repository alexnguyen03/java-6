package com.poly.model;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Accounts")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "username")
public class Account implements Serializable {
	@Id
	@NotBlank(message = "{NotBlank.account.username}")
	private String username;

	@NotBlank(message = "{NotBlank.account.password}")
	private String password;

	@NotBlank(message = "{NotBlank.account.fullname}")
	private String fullname;

	@NotBlank(message = "{NotBlank.account.email}")
	@Email(message = "{Email.account.email}")
	private String email;

	private String photo;
	private String token;
	Boolean activated;
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	private List<Order> orders;
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	private List<Review> reviews;
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	private List<Cart> carts;

	// @JsonIgnore
	// @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	// List<Authority> authorities;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "Accounts_Roles", joinColumns = @JoinColumn(name = "username", referencedColumnName = "username"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
	private List<Role> roles;

	@Override
	public String toString() {
		return "id" + username;
	}
}