package myspring.user.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import myspring.user.dao.mapper.StudentMapper;
import myspring.user.service.UserService;
import myspring.user.vo.StudentVO;
import myspring.user.vo.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext.xml")
public class MyBatisTest {
	
	// Log4j 사용을 위하여 추가함
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	SqlSession sqlSession;
	
	// Service 구현 클래스 주입
	@Autowired
	UserService userService;
	
	// Student 테이블 DB 가져오기 위해 Mapper 인터페이스 직접 주입
	@Autowired 
	StudentMapper studentMapper;
	
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
		UserVO user = sqlSession.selectOne("userNS.selectUserById", "gildong"); // (mapper�� id, 荑쇰━ 蹂���)
		System.out.println(user);
		
		UserVO insertUser = new UserVO("java", "김자바", "여", "제주");
		int count = sqlSession.insert("insertUser", insertUser);
		System.out.println("등록된 건수 : " + count);
	}
	
	// 특정 user update + 전체 목록 가져오는 테스트 메소드
	@Test @Ignore
	public void sql2() {
		// update
		UserVO updateUser = new UserVO("java", "이액트", "여", "원주");
		int count = sqlSession.update("userNS.updateUser", updateUser);
//		System.out.println("업데이트 건수 : " + count);
		
		// Log 찍기
		logger.info(">>>>> update count : " + count);
		
		
		// all select
		List<UserVO> selectList = sqlSession.selectList("userNS.selectUserList");
		for (UserVO userVO : selectList) {
//			System.out.println(userVO);
			
			// Log 찍기
			logger.debug(">>>>> " + userVO);
		}
	}
	
	// UserDaoImpl & UserService 사용 테스트 메소드
	@Test //@Ignore
	public void service() {
		// UserService -> UserDao -> SqlSession -> SqlSessionFactory -> DataSource
		
		UserVO user = userService.getUser("gildong");
		System.out.println("service method : " + user);
		
	}
	
	// Student DB 데이터 가져오기위한 테스트 메소드
	@Test
	public void studentMapper() {
		List<StudentVO> studentVO = studentMapper.selectStudentDeptById();
		for (StudentVO studentVO2 : studentVO) {
			System.out.println("studentMapper method : " + studentVO);
		}
	}
}
