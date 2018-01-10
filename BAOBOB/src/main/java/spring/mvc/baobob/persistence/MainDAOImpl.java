package spring.mvc.baobob.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.baobob.vo.Member;

@Repository
public class MainDAOImpl implements MainDAO {

	@Autowired
	SqlSession sqlSession;

	//���̵� �ߺ� Ȯ��
	@Override
	public int confirmId(String id) {
		System.out.println("confirmId ����");
		MainDAO dao = sqlSession.getMapper(MainDAO.class);
		int cnt = dao.confirmId(id);
		System.out.println("confirmId ����");
		return cnt;
	}

	//����ó��
	@Override
	public int memberInsert(Member m) {
		System.out.println("memberInsert ����");
		MainDAO dao = sqlSession.getMapper(MainDAO.class);
		int cnt = dao.memberInsert(m);
		System.out.println("memberInsert ����");
		return cnt;
	}

}
