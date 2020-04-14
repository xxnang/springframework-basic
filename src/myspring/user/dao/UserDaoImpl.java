package myspring.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myspring.user.vo.UserVO;
//@Repository("userDao") - UserDaoImplMapper.java를 사용하기 위해 얘를 죽임
public class UserDaoImpl implements UserDao {

	// SqlSession에 의존
	// @Autowired - UserDaoImplMapper.java를 사용하기 위해 얘를 죽임
    private SqlSession session;
	
	public void setSqlSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public UserVO read(String id) {
		UserVO user  = session.selectOne("userNS.selectUserById", id);
		return user;
	}

	public List<UserVO> readAll() {
		List<UserVO> userList = session.selectList("userNS.selectUserList");
		return userList;
	}
	
	public void insert(UserVO user) {
		session.update("userNS.insertUser", user);
		System.out.println("등록된 Record UserId=" + user.getUserId() + " Name=" + user.getName());
	}

	@Override
	public void update(UserVO user) {
		session.update("userNS.updateUser", user);
	}

	@Override
	public void delete(String id) {
		session.delete("userNS.deleteUser", id);
		System.out.println("등록된 Record with ID = " + id ); 
	}




	

}
