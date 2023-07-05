
package com.poly.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Categories")
public class Category implements Serializable {
	@Id
	@NotBlank(message = "{NotBlank.category.id}")
	@Length(min=4,max=4,message="{Length.category.id}")
	String id;
	@NotBlank(message = "{NotBlank.category.name}")
	String name;
	@OneToMany(mappedBy = "category")
	List<Product> products;

	@Override
	public String toString() {
		return "id" + id;
	}
}