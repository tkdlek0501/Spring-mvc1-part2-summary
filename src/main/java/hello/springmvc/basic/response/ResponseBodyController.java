package hello.springmvc.basic.response;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hello.springmvc.basic.HelloData;

// responseBody로 return (this is used by REST API or AJAX)
@RestController
public class ResponseBodyController {
	
	@GetMapping("/response-body-string-v2")
	public ResponseEntity<String> responseBodyV2(HttpServletResponse response) {
		return new ResponseEntity<>("ok", HttpStatus.OK);
	}

//TODO: ResponseBody 사용법
// ResponseEntity<...> 와 @ResponseStatus 방법 모두 알고 있기! 	
// 최근 가장 흔하게 사용되는 방법   but status 코드 분기처리가 필요할 때도 있어서 아래 ResponseEntity도 알아야 한다.
	@ResponseStatus(HttpStatus.OK) // ResponseEntity 타입을 쓸때처럼 HTTP 상태코드를 지정해줘서 반환해줄 수 있다. but 동적인 처리를 해줘야 한다면 ResponseEntity 사용해야된다.
//	@ResponseBody
	@GetMapping("/response-body-string-v5")
	public HelloData responseBodyV3() {
		HelloData helloData = new HelloData();
		helloData.setUsername("HJ");
		helloData.setAge(20);
		return helloData;
	}
	
	// 이렇게 쓰면 json형태 뿐만아니라 상태코드까지 반환 하는데 좋은 방법일까?
	@GetMapping("/response-body-string-v2-sub")
	public ResponseEntity<HelloData> responseBodyV2Sub() {
		HelloData helloData = new HelloData();
		helloData.setUsername("HJ");
		helloData.setAge(20);
		return new ResponseEntity<>(helloData, HttpStatus.OK);
		// return new ResponseEntity<>(helloData); -> 상태코드 없이는 못씀
	}
	
	
// ---------------------------------------------------------------------------	
	
	// 원래 내가 쓰던 방법
	@GetMapping("myResponseBodyMethod")
	@ResponseBody
	public Map<String, Object> myResponseBodyMethod(){
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		result.put("username", "HJ");
		return result;
	}
}
