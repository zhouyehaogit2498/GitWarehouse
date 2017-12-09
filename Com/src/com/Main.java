package com;

import java.util.Scanner;

import com.service.CompanyService;
import com.service.DepartmentService;

public class Main {
	public static void main(String[] args) {
		CompanyService comObj = new CompanyService();
		DepartmentService deptObj = new DepartmentService();
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println("1、班级管理     2、学生管理  ");
			String strInput = scan.nextLine();
			if (strInput.equals("1")) {
				while (true) {
					System.out.println("1添加     2修改     3删除     4查询    5返回");
					String cStrInput = scan.nextLine();
					if (cStrInput.equals("1")) {
						comObj.add(scan);
					} else if (cStrInput.equals("2")) {
							comObj.update(scan);
					} else if (cStrInput.equals("3")) {
						comObj.del(scan);
					} else if (cStrInput.equals("4")) {
						comObj.query();
					} else if (cStrInput.equals("5")) {
						break;
					}
				}
			} else if (strInput.equals("2")) {
				while (true) {
					System.out.println("1添加     2修改     3删除     4查询    5返回");
					String dStrInput = scan.nextLine();
					if (dStrInput.equals("1")) {
						deptObj.add(scan);
					} else if (dStrInput.equals("2")) {
						deptObj.update(scan);
					} else if (dStrInput.equals("3")) {
						deptObj.del(scan);
					} else if (dStrInput.equals("4")) {
						while(true){
							System.out.println("1、查询所有学生         2、查询所有班级         3、按条件查询         4查所有学生按班级排放         5.查指定班级的学生数量      6、返回");
							String chaxun = scan.nextLine();
							if("1".equals(chaxun)){
								deptObj.query();
							}else if("2".equals(chaxun)){
								deptObj.query1(scan);
							}else if("3".equals(chaxun)){
								deptObj.query2(scan);
							}else if("4".equals(chaxun)){
								deptObj.query3(scan);
							}else if("5".equals(chaxun)){
								deptObj.query4(scan);
							}else if("6".equals(chaxun)){
								break;
							}
							
						}
					} else if (dStrInput.equals("5")) {
						break;
					}
				}
			} else if (strInput.equals("3")) {

			}
		}
		//s.close();
	}
}
