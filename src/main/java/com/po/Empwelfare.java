package com.po;

import java.io.Serializable;
//员工福利关系表
public class Empwelfare implements Serializable{
private Integer ewid;
private Integer eid;
private Integer wid;
public Integer getEwid() {
	return ewid;
}
public void setEwid(Integer ewid) {
	this.ewid = ewid;
}
public Integer getEid() {
	return eid;
}
public void setEid(Integer eid) {
	this.eid = eid;
}
public Integer getWid() {
	return wid;
}
public void setWid(Integer wid) {
	this.wid = wid;
}
public Empwelfare(Integer eid, Integer wid) {
	this.eid = eid;
	this.wid = wid;
}
public Empwelfare() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "Empwelfare [ewid=" + ewid + ", eid=" + eid + ", wid=" + wid + "]";
}

}
