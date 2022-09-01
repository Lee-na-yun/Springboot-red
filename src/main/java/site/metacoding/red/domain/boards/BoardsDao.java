package site.metacoding.red.domain.boards;

import java.util.List;

import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.web.dto.request.boards.WriteDto;


public interface BoardsDao {
	public void delete(Integer id);
	public void update(Boards boards);
	public void insert(WriteDto writeDto);
	public Boards findById(Integer id);
	public List<Boards> findAll();	
}
