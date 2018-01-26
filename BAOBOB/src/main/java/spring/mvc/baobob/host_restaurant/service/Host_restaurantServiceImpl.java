package spring.mvc.baobob.host_restaurant.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import spring.mvc.baobob.host_restaurant.persistence.Host_restaurantDAO;
import spring.mvc.baobob.vo.EmployeeVO;
import spring.mvc.baobob.vo.Member;
import spring.mvc.baobob.vo.MenuVO;
import spring.mvc.baobob.vo.RestaurantVO;
import spring.mvc.baobob.vo.Restaurant_ChartVO;
import spring.mvc.baobob.vo.Restaurant_scheduleVO;
import spring.mvc.baobob.vo.TableVO;
import spring.mvc.baobob.vo.hostTChartVO;

@Service
public class Host_restaurantServiceImpl implements Host_restaurantService {
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	Host_restaurantDAO dao;

	// ���� ����Ʈ
	@Override
	public void restaurantList(HttpServletRequest req, Model model) {
		log.debug("service.restaurantList()");

		int cnt = 0;

		// ���� �� ��ȸ
		cnt = dao.getRestaurantCnt();

		// ��� ���� ����
		ArrayList<RestaurantVO> dtos = dao.getRestaurantList();

		// ���� ��, ��� ���� ���� ����
		model.addAttribute("cnt_restaurant", cnt); // ���� ��
		model.addAttribute("dtos_restaurant", dtos); // ��� ���� ����
	}

	// ���� �߰� ó��
	@Override
	public void restaurantAdd(HttpServletRequest req, Model model) {
		log.debug("service.restaurantAdd()");

		int cnt = 0;
		
		// ���̺� index
		int index = 0;

		// Ÿ���� ����(���̺����� ��������)
		String info = req.getParameter("info");
		
		// ������ �����ϴ� Ÿ���� �࿭ (��:5*5)
		int col = Integer.parseInt(req.getParameter("col")); // ��
		int row = Integer.parseInt(req.getParameter("row")); // ��
		
		// ���� ����
		String name = req.getParameter("name");	// �����
		String tel = req.getParameter("tel"); // ���� ��ȭ��ȣ

		// ���� ���� ����
		RestaurantVO restaurant_dto = new RestaurantVO();
		restaurant_dto.setRestaurant_name(name);
		restaurant_dto.setRestaurant_tel(tel);

		// ���� �߰� ó��
		cnt = dao.addRestaurant(restaurant_dto);

		// ���� �߰��� �����ߴٸ�
		if (cnt != 0) {
			// Ÿ�� �ϳ� �ϳ��� ���¸� ���� ���� info�� �ڸ�
			String[] state = info.split(",");

			// ����ŭ �ݺ�
			for (int i = 0; i < row; i++) {
				// �ุŭ �ݺ�
				for (int j = 0; j < col; j++) {
					// ���̺� ���� ����
					TableVO table_dto = new TableVO();

					table_dto.setRestaurant_table_index(index);
					table_dto.setState(state[index]);
					table_dto.setTable_row(i);
					table_dto.setTable_col(j);

					// ���峻 ���̺� �߰� ó��
					cnt = dao.addTable(table_dto);

					// ���̺� �߰��� �����ߴٸ�
					if (cnt != 0) {
						index++;	// ���̺� index ����
					}
				}
			}
		}

		// ���� ���� ����
		model.addAttribute("cnt", cnt);
	}

	// �Ĵ� �� ������ - ������ ���� ���� ��ȸ
	// �Ĵ� ������ - ���� �߰��� ���̺� ������ ���� ���� ���� ��ȸ
	@Override
	public void restaurantView(HttpServletRequest req, Model model) {
		log.debug("service.restaurantView()");

		String str = req.getParameter("count");
		
		// ������ ���̺� ����
		if ((str != null && str.equals("")) || (str != null && str.length() != 0)) {
			Integer count = Integer.parseInt(str);
			// ������ ���̺� ���� ����
			model.addAttribute("count", count);
		}
		
		// ������ ��¥�� �ð�
		String date = "20" + req.getParameter("date");
		String time = req.getParameter("time");
		
		// ���� ��ȣ
		int restaurant_index = Integer.parseInt(req.getParameter("index"));

		String info = "";
		int col = 0;
		int row = 0;
		
		// ���� ����
		RestaurantVO restaurant_dto = new RestaurantVO();	
		
		// ���峻 ���̺� ����
		TableVO table_dto = new TableVO();	
		
		// ���� ������ �����ϱ� ���� �� �̿�
		Map<String, Object> map = new HashMap<String, Object>();

		// �Ĵ� ������ - ���� �߰��� ���̺� ������ ���� ���� ���� ��ȸ
		if ((time != null && time.equals("")) || (time != null && time.length() != 0)) {
			// ��¥�� �ð� ����
			model.addAttribute("date", req.getParameter("date"));
			model.addAttribute("time", time);
			
			// DB�� �ִ� startTime �÷��� ������ ����
			String startTime = date + " " + time + ":00";

			// �ð��� ��, �� ���·� �ڸ�
			String[] end = time.split(":");

			// ���� �ð��� ���� 00���̸� 30������ �ٲ�
			if (end[1].equals("00")) {
				end[1] = "30";
			}
			// ���� �ð��� ���� 30���̸� �ÿ� 1�� �����ְ� 00������ �ٲ�
			else if (end[1].equals("30")) {
				end[0] = String.valueOf((Integer.parseInt(end[0]) + 1));
				end[1] = "00";
			}

			// ��, ��, �� ����
			String endTime = end[0] + ":" + end[1] + ":00";
			
			// ��¥, �ð� ����
			endTime = date + " " + endTime;

			// ������ ����, ������ ��¥ �ð� ���� ����
			Restaurant_scheduleVO schedule_dto = new Restaurant_scheduleVO();
			schedule_dto.setRestaurant_index(restaurant_index);
			schedule_dto.setSchedule_startTime(Timestamp.valueOf(startTime));
			schedule_dto.setSchedule_endTime(Timestamp.valueOf(endTime));

			// ������ �ε��� ��ȸ
			Integer schedule_index = dao.getScheduleIndex(schedule_dto);

			// ���� ���� ��ȸ
			restaurant_dto = dao.viewRestaurant(restaurant_index);
			
			// ������ �����ϴ� Ÿ���� �࿭ ��ȸ (��:5*5)
			table_dto = dao.getColRow(restaurant_index);

			col = table_dto.getTable_col() + 1;
			row = table_dto.getTable_row() + 1;

			int restaurant_table_index = 0;

			map.put("restaurant_index", restaurant_index);
			map.put("restaurant_table_index", restaurant_table_index);
			map.put("restaurant_schedule_index", schedule_index);

			// ����ŭ �ݺ�
			for (int i = 0; i < row; i++) {
				// �ุŭ �ݺ�
				for (int j = 0; j < col; j++) {
					map.replace("restaurant_table_index", restaurant_table_index);

					// Ÿ�� �ϳ��ϳ��� ���¸� info�� �߰�
					info += dao.getState(map);

					// �� ������ ������ �ƴϸ� ',' �߰�
					if (!(i + 1 == row && j + 1 == col)) {
						info += ',';
						restaurant_table_index++;	// ���̺� �ε��� 1 ����
					}
				}
			}
		} 

		// �Ĵ� �� ������ - ������ ���� ���� ��ȸ
		else {
			// ���� ���� ��ȸ
			restaurant_dto = dao.viewRestaurant(restaurant_index);

			// ������ �����ϴ� Ÿ���� �࿭ ��ȸ (��:5*5)
			table_dto = dao.getColRow(restaurant_index);

			col = table_dto.getTable_col() + 1;
			row = table_dto.getTable_row() + 1;

			int restaurant_table_index = 0;

			map.put("restaurant_index", restaurant_index);
			map.put("restaurant_table_index", restaurant_table_index);

			// ����ŭ �ݺ�
			for (int i = 0; i < row; i++) {
				// �ุŭ �ݺ�
				for (int j = 0; j < col; j++) {
					map.replace("restaurant_table_index", restaurant_table_index);

					// Ÿ�� �ϳ��ϳ��� ���¸� info�� �߰�
					info += dao.getState(map);

					// �� ������ ������ �ƴϸ� ',' �߰�
					if (!(i + 1 == row && j + 1 == col)) {
						info += ',';
						restaurant_table_index++;	// ���̺� �ε��� 1 ����
					}
				}
			}
		}
		
		// ���� ���� ����
		model.addAttribute("dto", restaurant_dto);
		
		// script.js - spaceBody(), spaceBody2()���� ����� ������
		model.addAttribute("info", info);
		model.addAttribute("col", col);
		model.addAttribute("row", row);
	}

	// �Ĵ� �� ������ - ���� ���� ó��
	@Override
	public void restaurantMod(HttpServletRequest req, Model model) {
		log.debug("service.restaurantMod()");

		int cnt = 0;
		int index = 0;

		// Ÿ���� ����(���̺����� ��������)
		String info = req.getParameter("info");
		
		// ������ �����ϴ� Ÿ���� �࿭ (��:5*5)
		int col = Integer.parseInt(req.getParameter("col")); // ��
		int row = Integer.parseInt(req.getParameter("row")); // ��

		// ���� ��ȣ
		int restaurant_index = Integer.parseInt(req.getParameter("index"));
		
		// ���� ����
		String name = req.getParameter("name");	// �����
		String tel = req.getParameter("tel"); // ���� ��ȭ��ȣ
		
		// ���� ���� ����
		RestaurantVO restaurant_dto = new RestaurantVO();
		restaurant_dto.setRestaurant_index(restaurant_index);
		restaurant_dto.setRestaurant_name(name);
		restaurant_dto.setRestaurant_tel(tel);

		// ���� ���� ó��
		cnt = dao.modRestaurant(restaurant_dto);

		// ���� ó���� �����ߴٸ�
		if (cnt != 0) {
			// ���峻 ���̺� ���� �ʱ�ȭ
			cnt = dao.resetTable(restaurant_dto);
		}

		// ���̺� ����
		TableVO table_dto = new TableVO();
		
		// ���� ������ �����ϱ� ���� �� �̿�
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("restaurant_dto", restaurant_dto);
		map.put("table_dto", table_dto);

		// ���峻 ���̺� ���� �ʱ�ȭ�� �����ߴٸ�
		if (cnt != 0) {
			// Ÿ�� �ϳ� �ϳ��� ���¸� ���� ���� info�� �ڸ�
			String[] state = info.split(",");

			// ����ŭ �ݺ�
			for (int i = 0; i < row; i++) {
				// �ุŭ �ݺ�
				for (int j = 0; j < col; j++) {
					table_dto.setRestaurant_table_index(index);
					table_dto.setState(state[index]);
					table_dto.setTable_row(i);
					table_dto.setTable_col(j);

					map.replace("table_dto", table_dto);

					// ���� �� ���̺� ���� ó��
					cnt = dao.modTable(map);

					if (cnt != 0) {
						index++;
					}
				}
			}
		}

		// ���� ���� ����
		model.addAttribute("cnt", cnt);
	}

	// �Ĵ� �� ������ - ���� ���� ó��
	@Override
	public void restaurantDel(HttpServletRequest req, Model model) {
		log.debug("service.restaurantDel()");

		// ���� ��ȣ
		int restaurant_index = Integer.parseInt(req.getParameter("index"));
		
		// ���� ���� ����
		RestaurantVO dto = new RestaurantVO();
		dto.setRestaurant_index(restaurant_index);

		// ���� �� ���̺� ���� �ʱ�ȭ
		int cnt = dao.resetTable(dto);

		// ���� �� ���̺� ���� �ʱ�ȭ�� �����ߴٸ�
		if (cnt != 0) {
			// ���� ���� ó��
			cnt = dao.delRestaurant(dto);
		}

		// ���� ���� ����
		model.addAttribute("cnt", cnt);
	}

	// �Ĵ� �� ������ - ��� �޴� ����Ʈ
	@Override
	public void allMenuList(HttpServletRequest req, Model model) {
		log.debug("service.allMenuList()");

		int cnt = 0;
		int count = 1;

		// �Ĵ� ����, �Ĵ� �ε��� üũ
		int index[] = dao.getRestaurantIndex();

		ArrayList<MenuVO> dtos = new ArrayList<MenuVO>();
		RestaurantVO dto = new RestaurantVO();

		for (int restaurant_index : index) {
			// �Ĵ� ���� ��ȸ
			dto = dao.viewRestaurant(restaurant_index);
			model.addAttribute("dto_" + count, dto);

			// �޴� ���� ��ȸ
			cnt = dao.getMenuCnt(restaurant_index);
			model.addAttribute("cnt_menu_" + count, cnt);

			// �� �޴� ���� ��ȸ
			dtos = dao.getMenuList(restaurant_index);
			model.addAttribute("dtos_menu_" + count, dtos);

			count++;
		}
	}

	// �Ĵ� �� ������ - ��� ���� ����Ʈ
	@Override
	public void allEmployeeList(HttpServletRequest req, Model model) {
		log.debug("service.employeeList()");

		int cnt = 0;
		int count = 1;
		int member_step = 51;

		// �Ĵ� ����, �Ĵ� �ε��� üũ
		int index[] = dao.getRestaurantIndex();

		ArrayList<EmployeeVO> dtos = new ArrayList<EmployeeVO>();
		RestaurantVO dto = new RestaurantVO();

		for (int restaurant_index : index) {
			// �Ĵ� ���� ��ȸ
			dto = dao.viewRestaurant(restaurant_index);
			model.addAttribute("dto_" + count, dto);

			// ���� �� ��ȸ
			cnt = dao.getEmployeeCnt(member_step);
			model.addAttribute("cnt_empl_" + count, cnt);

			// �� ���� ���� ��ȸ
			dtos = dao.getEmployeeList(member_step);
			model.addAttribute("dtos_empl_" + count, dtos);

			count++;
			member_step++;
		}
	}

	// �Ĵ� �� ������ - �Ĵ纰 ��Ʈ
	@Override
	public void allAccountChart(HttpServletRequest req, Model model) {
		log.debug("service.allAccountChart()");
		
		// ��� �޴��� �̸� ��ȸ
		String[] restaurant = dao.getRestaurantName();

		// �Ǹŵ����ִ� �޴��� �̸��� �Ǹž� ��ȸ
		Map<String , Object> map = new HashMap<String,Object>();
		
		// mapper���� �ҷ��� kind�� value�� �ٰ��̱⶧���� vo������ List������ �޾��ش�.
		List<Restaurant_ChartVO> menuList = dao.getRestaurantChart();
		
		// List �����͸� �� �Ǿ� map�� ��´�.
		for(Restaurant_ChartVO dto : menuList) {
			map.put(dto.getKind(), dto.getValue());
		}
		
		// �Ǹŵ����� �ִ��� ������ Ȯ��
		for(String s : restaurant) {
			int cnt = 0;
			
			// �Ǹŵ����� �ִ� �޴��� �ǳʶٰ�,
			for(Entry<String, Object> m : map.entrySet()) {
				if(s.equals(m.getKey())) {
					cnt = 1;
				}
			}
			
			// �Ǹŵ� ���� ���� �޴��� �Ǹž�(value)�� 0�� �־��ش�.
			if(cnt == 0) {
				map.put(s, 0);
			}
		}

		// ������ ������ �����ϱ� ���� Ʈ���� �̿�
		TreeMap<String, Object> tm = new TreeMap<String, Object>(map);
		
		// Ű�� �������� ����(�⺻)
		Iterator<String> iteratorKey = tm.keySet().iterator();
		
		String key = "";	// Ű
		String keys = "";	// Ű�� ����
		String value = "";	// ��
		String values = ""; // ���� ����
		int cnt = 0;	// ó������ Ȯ��
		
		while (iteratorKey.hasNext()) {
			key = iteratorKey.next();
			value = String.valueOf(tm.get(key));
			// ó���̸� �޸��� ������ ����
			if(cnt == 0) {
				keys = keys + key;
				values = values + value;
				cnt = 1;
			}
			// ó���� �ƴϸ� �޸��� �տ�
			else {
				keys = keys + "," + key;
				values = values + "," + value;
			}
		}
		
		// ����� �����Ѵ�.
		model.addAttribute("keys", keys);
		model.addAttribute("values", values);
		model.addAttribute("count", restaurant.length);
		model.addAttribute("chart", map);
		model.addAttribute("total", tm.get("�հ�"));
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////

	// �޴� ����Ʈ
	@Override
	public void menuList(HttpServletRequest req, Model model) {
		log.debug("service.menuList()");

		int cnt = 0;

		// �Ĵ� �� �����ڰ� �ƴ϶��
		if (!(String.valueOf(req.getSession().getAttribute("memStep")).equals("4"))) {
			// �Ĵ� �������� memberStep���� ���ڸ��� ���Ѵ�.(���ڸ��� restaurant_index�� ����)
			int restaurant_index = Integer
					.parseInt(String.valueOf(req.getSession().getAttribute("memStep")).substring(1, 2));

			// �޴� ���� ��ȸ
			cnt = dao.getMenuCnt(restaurant_index);

			// �� �޴� ���� ��ȸ
			ArrayList<MenuVO> dtos = dao.getMenuList(restaurant_index);

			// �޴� ����, ��� �޴� ���� ����
			model.addAttribute("cnt_menu", cnt);
			model.addAttribute("dtos_menu", dtos);
		}
	}

	// �޴� �߰�
	@Override
	public void menuAdd(MultipartHttpServletRequest req, Model model) {
		log.debug("service.menuAdd()");

		// �̹��� ������ ������
		MultipartFile file = req.getFile("img");
		
		// ���� ������ ���
		String saveDir = req.getRealPath("/resources/images/chg/");
		
		// ����Ǿ� �ִ� ���
		String realDir = "C:\\Dev\\workspace_baobob\\BAOBOB\\BAOBOB\\src\\main\\webapp\\resources\\images\\chg\\";

		try {
			file.transferTo(new File(saveDir + file.getOriginalFilename()));

			// ���� �����
			FileInputStream fis = new FileInputStream(saveDir + file.getOriginalFilename());
			FileOutputStream fos = new FileOutputStream(realDir + file.getOriginalFilename());

			int data = 0;

			while ((data = fis.read()) != -1) {
				fos.write(data);
			}
			fis.close();
			fos.close();

			// �̹��� ������ �̸�
			String fileName = file.getOriginalFilename();

			// �Ĵ� �������� memberStep���� ���ڸ��� ���Ѵ�.(���ڸ��� restaurant_index�� ����)
			int restaurant_index = Integer
					.parseInt(String.valueOf(req.getSession().getAttribute("memStep")).substring(1, 2));

			// �޴� ����
			String menuName = req.getParameter("name");
			String menuContent = req.getParameter("content");
			int menuPrice = Integer.parseInt(req.getParameter("price"));
			
			// �޴� ���� ����
			MenuVO dto = new MenuVO();
			dto.setRestaurant_menu_img(fileName);
			dto.setRestaurant_index(restaurant_index);
			dto.setRestaurant_menu_name(menuName);
			dto.setRestaurant_menu_content(menuContent);
			dto.setRestaurant_menu_price(menuPrice);
			
			// �޴� �߰� �� �޴��ε��� ���
			Integer restaurant_menu_index = dao.getMenuIndex(restaurant_index);
			if(restaurant_menu_index != null) {
				dto.setRestaurant_menu_index(restaurant_menu_index);
			}
			
			// �޴� �߰� ó��
			int cnt = dao.addMenu(dto);

			// ���� ���� ����
			model.addAttribute("cnt", cnt);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// �޴� ���� �� ��ȸ
	@Override
	public void menuView(HttpServletRequest req, Model model) {
		log.debug("service.menuView()");

		// �Ĵ� �������� memberStep���� ���ڸ��� ���Ѵ�.(���ڸ��� restaurant_index�� ����)
		int restaurant_index = Integer
				.parseInt(String.valueOf(req.getSession().getAttribute("memStep")).substring(1, 2));
		
		// ��ȸ�� �޴��� index
		int restaurant_menu_index = Integer.parseInt(req.getParameter("index"));

		// ��ȸ�� �޴� ���� ����
		MenuVO dto = new MenuVO();
		dto.setRestaurant_index(restaurant_index);
		dto.setRestaurant_menu_index(restaurant_menu_index);

		// �޴� �� ���� ��ȸ
		dto = dao.viewMenu(dto);

		// ��ȸ�� �޴� �� ���� ����
		model.addAttribute("dto", dto);
	}

	// �޴� ���� ó��
	@Override
	public void menuMod(MultipartHttpServletRequest req, Model model) {
		log.debug("service.menuMod()");

		// �̹��� ������ ������
		MultipartFile file = req.getFile("img");
		
		// ���� ������ ���
		String saveDir = req.getRealPath("/resources/images/chg/");
		
		// ����Ǿ� �ִ� ���
		String realDir = "C:\\Dev\\workspace_baobob\\BAOBOB\\BAOBOB\\src\\main\\webapp\\resources\\images\\chg\\";

		try {
			file.transferTo(new File(saveDir + file.getOriginalFilename()));

			// ���� �����
			FileInputStream fis = new FileInputStream(saveDir + file.getOriginalFilename());
			FileOutputStream fos = new FileOutputStream(realDir + file.getOriginalFilename());

			int data = 0;

			while ((data = fis.read()) != -1) {
				fos.write(data);
			}
			fis.close();
			fos.close();

			// �̹��� ������ �̸�
			String fileName = file.getOriginalFilename();

			// �Ĵ� �������� memberStep���� ���ڸ��� ���Ѵ�.(���ڸ��� restaurant_index�� ����)
			int restaurant_index = Integer
					.parseInt(String.valueOf(req.getSession().getAttribute("memStep")).substring(1, 2));

			// �޴� ����
			int restaurant_menu_index = Integer.parseInt(req.getParameter("restaurant_menu_index"));
			String menuName = req.getParameter("name");
			String menuContent = req.getParameter("content");
			int menuPrice = Integer.parseInt(req.getParameter("price"));
			
			// �޴� ���� ����
			MenuVO dto = new MenuVO();
			dto.setRestaurant_menu_img(fileName);
			dto.setRestaurant_index(restaurant_index);
			dto.setRestaurant_menu_index(restaurant_menu_index);
			dto.setRestaurant_menu_name(menuName);
			dto.setRestaurant_menu_content(menuContent);
			dto.setRestaurant_menu_price(menuPrice);

			// �޴� ���� ���� ó��
			int cnt = dao.modMenu(dto);

			// ���� ���� ����
			model.addAttribute("cnt", cnt);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// �޴� ���� ó��
	@Override
	public void menuDel(HttpServletRequest req, Model model) {
		log.debug("service.menuDel()");

		// �޴� ��ȣ
		int restaurant_menu_index = Integer.parseInt(req.getParameter("index"));
		
		// �޴� ���� ����
		MenuVO dto = new MenuVO();
		dto.setRestaurant_menu_index(restaurant_menu_index);

		// �޴� ���� ó��
		int cnt = dao.delMenu(dto);
		
		// ���� ���� ����
		model.addAttribute("cnt", cnt);
	}

	// ���� ����Ʈ
	@Override
	public void employeeList(HttpServletRequest req, Model model) {
		log.debug("service.employeeList()");

		int cnt = 0;
		
		// �������� step�� 10�� ���ϸ� �� �Ĵ��� �������� ��ȸ�� �� �ִ�. mapper���� ����ϱ� ���� ����
		int member_step = Integer.parseInt(String.valueOf(req.getSession().getAttribute("memStep")));

		// ���� �� ��ȸ
		cnt = dao.getEmployeeCnt(member_step);

		// �� ���� ���� ��ȸ
		ArrayList<EmployeeVO> dtos = dao.getEmployeeList(member_step);

		// ���� ��, ���� ���� ����
		model.addAttribute("cnt_empl", cnt);
		model.addAttribute("dtos_empl", dtos);
	}

	// ��ü ȸ�� ���(���� ���)
	@Override
	public void memberList(HttpServletRequest req, Model model) {
		log.debug("service.memberList()");

		int cnt = 0;

		// ��ü ȸ�� �� ��ȸ(Ÿ �Ĵ�, Ÿ �μ� ���� ����)
		cnt = dao.getMemberCnt();

		// �� ȸ�� ���� ��ȸ(Ÿ �Ĵ�, Ÿ �μ� ���� ����)
		ArrayList<Member> dtos = dao.getMemberList();

		// ȸ�� ��, ȸ�� ���� ����
		model.addAttribute("cnt_mem", cnt);
		model.addAttribute("dtos_mem", dtos);
	}

	// �������� ����� ȸ�� ���� ��ȸ
	@Override
	public void memberView(HttpServletRequest req, Model model) {
		log.debug("service.memberView()");

		// ȸ�� ���� ����
		Member dto = new Member();
		dto = dao.viewMember(req.getParameter("id"));

		model.addAttribute("dto", dto);
	}

	// ���� ��� ó��
	@Override
	public void employeeAdd(HttpServletRequest req, Model model) {
		log.debug("service.employeeAdd()");

		int cnt = 0;
		
		// �������� step�� 10�� ���ϸ� �� �Ĵ��� ������ ��ȸ�� �� �ִ�. mapper���� ����ϱ� ���� ����
		int member_step = Integer.parseInt(String.valueOf(req.getSession().getAttribute("memStep")));
		String member_id = req.getParameter("id");
		String jumin2 = req.getParameter("jumin2");
		
		// ���� ������ �����ϱ� ���� �� �̿�
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", member_id);
		map.put("jumin2", jumin2);
		map.put("member_step", (member_step + 10));

		// member_step ����
		cnt = dao.updateStep(map);

		// step ���濡 �����ߴٸ�
		if (cnt != 0) {
			// ���� ��� ó��
			cnt = dao.addEmployee(map);
		}

		// ���� ���� ����
		model.addAttribute("cnt", cnt);
	}

	// ���� ���� ��ȸ
	@Override
	public void employeeView(HttpServletRequest req, Model model) {
		log.debug("service.employeeView()");

		// ��ȸ�� ������ ���̵�
		String id = req.getParameter("id");
		
		// ���� ���� ����
		EmployeeVO dto = new EmployeeVO();
		dto = dao.viewEmployee(id);

		model.addAttribute("dto_empl", dto);
	}

	// ���� ���� ó��
	@Override
	public void employeeDel(HttpServletRequest req, Model model) {
		log.debug("service.employeeDel()");

		int cnt = 0;

		String member_id = req.getParameter("id");
		
		// step ����� ���� ���� ����Ʈ ��ȸ
		int step = dao.getCumPoint(req.getParameter("id"));

		// ���� ����Ʈ�� ���� step�� �޶���
		if (0 <= step && step <= 15000) {
			step = 9;
		} else if (15001 <= step && step <= 30000) {
			step = 10;
		} else if (30001 <= step && step <= 45000) {
			step = 11;
		} else if (45001 <= step) {
			step = 12;
		}
		
		// ���� ������ �����ϱ� ���� �� �̿�
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", member_id);
		map.put("member_step", step);

		// ���� ���� ó��
		cnt = dao.delEmployee(map);

		// ������ �����ߴٸ�
		if (cnt != 0) {
			// member_step ����
			cnt = dao.updateStep(map);
		}

		// ���� ���� ����
		model.addAttribute("cnt", cnt);
	}

	// ���� ����Ʈ
	@Override
	public void reservList(HttpServletRequest req, Model model) {
		log.debug("service.reservList()");

		// ���� ��¥ ����
		SimpleDateFormat date = new SimpleDateFormat("yy-MM-dd", Locale.KOREA);
		Date sysdate = new Date();
		String today = date.format(sysdate);

		// �Ĵ� �� �����ڰ� �ƴ϶��
		if (!(String.valueOf(req.getSession().getAttribute("memStep")).equals("4"))) {
			// �Ĵ� �������� memberStep���� ���ڸ��� ���Ѵ�.(���ڸ��� restaurant_index�� ����)
			int restaurant_index = Integer
					.parseInt(String.valueOf(req.getSession().getAttribute("memStep")).substring(1, 2));

			// ���� ������ �����ϱ� ���� �� �̿�
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("restaurant_index", restaurant_index);
			map.put("startTime", today + "-00:00");
			map.put("endTime", today + "-23:59");

			// ���� ��¥ ���� ��ȸ
			ArrayList<Restaurant_scheduleVO> dtos = dao.getReservList(map);

			// ���� ����
			model.addAttribute("index", restaurant_index);
			model.addAttribute("date", today);
			model.addAttribute("dtos", dtos);
		}
	}

	// ���� �߰� ó��
	@Override
	public void reservAdd(HttpServletRequest req, Model model) {
		log.debug("service.reservAdd()");

		int cnt = 0;
		int index = 0;
		
		// �Ĵ� ��ȣ
		int restaurant_index = Integer.parseInt(req.getParameter("index"));

		// Ÿ���� ����(���̺����� ��������)
		String info = req.getParameter("info");
		
		// ������ �����ϴ� Ÿ���� �࿭ (��:5*5)
		int col = Integer.parseInt(req.getParameter("col")); // ��
		int row = Integer.parseInt(req.getParameter("row")); // ��

		String date = "20" + req.getParameter("date");
		String time = req.getParameter("time") + ":00";

		// DB�� �ִ� startTime �÷��� ������ ����
		String startTime = date + " " + time;

		// �ð��� ��, �� ���·� �ڸ�
		String[] end = time.split(":");

		// ���� �ð��� ���� 00���̸� 30������ �ٲ�
		if (end[1].equals("00")) {
			end[1] = "30";
		}
		// ���� �ð��� ���� 30���̸� �ÿ� 1�� �����ְ� 00������ �ٲ�
		else if (end[1].equals("30")) {
			end[0] = String.valueOf((Integer.parseInt(end[0]) + 1));
			end[1] = "00";
		}

		// ��, ��, �� ����
		String endTime = end[0] + ":" + end[1] + ":00";
		
		// ��¥, �ð� ����
		endTime = date + " " + endTime;

		// ���� ���� ����
		RestaurantVO restaurant_dto = new RestaurantVO();
		restaurant_dto.setRestaurant_index(restaurant_index);

		// ������ ����, ������ ��¥ �ð� ���� ����
		Restaurant_scheduleVO schedule_dto = new Restaurant_scheduleVO();
		schedule_dto.setSchedule_startTime(Timestamp.valueOf(startTime));
		schedule_dto.setSchedule_endTime(Timestamp.valueOf(endTime));
		schedule_dto.setRestaurant_index(restaurant_index);

		// ������ index ��ȸ
		Integer schedule_index = dao.getScheduleIndex(schedule_dto);

		// ������ index�� �ִٸ�
		if (schedule_index != null) {
			schedule_dto.setRestaurant_schedule_index(schedule_index);
		}

		// ���̺� ����
		TableVO table_dto = new TableVO();
		
		// ���� ������ �����ϱ� ���� �� �̿�
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("restaurant_dto", restaurant_dto);
		map.put("table_dto", table_dto);
		map.put("schedule_dto", schedule_dto);

		// ������ index�� ���ٸ�
		if (schedule_index == null) {
			// ���ο� ���� �߰�
			cnt = dao.addReserv(map);
		}

		// ���ο� ������ �߰��Ѱ� �ƴ϶��(���� ������ �ִٸ�)
		if (cnt == 0) {
			// ���̺� �ʱ�ȭ(���� ���� �� �����ϴ� ���)
			cnt = dao.resetTable2(schedule_dto);
		}

		// ���ο� ������ �߰��� ��� Ȥ�� ���̺��� �ʱ�ȭ�� ���
		if (cnt != 0) {
			String[] state = info.split(",");

			// ����ŭ �ݺ�
			for (int i = 0; i < row; i++) {
				// �ุŭ �ݺ�
				for (int j = 0; j < col; j++) {
					table_dto.setRestaurant_table_index(index);
					table_dto.setState(state[index]);
					table_dto.setTable_row(i);
					table_dto.setTable_col(j);

					map.replace("table_dto", table_dto);

					cnt = dao.modTable2(map);

					if (cnt != 0) {
						index++;
					}
				}
			}
		}
		
		// ���̺� ������� �����ߴٸ�
		if(cnt != 0) {
			String member_id = req.getParameter("member_id");

			System.out.println("id : " + member_id);
			
			// ���̵� ���� ���� �� ���� ���� ���̵����� Ȯ��
			Integer member_step = dao.confirmId(member_id);

			// �̿� ������ ������
			if(member_step != null && ((1 <= member_step && member_step <= 12) || (51 <= member_step && member_step <= 53) || (61 <= member_step && member_step <= 63))) {
				// �����丮�� �̿� ���� �߰�
				cnt = dao.addHistory(member_id);
				
				// ���� �߰��� �����ߴٸ�
				if(cnt != 0) {
					int table_index = Integer.parseInt(req.getParameter("table_index"));
					map.put("restaurant_table_index", table_index);
					// ������� �����丮 ���̺� �̿� ���� �߰�
					cnt = dao.addRestaurantHistory(map);
				}
			} else {
				cnt = 2;
			}
		}
		
		// ���� ���� ����
		model.addAttribute("cnt", cnt);
	}

	// ������ ��¥�� �ִ� ��� ���� ��ȸ
	@Override
	public void reservView(HttpServletRequest req, Model model) {
		log.debug("service.reservView()");

		// �˻��� ��¥
		String searchDate = req.getParameter("date");
		
		// �Ĵ� ��ȣ
		int restaurant_index = Integer.parseInt(req.getParameter("index"));
		
		// timestamp������ ��ȯ�Ͽ� ��¥�� ����ϱ� ���� ��
		SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat dateForm2 = new SimpleDateFormat("yy-MM-dd");

		try {
			// yyyy-MM-dd HH:mm:ss ������ searchDate�� ��ȯ
			Date sysdate = dateForm.parse("20" + searchDate + " 00:00:00");
			String date = dateForm2.format(sysdate);

			// ���� ������ �����ϱ� ���� �� �̿�
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("restaurant_index", restaurant_index);
			map.put("startTime", date + "-00:00");
			map.put("endTime", date + "-23:59");

			// ������ ��¥�� �ִ� ��� ���� ��ȸ
			ArrayList<Restaurant_scheduleVO> dtos = dao.getReservList(map);

			// ���� ����
			model.addAttribute("date", searchDate);
			model.addAttribute("index", restaurant_index);
			model.addAttribute("dtos", dtos);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	// ������ ��¥�� ����ִ� ���̺� ���� ��ȸ
	@Override
	public TableVO restaurantView2(HttpServletRequest req, Model model) {
		log.debug("service.restaurantView2()");

		// �Ĵ� �������� memberStep���� ���ڸ��� ���Ѵ�.(���ڸ��� restaurant_index�� ����)
		int restaurant_index = Integer.parseInt(req.getParameter("index").substring(1, 2));
		
		// ���� ���� ����
		Restaurant_scheduleVO schedule_dto = new Restaurant_scheduleVO();
		schedule_dto.setSchedule_startTime(Timestamp.valueOf(req.getParameter("startTime")));
		schedule_dto.setSchedule_endTime(Timestamp.valueOf(req.getParameter("endTime")));
		schedule_dto.setRestaurant_index(restaurant_index);

		// ������ �ε��� ��ȸ
		int schedule_index = dao.getScheduleIndex(schedule_dto);

		// �Ĵ� ���� ��ȸ
		RestaurantVO restaurant_dto = new RestaurantVO();
		restaurant_dto = dao.viewRestaurant(restaurant_index);

		// ���̺� ���� ����
		TableVO table_dto = new TableVO();

		// ������ �����ϴ� Ÿ���� �࿭ ��ȸ (��:5*5)
		table_dto = dao.getColRow(restaurant_index);

		// ������ �����ϴ� Ÿ���� �࿭
		int col = table_dto.getTable_col() + 1;
		int row = table_dto.getTable_row() + 1;

		String info = "";
		int restaurant_table_index = 0;
		// ���� ������ �����ϱ� ���� �� �̿�
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("restaurant_index", restaurant_index);
		map.put("restaurant_table_index", restaurant_table_index);
		map.put("restaurant_schedule_index", schedule_index);

		// ����ŭ �ݺ�
		for (int i = 0; i < row; i++) {
			// �ุŭ �ݺ�
			for (int j = 0; j < col; j++) {
				map.replace("restaurant_table_index", restaurant_table_index);

				// Ÿ�� �ϳ��ϳ��� ���¸� info�� �߰�
				info += dao.getState(map);

				// �� ������ ������ �ƴϸ� ',' �߰�
				if (!(i + 1 == row && j + 1 == col)) {
					info += ',';
					restaurant_table_index++;	// ���̺� �ε��� 1 ����
				}
			}
		}

		// ���̺� ���� ����
		table_dto.setState(info);
		table_dto.setTable_col(col);
		table_dto.setTable_row(row);

		// ���� ���� ����
		model.addAttribute("dto", restaurant_dto);
		
		// script.js - spaceBody(), spaceBody2()���� ����� ������
		model.addAttribute("info", info);
		model.addAttribute("col", col);
		model.addAttribute("row", row);

		// ��¥ ����
		String date = req.getParameter("date");
		model.addAttribute("date", date);

		// ������ �ð��� �ִٸ�
		if (req.getParameter("time") != "") {
			// �ð� ����
			String time = req.getParameter("time");
			model.addAttribute("time", time);
		}
		
		// ���̺� ���� ����
		return table_dto;
	}

	// ����� ���̺� ��ȸ
	@Override
	public void useTableView(HttpServletRequest req, Model model) {
		log.debug("service.useTableView()");

		// ���̺� ��
		int table_cnt = 1;

		// ������� ���̺� ��
		int use_table_cnt = 0;

		System.out.println("memStep : " + (String.valueOf(req.getSession().getAttribute("memStep")).substring(1, 2)));
		
		// �Ĵ� �������� memberStep���� ���ڸ��� ���Ѵ�.(���ڸ��� restaurant_index�� ����)
		int restaurant_index = Integer.parseInt((String.valueOf(req.getSession().getAttribute("memStep")).substring(1, 2)));
		
		// ���� ��ȣ
		int restaurant_schedule_index = Integer.parseInt(req.getParameter("restaurant_schedule_index"));

		// ���� ���� ����
		Restaurant_scheduleVO schedule_dto = new Restaurant_scheduleVO();
		schedule_dto.setRestaurant_index(restaurant_index);
		schedule_dto.setRestaurant_schedule_index(restaurant_schedule_index);

		// ���̺� ���� ����
		TableVO table_dto = new TableVO();
		table_dto = dao.getColRow(restaurant_index);

		// ���̺� �࿭ ��ȸ
		int col = table_dto.getTable_col() + 1;
		int row = table_dto.getTable_row() + 1;

		int table_index = 0;

		// ���� ������ �����ϱ� ���� �� �̿�
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("restaurant_index", restaurant_index);
		map.put("restaurant_table_index", table_index);
		map.put("restaurant_schedule_index", restaurant_schedule_index);

		ArrayList<String> use_tables = new ArrayList<String>();
		ArrayList<String> bill = new ArrayList<String>();
		ArrayList<String> reserv_id = new ArrayList<String>();
		ArrayList<String> history_state = new ArrayList<String>();

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				map.replace("restaurant_table_index", table_index);

				// state ���� ��ȸ
				int state = dao.getState(map);

				// state�� ������� ���̺��� ��� use_tables����Ʈ�� �߰�
				if (state == 3) {
					use_tables.add(String.valueOf(table_cnt));
					
					// �ֹ� ���� ���(���̺� ���)
					Integer payValue = dao.getBill(map);
					
					// �ֹ� ������ ���ٸ�
					if(payValue == null ) {
						// null�� �ƴ� 0�� add�Ѵ�.
						bill.add("0");
					}
					// �ֹ� ������ �ִٸ�
					else {
						// �ֹ� ���� ����
						bill.add(String.valueOf(payValue));
					}
					
					// ������� ���̺� ���� ����
					use_table_cnt++;
					
					// ������ ���̵� ��ȸ
					String id = dao.getReservId(map);
					
					// �����丮 �ε����� �̿��� ������ ���̵� Ȯ��
					reserv_id.add(id);
					
					// ������ ���̺����� Ȯ��
					int h_state = dao.getHistoryState(map);
					
					if(h_state == 1) {
						history_state.add("(���� �Ϸ�)");
					} else {
						history_state.add("");
					}
				}

				// ���̺��� ã���� count�� ������Ų��.(������� ���̺��� ��� ���̺����� Ȯ���ϱ� ����)
				if (state != 0) {
					// ���̺� ���� ����
					table_cnt++;
				}

				// DB�� ����� ���̺� index
				table_index++;
			}
		}

		// ���� ��� �������� �˱� ���� ����
		model.addAttribute("restaurant_schedule_index", restaurant_schedule_index);

		// �̿����� ���̺���� ����
		model.addAttribute("use_tables", use_tables);
		
		// ���̺� �ֹ��� ���� ����
		model.addAttribute("bill", bill);

		// ��� ���̺����� �˱����� ����
		model.addAttribute("table_cnt", table_cnt);

		// ��� ���� ���̺��� 0�� �̻��� �� ȭ�� ����� ���� ����
		model.addAttribute("use_table_cnt", use_table_cnt);

		// ��� ���� ���̺��� � ���̵�� ����� �ڸ����� �˱� ���� ����
		model.addAttribute("reserv_id", reserv_id);
		
		// ���� ���θ� �˱� ���� ����
		model.addAttribute("history_state", history_state);
	}

	// ���̺� �ֹ� �߰�(�Ǹ�)
	@Override
	public void orderAdd(HttpServletRequest req, Model model) {
		log.debug("service.orderAdd()");

		// �Ĵ� �������� memberStep���� ���ڸ��� ���Ѵ�.(���ڸ��� restaurant_index�� ����)
		int restaurant_index = Integer
				.parseInt((String.valueOf(req.getSession().getAttribute("memStep")).substring(1, 2)));
		
		// ���� ��ȣ
		int restaurant_schedule_index = Integer.parseInt(req.getParameter("restaurant_schedule_index"));
		
		// ���̺� ��ȣ
		int table_Num = Integer.parseInt(req.getParameter("table_index"));
		
		// �޴� ��ȣ
		int menu_Num = Integer.parseInt(req.getParameter("menu_index"));

		// �޴� ����
		int menu_Count = Integer.parseInt(req.getParameter("menu_count"));
		
		// ���̺� ���� ����
		TableVO table_dto = new TableVO();
		table_dto = dao.getColRow(restaurant_index);

		// ������ �����ϴ� Ÿ���� �࿭ ��ȸ (��:5*5)
		int col = table_dto.getTable_col() + 1;
		int row = table_dto.getTable_row() + 1;

		// ���̺� �ε���
		int table_index = 0;

		// ������� ���̺� ��
		int table_cnt = 0;

		// ���� ������ �����ϱ� ���� �� �̿�
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("restaurant_index", restaurant_index);
		map.put("restaurant_table_index", table_index);
		map.put("restaurant_schedule_index", restaurant_schedule_index);

		loop: // 2�� for���� �ѹ��� Ż���ϱ� ���� ��
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				map.replace("restaurant_table_index", table_index);

				// state ���� ��ȸ
				int state = dao.getState(map);

				if (state != 0) {
					// ���° ���̺����� Ȯ��
					table_cnt++;
				}

				// ȭ�鿡�� ������ ���̺� ��ȣ�� �Ǹ� �ݺ��� �����Ѵ�.
				if (table_cnt == table_Num) {
					break loop;
				}
				// DB�� ����� ���̺� index
				table_index++;
			}
		}
		
		// �ֹ��� �޴� ��ȣ, �ֹ��� ���� ����
		map.put("restaurant_menu_index", menu_Num);
		map.put("restaurant_menu_count", menu_Count);
		
		// �̹� �ֹ��� �޴����� Ȯ��
		Integer cnt = dao.getMenuCount(map);
		
		// �̹� �ֹ��� �޴��� �ƴ϶��
		if(cnt == null) {
			// �ֹ� �߰� ó��
			cnt = dao.addFoodHistory(map);
		}
		// �̹� �ֹ��� �޴����
		else {
			// �ֹ� �߰��� ���� ���
			map.put("restaurant_menu_count", cnt + menu_Count);
			
			// �ֹ��� �޴��� ��� ���� ����
			cnt = dao.modFoodHistory(map);
		}

		// ���� ���� ����
		model.addAttribute("cnt", cnt);
	}

	// �ֹ� ���� ó��
	@Override
	public void orderDel(HttpServletRequest req, Model model) {
		log.debug("service.orderDel()");

		// �Ĵ� �������� memberStep���� ���ڸ��� ���Ѵ�.(���ڸ��� restaurant_index�� ����)
		int restaurant_index = Integer
				.parseInt((String.valueOf(req.getSession().getAttribute("memStep")).substring(1, 2)));
		int restaurant_schedule_index = Integer.parseInt(req.getParameter("restaurant_schedule_index")); // ���� ��ȣ
		int table_Num = Integer.parseInt(req.getParameter("table_Num")); // ���̺� ��ȣ
		int menu_Num = Integer.parseInt(req.getParameter("menu_Num")); // �޴� ��ȣ
		int menu_Count = Integer.parseInt(req.getParameter("menu_Count")); // �޴� ����
		
		// ���� ������ �����ϱ� ���� �� �̿�
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("restaurant_index", restaurant_index);
		map.put("restaurant_schedule_index", restaurant_schedule_index);
		map.put("restaurant_table_index", table_Num);
		map.put("restaurant_menu_index", menu_Num);
		map.put("restaurant_menu_count", menu_Count);
		
		Integer cnt = dao.getMenuCount(map);
		
		// �̹� �ֹ��� ������ ����� �������� ũ�ٸ�
		if(cnt > menu_Count) {
			// �ֹ� ����� ���� ���
			map.put("restaurant_menu_count", cnt - menu_Count);
			
			// �Ϻκ� �ֹ� ���
			cnt = dao.modFoodHistory(map);
		}
		// �̹� �ֹ��� ������ ����� �������� �۴ٸ�
		else {
			// �ֹ� ���� ó��
			cnt = dao.delFoodHistory(map);
		}

		// ���� ���� ����
		model.addAttribute("cnt", cnt);
	}

	// ���̺� ���(���� ó��)
	@Override
	public void payment(HttpServletRequest req, Model model) {
		log.debug("service.payment()");
		
		int cnt = 0;
		
		// ���̺� ��
		int table_cnt = 0;

		// �Ĵ� �������� memberStep���� ���ڸ��� ���Ѵ�.(���ڸ��� restaurant_index�� ����)
		int restaurant_index = Integer
				.parseInt((String.valueOf(req.getSession().getAttribute("memStep")).substring(1, 2)));
		int restaurant_schedule_index = Integer.parseInt(req.getParameter("restaurant_schedule_index")); // ���� ��ȣ
		int table_Num = Integer.parseInt(req.getParameter("table_Num")); // ���̺� ��ȣ
		String member_id = req.getParameter("member_id"); // ���� ���̵�
		int payValue = Integer.parseInt(req.getParameter("payValue"));	// ���� �ݾ�
		int use_point = Integer.parseInt(req.getParameter("point"));	// ���� ����Ʈ
		
		// ȸ�� ��� ��ȸ
		Integer member_step = dao.confirmId(member_id);
		
		if(member_step != null && ((1 <= member_step && member_step <= 12) || (51 <= member_step && member_step <= 53) || (61 <= member_step && member_step <= 63))) {
			int discount = 0;
			
			// �Ϲ� ȸ���� ��
			if(member_step == 9) {
				// ���ξ� ���
				discount = (payValue * 5) / 100;
				
				// ���Ҿ� ���
				payValue = ((payValue * 100) - (payValue * 5)) / 100;
			}
			// vip�� ��
			else if(member_step == 10) {
				// ���ξ� ���
				discount = (payValue * 10) / 100;
				
				// ���Ҿ� ���
				payValue = ((payValue * 100) - (payValue * 10)) / 100;
			}
			// vvip�� ��
			else if(member_step == 11) {
				// ���ξ� ���
				discount = (payValue * 15) / 100;
				
				// ���Ҿ� ���
				payValue = ((payValue * 100) - (payValue * 15)) / 100;
			}
			// svip�� ��
			else if(member_step == 12) {
				// ���ξ� ���
				discount = (payValue * 20) / 100;
				
				// ���Ҿ� ���
				payValue = ((payValue * 100) - (payValue * 20)) / 100;
			}

			// ��� ������ ����Ʈ ��ȸ
			int member_point = dao.getPoint(member_id);
			
			if(member_point >= use_point) {
				// ���� ������ �����ϱ� ���� �� �̿�
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("member_id", member_id); // ������ ���̵�
				map.put("member_use_point", use_point); // ����� ����Ʈ
				map.put("payValue", payValue - use_point); // ����� ����Ʈ��ŭ ���Ҿ� ���� �� ����
				map.put("discount", discount); // ���ξ� ����
				
				// ���� ���� ����
				Restaurant_scheduleVO schedule_dto = new Restaurant_scheduleVO();
				schedule_dto.setRestaurant_index(restaurant_index);
				schedule_dto.setRestaurant_schedule_index(restaurant_schedule_index);
		
				// ���̺� ���� ����
				TableVO table_dto = new TableVO();
				table_dto = dao.getColRow(restaurant_index);
		
				// ���̺� �࿭ ��ȸ
				int col = table_dto.getTable_col() + 1;
				int row = table_dto.getTable_row() + 1;
		
				int table_index = 0;
		
				// ���� ������ �����ϱ� ���� �� �̿�
				map.put("restaurant_index", restaurant_index);
				map.put("restaurant_table_index", table_index);
				map.put("restaurant_schedule_index", restaurant_schedule_index);
		
				loop: // 2�� for���� �ѹ��� Ż���ϱ� ���� ��
				for (int i = 0; i < row; i++) {
					for (int j = 0; j < col; j++) {
						map.replace("restaurant_table_index", table_index);
		
						// state ���� ��ȸ
						int state = dao.getState(map);
		
						if (state != 0) {
							// ���° ���̺����� Ȯ��
							table_cnt++;
						}
		
						// ȭ�鿡�� ������ ���̺� ��ȣ�� �Ǹ� �̿� ���� ���̺� ���� ����
						if (table_cnt == table_Num) {
							// �����丮 ���̺� �̿� ���� �߰�
							//cnt = dao.modHistory(member_id);
							
							// �߰��� �����ߴٸ�
							if(cnt != 0) {
								// ������� �����丮 ���̺� �̿� ���� �߰�
								//cnt = dao.modRestaurantHistory(map);

								// �߰��� �����ߴٸ�
								if(cnt != 0) {
									// member_point, member_cumpoint ���
									map.put("member_point", ((member_point - use_point) + (payValue / 10)));
									map.put("member_Cumpoint", payValue / 10);
									
									// member���� ����
									cnt = dao.modMemberPoint(map);
									
									// ������ �����ߴٸ�
									if(cnt != 0) {
										// step ����� ���� ���� ����Ʈ ��ȸ
										int step = dao.getCumPoint(member_id);

										// ���� ����Ʈ�� ���� step�� �޶���
										if (0 <= step && step <= 15000) {
											step = 9;
										} else if (15001 <= step && step <= 30000) {
											step = 10;
										} else if (30001 <= step && step <= 45000) {
											step = 11;
										} else if (45001 <= step) {
											step = 12;
										}
										map.put("member_step", step);
										
										// member_step ����
										cnt = dao.updateStep(map);
									}
								}
							}
							break loop;
						}
						// DB�� ����� ���̺� index
						table_index++;
					}
				}
			} else {
				cnt = 3;
			}
		} else {
			cnt = 2;
		}
		
		// ���� ���� ����
		model.addAttribute("cnt", cnt);
	}

	// ���� ����
	@Override
	public void scheduleDel(HttpServletRequest req, Model model) {
		log.debug("service.scheduleDel()");
		
		int cnt = 0;
		
		// �Ĵ� �������� memberStep���� ���ڸ��� ���Ѵ�.(���ڸ��� restaurant_index�� ����)
		int restaurant_index = Integer
				.parseInt((String.valueOf(req.getSession().getAttribute("memStep")).substring(1, 2)));
		int restaurant_schedule_index = Integer.parseInt(req.getParameter("restaurant_schedule_index")); // ���� ��ȣ

		// ���̺� ���� ����
		TableVO table_dto = new TableVO();
		table_dto = dao.getColRow(restaurant_index);

		// ���̺� �࿭ ��ȸ
		int col = table_dto.getTable_col() + 1;
		int row = table_dto.getTable_row() + 1;

		int table_index = 0;

		
		// ���� ������ �����ϱ� ���� �� �̿�
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("restaurant_index", restaurant_index);
		map.put("restaurant_table_index", table_index);
		map.put("restaurant_schedule_index", restaurant_schedule_index);

		loop: // 2�� for���� �ѹ��� Ż���ϱ� ���� ��
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				map.replace("restaurant_table_index", table_index);

				// state ���� ��ȸ
				int state = dao.getState(map);

				if (state == 3) {
					// ���° ���̺����� Ȯ��
					cnt++;
					break loop;
				}
			}
		}
		
		if(cnt == 0) {
			// ���� ���� ����
			Restaurant_scheduleVO dto = new Restaurant_scheduleVO();
			dto.setRestaurant_index(restaurant_index);
			dto.setRestaurant_schedule_index(restaurant_schedule_index);
			
			cnt = dao.resetTable2(dto);
		}
	}
	
	// �޴��� ��Ʈ
	@Override
	public void accountChart(HttpServletRequest req, Model model) {
		log.debug("service.accountChart()");

		// �Ĵ� �������� memberStep���� ���ڸ��� ���Ѵ�.(���ڸ��� restaurant_index�� ����)
		int restaurant_index = Integer
				.parseInt((String.valueOf(req.getSession().getAttribute("memStep")).substring(1, 2)));
		
		// ��� �޴��� �̸� ��ȸ
		String[] menu = dao.getMenuName(restaurant_index);

		// �Ǹŵ����ִ� �޴��� �̸��� �Ǹž� ��ȸ
		Map<String , Object> map = new HashMap<String,Object>();
		
		// mapper���� �ҷ��� kind�� value�� �ٰ��̱⶧���� vo������ List������ �޾��ش�.
		List<Restaurant_ChartVO> menuList = dao.getMenuCountChart(restaurant_index);
		
		// List �����͸� �� �Ǿ� map�� ��´�.
		for(Restaurant_ChartVO dto : menuList) {
			map.put(dto.getKind(), dto.getValue());
		}
		
		// �Ǹŵ����� �ִ��� ������ Ȯ��
		for(String s : menu) {
			int cnt = 0;
			
			// �Ǹŵ����� �ִ� �޴��� �ǳʶٰ�,
			for(Entry<String, Object> m : map.entrySet()) {
				if(s.equals(m.getKey())) {
					cnt = 1;
				}
			}
			
			// �Ǹŵ� ���� ���� �޴��� �Ǹž�(value)�� 0�� �־��ش�.
			if(cnt == 0) {
				map.put(s, 0);
			}
		}

		// ������ ������ �����ϱ� ���� Ʈ���� �̿�
		TreeMap<String, Object> tm = new TreeMap<String, Object>(map);
		
		// Ű�� �������� ����(�⺻)
		Iterator<String> iteratorKey = tm.keySet().iterator();
		
		String key = "";	// Ű
		String keys = "";	// Ű�� ����
		String value = "";	// ��
		String values = ""; // ���� ����
		int cnt = 0;	// ó������ Ȯ��
		
		while (iteratorKey.hasNext()) {
			key = iteratorKey.next();
			value = String.valueOf(tm.get(key));
			// ó���̸� �޸��� ������ ����
			if(cnt == 0) {
				keys = keys + key;
				values = values + value;
				cnt = 1;
			}
			// ó���� �ƴϸ� �޸��� �տ�
			else {
				keys = keys + "," + key;
				values = values + "," + value;
			}
		}
		
		// ����� �����Ѵ�.
		model.addAttribute("keys", keys);
		model.addAttribute("values", values);
		model.addAttribute("count", menu.length);
		model.addAttribute("chart", map);
		model.addAttribute("total", tm.get("�հ�"));
	}

	// ���� ��Ʈ
	@Override
	@SuppressWarnings("unchecked")
	public void accountChart2(HttpServletRequest req, Model model) {
		log.debug("service.accountChart2()");

		// �Ĵ� �������� memberStep���� ���ڸ��� ���Ѵ�.(���ڸ��� restaurant_index�� ����)
		int restaurant_index = Integer
				.parseInt((String.valueOf(req.getSession().getAttribute("memStep")).substring(1, 2)));

		Map<String, Integer> map = (Map<String, Integer>) dao.getSexChart(restaurant_index);

		// ���� ��Ʈ
		model.addAttribute("sexChart", map);
	}
}
