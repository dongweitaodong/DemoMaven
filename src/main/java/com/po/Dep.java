package com.po;

import java.io.Serializable;

//部门
public class Dep implements Serializable{
private Integer depid;
private String depname;

public Integer getDepid() {
	return depid;
}
public void setDepid(Integer depid) {
	this.depid = depid;
}
public String getDepname() {
	return depname;
}
public void setDepname(String depname) {
	this.depname = depname;
}
public Dep(Integer depid, String depname) {
	super();
	this.depid = depid;
	this.depname = depname;
}
public Dep() {
	super();
}

}
