package spring.mvc.baobob.host_movie.persistence;

import java.util.ArrayList;
import java.util.Map;

import spring.mvc.baobob.vo.MovieVO;
import spring.mvc.baobob.vo.TheaterVO;
import spring.mvc.baobob.vo.Theater_seatVO;

public interface Host_movieDAO {
	
	// ��ȭ ���� ���ϱ�
	public int getMovieCnt();
	
	// ��ȭ ��ü ��� ��ȸ(������ ��)
	public ArrayList<MovieVO> getMovieList(Map<String, Integer> map);
	
	// ��ȭ �߰� ó��
	public int hostMovieAddPro(MovieVO vo);
	
	// ��ȭ ���� ó��
	public int hostMovieDel(int movie_index);
	
	// ��ȭ ��
	public MovieVO hostMovieDetail(int movie_index);
	
	// ��ȭ ���� ó��
	public int hostMovieModPro(MovieVO vo);
	
	// �󿵰� ���� ����
	public int theater_index_check(int theater_index);
	
	// �󿵰� �߰��ϱ�
	public int insert_theater(Map<String, Object> map);
	
	// �󿵰� �¼��� state �ֱ�
	public int insert_theater_seat(Map<String, Object> map);
	
	// �󿵰� ���� ����
	public int getTheaterCnt();
	
	// �󿵰� ��� ��ȸ
	public ArrayList<TheaterVO> getTheaterList(Map<String, Integer> map);
	
	// �󿵰� ��
	public TheaterVO hostTheaterDetail(int theater_index);
	
	// �󿵰� �� �¼� ����
	public ArrayList<Theater_seatVO> hostTheaterSeatDetail(int theater_index);
	
	// �󿵰� �¼� ���� ó��
	public int modify_theater_seat(Map<String, Object> map);
	
	// �󿵰� ���� ó��
	public int hostTheaterDel(int theater_index);
	
	// �󿵰� �¼� ���� ó��
	public int hostTheaterSeatDel(int theater_index);
	
	
}
