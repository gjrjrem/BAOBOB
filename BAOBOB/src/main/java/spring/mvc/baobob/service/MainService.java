package spring.mvc.baobob.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public interface MainService {

	//���̵� �ߺ� Ȯ��
	public void confirmId(HttpServletRequest req, Model model);
	
	//���� ó��
	public void joinPro(HttpServletRequest req, Model model);
}
