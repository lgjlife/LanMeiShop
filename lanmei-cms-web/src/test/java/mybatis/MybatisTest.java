package mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.lanmei.user.dao.mapper.OsUserMapper;
import org.lanmei.user.dao.model.OsUser;

public class MybatisTest extends GetSqlSession{

	//@Test
	public void TestSelectAll() {
		
		SqlSession sqlsession = getSqlSession();
		
		try {
			OsUserMapper userMapper = sqlsession.getMapper(OsUserMapper.class);
			OsUser user = userMapper.selectById(1L);
			
			System.out.println(user.getPhoneNum());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
