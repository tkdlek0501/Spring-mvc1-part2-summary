package hello.springmvc.basic;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

//로거는 slf4j 인터페이스를 사용하고 구현체로는 Logback 선택
@Slf4j
@RestController // return 시에 String을 반환할 때 String 그대로 반환됨(HTTP 메시지 바디에 바로 입력됨) <-> @Controller 사용 시에는 String으로 반환하면 View를 가져오게 됨 
public class LogController {
	//	private final Logger log = LoggerFactory.getLogger(getClass()); -> 어노테이션으로 대체 가능(롬복에서 제공)
	
	@GetMapping("/log-test")
	public String logTest() {
		String name = "Spring";
		
		System.out.println("name = " + name); // 콘솔 출력 -> 어떤 상황이든 무조건 다 출력되니까 운영 서버에서는 사용하면 안된다.
		// log.trace(" trace my log="+ name); // 이렇게 사용하면 안된다!!!! 자바에서는 + 연산을 처리하게돼서 설정에 trace를 포함하지 않아도 쓸모 없이 메모리를 사용하게 된다. (trace my log=Spring 이 돼버림)
		
		// 로그 출력 -> 레벨에 따라 출력할 수 있어서 운영 서버에서 이렇게 사용해야 한다. 
		log.trace("trace log={}", name); 
		log.debug(" debug log={}", name); 
		log.info(" info log={}", name); // 정보
		log.warn(" warn log={}", name); // 경고
		log.error(" error log={}", name); // 에러
		
		return "ok";
	}
}

// 만약 local에서 작업하면서 모든 로그를 보고 싶으면 application.properties 에서 설정 (logging.level.패키자=레벨)
// 테스트 시에는 trace나 debug 레벨로 세팅하고 운영 서버에서는 info 레벨로 설정하는게 좋다 (properties 에서 설정 안하면 기본 info)
// 로그 사용시 좋은 점 : 쓰레드 및 클래스 이름 볼 수 있고, 설정 시 콘솔 뿐만 아니라 파일로도 남길 수 있다