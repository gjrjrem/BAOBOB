package spring.mvc.baobob.member_mypage.persistence;

import java.util.ArrayList;
import java.util.Map;

import spring.mvc.baobob.vo.BoardVO;
import spring.mvc.baobob.vo.Member;

public interface Member_mypageDAO {
	
	//1:1���� �۰��� ���ϱ�
	public int getArticleCnt();
	
	//1:1���Ǳ� ��� ��ȸ
	public ArrayList<BoardVO> getArticleList(Map<String, Integer> map);
	
	//1:1���� �������� ��������
	public BoardVO getArticle(int num);
	
	//��ȸ�� ����
	public void addReadCnt(int num);
	
	//��й�ȣ Ȯ��
	public int pwdCheck(Map<String,Object> map);
	
	//1:1���� ����ó��
	public int updateQuestion(BoardVO dto);
	
	//������� ���
	public int getMaxNum();
	
	//�亯���� ���
	public void updateReply(BoardVO dto);
	
	//1:1���� �ۼ�
	public int insertQuestion(BoardVO dto);
	
	//����� �����ϴ��� ����
	public int checkReply(BoardVO dto);
	
	//����� ���� ���
	public void updateRef_step(BoardVO dto);
	
	//1:1���� ����
	public int deleteQuestion(int num);
	
	//�ߺ�Ȯ�� üũ
	public int idCheck(String strId);
		
	//üũ
	public int memPCheck(Map<String, Object> map);
	
	//ȸ������ ��������
	public Member getMemberInfo(String strId);
	
	//ȸ������ ���� ó��
	public int updateMember(Member vo);
	

}
