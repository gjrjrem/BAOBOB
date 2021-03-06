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
	
	@Override
	public void parkSignInPro(HttpServletRequest req, Model model) {
		String member_id = req.getParameter("id").trim();
		String member_pwd = req.getParameter("pwd").trim();
		
		Map<String, String> map = new HashMap<>();
		map.put("member_id", member_id);
		map.put("member_pwd", member_pwd);
		String step = mDao.confirmIdPwd(map);
		
		int cnt = 0;
		if(step != null && !step.equals("13")) {
			req.getSession().setAttribute("parkId", member_id);
			cnt = 1;
		}
		
		model.addAttribute("cnt", cnt);
	}

	// 입장 시 번호 생성
	@Override
	public void guestParkingInPro(HttpServletRequest req, Model model) {
		String member_id = (String) req.getSession().getAttribute("parkId");

		//랜덤키 만들기
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
		// 비회원일 경우 회원 목록에 비회원 추가
		if (member_id == null) { 
			member_id = key;
			Member m = new Member();
			m.setMember_id(member_id);
			m.setMember_pwd(key);
			m.setMember_name("비회원");
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

		// 오늘 BAOBOB 방문 내역 추가
		cnt = dao.historyInsert(member_id);
	
		// 주차 내역 추가
		cnt = dao.parkInHistoryInsert(key);
		

		req.getSession().setAttribute("parkId", null);
		model.addAttribute("key", key);
		model.addAttribute("cnt", cnt);
	}

	// 퇴장 번호 확인
	@Override
	public void guestParkingOutCheckPro(HttpServletRequest req, Model model) {
		String key = req.getParameter("key").trim();

		ParkingHistory ph = dao.parkingOutKeyCheck(key);
		
		model.addAttribute("key", key);
		model.addAttribute("ph", ph);
	}

	// 퇴장 처리
	@Override
	public void guestParkingPay(HttpServletRequest req, Model model) {
		String key = req.getParameter("key").trim();

		// 고객의 입차 시간
		Timestamp inTime = dao.getParkingInTime(key);
		// 현재 출차하려는 시간
		Timestamp outTime = new Timestamp(System.currentTimeMillis());
		// 고객의 주차 시간(분)
		long userTime = ((outTime.getTime() - inTime.getTime()) / 1000) / 60;

		// 주차 요금
		ParkingFee pf = hDao.getParkingFee();

		// 고객의 바오밥 멀티플렉스 이용 내역
		int movie = dao.getMovieHistoryCount(key);
		int rest = dao.getRestaurantHistoryCount(key);

		// 계산될 시간 = 고객 이용 시간 - 할인 시간
		long time = userTime - (pf.getP_fee_movie_time() * movie) - (pf.getP_fee_rest_time() * rest);
		int price = 0;
		if (time > 0) {
			// 계산될 시간 -= 기본 시간
			time -= pf.getP_fee_base_time();
			price = pf.getP_fee_base_price(); // 기본 요금
			while (time > 0) {
				time -= pf.getP_fee_exc_time();
				price += pf.getP_fee_exc_price();
			}
		}

		// 주차 내역 수정
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p_history_price", price);
		map.put("key", key);
		int cnt = dao.parkingHistoryUpdate(map);
		model.addAttribute("cnt", cnt);

		// 내역 가져오기
		ParkingHistory ph = dao.getParkingHistory(key);
		model.addAttribute("ph", ph);
		model.addAttribute("movie", movie);
		model.addAttribute("rest", rest);
		
		//영수증
		model.addAttribute("time", userTime/60 + "시간 " + userTime%60 + "분");
		model.addAttribute("mTime", (pf.getP_fee_movie_time() * movie) / 60);
		model.addAttribute("rTime", (pf.getP_fee_rest_time() * rest) / 60);
		
		//결제 금액의 10% 포인트 적립
		int point = (int) (price * 0.1);
		String id = dao.keyMemberIdSelect(key);
		if(id != null) {
			Member m = new Member();
			m.setMember_id(id);
			m.setMember_point(point);;
			dao.memberPointUpdate(m);
			model.addAttribute("point", point);
		}
	}

	// 주차 내역 출력
	@Override
	public void guestParkingMy(HttpServletRequest req, Model model) {
		String key = req.getParameter("key").trim();
		ParkingHistory ph = dao.getParkingHistory(key);
		model.addAttribute("ph", ph);
	}

}
