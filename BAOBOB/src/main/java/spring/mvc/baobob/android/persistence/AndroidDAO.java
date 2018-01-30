package spring.mvc.baobob.android.persistence;

import java.util.ArrayList;

import spring.mvc.baobob.vo.Android;
import spring.mvc.baobob.vo.BoardVO;
import spring.mvc.baobob.vo.Member;

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
	
	//���� �̿� ����
	public ArrayList<Android> getMemberParking(String id);
	
	//ȸ�� ���� ����
	public int anMemberUpdate(Member m);
}
