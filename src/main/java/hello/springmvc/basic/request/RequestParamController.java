package hello.springmvc.basic.request;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RequestParamController {

	@ResponseBody // @RestController 와 마찬가지로 String을 String 그대로 반환하게 해줌
	@RequestMapping("/request-param-v2")
	public String requestParamV2(
			@RequestParam("username") String userName,
			@RequestParam("age") int userAge
			) {
		
		log.info("username={}, age={}", userName, userAge);
		return "ok";
	}
	
	@ResponseBody 
	@RequestMapping("/request-param-required")
	public String requestParamV5( // default로는 true이기 때문에 필수값이 아니라는 것을 false로 따로 설정해줘야 한다.
			@RequestParam(value="username", required=false) String username, 
			@RequestParam(value="age", required=false) Integer age) { // or (value = "age", required=false, defaultValue="0") int age
		// int 에는 null이 들어갈 수 없으므로 required=false 일때는 Integer 타입으로 받던지 아니면 defaultValue 설정 필요 (지금까지 defaultValue 설정을 해줬었다.)
		log.info("username={}, age={}", username, age);
		return "ok";
	}
	
	// ----------------------------------------------------------------------------------------------
	
	@ResponseBody
	@RequestMapping("/model-attribute-v1")
	public String modelAttributeV1(@RequestParam String username, @RequestParam int age) {
		HelloData helloData = new HelloData();
		helloData.setUsername(username);
		helloData.setAge(age);
		
		log.info("helloData={}", helloData);
		return "ok";
	}
	
	// @ModelAttribute 를 사용하면 위 코드처럼 객체 생성 후 파라미터 이름과 동일한 필드에 파라미터의 값을 set 해준다, 이 어노테이션도 생략 가능하다
	@ResponseBody
	@RequestMapping("/model-attribute-v2")
	public String modelAttributeV1(HelloData helloData) { // @ModelAttribute 생략
		log.info("helloData={}", helloData);
		return "ok";
	}
	
	// 지금까지 내가 실무에서 사용해왔던 방식
	@ResponseBody
	@GetMapping("/test-my-mapping")
	public String testMyMappign(
			HelloData helloData, 
			@RequestParam(value = "temp", required = false, defaultValue="HJ") String temp
			) {
		// temp 가공 후
		helloData.setUsername(temp);
		
		log.info("helloData={}", helloData);
		return "ok";
	}
}
