package com.poly.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Products")
public class Product implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank(message = "{NotBlank.product.name}")
	private String name;
	private String image;
	@NotNull(message = "{NotNull.product.price}")
	@Min(value = 0, message = "{Min.product.price}")
	private Double price;
	@Temporal(TemporalType.DATE)
	@Column(name = "create_date")
	private Date createDate = new Date();
	private Boolean available;
	@NotNull(message = "{NotNull.product.quantity}")
	@Min(value = 0, message = "{Min.product.quantity}")
	private Integer quantity;
	@ManyToOne
	@JoinColumn(name = "category_id")
	@NotNull(message = "{NotNull.product.category}")
	private Category category;
	@OneToMany(mappedBy = "product")
	private List<OrderDetail> orderDetails;
	@OneToMany(mappedBy = "product")
	private List<CartDetail> cartDetails;
	@OneToMany(mappedBy = "product")
	private List<Review> reviews;

	@Override
	public String toString() {
		return "id" + id;
	}
}
