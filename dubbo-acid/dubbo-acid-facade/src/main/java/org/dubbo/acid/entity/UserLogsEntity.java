package org.dubbo.acid.entity;

import java.io.Serializable;

public class UserLogsEntity implements Serializable{

	private static final long serialVersionUID = 1858189729682527945L;
	
	private int id;
	private long userid;
	private String username;
	private int age;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	

}
