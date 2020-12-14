package com.service;
import java.util.List;
import com.po.Emp;
import com.po.PageBean;

//IEmpService业务逻辑层调用IEmpMapper数据访问层
public interface IEmpService {
	public boolean save(Emp emp);//添加
	public boolean update(Emp emp);//修改
	public boolean deById(Integer eid);//删除
	public Emp findById(Integer eid);//查询单个
	public List<Emp> fingAll(PageBean pd);//查询所有
	public int findMaxRow();//查询最大记录数
}
