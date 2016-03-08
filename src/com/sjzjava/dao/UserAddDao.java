package com.sjzjava.dao;

import org.apache.ibatis.session.SqlSession;

import com.sjzjava.dto.User;
import com.sjzjava.util.MybatisConnect;

public class UserAddDao extends MybatisConnect {
	
	// 创建能执行映射文件中sql的sqlSession
	private SqlSession session;
	// 映射sql的标识字符串
	private String statement;
	private String statementParemet = "com.sjzjava.dao.UserAddAction.";

	//查找最大用户ID
	public Integer selectMaxUserId() {

		// 数据库连接相关
		session = dbMybatisConnect();

		// 映射sql的标识字符串
		statement = statementParemet + "userMaxId";

		// 更新用户信息
		Integer maxUserId = session.selectOne(statement);
		
		if (maxUserId > 0) {
			return maxUserId + 1;
		} else {
			return 10001;
		}
		
		
	}

	// 添加用户
	public void userAdd(User user) {

		// 数据库连接相关
		session = dbMybatisConnect();

		// 映射sql的标识字符串
		statement = statementParemet + "insertUser";

		// 更新用户信息
		int insertResult = session.insert(statement, user);
		// 确定有数据更新后commit；
		if (insertResult > 0) {
			session.commit();
		}
	}

}
