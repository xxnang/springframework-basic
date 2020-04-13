package myspring.user.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import myspring.user.vo.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext.xml")
public class MyBatisTest {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	SqlSession sqlSession;
	
	// DataSource 테스트
	@Test @Ignore
	public void con() {
		try {
			Connection con = dataSource.getConnection();
			System.out.println(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// SqlSessionFactory 테스트
	@Test @Ignore
	public void mybatis_spring() {
		System.out.println(sqlSessionFactory.getClass().getName());
	}
	
	// SqlSessionTemplate 테스트
	@Test @Ignore
	public void mybatis_spring2() {
		System.out.println(sqlSession.getClass().getName());
	}
	
	// 본격 데이터 가져오기 테스트
	@Test @Ignore
	public void sql() {
		//Sqlsession의 selectOne() 사용
		UserVO user = sqlSession.selectOne("userNS.selectUserById", "gildong"); // (mapper의 id, 쿼리 변수)
		System.out.println(user);
		
		UserVO insertUser = new UserVO("java", "김자바", "여", "제주");
		int count = sqlSession.insert("insertUser", insertUser);
		System.out.println("등록된 건수 : " + count);
	}
	
	// 특정 user update + 전체 목록 가져오는 테스트 메소드 
	@Test
	public void sql2() {
		// update
		UserVO updateUser = new UserVO("java", "이액트", "여", "원주");
		int count = sqlSession.update("userNS.updateUser", updateUser);
		System.out.println("업데이트 건수 : " + count);
		
		// all select
		List<UserVO> selectList = sqlSession.selectList("userNS.selectUserList");
		for (UserVO userVO : selectList) {
			System.out.println(userVO);
		}
	}
}
