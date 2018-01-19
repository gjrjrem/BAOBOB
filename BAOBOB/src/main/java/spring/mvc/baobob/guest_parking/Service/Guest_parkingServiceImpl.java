package spring.mvc.baobob.guest_parking.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.baobob.guest_parking.persistence.Guest_parkingDAO;
import spring.mvc.baobob.host_parking.persistence.Host_parkingDAO;
import spring.mvc.baobob.persistence.MainDAO;
import spring.mvc.baobob.vo.Member;
import spring.mvc.baobob.vo.ParkingFee;
import spring.mvc.baobob.vo.ParkingHistory;

@Service
public class Guest_parkingServiceImpl implements Guest_parkingService {

	@Autowired
	Guest_parkingDAO dao;
	@Autowired
	MainDAO mDao;
	@Autowired
	Host_parkingDAO hDao;

	// ���� �� ��ȣ ����
	@Override
	public void guestParkingInPro(HttpServletRequest req, Model model) {
		String member_id = (String) req.getSession().getAttribute("memId");

		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		for (int i = 0; i < 6; i += 1) {
			int rIdx = r.nextInt(2);
			switch (rIdx) {
			case 0:
				sb.append((char) ((int) (r.nextInt(26)) + 65));
			case 1:
				sb.append(r.nextInt(10));
			}
		}
		String key = sb.toString();

		int cnt = 0;
		if (member_id == null) { // ��ȸ���� ��� ȸ�� ��Ͽ� ��ȸ�� �߰�
			member_id = key;
			Member m = new Member();
			m.setMember_id(member_id);
			m.setMember_pwd(key);
			m.setMember_name("��ȸ��");
			m.setMember_tel("010-0000-0000");
			m.setMember_email(req.getParameter("email"));
			m.setMember_birth("null");
			m.setMember_sex("null");
			m.setMember_address("null");
			m.setMember_point(0);
			m.setMember_step(8);
			m.setMember_cumPoint(0);
			cnt = mDao.memberInsert(m);

			model.addAttribute("step", "8");
		}

		// ���� BAOBOB �湮 ����
		String index = dao.historyDateCheck(member_id);
		if (index == null) { // �湮 ������ ���� ��� �߰�
			cnt = dao.historyInsert(member_id);
			if (cnt != 0) {
				index = dao.historyDateCheck(member_id);
			}
		}
		// �ٽ� �湮 ������ �����ϴ� �� Ȯ��
		if (index != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("history_index", index);
			map.put("key", key);
			cnt = dao.parkInHistoryInsert(map);
		}

		req.getSession().invalidate();
		model.addAttribute("key", key);
		model.addAttribute("cnt", cnt);
	}

	// ���� ��ȣ Ȯ��
	@Override
	public void guestParkingOutCheckPro(HttpServletRequest req, Model model) {
		String key = req.getParameter("key").trim();

		int mem = 0;
		int cnt = dao.parkingOutKeyCheck(key);
		if (cnt != 0) {
			mem = dao.parkingOutMemberCheck(key);
		}

		model.addAttribute("key", key);
		model.addAttribute("cnt", cnt);
		model.addAttribute("mem", mem);
	}

	// ���� ó��
	@Override
	public void guestParkingPay(HttpServletRequest req, Model model) {
		String key = req.getParameter("key");

		// ���� ���� �ð�
		Timestamp inTime = dao.getParkingInTime(key);
		// ���� �����Ϸ��� �ð�
		Timestamp outTime = new Timestamp(System.currentTimeMillis());
		// ���� ���� �ð�(��)
		long userTime = ((outTime.getTime() - inTime.getTime()) / 1000) / 60;

		// ���� ���
		ParkingFee pf = hDao.getParkingFee();

		// ���� �ٿ��� ��Ƽ�÷��� �̿� ����
		int movie = dao.getMovieHistoryCount(key);
		int rest = dao.getRestaurantHistoryCount(key);

		// ���� �ð� = �� �̿� �ð� - ���� �ð�
		long time = userTime - (pf.getP_fee_movie_time() * movie) - (pf.getP_fee_rest_time() * rest);
		int price = 0;
		if (time > 0) {
			// ���� �ð� -= �⺻ �ð�
			time -= pf.getP_fee_base_time();
			price = pf.getP_fee_base_price(); // �⺻ ���
			while (time > 0) {
				time -= pf.getP_fee_exc_time();
				price += pf.getP_fee_exc_price();
			}
		}

		// ���� ���� ����
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p_history_price", price);
		map.put("key", key);
		int cnt = dao.parkingHistoryUpdate(map);
		model.addAttribute("cnt", cnt);

		// ���� ��������
		ParkingHistory ph = dao.getParkingHistory(key);
		model.addAttribute("ph", ph);
		model.addAttribute("movie", movie);
		model.addAttribute("rest", rest);
		
		//������
		model.addAttribute("time", userTime/60 + "�ð� " + userTime%60 + "��");
		model.addAttribute("mTime", (pf.getP_fee_movie_time() * movie) / 60);
		model.addAttribute("rTime", (pf.getP_fee_rest_time() * rest) / 60);
	}

	// ���� ���� ���
	@Override
	public void guestParkingMy(HttpServletRequest req, Model model) {
		String key = req.getParameter("key").trim();
		ParkingHistory ph = dao.getParkingHistory(key);
		model.addAttribute("ph", ph);
	}

}
