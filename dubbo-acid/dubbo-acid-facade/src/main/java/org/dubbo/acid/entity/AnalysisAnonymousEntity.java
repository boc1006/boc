package org.dubbo.acid.entity;

import java.io.Serializable;

public class AnalysisAnonymousEntity implements Serializable {

	private static final long serialVersionUID = -307695598152779302L;
	
	private long id;
	private String clientid;
	private String clientip;
	private String pageid;
	private String referer;
	private String useragent;
	private String userdata;
	private long servtime;
	private long clienttme;
	private long dbtime;
	private String terminal;
	private String alldata;
	private int residencetime;
	private String siteid;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	public String getClientip() {
		return clientip;
	}
	public void setClientip(String clientip) {
		this.clientip = clientip;
	}
	public String getPageid() {
		return pageid;
	}
	public void setPageid(String pageid) {
		this.pageid = pageid;
	}
	public String getReferer() {
		return referer;
	}
	public void setReferer(String referer) {
		this.referer = referer;
	}
	public String getUseragent() {
		return useragent;
	}
	public void setUseragent(String useragent) {
		this.useragent = useragent;
	}
	public String getUserdata() {
		return userdata;
	}
	public void setUserdata(String userdata) {
		this.userdata = userdata;
	}
	public long getServtime() {
		return servtime;
	}
	public void setServtime(long servtime) {
		this.servtime = servtime;
	}
	public long getClienttme() {
		return clienttme;
	}
	public void setClienttme(long clienttme) {
		this.clienttme = clienttme;
	}
	public long getDbtime() {
		return dbtime;
	}
	public void setDbtime(long dbtime) {
		this.dbtime = dbtime;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getAlldata() {
		return alldata;
	}
	public void setAlldata(String alldata) {
		this.alldata = alldata;
	}
	public int getResidencetime() {
		return residencetime;
	}
	public void setResidencetime(int residencetime) {
		this.residencetime = residencetime;
	}
	public String getSiteid() {
		return siteid;
	}
	public void setSiteid(String siteid) {
		this.siteid = siteid;
	}
	
}
