package com.crow.user.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import com.crow.user.model.ERole;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class SignupDto {
	@NotBlank(message = "Name is mandatory")
	private String name;
	@NotBlank(message = "Other Name is mandatory")
	private String otherName;
	@NotBlank(message = "user Name is mandatory")
	private String userName;
	private List<ERole> roles;
}
