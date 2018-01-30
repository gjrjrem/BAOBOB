package spring.mvc.baobob.android.persistence;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.baobob.vo.Android;
import spring.mvc.baobob.vo.BoardVO;
import spring.mvc.baobob.vo.Member;

@Repository
public class AndroidDAOImpl implements AndroidDAO{

	@Autowired
	SqlSession sqlSession;

	//��ȭ �̿� �Ǽ�
	@Override
	public int getUseMovieCnt(String id) {
		AndroidDAO mapper = sqlSession.getMapper(AndroidDAO.class);
		int cnt = mapper.getUseMovieCnt(id);
		return cnt;
	}

	//�Ĵ� �̿� �Ǽ�
	@Override
	public int getUseRestaurantCnt(String id) {
		AndroidDAO mapper = sqlSession.getMapper(AndroidDAO.class);
		int cnt = mapper.getUseRestaurantCnt(id);
		return cnt;
	}

	//������ �̿� �Ǽ�
	@Override
	public int getUseParkingCnt(String id) {
		AndroidDAO mapper = sqlSession.getMapper(AndroidDAO.class);
		int cnt = mapper.getUseParkingCnt(id);
		return cnt;
	}
	
	//�ֱ� 10�ϰ��� ���� ����
	@Override
	public ArrayList<BoardVO> getBoardList(String id) {
		AndroidDAO mapper = sqlSession.getMapper(AndroidDAO.class);
		ArrayList<BoardVO> list = mapper.getBoardList(id);
		return list;
	}

	//��ȭ ���� ����
	@Override
	public ArrayList<Android> getMemberMovieTicketing(String id) {
		AndroidDAO mapper = sqlSession.getMapper(AndroidDAO.class);
		ArrayList<Android> list = mapper.getMemberMovieTicketing(id);;
		return list;
	}

	//���� �̿� ����
	@Override
	public ArrayList<Android> getMemberParking(String id) {
		AndroidDAO mapper = sqlSession.getMapper(AndroidDAO.class);
		ArrayList<Android> list = mapper.getMemberParking(id);
		return list;
	}

	//ȸ�� ���� ����
	@Override
	public int anMemberUpdate(Member m) {
		AndroidDAO mapper = sqlSession.getMapper(AndroidDAO.class);
		int cnt = mapper.anMemberUpdate(m);
		return cnt;
	}


}
