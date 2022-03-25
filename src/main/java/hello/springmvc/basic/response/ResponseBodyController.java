package hello.springmvc.basic.response;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

// 최근 가장 흔하게 사용되는 방법
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
	}
}
