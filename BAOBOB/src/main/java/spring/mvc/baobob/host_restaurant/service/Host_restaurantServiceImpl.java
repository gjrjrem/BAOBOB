package spring.mvc.baobob.host_restaurant.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
import spring.mvc.baobob.vo.MenuVO;

@Service
public class Host_restaurantServiceImpl implements Host_restaurantService {
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	Host_restaurantDAO dao;
	
	// �Ĵ�[1] �޴� ����Ʈ
	@Override
	public void menuList(HttpServletRequest req, Model model) {
		// TODO Auto-generated method stub
		log.debug("service.menuList()");

		int pageSize = 4; // �� �������� ����� ���� ��
		int pageBlock = 3; // �� ��ϴ� ����� �������� ��

		int cnt = 0; // �� ����
		int start = 0; // ���� ������ �� ���۹�ȣ
		int end = 0; // ���� ������ �� ��������ȣ
		int number = 0; // ����� �� ��ȣ
		String pageNum = null; // ������ ��ȣ
		int currentPage = 0; // ���� ������

		int pageCount = 0; // ������ ����
		int startPage = 0; // ���� ������
		int endPage = 0; // ������ ������

		cnt = dao.getMenuCnt();

		// �� ����
		pageNum = req.getParameter("pageNum");

		if (pageNum == null) {
			pageNum = "1"; // ù �������� 1�������� ����
		}

		currentPage = (Integer.parseInt(pageNum)); // ���� ������

		// pageCnt = 12 / 5 + 1
		pageCount = (cnt / pageSize) + (cnt % pageSize > 0 ? 1 : 0); // ������ ���� + ������

		// 1 = (1-1) * 5 + 1
		// 6 = (2-1) * 5 + 1
		// 11 = (3-1) * 5 + 1
		// 21 = (5-1) * 5 + 1
		start = (currentPage - 1) * pageSize + 1; // ���� ������ ���۹�ȣ

		// 5 = 1 + 5 - 1
		end = start + pageSize - 1;// ���� ������ ����ȣ
		// end = currentPage * pageSize;

		if (end > cnt) {
			end = cnt;
		}

		// = 21 - (5 - 1) * 5;
		number = cnt - (currentPage - 1) * pageSize; // ����� �۹�ȣ

		if (cnt > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", start);
			map.put("end", end);

			// �Խñ� ��� ��ȸ
			ArrayList<MenuVO> dtos = dao.getMenuList(map);
			model.addAttribute("dtos", dtos);
		}

		startPage = (currentPage / pageBlock) * pageBlock + 1; // (5 / 3) * 3 + 1 = 4

		if ((currentPage % pageBlock == 0)) {
			startPage -= pageBlock;
		}

		endPage = startPage + pageBlock - 1; // 6 = 4 + 3 - 1

		if (endPage > pageCount) {
			endPage = pageCount;
		}

		model.addAttribute("cnt", cnt); // �۰���
		model.addAttribute("number", number); // �۹�ȣ
		model.addAttribute("pageNum", pageNum); // ��������ȣ

		if (cnt > 0) {
			model.addAttribute("startPage", startPage); // ���� ������
			model.addAttribute("endPage", endPage); // ������ ������
			model.addAttribute("pageBlock", pageBlock); // ����� ����������
			model.addAttribute("pageCount", pageCount); // ����������
			model.addAttribute("currentPage", currentPage); // ���� ������
		}
	}

	// �Ĵ�[1] �޴� �߰�
	@Override
	public void menuAdd(MultipartHttpServletRequest req, Model model) {
		// TODO Auto-generated method stub
		log.debug("service.menuAdd()");

		MultipartFile file = req.getFile("img");
		String saveDir = req.getRealPath("/resources/images/chg/"); // ����
		String realDir = "C:\\Dev\\workspace_baobob\\BAOBOB\\BAOBOB\\src\\main\\webapp\\resources\\images\\chg"; // ����

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
		
		dto = dao.viewMenu(1);

		model.addAttribute("dto", dto);
	}

	// �Ĵ�[1] �޴� ���� ó��
	@Override
	public void menuMod(MultipartHttpServletRequest req, Model model) {
		// TODO Auto-generated method stub
		log.debug("service.menuMod()");
		
		
	}
}
