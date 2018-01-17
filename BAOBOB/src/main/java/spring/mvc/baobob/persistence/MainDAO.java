package spring.mvc.baobob.persistence;

import java.util.ArrayList;
import java.util.Map;

import spring.mvc.baobob.vo.FaqVO;
import spring.mvc.baobob.vo.Member;

public interface MainDAO {

	//���̵� �ߺ� Ȯ��
	public int confirmId(String id);
	
	//���� ó��
	public int memberInsert(Member m);
	
	//����Ű ����
	public int memberKeyInsert(Map<String, String> map);

	//�̸��� ���� ���� => id
	public String memberEmailId(String member_email);

	//����Ű ���� ���� Ȯ��
	public int memberKeySelect(Map<String, String> map);
	
	//����Ű ����
	public int memberKeyDelete(String member_key);
	
	//�̸��� ����
	public int sendEmail(String email, String id, String key);
	
	//��й�ȣ ã�� �̸��� ����
	public String memberConfirmPwdKey(Map<String, String> map);
	
	//ȸ�� ��� ����
	public int memberStepUpdate(Map<String, String> map);
	
	//���̵�, ��й�ȣ ���� ���� => �����ϸ� step
	public String confirmIdPwd(Map<String, String> map);
	
	//���̵� ���� ����
	public int getMemberCheck(String userId);
	
	//������
	public ArrayList<FaqVO> getHelper(Map<String, Integer> map);
}
