package spring.mvc.baobob.host_restaurant.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import spring.mvc.baobob.vo.Restaurant_scheduleVO;
import spring.mvc.baobob.vo.TableVO;

@Service
public class Host_restaurantServiceImpl implements Host_restaurantService {
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	Host_restaurantDAO dao;

	// �Ĵ� �� ������ - ���� ����Ʈ
	@Override
	public void restaurantList(HttpServletRequest req, Model model) {
		// TODO Auto-generated method stub
		log.debug("service.restaurantList()");

		int cnt = 0;

		// ���� ���� ��ȸ
		cnt = dao.getRestaurantCnt();

		// �� ���� ���� ��ȸ
		ArrayList<RestaurantVO> dtos = dao.getRestaurantList();

		model.addAttribute("cnt_restaurant", cnt); // ���� ��
		model.addAttribute("dtos_restaurant", dtos); // ��� ���� ����
	}

	// �Ĵ� �� ������ - ���� �߰� ó��
	@Override
	public void restaurantAdd(HttpServletRequest req, Model model) {
		// TODO Auto-generated method stub
		log.debug("service.restaurantAdd()");

		int cnt = 0;
		int index = 0;
		int row = Integer.parseInt(req.getParameter("row"));
		int col = Integer.parseInt(req.getParameter("col"));
		String info = req.getParameter("info");

		RestaurantVO dto = new RestaurantVO();
		dto.setRestaurant_tel(req.getParameter("tel"));
		dto.setRestaurant_name(req.getParameter("name"));

		cnt = dao.addRestaurant(dto);

		if (cnt != 0) {
			String[] state = info.split(",");

			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					TableVO dto2 = new TableVO();

					dto2.setIndex(index);
					dto2.setState(state[index]);
					dto2.setTable_row(i);
					dto2.setTable_col(j);

					cnt = dao.addTable(dto2);

					if (cnt != 0) {
						index++;
					}
				}
			}
		}

		model.addAttribute("cnt", cnt);
	}

	// �Ĵ� �� ������ - ������ ���� ���� ��ȸ / ������ ���� �Է�
	@Override
	public void restaurantView(HttpServletRequest req, Model model) {
		// TODO Auto-generated method stub
		log.debug("service.restaurantView()");

		String date = req.getParameter("date");
		String time = req.getParameter("time");
		
		RestaurantVO dto = new RestaurantVO();
		dto = dao.viewRestaurant(req.getParameter("index"));

		TableVO dto2 = new TableVO();
		dto2 = dao.getColRow(req.getParameter("index"));

		int col = dto2.getTable_col() + 1;
		int row = dto2.getTable_row() + 1;

		String info = "";
		int index = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("restaurant_index", req.getParameter("index"));
		map.put("restaurant_table_index", index);

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				map.replace("restaurant_table_index", index);

				info += dao.getState(map);

				if (!(i + 1 == row && j + 1 == col)) {
					info += ',';
					index++;
				}
			}
		}

		model.addAttribute("dto", dto);
		model.addAttribute("info", info);
		model.addAttribute("col", col);
		model.addAttribute("row", row);
		model.addAttribute("date", date);
		model.addAttribute("time", time);
	}

	// �Ĵ� �� ������ - ���� ���� ó��
	@Override
	public void restaurantMod(HttpServletRequest req, Model model) {
		// TODO Auto-generated method stub
		log.debug("service.restaurantMod()");

		int cnt = 0;
		int index = 0;
		int row = Integer.parseInt(req.getParameter("row"));
		int col = Integer.parseInt(req.getParameter("col"));
		String info = req.getParameter("info");

		RestaurantVO dto = new RestaurantVO();
		dto.setRestaurant_index(Integer.parseInt(req.getParameter("index")));
		dto.setRestaurant_tel(req.getParameter("tel"));
		dto.setRestaurant_name(req.getParameter("name"));

		cnt = dao.modRestaurant(dto);

		if (cnt != 0) {
			cnt = dao.resetTable(dto);
		}

		TableVO dto2 = new TableVO();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dto", dto);
		map.put("dto2", dto2);

		if (cnt != 0) {
			String[] state = info.split(",");

			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					dto2.setIndex(index);
					dto2.setState(state[index]);
					dto2.setTable_row(i);
					dto2.setTable_col(j);

					map.replace("dto2", dto2);

					cnt = dao.modTable(map);

					if (cnt != 0) {
						index++;
					}
				}
			}
		}

		model.addAttribute("cnt", cnt);
	}

	// �Ĵ� �� ������ - ���� ���� ó��
	@Override
	public void restaurantDel(HttpServletRequest req, Model model) {
		// TODO Auto-generated method stub
		log.debug("service.restaurantDel()");

		RestaurantVO dto = new RestaurantVO();

		dto.setRestaurant_index(Integer.parseInt(req.getParameter("index")));

		int cnt = dao.resetTable(dto);

		if (cnt != 0) {
			cnt = dao.delRestaurant(dto);
		}

		model.addAttribute("cnt", cnt);
	}

	/////////////////////////////////////////////////////////////////////////////////////////

	// �Ĵ�[1] �޴� ����Ʈ
	@Override
	public void menuList(HttpServletRequest req, Model model) {
		// TODO Auto-generated method stub
		log.debug("service.menuList()");

		int cnt = 0;

		// �޴� ���� ��ȸ
		cnt = dao.getMenuCnt();

		// �� �޴� ���� ��ȸ
		ArrayList<MenuVO> dtos = dao.getMenuList();

		model.addAttribute("cnt_menu", cnt); // �޴� ����
		model.addAttribute("dtos_menu", dtos); // ��� �޴� ����
	}

	// �Ĵ�[1] �޴� �߰�
	@Override
	public void menuAdd(MultipartHttpServletRequest req, Model model) {
		// TODO Auto-generated method stub
		log.debug("service.menuAdd()");

		MultipartFile file = req.getFile("img");
		String saveDir = req.getRealPath("/resources/images/chg/"); // ����
		String realDir = "C:\\Dev\\workspace_baobob\\BAOBOB\\BAOBOB\\src\\main\\webapp\\resources\\images\\chg\\"; // ����

		try {
			file.transferTo(new File(saveDir + file.getOriginalFilename()));

			FileInputStream fis = new FileInputStream(saveDir + file.getOriginalFilename());
			FileOutputStream fos = new FileOutputStream(realDir + file.getOriginalFilename());

			int data = 0;

			while ((data = fis.read()) != -1) {
				fos.write(data);
			}
			fis.close();
			fos.close();

			String fileName = file.getOriginalFilename();
			MenuVO dto = new MenuVO();

			dto.setRestaurant_menu_img(fileName);
			dto.setRestaurant_menu_name(req.getParameter("name"));
			dto.setRestaurant_menu_content(req.getParameter("content"));
			dto.setRestaurant_menu_price(Integer.parseInt(req.getParameter("price")));
			int cnt = dao.addMenu(dto);

			model.addAttribute("cnt", cnt);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// �Ĵ�[1] ������ �޴� ���� ���� / ������ ���� �Է�
	@Override
	public void menuView(HttpServletRequest req, Model model) {
		// TODO Auto-generated method stub
		log.debug("service.menuView()");

		MenuVO dto = new MenuVO();

		dto = dao.viewMenu(Integer.parseInt(req.getParameter("index")));

		model.addAttribute("dto", dto);
	}

	// �Ĵ�[1] �޴� ���� ó��
	@Override
	public void menuMod(MultipartHttpServletRequest req, Model model) {
		// TODO Auto-generated method stub
		log.debug("service.menuMod()");

		MultipartFile file = req.getFile("img");
		String saveDir = req.getRealPath("/resources/images/chg/"); // ����
		String realDir = "C:\\Dev\\workspace_baobob\\BAOBOB\\BAOBOB\\src\\main\\webapp\\resources\\images\\chg\\"; // ����

		try {
			file.transferTo(new File(saveDir + file.getOriginalFilename()));

			FileInputStream fis = new FileInputStream(saveDir + file.getOriginalFilename());
			FileOutputStream fos = new FileOutputStream(realDir + file.getOriginalFilename());

			int data = 0;

			while ((data = fis.read()) != -1) {
				fos.write(data);
			}
			fis.close();
			fos.close();

			String fileName = file.getOriginalFilename();
			MenuVO dto = new MenuVO();

			dto.setRestaurant_menu_index(Integer.parseInt(req.getParameter("index")));
			dto.setRestaurant_menu_img(fileName);
			dto.setRestaurant_menu_name(req.getParameter("name"));
			dto.setRestaurant_menu_content(req.getParameter("content"));
			dto.setRestaurant_menu_price(Integer.parseInt(req.getParameter("price")));
			int cnt = dao.modMenu(dto);

			model.addAttribute("cnt", cnt);
			model.addAttribute("index", req.getParameter("index"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// �Ĵ�[1] �޴� ���� ó��
	@Override
	public void menuDel(HttpServletRequest req, Model model) {
		// TODO Auto-generated method stub
		log.debug("service.menuDel()");

		MenuVO dto = new MenuVO();

		dto.setRestaurant_menu_index(Integer.parseInt(req.getParameter("index")));

		int cnt = dao.delMenu(dto);
		model.addAttribute("cnt", cnt);
	}

	// �Ĵ�[1] ���� ����Ʈ
	@Override
	public void employeeList(HttpServletRequest req, Model model) {
		// TODO Auto-generated method stub
		log.debug("service.employeeList()");

		int cnt = 0;

		// ���� �� ��ȸ
		cnt = dao.getEmployeeCnt();

		// �� ���� ���� ��ȸ
		ArrayList<EmployeeVO> dtos = dao.getEmployeeList();

		model.addAttribute("cnt_empl", cnt); // ���� ��
		model.addAttribute("dtos_empl", dtos); // ��� ���� ����
	}

	// ��ü ȸ�� ���(�Ĵ�[1] ���� ���)
	@Override
	public void memberList(HttpServletRequest req, Model model) {
		// TODO Auto-generated method stub
		log.debug("service.memberList()");

		int cnt = 0;

		// ��ü ȸ�� �� ��ȸ(�Ĵ�[1] ���� ����)
		cnt = dao.getMemberCnt();

		// �� ȸ�� ���� ��ȸ(�Ĵ�[1] ���� ����)
		ArrayList<Member> dtos = dao.getMemberList();

		model.addAttribute("cnt_mem", cnt); // ��ü ȸ�� �� ��ȸ(�Ĵ�[1] ���� ����)
		model.addAttribute("dtos_mem", dtos); // �� ȸ�� ���� ��ȸ(�Ĵ�[1] ���� ����)
	}

	// �Ĵ�[1] �������� ����� ȸ�� ���� ��ȸ
	@Override
	public void memberView(HttpServletRequest req, Model model) {
		// TODO Auto-generated method stub
		log.debug("service.memberView()");

		Member dto = new Member();

		dto = dao.viewMember(req.getParameter("id"));

		model.addAttribute("dto", dto);
	}

	// �Ĵ�[1] ���� ��� ó��
	@Override
	public void employeeAdd(HttpServletRequest req, Model model) {
		// TODO Auto-generated method stub
		log.debug("service.employeeAdd()");

		int cnt = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", req.getParameter("id"));
		map.put("jumin2", req.getParameter("jumin2"));
		map.put("member_step", 61);

		cnt = dao.updateStep(map);

		if (cnt != 0) {
			cnt = dao.addEmployee(map);
		}

		model.addAttribute("cnt", cnt);
	}

	// �Ĵ�[1] ���� ���� ��ȸ
	@Override
	public void employeeView(HttpServletRequest req, Model model) {
		// TODO Auto-generated method stub
		log.debug("service.employeeView()");

		EmployeeVO dto = new EmployeeVO();

		dto = dao.viewEmployee(req.getParameter("id"));

		model.addAttribute("dto_empl", dto);
	}

	// �Ĵ�[1] ���� ���� ó��
	@Override
	public void employeeDel(HttpServletRequest req, Model model) {
		// TODO Auto-generated method stub
		log.debug("service.employeeDel()");

		int cnt = 0;
		int step = dao.getCumPoint(req.getParameter("id"));

		if (0 <= step && step <= 15000) {
			step = 9;
		} else if (15001 <= step && step <= 30000) {
			step = 10;
		} else if (30001 <= step && step <= 45000) {
			step = 11;
		} else if (45001 <= step) {
			step = 12;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", req.getParameter("id"));
		map.put("member_step", step);

		cnt = dao.delEmployee(map);

		if (cnt != 0) {
			cnt = dao.updateStep(map);
		}

		model.addAttribute("cnt", cnt);
	}

	@Override
	public void hostReservList(HttpServletRequest req, Model model) {
		// TODO Auto-generated method stub
		log.debug("service.hostReservList()");
	}

	// �Ĵ�[1] ���� �߰�
	@Override
	public void reservAdd(HttpServletRequest req, Model model) {
		// TODO Auto-generated method stub
		log.debug("service.reservAdd()");

		int cnt = 0;
		int index = 0;
		int row = Integer.parseInt(req.getParameter("row"));
		int col = Integer.parseInt(req.getParameter("col"));
		String info = req.getParameter("info");
		
		String date = "20" + req.getParameter("date");
		String time = req.getParameter("time") + ":00";
		String startTime = date + " " + time;
		String[] end = time.split(":");
		
		if(end[1] == "00") {
			end[1] = "30";
		} else if(end[1] == "30") {
			end[0] = String.valueOf((Integer.parseInt(end[0]) + 1));
			end[1] = "00";
		}
		
		String endTime = end[0] + ":" + end[1] + ":00";
		endTime = date + " " + endTime;

		System.out.println("startTime : " + startTime);
		System.out.println("endTime : " + endTime);
		
		RestaurantVO dto = new RestaurantVO();
		dto.setRestaurant_index(Integer.parseInt(req.getParameter("index")));
		
		cnt = dao.resetTable(dto);

		Restaurant_scheduleVO dto3 = new Restaurant_scheduleVO();

		dto3.setSchedule_startDate(Timestamp.valueOf(startTime));
		dto3.setSchedule_startTime(Timestamp.valueOf(startTime));
		dto3.setSchedule_endTime(Timestamp.valueOf(endTime));

		TableVO dto2 = new TableVO();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dto", dto);
		map.put("dto2", dto2);
		map.put("dto3", dto3);

		cnt = dao.addReserv(map);
		
		if (cnt != 0) {
			String[] state = info.split(",");

			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					dto2.setIndex(index);
					dto2.setState(state[index]);
					dto2.setTable_row(i);
					dto2.setTable_col(j);

					map.replace("dto2", dto2);

					cnt = dao.modTable(map);

					if (cnt != 0) {
						index++;
					}
				}
			}
		}

		model.addAttribute("cnt", cnt);
	}
}
