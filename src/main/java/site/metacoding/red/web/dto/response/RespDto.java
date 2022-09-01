package site.metacoding.red.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class RespDto<T> {		// 응답하는 공통 dto

	private Integer code; // 1정상, -1실패
	private String msg; // 통신에 대한 결과 메세지 담기
	private T body; // body타입이 항상 다름 => 제네릭사용 <T>,T
}
