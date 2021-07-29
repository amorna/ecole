package com.amorna.ecole.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "users_roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRole implements Serializable {
@Id
@Column(name = "username", nullable = false, length = 20)
private String userName;
@Id
@Column(name = "role", nullable = false, length = 20)
private String role;
}
