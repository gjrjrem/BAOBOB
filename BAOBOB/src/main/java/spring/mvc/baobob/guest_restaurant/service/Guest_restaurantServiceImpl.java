package spring.mvc.baobob.guest_restaurant.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.baobob.guest_restaurant.persistence.Guest_restaurantDAO;
import spring.mvc.baobob.vo.MenuVO;
import spring.mvc.baobob.vo.RestaurantVO;
import spring.mvc.baobob.vo.Restaurant_scheduleVO;
import spring.mvc.baobob.vo.ReviewVO;
import spring.mvc.baobob.vo.TableVO;

@Service
public class Guest_restaurantServiceImpl implements Guest_restaurantService{

	private Logger log = Logger.getLogger(this.getClass());

	@Autowired 
	Guest_restaurantDAO dao;
	//==========================================================================
	//============================== 1. ������� ���� ==============================
	//==========================================================================
	//������� ����
	@Override
	public void restaurant_imfomation(HttpServletRequest req, Model model) {
		int restaurant_index = Integer.parseInt(req.getParameter("restaurant_index"));
		
		model.addAttribute("restaurant_index", restaurant_index);
	}
	//==========================================================================
	//============================== 2. ������� �޴� ==============================
	//==========================================================================
	//������� �޴� ����
	@Override
	public void restaurant_menuInfo(HttpServletRequest req, Model model) {
		int cnt = 0;
		int restaurant_index = Integer.parseInt(req.getParameter("restaurant_index"));
		
		cnt = dao.getArticleCnt_menu(restaurant_index);
		
		ArrayList<MenuVO> dtos = dao.getArticle_menu(restaurant_index);
		
		model.addAttribute("cnt", cnt);
		model.addAttribute("dtos", dtos);
		model.addAttribute("restaurant_index", restaurant_index);
		
	}
	//==========================================================================
	//============================== 3. ������� ���� ==============================
	//==========================================================================
	//������� ���̺� Ȯ�� //host�� restaurantView����
	@Override
	public void restaurant_tableList(HttpServletRequest req, Model model) {
		log.debug("===== Service/restaurant_tableList() =====");

		String date = "20" + req.getParameter("date");
		model.addAttribute("date", req.getParameter("date"));

		String time = req.getParameter("time");
		String startTime = "";
		String[] end;
		String endTime = "";

		time = req.getParameter("time") + ":00";
		model.addAttribute("time", req.getParameter("time"));
		startTime = date + " " + time;
		end = time.split(":");
		
		if (end[1].equals("00")) {
			end[1] = "30";
		} else if (end[1].equals("30")) {
			end[0] = String.valueOf((Integer.parseInt(end[0]) + 1));
			end[1] = "00";
		}
		endTime = end[0] + ":" + end[1] + ":00";
		endTime = date + " " + endTime;
		
		//������� �ε��� 
		int restaurant_index = Integer.parseInt(req.getParameter("restaurant_index"));
		
		Restaurant_scheduleVO schedule_dto = new Restaurant_scheduleVO();
		schedule_dto.setSchedule_startTime(Timestamp.valueOf(startTime));
		schedule_dto.setSchedule_endTime(Timestamp.valueOf(endTime));
		schedule_dto.setRestaurant_index(restaurant_index);

		//������ �ε��� ��ȸ
		Integer schedule_index = dao.getScheduleIndex(schedule_dto);
		RestaurantVO restaurant_dto = new RestaurantVO();
		//������� ���� ��ȸ dao.viewRestaurant()
		restaurant_dto = dao.reserv_tableList(restaurant_index);
	
		//���̺� �࿭ ��ȸ
		TableVO table_dto = new TableVO();
		table_dto = dao.getColRow(restaurant_index);

		int col = table_dto.getTable_col() + 1;
		int row = table_dto.getTable_row() + 1;

		String info = "";
		int table_index = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("restaurant_index", restaurant_index);
		map.put("restaurant_table_index", table_index);
		map.put("restaurant_schedule_index", schedule_index);

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				map.replace("restaurant_table_index", table_index);

				info += dao.getState(map);
				// state ���� ��ȸ int state = dao.getState(map); 
				//info�� state ������ ���� ���ٷ� �����. info += String.valueOf(state);

				if (!(i + 1 == row && j + 1 == col)) {
					info += ',';
					table_index++;
				}
			}
		}
		
		model.addAttribute("dto", restaurant_dto);
		model.addAttribute("restaurant_index", restaurant_index);
		model.addAttribute("info", info);
		model.addAttribute("col", col);
		model.addAttribute("row", row);
			
		
		
	}

	
	//������� ����ó��
	@Override
	public void reservAdd(HttpServletRequest req, Model model) {
		log.debug("service.reservAdd()");
		
		int cnt=0;
		int index=0;
		
		//������� �ε��� 
		int restaurant_index = Integer.parseInt(req.getParameter("restaurant_index"));
		//���̵�
		String member_id = (String) req.getSession().getAttribute("memId");

		// Ÿ���� ����(���̺����� ��������)
		String info = req.getParameter("info");
		
		// ������ �����ϴ� ���̺��� ��ġ(�࿭)
		int row=Integer.parseInt(req.getParameter("row"));
		int col=Integer.parseInt(req.getParameter("col"));

		String date = "20" + req.getParameter("date");
		String time = req.getParameter("time") + ":00";
		String startTime = date + " " + time;
		String[] end = time.split(":");					

		// �ð��� ��, �� ���·� �ڸ�
		System.out.println("=====member_id : " + member_id +"=====");
		System.out.println("restaurant_index:"+restaurant_index);
		
		//���� ���� �ð� ���� ���� �ð� ��� �κ�(ex:12�� �����̸� 30���� ���Ͽ� ����ð����� ����)
		if(end[1].equals("00")) {		// ���� �ð��� ����00���̸� 30������ �ٲ�
			end[1]="30";
		} else if(end[1].equals("30")) {// ���� �ð��� ���� 30���̸� �ÿ� 1�� �����ְ� 00������ �ٲ�
			end[0]=String.valueOf((Integer.parseInt(end[0]) + 1));
			end[1]="00";
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
	
		//�������ε��� ��ȸ
		Integer schedule_index = dao.getScheduleIndex(schedule_dto);
		
		// ������ index�� �ִٸ�
		if (schedule_index != null) {
			schedule_dto.setRestaurant_schedule_index(schedule_index);
		}

		// ���̺� ����
		TableVO table_dto = new TableVO();
		int table_index = 0;
		
		// ���� ������ �����ϱ� ���� �� �̿�
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("restaurant_dto", restaurant_dto);
		map.put("table_dto", table_dto);
		map.put("schedule_dto", schedule_dto);
		map.put("member_id", member_id);
		System.out.println("member_id"+ member_id);
		
		// ������ index�� ���ٸ�
		if(schedule_index == null) {
			cnt = dao.addReserv(map);
			schedule_index = dao.getScheduleIndex(schedule_dto);
			schedule_dto.setRestaurant_schedule_index(schedule_index);
			map.replace("schedule_dto", schedule_dto);
		}
		
		// ���ο� ������ �߰��Ѱ� �ƴ϶��(���� ������ �ִٸ�)
		if(cnt == 0) {
			cnt = dao.resetTable2(schedule_dto);// ���̺� �ʱ�ȭ(���� ���� �� �����ϴ� ���)
		}
		
		// ���ο� ������ �߰��� ��� Ȥ�� ���̺��� �ʱ�ȭ�� ���
		if(cnt!=0) {
			String[] state = info.split(",");
			
			for (int i = 0; i < row; i++) {
				for(int j = 0; j <col; j++) {
					table_dto.setRestaurant_table_index(index);
					table_dto.setState(state[index]);
					table_dto.setTable_row(i);
					table_dto.setTable_col(j);
					
					map.replace("table_dto", table_dto);
					
					cnt = dao.modTable2(map);

					System.out.println("modTable2 - cnt : " + cnt);
					if(cnt != 0) {
						index++;
					}
					//������ �¼��� table_index��������
					/*if(table_dto.getState().equals(3)) {
						System.out.println();
						table_index=table_dto.getRestaurant_table_index();
					}*/
				}
			}
			
			//����� �ڸ��� ��ġ�� �����ش�.
			table_index = Integer.parseInt(req.getParameter("table_index"));
			map.put("restaurant_table_index", table_index);
			// ���̺� ������� �����ߴٸ� �����丮�� �߰�
			cnt = dao.AddHistory(map);
			//�����丮�� �߰��� �����ߴٸ� ������� �����丮 �߰�
			if(cnt != 0) {
				System.out.println("�����丮 �߰� �Ϸ�  : " + cnt);
				
				cnt = dao.AddRHistory(map);
				System.out.println("������� �����丮 �߰� �Ϸ� : " + cnt);	
			}
		}
		model.addAttribute("cnt", cnt);
		model.addAttribute("restaurant_index", restaurant_index);
	}
		
	
	
	//==========================================================================
	//============================== 4. ������� ���� ==============================
	//==========================================================================
	//4-1. ���� ����Ʈ
	@Override
	public void reviewList(HttpServletRequest req, Model model) {
		int pageSize = 3; 		// �� �������� ����� �Խñ� ����
		int pageBlock = 5; 		// �� ���� ������ ����

		int cnt = 0; 			// �Խñ� ����
		int start = 0; 			// ���� ������ �Խñ� ���� ��ȣ
		int end = 0;			// ���� ������ �Խñ� ������ ��ȣ
		int number = 0; 		// ����� �Խñ� ��ȣ
		String pageNum = null; 	// ������ ��ȣ
		int currentPage = 0; 	// ���� ������

		int pageCount = 0; 		// ������ ����
		int startPage = 0; 		// ����������
		int endPage = 0; 		// ������ ������

		int restaurant_index = Integer.parseInt(req.getParameter("restaurant_index"));                                                                                                                                                                                                                                                                               // dao ��ü����(�̱���, ������)
		//���� ��û ���� ���ϱ�
		cnt = dao.getReviewCnt(restaurant_index);

		pageNum = req.getParameter("pageNum");

		if (pageNum == null) {
			pageNum = "1"; // ù�������� 1�������� ����
		}

		currentPage = Integer.parseInt(pageNum); //���� ������
		//������ ���� (pageSize�� 5�̰� ��ü �۰����� 12�� 2���� ���µ� �� 2���� �������� �Ҵ��� ����Ѵ�.)
				//pageCnt = 12 / 5 + 1; ... ������ 2���� 1�������� �Ҵ�ǹǷ� 3������(2������+1������)
		pageCount = (cnt / pageSize) + ((cnt % pageSize > 0) ? 1 : 0); 

		start = (currentPage - 1) * pageSize + 1;		// ���� �������� ���� ��ȣ

		end = start + pageSize - 1;						// ���� ������ �Խñ� ������ ��ȣ

		if (end > cnt)
			end = cnt;

		//1=21-(5(����������)-1)*5
		number = cnt - (currentPage -1) * pageSize; //����� �۹�ȣ..�ֽű�(ū������)�� 1������ ����� �۹�ȣ
			
		if (cnt > 0) {
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("start", start);
			map.put("end", end);
			map.put("restaurant_index", restaurant_index);
			
			ArrayList<ReviewVO> dtos = dao.getReviewList(map);
			model.addAttribute("dtos", dtos);
		}

		startPage = (currentPage / pageBlock) * pageBlock + 1;

		if (currentPage % pageBlock == 0)
			startPage -= pageBlock;

		endPage = startPage + pageBlock - 1;
		if (endPage > pageCount)
			endPage = pageCount;

		model.addAttribute("cnt", cnt); // �۰���
		model.addAttribute("number", number); // �۹�ȣ
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("restaurant_index", restaurant_index);
		
		if (cnt > 0) {
			model.addAttribute("startPage", startPage); // ����������
			model.addAttribute("endPage", endPage); 	// ������������
			model.addAttribute("pageBlock", pageBlock); // ����� ������ ����
			model.addAttribute("pageCount", pageCount); // ������ ����
			model.addAttribute("currentPage", currentPage);// ���� ������
		}
	}
	
	//4-2. ���� �ۼ�
	@Override
	public void reviewWrite(HttpServletRequest req, Model model) {
		int restaurant_index = Integer.parseInt(req.getParameter("restaurant_index"));
		String review_grade = req.getParameter("star");
		String review_content = req.getParameter("review_content");
		String member_id = (String) req.getSession().getAttribute("memId");
		
		ReviewVO dto = new ReviewVO();
		dto.setReview_grade(review_grade);
		dto.setReview_content(review_content);
		dto.setMember_id(member_id);
		
		int insertCnt = dao.insertReviewPro(dto);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("restaurant_index", restaurant_index);
		
		int insertCnt2 = dao.insertRestaurant_review_Pro(map);
		
		model.addAttribute("restaurant_index", restaurant_index);
		model.addAttribute("insertCnt", insertCnt);
	}

	
	//4-3-2. ���� ����
	@Override
	public void review_modifyView(HttpServletRequest req, Model model) {
		System.out.println("===== 4-3-2. ���� ���� review_modifyView ===== ");
		int review_index = Integer.parseInt(req.getParameter("review_index"));
		int restaurant_index = Integer.parseInt(req.getParameter("restaurant_index"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		String member_id = (String) req.getSession().getAttribute("memId");
		String member_pwd = req.getParameter("member_pwd");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", member_id);
		map.put("member_pwd", member_pwd);
		map.put("review_index", review_index);
		
		int selectCnt = dao.pwdCheck(map);
		
		if(selectCnt==1) {
			//�������������
			ReviewVO dto =  dao.getReviewInfo(map);
			model.addAttribute("dto", dto);
		}
		
		model.addAttribute("selectCnt", selectCnt);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("restaurant_index", restaurant_index);
	}
	
	//4-3-3 ���� ����
	@Override
	public void review_modeifyPro(HttpServletRequest req, Model model) {
		System.out.println("===== ���� ���� ó�� review_modeifyPro - ���� =====");
		int restaurant_index = Integer.parseInt(req.getParameter("restaurant_index"));
		int review_index = Integer.parseInt(req.getParameter("review_index"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		String member_id = (String) req.getSession().getAttribute("memId");
		//String member_id = "member_id 1";
		//System.out.println("restaurant_index : " + restaurant_index);
		System.out.println("review_index : " + review_index);
		System.out.println("pageNum : " + pageNum);

		ReviewVO dto = new ReviewVO();
		dto.setMember_id(member_id);
		dto.setReview_index(review_index);
		dto.setReview_grade(req.getParameter("review_grade"));
		dto.setReview_content(req.getParameter("review_content"));
		System.out.println("������Ʈ ���(��)!!");
		System.out.println("=========id : " + dto.getMember_id());
		System.out.println("=========grade : " + dto.getReview_grade());
		System.out.println("=========content : " + dto.getReview_content());

		
		int cnt = dao.updateReviewPro(dto);
		System.out.println("������Ʈ ���(��)!!cnt : " + cnt);
		model.addAttribute("cnt", cnt);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("restaurant_index", restaurant_index);

	}
	
	//4-4. ���� ����
	@Override
	public void reviewDeletePro(HttpServletRequest req, Model model) {
		int restaurant_index = Integer.parseInt(req.getParameter("restaurant_index"));
		int review_index = Integer.parseInt(req.getParameter("review_index"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		//String member_id = "member_id 1";
		String member_id = (String) req.getSession().getAttribute("memId");
		String member_pwd = req.getParameter("member_pwd");
		//1234
		
		System.out.println("review_index : " + review_index);
		System.out.println("member_pwd : " + member_pwd);
		System.out.println("pageNum : " + pageNum);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", member_id);
		map.put("member_pwd", member_pwd);
		map.put("review_index", review_index);
		
		int selectCnt = dao.pwdCheck(map);
		//selectCnt 0: ���̵� X, -1 : ���̵�O ���X, 1 : ���̵� ��� ��ġ
		if(selectCnt==1) {
			//�������������
			int deleteCnt = dao.delete_ReviewPro(map);
			
			model.addAttribute("deleteCnt", deleteCnt);
			//model.addAttribute("dto", review_index);
			System.out.println("deleteCnt : " + deleteCnt);
		}
		
		System.out.println("selectCnt : " + selectCnt);
		model.addAttribute("selectCnt", selectCnt);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("restaurant_index", restaurant_index);
		System.out.println("pageNum : " + pageNum);

		
		model.addAttribute("selectCnt", selectCnt);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("restaurant_index", restaurant_index);
	}
	
	
	
	
	
	
	
}
