package com.po;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

//员工表
public class Emp implements Serializable{
private Integer eid;
private String ename;
private String sex;
private String address;
private Date birthday;
private String photo;
private Integer depid;

//与前台页面有关的临时属性
  //与部门
private String depname;
  //与薪资
private Float emoney;
  //与福利（由于有多个福利，不能使用下拉列表，因为下拉列表每次只能选择一个，这里因为从页面选择，只能通过数组来存储，通过复选框来实现多选）
private String[] wid;//前台望后台传，福利的编号数组，接收页面复选框的值
  //与福利，在展示的时候就要展示福利的名称，在后台展示的时候用集合
private List<Welfare> lswf;
 //文件上传MultipartFile是String类型，代表HTML中formdate方式上传的文件
private MultipartFile pic;
 //与日期(前台传递进来的是String类型)
private String sdate;
public Integer getEid() {
	return eid;
}
public void setEid(Integer eid) {
	this.eid = eid;
}
public String getEname() {
	return ename;
}
public void setEname(String ename) {
	this.ename = ename;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public Date getBirthday() {
	return birthday;
}
public void setBirthday(Date birthday) {
	this.birthday = birthday;
}
public String getPhoto() {
	return photo;
}
public void setPhoto(String photo) {
	this.photo = photo;
}
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
public Float getEmoney() {
	return emoney;
}
public void setEmoney(Float emoney) {
	this.emoney = emoney;
}
public String[] getWid() {
	return wid;
}
public void setWid(String[] wid) {
	this.wid = wid;
}
public List<Welfare> getLswf() {
	return lswf;
}
public void setLswf(List<Welfare> lswf) {
	this.lswf = lswf;
}
public MultipartFile getPic() {
	return pic;
}
public void setPic(MultipartFile pic) {
	this.pic = pic;
}
public String getSdate(){
	//从数据库获取数据时，数据库是date类型，传递到前台时是String类型
	sdate=new SimpleDateFormat("yyyy-MM-dd").format(birthday);
	return sdate;
}
public void setSdate(String sdate) {
	try {
		//在前台页面赋值时，页面birthday是String类型，传递到后台时是date类型
		birthday=new SimpleDateFormat("yyyy-MM-dd").parse(sdate);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	this.sdate = sdate;
}


public Emp(Integer eid, String ename, String sex, String address, Date birthday, String photo, Integer depid) {
	super();
	this.eid = eid;
	this.ename = ename;
	this.sex = sex;
	this.address = address;
	this.birthday = birthday;
	this.photo = photo;
	this.depid = depid;
}
public Emp() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "Emp [eid=" + eid + ", ename=" + ename + ", sex=" + sex + ", address=" + address + ", birthday=" + birthday
			+ ", photo=" + photo + ", depid=" + depid + ", depname=" + depname + ", emoney=" + emoney + ", wid="
			+ Arrays.toString(wid) + ", lswf=" + lswf + ", pic=" + pic + ", sdate=" + sdate + "]";
}


}
