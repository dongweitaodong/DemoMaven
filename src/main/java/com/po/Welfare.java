package com.po;

import java.io.Serializable;
//福利表
public class Welfare implements Serializable{
private Integer wid;
private String wname;
public Integer getWid() {
	return wid;
}
public void setWid(Integer wid) {
	this.wid = wid;
}
public String getWname() {
	return wname;
}
public void setWname(String wname) {
	this.wname = wname;
}
public Welfare(Integer wid, String wname) {
	super();
	this.wid = wid;
	this.wname = wname;
}
public Welfare() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "Welfare [wid=" + wid + ", wname=" + wname + "]";
}

}
