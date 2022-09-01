package site.metacoding.red.domain.users;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.red.web.dto.request.users.UpdateDto;

//@Setter update변경하기위해 주석처리함
@Getter
public class Users {	// 데이터베이스와 통신하기 위한 dto
	private Integer id;
	private String username;
	private String password;
	private String email;
	private Timestamp createdAt;
	
	
	// update 변경을 위한 의미있는 메소드 만들기위해 setter를 없앰(영속화)
	public void 전체수정(UpdateDto updateDto) {	// body데이터만 받으면 됨
		this.username = updateDto.getUsername();
		this.password = updateDto.getPassword();
		this.email = updateDto.getEmail();
	}
	
	public void 패스워드수정(String password) {
		this.password = password;
	}
	
	
	
}
