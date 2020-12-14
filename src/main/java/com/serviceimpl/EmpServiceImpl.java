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
	//��DaoMapperUtilע�뵽EmpService��ʵ����EmpServiceImpl�У�Ȼ��ͨ��DaoMapperUtil�Ķ���daomapper����DaoMapperUtil�еķ���
	@Resource(name="DaoMapper")
    private DaoMapperUtil daomapper;
	
	public DaoMapperUtil getDaomapper() {
		return daomapper;
	}
	public void setDaomapper(DaoMapperUtil daomapper) {
		this.daomapper = daomapper;
	}
	@Override
	public boolean save(Emp emp) {//���Ա����Ϣ
		//���Emp�Ķ���,ע�⣺Empmapper����ֵ������int���ͣ��������int������,
		System.out.println(emp.toString());
		int code=daomapper.getEmpMapper().save(emp);
		//��ӳɹ����ص�����������Ҫ�ж��Ƿ���ӳɹ�
		if(code>0){
			//��һ�������ȡ���ղ���ӵ�Ա��id����취��ȡ��Ա����������id���������Ǹ���ӵ�Ա�����е���Ϣ����ȡ�ղ����Ա����Ϣ��id��
			Integer eid=daomapper.getEmpMapper().findMaxEid();
			//�ڶ��������Ա����н�ʣ�ע�⣺Ա�����н���ǹ����ģ����������Ա����Ϣʱ����Ҫ���н�ʣ�
			Slary ca=new Slary(eid, emp.getEmoney());
			daomapper.getSlaryMapper().save(ca);
			//�����������Ա���ĸ�����ע�⣺Ա����͸����ǹ����ģ����������Ա����Ϣʱ����Ҫ��Ӹ�����
			//���ǣ�Ա����ѡ������ʱ�򣬶�Ӧ��ѡ����������������һ�Զ�Ĺ�ϵ��������Ҫ����һ�����飬��Ա����ѡ��ĸ���id���ŵ�������
			String[] wids=emp.getWid();//�����ʹ��Ա����emp�е���ʱ����
			//�������������жϣ��ж�wids�����Ƿ���ֵ
			if(wids!=null&&wids.length>0){
				//ͨ��forѭ��һ���õ�ֵ
				for(int i=0;i<wids.length;i++){
					//����empwelfareԱ����������
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
	public boolean update(Emp emp) {//�޸�Ա����Ϣ
		/*******��һ�����޸�Ա����Ϣ********/
		//�޸�Ա��emp����
		Integer code=daomapper.getEmpMapper().update(emp);
		//�ж�code�����Ƿ���ֵ
		if(code>0){
		/*******�ڶ������޸�Ա��н��(1��ԭ����н��2���޸ĵ�н��)********/
			//1��ԭ����н��
			Slary oldca=daomapper.getSlaryMapper().findByEid(emp.getEid());
			 if(oldca!=null&&oldca.getEmoney()!=null){//ԭ����н��
				//�ж�ԭ����н�ʺ����е�н�����Ƚϣ����ԭ����н�ʱ����ڵ�н�ʵͣ��Ͱ����е�н�ʸ�����ԭ����н��
				 if(oldca.getEmoney()<emp.getEmoney()){
					 oldca.setEmoney(emp.getEmoney());
					 daomapper.getSlaryMapper().updateByEid(oldca);
				 }else{//�������ԭ����н�ʱ����ڵ�н�ʸߣ��ǾͲ����޸�
					 daomapper.getSlaryMapper().updateByEid(oldca);
				 }
			}else{//ԭ��û��н��
				//ԭ��û��н�ʣ��Ǿ�Ҫ����slary,�����е�н�ʴ��ȥ
				Slary sl=new Slary(emp.getEid(),emp.getEmoney());
				daomapper.getSlaryMapper().save(sl);
			}
		}
		/*******���������޸�Ա������(1����ȡԭ���ĸ���2����ɾ��ԭ���ĸ���3���������Ҫ�ĸ���)********/
		List<Welfare> lswf=daomapper.getEmpwelfareMapper().findByEid(emp.getEid());
		 if(lswf!=null&&lswf.size()>0){
			 //ɾ��ԭ���ĸ���
			 daomapper.getEmpwelfareMapper().deleByEid(emp.getEid());
		 }
		 //����µĸ���
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
		//ɾ���ӱ�����
		daomapper.getSlaryMapper().delByEid(eid);
		daomapper.getEmpwelfareMapper().deleByEid(eid);
		//ɾ��Ա����
		int code=daomapper.getEmpMapper().deById(eid);
		if(code>0){
			return true;
		}
		return false;
	}

	@Override
	public Emp findById(Integer eid) {
		//��ȡԱ������
		Emp oldemp=daomapper.getEmpMapper().findById(eid);
		//��ȡн��
		Slary oldsa=daomapper.getSlaryMapper().findByEid(eid);
		if(oldsa!=null&&oldsa.getEmoney()!=null){
			oldemp.setEmoney(oldsa.getEmoney());
		}
		//��ȡ����
		List<Welfare> lswf=daomapper.getEmpwelfareMapper().findByEid(eid);
		if(lswf!=null&&lswf.size()>0){
			//���������������
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
	public List<Emp> fingAll(PageBean pd) {//��ѯ����
		System.out.print("��ѯ����");
		if(pd==null){
			System.out.print("��ѯ����1");
			pd=new PageBean();
			}
		System.out.print("��ѯ����2");
			return daomapper.getEmpMapper().findPageAll(pd);
		}


	@Override
	public int findMaxRow() {
		// TODO Auto-generated method stub
		return daomapper.getEmpMapper().findMaxRow();
		      
	}

}
