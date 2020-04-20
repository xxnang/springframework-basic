package myspring.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import myspring.user.service.UserService;
import myspring.user.vo.UserVO;

/**
 * 이 컨트롤러는 RESTful - json 방식을 사용하는 방법을 이용한 것임.
 * 코드가 비교적 훨씬 간단함
 * 
 * @author mina
 *
 */

@RestController
//@RequestMapping("/users")
public class RestUserController {
	
	@Autowired
	UserService userService;
	
	// 사용자 목록 조회
	// GET
	@GetMapping("/users")
	public List<UserVO> userList() {
		return userService.getUserList();
	}
	
	// 사용자 상세 정보 
	@GetMapping("/users/{id}")
	public UserVO userDetail(@PathVariable String id) {
		return userService.getUser(id);
	}
	
	// 사용자 등록 - POST
	@PostMapping("/users")
	public Boolean userInsert(@RequestBody UserVO user) {
		if(user != null) {
			userService.insertUser(user);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}
	
	// 데이터 수정하기
	@PutMapping("/users")
	public Boolean userUpdate(@RequestBody UserVO user) {
		if(user != null) {
			userService.updateUser(user);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}
	
	// 사용자 삭제하기
	@RequestMapping(value="/users/{id}", method=RequestMethod.DELETE)
	public Boolean userDelete(@PathVariable String id) {
		if(id != null) {
			userService.deleteUser(id);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

}
