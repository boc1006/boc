package org.dubbo.acid.entity;

import java.io.Serializable;

public class MessageSubEntity implements Serializable{
	private static final long serialVersionUID = 8248040436788579781L;
	private int id;
	private String contents;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
}
