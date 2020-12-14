package com.service;

import java.util.List;

import com.po.Welfare;

//IWelfareService调用IWelfareMapper层，Mapper是访问数据数据库层，Service是业务逻辑层，进行关联
public interface IWelfareService {
	public List<Welfare> findAll();
}
