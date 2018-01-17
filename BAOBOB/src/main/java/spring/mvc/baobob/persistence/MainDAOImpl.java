package spring.mvc.baobob.persistence;

import java.util.ArrayList;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.quartz.SimpleThreadPoolTaskExecutor;
import org.springframework.stereotype.Repository;

import spring.mvc.baobob.service.EmailHandler;
import spring.mvc.baobob.vo.FaqVO;
import spring.mvc.baobob.vo.Member;

@Repository
public class MainDAOImpl implements MainDAO {

	@Autowired
	SqlSession sqlSession;

	// ���̵� �ߺ� Ȯ��
	@Override
	public int confirmId(String id) {
		System.out.println("confirmId ����");

		MainDAO dao = sqlSession.getMapper(MainDAO.class);
		int cnt = dao.confirmId(id);

		System.out.println("confirmId ����");
		return cnt;
	}

	// ����ó��
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

	// ���� ���� ����
	@Override
	public int sendEmail(String email, String id, String key) {
		int cnt = 0;
		try {
			EmailHandler sendMail = new EmailHandler(mailSender);
			if (id != null) {
				sendMail.setSubject("BAOBOB ���� ���� ����");
				sendMail.setText(new StringBuffer("��ũ�� ���� ������ �Ϸ��ϼ���.<br><br>")
						.append("<a href='http://192.168.0.158/baobob/mainConfirmEmail?key=").append(key)
						.append("&id=" + id + "'>����<a>").toString());
			} else {
				sendMail.setSubject("BAOBOB ��й�ȣ ã�� ���� ����");
				sendMail.setText(new StringBuffer("<html>��ũ�� ���� ������ �Ϸ��ϼ���.<br><br>")
						.append("<a href='http://192.168.0.158/baobob/mainConfirmPwdEmail?key=").append(key)
						.append("&email=" + email + "'>����</a></html>").toString());
			}
			sendMail.setFrom("admin@baobob.com", "BAOBOB");
			sendMail.setTo(email);
			sendMail.send();

			cnt = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cnt;
	}

	// ����Ű ����
	public int memberKeyInsert(Map<String, String> map) {
		System.out.println("memberKeyInsert ����");
		MainDAO mapper = sqlSession.getMapper(MainDAO.class);
		int cnt = mapper.memberKeyInsert(map);
		System.out.println("memberKeyInsert ����");
		return cnt;
	}

	// �̸��� ���� ���� => id
	public String memberEmailId(String member_email) {
		System.out.println("memberEmailId ����");
		MainDAO mapper = sqlSession.getMapper(MainDAO.class);
		String member_id = mapper.memberEmailId(member_email);
		System.out.println("memberEmailId ����");
		return member_id;
	}

	// ����Ű ���� ���� Ȯ��
	public int memberKeySelect(Map<String, String> map) {
		System.out.println("memberKeySelect ����");
		MainDAO mapper = sqlSession.getMapper(MainDAO.class);
		int cnt = mapper.memberKeySelect(map);
		System.out.println("memberKeySelect ����");
		return cnt;
	}

	// ����Ű ����
	public int memberKeyDelete(String member_key) {
		System.out.println("memberKeyDelete ����");
		MainDAO mapper = sqlSession.getMapper(MainDAO.class);
		int cnt = mapper.memberKeyDelete(member_key);
		System.out.println("memberKeyDelete ����");
		return cnt;
	}

	// ȸ�� ��� ����
	@Override
	public int memberStepUpdate(Map<String, String> map) {
		System.out.println("memberStepUpdate ����");
		MainDAO mapper = sqlSession.getMapper(MainDAO.class);
		int cnt = mapper.memberStepUpdate(map);
		System.out.println("memberStepUpdate ����");
		return cnt;
	}

	// ���̵�, ��й�ȣ ���� ���� => �����ϸ� step
	@Override
	public String confirmIdPwd(Map<String, String> map) {
		System.out.println("confirmIdPwd ����");
		MainDAO mapper = sqlSession.getMapper(MainDAO.class);
		String step = mapper.confirmIdPwd(map);
		System.out.println("confirmIdPwd ����");
		return step;
	}

	// ��й�ȣ ã�� �̸��� ����
	public String memberConfirmPwdKey(Map<String, String> map) {
		MainDAO mapper = sqlSession.getMapper(MainDAO.class);
		String member_pwd = mapper.memberConfirmPwdKey(map);
		return member_pwd;
	};

	// ���̵� ���� ����
	@Override
	public int getMemberCheck(String userId) {
		MainDAO mapper = sqlSession.getMapper(MainDAO.class);
		int cnt = mapper.getMemberCheck(userId);
		return cnt;
	}

	//������
	public ArrayList<FaqVO> getHelper(Map<String, Integer> map) {
		MainDAO mapper = sqlSession.getMapper(MainDAO.class);
		ArrayList<FaqVO> list = mapper.getHelper(map);
		return list;
	}
}
