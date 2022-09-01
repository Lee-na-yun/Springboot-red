package site.metacoding.red.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;
import site.metacoding.red.web.dto.request.users.JoinDto;
import site.metacoding.red.web.dto.request.users.UpdateDto;
import site.metacoding.red.web.dto.response.RespDto;

@RequiredArgsConstructor
@RestController
public class UsersController {

	private final UsersDao usersDao;
	
	@GetMapping("/users/{id}")
	public RespDto<?> getUsers(@PathVariable Integer id) {
		return new RespDto<>(1, "성공", usersDao.findById(id)); // json으로 변경해서 전송해줌 //제네릭 new부분에는 <>안에 안써도됨
	}
	
	@GetMapping("/users")
	public RespDto<?> getUserList(){
		return new RespDto<>(1, "성공", usersDao.findAll());
	}
	
	@PostMapping("/users")
	public RespDto<?> insert(JoinDto joinDto) { // body가 없어서 모르니까 <?> // JoinDto로 정확하게 받을것만 받기
		usersDao.insert(joinDto);	// insert됨
		return new RespDto<>(1, "회원가입완료", null);	// 201번 insert됨	// null = select가 아니라 응답할것이 없다.
	}
	
	// update, delete 만들기
//	@PutMapping("/users/{id}")
//	public RespDto<?> update(@PathVariable Integer id, UpdateDto updateDto){
//		usersDao.update(id, updateDto);
//		return new RespDto<>(1, "회원수정완료", null);	
//	} // 이렇게 하면 id와 updateDto 2개를 바인딩하지 못해서 서버가 터짐!
	
//	@PutMapping("/users/{id}")
//	public RespDto<?> update(@PathVariable Integer id, UpdateDto updateDto){
//		Users users = new Users();	//updateDto를 users dto로 반환하면서 id받기
//		users.setId(id);
//		users.setUsername(updateDto.getUsername());
//		users.setPassword(updateDto.getPassword());
//		users.setEmail(updateDto.getEmail());
//		
//		usersDao.update(users);
//		return new RespDto<>(1, "회원수정완료", null);	
//	} // 이렇게하면 컨트롤러가 지저분해지므로 깔끔하게 변경해보자!
	
	@PutMapping("/users/{id}")
	public RespDto<?> update(@PathVariable Integer id, UpdateDto updateDto){
		// 1) id로 select 하기(영속화)
		Users usersPs = usersDao.findById(id);
		
		// 2) 변경 : Users에 전체수정 메소드 만들어서 때리기
		usersPs.전체수정(updateDto);
		
		// 3) 영속화된 오브젝트로 update하기 = 전체 update
		usersDao.update(usersPs);
		return new RespDto<>(1, "회원수정완료", null);	
	} 
	
	@PutMapping("/users/{id}/password")
	public RespDto<?> updatePassword(@PathVariable Integer id, String password){		
		// 1) 영속화
		Users usersPs = usersDao.findById(id);
		// 2) 변경
		usersPs.패스워드수정(password);
		// 3) 전체 update
		usersDao.update(usersPs);
		return new RespDto<>(1, "회원패스워드 수정완료", null);
	} 
	
	@DeleteMapping("/users/{id}")
	public RespDto<?> delete(@PathVariable Integer id) {
		usersDao.delete(id);
		return new RespDto<>(1, "성공", usersDao.findById(id));
	}
	
	
	
	
	
	
}
