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
		
		System.out.println("startTime : " + startTime);
		System.out.println("endTime : " + endTime);
		
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

		System.out.println("index : " + restaurant_index);
		
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
				// state ���� ��ȸ
				//int state = dao.getState(map);
				// info�� state ������ ���� ���ٷ� �����.
				//info += String.valueOf(state);

				if (!(i + 1 == row && j + 1 == col)) {
					info += ',';
					table_index++;
				}
			}
		}
		//table_dto.setState(info);
		//table_dto.setTable_col(col);
		//table_dto.setTable_row(row);

		
		model.addAttribute("dto", restaurant_dto);
		model.addAttribute("restaurant_index", restaurant_index);
		model.addAttribute("info", info);
		model.addAttribute("col", col);
		model.addAttribute("row", row);
			
		
		/*RestaurantVO restaurant_dto = new RestaurantVO();
		restaurant_dto = dao.reserv_tableList(Integer.parseInt(req.getParameter("index")));

		TableVO table_dto = new TableVO();
		table_dto = dao.getColRow(Integer.parseInt(req.getParameter("index")));

		int col = table_dto.getTable_col() + 1;
		int row = table_dto.getTable_row() + 1;

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

		model.addAttribute("dto", restaurant_dto);
		model.addAttribute("info", info);
		model.addAttribute("col", col);
		model.addAttribute("row", row);
		
		String date = req.getParameter("date");
		model.addAttribute("date", date);

		if (req.getParameter("time") != "") {
			String time = req.getParameter("time");
			model.addAttribute("time", time);
		}*/
	}

	//������� ����ó��
	@Override
	public void reservAdd(HttpServletRequest req, Model model) {
		log.debug("service.reservAdd()");
		
		int cnt=0;
		int index=0;
		int row=Integer.parseInt(req.getParameter("row"));
		int col=Integer.parseInt(req.getParameter("col"));
		//������� �ε��� 
		int restaurant_index = Integer.parseInt(req.getParameter("restaurant_index"));
		//���̵�
		String member_id = (String) req.getSession().getAttribute("memId");
		String info = req.getParameter("info");
		
		//���� ���� �ð� ���� ���� �ð� ��� �κ�(ex:12�� �����̸� 30���� ���Ͽ� ����ð����� ����)
		String date = "20" + req.getParameter("date");
		String time = req.getParameter("time") + ":00";
		String startTime = date + " " + time;
		String[] end = time.split(":");
		System.out.println("=====member_id : " + member_id +"=====");
		System.out.println("restaurant_index:"+restaurant_index);
		
		if(end[1].equals("00")) {
			end[1]="30";
		} else if(end[1].equals("30")) {
			end[0]=String.valueOf((Integer.parseInt(end[0]) + 1));
			end[1]="00";
		}
		
		String endTime = end[0] + ":" + end[1] + ":00";
		endTime = date + " " + endTime;
		
		RestaurantVO restaurant_dto = new RestaurantVO();
		restaurant_dto.setRestaurant_index(restaurant_index);
		
		Restaurant_scheduleVO schedule_dto = new Restaurant_scheduleVO();
		schedule_dto.setSchedule_startTime(Timestamp.valueOf(startTime));
		schedule_dto.setSchedule_endTime(Timestamp.valueOf(endTime));
		schedule_dto.setRestaurant_index(restaurant_index);
	
		System.out.println("���۽ð�" + schedule_dto.getSchedule_startTime());
		System.out.println("����ð�" + schedule_dto.getSchedule_endTime());
		System.out.println("������� �ε��� " + schedule_dto.getRestaurant_index());
		
		//�������ε��� ��ȸ
		Integer schedule_index = dao.getScheduleIndex(schedule_dto);
		System.out.println("������ �ε����� �ִ°�?" + schedule_index);
		
		if (schedule_index != null) {
			schedule_dto.setRestaurant_schedule_index(schedule_index);
		}

		TableVO table_dto = new TableVO();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dto", restaurant_dto);
		map.put("dto2", table_dto);
		map.put("dto3", schedule_dto);
		map.put("member_id", member_id);
		
		//�������� ������..
		if(schedule_index == null) {
			cnt = dao.addReserv(map);
		}
		
		//���� ���� �� ������
		if(cnt == 0) {
			cnt = dao.resetTable2(schedule_dto);
		}
		
		if(cnt!=0) {
			String[] state = info.split(",");
			
			for (int i = 0; i < row; i++) {
				for(int j = 0; j <col; j++) {
					table_dto.setRestaurant_table_index(index);
					table_dto.setState(state[index]);
					table_dto.setTable_row(i);
					table_dto.setTable_col(j);
					
					map.replace("dto2", table_dto);
					
					cnt = dao.modTable2(map);
					//dao.AddHistory(map);
					//dao.AddRHistory(map);
					System.out.println("modTable2 - cnt : " + cnt);
					if(cnt != 0) {
						index++;
					}
				}
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
		//StockDAO dao = StockDAOImpl.getInstance();
		System.out.println("restaurant_index" + restaurant_index);
		//���� ��û ���� ���ϱ�
		System.out.println("ArticleCnt() ȣ�� �� cnt : " + cnt);
		cnt = dao.getReviewCnt(restaurant_index);
		System.out.println("ArticleCnt() ȣ�� �� cnt : " + cnt);

		pageNum = req.getParameter("pageNum");

		if (pageNum == null) {
			pageNum = "1"; // ù�������� 1�������� ����
		}

		currentPage = Integer.parseInt(pageNum);
		System.out.println("currentPage : " + currentPage);

		pageCount = (cnt / pageSize) + (cnt % pageSize > 0 ? 1 : 0);

		start = (currentPage - 1) * pageSize + 1;		// ���� �������� ���� ��ȣ

		end = start + pageSize - 1;						// ���� ������ �Խñ� ������ ��ȣ

		System.out.println("start : " + start);
		System.out.println("end : " + end);

		if (end > cnt)
			end = cnt;

		number = cnt - (currentPage - 1) * pageSize;

		System.out.println("number : " + number);
		System.out.println("cnt : " + cnt);
		System.out.println("currentPage : " + currentPage);
		System.out.println("pageSize : " + pageSize);

		
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
		System.out.println("startPage : " + startPage);

		endPage = startPage + pageBlock - 1;
		if (endPage > pageCount)
			endPage = pageCount;
		System.out.println("endPage : " + endPage);

		model.addAttribute("cnt", cnt); // �۰���
		model.addAttribute("number", number); // �۹�ȣ
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("restaurant_index", restaurant_index);
		
		if (cnt > 0) {
			model.addAttribute("startPage", startPage); // ����������
			model.addAttribute("endPage", endPage); // ������������
			model.addAttribute("pageBlock", pageBlock); // ����� ������ ����
			model.addAttribute("pageCount", pageCount); // ������ ����
			model.addAttribute("currentPage", currentPage);// ���� ������
		}
	}
	
	//4-2. ���� �ۼ�
	@Override
	public void reviewWrite(HttpServletRequest req, Model model) {
		int restaurant_index = Integer.parseInt(req.getParameter("restaurant_index"));
		String review_grade = req.getParameter("review_grade");
		String review_content = req.getParameter("review_content");
		String member_id = (String) req.getSession().getAttribute("memId");
		//String member_id = "member_id 1";
		
		System.out.println("restaurant_index : " + restaurant_index);
		System.out.println("review_grade : " + review_grade);
		System.out.println("review_content : " + review_content);
		System.out.println("member_id : " + member_id);
		
		ReviewVO dto = new ReviewVO();
		dto.setReview_grade(review_grade);
		dto.setReview_content(review_content);
		//dto.setReview_state(review_state);
		dto.setMember_id(member_id);
		
		//����/����/ review_grade, review_content, member_id, review_reg_date  
		int insertCnt = dao.insertReviewPro(dto);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("restaurant_index", restaurant_index);
		
		int insertCnt2 = dao.insertRestaurant_review_Pro(map);
		
		System.out.println("===== State : " + dto.getReview_state() +" =====");
		model.addAttribute("restaurant_index", restaurant_index);
		model.addAttribute("insertCnt", insertCnt);
		System.out.println("restaurant_index : "+ restaurant_index);
		System.out.println("insertCnt : "+ insertCnt);
		System.out.println("insertCnt2 : "+ insertCnt2);

	}

	
	//4-3-2. ���� ����
	@Override
	public void review_modifyView(HttpServletRequest req, Model model) {
		System.out.println("===== 4-3-2. ���� ���� review_modifyView ===== ");
		int review_index = Integer.parseInt(req.getParameter("review_index"));
		int restaurant_index = Integer.parseInt(req.getParameter("restaurant_index"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		String member_id = (String) req.getSession().getAttribute("memId");
		
		//String member_id = "member_id 1";
		String member_pwd = req.getParameter("member_pwd");
		//1234
		
		System.out.println("review_index : " + review_index);
		System.out.println("member_pwd : " + member_pwd);
		System.out.println("pageNum : " + pageNum);
		//System.out.println("restaurant_index : " + restaurant_index);

		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", member_id);
		map.put("member_pwd", member_pwd);
		map.put("review_index", review_index);
		
		int selectCnt = dao.pwdCheck(map);
		
		if(selectCnt==1) {
			//�������������
			ReviewVO dto =  dao.getReviewInfo(map);
			
			model.addAttribute("dto", dto);
			System.out.println("selectCnt : " + selectCnt);
			System.out.println(dto.getReview_index());
			System.out.println(dto.getReview_grade());
			System.out.println(dto.getReview_content());
		}
		
		System.out.println("selectCnt : " + selectCnt);
		model.addAttribute("selectCnt", selectCnt);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("restaurant_index", restaurant_index);
		System.out.println("pageNum : " + pageNum);
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
