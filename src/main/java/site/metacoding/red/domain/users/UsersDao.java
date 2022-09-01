package site.metacoding.red.domain.users;

import java.util.List;

import site.metacoding.red.web.dto.request.users.JoinDto;
import site.metacoding.red.web.dto.request.users.UpdateDto;

public interface UsersDao {
	public void delete(Integer id);
//	public void update(Integer id, UpdateDto updateDto);
	public void update(Users users);
	public void insert(JoinDto joinDto);
	public Users findById(Integer id);
	public List<Users> findAll();	// result type=List
}
