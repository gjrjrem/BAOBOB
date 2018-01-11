package spring.mvc.baobob.guest_movie.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.baobob.guest_movie.persistence.Guest_movieDAO;
import spring.mvc.baobob.vo.MovieVO;

@Service
public class Guest_movieServiceImpl implements Guest_movieService{

	@Autowired
	Guest_movieDAO gmdao;

	//�⺻ ��ȭ����Ʈ
	@Override
	public void movieList(HttpServletRequest req, Model model) {
		
	}

	//������ ��ȭ����Ʈ
	@Override
	public void playingMovieList(HttpServletRequest req, Model model) {
		
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

	@Override
	public void hd_TrailerPlaying(HttpServletRequest req, Model model) {
		int movie_index = Integer.parseInt(req.getParameter("movie_index"));
		
		MovieVO movie = gmdao.getMovie(movie_index);
		
		System.out.println(movie.getMovie_trailer());
		if(movie != null) {
			model.addAttribute("movie",movie);
		}
		
		
	}
	
}
