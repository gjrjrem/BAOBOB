package spring.mvc.baobob.guest_movie.persistence;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.baobob.host_movie.persistence.Host_movieDAO;
import spring.mvc.baobob.vo.Member;
import spring.mvc.baobob.vo.MovieFinderVO;
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

	//���� - ��ȭ ����
	public ArrayList<MovieVO> mainMovieRank(){
		Guest_movieDAO mapper = sqlSession.getMapper(Guest_movieDAO.class);
		ArrayList<MovieVO> list = mapper.mainMovieRank();
		return list;
	}

	//���� - �� ���� ��ȭ ����
	public int mainMovieTheaterCnt() {
		Guest_movieDAO mapper = sqlSession.getMapper(Guest_movieDAO.class);
		int cnt = mapper.mainMovieTheaterCnt();
		return cnt;
	}
	
	//���� - ���� ��ȭ
	@Override
	public ArrayList<MovieVO> mainMovieTheater(Map<String, Object> map) {
		Guest_movieDAO mapper = sqlSession.getMapper(Guest_movieDAO.class);
		ArrayList<MovieVO> list = mapper.mainMovieTheater(map);
		return list;
	}
	
	//�������
	@Override
	public Member getMemberInfo(String member_id) {
		Member member = null;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		member = gmdao.getMemberInfo(member_id);
		
		return member;
	}
//����------------
	//1. Insert history_tbl
	@Override
	public int insertHistory(String member_id) {
		int cnt = 0;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		cnt = gmdao.insertHistory(member_id);
		
		return cnt;
	}
	
	//2. Insert movie_history_tbl
	@Override
	public int insertMovieHistory(Map<String, Object> map) {
		int cnt = 0;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		cnt = gmdao.insertMovieHistory(map);
		
		return cnt;
	}

	//3. Update theater_seat_tbl �ش� seat_index�� seat_state=6 ����(�¼� ���� ���༮���� ����)
	@Override
	public int updateSeatState(Map<String,Object> map) {
		int cnt = 0;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		cnt = gmdao.updateSeatState(map);
		
		return cnt;
	}

	//4. Update theater_schedule_tbl schedule_empty_seat= -totalCnt���ֱ�(���ڸ��� ����)
	@Override
	public int updateEmptySeat(Map<String, Object> map) {
		int cnt = 0;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		cnt = gmdao.updateEmptySeat(map);
		
		return cnt;
	}

	//5. Update movie_tbl  movie_count + totalCnt���ֱ�(��ȭ�������� ����)
	@Override
	public int updateMovieCount(Map<String, Object> map) {
		int cnt = 0;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		cnt = gmdao.updateMovieCount(map);
		
		return cnt;
	}

	//6. Update member_tbl member_point, member_cumpoint (������ ����)
	@Override
	public int updateIncreasePoint(Map<String, Object> map) {
		int cnt = 0;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		cnt = gmdao.updateIncreasePoint(map);
		
		return cnt;
	}

	//7. Update member_tbl member_point (����Ʈ ��������� ����)
	@Override
	public int updateDecreasePoint(Map<String, Object> map) {
		int cnt = 0;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		cnt = gmdao.updateDecreasePoint(map);
		
		return cnt;
	}

	//8. SELECT member_tble���� member_cumPointȮ��
	@Override
	public int getMemberCumPoint(String member_id) {
		int member_cumPoint = 0;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		member_cumPoint = gmdao.getMemberCumPoint(member_id);
		
		return member_cumPoint;
	}

	//9. UPDATE member_step 8�� Ȯ���ؼ� if������ üũ�ؼ� ���!!
	@Override
	public int updateMemberStep(Map<String, Object> map) {
		int cnt = 0;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		cnt = gmdao.updateMemberStep(map);
		
		return cnt;
	}
// ������

	//�������δ� �˻� ��� ����
	@Override
	public int movieFinderResultCnt(MovieFinderVO movieFinderInfo) {
		int cnt = 0;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		cnt = gmdao.movieFinderResultCnt(movieFinderInfo);
		
		return cnt;
	}
	
	//�������δ� �˻� ���
	@Override
	public ArrayList<MovieVO> movieFinderResult(MovieFinderVO movieFinderInfo) {
		ArrayList<MovieVO> movies;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		movies = gmdao.movieFinderResult(movieFinderInfo);
		
		return movies;
	}

	//��ü ��ȭ ���� ��
	@Override
	public int allMovieCount() {
		int sum = 0;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		sum = gmdao.allMovieCount();
		
		return sum;
	}

	//�ش� ��ȭ ���� ��
	@Override
	public int indexMovieCount(int movie_index) {
		int cnt = 0;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		cnt = gmdao.indexMovieCount(movie_index);
		
		return cnt;
	}

	
}
