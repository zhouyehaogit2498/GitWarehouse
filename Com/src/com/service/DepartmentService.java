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
import java.util.Scanner;

/**
 * 查询： 查询所有班级，2查询所有学生 3按条件查询 4查所有学生按班级排放 5.查指定班级的学生数量
 */
public class DepartmentService {
	static String classPath = "E:/test/Com.txt";
	static String studentPath = "E:/test/Student.txt";
//	CompanyService comObj = new CompanyService();

	public void add(Scanner scan) {
		// 请输入公司
		System.out.println("请输入要添加学生的班级名称：");
		paohui();
		String comName = scan.nextLine();
		boolean isExist = true;
		String Arr[] = readFile(classPath).split("&&");
		for (int i = 0; i < Arr.length; i++) {
			if (comName.equals(Arr[i])) {
				System.out.println("请输入学生名称：");
				String name = scan.nextLine();
				System.out.println("请输入学生年龄：");
				String desc = scan.nextLine();
				String filestutext = comName + "," + name + "," + desc;
				writeFile(studentPath, filestutext);
				System.out.println("添加成功");
				isExist = false;
				break;
			}
		}
		if (isExist) {
			System.out.println("有问题，班级不存在");
			return;
		}
	}

	/**
	 * 查询部门
	 */
	public void query() {
		String stuPath = readFile(studentPath);
		String studentArr[] = stuPath.split("&&");
		for (int i = 0; i < studentArr.length; i++) {
			String studentInfo = studentArr[i];
			String stuArr[] = studentInfo.split(",");
			System.out.println("所在班级:" + stuArr[0] + "  学生姓名:" + stuArr[1] + "   学生年龄:" + stuArr[2]);
		}
	}

	public void query1(Scanner scan) {
		String claPath = readFile(classPath);
		String classArr[] = claPath.split("&&");
		for (int i = 0; i < classArr.length; i++) {
			String claArr[] = classArr[i].split(",");
			System.out.println("班级名称：" + claArr[0]);
		}
	}

	public void query2(Scanner scan) {
		System.out.println("请输入要查询学生的班级：");
		paohui();// 这个方法是打印出已有的班级
		String className = scan.nextLine();
		System.out.println("学生如下");
		String studentArr[] = readFile(studentPath).split("&&");
		for (int i = 0; i < studentArr.length; i++) {
			String student = studentArr[i];
			String strArr[] = student.split(",");
			if (className.equals(strArr[0])) {// 学生的班级与输入的班级相等就表示这个学生是输入的要查询的班级下的学生
				System.out.println("班级名称:" + strArr[0] + "    学生名字:" + strArr[1] + "    学生年龄:" + strArr[2]);
			}
		}
	}

	public void query3(Scanner scan) {
		String classFile = readFile(classPath);
		String studentFile = readFile(studentPath);
		String classFileArr[] = classFile.split("&&");
		String studentFileArr[] = studentFile.split("&&");
		for (int i = 0; i < classFileArr.length; i++) {
			String className[] = classFileArr[i].split(",");
			for (int j = 0; j < studentFileArr.length; j++) {
				if (studentFileArr[j].startsWith(className[0])) {
					String student[] = studentFileArr[j].split(",");
					System.out.println("班级名称：" + className[0] + " 学生名称：" + student[1] + "学生年龄：" + student[2]);
				}
			}
		}

	}

	public void query4(Scanner scan) {
		System.out.println("请输入要查询的班级：");
		paohui();
		int a = 0;
		String className = scan.nextLine();
		System.out.println("数量如下");
		String studentArr[] = readFile(studentPath).split("&&");//获取学生文件并把它拆开变成数组
		for (int i = 0; i < studentArr.length; i++) {
			String student = studentArr[i];//得到学生文件里面的每一个
			String strArr[] = student.split(",");
			if (className.equals(strArr[0])) {// 学生的班级与输入的班级相等就表示这个学生是输入的要查询的班级下的学生
				a++;
			}
		}
		System.out.println(a);
	}

	public void update(Scanner scan) {
		System.out.println("请输入要修改的学生名字：");
		String comName = scan.nextLine();
		System.out.println("请输入新的学生名字：");
		String name = scan.nextLine();
		System.out.println("请输入新的学生年龄：");
		String desc = scan.nextLine();
		String filestutext = name+","+desc;
		//String newFileContext = null;
		String claPath = readFile(studentPath);
		String classArr[] = claPath.split("&&");
		boolean isExist = false;
		StringBuffer newFileContextSb = new StringBuffer();
		for(int i = 0;i<classArr.length;i++){
			String oneclassInfo = classArr[i];
			String claArr[] = oneclassInfo.split(",");
			String name1 = claArr[1];
			if(comName.equals(name1)){ //找到了要修改的学生
				newFileContextSb.append(filestutext).append("&&");
				//newFileContext = claPath.replace(oneclassInfo, filestutext);
				isExist = true;
				//break;
			}else {//不需要修改的
				newFileContextSb.append(oneclassInfo).append("&&");
			}
		}
		if(isExist == false){
			System.out.println("学生不存在，请检查");
			return;
		}
		writeFileFull(studentPath,newFileContextSb.toString());
		System.out.println("修改成功");
	}

	public void del(Scanner scan) {
		System.out.println("请输入要删除的学生：");
		String comName = scan.nextLine();
		String filestutext = "";
		String newFileContext = null;
		String claPath = readFile(studentPath);
		String classArr[] = claPath.split("&&");
		boolean isExist = false;
		for(int i = 0;i<classArr.length;i++){
			String oneclassInfo = classArr[i];
			String claArr[] = oneclassInfo.split(",");
			String name1 = claArr[1];
			if(comName.equals(name1)){
				newFileContext = claPath.replace(oneclassInfo, filestutext);
				isExist = true;
				break;
			}
		}
		if(isExist == false){
			System.out.println("学生不存在，请检查");
			return;
		}
		writeFileFull(studentPath,newFileContext);
		System.out.println("修改成功");
	}

	/**
	 * 判断部门名称是否存在
	 */
	public void isExist(String deptName, String comName) {

	}

	public static void writeFile(String companyPath, String fileContest) {
		try {
			File f = new File(companyPath);// 构建一个文件实例
			f.createNewFile();// 如果文件不存在就创建文件
			FileWriter fw = new FileWriter(companyPath, true);// true表示是往文件中追加内容,不会覆盖之前的内容
			BufferedWriter writer = new BufferedWriter(fw);// 构建一个字符输出流，可以写出为String
			writer.write(fileContest);
			writer.newLine();// 写完一句后就换行
			writer.close();
		} catch (Exception e) {
			System.out.println("写文件内容操作出错");
			e.printStackTrace();
		}
	}

	public static String readFile(String filePath) {
		StringBuffer fileContentSb = new StringBuffer();// 文件内容
		try {
			File f = new File(filePath);
			if (f.isFile() && f.exists()) {
				InputStream fis = new FileInputStream(f);// 字节流
				InputStreamReader read = new InputStreamReader(fis);// 字符流
																	// //,
																	// "UTF-8"
				BufferedReader reader = new BufferedReader(read);
				String line;
				while ((line = reader.readLine()) != null) {
					fileContentSb.append(line).append("&&");
				}
				reader.close();// 关闭此流并释放与此流关联的所有系统资源。
				read.close();
				fis.close();
			}
		} catch (Exception e) {
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
			OutputStream os = new FileOutputStream(f); // 构建输出字节流
			OutputStreamWriter osw = new OutputStreamWriter(os);// 构建输出字符流
			BufferedWriter writer = new BufferedWriter(osw); // 构建一个字符输出流,可以写出为string
			String companyArr[] = fileContent.split("&&"); // 将一串公司信息拆成单个公司
			for (int i = 0; i < companyArr.length; i++) {
				String company = companyArr[i]; // 单个公司
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

	public static void paohui() {
		String claPath = readFile(classPath);
		String classArr[] = claPath.split("&&");
		for (int i = 0; i < classArr.length; i++) {
			String claArr[] = classArr[i].split(",");
			System.out.println("班级名称：" + claArr[0]);
		}
	}
	public static void paohui1(String classname , String filestutext){
		String newFileContext = null;
		String claPath = readFile(studentPath);
		String classArr[] = claPath.split("&&");
		boolean isExist = false;
		for(int i = 0;i<classArr.length;i++){
			String oneclassInfo = classArr[i];
			String claArr[] = oneclassInfo.split(",");
			String name1 = claArr[0];
			if(classname.equals(name1)){
				newFileContext = claPath.replace(oneclassInfo, filestutext);
				isExist = true;
				break;
			}
		}
		if(isExist == false){
			System.out.println("班级不存在，请检查");
			return;
		}
		writeFileFull(studentPath,newFileContext);
	}
}
