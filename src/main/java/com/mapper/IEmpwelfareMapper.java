package com.mapper;
import java.util.List;

import org.springframework.stereotype.Service;
//福利表接口


import com.po.Empwelfare;
import com.po.Welfare;
@Service("EmpwelfareDao")
//员工福利关系表
public interface IEmpwelfareMapper {
	//保存员工福利数据
	public int save(Empwelfare epf);
	//根据员工编号查询对应的福利集合
	public List<Welfare> findByEid(Integer eid);
	//根据员工编号删除对应的福利数据
	public int deleByEid(Integer eid);

}


