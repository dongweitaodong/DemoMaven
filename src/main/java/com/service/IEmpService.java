package com.service;
import java.util.List;
import com.po.Emp;
import com.po.PageBean;

//IEmpServiceҵ���߼������IEmpMapper���ݷ��ʲ�
public interface IEmpService {
	public boolean save(Emp emp);//���
	public boolean update(Emp emp);//�޸�
	public boolean deById(Integer eid);//ɾ��
	public Emp findById(Integer eid);//��ѯ����
	public List<Emp> fingAll(PageBean pd);//��ѯ����
	public int findMaxRow();//��ѯ����¼��
}
