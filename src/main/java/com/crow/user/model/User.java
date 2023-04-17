package com.crow.user.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="t_users")
public class User {
	@Id
	private UUID id;
	@Column(length = 45, nullable = false)
	@NotBlank(message = "Name is required")
	private String name;
	@Column(length = 45, nullable = false)
	@NotBlank(message = "Other is required")
	private String otherName;
	@Column(length = 15, nullable = false, unique = true)
	@NotBlank(message = "User is required")
	private String userName;
	@Column(length = 15, nullable = false)
	@NotBlank(message = "Password is required")
	private String password;
	@Column(name = "roles", columnDefinition="text[]", nullable = false)
    @Type(type = "com.karakays.hibernate.array.EnumArrayType",
            parameters = { @Parameter(name="enumClass", value="com.crow.user.model.ERole") })
	private List<ERole> roles;
	@Column(length = 10, nullable = false)
	@Enumerated(EnumType.STRING)
	private EAccess access;
	
}
