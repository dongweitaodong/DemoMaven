package com.service;

import java.util.List;

import com.po.Dep;

//service����Ҫ��Ҫ������ҵ���߼���صĲ�������mapper����е���
//IDepService�����IDepMapper�ӿ�
public interface IDepService {
	public List<Dep> findAll();
}
