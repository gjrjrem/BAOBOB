package spring.mvc.baobob.guest_movie.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.baobob.guest_movie.persistence.Guest_movieDAO;
import spring.mvc.baobob.vo.Member;
import spring.mvc.baobob.vo.MovieResViewVO;
import spring.mvc.baobob.vo.MovieVO;
import spring.mvc.baobob.vo.ReviewVO;
import spring.mvc.baobob.vo.TheaterVO;
import spring.mvc.baobob.vo.Theater_scheduleVO;
import spring.mvc.baobob.vo.Theater_seatVO;

@Service
public class Guest_movieServiceImpl implements Guest_movieService{

	@Autowired
	Guest_movieDAO gmdao;

	//��ȭ ����
	@Override
	public void movieMain(HttpServletRequest req, Model model) {
		ArrayList<String> rankList = gmdao.mainMovieRank();
		model.addAttribute("rank", rankList);
		
		int movieCnt = gmdao.mainMovieTheaterCnt();
		if(movieCnt > 0) {
			String pageNum = req.getParameter("pageNum");
			if(pageNum == null) {pageNum = "1";}
			int current = Integer.parseInt(pageNum);
			
			int postSize = 5;
			int start = (current - 1) * postSize + 1;
			int end = start + postSize - 1;
			if(end > movieCnt) { end = movieCnt; }
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", start);
			map.put("end",  end);
			ArrayList<String> movieList = gmdao.mainMovieTheater(map);
			model.addAttribute("start", start);
			model.addAttribute("end", end);
			model.addAttribute("movieList", movieList);
			model.addAttribute("pageNum", pageNum);
			model.addAttribute("movieCnt", movieCnt);
			model.addAttribute("postSize", postSize);
		}
	}
	
	//��ȭ����Ʈ
	@Override
	public void movieList(HttpServletRequest req, Model model) {
		
		int pageSize = 8; 		// �� �������� ����� �� ����
		int pageBlock = 3;		// �� ���� ������ ����
		
		int cnt = 0;			// �� ����
		int start = 0;			// ���� ������ ���� ���۹�ȣ
		int end = 0;			// ���� ������ ���� ��������ȣ
		int number = 0;			// ����� �۹�ȣ
		String pageNum = null;	// ������ ��ȣ
		int currentPage = 0;	// ���� ������
		
		int pageCount = 0;		// ������ ����
		int startPage = 0;		// ����������
		int endPage = 0;		// ������ ������
		
		int movie_state = 1;
		
		movie_state = Integer.parseInt(req.getParameter("movie_state"));
		
		// movie_state(�󿵿���:0, �� ��:1, ������:2)
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("movie_state", movie_state);
		
		// movie_state�� ���� ��ȭ ���� ���ϱ�
		if(movie_state == 0) { //�󿵿���
			cnt = gmdao.getMovieStateCnt(map);
		}else if(movie_state == 1){ //movie_state == 1 ����
			cnt = gmdao.getMovieStateCnt(map);
		}else { //movie_state == 2 ������
			cnt = gmdao.getMovieStateCnt(map);
		}
		System.out.println("��ȭ����: " + cnt);
		 
		pageNum = req.getParameter("pageNum");
		
		if(pageNum == null) { //�������� ������ 
			pageNum = "1"; //ù�������� 1�������� ����
		}
		
		currentPage = Integer.parseInt(pageNum); // ����������
		
		// pageCount = (12 / 5) + (1) // ������2���� 1�������� �Ҵ�ǹǷ� �� 3�������� �ȴ�.
		pageCount = (cnt / pageSize) + ((cnt % pageSize > 0) ? 1 : 0); // ������ ����(�������� ������=> ������ ����+1)
		
		start = ((currentPage-1) * pageSize) + 1; // ���� ��������  DB���� �̾ƿ� ���۹�ȣ
		
		end = start + pageSize - 1;// ���� �������� DB���� �̾ƿ� ����ȣ
		//end = currentPage * pageSize;
		
		/*System.out.println("start: " + start);
		System.out.println("end: " + end);*/
		
		if(end > cnt) end = cnt;
		
		number = (currentPage - 1) * pageSize; // ����� �۹�ȣ(�����ص� �۹�ȣ �����ǰ�).. �ֽű� (ū������)�� 1p 

		if(cnt > 0) {
			//�Խñ� ��� ��ȸ
			map.put("start", start);
			map.put("end", end);
			ArrayList<MovieVO> movies = null;
			// movie_state�� ���� ��ȭ ����Ʈ ���ϱ�
			if(movie_state == 0) { //�󿵿���
				movies = gmdao.getMovieStateMovies(map);
			}else if(movie_state == 1){ //movie_state == 1 ����
				movies = gmdao.getMovieStateMovies(map);
			}else { //movie_state == 2 ������
				movies = gmdao.getMovieStateMovies(map);
			}
			
			model.addAttribute("movies", movies); //ū �ٱ��� : �Խñ� ���   cf)�����ٱ���: �Խñ� 1��
		}
		
		startPage = (currentPage / pageBlock) * pageBlock + 1; //(5/3) * 3 + 1 = 4
		if(currentPage % pageBlock == 0) startPage -= pageBlock; // (5%3) == 0 
		
		endPage = startPage + pageBlock - 1; // 4 + 3 - 1 = 6
		if(endPage > pageCount) endPage = pageCount;
		
		model.addAttribute("cnt", cnt);			// �۰���
		model.addAttribute("number", number);	// �۹�ȣ
		model.addAttribute("pageNum", pageNum);	// ��������ȣ
		
		if(cnt > 0) {
			model.addAttribute("movie_state", movie_state);// ��ȭ����
			model.addAttribute("startPage", startPage); // ����������
			model.addAttribute("endPage", endPage);// ������������
			model.addAttribute("pageBlock", pageBlock);// ����� ������ ����
			model.addAttribute("pageCount", pageCount);// ������ ����
			model.addAttribute("currentPage", currentPage);// ���� ������
		}
	}


	//HD-Ʈ���Ϸ�
	@Override
	public void hd_Trailer(HttpServletRequest req, Model model) {
		
		int pageSize = 8; 		// �� �������� ����� �� ����
		int pageBlock = 3;		// �� ���� ������ ����
		
		int cnt = 0;			// �� ����
		int start = 0;			// ���� ������ ���� ���۹�ȣ
		int end = 0;			// ���� ������ ���� ��������ȣ
		int number = 0;			// ����� �۹�ȣ
		String pageNum = null;	// ������ ��ȣ
		int currentPage = 0;	// ���� ������
		
		int pageCount = 0;		// ������ ����
		int startPage = 0;		// ����������
		int endPage = 0;		// ������ ������
		
		// ��ȭ ���� ���ϱ�
		cnt = gmdao.getMovieCnt();
		System.out.println("��ȭ����: " + cnt);
		 
		pageNum = req.getParameter("pageNum");
		
		if(pageNum == null) { //�������� ������ 
			pageNum = "1"; //ù�������� 1�������� ����
		}
		
		currentPage = Integer.parseInt(pageNum); // ����������
		
		// pageCount = (12 / 5) + (1) // ������2���� 1�������� �Ҵ�ǹǷ� �� 3�������� �ȴ�.
		pageCount = (cnt / pageSize) + ((cnt % pageSize > 0) ? 1 : 0); // ������ ����(�������� ������=> ������ ����+1)
		
		start = ((currentPage-1) * pageSize) + 1; // ���� ��������  DB���� �̾ƿ� ���۹�ȣ
		
		end = start + pageSize - 1;// ���� �������� DB���� �̾ƿ� ����ȣ
		//end = currentPage * pageSize;
		
		/*System.out.println("start: " + start);
		System.out.println("end: " + end);*/
		
		if(end > cnt) end = cnt;
		
		number = cnt - (currentPage - 1) * pageSize; // ����� �۹�ȣ(�����ص� �۹�ȣ �����ǰ�).. �ֽű� (ū������)�� 1p 
		/*System.out.println("number: " + number);
		System.out.println("cnt: " + cnt);
		System.out.println("currentPage: " + currentPage);
		System.out.println("pageSize: " + pageSize);*/

		if(cnt > 0) {
			//�Խñ� ��� ��ȸ
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("start", start);
			map.put("end", end);
			
			ArrayList<MovieVO> movies = gmdao.getAllMovies(map);
			model.addAttribute("movies", movies); //ū �ٱ��� : �Խñ� ���   cf)�����ٱ���: �Խñ� 1��
		}
		
		startPage = (currentPage / pageBlock) * pageBlock + 1; //(5/3) * 3 + 1 = 4
		if(currentPage % pageBlock == 0) startPage -= pageBlock; // (5%3) == 0 
		
		endPage = startPage + pageBlock - 1; // 4 + 3 - 1 = 6
		if(endPage > pageCount) endPage = pageCount;
		
		model.addAttribute("cnt", cnt);			// �۰���
		model.addAttribute("number", number);		// �۹�ȣ
		model.addAttribute("pageNum", pageNum);	// ��������ȣ
		
		if(cnt > 0) {
			model.addAttribute("startPage", startPage); // ����������
			model.addAttribute("endPage", endPage);// ������������
			model.addAttribute("pageBlock", pageBlock);// ����� ������ ����
			model.addAttribute("pageCount", pageCount);// ������ ����
			model.addAttribute("currentPage", currentPage);// ���� ������
		}
	}

	//movie���� �̿�(+ ���ƿ� ����)
	@Override
	public void movieInfo(HttpServletRequest req, Model model) {
		int movie_index = Integer.parseInt(req.getParameter("movie_index"));
		
		MovieVO movie = gmdao.getMovie(movie_index);
		
		System.out.println(movie.getMovie_trailer());
		if(movie != null) {
			model.addAttribute("movie",movie);
			String strLikeCnt = gmdao.movieLike(movie_index);
			if(strLikeCnt != null) {
				//���ƿ� �ۼ�Ʈ ���ϱ�
				int reviewCnt = gmdao.getMovieReviewCnt(movie_index);
				int likeCnt = Integer.parseInt(strLikeCnt);
				int likePercent = 0;
				if(reviewCnt == 0) {
					likePercent = 0;
				}else {
					likePercent = (int)((likeCnt*100)/reviewCnt);
				}
				
				System.out.println("likePercent:" + likePercent);
				model.addAttribute("likePercent", likePercent);
			}
		}
	}

	//�˻���� ����Ʈ
	@Override
	public void searchResult(HttpServletRequest req, Model model) {
		//���� �ޱ�
		String keyword = req.getParameter("keyword");
		String sel = req.getParameter("sel");
		
		String[] str_movie_janre = req.getParameterValues("movie_janre");
		String[] str_movie_age = req.getParameterValues("movie_age");
		String[] movie_country = req.getParameterValues("movie_country");
		
		int[] movie_janre = new int[str_movie_janre.length];

		for(int i =0; i<movie_janre.length; i++){
			movie_janre[i]=Integer.parseInt(str_movie_janre[i]);
		};
		
		int[] movie_age = new int[str_movie_age.length];

		for(int i =0; i<movie_age.length; i++){
			movie_age[i]=Integer.parseInt(str_movie_age[i]);
		};
		
		model.addAttribute("keyword", keyword);
		model.addAttribute("sel", sel);
		model.addAttribute("movie_janre", movie_janre);
		model.addAttribute("movie_age", movie_age);
		model.addAttribute("movie_country", movie_country);
		
		int pageSize = 8; 		// �� �������� ����� �� ����
		int pageBlock = 3;		// �� ���� ������ ����
		
		int cnt = 0;			// �� ����
		int start = 0;			// ���� ������ ���� ���۹�ȣ
		int end = 0;			// ���� ������ ���� ��������ȣ
		int number = 0;			// ����� �۹�ȣ
		String pageNum = null;	// ������ ��ȣ
		int currentPage = 0;	// ���� ������
		
		int pageCount = 0;		// ������ ����
		int startPage = 0;		// ����������
		int endPage = 0;		// ������ ������
		
		// ��ȭ ���� ���ϱ�
		cnt = gmdao.getMovieCnt();
		System.out.println("��ȭ����: " + cnt);
		 
		pageNum = req.getParameter("pageNum");
		
		if(pageNum == null) { //�������� ������ 
			pageNum = "1"; //ù�������� 1�������� ����
		}
		
		currentPage = Integer.parseInt(pageNum); // ����������
		
		// pageCount = (12 / 5) + (1) // ������2���� 1�������� �Ҵ�ǹǷ� �� 3�������� �ȴ�.
		pageCount = (cnt / pageSize) + ((cnt % pageSize > 0) ? 1 : 0); // ������ ����(�������� ������=> ������ ����+1)
		
		start = ((currentPage-1) * pageSize) + 1; // ���� ��������  DB���� �̾ƿ� ���۹�ȣ
		
		end = start + pageSize - 1;// ���� �������� DB���� �̾ƿ� ����ȣ
		//end = currentPage * pageSize;
		
		/*System.out.println("start: " + start);
		System.out.println("end: " + end);*/
		
		if(end > cnt) end = cnt;
		
		number = cnt - (currentPage - 1) * pageSize; // ����� �۹�ȣ(�����ص� �۹�ȣ �����ǰ�).. �ֽű� (ū������)�� 1p 
		/*System.out.println("number: " + number);
		System.out.println("cnt: " + cnt);
		System.out.println("currentPage: " + currentPage);
		System.out.println("pageSize: " + pageSize);*/

		if(cnt > 0) {
			//�Խñ� ��� ��ȸ
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("start", start);
			map.put("end", end);
			
			ArrayList<MovieVO> movies = gmdao.getAllMovies(map);
			model.addAttribute("movies", movies); //ū �ٱ��� : �Խñ� ���   cf)�����ٱ���: �Խñ� 1��
		}
		
		startPage = (currentPage / pageBlock) * pageBlock + 1; //(5/3) * 3 + 1 = 4
		if(currentPage % pageBlock == 0) startPage -= pageBlock; // (5%3) == 0 
		
		endPage = startPage + pageBlock - 1; // 4 + 3 - 1 = 6
		if(endPage > pageCount) endPage = pageCount;
		
		model.addAttribute("cnt", cnt);			// �۰���
		model.addAttribute("number", number);		// �۹�ȣ
		model.addAttribute("pageNum", pageNum);	// ��������ȣ
		
		if(cnt > 0) {
			model.addAttribute("startPage", startPage); // ����������
			model.addAttribute("endPage", endPage);// ������������
			model.addAttribute("pageBlock", pageBlock);// ����� ������ ����
			model.addAttribute("pageCount", pageCount);// ������ ����
			model.addAttribute("currentPage", currentPage);// ���� ������
		}
		
	}

	//���� ����Ʈ
	@Override
	public void reviewList(HttpServletRequest req, Model model) {
		int pageSize = 8; 		// �� �������� ����� �� ����
		int pageBlock = 3;		// �� ���� ������ ����
		
		int cnt = 0;			// �� ����
		int start = 0;			// ���� ������ ���� ���۹�ȣ
		int end = 0;			// ���� ������ ���� ��������ȣ
		int number = 0;			// ����� �۹�ȣ
		String pageNum = null;	// ������ ��ȣ
		int currentPage = 0;	// ���� ������
		
		int pageCount = 0;		// ������ ����
		int startPage = 0;		// ����������
		int endPage = 0;		// ������ ������
		
		//grade�ҷ�����
		
		// �ش� ��ȭ ���� ���� ���ϱ�
		int movie_index = Integer.parseInt(req.getParameter("movie_index"));
		cnt = gmdao.getMovieReviewCnt(movie_index);
		System.out.println("���䰹��: " + cnt);
		 
		pageNum = req.getParameter("pageNum");
		
		if(pageNum == null) { //�������� ������ 
			pageNum = "1"; //ù�������� 1�������� ����
		}
		
		currentPage = Integer.parseInt(pageNum); // ����������
		
		// pageCount = (12 / 5) + (1) // ������2���� 1�������� �Ҵ�ǹǷ� �� 3�������� �ȴ�.
		pageCount = (cnt / pageSize) + ((cnt % pageSize > 0) ? 1 : 0); // ������ ����(�������� ������=> ������ ����+1)
		
		start = ((currentPage-1) * pageSize) + 1; // ���� ��������  DB���� �̾ƿ� ���۹�ȣ
		
		end = start + pageSize - 1;// ���� �������� DB���� �̾ƿ� ����ȣ
		//end = currentPage * pageSize;
		
		/*System.out.println("start: " + start);
		System.out.println("end: " + end);*/
		
		if(end > cnt) end = cnt;
		
		number = cnt - (currentPage - 1) * pageSize; // ����� �۹�ȣ(�����ص� �۹�ȣ �����ǰ�).. �ֽű� (ū������)�� 1p 
		/*System.out.println("number: " + number);
		System.out.println("cnt: " + cnt);
		System.out.println("currentPage: " + currentPage);
		System.out.println("pageSize: " + pageSize);*/

		if(cnt > 0) {
			//�Խñ� ��� ��ȸ
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("start", start);
			map.put("end", end);
			map.put("movie_index", movie_index);
			
			ArrayList<ReviewVO> movieReviews = gmdao.getAllMovieReviews(map);
			model.addAttribute("reviews", movieReviews); //ū �ٱ��� : �Խñ� ���   cf)�����ٱ���: �Խñ� 1��
		}
		
		startPage = (currentPage / pageBlock) * pageBlock + 1; //(5/3) * 3 + 1 = 4
		if(currentPage % pageBlock == 0) startPage -= pageBlock; // (5%3) == 0 
		
		endPage = startPage + pageBlock - 1; // 4 + 3 - 1 = 6
		if(endPage > pageCount) endPage = pageCount;
		
		model.addAttribute("cnt", cnt);			// �۰���
		model.addAttribute("number", number);		// �۹�ȣ
		model.addAttribute("pageNum", pageNum);	// ��������ȣ
		
		if(cnt > 0) {
			model.addAttribute("startPage", startPage); // ����������
			model.addAttribute("endPage", endPage);// ������������
			model.addAttribute("pageBlock", pageBlock);// ����� ������ ����
			model.addAttribute("pageCount", pageCount);// ������ ����
			model.addAttribute("currentPage", currentPage);// ���� ������
		}
	}
		
	//��ȭ �����ۼ�ó��
	@Override
	public void movieReviewPro(HttpServletRequest req, Model model) {
		ReviewVO review = new ReviewVO();
		
		//dao������ ó���ϱ� ���� pro�� ����(1�϶� �ۼ�/ 2�϶� ���� / 3�϶� ����)
		int pro = Integer.parseInt(req.getParameter("pro"));
		
		int movie_index = Integer.parseInt(req.getParameter("movie_index"));
		int cnt = 0;
		
		if(pro==1) { //�ۼ��Ͻ�
			String review_grade = req.getParameter("review_grade");
			String member_id = req.getParameter("member_id");
			String review_content = req.getParameter("review_content");
			
			//1. ��ȭ�� �ѻ���� �ϳ��� ���丸 ����
			Map<String,Object> map1 = new HashMap<String,Object>();
			map1.put("member_id", member_id);
			map1.put("movie_index", movie_index);
			
			//2. review_tbl insert
			review.setReview_content(review_content);
			review.setReview_grade(review_grade);
			review.setMember_id(member_id);
			review.setReview_state(1);
			cnt = gmdao.insertReview(review);
			
			//3. movie_review_tbl insert
			if(cnt==1) {//review �Է� �����ϸ� reviewIndex currval
				Map<String,Object> map2 = new HashMap<String,Object>();
				map2.put("movie_index", movie_index);
				cnt = gmdao.insertMovieReview(map2);
				
			}else {// ����
				cnt = 0;
			}
			
		}else{// ����,���� �Ͻ�
			int review_index = Integer.parseInt(req.getParameter("review_index"));
			
			if(pro==2) { //�����϶�
				String review_grade = req.getParameter("review_grade");
				String member_id = req.getParameter("member_id");
				String review_content = req.getParameter("review_content");
				
				//1. review_tbl update
				review.setReview_index(review_index);
				review.setReview_content(review_content);
				review.setReview_grade(review_grade);
				review.setMember_id(member_id);
				review.setReview_state(1);
				cnt = gmdao.updateReview(review);
				
			}else {//pro==3 �����϶�
				//1. movie_review_tbl ���� ����
				Map<String,Object> map2 = new HashMap<String,Object>();
				map2.put("movie_index", movie_index);
				map2.put("review_index", review_index);
				cnt = gmdao.deleteMovieReview(map2);
				
				//2. review_tbl ����(pk�� review_index�� ������ ��) 
				if(cnt==1) {//review �Է� �����ϸ� 
					cnt = gmdao.deleteReview(review_index);
					
				}else {// ����
					cnt = 0;
				}
						
			}
		}
		
		model.addAttribute("pro", pro);
		model.addAttribute("cnt", cnt);
		
	}

	//���� ������� üũ
	@Override
	public void movieReviewCheck(HttpServletRequest req, Model model) {
		String member_id = (String) req.getSession().getAttribute("memId");
		int movie_index = Integer.parseInt(req.getParameter("movie_index"));
		System.out.println("member_id: "+ member_id);
		int cnt = 0;
		System.out.println("movie_index: "+ movie_index);
		
		//1. ��ȭ�� �ѻ���� �ϳ��� ���丸 ����
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("member_id", member_id);
		map1.put("movie_index", movie_index);
		cnt = gmdao.movieReviewCheck(map1);
		
		if(cnt>0) { //�̹� �ش翵ȭ�� ���並 �������
			cnt = 0;
		}else { //���� ������ ���
			cnt = 1;
		}
		model.addAttribute("cnt", cnt);
	}

	//����â�� ��� ��ȭ����Ʈ(�������� �� ����)
	@Override
	public void reserveMovieList(HttpServletRequest req, Model model) {
		
		int cnt = 0;// ��ȭ ����
		
		//��ü ��ȭ ����
		cnt = gmdao.getMovieCnt();
		
		if(cnt > 0) {
			//�󿵰� ����
			int theaterCnt = gmdao.theaterCnt();
			int[] theaterSeats = new int[theaterCnt+1];
			
			//�� �󿵰� ������ ���¼� ���� ���ϱ�
			for(int i=1; i<=theaterCnt; i++) {
				theaterSeats[i] = gmdao.theaterSeats(i);
			}
			model.addAttribute("theaterSeats", theaterSeats);
			
			//�Խñ� ��� ��ȸ
			ArrayList<MovieVO> movies = null;
			// movie_state�� ���� ��ȭ ����Ʈ ���ϱ�
			movies = gmdao.getAllReserveMovies();
			model.addAttribute("movies", movies); 
		}
		
		model.addAttribute("cnt", cnt);//��ü ��ȭ����
		
	}

	//��ȭ �Ǵ� ��¥, �󿵰� ����
	@Override
	public void reserveDateList(HttpServletRequest req, Model model) {
		int cnt = 0;// ��¥ ����
		
		int movie_index = Integer.parseInt(req.getParameter("movie"));
		int plusDay = Integer.parseInt(req.getParameter("plusDay"));
		
		//��ȭ,��¥�� ������ ����
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("movie_index", movie_index);
		map.put("plusDay", plusDay);
		cnt = gmdao.getDateCnt(map);
		
		if(cnt > 0) {
			
			//��ȭ�� �Ǵ� ��¥, �󿵰� ���� ���� ��
			ArrayList<Theater_scheduleVO> schedules = null;
			schedules = gmdao.getAllReserveSchedules(map);
			model.addAttribute("schedules", schedules); 
		}
		
		model.addAttribute("cnt", cnt);//��ü ��ȭ����
	}

	//��ȭ ������(���ſ��� Ajax�� ���� ����)
	@Override
	public void reserveMovieResult(HttpServletRequest req, Model model) {
		
		//#movieInfo�� �� ����
		int movie_index = Integer.parseInt(req.getParameter("movie_index"));
		MovieVO movie = gmdao.getMovie(movie_index);
		model.addAttribute("movie", movie);
	}

	//������ ������(���ſ��� Ajax�� ���� ����)
	@Override
	public void reserveScheduleResult(HttpServletRequest req, Model model) {

		//#scheduleInfo�� ������
		int theater_schedule_index = Integer.parseInt(req.getParameter("theater_schedule_index"));
		Theater_scheduleVO schedule = gmdao.getSchedule(theater_schedule_index);
		model.addAttribute("schedule", schedule);
	}

	//�¼��� �����ֱ�
	@Override
	public MovieResViewVO movieResView(HttpServletRequest req, Model model) {
		// �¼��� ������ ���� �ٱ��� ����
		MovieResViewVO seatInfo = new MovieResViewVO();
		
		TheaterVO theater = null;
		ArrayList<Theater_seatVO> seats = null;
		
		int theater_index = Integer.parseInt(req.getParameter("theater_index"));
		int theater_schedule_index = Integer.parseInt(req.getParameter("theater_schedule_index"));
		
		//�¼� ���� ������ ���� �ٱ���
		ArrayList<Integer> state = new ArrayList<Integer>();
		
		//�������� ��������
		theater = gmdao.theaterDetail(theater_index);
		System.out.println("theater_col " + theater.getTheater_col());
		System.out.println("theater_row " + theater.getTheater_row());

		//�ش� ������ �󿵰�(theater_schedule_index)�� �¼� ���� ��������
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("theater_index", theater_index);
		map.put("theater_schedule_index", theater_schedule_index);
		seats = gmdao.theaterSeatDetail(map);
		
		//�¼����¸� �Ѱ��ֱ�
		for(Theater_seatVO seat : seats) {
			state.add(seat.getSeat_state());
		}
		//�¼� ������ ������ row, col�� ũ��, �¼����µ��� �Ѱ��ش�.
		seatInfo.setTotalRow(theater.getTheater_row());
		seatInfo.setTotalCol(theater.getTheater_col());
		seatInfo.setState(state);
		
		System.out.println("=========================");
		System.out.println("state : " + state);
		System.out.println("=========================");
		
//			model.addAttribute("vo", vo);
//			model.addAttribute("seat_vos", seat_vos);
//			model.addAttribute("state", state);
		
		return seatInfo;
	}

	//�¼��� ����
	@Override
	public void seatSelect(HttpServletRequest req, Model model) {
		// �¼��� ������ ���� �ٱ��� ����
		MovieResViewVO seatInfo = new MovieResViewVO();
		
		TheaterVO theater = null;
		ArrayList<Theater_seatVO> seats = null;
		
		int theater_index = Integer.parseInt(req.getParameter("theater_index"));
		int theater_schedule_index = Integer.parseInt(req.getParameter("theater_schedule_index"));
		
		//�¼� ���� ������ ���� �ٱ���
		ArrayList<Integer> state = new ArrayList<Integer>();
		//�¼� index������ ���� �ٱ���
		ArrayList<Integer> seat_index = new ArrayList<Integer>();
		
		//�������� ��������
		theater = gmdao.theaterDetail(theater_index);
		System.out.println("theater_col " + theater.getTheater_col());
		System.out.println("theater_row " + theater.getTheater_row());

		//�ش� ������ �󿵰�(theater_schedule_index)�� �¼� ���� ��������
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("theater_index", theater_index);
		map.put("theater_schedule_index", theater_schedule_index);
		seats = gmdao.theaterSeatDetail(map);
		
		//�¼����¸� �Ѱ��ֱ�
		for(Theater_seatVO seat : seats) {
			state.add(seat.getSeat_state());
			seat_index.add(seat.getSeat_index());
		}
		//�¼� ������ ������ row, col�� ũ��, �¼����µ��� �Ѱ��ش�.
		seatInfo.setTotalRow(theater.getTheater_row());
		seatInfo.setTotalCol(theater.getTheater_col());
		seatInfo.setState(state);
		seatInfo.setSeat_index(seat_index);
		
		System.out.println("=========================");
		System.out.println("state : " + state);
		System.out.println("=========================");
		
		model.addAttribute("seatInfo", seatInfo);
		
	}
	
	//�� �¼� ����
	@Override
	public void seatInfo(HttpServletRequest req, Model model) {
		int seat_index = Integer.parseInt(req.getParameter("seat_index"));
		Theater_seatVO seat = new Theater_seatVO();
		
		seat = gmdao.seatInfo(seat_index);
		
		model.addAttribute("seat",seat);
		
	}

	//nextDealButton�� ���� seatInfos
	@Override
	public void seatInfos(HttpServletRequest req, Model model) {
		int adultCnt = Integer.parseInt(req.getParameter("adultCnt"));
		int teenagerCnt = Integer.parseInt(req.getParameter("teenagerCnt"));
		String[] str_seat_index_arr = req.getParameterValues("seat_index_arr");
		int size = str_seat_index_arr.length;
		//�Ѱ� �¼��� ����
		Theater_seatVO seat = new Theater_seatVO();
		//���õ� �������� �¼� ����
		ArrayList<Theater_seatVO> seats = new ArrayList<Theater_seatVO>();
				
		int[] seat_index_arr = new int[size];
		
		for(int i=0; i<size; i++) {
			seat_index_arr[i] = Integer.parseInt(str_seat_index_arr[i]);
			
			//�Ѱ� �¼��� ���� �ٱ��Ͽ� ���
			seat = gmdao.seatInfo(seat_index_arr[i]);
			//�¼��� ������ ArrayList�� ���
			seats.add(seat);
		}
		
		int theater_schedule_index = seat.getTheater_schedule_index();
		
		model.addAttribute("seats", seats);
		model.addAttribute("adultCnt", adultCnt);
		model.addAttribute("teenagerCnt", teenagerCnt);
		model.addAttribute("theater_schedule_index", theater_schedule_index);
		
	}
	
	//����â���� �Ѿ�� �κ�
	@Override
	public void seatInfos2(HttpServletRequest req, Model model) {
		int adultCnt = Integer.parseInt(req.getParameter("adultCnt"));
		int teenagerCnt = Integer.parseInt(req.getParameter("teenagerCnt"));
		String str_seat_index_info = req.getParameter("seat_index_arr");
		String[] str_seat_index_arr = str_seat_index_info.split(",");
		String member_id = (String) req.getSession().getAttribute("memId");
		
		int size = str_seat_index_arr.length;
		//�Ѱ� �¼��� ����
		Theater_seatVO seat = new Theater_seatVO();
		//���õ� �������� �¼� ����
		ArrayList<Theater_seatVO> seats = new ArrayList<Theater_seatVO>();
				
		int[] seat_index_arr = new int[size];
		
		for(int i=0; i<size; i++) {
			seat_index_arr[i] = Integer.parseInt(str_seat_index_arr[i]);
			
			//�Ѱ� �¼��� ���� �ٱ��Ͽ� ���
			seat = gmdao.seatInfo(seat_index_arr[i]);
			//�¼��� ������ ArrayList�� ���
			seats.add(seat);
		}
		int theater_schedule_index = seat.getTheater_schedule_index();
		
		//id���õ� ��� ���� ��������(����Ʈ ��)
		Member member = new Member();
		member = gmdao.getMemberInfo(member_id);
		
		//������ ���� ��������
		Theater_scheduleVO schedule = gmdao.getSchedule(theater_schedule_index);
		int movie_index = schedule.getMovie_index();
		
		//��ȭ ���� ��������
		MovieVO movie = gmdao.getMovie(movie_index);
		
		model.addAttribute("str_seat_index_info", str_seat_index_info);//seat_index string������ �����ִ°�
		model.addAttribute("schedule", schedule);
		model.addAttribute("movie", movie);
		model.addAttribute("member", member);
		model.addAttribute("seats", seats);
		model.addAttribute("adultCnt", adultCnt);
		model.addAttribute("teenagerCnt", teenagerCnt);
		model.addAttribute("theater_schedule_index", theater_schedule_index);
		
	}

	//��������ó��
	@Override
	public void reservationPro(HttpServletRequest req, Model model) {
		
		String str_seat_index_info = req.getParameter("str_seat_index_info");
		int theater_schedule_index = Integer.parseInt(req.getParameter("theater_schedule_index"));
		int totalCnt = Integer.parseInt(req.getParameter("totalCnt"));
		int movie_index = Integer.parseInt(req.getParameter("movie_index"));
		int movie_history_price = Integer.parseInt(req.getParameter("movie_history_price"));
		int member_point = Integer.parseInt(req.getParameter("member_point"));
		
		System.out.println(str_seat_index_info + theater_schedule_index + totalCnt + movie_index + movie_history_price + member_point);
		
		//String[] str_seat_index_arr = str_seat_index_info.split(",");
		String member_id = (String) req.getSession().getAttribute("memId");
		
		//int size = str_seat_index_arr.length;
		
		//1. Insert history_tbl
		
		//2. Insert movie_history_tbl
		
		//3. Update theater_seat_tbl �ش� seat_index�� seat_state=6 ����(�¼� ���� ���༮���� ����)
		
		//4. Update theater_schedule_tbl schedule_empty_seat= -totalCnt���ֱ�(���ڸ��� ����)
		
		//5. Update movie_tbl  movie_count + totalCnt���ֱ�(��ȭ�������� ����)
		
		//6. Update member_tbl member_point, member_cumpoint (������ ����)
		
		//7. Update member_tbl member_point (����Ʈ ��������� ����)
	}

	

	
	

	
	
	
	
}
