package com.service;

import java.util.List;

import com.po.Dep;

//service层主要主要负责与业务逻辑相关的操作，对mapper层进行调用
//IDepService层调用IDepMapper接口
public interface IDepService {
	public List<Dep> findAll();
}
