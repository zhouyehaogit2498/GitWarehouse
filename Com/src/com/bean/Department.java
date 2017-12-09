package com.bean;

import java.util.ArrayList;
import java.util.List;

public class Department {
	private String name;
	private String desc;
	
	private List<Emp> empList = new ArrayList<>();  //Ա��list

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public List<Emp> getEmpList() {
		return empList;
	}
	public void setEmpList(List<Emp> empList) {
		this.empList = empList;
	}
	
	@Override
	public String toString() {
		return "ѧ����Ϣ  [ ���ƣ�" + name + ", ���䣺" + desc + "]";
	}

}
