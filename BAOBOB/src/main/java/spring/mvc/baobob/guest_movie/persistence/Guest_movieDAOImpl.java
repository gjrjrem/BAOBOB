package spring.mvc.baobob.guest_movie.persistence;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.baobob.host_movie.persistence.Host_movieDAO;
import spring.mvc.baobob.vo.Member;
import spring.mvc.baobob.vo.MovieVO;
import spring.mvc.baobob.vo.ReviewVO;
import spring.mvc.baobob.vo.TheaterVO;
import spring.mvc.baobob.vo.Theater_scheduleVO;
import spring.mvc.baobob.vo.Theater_seatVO;

@Repository
public class Guest_movieDAOImpl implements Guest_movieDAO{

	@Autowired
	public SqlSession sqlSession;

	//��ü ��ȭ����
	@Override
	public int getMovieCnt() {
		int cnt = 0;

		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		cnt = gmdao.getMovieCnt();
		
		return cnt;
	}
	
	//movie_state�� ���� ��ȭ����
	@Override
	public int getMovieStateCnt(Map<String, Object> map) {
		int cnt = 0;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		cnt = gmdao.getMovieStateCnt(map);
		
		return cnt;
	}

	//�⺻��ȭ����
	@Override
	public ArrayList<MovieVO> getAllMovies(Map<String,Object> map) {
		ArrayList<MovieVO> movies = null;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		movies = gmdao.getAllMovies(map);
		
		return movies;
	}
	
	//movie_state�� ���� ��ȭ����Ʈ
	@Override
	public ArrayList<MovieVO> getMovieStateMovies(Map<String, Object> map) {
		ArrayList<MovieVO> movies = null;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		movies = gmdao.getMovieStateMovies(map);
		
		return movies;
	}

	//��ȭ������
	@Override
	public MovieVO getMovie(int movie_index) {
		MovieVO movie = null;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		movie = gmdao.getMovie(movie_index);
		
		return movie;
	}
	
	//movie_index�� ���� ���� ����
	@Override
	public int getMovieReviewCnt(int movie_index) {
		int cnt = 0;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		cnt = gmdao.getMovieReviewCnt(movie_index);
		
		return cnt;
	}

	//movie_index�� ���� ���� ����Ʈ
	@Override
	public ArrayList<ReviewVO> getAllMovieReviews(Map<String, Object> map) {
		ArrayList<ReviewVO> reviews = null;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		reviews = gmdao.getAllMovieReviews(map);
		
		return reviews;
	}

	//�Ѿ��̵�� �ѿ�ȭ�� �� ���丸 ���� ���� - ������� Ȯ��
	@Override
	public int movieReviewCheck(Map<String, Object> map) {
		int cnt =0;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		cnt = gmdao.movieReviewCheck(map);
		
		return cnt;
	}
	
	//review �߰�
	@Override
	public int insertReview(ReviewVO review) {
		int cnt = 0;

		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		cnt = gmdao.insertReview(review);
		
		return cnt;
	}

	//movieReview �߰�
	@Override
	public int insertMovieReview(Map<String,Object> map) {
		int cnt = 0;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		cnt = gmdao.insertMovieReview(map);
		
		return cnt;
	}

	//���ƿ� ���� ��� ��
	@Override
	public String movieLike(int movie_index) {
		String likeCnt = "0";
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		likeCnt = gmdao.movieLike(movie_index);
		
		return likeCnt;
	}

	//�������
	@Override
	public int updateReview(ReviewVO review) {
		int cnt = 0;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		cnt = gmdao.updateReview(review);
		
		return cnt;
	}

	// movieReview ���� 
	@Override
	public int deleteMovieReview(Map<String, Object> map) {
		int cnt = 0;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		cnt = gmdao.deleteMovieReview(map);
		return cnt;
	}

	//���� ����
	@Override
	public int deleteReview(int review_index) {
		int cnt = 0;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		cnt = gmdao.deleteReview(review_index);
		
		return cnt;
	}

	//���� - ��ü ��ȭ ����Ʈ(�������� �� ����)
	@Override
	public ArrayList<MovieVO> getAllReserveMovies() {
		ArrayList<MovieVO> movies = null;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		movies = gmdao.getAllReserveMovies();
		
		return movies;
	}

	//���ŵǴ� ��¥
	@Override
	public int getDateCnt(Map<String,Object> map) {
		int cnt = 0;

		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		cnt = gmdao.getDateCnt(map);
		
		return cnt;
	}

	//���� - ��ȭ �Ǵ� ��¥, �󿵰� ����
	@Override
	public ArrayList<Theater_scheduleVO> getAllReserveSchedules(Map<String,Object> map) {
		ArrayList<Theater_scheduleVO> schedules = null;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		schedules = gmdao.getAllReserveSchedules(map);
		
		return schedules;
	}

	//�󿵰� ����
	@Override
	public int theaterCnt() {
		int cnt = 0;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		cnt = gmdao.theaterCnt();
		
		return cnt;
	}
	
	//�� �󿵰� ������ ���¼� ���� ���ϱ�
	@Override
	public int theaterSeats(int theater_index) {
		int cnt = 0;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		cnt = gmdao.theaterSeats(theater_index);
		
		return cnt;
	}

	//������ ����
	@Override
	public Theater_scheduleVO getSchedule(int theater_schedule_index) {
		Theater_scheduleVO schedule = null;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		schedule = gmdao.getSchedule(theater_schedule_index);
		
		return schedule;
	}

	// �󿵰� ��
	@Override
	public TheaterVO theaterDetail(int theater_index) {
		TheaterVO theater = null;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		theater = gmdao.theaterDetail(theater_index);
		
		return theater;
	}
	
	// �󿵰� �� �¼� ����
	@Override
	public ArrayList<Theater_seatVO> theaterSeatDetail(Map<String,Integer> map) {
		ArrayList<Theater_seatVO> seats = null;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		seats = gmdao.theaterSeatDetail(map);
		
		return seats;
	}

	//���õ� �Ѱ��� �¼� ����
	@Override
	public Theater_seatVO seatInfo(int seat_index) {
		Theater_seatVO seat = null;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		seat = gmdao.seatInfo(seat_index);
		
		return seat;
	}
	


	

	

	
}
