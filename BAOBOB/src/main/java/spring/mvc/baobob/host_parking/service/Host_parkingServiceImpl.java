package spring.mvc.baobob.host_parking.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.baobob.host_parking.persistence.Host_parkingDAO;
import spring.mvc.baobob.vo.Parking;
import spring.mvc.baobob.vo.ParkingFee;
import spring.mvc.baobob.vo.ParkingHistory;
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
					cnt = dao.parkingChange(space);
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

	//�ش� ���� ���� ����
	public void getSpaceState(HttpServletRequest req, Model model) {
		int idx = Integer.parseInt(req.getParameter("idx"));
		
		//���� ����
		Parking space = dao.getSpaceState(idx);
		model.addAttribute("space", space);
		
		//������ ���� �ð��� ��ġ�ϴ� ����
		ArrayList<String> ids = dao.getParkLastDateMember(space.getPark_last_date());
		String id = "";
		for(String str : ids) {
			id += id.equals("") ? str : ", " + str;
		}
		model.addAttribute("id", id);
	}
	
	//���� ����
	@Override
	public void getParkingHistory(HttpServletRequest req, Model model) {
		int postCnt = dao.getParkingHistoryCnt();
		if(postCnt != 0) {
			String pageNum = req.getParameter("pageNum");
			if(pageNum == null) { pageNum = "1";}
			int currentPage = Integer.parseInt(pageNum);
			
			int pageSize = 15;
			int navSize = 10;
			
			int startPost = (currentPage - 1) * pageSize + 1;
			int endPost = startPost + pageSize - 1;
			if(endPost > postCnt) { endPost = postCnt; }
			
			int navCnt = postCnt / pageSize + (postCnt % pageSize == 0 ? 0 : 1);
			int startNav = (currentPage / navSize) * navSize + 1;
			if(currentPage % navSize == 0) { startNav -= navSize;}
			int endNav = startNav + navSize - 1;
			if(endNav > navCnt) { endNav = navCnt; }
			
			model.addAttribute("startPost", startPost);
			model.addAttribute("endPost", endPost);
			model.addAttribute("pageNum", pageNum);
			model.addAttribute("startNav", startNav);
			model.addAttribute("endNav", endNav);
			model.addAttribute("navSize", navSize);
			model.addAttribute("postCnt", postCnt);
			model.addAttribute("navCnt", navCnt);
			
			int number = postCnt - startPost + 1;
			model.addAttribute("number", number);
			
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("start", startPost);
			map.put("end", endPost);
	
			ArrayList<ParkingHistory> phs = dao.getParkingHistory(map);
			model.addAttribute("phs", phs);
		}
	}
	
	//���γ���(������ ������)
	@Override
	public void getParkingPayList(HttpServletRequest req, Model model) {
		int postCnt = dao.getParkingPayCnt();
		if(postCnt != 0) {
			String pageNum = req.getParameter("pageNum");
			if(pageNum == null) pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			
			int postSize = 15;
			int navSize = 10;
			
			int startPost = (currentPage - 1) * postSize + 1;
			int endPost = startPost + postSize - 1;
			if(endPost > postCnt) endPost = postCnt;
			
			int navCnt = (postCnt / postSize) + (postCnt % postSize == 0 ? 0 : 1); 
			int startNav = (currentPage / navSize) * navSize + 1;
			if(startNav % navSize == 0) startNav -= navSize;
			int endNav = startNav + navSize - 1;
			if(endNav > navCnt) endNav = navCnt;
			
			model.addAttribute("startPost", startPost);
			model.addAttribute("endPost", endPost);
			model.addAttribute("pageNum", pageNum);
			model.addAttribute("startNav", startNav);
			model.addAttribute("endNav", endNav);
			model.addAttribute("navSize", navSize);
			model.addAttribute("postCnt", postCnt);
			model.addAttribute("navCnt", navCnt);
			
			int number = postCnt - startPost + 1;
			model.addAttribute("number", number);
			
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("start", startPost);
			map.put("end", endPost);
			
			ArrayList<ParkingHistory> phs = dao.getParkingPayList(map);
			model.addAttribute("phs", phs);
		}
	}

}
