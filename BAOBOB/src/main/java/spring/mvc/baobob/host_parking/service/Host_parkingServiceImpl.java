package spring.mvc.baobob.host_parking.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.baobob.host_parking.persistence.Host_parkingDAO;
import spring.mvc.baobob.vo.Parking;
import spring.mvc.baobob.vo.ParkingSpace;

@Service
public class Host_parkingServiceImpl implements Host_parkingService {

	@Autowired
	Host_parkingDAO dao;

	//������ ���� ����
	@Override
	public void getParkingSpace(HttpServletRequest req, Model model) {
		ParkingSpace ps = dao.getParkingSpace();
		model.addAttribute("pSpace", ps);
		
		model.addAttribute("page", 1);
	}
	
	//������ ���� ���� ���/����
	@Override
	public void parkingSpaceChange(HttpServletRequest req, Model model) {
		int col = Integer.parseInt(req.getParameter("col"));
		int row = Integer.parseInt(req.getParameter("row"));
		String info = req.getParameter("info");
		
		//������ ���� �迭 ����
		ParkingSpace ps = new ParkingSpace();
		ps.setP_space_col(col);
		ps.setP_space_row(row);
		ps.setP_space_info(info);
		int cnt = dao.parkingSpaceChange(ps);

		if(cnt != 0) {
			//������ �� ���� ���� ���
			System.out.println(info);
			String[] typeNums = info.split(",");
			for(int y = 0; y < row; y += 1) {
				for(int x = 0; x < col; x += 1) {
					int idx = x + y * col;
					int type = Integer.parseInt(typeNums[idx]);
					
					Parking space = new Parking();
					space.setPark_index(idx);
					space.setPark_state(0);
					space.setPark_theme(type);
					cnt = dao.parkingChange(space);
				}
			}
		}
		
		//������ �⺻ ��� ����
		
		model.addAttribute("cnt", cnt);
	}

	//������ �ǽð� ��Ȳ
	@Override
	public void getParkingSpaceState(HttpServletRequest req, Model model) {
		ParkingSpace ps = dao.getParkingSpace(); 
		
	}

}
