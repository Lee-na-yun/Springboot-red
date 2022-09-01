package site.metacoding.red.web.dto.request.users;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateDto {	// 외부 클라이언트와 통신을 위한 dto

	private String username;
	private String password;
	private String email;
}
