package com.mapper;
import org.springframework.stereotype.Service;


import com.po.Slary;
//薪资表
@Service("SlaryDao")
public interface ISlaryMapper {
//薪资保存
public int save(Slary ca);
//根据员工编号修改薪资
public int updateByEid(Slary ca);
//根据员工编号删除薪资
public int delByEid(Integer eid);
//根据员工编号查询薪资
public Slary findByEid(Integer eid);
}



