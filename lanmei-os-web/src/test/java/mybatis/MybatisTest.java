package mybatis;

import com.lanmei.user.dao.mapper.OsUserMapper;
import com.lanmei.user.dao.model.OsUser;
import org.apache.ibatis.session.SqlSession;


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
