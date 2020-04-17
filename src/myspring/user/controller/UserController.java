package myspring.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import myspring.user.service.UserService;
import myspring.user.vo.UserVO;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// 사용자 목록 조회
	@RequestMapping("/userList.do")
	public ModelAndView userList() {
		List<UserVO> userList = userService.getUserList();
		return new ModelAndView("userList.jsp", "userList", userList); // (jsp, jsp에서 쓸 이름, 객체)
	}
	
	// 사용자 상세 정보 조회
	@RequestMapping("/userDetail.do")
	// @RequestParam == 서블릿 방법의 request.getParameter()
	public String userDetail(@RequestParam("id") String userid, Model model) { // ("id")는 jsp에서 쓰이는 변수명과 같아야 함 / () 안쓰려면 여기 파라미터랑 jsp 변수명을 같게 해줘야 함
		
		UserVO user = userService.getUser(userid); // 서비스 통해 결과 받고
		model.addAttribute("user", user); // 그 객체 jsp로 보내줌 / "user"는 jsp에서 쓰는 이름
		
		return "userDetail.jsp";
	}

}
