package spring.mvc.baobob.guest_parking.Service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface Guest_parkingService {

	//���� �� ��ȣ ����
	public void guestParkingInPro(HttpServletRequest req, Model model);
	
	//���� ��ȣ Ȯ��
	public void guestParkingOutCheckPro(HttpServletRequest req, Model model);
	
}
