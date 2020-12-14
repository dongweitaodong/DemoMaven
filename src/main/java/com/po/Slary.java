package com.po;

import java.io.Serializable;

//薪资
public class Slary implements Serializable{
private Integer sid;
private Integer eid;
private Float emoney;
public Integer getSid() {
	return sid;
}
public void setSid(Integer sid) {
	this.sid = sid;
}
public Integer getEid() {
	return eid;
}
public void setEid(Integer eid) {
	this.eid = eid;
}
public Float getEmoney() {
	return emoney;
}
public void setEmoney(Float emoney) {
	this.emoney = emoney;
}
public Slary() {
	super();
	// TODO Auto-generated constructor stub
}
public Slary(Integer eid, Float emoney) {
	this.eid = eid;
	this.emoney = emoney;
}
@Override
public String toString() {
	return "Slary [sid=" + sid + ", eid=" + eid + ", emoney=" + emoney + "]";
}

}
