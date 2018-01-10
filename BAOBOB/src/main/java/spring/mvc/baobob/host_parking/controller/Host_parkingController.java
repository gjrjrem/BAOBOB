package spring.mvc.baobob.host_parking.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.baobob.host_parking.service.Host_parkingService;

@Controller
public class Host_parkingController {

	@Autowired
	Host_parkingService service;
	
	//������ ���� ���� ������
	@RequestMapping("hostParkingSetting")
	public String hostParkingSetting(HttpServletRequest req, Model model) {
		System.out.println("hostParkingSetting()");
		
		service.getParkingSpace(req, model);
		
		return "host/host_parking/hostParkingSetting";
	}
	
	//������ ���� ���� ���/����
	@RequestMapping("hostParkingSettingChange")
	public String hostParkingSettingChange(HttpServletRequest req, Model model) {
		System.out.println("hostParkingSettingChange");
		
		service.parkingSpaceChange(req, model);
		
		return "host/host_parking/hostParkingSettingChange";
	}

	@RequestMapping("hostParkingState")
	public String hostParkingState(HttpServletRequest req, Model model) {
		System.out.println("hostParkingState()");
		return "host/host_parking/hostParkingState";
	}

	@RequestMapping("hostParkingChart")
	public String hostParkingChart(HttpServletRequest req, Model model) {
		System.out.println("hostParkingChart()");
		return "host/host_parking/hostParkingChart";
	}

	@RequestMapping("hostParkingPay")
	public String hostParkingPay(HttpServletRequest req, Model model) {
		System.out.println("hostParkingPay()");
		return "host/host_parking/hostParkingPay";
	}
	
	@RequestMapping("main/mainJoin")
	public String mainJoin() {
		System.out.println("mainJoin");
		return "main/mainJoin";
	}
}
