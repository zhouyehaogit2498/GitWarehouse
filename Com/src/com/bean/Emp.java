package com.bean;

public class Emp {
	private String name;
	private int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Ա����Ϣ    ���֣�" + name + ", ���䣺" + age;
	}


}
