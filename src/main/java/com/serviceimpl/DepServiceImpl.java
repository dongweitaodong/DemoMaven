package com.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.po.Dep;
import com.service.IDepService;
import com.util.DaoMapperUtil;
//com.service的实现层
@Service("DepService")
@Transactional
public class DepServiceImpl implements IDepService {
    //将DaoMapperUtil注入到DepService的实现类DepServiceImpl中，然后通过DaoMapperUtil的对象daomapper调用DaoMapperUtil中的方法
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
