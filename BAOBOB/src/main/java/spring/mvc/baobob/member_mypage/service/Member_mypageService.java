package spring.mvc.baobob.member_mypage.service;

import javax.servlet.http.HttpServletRequest;


import org.springframework.ui.Model;

public interface Member_mypageService {
	
	//1:1���� ����Ʈ
	public void memQuestionList(HttpServletRequest req, Model model);

}
