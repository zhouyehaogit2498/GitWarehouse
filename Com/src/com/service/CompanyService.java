package com.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Scanner;

import com.bean.Company;
import com.tools.DataTools;
import com.tools.MyException;
public class CompanyService {
	static String classPath = "E:/test/Com.txt";
	public void add(Scanner scan) {
		System.out.println("请输入班级名字：");
		String name = scan.nextLine();
		String claPath = readFile(classPath);
		String classArr[] = claPath.split("&&");
		for(int i=0;i<classArr.length;i++){
			if(name.equals(classArr[i])){
				System.out.println("班级已存在不能添加重复的");
				return;
			}
		}
		writeFile(classPath,name);
		System.out.println("添加成功");
	}

	public void query() {
		String claPath = readFile(classPath);
		String classArr[] = claPath.split("&&");
		for(int i =0;i<classArr.length;i++){
			String claArr[] = classArr[i].split(",");
			System.out.println("班级名称："+claArr[0]);
		}
	}

	public void update(Scanner scan) {
		System.out.println("请输入要修改的班级名字：");
		String classname = scan.nextLine();
		System.out.println("请输入新的班级名字：");
		String name = scan.nextLine();
		String fileComtext = name;
		paohui(classname,fileComtext);
		System.out.println("修改成功");
	}

	public void del(Scanner scan) {
		System.out.println("请输入要删除的班级名字：");
		String classname = scan.nextLine();
		String fileComtext = "";
		String newFileContext = null;
		String claPath = readFile(classPath);
		String classArr[] = claPath.split("&&");
		boolean isExist = false;
		for(int i = 0;i<classArr.length;i++){
			String oneclassInfo = classArr[i];
			String claArr[] = oneclassInfo.split(",");
			String name1 = claArr[0];
			if(classname.equals(name1)){
				newFileContext = claPath.replace(oneclassInfo, fileComtext);
				isExist = true;
				break;
			}
		}
		if(isExist == false){
			System.out.println("班级不存在，请检查");
			return;
		}
		writeFileFull(classPath,newFileContext);
		System.out.println("删除成功");
	}
	
	public void isExist(String comName) throws MyException{
		//判断装公司对象的list是否有公司对象
		List<Company> comList = DataTools.comList;
		if (null == comList || comList.isEmpty()) {
			throw new MyException("公司不存在，请先添加公司");
		}
		boolean isExist = false;//默认公司是不存在 
		for (Company company : comList) { //循环公司list去判断传过来的公司中名在list中是否存在
			String name = company.getName();
			if (name.equals(comName)) { //如果相等表示传过来的公司名在list中是存在的
				isExist = true;
				break;
			}
		}
		if (!isExist) { //如果没找到，这里取反后就会是true
			throw new MyException("公司不存在，请先添加公司");
		}
	}
	public static void writeFile(String companyPath,String fileContest){
		try{
			File f = new File(companyPath);//构建一个文件实例
				f.createNewFile();//如果文件不存在就创建文件
			FileWriter fw = new FileWriter(companyPath,true);//true表示是往文件中追加内容,不会覆盖之前的内容
			BufferedWriter writer = new BufferedWriter(fw);//构建一个字符输出流，可以写出为String
			writer.write(fileContest);
			writer.newLine();//写完一句后就换行
			writer.close();
		}catch(Exception e){
			System.out.println("写文件内容操作出错");
			e.printStackTrace();
		}
	}
	public static String readFile(String filePath){
		StringBuffer fileContentSb = new StringBuffer();//文件内容
		try{
			File f = new File(filePath);
			if(f.isFile() && f.exists()){
				InputStream fis = new FileInputStream(f);//字节流
				InputStreamReader read =new InputStreamReader(fis);// 字符流
																 // //,
																 // "UTF-8"
				BufferedReader reader = new BufferedReader(read);
				String line;
				while((line = reader.readLine()) !=null ){
					fileContentSb.append(line).append("&&");
				}
				reader.close();//关闭此流并释放与此流关联的所有系统资源。
				read.close();
				fis.close();
			}
		}catch(Exception e){
			System.out.println("读取文件内容操作出错");
			e.printStackTrace();
		}
		return fileContentSb.toString();
	}
	public static void writeFileFull(String filePathAndName, String fileContent) {
		try {
			File f = new File(filePathAndName); // 构建一个文件实例
			if (!f.exists()) {
				f.createNewFile(); // 如果文件不存在就创建文件
			}
			OutputStream os = new FileOutputStream(f); //构建输出字节流
			OutputStreamWriter osw = new OutputStreamWriter(os);//构建输出字符流
			BufferedWriter writer = new BufferedWriter(osw); // 构建一个字符输出流,可以写出为string
			String companyArr[] = fileContent.split("&&"); //将一串公司信息拆成单个公司
			for (int i = 0; i < companyArr.length; i++) {
				String company = companyArr[i]; //单个公司
				if (!"".equals(company)) {
					writer.write(company); // 将内容写到文件中
					writer.newLine(); // 写完一句后就换行
				}
			}
			writer.close();
		} catch (Exception e) {
			System.out.println("写文件内容操作出错");
			e.printStackTrace();
		}
	}
	public static void paohui(String classname , String fileComtext){
		String newFileContext = null;
		String claPath = readFile(classPath);
		String classArr[] = claPath.split("&&");
		boolean isExist = false;
		for(int i = 0;i<classArr.length;i++){
			String oneclassInfo = classArr[i];
			String claArr[] = oneclassInfo.split(",");
			String name1 = claArr[0];
			if(classname.equals(name1)){
				newFileContext = claPath.replace(oneclassInfo, fileComtext);
				isExist = true;
				break;
			}
		}
		if(isExist == false){
			System.out.println("班级不存在，请检查");
			return;
		}
		writeFileFull(classPath,newFileContext);
	}
}
