package com.turing.test;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.turing.entity.Dept;
import com.turing.entity.Emp;
import com.turing.entity.EmpExample;
import com.turing.entity.EmpExample.Criteria;
import com.turing.mapper.DeptMapper;
import com.turing.mapper.EmpMapper;
import com.turing.util.MybatisUtil;

public class MybatisTestGenerator {

	SqlSession session = null;
	EmpMapper mapper = null;
	
	@Before
	public void start() {
		session = MybatisUtil.openSession();
		 mapper = session.getMapper(EmpMapper.class);
	}
	@After
	public void end() {
		session.close();
	}
	//1������id��ѯԱ����
	@Test
	public void test1() {
		Emp emp = mapper.selectByPrimaryKey(2);
		System.out.println(emp);
	}
	
	//2���������Ʋ�ѯ���š�
	@Test
	public void test2() {
		EmpExample example = new EmpExample();
		example.createCriteria().andNameEqualTo("С��");
		List<Emp> list = mapper.selectByExample(example);
		for (Emp emp : list) {
			System.out.println(emp.getDeptno());
		}
	}
	
	//3����ѯidΪ1����������Ϊ������ա���Ա����
	@Test
	public void test3() {
		EmpExample example  = new EmpExample();
		example.createCriteria().andNameEqualTo("С��").andIdEqualTo(4);
		List<Emp> list = mapper.selectByExample(example);
		System.out.println(list);
	}
	
	//4����ѯ�����а���ĳ���ؼ��֣���id��10���ڵ�Ա����
	@Test
	public void test4() {
		EmpExample example = new EmpExample();
		//�ؼ����������������ķ�������װ�ڸ�Criteria��������
		//����������andXXX��ͷ
		Criteria criteria = example.createCriteria();
		//��������
		criteria.andNameLike("%��%");
		criteria.andIdBetween(1, 10);
		//ִ�в�ѯ
		List<Emp> list = mapper.selectByExample(example);
		System.out.println(list);     
		
	}
	
	//5����ѯ����Ա������Ա����Ž������С�
	@Test
	public void test5() {
		EmpExample example = new EmpExample();
		example.setOrderByClause("id desc");
		List<Emp> list = mapper.selectByExample(example);
		for (Emp emp : list) {
			System.out.println(emp);
		}
	}
	
	//6����ҳ��ѯ��ÿҳ��ʾ5������ѯ�ڶ�ҳԱ����Ϣ��
	@Test
	public void test6() {
		int pagerNow = 2;
		int pagerSize = 5;
		pagerNow = (pagerNow-1)*pagerSize;
		EmpExample example = new EmpExample();
		RowBounds rowBounds = new RowBounds(pagerNow,pagerSize);
		List<Emp> list = mapper.selectByExampleWithRowbounds(example, rowBounds);
		for (Emp emp : list) {
			System.out.println(emp);
		}
	}
	
	//7����ѯ(��������"��"������id��3~7֮��)������ (���Ų���2�Ų���)��
	@Test
	public void test7() {
		EmpExample example = new EmpExample();
		Criteria criteria1 = example.createCriteria();
		criteria1.andNameLike("%��%").andIdBetween(3, 7);
		Criteria criteria2 = example.createCriteria();
		criteria2.andDeptnoNotEqualTo(2);
		 //�ں�����example
		example.or(criteria2);
		List<Emp> list = mapper.selectByExample(example);
		for (Emp emp : list) {
			System.out.println(emp);
		}
	}
	
	//8������idɾ��Ա����
	@Test
	public void test8() {
		int key = mapper.deleteByPrimaryKey(10);
		System.out.println(key);
	}
	
	//9����2��Ա��������Ϊ�������ԡ�,δ�޸ĵ��ֶβ��䡣
	@Test
	public void test9() {
		Emp emp = new Emp();
		emp.setId(2);
		emp.setName("������");
		int keySelective = mapper.updateByPrimaryKeySelective(emp);
		System.out.println(keySelective);
	}
	//10����ӡ������������š�
	@Test
	public void test10() {
		SqlSession session2 = MybatisUtil.openSession();
		DeptMapper mapper2 = session2.getMapper(DeptMapper.class);
		Dept dept = new Dept(null, "������");
		int insert = mapper2.insert(dept);
		System.out.println(insert);
	}
}
