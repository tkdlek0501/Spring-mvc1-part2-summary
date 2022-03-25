package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MappingController {
	
	// 배열의 형태도 된다
	@GetMapping({"/hello-basic", "/hello-go"}) 
	public String getHelloBasic() {
		log.info("helloBasic");
		return "ok";
	}
	 
	// 실무 때는 파라미터 꺼낼 때 @RequestParam 만 사용해서 @pathVariable 이 익숙하지 않음 
	// 최근 HTTP API는 리소스 경로에 식별자를 넣는 스타일을 선호 (ex. /users/1)
	@GetMapping("/mapping/{userId}")
	public String mappingPath(@PathVariable("userId") int userId) {
		log.info("mappingPath userId ={}", userId);
		return "ok";
	}
	// 식별자와 변수명이 같으면 가져올 때 괄호 부분 생략 가능하다
	@GetMapping("/simpleMapping/{userId}")
	public String simpleMappingPath(@PathVariable int userId) {
		log.info("mappingPath userId ={}", userId);
		return "ok";
	}
	
	// 다중 매핑; 식별자 여러개 전달
	@GetMapping("/mapping/user/{userId}/orders/{orderId}")
	public String mappingPath(@PathVariable int userId, @PathVariable Long orderId) {
		log.info("mappingPath userId={}, orderId={}", userId, orderId);
		return "ok";
	}
	
}
