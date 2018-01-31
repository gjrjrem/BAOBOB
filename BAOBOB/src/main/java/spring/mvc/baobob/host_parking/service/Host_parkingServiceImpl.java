package spring.mvc.baobob.host_parking.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

	// ������ ����
	public void hostParkingMain(HttpServletRequest req, Model model) {
		// ���� �̿��ڼ�
		int notOutCount = dao.getParkingMember();
		model.addAttribute("notOut", notOutCount);

		// ��� ���� �ð�
		List<Map<String, Object>> list = dao.getAvgPakingTime();
		if (list.get(0) != null) {
			String hour = String.valueOf(list.get(0).get("HOUR"));
			String minute = String.valueOf(list.get(0).get("MINUTE"));
			model.addAttribute("hour", hour);
			model.addAttribute("minute", minute);
		} else {
			model.addAttribute("hour", 0);
			model.addAttribute("minute", 0);
		}

		// ���� ��ȭ / �Ĵ� �̿���
		int movie = dao.getParkingMovieMember();
		int rest = dao.getParkingRestaurantMember();
		model.addAttribute("movie", movie);
		model.addAttribute("rest", rest);
	}
	
	//���� ajax. ���� ���� ��ȭ
	public void hostParkingMainSpace(HttpServletRequest req, Model model) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("order", 0);
		ArrayList<Parking> psIn = dao.getParkChageState(map);
		model.addAttribute("psIn", psIn);
		
		map.put("order", 1);
		ArrayList<Parking> psOut = dao.getParkChageState(map);
		model.addAttribute("psOut", psOut);
	}
	
	// ������ ���� ����
	@Override
	public void getParkingSpace(HttpServletRequest req, Model model) {
		ParkingSpace ps = dao.getParkingSpace();
		model.addAttribute("pSpace", ps);

		ParkingFee pf = dao.getParkingFee();
		model.addAttribute("pf", pf);
	}

	// ������ ���� ���� ���/����
	@Override
	public void parkingSpaceChange(HttpServletRequest req, Model model) {
		int col = Integer.parseInt(req.getParameter("col"));
		int row = Integer.parseInt(req.getParameter("row"));
		String info = req.getParameter("info");

		// ������ ���� �迭 ����
		ParkingSpace ps = new ParkingSpace();
		ps.setP_space_col(col);
		ps.setP_space_row(row);
		ps.setP_space_info(info);
		int cnt = dao.parkingSpaceChange(ps);

		if (cnt != 0) {
			// ������ �� ���� ���� ���
			String[] typeNums = info.split(",");
			for (int y = 0; y < row; y += 1) {
				for (int x = 0; x < col; x += 1) {
					int idx = x + y * col;
					int type = Integer.parseInt(typeNums[idx]);

					Parking space = new Parking();
					space.setPark_index(idx);
					space.setPark_state(1);
					space.setPark_theme(type);
					cnt = dao.parkingChange(space);
				}
			}

			// ������ �⺻ ��� ����
			int baseTime = Integer.parseInt(req.getParameter("baseTime"));
			int baseFee = Integer.parseInt(req.getParameter("baseFee"));
			int excTime = Integer.parseInt(req.getParameter("excTime"));
			int excFee = Integer.parseInt(req.getParameter("excFee"));
			int movieTime = Integer.parseInt(req.getParameter("movieTime"));
			int restTime = Integer.parseInt(req.getParameter("restTime"));

			ParkingFee pf = new ParkingFee();
			pf.setP_fee_base_price(baseFee);
			pf.setP_fee_base_time(baseTime);
			pf.setP_fee_exc_price(excFee);
			pf.setP_fee_exc_time(excTime);
			pf.setP_fee_movie_time(movieTime);
			pf.setP_fee_rest_time(restTime);

			cnt = dao.parkingFeeChange(pf);
		}

		model.addAttribute("cnt", cnt);
	}

	// ������ �ǽð� ����
	@Override
	public void getParkingSpaceState(HttpServletRequest req, Model model) {
		ParkingSpace ps = dao.getParkingSpace();
		if(ps != null) {
			int col = ps.getP_space_col();
			int row = ps.getP_space_row();
			int last_idx = col * row;
			
			//������ ���� Ÿ�� ����
			String[] spaces = ps.getP_space_info().split(","); 
			
			//������ ���� ���� ����
			ArrayList<String> list = dao.getParkingStates(last_idx);
			String states = "";
			for (int i = 0; i < list.size(); i += 1) {
				if(spaces[i].equals("0") || spaces[i].equals("9") || spaces[i].equals("8")) {
					list.set(i, "2");
				}
				states += states.equals("") ? list.get(i) : "," + list.get(i);
			}
	
			model.addAttribute("col", col);
			model.addAttribute("row", row);
			model.addAttribute("states", states);
		}
	}

	// �ش� ���� ���� ����
	public void getSpaceState(HttpServletRequest req, Model model) {
		int idx = Integer.parseInt(req.getParameter("idx"));
		String spaceNumber = req.getParameter("spaceNumber");

		// ���� ����
		Parking space = dao.getSpaceState(idx);
		model.addAttribute("space", space);

		// ������ ���� �ð��� ��ġ�ϴ� ����
		ArrayList<String> ids = dao.getParkLastDateMember(space.getPark_last_date());
		String id = "";
		for (String str : ids) {
			id += id.equals("") ? str : ", " + str;
		}
		model.addAttribute("id", id);
		model.addAttribute("spaceNumber", spaceNumber);
	}

	// ���� ��Ȳ ��Ʈ
	public void getHostParkingChart(HttpServletRequest req, Model model) {
		// 1
		// �Ϸ� ��� ���� �ð�, �Ϸ� ��� ���� �ð�
		List<Map<String, Object>> dayInTimeAvg = dao.getDayInTimeAvg();
		List<Map<String, Object>> dayOutTimeAvg = dao.getDayOutTimeAvg();

		String[] time = { "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21" };

		// dayInTimeAvg : List<Map<String, Object>> =>> Map<String, Object>���� �ٲٱ�
		Map<String, Object> inMap = new HashMap<String, Object>();
		for (Map<String, Object> map : dayInTimeAvg) {
			String hour = null;
			Object avg = null;

			for (Entry<String, Object> e : map.entrySet()) {
				if (e.getKey().equals("HOUR")) {
					hour = String.valueOf(e.getValue());
				} else if (e.getKey().equals("AVG")) {
					avg = e.getValue();
				}
			}
			if (hour != null && avg != null) inMap.put(hour, avg);
		}
		// dayOutTimeAvg : List<Map<String, Object>> =>> Map<String, Object>���� �ٲٱ�
		Map<String, Object> outMap = new HashMap<String, Object>();
		for (Map<String, Object> map : dayOutTimeAvg) {
			String hour = null;
			Object avg = null;

			for (Entry<String, Object> e : map.entrySet()) {
				if (e.getKey().equals("HOUR")) {
					hour = String.valueOf(e.getValue());
				} else if (e.getKey().equals("AVG")) {
					avg = e.getValue();
				}
			}
			if (hour != null && avg != null) outMap.put(hour, avg);
		}
		// dayInTimeAvg, dayOutTimeAvg ��Ʈ ��� Attribute�� ����
		for (int i = 0; i < time.length; i += 1) {
			boolean flg1 = false;
			boolean flg2 = false;

			for (Entry<String, Object> e : inMap.entrySet()) {
				if (e.getKey().equals(time[i])) {
					model.addAttribute("HI" + e.getKey(), e.getValue());
					flg1 = true;
				}
			}
			if (!flg1) model.addAttribute("HI" + time[i], "0");
			
			for (Entry<String, Object> e : outMap.entrySet()) {
				if (e.getKey().equals(time[i])) {
					model.addAttribute("HO" + e.getKey(), e.getValue());
					flg2 = true;
				}
			}
			if (!flg2) model.addAttribute("HO" + time[i], "0");
		}

		// 2
		// ���� ���Ϻ� ����/���� ��
		List<Map<String, Object>> weekIn = dao.getWeekIn();
		List<Map<String, Object>> weekOut = dao.getWeekOut();

		Map<String, Object> weekInMap = new HashMap<String, Object>();
		for (Map<String, Object> map : weekIn) {
			String key = null;
			Object value = null;
			for (Entry<String, Object> e : map.entrySet()) {
				if (e.getKey().equals("COUNT")) {
					value = e.getValue();
				} else if (e.getKey().equals("DAY")) {
					key = String.valueOf(e.getValue());
				}
			}
			if (key != null && value != null) weekInMap.put(key, value);
		}
		Map<String, Object> weekOutMap = new HashMap<String, Object>();
		for (Map<String, Object> map : weekOut) {
			String key = null;
			Object value = null;
			for (Entry<String, Object> e : map.entrySet()) {
				if (e.getKey().equals("COUNT")) {
					value = e.getValue();
				} else if (e.getKey().equals("DAY")) {
					key = String.valueOf(e.getValue());
				}
			}
			if (key != null && value != null) weekOutMap.put(key, value);
		}

		String[] week = { "2", "3", "4", "5", "6", "7", "1" };

		for (int i = 0; i < week.length; i += 1) {
			boolean flg1 = false;
			boolean flg2 = false;

			for (Entry<String, Object> e : weekInMap.entrySet()) {
				if (e.getKey().equals(week[i])) {
					flg1 = true;
					model.addAttribute("I" + e.getKey(), e.getValue());
				}
			}
			for (Entry<String, Object> e : weekOutMap.entrySet()) {
				if (e.getKey().equals(week[i])) {
					flg2 = true;
					model.addAttribute("O" + e.getKey(), e.getValue());
				}
			}

			if (!flg1) model.addAttribute("I" + week[i], "0");
			if (!flg2) model.addAttribute("O" + week[i], "0");

		}

		// 3
		// �� ����
		List<Map<String, Object>> sexList = dao.getParkingSexRatio();
		Map<String, Object> sexMap = new HashMap<String, Object>();
		for (Map<String, Object> map : sexList) {
			Object key = null;
			Object value = null;
			for (Entry<String, Object> e : map.entrySet()) {
				if (e.getKey().equals("SEX")) {
					key = e.getValue();
				} else if (e.getKey().equals("COUNT")) {
					value = e.getValue();
				}
			}
			if (key != null & value != null)
				sexMap.put(String.valueOf(key), value);
		}
		String[] sexs = {"null", "��", "��"};
		for(int i = 0; i < sexs.length; i += 1) {
			boolean flg = false;
			for(Entry<String, Object> e : sexMap.entrySet()) {
				if(e.getKey().equals(sexs[i])) {
					model.addAttribute("sex" + i, e.getValue());
					flg = true;
				}
			}
			if(!flg) model.addAttribute("sex" + i, 0);
		}
	}

	// ���� ��Ȳ ��Ʈ - ajax ���� ����
	public void getHostParkingChartMonth(HttpServletRequest req, Model model) {
		List<Map<String, Object>> mothIn = dao.getMonthIn();
		List<Map<String, Object>> mothOut = dao.getMonthOut();

		Map<String, Object> weekInMap = new HashMap<String, Object>();
		for (Map<String, Object> map : mothIn) {
			String key = null;
			Object value = null;
			for (Entry<String, Object> e : map.entrySet()) {
				if (e.getKey().equals("COUNT")) {
					value = e.getValue();
				} else if (e.getKey().equals("MON")) {
					key = String.valueOf(e.getValue());
				}
			}
			if (key != null && value != null) {
				weekInMap.put(key, value);
			}
		}
		Map<String, Object> weekOutMap = new HashMap<String, Object>();
		for (Map<String, Object> map : mothOut) {
			String key = null;
			Object value = null;
			for (Entry<String, Object> e : map.entrySet()) {
				if (e.getKey().equals("COUNT")) {
					value = e.getValue();
				} else if (e.getKey().equals("MON")) {
					key = String.valueOf(e.getValue());
				}
			}
			if (key != null && value != null) {
				weekOutMap.put(key, value);
			}
		}

		String[] week = { "01", "02", "03", "04", "0", "06", "07", "08", "09", "10", "11", "12" };

		for (int i = 0; i < week.length; i += 1) {
			boolean flg1 = false;
			boolean flg2 = false;

			for (Entry<String, Object> e : weekInMap.entrySet()) {
				if (e.getKey().equals(week[i])) {
					flg1 = true;
					model.addAttribute("I" + e.getKey(), e.getValue());
				}
			}
			for (Entry<String, Object> e : weekOutMap.entrySet()) {
				if (e.getKey().equals(week[i])) {
					flg2 = true;
					model.addAttribute("O" + e.getKey(), e.getValue());
				}
			}

			if (!flg1) {
				model.addAttribute("I" + week[i], "0");
			}
			if (!flg2) {
				model.addAttribute("O" + week[i], "0");
			}
		}
	};

	// ���γ���(������ ������)
	@Override
	public void getParkingPayList(HttpServletRequest req, Model model) {
		String standard = req.getParameter("standard");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("standard", standard);
		
		int postCnt = dao.getParkingPayCnt();
		if (postCnt != 0) {
			String pageNum = req.getParameter("pageNum");
			if (pageNum == null)
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);

			int postSize = 15;
			int navSize = 10;

			int startPost = (currentPage - 1) * postSize + 1;
			int endPost = startPost + postSize - 1;
			if (endPost > postCnt)
				endPost = postCnt;

			int navCnt = (postCnt / postSize) + (postCnt % postSize == 0 ? 0 : 1);
			int startNav = (currentPage / navSize) * navSize + 1;
			if (startNav % navSize == 0)
				startNav -= navSize;
			int endNav = startNav + navSize - 1;
			if (endNav > navCnt)
				endNav = navCnt;

			model.addAttribute("startPost", startPost);
			model.addAttribute("endPost", endPost);
			model.addAttribute("pageNum", pageNum);
			model.addAttribute("startNav", startNav);
			model.addAttribute("endNav", endNav);
			model.addAttribute("navSize", navSize);
			model.addAttribute("navCnt", navCnt);

			int number = postCnt - startPost + 1;
			model.addAttribute("number", number);

			map.put("start", startPost);
			map.put("end", endPost);

			ArrayList<ParkingHistory> phs = dao.getParkingPayList(map);
			model.addAttribute("phs", phs);
		}
		model.addAttribute("postCnt", postCnt);
	}

	// ���� ��Ȳ
	@Override
	public void getParkingPayChart(HttpServletRequest req, Model model) {
		// ���� ���� �ð��� ���� �ݾװ� ���� ���� �ݾ�

		// ���� ���
		ParkingFee fee = dao.getParkingFee();

		// ���� ���� ����
		ArrayList<ParkingHistory> thisPh = dao.getThisYearPayList();

		Map<String, Integer> timePrice = new HashMap<String, Integer>(); // ���� ���� �ð��� ���� �ݾ�
		Map<String, Integer> userPrice = new HashMap<String, Integer>(); // ���� ���� �ݾ�
		for (ParkingHistory ph : thisPh) {
			long userTime = ph.getP_history_out().getTime() - ph.getP_history_in().getTime();
			long minute = (userTime / 1000) / 60; // �̿� �ð�(��)

			String month = ph.getP_history_out().toString().substring(5, 7); // �̿��� ��
			long time = minute - fee.getP_fee_base_time(); // �̿� �ʰ� �ð�
			int price = fee.getP_fee_base_price(); // �⺻ ���
			while (time > 0) {
				time -= fee.getP_fee_exc_time();
				price += fee.getP_fee_exc_price();
			}

			if (timePrice.get("P" + month) != null) {
				timePrice.put("P" + month, timePrice.get("P" + month) + price);
				userPrice.put("U" + month, userPrice.get("U" + month) + ph.getP_history_price());
			} else {
				timePrice.put("P" + month, price);
				userPrice.put("U" + month, ph.getP_history_price());
			}
		}

		String[] month = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		for (int i = 0; i < month.length; i += 1) {
			boolean flg = false;
			for (Entry<String, Integer> e : timePrice.entrySet()) {
				if (e.getKey().equals("P" + month[i])) {
					flg = true;
					model.addAttribute("P" + month[i], e.getValue());
					model.addAttribute("U" + month[i], userPrice.get("U" + month[i]));
				}
			}
			if (!flg) {
				model.addAttribute("P" + month[i], 0);
				model.addAttribute("U" + month[i], 0);
			}
		}
		
		//����
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("year",  0);
		String totalPrice = dao.getTotalPrice(map);
		if(totalPrice == null) totalPrice = "0"; 
		map.put("year",  1);
		String yearPrice = dao.getTotalPrice(map); //���� ����
		if(yearPrice == null) yearPrice = "0"; 
		map.put("year",  -1);
		String prevPrice = dao.getTotalPrice(map);
		if(prevPrice == null) prevPrice = "0";
		
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("yearPrice", yearPrice);
		model.addAttribute("prevPrice", prevPrice);
	}

	//�Ƶ��̳�. ���� ���� ����
	public void arduinoInput(HttpServletRequest req, Model model) {
		//1. �� ���� (pin1~pin6)
		Map<Integer, Object> pinMap = new HashMap<Integer, Object>();
		for(int i = 1; i <= 6; i += 1) {
			String pin = req.getParameter("pin" + i);
			if(pin != null) {
				pinMap.put(i - 1, pin);
			} else {
				pinMap.put(i  -1, 0);
			}
		}
		
		//���� �κ�. �׽�Ʈ �ʿ�
		ParkingSpace ps = dao.getParkingSpace();
		
		// ������ ���� ���� ����
		int last_idx = ps.getP_space_col() * ps.getP_space_row();
		ArrayList<String> list = dao.getParkingStates(last_idx);
		
		// ������ ���� Ÿ�� ����
		String[] spaces = ps.getP_space_info().split(",");
		
		//2. �ε��� ����(���� ������ ����)
		int count = 0;
		Map<Integer, Object> idxMap = new HashMap<Integer, Object>();
		for(int i = 0; i < list.size(); i += 1) {
			if(!spaces[i].equals("0")) { //������� �ƴϸ�(���� �����̸�. 1~4)
				idxMap.put(count, i);
				count++;
			}
			if(count == 6) { break; }
		}
		
		//3. �ش� �ε����� �� ����
		int cnt = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		for(int i = 0 ; i < 6; i += 1) {
			map.put("index", idxMap.get(i));
			map.put("pin", pinMap.get(i));
			cnt = dao.arduinoInput(map);
		}
		
		model.addAttribute("cnt", cnt);
	}
}
