package spring.mvc.baobob.persistence;

import java.util.Map;

import spring.mvc.baobob.vo.Member;

public interface MainDAO {

	//���̵� �ߺ� Ȯ��
	public int confirmId(String id);
	
	//���� ó��
	public int memberInsert(Member m);
	
	//����Ű ����
	public int memberKeyInsert(Map<String, String> map);

	//����Ű ���� ���� Ȯ��
	public int memberKeySelect(Map<String, String> map);
	
	//����Ű ����
	public int memberKeyDelete(String member_id);
	
	//�̸��� ����
	public int sendEmail(String email, String id, String key);
	
	//ȸ�� ��� ����
	public int memberStepUpdate(Map<String, String> map);
	
	//���̵�, ��й�ȣ ���� ���� => �����ϸ� step
	public String confirmIdPwd(Map<String, String> map);
}
