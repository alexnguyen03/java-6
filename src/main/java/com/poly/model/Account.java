package com.poly.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
@Entity
@Table(name = "Accounts")
public class Account implements Serializable {
	@Id
	@NotBlank(message = "{NotBlank.account.username}")
	private String username;
	@NotBlank(message = "{NotBlank.account.password}")
	@Length(min = 6, max = 12, message = "{Length.account.password}")
	private String password;
	@NotBlank(message = "{NotBlank.account.fullname}")
	private String fullname;
	@NotBlank(message = "{NotBlank.account.email}")
	@Email(message = "{Email.account.email}")
	private String email;

	private String photo;
	private Boolean activated;
	//	private Boolean admin;
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	private List<Order> orders;
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	private List<Review> reviews;
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	private List<Cart> carts;

	@Override
	public String toString() {
		return "id" + username;
	}
}