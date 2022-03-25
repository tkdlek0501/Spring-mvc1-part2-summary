package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// view를 return
@Controller
public class ResponoseViewController {

	// springboot 는 thymeleaf 라이브러리를 추가하면 templates 패키지에 view가 담기는 것을 기본적으로 설정하고 있기 때문에 properties 에서 따로 prefix등 설정을 안해줘도 path를 읽어온다!
	// spring.thymeleaf.prefix=classpath:/templates/
	// spring.thymeleaf.suffix=.html
	
	//TODO: view, model 반환 방법
	// 반환타입 String과 Model 을 사용하자!
	@RequestMapping("/response-view-v2")
	public String resposneViewV2(Model model) {
		model.addAttribute("data", "hello!");
		return "response/hello";
	}
	// String으로 반환하지만 thymeleaf를 사용함으로써 view를 반환/ model에 data를 담아서 응답
	
	// ------------------------------------------------------------------------------------------
	
	@RequestMapping("/response-view-v1")
	public ModelAndView resposneViewV1() {
		ModelAndView mv = new ModelAndView("response/hello").addObject("data", "hello!");
		return mv;
	}
	
	// 위 코드를 아래와 같이 매핑 주소 이름과 view의 논리적 이름이 같으면 return 까지 생략해도 된다 -> 명시적이지 않기 때문에 권장 x
	@RequestMapping("/response/hello")
	public void resposneViewV3(Model model) {
		model.addAttribute("data", "hello!");
	}
	
	// 원래 내가 쓰던 방법 (ModelAndView 사용) -> ModelAndView에 model과 view 모두 담아서 가장 명시적이라고 생각했음
	@GetMapping("myResponseMethod")
	public ModelAndView myResponseMethod(ModelAndView mv, @RequestParam("param") String param) {
		mv.setViewName("response/hello");
		mv.addObject("data", "hello!");
		return mv;
	}
}
