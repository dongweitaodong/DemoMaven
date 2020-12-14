package com.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.po.Dep;
import com.service.IDepService;
import com.util.DaoMapperUtil;
//com.service��ʵ�ֲ�
@Service("DepService")
@Transactional
public class DepServiceImpl implements IDepService {
    //��DaoMapperUtilע�뵽DepService��ʵ����DepServiceImpl�У�Ȼ��ͨ��DaoMapperUtil�Ķ���daomapper����DaoMapperUtil�еķ���
	@Resource(name="DaoMapper")
	private DaoMapperUtil daomapper;
	
    
	public DaoMapperUtil getDaomapper(){
		return daomapper;
	}

	public void setDaomapper(DaoMapperUtil daomapper) {
		this.daomapper = daomapper;
	}

	@Override
	public List<Dep> findAll() {
		// TODO Auto-generated method stub
		return daomapper.getDepMapper().findAll();
	}

}
