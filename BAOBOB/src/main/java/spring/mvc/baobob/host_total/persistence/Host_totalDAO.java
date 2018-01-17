package spring.mvc.baobob.host_total.persistence;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;

import spring.mvc.baobob.vo.Member;

public interface Host_totalDAO {
	
	//ȸ���� ���ϱ�
	public int getMemCnt();
	
	//ȸ�� ��� ���ϱ�
	public ArrayList<Member> getMemList(Map<String, Integer> map);
	
	//ȸ�� �߰�
	public int insertMember(Member vo);
	
	// ���̵� �ߺ� Ȯ��
	public int confirmId(String id);
	
	//ȸ������ ��������
	public Member getMemberInfo(String strId);
	
	//ȸ������ ���� ó��
	public int hostTMemModifyPro(Member vo);
	
	//ȸ������ ó��
	public int hostTMemDelPro(String strId);
	
	//��ȭ�� ��� �Ѿ� ���ϱ�
	public int getMovieSale();
	
	//��ȭ�� ���������(íƮ)
	@MapKey("kind")
	public Map<String,Integer> getMovieChart();
	
	
	
	
}
