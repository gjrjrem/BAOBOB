package spring.mvc.baobob.guest_movie.persistence;

import java.util.ArrayList;
import java.util.Map;

import spring.mvc.baobob.vo.Member;
import spring.mvc.baobob.vo.MovieFinderVO;
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
	public ArrayList<MovieVO> mainMovieRank();
	
	//���� - �� ���� ��ȭ ����
	public int mainMovieTheaterCnt();
	
	//���� - �� ���� ��ȭ
	public ArrayList<MovieVO> mainMovieTheater(Map<String, Object> map);
	
	//member ����
	public Member getMemberInfo(String member_id);

//��������-----------------
	//1. ���� ���̺� �߰�
	public int insertHistory(String member_id);
	
	//2. ��ȭ �������̺� �߰�
	public int insertMovieHistory(Map<String,Object> map);
	
	//3. �¼� ���̺��� seat_state ����
	public int updateSeatState(Map<String,Object> map);
	
	//4. ���������̺��� emtpy_seat ����(������ ��ŭ)
	public int updateEmptySeat(Map<String,Object> map);
	
	//5. Update movie_tbl  movie_count + totalCnt���ֱ�(��ȭ�������� ����)
	public int updateMovieCount(Map<String,Object> map);
	
	//6. Update member_tbl member_point, member_cumpoint (������ ����)
	public int updateIncreasePoint(Map<String,Object> map);
	
	//7. Update member_tbl member_point (����Ʈ ��������� ����)
	public int updateDecreasePoint(Map<String,Object> map);
	
	//8. SELECT member_tble���� member_cumPointȮ��
	public int getMemberCumPoint(String member_id);
	
	//9. UPDATE member_step(Service���� if�������� ���� ������ ����)
	public int updateMemberStep(Map<String,Object> map);
//���� ��--------------
	
	//�������δ� ��� ����
	public int movieFinderResultCnt(MovieFinderVO movieFinderInfo);
	
	//�������δ� ���
	public ArrayList<MovieVO> movieFinderResult(MovieFinderVO movieFinderInfo);
	
	//��ü ��ȭ ���� ��
	public int allMovieCount();
	
	//�ش� ��ȭ ���� �� 
	public int indexMovieCount(int movie_index);
	
	//��ȭ-�������� wishList���� üũ�ؼ� ��Ʈ�� ����
	public int checkWishCnt(Map<String,Object> map);
}