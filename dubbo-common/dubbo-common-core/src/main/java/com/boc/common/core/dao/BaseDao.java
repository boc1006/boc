package com.boc.common.core.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import com.boc.common.exception.BizException;
import com.boc.common.metatype.DTO;
import com.boc.common.page.Page;


/**
 * 
 * @描述: 数据访问层基础支撑接口.
 * @作者: hj87080234@gmail.com
 * @创建时间: 2013-7-22,下午4:52:52 .
 * @版本: 1.0 .
 * @param <T>
 */
public interface BaseDao<T> {
	
	public abstract int insert(String statement,T entity) throws BizException;
	public abstract int insert(String statement,List<T> list) throws BizException;
	
	public abstract int update(String statement,T entity) throws BizException;
	public abstract int update(String statement,DTO entity) throws BizException;
	public abstract int update(String statement,List<T> list) throws BizException;
	
	public abstract int delete(String statement,T entity) throws BizException;
	public abstract int delete(String statement,DTO entity) throws BizException;
	public abstract int delete(String statement,List<T> list) throws BizException;
	
	public abstract T queryForObject(String statement,DTO entity) throws BizException;
	public abstract T queryForObject(String statement,T entity) throws BizException;
	public abstract T queryForObject(String statement, Integer id);
	
	public abstract List<T> queryForList(String statement,T entity) throws BizException;
	public abstract List<T> queryForList(String statement,DTO entity) throws BizException;
	public abstract List<T> queryForList(String statement,Page entity) throws BizException;
	public abstract List<T> queryForList(String statement) throws BizException;
	
	public abstract String getSequenceValue(String seqName) throws BizException;
	
	public abstract SqlSessionTemplate getSessionTemplate();
	public abstract SqlSession getSqlSession();
}
