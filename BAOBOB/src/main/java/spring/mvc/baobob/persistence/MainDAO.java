package spring.mvc.baobob.persistence;

import spring.mvc.baobob.vo.Member;

public interface MainDAO {

	//���̵� �ߺ� Ȯ��
	public int confirmId(String id);
	
	//���� ó��
	public int memberInsert(Member m);
}
