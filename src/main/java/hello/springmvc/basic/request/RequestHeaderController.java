package hello.springmvc.basic.request;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RequestHeaderController {

	@RequestMapping("/headers")
	public String headers(HttpServletRequest request,
			HttpServletResponse response,
			HttpMethod httpMethod, // HTTP 메서드 조회
			Locale locale, // Locale 정보 조회
			@RequestHeader MultiValueMap<String, String> headerMap, // 모든 HTTP 헤더를 MultiValueMap 형식으로 조회
			@RequestHeader("host") String host, // 특정 HTTP 헤더 조회 / host : 요청 시 필수값; 어떤 URL에서 요청 했는지 
			@CookieValue(value = "myCookie", required = false) String cookie // 특정 쿠키 조회
			) {
	
	log.info("request={}", request);
	log.info("response={}", response);
	log.info("httpMethod={}", httpMethod);
	log.info("locale={}", locale);
	log.info("headerMap={}", headerMap);
	log.info("host={}", host);
	log.info("cookie={}", cookie);
	
	return "ok";
	}

}
