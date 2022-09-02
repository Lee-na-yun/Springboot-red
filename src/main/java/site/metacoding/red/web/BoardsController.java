package site.metacoding.red.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.web.dto.request.boards.UpdateDto;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.response.RespDto;

@RequiredArgsConstructor
@RestController
public class BoardsController {

	private final BoardsDao boardsDao;
	

	// 글쓰기 (INSERT)
	@PostMapping("/boards")
	public RespDto<?> insert(WriteDto writeDto){
		boardsDao.insert(writeDto);
		return new RespDto<>(1,"글쓰기 성공", null);
	}
	
	// 글전체조회 (SELECT)
	@GetMapping("/boards")
	public RespDto<?> getList(){
		return new RespDto<>(1, "글조회 성공", boardsDao.findAll());
	}
	
	// 글1개 조회 (SELECT)
	@GetMapping("/boards/{id}")
	public RespDto<?> select(@PathVariable Integer id){
		return new RespDto<>(1, "글1개조회 성공", boardsDao.findByIdtoDetail(id));
	}
	
	// 글전체수정 (UPDATE)
	@PutMapping("/boards/{id}")
	public RespDto<?> update(@PathVariable Integer id, UpdateDto updateDto){
		// 1. 영속화하기
		Boards boardsPs = boardsDao.findById(id);
		
		// 2. 변경
		boardsPs.글전체수정(updateDto);
		
		// 3. update하기
		boardsDao.update(boardsPs);
		return new RespDto<>(1, "글전체수정 완료", null);
	}
	
	
	// 글삭제 (DELETE)
	@DeleteMapping("/boards/{id}")
	public RespDto<?> delete(@PathVariable Integer id){
		boardsDao.delete(id);
		return new RespDto<>(1, "글삭제 완료", null);
	}
}
