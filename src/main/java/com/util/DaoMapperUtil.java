package com.util;
//写一个DaoMapperUtil类，在service类中想调用DaoMapperUtil类中的方法时，直接调用DaoMapperUtil，就可以调用DaoMapperUtil类中的方法

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mapper.IDepMapper;
import com.mapper.IEmpMapper;
import com.mapper.IEmpwelfareMapper;
import com.mapper.ISlaryMapper;
import com.mapper.IWelfareMapper;

@Service("DaoMapper")
public class DaoMapperUtil {
	@Resource(name="DepDao")
      public IDepMapper depMapper;
	
	@Resource(name="EmpDao")
	  public IEmpMapper empMapper;
	
	@Resource(name="EmpwelfareDao")
	  public IEmpwelfareMapper empwelfareMapper;
	
	@Resource(name="SlaryDao")
	  public ISlaryMapper slaryMapper;
	
	@Resource(name="WelfareDao")
	  public IWelfareMapper welfareMapper;

	public IDepMapper getDepMapper() {
		return depMapper;
	}

	public void setDepMapper(IDepMapper depMapper) {
		this.depMapper = depMapper;
	}

	public IEmpMapper getEmpMapper() {
		return empMapper;
	}

	public void setEmpMapper(IEmpMapper empMapper) {
		this.empMapper = empMapper;
	}

	public IEmpwelfareMapper getEmpwelfareMapper() {
		return empwelfareMapper;
	}

	public void setEmpwelfareMapper(IEmpwelfareMapper empwelfareMapper) {
		this.empwelfareMapper = empwelfareMapper;
	}

	public ISlaryMapper getSlaryMapper() {
		return slaryMapper;
	}

	public void setSlaryMapper(ISlaryMapper slaryMapper) {
		this.slaryMapper = slaryMapper;
	}

	public IWelfareMapper getWelfareMapper() {
		return welfareMapper;
	}

	public void setWelfareMapper(IWelfareMapper welfareMapper) {
		this.welfareMapper = welfareMapper;
	}
	
	
}
