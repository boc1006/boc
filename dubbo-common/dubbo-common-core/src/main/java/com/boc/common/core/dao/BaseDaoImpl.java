package com.boc.common.core.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.jdbc.SqlRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.druid.pool.DruidDataSource;
import com.boc.common.exception.BizSystemException;
import com.boc.common.metatype.DTO;
import com.boc.common.page.Page;

/**
 * 数据访问层基础支撑类
 * <p>@Title BaseDaoImpl</p>
 * <p>@Description com.boc.common.core.dao</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2016年11月11日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public abstract class BaseDaoImpl<T> extends SqlSessionDaoSupport implements BaseDao<T> {

	protected static final Logger log = LoggerFactory.getLogger(BaseDaoImpl.class);

	/**
	 * 注入SqlSessionTemplate实例(要求Spring中进行SqlSessionTemplate的配置).<br/>
	 * 可以调用sessionTemplate完成数据库操作.
	 */
	@Autowired
	private SqlSessionTemplate sessionTemplate;

	@Autowired
	protected SqlSessionFactory sqlSessionFactory;

	@Autowired
	private DruidDataSource druidDataSource;
	
	@Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {  
        super.setSqlSessionFactory(sqlSessionFactory);  
    } 

	public SqlSessionTemplate getSessionTemplate() {
		return sessionTemplate;
	}

	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}

	public SqlSession getSqlSession() {
		return super.getSqlSession();
	}

	@Override
	public int insert(String statement, T entity) {
		int result = sessionTemplate.insert(getStatement(statement), entity);
		if (result <= 0) {
			throw BizSystemException.DB_INSERT_RESULT_0.newInstance(getStatement(statement));
		}

		return result;
	}
	
	@Override
	public int insert(String statement, List<T> list) {
		if (list == null || list.size() <= 0) {
			return 0;
		}

		int result = sessionTemplate.insert(getStatement(statement), list);

		if (result <= 0) {
			throw BizSystemException.DB_INSERT_RESULT_0.newInstance(getStatement(statement));
		}

		return result;
	}

	@Override
	public int update(String statement, T entity) {
		int result = sessionTemplate.update(getStatement(statement), entity);
//		if (result <= 0) {
//			throw BizSystemException.DB_UPDATE_RESULT_0.newInstance(getStatement(statement));
//		}
		return result;
	}	
	
	@Override
	public int update(String statement, DTO entity) {
		int result = sessionTemplate.update(getStatement(statement), entity);
		if (result <= 0) {
			throw BizSystemException.DB_UPDATE_RESULT_0.newInstance(getStatement(statement));
		}
		return result;
	}	
	@Override
	public int update(String statement, List<T> list) {
		if (list == null || list.size() <= 0) {
			return 0;
		}

		int result = sessionTemplate.update(getStatement(statement), list);
//		if (result <= 0) {
//			throw BizSystemException.DB_UPDATE_RESULT_0.newInstance(getStatement(statement));
//		}
		return result;
	}

	@Override
	public int delete(String statement, T entity) {
		return (int) sessionTemplate.delete(getStatement(statement), entity);
	}
	
	@Override
	public int delete(String statement, DTO entity) {
		return (int) sessionTemplate.delete(getStatement(statement), entity);
	}

	@Override
	public int delete(String statement, List<T> list) {
		return (int) sessionTemplate.delete(getStatement(statement), list);
	}

	@Override
	public T queryForObject(String statement, T entity) {
		return sessionTemplate.selectOne(getStatement(statement), entity);
	}

	@Override
	public T queryForObject(String statement, DTO entity) {
		return sessionTemplate.selectOne(getStatement(statement), entity);
	}

	@Override
	public T queryForObject(String statement, Integer id) {
		return sessionTemplate.selectOne(getStatement(statement), id);
	}

	@Override
	public List<T> queryForList(String statement, T entity) {
		return sessionTemplate.selectList(getStatement(statement), entity);
	}

	@Override
	public List<T> queryForList(String statement, DTO entity) {
		return sessionTemplate.selectList(getStatement(statement), entity);
	}

	@Override
	public List<T> queryForList(String statement, Page entity) {
		return sessionTemplate.selectList(getStatement(statement), entity);
	}

	@Override
	public List<T> queryForList(String statement) {
		return sessionTemplate.selectList(getStatement(statement));
	}

	/**
	 * 获取Mapper命名空间.
	 * 
	 * @param sqlId
	 * @return
	 */
	public String getStatement(String sqlId) {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getClass().getName()).append(".").append(sqlId);
		return sb.toString();
	}

	/**
	 * 根据序列名称,获取序列值
	 */
	public String getSequenceValue(String seqName) {
		boolean isClosedConn = false;
		// 获取当前线程的连接
		Connection connection = this.sessionTemplate.getConnection();
		// 获取Mybatis的SQLRunner类
		SqlRunner sqlRunner = null;
		try {
			// 要执行的SQL
			String sql = "";
			// 数据库驱动类
			String driverClass = druidDataSource.getDriver().getClass().getName();
			// 不同的数据库,拼接SQL语句
			if (driverClass.equals("com.ibm.db2.jcc.DB2Driver")) {
				sql = "  VALUES " + seqName.toUpperCase() + ".NEXTVAL";
			}
			if (driverClass.equals("oracle.jdbc.OracleDriver")) {
				sql = "SELECT " + seqName.toUpperCase() + ".NEXTVAL FROM DUAL";
			}
			if (driverClass.equals("com.mysql.jdbc.Driver")) {
				sql = "SELECT  PT_UPMS_FUN_SEQ_NEXTVAL('" + seqName.toUpperCase() + "')";
			}
			// 如果状态为关闭,则需要从新打开一个连接
			if (connection.isClosed()) {
				connection = sqlSessionFactory.openSession().getConnection();
				isClosedConn = true;
			}
			sqlRunner = new SqlRunner(connection);
			Object[] args = {};
			// 执行SQL语句
			Map<String, Object> params = sqlRunner.selectOne(sql, args);
			for (Object o : params.values()) {
				return o.toString();
			}
			return null;
		} catch (Exception e) {
			throw BizSystemException.DB_GET_SEQ_NEXT_VALUE_ERROR.newInstance(seqName);
		} finally {
			if (isClosedConn) {
				sqlRunner.closeConnection();
			}
		}
	}

}
