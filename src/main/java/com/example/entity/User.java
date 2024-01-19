package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@NotBlank(message = "名前は必須項目です。")
	private String firstName;
	
	@Column(nullable = false)
	@NotBlank(message = "名字は必須項目です。")
	private String lastName;
	
	@Column(nullable = false, unique = true)
	@NotBlank(message = "E-mailは必須項目です。")
	@Email(message = "E-mailは正しい形式で入力してください。")
	private String email;
}

