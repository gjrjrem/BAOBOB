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
	//============================== 1. 레스토랑 정보 ==============================
	//==========================================================================
	//레스토랑 정보
	@Override
	public void restaurant_imfomation(HttpServletRequest req, Model model) {
		int restaurant_index = Integer.parseInt(req.getParameter("restaurant_index"));
		
		model.addAttribute("restaurant_index", restaurant_index);
	}
	//==========================================================================
	//============================== 2. 레스토랑 메뉴 ==============================
	//==========================================================================
	//레스토랑 메뉴 정보
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
	//============================== 3. 레스토랑 예약 ==============================
	//==========================================================================
	//레스토랑 테이블 확인 //host의 restaurantView참고
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
		
		//레스토랑 인뎃스 
		int restaurant_index = Integer.parseInt(req.getParameter("restaurant_index"));
		
		Restaurant_scheduleVO schedule_dto = new Restaurant_scheduleVO();
		schedule_dto.setSchedule_startTime(Timestamp.valueOf(startTime));
		schedule_dto.setSchedule_endTime(Timestamp.valueOf(endTime));
		schedule_dto.setRestaurant_index(restaurant_index);

		//스케쥴 인덱스 조회
		Integer schedule_index = dao.getScheduleIndex(schedule_dto);
		RestaurantVO restaurant_dto = new RestaurantVO();
		//레스토랑 정보 조회 dao.viewRestaurant()
		restaurant_dto = dao.reserv_tableList(restaurant_index);
	
		//테이블 행열 조회
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
				// state 정보 조회 int state = dao.getState(map); 
				//info에 state 정보를 더해 한줄로 만든다. info += String.valueOf(state);

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

	
	//레스토랑 예약처리
	@Override
	public void reservAdd(HttpServletRequest req, Model model) {
		log.debug("service.reservAdd()");
		
		int cnt=0;
		int index=0;
		
		//레스토랑 인뎃스 
		int restaurant_index = Integer.parseInt(req.getParameter("restaurant_index"));
		//아이디
		String member_id = (String) req.getSession().getAttribute("memId");

		// 타일의 상태(테이블인지 복도인지)
		String info = req.getParameter("info");
		
		// 매장을 구성하는 테이블의 위치(행열)
		int row=Integer.parseInt(req.getParameter("row"));
		int col=Integer.parseInt(req.getParameter("col"));

		String date = "20" + req.getParameter("date");
		String time = req.getParameter("time") + ":00";
		String startTime = date + " " + time;
		String[] end = time.split(":");					

		// 시간을 시, 분 형태로 자름
		System.out.println("=====member_id : " + member_id +"=====");
		System.out.println("restaurant_index:"+restaurant_index);
		
		//예약 시작 시간 예약 종료 시간 계산 부분(ex:12시 예약이면 30분을 더하여 종료시간으로 지정)
		if(end[1].equals("00")) {		// 시작 시간의 분이00분이면 30분으로 바꿈
			end[1]="30";
		} else if(end[1].equals("30")) {// 시작 시간의 분이 30분이면 시에 1을 더해주고 00분으로 바꿈
			end[0]=String.valueOf((Integer.parseInt(end[0]) + 1));
			end[1]="00";
		}
		// 시, 분, 초 조합
		String endTime = end[0] + ":" + end[1] + ":00";
		// 날짜, 시간 조합
		endTime = date + " " + endTime;
		
		// 매장 정보 저장
		RestaurantVO restaurant_dto = new RestaurantVO();
		restaurant_dto.setRestaurant_index(restaurant_index);
		
		// 예약할 매장, 예약할 날짜 시간 정보 저장
		Restaurant_scheduleVO schedule_dto = new Restaurant_scheduleVO();
		schedule_dto.setSchedule_startTime(Timestamp.valueOf(startTime));
		schedule_dto.setSchedule_endTime(Timestamp.valueOf(endTime));
		schedule_dto.setRestaurant_index(restaurant_index);
	
		//스케쥴인덱스 조회
		Integer schedule_index = dao.getScheduleIndex(schedule_dto);
		
		// 스케줄 index가 있다면
		if (schedule_index != null) {
			schedule_dto.setRestaurant_schedule_index(schedule_index);
		}

		// 테이블 정보
		TableVO table_dto = new TableVO();
		int table_index = 0;
		
		// 여러 정보를 저장하기 위해 맵 이용
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("restaurant_dto", restaurant_dto);
		map.put("table_dto", table_dto);
		map.put("schedule_dto", schedule_dto);
		map.put("member_id", member_id);
		System.out.println("member_id"+ member_id);
		
		// 스케줄 index가 없다면
		if(schedule_index == null) {
			cnt = dao.addReserv(map);
			schedule_index = dao.getScheduleIndex(schedule_dto);
			schedule_dto.setRestaurant_schedule_index(schedule_index);
			map.replace("schedule_dto", schedule_dto);
		}
		
		// 새로운 예약을 추가한게 아니라면(기존 예약이 있다면)
		if(cnt == 0) {
			cnt = dao.resetTable2(schedule_dto);// 테이블 초기화(예약 위에 또 예약하는 경우)
		}
		
		// 새로운 예약을 추가한 경우 혹은 테이블을 초기화한 경우
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
					//선택한 좌석의 table_index가져오기
					/*if(table_dto.getState().equals(3)) {
						System.out.println();
						table_index=table_dto.getRestaurant_table_index();
					}*/
				}
			}
			
			//예약된 자리의 위치를 보내준다.
			table_index = Integer.parseInt(req.getParameter("table_index"));
			map.put("restaurant_table_index", table_index);
			// 테이블 예약까지 성공했다면 히스토리에 추가
			cnt = dao.AddHistory(map);
			//히스토리에 추가를 성공했다면 레스토랑 히스토리 추가
			if(cnt != 0) {
				System.out.println("히스토리 추가 완료  : " + cnt);
				
				cnt = dao.AddRHistory(map);
				System.out.println("레스토랑 히스토리 추가 완료 : " + cnt);	
			}
		}
		model.addAttribute("cnt", cnt);
		model.addAttribute("restaurant_index", restaurant_index);
	}
		
	
	
	//==========================================================================
	//============================== 4. 레스토랑 리뷰 ==============================
	//==========================================================================
	//4-1. 리뷰 리스트
	@Override
	public void reviewList(HttpServletRequest req, Model model) {
		int pageSize = 3; 		// 한 페이지당 출력할 게시글 갯수
		int pageBlock = 5; 		// 한 블럭당 페이지 갯수

		int cnt = 0; 			// 게시글 갯수
		int start = 0; 			// 현재 페이지 게시글 시작 번호
		int end = 0;			// 현재 페이지 게시글 마지막 번호
		int number = 0; 		// 출력할 게시글 번호
		String pageNum = null; 	// 페이지 번호
		int currentPage = 0; 	// 현재 페이지

		int pageCount = 0; 		// 페이지 갯수
		int startPage = 0; 		// 시작페이지
		int endPage = 0; 		// 마지막 페이지

		int restaurant_index = Integer.parseInt(req.getParameter("restaurant_index"));                                                                                                                                                                                                                                                                               // dao 객체생성(싱글톤, 다형성)
		//구매 요청 갯수 구하기
		cnt = dao.getReviewCnt(restaurant_index);

		pageNum = req.getParameter("pageNum");

		if (pageNum == null) {
			pageNum = "1"; // 첫페이지를 1페이지로 설정
		}

		currentPage = Integer.parseInt(pageNum); //현재 페이지
		//페이지 갯수 (pageSize가 5이고 전체 글갯수가 12면 2개가 남는데 그 2개도 페이지를 할당해 줘야한다.)
				//pageCnt = 12 / 5 + 1; ... 나머지 2건이 1페이지로 할당되므로 3페이지(2페이지+1페이지)
		pageCount = (cnt / pageSize) + ((cnt % pageSize > 0) ? 1 : 0); 

		start = (currentPage - 1) * pageSize + 1;		// 현재 페이지글 시작 번호

		end = start + pageSize - 1;						// 현재 페이지 게시글 마지막 번호

		if (end > cnt)
			end = cnt;

		//1=21-(5(현제페이지)-1)*5
		number = cnt - (currentPage -1) * pageSize; //출력할 글번호..최신글(큰페이지)가 1페이지 출력할 글번호
			
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

		model.addAttribute("cnt", cnt); // 글갯수
		model.addAttribute("number", number); // 글번호
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("restaurant_index", restaurant_index);
		
		if (cnt > 0) {
			model.addAttribute("startPage", startPage); // 시작페이지
			model.addAttribute("endPage", endPage); 	// 마지막페이지
			model.addAttribute("pageBlock", pageBlock); // 출력할 페이지 개수
			model.addAttribute("pageCount", pageCount); // 페이지 갯수
			model.addAttribute("currentPage", currentPage);// 현재 페이지
		}
	}
	
	//4-2. 리뷰 작성
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

	
	//4-3-2. 리뷰 수정
	@Override
	public void review_modifyView(HttpServletRequest req, Model model) {
		System.out.println("===== 4-3-2. 리뷰 수정 review_modifyView ===== ");
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
			//댓글정보가져옴
			ReviewVO dto =  dao.getReviewInfo(map);
			model.addAttribute("dto", dto);
		}
		
		model.addAttribute("selectCnt", selectCnt);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("restaurant_index", restaurant_index);
	}
	
	//4-3-3 리뷰 수정
	@Override
	public void review_modeifyPro(HttpServletRequest req, Model model) {
		System.out.println("===== 리뷰 수정 처리 review_modeifyPro - 시작 =====");
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
		System.out.println("업데이트 결과(전)!!");
		System.out.println("=========id : " + dto.getMember_id());
		System.out.println("=========grade : " + dto.getReview_grade());
		System.out.println("=========content : " + dto.getReview_content());

		
		int cnt = dao.updateReviewPro(dto);
		System.out.println("업데이트 결과(후)!!cnt : " + cnt);
		model.addAttribute("cnt", cnt);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("restaurant_index", restaurant_index);

	}
	
	//4-4. 리뷰 삭제
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
		//selectCnt 0: 아이디 X, -1 : 아이디O 비번X, 1 : 아이디 비번 일치
		if(selectCnt==1) {
			//댓글정보가져옴
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
