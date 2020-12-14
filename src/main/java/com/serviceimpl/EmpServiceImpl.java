package com.serviceimpl;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.po.Emp;
import com.po.Empwelfare;
import com.po.PageBean;
import com.po.Slary;
import com.po.Welfare;
import com.service.IEmpService;
import com.util.DaoMapperUtil;
@Service("EmpService")
@Transactional
public class EmpServiceImpl implements IEmpService {
	//将DaoMapperUtil注入到EmpService的实现类EmpServiceImpl中，然后通过DaoMapperUtil的对象daomapper调用DaoMapperUtil中的方法
	@Resource(name="DaoMapper")
    private DaoMapperUtil daomapper;
	
	public DaoMapperUtil getDaomapper() {
		return daomapper;
	}
	public void setDaomapper(DaoMapperUtil daomapper) {
		this.daomapper = daomapper;
	}
	@Override
	public boolean save(Emp emp) {//添加员工信息
		//添加Emp的对象,注意：Empmapper返回值类型是int类型，这里就用int来接受,
		System.out.println(emp.toString());
		int code=daomapper.getEmpMapper().save(emp);
		//添加成功返回的是行数，需要判断是否添加成功
		if(code>0){
			//第一步：如何取到刚才添加的员工id？想办法获取到员工表中最大的id，就是我们刚添加到员工表中的信息，获取刚才添加员工信息的id，
			Integer eid=daomapper.getEmpMapper().findMaxEid();
			//第二步：添加员工的薪资（注意：员工表和薪资是关联的，所以在添加员工信息时就需要添加薪资）
			Slary ca=new Slary(eid, emp.getEmoney());
			daomapper.getSlaryMapper().save(ca);
			//第三步：添加员工的福利（注意：员工表和福利是关联的，所以在添加员工信息时就需要添加福利）
			//考虑：员工在选择福利的时候，对应会选择多个福利，这里是一对多的关系，所以需要创建一个数组，将员工所选择的福利id都放到数组中
			String[] wids=emp.getWid();//巧妙地使用员工表emp中的临时属性
			//对数组对象进行判断，判断wids里面是否有值
			if(wids!=null&&wids.length>0){
				//通过for循环一次拿到值
				for(int i=0;i<wids.length;i++){
					//创建empwelfare员工福利对象
					Empwelfare ewf=new Empwelfare(eid,new Integer(wids[i]));
					System.out.println(ewf.toString());
					daomapper.getEmpwelfareMapper().save(ewf);
				}
			}
			return true;
		}
		return false;
	
	}
	@Override
	public boolean update(Emp emp) {//修改员工信息
		/*******第一步：修改员工信息********/
		//修改员工emp对象
		Integer code=daomapper.getEmpMapper().update(emp);
		//判断code里面是否有值
		if(code>0){
		/*******第二步：修改员工薪资(1、原来的薪资2、修改的薪资)********/
			//1、原来的薪资
			Slary oldca=daomapper.getSlaryMapper().findByEid(emp.getEid());
			 if(oldca!=null&&oldca.getEmoney()!=null){//原来有薪资
				//判断原来的薪资和现有的薪资作比较，如果原来的薪资比现在的薪资低，就把现有的薪资给覆盖原来的薪资
				 if(oldca.getEmoney()<emp.getEmoney()){
					 oldca.setEmoney(emp.getEmoney());
					 daomapper.getSlaryMapper().updateByEid(oldca);
				 }else{//否则就是原来的薪资比现在的薪资高，那就不做修改
					 daomapper.getSlaryMapper().updateByEid(oldca);
				 }
			}else{//原来没有薪资
				//原来没有薪资，那就要创建slary,把现有的薪资存进去
				Slary sl=new Slary(emp.getEid(),emp.getEmoney());
				daomapper.getSlaryMapper().save(sl);
			}
		}
		/*******第三步：修改员工福利(1、获取原来的福利2、先删除原来的福利3、添加现在要的福利)********/
		List<Welfare> lswf=daomapper.getEmpwelfareMapper().findByEid(emp.getEid());
		 if(lswf!=null&&lswf.size()>0){
			 //删除原来的福利
			 daomapper.getEmpwelfareMapper().deleByEid(emp.getEid());
		 }
		 //添加新的福利
		 String[] wids=emp.getWid();
		 if(wids!=null&&wids.length>0){
				for(int i=0;i<wids.length;i++){
					Empwelfare ewf=new Empwelfare(emp.getEid(),new Integer(wids[i]));
					daomapper.getEmpwelfareMapper().save(ewf);
				}
				return true;
			}
		return false;
	}

	@Override
	public boolean deById(Integer eid) {
		//删除子表数据
		daomapper.getSlaryMapper().delByEid(eid);
		daomapper.getEmpwelfareMapper().deleByEid(eid);
		//删除员工表
		int code=daomapper.getEmpMapper().deById(eid);
		if(code>0){
			return true;
		}
		return false;
	}

	@Override
	public Emp findById(Integer eid) {
		//获取员工对象
		Emp oldemp=daomapper.getEmpMapper().findById(eid);
		//获取薪资
		Slary oldsa=daomapper.getSlaryMapper().findByEid(eid);
		if(oldsa!=null&&oldsa.getEmoney()!=null){
			oldemp.setEmoney(oldsa.getEmoney());
		}
		//获取福利
		List<Welfare> lswf=daomapper.getEmpwelfareMapper().findByEid(eid);
		if(lswf!=null&&lswf.size()>0){
			//创建福利编号数组
			String[] wids=new String[lswf.size()];
			for(int i=0;i<lswf.size();i++){
				Welfare wf=lswf.get(i);
				wids[i]=wf.getWid().toString();
			}
			oldemp.setWid(wids);
		}
		oldemp.setLswf(lswf);
		return oldemp;
	}

	@Override
	public List<Emp> fingAll(PageBean pd) {//查询所有
		System.out.print("查询所有");
		if(pd==null){
			System.out.print("查询所有1");
			pd=new PageBean();
			}
		System.out.print("查询所有2");
			return daomapper.getEmpMapper().findPageAll(pd);
		}


	@Override
	public int findMaxRow() {
		// TODO Auto-generated method stub
		return daomapper.getEmpMapper().findMaxRow();
		      
	}

}
