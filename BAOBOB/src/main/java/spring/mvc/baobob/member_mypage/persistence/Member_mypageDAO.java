package spring.mvc.baobob.member_mypage.persistence;

import java.util.ArrayList;
import java.util.Map;

import spring.mvc.baobob.vo.BoardVO;

public interface Member_mypageDAO {
	
	//1:1���� �۰��� ���ϱ�
	public int getArticleCnt();
	
	//1:1���Ǳ� ��� ��ȸ
	public ArrayList<BoardVO> getArticleList(Map<String, Integer> map);

}
