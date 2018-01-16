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
import spring.mvc.baobob.vo.MovieReviewVO;
import spring.mvc.baobob.vo.MovieVO;
import spring.mvc.baobob.vo.ReviewVO;

@Service
public class Guest_movieServiceImpl implements Guest_movieService{

	@Autowired
	Guest_movieDAO gmdao;

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

	
	
	
	
}
