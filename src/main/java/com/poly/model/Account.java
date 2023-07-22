package com.poly.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
	@OneToMany(mappedBy = "account")
	private List<Order> orders;
	@OneToMany(mappedBy = "account")
	private List<Review> reviews;
	@OneToMany(mappedBy = "account")
	private List<Cart> carts;

	@Override
	public String toString() {
		return "id" + username;
	}
}