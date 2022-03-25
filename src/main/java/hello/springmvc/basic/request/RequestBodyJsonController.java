package hello.springmvc.basic.request;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;

// json 형태로 가져오는 방법
@Slf4j
@Controller
public class RequestBodyJsonController {

	@PostMapping("/requets-body-json-v3")
	@ResponseBody
	public String requestBodyJsonV3(@RequestBody HelloData helloData) {
		log.info("messageBody={}", helloData); 
		log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
		return "ok";
	}
	
	// @RequestBody를 사용하면 HTTP 메시지 컨버터가 HTTP 메시지 바디의 내용을 우리가 원하는 문자나 객체 등으로 변환해준다.
	
	// 응답 시에도 String이 아닌 json으로 반환해줄 수 있다
	@PostMapping("/requets-body-json-v5")
	@ResponseBody
	public HelloData requestBodyJsonV5(@RequestBody HelloData data) {
		log.info("username={}, age={}", data.getUsername(), data.getAge());
		return data;
	}
}
