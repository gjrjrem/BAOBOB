package spring.mvc.baobob.persistence;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

	@Autowired
	private JavaMailSender mailSender;
	
	//���� ���� ����
	@Override
	public int sendEmail(String email, String id, String key) {
		int cnt = 0;
		try {
			SimpleMailMessage msg= new SimpleMailMessage();
			msg.setFrom("admin@baobob.com");
			msg.setTo(email);
			msg.setSubject("BAOBOB ���� ���� ����");
			msg.setText(new StringBuffer("��ũ�� ���� ������ �Ϸ��ϼ���. <br><br>")
					.append("<a href='http://localhost:8087/baobob/mainConfirmEmail?key=" + key + "")
					.append("&id=" + id + "'>����<a>").toString());
			
			mailSender.send(msg);
			cnt = 1;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}


	//����Ű ����
	public int memberKeyInsert(Map<String, String> map) {
		System.out.println("memberKeyInsert ����");
		MainDAO mapper = sqlSession.getMapper(MainDAO.class);
		int cnt = mapper.memberKeyInsert(map);
		System.out.println("memberKeyInsert ����");
		return cnt;
	}

	//����Ű ���� ���� Ȯ��
	public int memberKeySelect(Map<String, String> map){
		System.out.println("memberKeySelect ����");
		MainDAO mapper = sqlSession.getMapper(MainDAO.class);
		int cnt = mapper.memberKeySelect(map);
		System.out.println("memberKeySelect ����");
		return cnt;
	}
	
	//����Ű ����
	public int memberKeyDelete(String member_id){
		System.out.println("memberKeyDelete ����");
		MainDAO mapper = sqlSession.getMapper(MainDAO.class);
		int cnt = mapper.memberKeyDelete(member_id);
		System.out.println("memberKeyDelete ����");
		return cnt;
	}
	
	
	//ȸ�� ��� ����
	@Override
	public int memberStepUpdate(Map<String, String> map) {
		System.out.println("memberStepUpdate ����");
		MainDAO mapper = sqlSession.getMapper(MainDAO.class);
		int cnt = mapper.memberStepUpdate(map);
		System.out.println("memberStepUpdate ����");
		return cnt;
	}

}
