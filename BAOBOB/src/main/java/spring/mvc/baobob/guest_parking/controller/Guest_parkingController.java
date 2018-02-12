package spring.mvc.baobob.guest_parking.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.baobob.guest_parking.Service.Guest_parkingService;
import spring.mvc.baobob.service.MainService;

@Controller
public class Guest_parkingController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	Guest_parkingService service;
	
	@Autowired
	MainService mService;
	
	//����
	@RequestMapping("guestParkingMain")
	public String guestParkingMain() {
		log.info("guestParkingMain()");
		return "guest/guest_parking/guestParkingMain";
	}
	
	//����
	@RequestMapping("guestParkingIn")
	public String guestParkingIn() {
		log.info("guestParkingIn()");
		return "guest/guest_parking/guestParkingIn";
	}

	//���� - ȸ�� ���ý�
	@RequestMapping("guestParkingMember")
	public String guestParkingMember(HttpServletRequest req, Model model) {
		log.info("guestParkingMember()");
		return "guest/guest_parking/guestParkingMember";
	}
	
	//���� - ��ȸ�� ���ý�
	@RequestMapping("guestParkingGuest")
	public String guestParkingGuest(HttpServletRequest req, Model model) {
		log.info("hostParkingGuest()");
		return "guest/guest_parking/guestParkingGuest";
	}
	
	//��ȸ�� ����
	@RequestMapping("guestParkingGuestIn")
	public String guestParkingGuestIn(HttpServletRequest req, Model model) {
		log.info("guestParkingGuestIn()");
		service.guestParkingInPro(req, model);
		return "guest/guest_parking/guestParkingNumber";
	}
	
	//ȸ�� ����- �α��� ó�� => ��ȣ ����
	@RequestMapping("guestParkingMemberIn")
	public String guestParkingSignIn(HttpServletRequest req, Model model) {
		log.info("guestParkingMemberIn()");
		
		service.parkSignInPro(req, model);
		if(req.getSession().getAttribute("parkId") != null) {
			service.guestParkingInPro(req, model);
		} else {
			req.setAttribute("memChk", "0");
		}
		return "guest/guest_parking/guestParkingNumber";
	}
	
	//ȸ�� ����- ���̽��� �α��� ó�� => ��ȣ ����
	@RequestMapping("guestParkingFirebaseLogin")
	public String guestParkingFirebaseLogin (HttpServletRequest req, Model model) {
		log.info("guestParkingFirebaseLogin()");

		mService.firebaseLoginPro(req, model);
		service.guestParkingInPro(req, model);
		
		return "guest/guest_parking/guestParkingNumber";
	}
	
	//����
	@RequestMapping("guestParkingOut")
	public String guestParkingOut() {
		log.info("guestParkingOut()");
		return "guest/guest_parking/guestParkingOutCheck";
	}
	
	//���� - ��ȣ Ȯ��
	@RequestMapping("guestParkingOutCheckPro")
	public String guestParkingOutCheckPro(HttpServletRequest req, Model model) {
		log.info("guestParkingOutCheckPro()");
		
		service.guestParkingOutCheckPro(req, model);
		
		return "guest/guest_parking/guestParkingPay";
	}

	//����
	@RequestMapping("guestParkingPayPro")
	public String guestParkingPay(HttpServletRequest req, Model model) {
		log.info("guestParkingPay()");
		
		service.guestParkingPay(req, model);
		
		return "guest/guest_parking/guestParkingPayPro";
	}
	
	//���� ����
	@RequestMapping("guestParkingReceipt")
	public String guestParkingReceipt(HttpServletRequest req, Model model) {
		log.info("guestParkingReceipt()");
		return "guest/guest_parking/guestParkingReceipt";
	}
	
	//���� ���� ���
	@RequestMapping("guestParkingMy")
	public String guestParkingMy(HttpServletRequest req, Model model) {
		log.info("guestParkingMy()");
		
		service.guestParkingMy(req, model);
		
		return "guest/guest_parking/guestParkingMy";
	}

}
