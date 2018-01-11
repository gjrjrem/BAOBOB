package spring.mvc.baobob.host_parking.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.baobob.host_parking.persistence.Host_parkingDAO;
import spring.mvc.baobob.vo.Parking;
import spring.mvc.baobob.vo.ParkingFee;
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
		
		ParkingFee pf = dao.getParkingFee();
		model.addAttribute("pf", pf);
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
					//cnt = dao.parkingChange(space);
				}
			}
		
			//������ �⺻ ��� ����
			int baseTime = Integer.parseInt(req.getParameter("baseTime"));
			int baseFee = Integer.parseInt(req.getParameter("baseFee"));
			int excTime = Integer.parseInt(req.getParameter("excTime"));
			int excFee = Integer.parseInt(req.getParameter("excFee"));
			
			ParkingFee pf = new ParkingFee();
			pf.setP_fee_base_price(baseFee);
			pf.setP_fee_base_time(baseTime);
			pf.setP_fee_exc_price(excFee);
			pf.setP_fee_exc_time(excTime);
			
			cnt = dao.parkingFeeChange(pf);
		}
		
		model.addAttribute("cnt", cnt);
	}

	//������ �ǽð� ��Ȳ
	@Override
	public void getParkingSpaceState(HttpServletRequest req, Model model) {
		ParkingSpace ps = dao.getParkingSpace(); 
		int col = ps.getP_space_col();
		int row = ps.getP_space_row();
		int last_idx =  col * row; 
		
		//������ ���� ���� ����
		ArrayList<String> list = dao.getParkingStates(last_idx);
		String states = "";
		for(String state : list) {
			states += states.equals("") ? state : "," + state ; 
		}
		
		model.addAttribute("col", col);
		model.addAttribute("row", row);
		model.addAttribute("states", states);
	}

}
