package com.service;

import java.util.List;

import com.po.Welfare;

//IWelfareService����IWelfareMapper�㣬Mapper�Ƿ����������ݿ�㣬Service��ҵ���߼��㣬���й���
public interface IWelfareService {
	public List<Welfare> findAll();
}
