package hello.springmvc.basic.request;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

// messageBody를 가져와서 사용하는 방법
@Slf4j
@Controller
public class RequestBodyStringController {
	
	// 결론적으로 이 방법을 사용하자!!
	// @RequestBody를 이용하면 그냥 바로 messageBody를 가져올 수도 있다
	// @ResponseBody 는 messageBody에 String을 그대로 넣게 해주는 기능, view를 사용하지 않는다
	@ResponseBody
	@PostMapping("/request-body-string-v4")
	public String requestBodyStringV4(@RequestBody String messageBody) {
		log.info("messageBody={}", messageBody);
		return "ok";
	}
	
	// ※ 이렇게 메시지 바디를 직접 조회하는 기능은 요청 파라미터를 조회하는 @RequestParam과 @ModelAttribute와는 전혀 관계가 없다!!
	
	// --------------------------------------------------------------------------------------------
	
	
	// 스프링 MVC 는 request나 response를 통쨰로 들고오지 않고도 
	// HttpEntity를 이용해 요청시와 응답시 메시지 바디 부분을 조회 (header 정보도 가져올 수 있음 : httpEntity.getHeaders())
	@PostMapping("/request-body-string-v3")
	public HttpEntity<String> requestBodyString(HttpEntity<String> httpEntity) throws IOException{
		String messageBody = httpEntity.getBody();
		log.info("messageBody={}", messageBody);
		
		return new HttpEntity<>("ok");
	}
	
	// HttpEntity 를 상속받은 객체들 사용 가능
	// RequestEntity : HttpMethod, url 정보가 추가  ; 요청에서 사용
	// ResponseEntity : HTTP 상태 코드 설정 가능 ; 응답에서 사용
	@PostMapping("/request-body-string-v3-sub")
	public HttpEntity<String> requestBodyStringV3Sub(RequestEntity<String> httpEntity) throws IOException{
		String messageBody = httpEntity.getBody();
		log.info("messageBody={}", messageBody);
		
		return new ResponseEntity<String>("ok", HttpStatus.CREATED); // 상태코드 반환 가능
	}
	
}
