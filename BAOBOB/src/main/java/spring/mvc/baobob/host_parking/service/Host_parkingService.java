package spring.mvc.baobob.host_parking.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface Host_parkingService {

	//������ ���� ����
	public void getParkingSpace(HttpServletRequest req, Model model);
	
	//������ ���� ���� ���/����
	public void parkingSpaceChange(HttpServletRequest req, Model model);
}
