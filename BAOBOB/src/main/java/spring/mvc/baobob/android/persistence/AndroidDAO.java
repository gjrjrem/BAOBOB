package spring.mvc.baobob.android.persistence;

import java.util.ArrayList;
import java.util.Map;

import spring.mvc.baobob.guest_movie.persistence.Guest_movieDAO;
import spring.mvc.baobob.vo.Android;
import spring.mvc.baobob.vo.BoardVO;
import spring.mvc.baobob.vo.Member;
import spring.mvc.baobob.vo.MovieVO;
import spring.mvc.baobob.vo.Restaurant_scheduleVO;
import spring.mvc.baobob.vo.TableVO;

public interface AndroidDAO {

	// ��ȭ �̿� �Ǽ�
	public int getUseMovieCnt(String id);

	// �Ĵ� �̿� �Ǽ�
	public int getUseRestaurantCnt(String id);

	// ������ �̿� �Ǽ�
	public int getUseParkingCnt(String id);

	// �ֱ� 10�ϰ��� ���� ����
	public ArrayList<BoardVO> getBoardList(String id);

	// ��ȭ ���� ����
	public ArrayList<Android> getMemberMovieTicketing(String id);

	// �Ĵ� �̿� ����
	public ArrayList<Android> getUseRestaurantList(String id);

	// ���� �̿� ����
	public ArrayList<Android> getMemberParking(String id);

	// ȸ�� ���� ����
	public int anMemberUpdate(Member m);

	// ��ȭ ����
	public MovieVO androidMovieInfo(String movie_title);

	// ��ȭ ���
	public ArrayList<Android> getMovieList();

	// �Ĵ� ���
	public ArrayList<Android> getRestaurantList();

	// ����) �ش� ��¥ ���ϴ� ��ȭ
	public ArrayList<Android> getMovieSchedule(String day);

	// ȸ���� ���� ����Ʈ
	public int getMemberPoint(String id);

	//��ȭ ����) seat ����
	public int updateSeatState(Map<String,Object> map);
	
	// ��ȭ ����) ��ȭ �������� ����
	public int movieCountUpdate(Map<String, Object> map);

	// �Ĵ� �޴� ) MainList����
	public ArrayList<Android> getRestaurantMenu(int restaurant_index);
	
	// �Ĵ� �޴� ) MainPage����
	public ArrayList<Android> getRestaurantTitleMenu(String restaurant_name);
	
	//�Ĵ� �¼� ����
	public ArrayList<Android> getRestaurantSeatState(int restIndex);
	
	//�Ĵ� ����� �¼�
	public ArrayList<Android> getRestaurantTicketSeat(Map<String, Object> map);
	
	//�Ĵ� ���� 1) ������ ���
	public int setRestaurantSchedule(Restaurant_scheduleVO rest);
	
	//�Ĵ� ���� 2) ������ ���
	public int setRestaurantTable(Map<String, Object> map);

	//�Ĵ� ���� 3) �����丮
	public int setRestaurantMainHistory(String member_id);

	//�Ĵ� ���� 4) �Ĵ� �����丮
	public int setRestaurantHistory(int restTableIndex);
}
