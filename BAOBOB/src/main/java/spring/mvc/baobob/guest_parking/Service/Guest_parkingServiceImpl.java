package spring.mvc.baobob.guest_parking.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.baobob.guest_parking.persistence.Guest_parkingDAO;
import spring.mvc.baobob.persistence.MainDAO;
import spring.mvc.baobob.vo.Member;

@Service
public class Guest_parkingServiceImpl implements Guest_parkingService{

	@Autowired
	Guest_parkingDAO dao;
	@Autowired
	MainDAO mDao;
	
	//���� �� ��ȣ ����
	@Override
	public void guestParkingInPro(HttpServletRequest req, Model model) {
		String member_id = (String) req.getSession().getAttribute("memId");
		
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		for(int i = 0; i < 6; i += 1) {
			int rIdx = r.nextInt(2);
			switch(rIdx) {
			case 0: sb.append((char) ((int)(r.nextInt(26)) + 65));
			case 1: sb.append(r.nextInt(10));
			}
		}
		String key = sb.toString();
		
		int cnt = 0;
		if(member_id == null) { //��ȸ���� ��� ȸ�� ��Ͽ� ��ȸ�� �߰�
			member_id = key;
			Member m = new Member();
			m.setMember_id(member_id);
			m.setMember_pwd("0000");
			m.setMember_name("��ȸ��");
			m.setMember_tel(req.getParameter("tel"));
			m.setMember_email("null");
			m.setMember_birth("null");
			m.setMember_sex("null");
			m.setMember_address("null");
			m.setMember_point(0);
			m.setMember_step(9);
			m.setMember_cumPoint(0);
			cnt = mDao.memberInsert(m);
		}
		
		String index = dao.historyDateCheck(member_id);
		if(index == null) { //�������� history_index�� ���� ��� �߰�
			cnt = dao.historyInsert(member_id);
			if(cnt != 0) {
				index = dao.historyDateCheck(member_id);
			}
		} 
		//�ٽ� history_index�� �����ϴ� �� Ȯ��
		if(index != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("history_index", index);
			map.put("key",  key);
			cnt = dao.parkInHistoryInsert(map);
		}
		
		req.getSession().invalidate();
		model.addAttribute("key", key);
		model.addAttribute("cnt", cnt);
	}

	//���� ��ȣ Ȯ��
	@Override
	public void guestParkingOutCheckPro(HttpServletRequest req, Model model) {
		String key = req.getParameter("key");

		int mem = 0;
		int cnt = dao.parkingOutKeyCheck(key);
		if(cnt != 0) {
			mem = dao.parkingOutMemberCheck(key);
		}
		
		model.addAttribute("cnt", cnt);
		model.addAttribute("mem", mem);
	}

}
