package spring.mvc.baobob.host_parking.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.baobob.host_parking.service.Host_parkingService;

@Controller
public class Host_parkingController {

	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	Host_parkingService service;
	
	//������ ���� ����
	@RequestMapping("hostParkingMain")
	public String hostParkingMain(HttpServletRequest req, Model model) {
		log.info("hostParkingMain()");
		
		service.hostParkingMain(req, model);
		
		return "host/host_parking/hostParkingMain";
	}
	
	//���� ajax. ���� ���� ��ȭ
	@RequestMapping("hostParkingMainSpace")
	public String hostParkingMainSpace(HttpServletRequest req, Model model) {
		log.info("hostParkingMainSpace()");
		
		service.hostParkingMainSpace(req, model);
		
		return "host/host_parking/hostParkingMainSpace";
	}
	
	//������ ���� ���� ������
	@RequestMapping("hostParkingSetting")
	public String hostParkingSetting(HttpServletRequest req, Model model) {
		log.info("hostParkingSetting()");
		
		service.getParkingSpace(req, model);
		
		return "host/host_parking/hostParkingSetting";
	}
	
	//������ ���� ���� ���/����
	@RequestMapping("hostParkingSettingChange")
	public String hostParkingSettingChange(HttpServletRequest req, Model model) {
		log.info("hostParkingSettingChange");
		
		service.parkingSpaceChange(req, model);
		
		return "host/host_parking/hostParkingSettingChange";
	}

	//�ǽð� ���� ��Ȳ
	@RequestMapping("hostParkingState")
	public String hostParkingState(HttpServletRequest req, Model model) {
		log.info("hostParkingState()");
		
		service.getParkingSpaceState(req, model);
		
		return "host/host_parking/hostParkingState";
	}
	
	//�ǽð� ���� ��Ȳ ajax
	@RequestMapping("hostParkingStateAjax")
	public String hostParkingStateAjax(HttpServletRequest req, Model model) {
		log.info("hostParkingStateAjax()");
		
		service.getParkingSpaceState(req, model);
		
		return "host/host_parking/hostParkingStateAjax";
	}
	
	//�ǽð� ���� ��Ȳ ���� ���� ajax
	@RequestMapping("hostParkingSpaceState")
	public String hostParkingSpaceState(HttpServletRequest req, Model model) {
		log.info("hostParkingSpaceState()");
		
		service.getSpaceState(req, model);
		
		return "host/host_parking/hostParkingSpaceState";
	}

	//���� ��Ȳ
	@RequestMapping("hostParkingChart")
	public String hostParkingChart(HttpServletRequest req, Model model) {
		log.info("hostParkingChart()");
		
		service.getHostParkingChart(req, model);
		
		return "host/host_parking/hostParkingChart";
	}

	//���� ��Ȳ - ajax ����
	@RequestMapping("hostParkingChartMonth")
	public String hostParkingChartMonth(HttpServletRequest req, Model model) {
		log.info("hostParkingChartMonth()");
		
		service.getHostParkingChartMonth(req, model);
		
		return "host/host_parking/hostParkingChartMonth";
	}

	//���� ���� ����
	@RequestMapping("hostParkingPay")
	public String hostParkingPay(HttpServletRequest req, Model model) {
		log.info("hostParkingPay()");
		
		service.getParkingPayList(req, model);
		
		return "host/host_parking/hostParkingPay";
	}
	
	//���� ���� ���� ajax
	@RequestMapping("hostParkingPayList")
	public String hostParkingPayList(HttpServletRequest req, Model model) {
		log.info("hostParkingPayList()");
		
		service.getParkingPayList(req, model);
		
		return "host/host_parking/hostParkingPayList";
	}
	
	//���� ���� ��Ȳ
	@RequestMapping("hostParkingPayChart")
	public String hostParkingPayChart(HttpServletRequest req, Model model) {
		log.info("hostParkingPayChart()");
		
		service.getParkingPayChart(req, model);
		
		return "host/host_parking/hostParkingPayChart";
	}
	
	//�Ƶ��̳�
	@RequestMapping("arduinoInput")
	public String arduinoInput(HttpServletRequest req, Model model) {
		
		service.arduinoInput(req,  model);
		
		return "host/host_parking/arduinoInput";
	}
}
