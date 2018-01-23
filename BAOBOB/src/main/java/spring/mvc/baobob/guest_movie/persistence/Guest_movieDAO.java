package spring.mvc.baobob.guest_movie.persistence;

import java.util.ArrayList;
import java.util.Map;

import spring.mvc.baobob.vo.Member;
import spring.mvc.baobob.vo.MovieVO;
import spring.mvc.baobob.vo.ReviewVO;
import spring.mvc.baobob.vo.TheaterVO;
import spring.mvc.baobob.vo.Theater_scheduleVO;
import spring.mvc.baobob.vo.Theater_seatVO;

public  interface Guest_movieDAO {

	//��ü ��ȭ����
	public int getMovieCnt();
	
	//movie_state�� ���� ��ȭ����
	public int getMovieStateCnt(Map<String,Object> map);
	
	//�⺻��ȭ��������Ʈ
	public ArrayList<MovieVO> getAllMovies(Map<String,Object> map);
	
	//movie_state�� ���� ��ȭ����Ʈ
	public ArrayList<MovieVO> getMovieStateMovies(Map<String,Object> map);
	
	//��ȭ ������
	public MovieVO getMovie(int movie_index);
	
	//movie_index�� ���� ���� ����
	public int getMovieReviewCnt(int movie_index);
	
	//movie_index�� ���� ���� ����Ʈ
	public ArrayList<ReviewVO> getAllMovieReviews(Map<String,Object> map);
	
	//�Ѿ��̵�� �ѿ�ȭ�� �� ���丸 ���� ���� - ������� Ȯ��
	public int movieReviewCheck(Map<String,Object> map);
	
	//���� �ۼ�
	public int insertReview(ReviewVO review);
	
	//movieReview �߰�
	public int insertMovieReview(Map<String,Object> map);
	
	//���ƿ� ���� ��� ��
	public String movieLike(int movie_index);
	
	//���� ����
	public int updateReview(ReviewVO review);
	
	//movieReview ����
	public int deleteMovieReview(Map<String,Object> map);
	
	//���� ����
	public int deleteReview(int review_index);
	
	//���� - ��ü ��ȭ ����Ʈ(�������� �� ����)
	public ArrayList<MovieVO> getAllReserveMovies();
	
	//���� �Ǵ� ��¥ ����
	public int getDateCnt(Map<String,Object> map);
	
	//���� - ��ȭ �Ǵ� ��¥, �󿵰� ����
	public ArrayList<Theater_scheduleVO> getAllReserveSchedules(Map<String,Object> map);
	
	//�󿵰� ����
	public int theaterCnt();
	
	//�� �󿵰� ���� ���¼� �������ϱ�
	public int theaterSeats(int theater_index);
	
	//������ ����
	public Theater_scheduleVO getSchedule(int theater_schedule_index);
	
	// �󿵰� ��
	public TheaterVO theaterDetail(int theater_index);
	
	// �󿵰� �� �¼� ����
	public ArrayList<Theater_seatVO> theaterSeatDetail(Map<String,Integer> map);
	
	//���õ� �Ѱ� �¼��� ����
	public Theater_seatVO seatInfo(int seat_index);
	
	//���� - ��ȭ ����
	public ArrayList<String> mainMovieRank();
	
	//���� - �� ���� ��ȭ ����
	public int mainMovieTheaterCnt();
	
	//���� - �� ���� ��ȭ
	public ArrayList<String> mainMovieTheater(Map<String, Object> map);
	
	//member ����
	public Member getMemberInfo(String member_id);
}