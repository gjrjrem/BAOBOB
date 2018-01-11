package spring.mvc.baobob;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.mvc.baobob.service.MainService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}

	@RequestMapping("mainDefault")
	public String mainDefault(Model model) {
		return "mainDefault";
	}

	@RequestMapping("mainEx")
	public String mainEx(Model model) {
		return "guest/example";
	}
	

	@RequestMapping("adminEx")
	public String adminEx(Model model) {
		return "host/example";
	}
	
	//==============================================================================
	
	@Autowired
	MainService service;
	
	@RequestMapping("mainIndex")
	public String index(HttpServletRequest req, Model model) {
		System.out.println("index()");
		return "main/index";
	}
	
	//ȸ������
	@RequestMapping("mainJoin")
	public String mainJoin() {
		System.out.println("mainJoin()");
		return "main/mainJoin";
	}
	
	//���̵� �ߺ� �˻�
	@RequestMapping("mainConfirmId")
	public String confirmId(HttpServletRequest req, Model model) {
		System.out.println("confirmId()");
		
		service.confirmId(req, model);
		
		return "main/cntPage";
	}
	
	//����ó��
	@RequestMapping("mainJoinPro")
	public String joinPro(HttpServletRequest req, Model model) {
		System.out.println("joinPro()");
		
		service.joinPro(req, model);
		
		return "main/mainJoinPro";
	}
	
	//���� ���� Ȯ��
	@RequestMapping("mainConfirmEmail")
	public String mainConfirmEmail(HttpServletRequest req, Model model) {
		System.out.println("mainConfirmEmail");
		
		service.confirmEmail(req, model);
		
		return "main/mainConfirmEmail";
	}
	
	//�α��� mainSignIn
	@RequestMapping("mainSignIn")
	public String mainSignIn(HttpServletRequest req, Model model) {
		System.out.println("joinPro()");
		
		
		return "main/mainSignIn";
	}
}
