package com.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.po.Emp;
import com.po.PageBean;
//员工表
@Service("EmpDao")
public interface IEmpMapper {
public int save(Emp emp);//添加
public int update(Emp emp);//修改
public int deById(Integer eid);//删除
public Emp findById(Integer eid);//查询单个
public List<Emp> findPageAll(PageBean pd);//查询所有
public int findMaxRow();//查询最大记录数

//获取Id最大值
public int findMaxEid();
}




