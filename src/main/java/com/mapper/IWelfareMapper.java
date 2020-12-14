package com.mapper;
import java.util.List;
import org.springframework.stereotype.Service;
import com.po.Welfare;
@Service("WelfareDao")
//福利表Dao
public interface IWelfareMapper {
	//只有一个接口就是在添加页面上展示有几个福利
public List<Welfare> findAll();
}
