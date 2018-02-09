package spring.mvc.baobob.android.persistence;

import java.util.ArrayList;
import java.util.Map;

import spring.mvc.baobob.vo.Android;
import spring.mvc.baobob.vo.BoardVO;
import spring.mvc.baobob.vo.Member;
import spring.mvc.baobob.vo.MovieVO;

public interface AndroidDAO {

	//��ȭ �̿� �Ǽ�
	public int getUseMovieCnt(String id);
	
	//�Ĵ� �̿� �Ǽ�
	public int getUseRestaurantCnt(String id);
	
	//������ �̿� �Ǽ�
	public int getUseParkingCnt(String id);
	
	//�ֱ� 10�ϰ��� ���� ����
	public ArrayList<BoardVO> getBoardList(String id);
	
	//��ȭ ���� ����
	public ArrayList<Android> getMemberMovieTicketing(String id);


	//�Ĵ� �̿� ����
	public ArrayList<Android> getUseRestaurantList(String id);

	
	//���� �̿� ����
	public ArrayList<Android> getMemberParking(String id);
	
	//ȸ�� ���� ����
	public int anMemberUpdate(Member m);
	
	//��ȭ ����
	public MovieVO androidMovieInfo(String movie_title);

	//��ȭ ���
	public ArrayList<Android> getMovieList();
	
	//�Ĵ� ���
	public ArrayList<Android> getRestaurantList();
	
	//����) �ش� ��¥ ���ϴ� ��ȭ
	public ArrayList<Android> getMovieSchedule(String day);
	
	//ȸ���� ���� ����Ʈ
	public int getMemberPoint(String id);
	
	// ��ȭ ����) ��ȭ �������� ����
	public int movieCountUpdate(Map<String, Object> map);
}
