package spring.mvc.baobob.host_movie.service;

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
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import spring.mvc.baobob.host_movie.persistence.Host_movieDAO;
import spring.mvc.baobob.host_movie.persistence.Host_movieDAOImpl;
import spring.mvc.baobob.twitterKoreanParser.KoreanParser;
import spring.mvc.baobob.vo.HostMovieChartVO;
import spring.mvc.baobob.vo.Member;
import spring.mvc.baobob.vo.MovieResViewVO;
import spring.mvc.baobob.vo.MovieVO;
import spring.mvc.baobob.vo.TheaterVO;
import spring.mvc.baobob.vo.Theater_scheduleVO;
import spring.mvc.baobob.vo.Theater_seatVO;
import spring.mvc.baobob.vo.WordVO;
import spring.mvc.baobob.vo.hostTChartVO;

@Service
public class Host_movieServiceImpl implements Host_movieService{
	
	@Autowired
	Host_movieDAO dao = new Host_movieDAOImpl();
	
	private Logger log = Logger.getLogger(this.getClass());

	// ��ȭ ���
	@Override
	public void hostMovieList(HttpServletRequest req, Model model) {
		int cnt = 0;			// ��ȭ ����
		cnt = dao.getMovieCnt(); // ��ȭ ���� ���ϱ�
		if(cnt > 0) {
			ArrayList<MovieVO> vos = dao.getMovieList(); // ��ȭ ��� ��ȸ
			model.addAttribute("vos", vos);
			model.addAttribute("cnt", cnt);
		}
		model.addAttribute("cnt", cnt);
	}
	
	//��ȭ �߰� ó��
	@Override
	public void hostMovieAddPro(MultipartHttpServletRequest req, Model model) {
		MultipartFile file = req.getFile("movie_poster"); // hostMovieAddForm.jsp�� <input type="file"> ���¸� ������
		String saveDir = req.getSession().getServletContext().getRealPath("/resources/images/phc/"); // �ӽ� ������ ���
		String realDir = "C:\\Dev\\workspace_baobob\\BAOBOB\\BAOBOB\\src\\main\\webapp\\resources\\images\\phc\\"; // ������ ���
		
		try {
			file.transferTo(new File(saveDir+file.getOriginalFilename())); // ���ε� �� ���� �����͸� ������ ��� ���Ͽ� �����Ѵ�.
			
			FileInputStream fis = new FileInputStream(saveDir + file.getOriginalFilename()); // �о�� ���ϰ�� ����
			FileOutputStream fos = new FileOutputStream(realDir + file.getOriginalFilename()); // �� ���� ���
			
			int data = 0;
			
			while((data = fis.read()) != -1) { // �ӽ� ���� ��ο� ������ ������ read�ؼ� ������ ���� ��ο� write
				fos.write(data);
			}
			fis.close(); // FileInputStream ����
			fos.close(); // FileOutputStream ����
			
			String movie_title = req.getParameter("movie_title");
			String movie_content = req.getParameter("movie_content");
			int movie_janre = Integer.parseInt(req.getParameter("movie_janre"));
			int movie_age = Integer.parseInt(req.getParameter("movie_age"));
			String movie_rel_date = req.getParameter("movie_rel_date");
			String movie_director = req.getParameter("movie_director");
			String movie_star = req.getParameter("movie_star");
			String movie_country = req.getParameter("movie_country");
			int movie_runTime = Integer.parseInt(req.getParameter("movie_runTime"));
			String movie_poster = file.getOriginalFilename();
			String movie_trailer = req.getParameter("movie_trailer");
			int movie_state = Integer.parseInt(req.getParameter("movie_state"));
			
			MovieVO vo = new MovieVO();
			
			vo.setMovie_title(movie_title);
			vo.setMovie_content(movie_content);
			vo.setMovie_janre(movie_janre);
			vo.setMovie_age(movie_age);
			vo.setMovie_rel_date(movie_rel_date);
			vo.setMovie_age(movie_age);
			vo.setMovie_director(movie_director);
			vo.setMovie_star(movie_star);
			vo.setMovie_country(movie_country);
			vo.setMovie_runTime(movie_runTime);
			vo.setMovie_poster(movie_poster);
			vo.setMovie_trailer(movie_trailer);
			vo.setMovie_state(movie_state);
			
			int cnt = dao.hostMovieAddPro(vo); // ��ȭ �߰� ó��
			
			model.addAttribute("cnt", cnt);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	// ��ȭ ����
	@Override
	public void hostMovieDel(HttpServletRequest req, Model model) {
		int movie_index = Integer.parseInt(req.getParameter("movie_index"));
		
		int cnt = dao.hostMovieDel(movie_index); // movie_index�� ��ȭ������ ����
		
		model.addAttribute("cnt", cnt);
	}

	// ��ȭ ��
	@Override
	public void hostMovieDetail(HttpServletRequest req, Model model) {
		int movie_index = Integer.parseInt(req.getParameter("movie_index"));
		
		MovieVO vo = dao.hostMovieDetail(movie_index); // movie_index�� �ش��ϴ� ��ȭ �� ������ MovieVO�� ����
		
		if(vo!=null) {						// ���� ������ null�� �ƴϸ�
			model.addAttribute("vo", vo);
		}
		
	}

	// ��ȭ ����
	@Override
	public void hostMovieModPro(MultipartHttpServletRequest req, Model model) {
		String movie_poster = null;
		try {
			if(req.getFile("movie_poster").getOriginalFilename()!="") { // �����͸� �����Ѵٸ�
				MultipartFile file = req.getFile("movie_poster");
				String saveDir = req.getSession().getServletContext().getRealPath("/resources/images/phc/");
				String realDir = "C:\\Dev\\workspace_baobob\\BAOBOB\\BAOBOB\\src\\main\\webapp\\resources\\images\\phc\\";
				file.transferTo(new File(saveDir+file.getOriginalFilename()));
				FileInputStream fis = new FileInputStream(saveDir + file.getOriginalFilename());
				FileOutputStream fos = new FileOutputStream(realDir + file.getOriginalFilename());
				
				int data = 0;
				
				while((data = fis.read()) != -1) {
					fos.write(data);
				}
				fis.close();
				fos.close();
				movie_poster = file.getOriginalFilename();
			} else { // �����͸� �������� �ʴٸ�
				movie_poster = req.getParameter("movie_posterDefault"); // ������ ������ ���� ������
			}
			
			int movie_index = Integer.parseInt(req.getParameter("movie_index"));
			String movie_title = req.getParameter("movie_title");
			String movie_content = req.getParameter("movie_content");
			int movie_janre = Integer.parseInt(req.getParameter("movie_janre"));
			int movie_age = Integer.parseInt(req.getParameter("movie_age"));
			String movie_rel_date = req.getParameter("movie_rel_date");
			String movie_director = req.getParameter("movie_director");
			String movie_star = req.getParameter("movie_star");
			String movie_country = req.getParameter("movie_country");
			int movie_runTime = Integer.parseInt(req.getParameter("movie_runTime"));
			String movie_trailer = req.getParameter("movie_trailer");
			int movie_state = Integer.parseInt(req.getParameter("movie_state"));
			
			MovieVO vo = new MovieVO();
			
			vo.setMovie_index(movie_index);
			vo.setMovie_title(movie_title);
			vo.setMovie_content(movie_content);
			vo.setMovie_janre(movie_janre);
			vo.setMovie_age(movie_age);
			vo.setMovie_rel_date(movie_rel_date);
			vo.setMovie_age(movie_age);
			vo.setMovie_director(movie_director);
			vo.setMovie_star(movie_star);
			vo.setMovie_country(movie_country);
			vo.setMovie_runTime(movie_runTime);
			vo.setMovie_poster(movie_poster);
			vo.setMovie_trailer(movie_trailer);
			vo.setMovie_state(movie_state);
			
			int cnt = dao.hostMovieModPro(vo); // ��ȭ ���� ���� ó��
			
			model.addAttribute("cnt", cnt);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}

	// �󿵰� �߰� ó��
	@Override
	public void hostTheaterAddPro(HttpServletRequest req, Model model) {
		int theater_index = Integer.parseInt(req.getParameter("theater_index")); // n��
		int theater_col = Integer.parseInt(req.getParameter("col")); // �󿵰� ��ü ��
		int theater_row = Integer.parseInt(req.getParameter("row")); // �󿵰� ��ü ��
		String status = req.getParameter("state"); // ��ü �¼� ����
		String[] state = status.split(","); // ��ü �¼��� �¼� ����
		
		int indexChkCnt = dao.theater_index_check(theater_index); // �߰��Ϸ��� �󿵰��� �ߺ��Ǵ��� üũ
		if(indexChkCnt == 0) { // �߰��Ϸ��� �󿵰��� �ߺ��� �ƴ϶�� �󿵰��� ���� �߰�
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("theater_index", theater_index); // �󿵰� n��
			map.put("theater_col", theater_col); // �󿵰� ��ü ��
			map.put("theater_row", theater_row); // �󿵰� ��ü ��
			int insertCnt = dao.insert_theater(map); // �󿵰� �߰� ó��
			if(insertCnt == 1) { // �󿵰� �߰��� �����ϸ�
				map.put("state", 0); // �¼����� �ʱ� ����
				map.put("col", 0); // �� �ʱ� ����
				map.put("row", 0); // �� �ʱ� ����
				map.put("price", 0); // �¼��� ���� �ʱ� ����
				
				for(int row = 1; row<=theater_row; row++) { // 1���� ��ü ����� for�� ����
					for(int col = 1; col<=theater_col; col++) { // 1���� ��ü ������ for�� ����
						map.replace("state", Integer.parseInt(state[(row-1)*theater_col-1+col])); // ��� ���� �ش��ϴ� �¼� ���� ����
						map.replace("col", col); // ��
						map.replace("row", row); // ��
						if(Integer.parseInt(state[(row-1)*theater_col-1+col])==3)  // �¼����°� �Ϲݼ� 9000��
							map.replace("price", 9000);
						else if(Integer.parseInt(state[(row-1)*theater_col-1+col])==4) // �¼����°� �����̾��� 11000��
							map.replace("price", 11000);
						else if(Integer.parseInt(state[(row-1)*theater_col-1+col])==5) // �¼� ���°� Ŀ�ü� 15000��
							map.replace("price", 15000);
						else map.replace("price", 0); // �������� 0������ ����
							
						dao.insert_theater_seat(map); // ��� ���� �ش��ϴ� state�� ������ INSERT
					}
				}
				model.addAttribute("cnt", 1); // �¼� ��� ��
			}
		}
	}
	
	// �󿵰� ����Ʈ
	@Override
	public void hostTheaterList(HttpServletRequest req, Model model) {
		int cnt = 0; // �󿵰� ��
		cnt = dao.getTheaterCnt(); // �󿵰� �� ���ϱ�
		if(cnt > 0) {
			ArrayList<TheaterVO> vos = dao.getTheaterList(); // �󿵰� ��� ��ȸ
			model.addAttribute("vos", vos);
		}
		model.addAttribute("cnt", cnt);
	}
	
	// �󿵰� ��
	@Override
	public void hostTheaterDetail(HttpServletRequest req, Model model) {
		TheaterVO vo = null;
		ArrayList<Theater_seatVO> seat_vos = null;
		
		int theater_index = Integer.parseInt(req.getParameter("theater_index")); // �󿵰� index
		ArrayList<Integer> state = new ArrayList<Integer>(); // ��ü �¼� ����
		
		vo = dao.hostTheaterDetail(theater_index); // �󿵰� index�� �ش��ϴ� �󿵰� ������ TheaterVO�� ����
		seat_vos = dao.hostTheaterSeatDetail(theater_index); // �󿵰� index�� �ش��ϴ� �¼������� ArrayList<Theater_seatVO>�� ����
		for(Theater_seatVO seat_vo : seat_vos) { // �迭�� �޾ƿ� seat ������  ��ü �¼� ���׿� ����
			state.add(seat_vo.getSeat_state());
		}

		model.addAttribute("vo", vo);
		model.addAttribute("seat_vos", seat_vos);
		model.addAttribute("state", state);
	}
	
	// �󿵰� ���� ó��
	@Override
	public void hostTheaterModPro(HttpServletRequest req, Model model) {
		int theater_index = Integer.parseInt(req.getParameter("theater_index")); // �󿵰� index (n��)
		int theater_col = Integer.parseInt(req.getParameter("col")); // �󿵰� ��ü ��
		int theater_row = Integer.parseInt(req.getParameter("row")); // �󿵰� ��ü ��
		String status = req.getParameter("state"); // ��ü �¼� ����
		String[] state = status.split(","); // ��ü �¼��� �¼� ����
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("theater_index", theater_index); // n��
		map.put("theater_col", theater_col); // �󿵰� ��ü ��
		map.put("theater_row", theater_row); // �󿵰� ��ü ��
		map.put("state", 0); // �¼� ����
		map.put("col", 0); // ��
		map.put("row", 0); // ��
		map.put("price", 0); // �¼� ���� 
		for(int row = 1; row<=theater_row; row++) { // 1���� ��ü ����� for�� ����
			for(int col = 1; col<=theater_col; col++) { // 1���� ��ü ������ for�� ����
				map.replace("state", Integer.parseInt(state[(row-1)*theater_col-1+col])); // ��� ���� �ش��ϴ� �¼� ���� ����
				map.replace("col", col); // ��
				map.replace("row", row); // ��
				if(Integer.parseInt(state[(row-1)*theater_col-1+col])==3)  //�Ϲݼ��̸� 9000��
					map.replace("price", 9000);
				else if(Integer.parseInt(state[(row-1)*theater_col-1+col])==4) // �����̾����̸� 11000��
					map.replace("price", 11000);
				else if(Integer.parseInt(state[(row-1)*theater_col-1+col])==5) // Ŀ�ü��̸� 15000��
					map.replace("price", 15000);
				else map.replace("price", 0); // ������ 0��
					
				int cnt = dao.modify_theater_seat(map); // �¼� ���� UPDATE
				
				if(cnt >= 1)
					model.addAttribute("cnt", 1);
			}
		}
			
	}

	// �󿵰� ���� ó��
	@Override
	public void hostTheaterDel(HttpServletRequest req, Model model) {
		int theater_index = Integer.parseInt(req.getParameter("theater_index")); // ������ �󿵰� index
		
		int deleteCnt = dao.hostTheaterSeatDel(theater_index);  // theater_index�� Forign key�� �ɷ��ִ� �ڽ� ���̺��� seat���̺��� ���� ���� ó��
		
		if(deleteCnt >= 1) { // �󿵰��� �ش��ϴ� �¼� ������ ���� �����ϸ�
			int cnt = dao.hostTheaterDel(theater_index); // �󿵰� ���� ����
			model.addAttribute("cnt", cnt);
		}
		
	}
	
	// ������ ��ȸ
	@Override
	public void hostScheduleList(HttpServletRequest req, Model model) {
		SimpleDateFormat date = new SimpleDateFormat ( "yy-MM-dd", Locale.KOREA ); // ��¥�� yy-MM-dd���·� ��ȯ�ϴ� simpleDateFormat ����
		Date sysdate = new Date(); // ���� �ð�
		String today = date.format ( sysdate ); // Date���·� ������ ����ð��� ���ڿ� yy-MM-dd ���·� ��ȯ  
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("theater_index", 1); // ó�� ��ȸ�� �󿵰� 1�� ��ȸ�ϱ����� ����
		map.put("startDate", today+"-00:00"); // yy-MM-dd-00:00���� ����
		map.put("endDate", today+"-23:59"); // yy-MM-dd-23:59���� ����
		ArrayList<Theater_scheduleVO> vos1 = dao.hostScheduleList(map); // ���ó�¥ ������ ��ȸ
		model.addAttribute("day0", today);

		// 1�� �� ������ ��ȸ
		sysdate.setDate(sysdate.getDate()+1); // ���糯¥ + 1��
		today = date.format ( sysdate ); //+1�� �� ��¥�� ���ڿ� yy-MM-dd������ ��ȯ
		map.replace("startDate", today+"-00:00"); // yy-MM-dd-00:00���� ����
		map.replace("endDate", today+"-23:59"); // yy-MM-dd-23:59���� ����
		model.addAttribute("day1", today);
		ArrayList<Theater_scheduleVO> vos2 = dao.hostScheduleList(map); // ���ó�¥ ������ ��ȸ

		// 2�� �� ������ ��ȸ
		sysdate.setDate(sysdate.getDate()+1);
		today = date.format ( sysdate ); //+1��
		map.replace("startDate", today+"-00:00");
		map.replace("endDate", today+"-23:59");
		model.addAttribute("day2", today);
		ArrayList<Theater_scheduleVO> vos3 = dao.hostScheduleList(map);
		
		// 3�� �� ������ ��ȸ
		sysdate.setDate(sysdate.getDate()+1);
		today = date.format ( sysdate ); //+1��
		map.replace("startDate", today+"-00:00");
		map.replace("endDate", today+"-23:59");
		model.addAttribute("day3", today);
		ArrayList<Theater_scheduleVO> vos4 = dao.hostScheduleList(map);
		
		// 4�� �� ������ ��ȸ
		sysdate.setDate(sysdate.getDate()+1);
		today = date.format ( sysdate ); //+1��
		map.replace("startDate", today+"-00:00");
		map.replace("endDate", today+"-23:59");
		model.addAttribute("day4", today);
		ArrayList<Theater_scheduleVO> vos5 = dao.hostScheduleList(map);
		
		ArrayList<TheaterVO> theaterVOS = dao.getTheaterAllList(); // ��� �󿵰� ����
		
		ArrayList<MovieVO> movieVOS = dao.getMovieList(); // ��� ��ȭ ����
		
		model.addAttribute("vos1", vos1);
		model.addAttribute("vos2", vos2);
		model.addAttribute("vos3", vos3);
		model.addAttribute("vos4", vos4);
		model.addAttribute("vos5", vos5);
		
		model.addAttribute("movieVOS", movieVOS);
		model.addAttribute("theaterVOS", theaterVOS);

		
	}
	
	// ������ �˻� ��� ��ȸ
	@Override
	public void hostScheduleSearch(HttpServletRequest req, Model model) {
		String selDate = req.getParameter("date"); // ������ ��¥
		int theater_index = Integer.parseInt(req.getParameter("theater_index")); // ������ �󿵰�
		SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // yyyy-MM-dd HH:mm:ss ���·� ��ȯ�ϴ� SimpleDateFormat ����
		SimpleDateFormat dateForm2 = new SimpleDateFormat("yy-MM-dd"); //yyyy-MM-dd ���·� ��ȯ�ϴ� SimpleDateFormat ����
		try {
			Date sysdate = dateForm.parse("20"+selDate+" 00:00:00"); // ���ڿ� yy-MM-dd�� Date���� 20yy-MM-dd 00:00:00���� ��ȯ�Ͽ� ������ ����
			
			String date = dateForm2.format(sysdate); // Date���¸� ���ڿ� yy-MM-dd������ ��ȯ
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("theater_index", theater_index); // ������ �󿵰�
			map.put("startDate", date+"-00:00"); // ������ ��¥�� yy-MM-dd-00:00
			map.put("endDate", date+"-23:59"); // ������ ��¥�� yy-MM-dd-23:59
			ArrayList<Theater_scheduleVO> vos1 = dao.hostScheduleList(map); // ���� ��¥ ������ ��ȸ
			model.addAttribute("day0", date);
			
			// 1�� �� ������ ��ȸ
			sysdate.setDate(sysdate.getDate()+1); // ������ ��¥ +1��
			date = dateForm2.format ( sysdate ); // yy-MM-dd ���·� ��ȯ
			map.replace("startDate", date+"-00:00"); // +1�� ��¥�� yy-MM-dd-00:00
			map.replace("endDate", date+"-23:59"); // +1�� ��¥�� yy-MM-dd- 23:59
			model.addAttribute("day1", date);
			ArrayList<Theater_scheduleVO> vos2 = dao.hostScheduleList(map);  // +1�� ��¥ ������ ��ȸ
			
			// 2�� �� ������ ��ȸ
			sysdate.setDate(sysdate.getDate()+1); // ������ ��¥ +1�� +1��
			date = dateForm2.format ( sysdate ); // yy-MM-dd ���·� ��ȯ
			map.replace("startDate", date+"-00:00"); // +1+1�� ��¥�� yy-MM-dd-00:00
			map.replace("endDate", date+"-23:59"); // +1+1�� ��¥�� yy-MM-dd- 23:59
			model.addAttribute("day2", date);
			ArrayList<Theater_scheduleVO> vos3 = dao.hostScheduleList(map); // +1�� +1�� ��¥ ������ ��ȸ
						
			// 3�� �� ������ ��ȸ
			sysdate.setDate(sysdate.getDate()+1); // ������ ��¥ +1�� +1�� +1��
			date = dateForm2.format ( sysdate ); // yy-MM-dd ���·� ��ȯ
			map.replace("startDate", date+"-00:00"); // +1+1+1�� ��¥�� yy-MM-dd-00:00
			map.replace("endDate", date+"-23:59"); // +1+1+1�� ��¥�� yy-MM-dd- 23:59
			model.addAttribute("day3", date);
			ArrayList<Theater_scheduleVO> vos4 = dao.hostScheduleList(map); // +1�� +1�� +1�� ��¥ ������ ��ȸ
			
			// 4�� �� ������ ��ȸ
			sysdate.setDate(sysdate.getDate()+1); // ������ ��¥ +1�� +1�� +1�� +1��
			date = dateForm2.format ( sysdate ); // yy-MM-dd ���·� ��ȯ
			map.replace("startDate", date+"-00:00"); // +1+1�� ��¥�� yy-MM-dd-00:00
			map.replace("endDate", date+"-23:59"); // +1+1�� ��¥�� yy-MM-dd- 23:59
			model.addAttribute("day4", date);
			ArrayList<Theater_scheduleVO> vos5 = dao.hostScheduleList(map); // +1�� +1�� +1�� +1�� ��¥ ������ ��ȸ
			
			ArrayList<TheaterVO> theaterVOS = dao.getTheaterAllList(); // ��� �󿵰� ����
			
			ArrayList<MovieVO> movieVOS = dao.getMovieList(); // ��� ��ȭ ����
			
			model.addAttribute("date", selDate);
			model.addAttribute("theater_index", theater_index);
			model.addAttribute("vos1", vos1);
			model.addAttribute("vos2", vos2);
			model.addAttribute("vos3", vos3);
			model.addAttribute("vos4", vos4);
			model.addAttribute("vos5", vos5);
			
			model.addAttribute("movieVOS", movieVOS);
			model.addAttribute("theaterVOS", theaterVOS);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

	// �󿵰� �߰� �� ��ȭ ������ �󿵰� ���� ��������
	@Override
	public void hostScheduleAddForm(HttpServletRequest req, Model model) {
		ArrayList<MovieVO> movieVOS = dao.getMovieING(); // ������ ��ȭ ����
		
		ArrayList<TheaterVO> theaterVOS = dao.getTheaterAllList(); // ��� �󿵰� ����
		
		model.addAttribute("movieVOS", movieVOS);
		model.addAttribute("theaterVOS", theaterVOS);
	}

	// �󿵰����� �󿵰� ��ȸ
	@Override
	public void checkPosTheater(HttpServletRequest req, Model model) {
		String schedule_startDate = req.getParameter("schedule_startDate"); // ������ ��¥
		String schedule_startTime = req.getParameter("schedule_startTime"); // ������ �ð�
		
		ArrayList<TheaterVO> theaterVOS = null;
		
		String schedule_start = schedule_startDate +"-"+ schedule_startTime; // ������ ��¥�� �ð�
		
		int cnt = dao.checkPosTheaterCnt(schedule_start); // �� ������ �󿵰�
		if(cnt == 0) { // ���ýð��� ��� �󿵰��� �������� ���ٸ�
			theaterVOS = dao.getTheaterAllList(); // ��� �󿵰� ������ ������
		} else { // ������ �ð��� 1���̻��� �󿵰��� �������� �ִٸ�
			theaterVOS = dao.checkPosTheater(schedule_start); //������ �ð��뿡 �� ������ �󿵰��� ������
		}
		
		ArrayList<MovieVO> movieVOS = dao.getMovieING(); // ������ ��ȭ ����
		
		model.addAttribute("confirm", 1);
		model.addAttribute("movieVOS", movieVOS);
		model.addAttribute("theaterVOS", theaterVOS);
	}
	
	// ������ �߰� ó��
	@Override
	public void hostScheduleAddPro(HttpServletRequest req, Model model) {
		String schedule_startDate = req.getParameter("schedule_startDate"); // ������ ���� ��¥
		String schedule_startTime = req.getParameter("schedule_startTime"); // ������ ���� �ð�
		int movie_index = Integer.parseInt(req.getParameter("movie_index")); // ��ȭ
		int theater_index = Integer.parseInt(req.getParameter("theater_index")); // �󿵰�
		int schedule_MDNstate = 1; // ����, �Ϲ�, �ɾ� state
		
		int time = Integer.parseInt(schedule_startTime.split(":")[0]);
		if(22<=time && time<=23 || 00<=time && time<=03) { // ������ �ð��� �ɾ߽ð��̸�
			schedule_MDNstate = 2; // state = �ɾ�
		} else if(04<=time && time<=10) { // ������ �ð��� ���� �ð��̸�
			schedule_MDNstate = 0; // state = ����
		}
		
		String schedule_start = schedule_startDate +"-"+ schedule_startTime; // ������ ���� ��¥�� �ð�
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("schedule_start", schedule_start); // ������ ���� ��¥�� �ð�
		map.put("movie_index", movie_index); // ��ȭ
		map.put("theater_index", theater_index); // �󿵰�
		map.put("schedule_MDNstate", schedule_MDNstate); // ����, �Ϲ�, �ɾ� state
		int cnt = dao.hostScheduleAddPro(map); // ������ �߰� ó��
		
		TheaterVO theaterVO = dao.hostTheaterDetail(theater_index); // �󿵰��� �� ��� �� ���� ������
		
		// ������ �� ��� �� ���� ������ 2�� for���� ������ 2���迭�� �¼�state ����
		int totalCol = theaterVO.getTheater_col(); // �� ��
		int totalRow = theaterVO.getTheater_row(); // �� ��
		int[][] seatState = new int[totalRow][totalCol]; // �¼��� ���� 2���迭 ũ�⸦ �� ���� �� ������ ����
		int[][] seatPrice = new int[totalRow][totalCol]; // �¼��� ���� 2���迭 ũ�⸦ �� ���� �� ������ ����

		Map<String, Integer> map2 = new HashMap<String, Integer>();
		map2.put("theater_index", theater_index); // �󿵰�
		map2.put("row", 0); // �� �ʱ� ����
		map2.put("col", 0); // �� �ʱ� ����
		
		int empty_seat = 0; // �Ϲݼ�, �����̾���, Ŀ�ü��� ���¼� �ʱ� ����
		for(int row = 1; row<=totalRow; row++) { // 1���� ��ü ����� for�� ����
			for(int col = 1; col<=totalCol; col++) { // 1���� ��ü ������ for�� ����
				map2.replace("row", row); // �� ����
				map2.replace("col", col); // �� ����
				seatState[row-1][col-1] = dao.getTheaterSeatState(map2); //�󿵰�index, ��� ���� �ش��ϴ� �¼� state�� ������ 
				seatPrice[row-1][col-1] = dao.getTheaterSeatPrice(map2); //�󿵰�index, ��� ���� �ش��ϴ� �¼� ���������� ������ 
				
				if(seatState[row-1][col-1] == 3 || seatState[row-1][col-1] == 4 || seatState[row-1][col-1] == 5) { // �¼�state�� �Ϲ�, �����̾�, Ŀ�ü��̸�
					empty_seat += 1; // ���¼� +1
				}
			}
		}
		dao.updateEmpty_seat(empty_seat); // ���¼� ������Ʈ
		
		// 2�� for���� �̿��Ͽ� ������ ��Ʈ ���̺� insert
		map2.put("seatState", 0); // �¼����� �ʱ⼳��
		map2.put("seatPrice", 0); // �¼� ���� �ʱ⼳��
		for(int row = 1; row<=totalRow; row++) { // 1���� ��ü ����� for�� ����
			for(int col = 1; col<=totalCol; col++) { // 1���� ��ü ������ for�� ����
				map2.replace("row", row); // �� ����
				map2.replace("col", col); // �� ����
				map2.replace("seatState", seatState[row-1][col-1]); // ��� ���� �ش��ϴ� �¼� ������ ����
				map2.replace("seatPrice", seatPrice[row-1][col-1]); // ��� ���� �ش��ϴ� �¼� ���� ����
				dao.TheaterScheduleSeatAddPro(map2); // �¼� ���̺� �ش� �󿵰�index�� �¼� ���� �߰� ó��
			}
		}
		model.addAttribute("cnt", cnt);
	}

	// ������ �� ���� ��������
	@Override
	public void hostScheduleDetail(HttpServletRequest req, Model model) {
		int theater_index = Integer.parseInt(req.getParameter("theater_index")); // �󿵰�
		int movie_index = Integer.parseInt(req.getParameter("movie_index")); // ��ȭ
		int theater_schedule_index = Integer.parseInt(req.getParameter("theater_schedule_index")); // ������
		
		TheaterVO theaterVO = dao.hostTheaterDetail(theater_index); // �󿵰� �� ���� ��������
		MovieVO movieVO = dao.hostMovieDetail(movie_index); // ��ȭ �� ���� ��������
		Theater_scheduleVO scheduleVO = dao.hostScheduleDetail(theater_schedule_index); // ������ �� ���� ��������
		
		ArrayList<MovieVO> movieVOS = dao.getMovieING(); // ������ ��ȭ ����
		
		ArrayList<TheaterVO> theaterVOS = dao.getTheaterAllList(); // ��� �󿵰� ����
		
		model.addAttribute("theaterVO", theaterVO);
		model.addAttribute("movieVO", movieVO);
		model.addAttribute("scheduleVO", scheduleVO);
		model.addAttribute("movieVOS", movieVOS);
		model.addAttribute("theaterVOS", theaterVOS);
	}

	// ������ ���� ó��
	@Override
	public void hostScheduleModPro(HttpServletRequest req, Model model) {
		String startDate = req.getParameter("startDate"); // ~���� �ٲ� ������ ��¥
		String startTime = req.getParameter("startTime"); // ~���� �ٲ� ������ �ð�
		int theater_index = Integer.parseInt(req.getParameter("theater_index")); // �󿵰�
		int movie_index = Integer.parseInt(req.getParameter("movie_index")); // ��ȭ
		int theater_schedule_index = Integer.parseInt(req.getParameter("theater_schedule_index")); // ������
		
		String schedule_startTime = startDate+"-"+startTime; // ~���� �ٲ� ������ ��¥�� �ð�
		
		int cnt = 0;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("schedule_startTime", schedule_startTime); // ~���� �ٲ� ������ ��¥�� �ð�
		map.put("theater_schedule_index", theater_schedule_index); // ������
		map.put("theater_index", theater_index); // �󿵰�
		int sche_chkCnt = dao.chkCnt(map); // �����Ϸ��� �ð��� �ٸ� �������� �ִ��� Ȯ��
		
		if(sche_chkCnt == 0) { // ������ �ð����� ������ ������ ���� �ϸ�
			map.put("movie_index", movie_index); // ��ȭ
			
			int schedule_MDNstate = 1; // ���� �Ϲ� �ɾ� state
			
			int time = Integer.parseInt(startTime.split(":")[0]); // ���۽ð� ��:�п��� �� ������ ����
			if(22<=time && time<=23 || 00<=time && time<=03) { // ������ �ð��� �ɾ߽ð��̸�
				schedule_MDNstate = 2; // state = �ɾ�
			} else if(04<=time && time<=10) { // ������ �ð��� �����ð��̸�
				schedule_MDNstate = 0; // state = ����
			}
			map.put("schedule_MDNstate", schedule_MDNstate);
			
			cnt = dao.updateSchedule(map);//������ ���� ó��
		}
		model.addAttribute("cnt", cnt);
	}

	// ������ ���� ó��
	@Override
	public void hostScheduleDelPro(HttpServletRequest req, Model model) {
		int theater_schedule_index = Integer.parseInt(req.getParameter("theater_schedule_index")); // ������ ������
		int cnt = dao.hostScheduleDelPro(theater_schedule_index); // ������ ����ó��
		
		model.addAttribute("cnt", cnt); 
	}
	
	// ���� ���
	@Override
	public void hostMovieEmp(HttpServletRequest req, Model model) {
		int cnt = dao.hostMovieEmpCnt(); // ���� �� Ȯ���ϱ�
		
		if(cnt > 0) { // ���� ����� �����ϸ�
			ArrayList<Member> vos = dao.hostMovieEmpList(); // ���� ����� ArrayList<Member>�� ����
			model.addAttribute("vos", vos);
		}
		model.addAttribute("cnt", cnt);
	}
	
	// ���� ��� ���̵� Ȯ��
	@Override
	public void hostMovieEmpChkMemberId(HttpServletRequest req, Model model) {
		String member_id = req.getParameter("member_id"); // ����� ���̵�
		
		int idCnt = dao.hostMovieEmpChkMemberId(member_id); // ����� ���̵� �ش��ϴ� ������ �ִ��� Ȯ�� 
		
		if(idCnt != 0) { // ����� ���̵� ������ �ִٸ�
			Member vo = dao.hostMovieEmpInfo(member_id); 	// ���̵�� ����� ���� ���� ��������
			vo.setMember_birth(vo.getMember_birth().substring(2,8));
			model.addAttribute("vo", vo);
		}
		model.addAttribute("member_id", member_id);
		model.addAttribute("idCnt", idCnt);
	}

	// ���� ��� ó��
	@Override
	public void hostMovieEmpAddPro(HttpServletRequest req, Model model) {
		String member_id = req.getParameter("member_id"); // ����� ���� ���̵�
		String employee_jumin2 = req.getParameter("employee_jumin2"); // ����� ���� �ֹε�Ϲ�ȣ ���ڸ�
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", member_id);
		map.put("employee_jumin2", employee_jumin2);
		
		int cnt = dao.memberChangeState(member_id); // ����� ���� ���̵��� state�� ��ȭ �������� ����
		
		if(cnt == 1) { // ����� ���� ���̵��� state�� ��ȭ �������� ���� ������
			int insertCnt = dao.insertEmp(map); // ������Ͽ� �߰� ó��
			if(insertCnt==1) {
				cnt = 1;
			}else {
				cnt = 0;
			}
		}
		model.addAttribute("cnt", cnt);
	}

	// ���� �ذ�
	@Override
	public void hostMovieEmpDel(HttpServletRequest req, Model model) {
		String member_id = req.getParameter("member_id"); // �ذ��� ���� ���̵�
		
		int point = dao.getMemberPoint(member_id); // ȸ������ ���ư��� �� ��������Ʈ ��� Ȯ��
		int member_step = 9; // member step �ʱ� ����
		
		if(point <= 15000 ) { // �Ϲ�ȸ������ ����
			member_step = 9;
		}else if(point <= 30000) { // VIPȸ������ ����
			member_step = 10;
		}else if(point <= 45000) { // VVIPȸ������ ����
			member_step = 11;
		}else { //SVIP ȸ������ ����
			member_step = 12;
		}
		
		int cnt = dao.hostMovieEmpDel(member_id); // ���� ��Ͽ��� ���� ó��
		
		if(cnt > 0) { // ���� ��Ͽ��� ���� ó�� �����ϸ�
			Member vo = new Member();
			vo.setMember_id(member_id);
			vo.setMember_step(member_step);
			
			cnt = dao.updateMemberStep(vo); // �ذ��� ���� ���̵��� step�� ����
		}
		model.addAttribute("cnt", cnt);
	}

	// ���� ��
	@Override
	public void hostTheaterScheduleDetail(HttpServletRequest req, Model model) {
		TheaterVO vo = null; // �󿵰� ��
		ArrayList<Theater_seatVO> seat_vos = null; // �¼� ��
		
		int theater_index = Integer.parseInt(req.getParameter("theater_index")); // ������ �󿵰�
		int theater_schedule_index = Integer.parseInt(req.getParameter("theater_schedule_index")); // ������ ������
		
		ArrayList<Integer> state = new ArrayList<Integer>(); // �¼� ����
		
		vo = dao.hostTheaterDetail(theater_index); // �󿵰� �� ������ ����
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("theater_index", theater_index); // �󿵰�
		map.put("theater_schedule_index", theater_schedule_index); // ������
		seat_vos = dao.hostTheaterScheduleSeatDetail(map); // �����ٿ� �ش��ϴ� �¼� �� ������ ������
		
		for(Theater_seatVO seat_vo : seat_vos) {  // �¼� ���������� �¼����¸� state�� �迭���·� ����
			state.add(seat_vo.getSeat_state());
		}
		
		model.addAttribute("vo", vo);
		model.addAttribute("seat_vos", seat_vos);
		model.addAttribute("state", state);
		
	}

	// ���� ��
	@Override
	public MovieResViewVO hostMovieResView(HttpServletRequest req, Model model) {
		MovieResViewVO viewVO = new MovieResViewVO(); // ������ ��������
		
		TheaterVO vo = null; // �󿵰� ��
		ArrayList<Theater_seatVO> seat_vos = null; // �¼� ��
		
		int theater_index = Integer.parseInt(req.getParameter("theater_index")); // ������ �󿵰�
		int theater_schedule_index = Integer.parseInt(req.getParameter("theater_schedule_index")); // ������ ������
		
		ArrayList<Integer> state = new ArrayList<Integer>(); // �¼� ����
		
		vo = dao.hostTheaterDetail(theater_index); // �󿵰� �� ������ ����
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("theater_index", theater_index); // �󿵰�
		map.put("theater_schedule_index", theater_schedule_index); // ������
		seat_vos = dao.hostTheaterScheduleSeatDetail(map); // �����ٿ� �ش��ϴ� �¼� �� ������ ������
		
		for(Theater_seatVO seat_vo : seat_vos) { // �¼� ���������� �¼����¸� state�� �迭���·� ����
			state.add(seat_vo.getSeat_state());
		}
		
		viewVO.setTotalRow(vo.getTheater_row()); // ��ü ��
		viewVO.setTotalCol(vo.getTheater_col()); // ��ü ��
		viewVO.setState(state); // �¼� ����(�迭 ����)
		
		return viewVO;
	}

	// �帣�� ��ȭ ������ ��
	@Override
	public void movieJanreCountChart(HttpServletRequest req, Model model) {
		Map<String , Object> map = new HashMap<String,Object>();
		String[] janre = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}; 
		//mapper���� �ҷ��� kind�� value�� �ٰ��̱⶧���� vo������ List������ �޾��ش�.
		List<HostMovieChartVO> voList = dao.getMovieCountChart(); 
		
		//vo������Ÿ�� i �� List�����͵��� �ѰǾ� ���ͼ� map�� ��´�.
		//(map�� key���� String�̱⶧���� int���� kind�� String���� ����ȯ �Ѵ�.
		for (HostMovieChartVO i : voList) {
			map.put(i.getKind() , i.getValue());
		}
		
		// Ű ���� ���� �帣�� value�� 0���� �ʱ�ȭ �ش�.
		for (String s : janre) {
			int chk = 0;
			for(Entry<String, Object> m : map.entrySet()) {
				if(s.equals(m.getKey())) {
					chk = 1;
				}
			}
			if(chk == 0) {
				map.put(s, 0);
			}
		}
		
		model.addAttribute("movieJanreCountChart",map);
		
		map.forEach((k,v)->{
			log.debug(k + " : " + v);
		});
	}

	// ���� ���ɺ� ����
	@Override
	public void movieAgeChart(HttpServletRequest req, Model model) {
		Map<String , Object> map = new HashMap<String,Object>();
		String[] age = {"0", "12", "15", "19"};
		//mapper���� �ҷ��� kind�� sum�� �ٰ��̱⶧���� vo������ List������ �޾��ش�.
		List<hostTChartVO> voList = dao.movieAgeChart(); 
		
		//vo������Ÿ�� i �� List�����͵��� �ѰǾ� ���ͼ� map�� ����ش�.
		//(map�� key���� String�̱⶧���� int���� kind�� String���� ����ȯ ���ش�.
		for (hostTChartVO i : voList) {
			map.put(Integer.toString(i.getKind()) , i.getSum());
		}
		
		// Ű ���� ���� ���ѿ����� value�� 0���� �ʱ�ȭ �ش�.
		for (String s : age) {
			int chk = 0;
			for(Entry<String, Object> m : map.entrySet()) {
				if(s.equals(m.getKey())) {
					chk = 1;
				}
			}
			if(chk == 0) {
				map.put(s, 0);
			}
		}
		
		
		model.addAttribute("movieAgeChart",map);
		
		map.forEach((k,v)->{
			log.debug(k + " : " + v);
		});
	}

	// ������ ���� ��
	@Override
	public void movieSexCountChart(HttpServletRequest req, Model model) {
		Map<String , Object> map = new HashMap<String,Object>();
		String[] sex = {"��", "��"};
		//mapper���� �ҷ��� kind�� value�� �ٰ��̱⶧���� vo������ List������ �޾��ش�.
		List<HostMovieChartVO> voList = dao.movieSexCountChart(); 
		
		//vo������Ÿ�� i �� List�����͵��� �ѰǾ� ���ͼ� map�� ����ش�.
		//(map�� key���� String�̱⶧���� int���� kind�� String���� ����ȯ ���ش�.
		for (HostMovieChartVO i : voList) {
			map.put(i.getKind() , i.getValue());
		}
		
		// Ű ���� ���� ������ value�� 0���� �ʱ�ȭ �ش�.
		for (String s : sex) {
			int chk = 0;
			for(Entry<String, Object> m : map.entrySet()) {
				if(s.equals(m.getKey())) {
					chk = 1;
				}
			}
			if(chk == 0) {
				map.put(s, 0);
			}
		}
		
		model.addAttribute("movieSexCountChart",map);
		
		map.forEach((k,v)->{
			log.debug(k + " : " + v);
		});
	}

	
	// ���� Ŭ����
	// ����Ŭ���� ����Ʈ
	private static List<WordVO> wordVos;
	
	// �ܾ� ���¼� �м��� ó���ϴ� �޼��� (��ȭ �����Ͻ� ���� ����)
	@Override
	public void wordAnalyzer(HttpServletRequest req, Model model) {
		int movie_index = Integer.parseInt(req.getParameter("movie_index"));
		StringBuilder sb = new StringBuilder(req.getParameter("review_content"));
		wordExtractAndAnalyze(sb.toString(), movie_index);
	}

	// ���¼� �м��� ����� �����ͺ��̽��� �����ϴ� ���μ���
	@Override
	public void wordExtractAndAnalyze(String text, int movie_index) {
		log.debug("WordCloud analyze");
		new Runnable() {
			public void run() {

				List<WordVO> wordMap = KoreanParser.getWordsMap(text);
				if(wordMap.isEmpty())return;
				Timestamp time = new Timestamp(System.currentTimeMillis());
				for(WordVO dto : wordMap) {
					
					// ������ �ִ� �ܾ��� ��� ī��Ʈ ������Ʈ
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("word", dto.getWord());
					map.put("movie_index", movie_index);
					if(dao.checkWordCloud(map) == 1) {
						dto.setUpdate_date(time);
						dto.setMovie_index(movie_index);
						dao.updateWordCloud(dto);
						// ������ ���� �ܾ��� ��� �ܾ�� ī��Ʈ �߰�	
					} else {
						dto.setUpdate_date(time);
						dto.setReg_date(time);
						dto.setMovie_index(movie_index);
						dao.addWordCloud(dto);
					}
				}
				// ���� Ŭ���� ���� refresh ����
				setWordList();
				log.debug("WordCloud �м� ����");
			}
		}.run();
	}

	// ����Ŭ���� �ܾ ������
	public synchronized void setWordList() {
		log.debug("Word Cloud word set request");
		wordVos = dao.getWordCloudModel();
	}
	
	// ��ȭ ���� ���� Ŭ����
	@Override
	public void movieWordcloud(HttpServletRequest req, Model model) {
		int movie_index = Integer.parseInt(req.getParameter("movie_index")); // get������� ��ȭindex�� ������
		int countOfWords = 30; // 30���� ����Ŭ���� �ܾ ������
		if(req.getParameter("count")!=null) {
			countOfWords = Integer.parseInt(req.getParameter("count"));
		}
		
		String type = "";
		type = req.getParameter("type");
		
		Map<String, Object> map = new HashMap<>();
		map.put("countOfWords", countOfWords);
		map.put("movie_index", movie_index);
		map.put("type", type);
		
		List<WordVO> wordList = null;
		wordList = dao.searchWordcloud(map);

		model.addAttribute("type", type);
		model.addAttribute("movie_index", movie_index);
		model.addAttribute("wordList", wordList);
		model.addAttribute("listSize", wordList.size());
		model.addAttribute("countOfWords", countOfWords);
	}

	// ���� ����ϱ� �� ��� ȸ�� ���� �ҷ�����
	@Override
	public void getMemberList(HttpServletRequest req, Model model) {
		if(dao.getMemberCnt()>0) { // ��������ϱ� �� ȸ���� 1���̶� �����ϴ��� üũ
			ArrayList<Member> vos = dao.getMemberList(); // ���� ����ϱ� �� ��� ȸ�� ���� �ҷ�����
			model.addAttribute("vos", vos);
			model.addAttribute("cnt", 1);
		}else {
			model.addAttribute("cnt", 0);
		}
	}
}

