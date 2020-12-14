package com.mapper;
import java.util.List;
import org.springframework.stereotype.Service;
import com.po.Dep;

//部门实现类,因为部门不做修改，是一个固定的，所以这里只展示查询
@Service("DepDao")
public interface IDepMapper {
	//在页面添加信息时，通过下拉列表选择部门即可
public List<Dep> findAll();
}
