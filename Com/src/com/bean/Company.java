package com.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 实体类 公司
 * @author Administrator
 *
 */
public class Company {
	private String name;
	
	private List<Department> deptList = new ArrayList<>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Department> getDeptList() {
		return deptList;
	}
	public void setDeptList(List<Department> deptList) {
		this.deptList = deptList;
	}
	
	@Override
	public String toString() {
		return "公司信息 [名称=" + name + ", 部门  " + deptList.toString() + "]";
	}
	
	 

	
}
