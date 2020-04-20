package myspring.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		return new ModelAndView("userList", "userList", userList); // (jsp, jsp에서 쓸 이름, 객체)
	}
	
	// 사용자 상세 정보 조회
	@RequestMapping("/userDetail.do")
	// @RequestParam == 서블릿 방법의 request.getParameter() (QueryString ?key=value& 형식으로 전달되는 데이터는 @RequestParam 어노테이션 사용
	public String userDetail(@RequestParam("id") String userid, Model model) { // ("id")는 jsp에서 쓰이는 변수명과 같아야 함 / () 안쓰려면 여기 파라미터랑 jsp 변수명을 같게 해줘야 함
		
		UserVO user = userService.getUser(userid); // 서비스 통해 결과 받고
		model.addAttribute("user", user); // 그 객체 jsp로 보내줌 / "user"는 jsp에서 쓰는 이름
		
		return "userDetail";
	}
	
	// 사용자 등록 form 불러오기
	// session 사용으로 변경해보기
	@RequestMapping("/userInsertForm.do")
	public String insertUserForm(HttpSession session) { // 타세션 인자 추가
		List<String> genderList = new ArrayList<String>();
		genderList.add("남");
		genderList.add("여");
		
		// session 객체에 genderList 객체를 저장
		session.setAttribute("genderList", genderList); // ("키값", value값)

		List<String> cityList = new ArrayList<String>();
		cityList.add("서울");
		cityList.add("부산");
		cityList.add("대구");
		cityList.add("제주");
		
		// session 객체에 cityList 객체를 저장
		session.setAttribute("cityList", cityList); // ("키값", value값)

// session 쓰면 얘네 없어도 됨
//		Map<String, List<String>> map = new HashMap<>();
//		map.put("genderList", genderList);
//		map.put("cityList", cityList);

		return "userInsert"; // 뷰만 넘기면 되므로 String return 타입으로!
	}
	
	// 사용자 등록 DB 처리
	// POST 방식
	@RequestMapping(value="/userInsert.do", method=RequestMethod.POST) // default가 GET이기 때문에 POST이면 value를 써줘야 함
	public String userInsert(@ModelAttribute UserVO user) { // 어노테이션 사용
		System.out.println(">>> UserVO : " + user);
		userService.insertUser(user);
		return "redirect:/userList.do"; // 리다이렉트를 통해 등록 후 사용자 목록 페이지로 이동
	}

	// 사용자 삭제 처리
	// userDelete.do/gildong
	// url에 슬래시 base로 append 하는 방식일 땐 @PathVariable 사용
	@RequestMapping("/userDelete.do/{id}")
	public String userDelete(@PathVariable("id") String userid) {
		
		userService.deleteUser(userid);
		
		return "redirect:/userList.do";
	}
	
	// 사용자 정보 업데이트 form 불러오기
	@RequestMapping("/userUpdateForm.do")
	public ModelAndView userUpdateForm(@RequestParam String id) {
		UserVO user = userService.getUser(id);
		List<String> genderList = new ArrayList<String>();
		genderList.add("남");
		genderList.add("여");
		List<String> cityList = new ArrayList<String>();
		cityList.add("서울");
		cityList.add("부산");
		cityList.add("대구");
		cityList.add("제주");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("genderList", genderList);
		map.put("cityList", cityList);
		map.put("user", user);
		return new ModelAndView("userUpdate", "map", map);
	}

	// 사용자 정보 업데이트
	@RequestMapping(value="/userUpdate.do", method=RequestMethod.POST)
	public String userUpdate(@ModelAttribute UserVO user) {
		userService.updateUser(user);
		return "redirect:/userList.do";
	}
}
