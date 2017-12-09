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
 * ��ѯ�� ��ѯ���а༶��2��ѯ����ѧ�� 3��������ѯ 4������ѧ�����༶�ŷ� 5.��ָ���༶��ѧ������
 */
public class DepartmentService {
	static String classPath = "E:/test/Com.txt";
	static String studentPath = "E:/test/Student.txt";
//	CompanyService comObj = new CompanyService();

	public void add(Scanner scan) {
		// �����빫˾
		System.out.println("������Ҫ���ѧ���İ༶���ƣ�");
		paohui();
		String comName = scan.nextLine();
		boolean isExist = true;
		String Arr[] = readFile(classPath).split("&&");
		for (int i = 0; i < Arr.length; i++) {
			if (comName.equals(Arr[i])) {
				System.out.println("������ѧ�����ƣ�");
				String name = scan.nextLine();
				System.out.println("������ѧ�����䣺");
				String desc = scan.nextLine();
				String filestutext = comName + "," + name + "," + desc;
				writeFile(studentPath, filestutext);
				System.out.println("��ӳɹ�");
				isExist = false;
				break;
			}
		}
		if (isExist) {
			System.out.println("�����⣬�༶������");
			return;
		}
	}

	/**
	 * ��ѯ����
	 */
	public void query() {
		String stuPath = readFile(studentPath);
		String studentArr[] = stuPath.split("&&");
		for (int i = 0; i < studentArr.length; i++) {
			String studentInfo = studentArr[i];
			String stuArr[] = studentInfo.split(",");
			System.out.println("���ڰ༶:" + stuArr[0] + "  ѧ������:" + stuArr[1] + "   ѧ������:" + stuArr[2]);
		}
	}

	public void query1(Scanner scan) {
		String claPath = readFile(classPath);
		String classArr[] = claPath.split("&&");
		for (int i = 0; i < classArr.length; i++) {
			String claArr[] = classArr[i].split(",");
			System.out.println("�༶���ƣ�" + claArr[0]);
		}
	}

	public void query2(Scanner scan) {
		System.out.println("������Ҫ��ѯѧ���İ༶��");
		paohui();// ��������Ǵ�ӡ�����еİ༶
		String className = scan.nextLine();
		System.out.println("ѧ������");
		String studentArr[] = readFile(studentPath).split("&&");
		for (int i = 0; i < studentArr.length; i++) {
			String student = studentArr[i];
			String strArr[] = student.split(",");
			if (className.equals(strArr[0])) {// ѧ���İ༶������İ༶��Ⱦͱ�ʾ���ѧ���������Ҫ��ѯ�İ༶�µ�ѧ��
				System.out.println("�༶����:" + strArr[0] + "    ѧ������:" + strArr[1] + "    ѧ������:" + strArr[2]);
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
					System.out.println("�༶���ƣ�" + className[0] + " ѧ�����ƣ�" + student[1] + "ѧ�����䣺" + student[2]);
				}
			}
		}

	}

	public void query4(Scanner scan) {
		System.out.println("������Ҫ��ѯ�İ༶��");
		paohui();
		int a = 0;
		String className = scan.nextLine();
		System.out.println("��������");
		String studentArr[] = readFile(studentPath).split("&&");//��ȡѧ���ļ��������𿪱������
		for (int i = 0; i < studentArr.length; i++) {
			String student = studentArr[i];//�õ�ѧ���ļ������ÿһ��
			String strArr[] = student.split(",");
			if (className.equals(strArr[0])) {// ѧ���İ༶������İ༶��Ⱦͱ�ʾ���ѧ���������Ҫ��ѯ�İ༶�µ�ѧ��
				a++;
			}
		}
		System.out.println(a);
	}

	public void update(Scanner scan) {
		System.out.println("������Ҫ�޸ĵ�ѧ�����֣�");
		String comName = scan.nextLine();
		System.out.println("�������µ�ѧ�����֣�");
		String name = scan.nextLine();
		System.out.println("�������µ�ѧ�����䣺");
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
			if(comName.equals(name1)){ //�ҵ���Ҫ�޸ĵ�ѧ��
				newFileContextSb.append(filestutext).append("&&");
				//newFileContext = claPath.replace(oneclassInfo, filestutext);
				isExist = true;
				//break;
			}else {//����Ҫ�޸ĵ�
				newFileContextSb.append(oneclassInfo).append("&&");
			}
		}
		if(isExist == false){
			System.out.println("ѧ�������ڣ�����");
			return;
		}
		writeFileFull(studentPath,newFileContextSb.toString());
		System.out.println("�޸ĳɹ�");
	}

	public void del(Scanner scan) {
		System.out.println("������Ҫɾ����ѧ����");
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
			System.out.println("ѧ�������ڣ�����");
			return;
		}
		writeFileFull(studentPath,newFileContext);
		System.out.println("�޸ĳɹ�");
	}

	/**
	 * �жϲ��������Ƿ����
	 */
	public void isExist(String deptName, String comName) {

	}

	public static void writeFile(String companyPath, String fileContest) {
		try {
			File f = new File(companyPath);// ����һ���ļ�ʵ��
			f.createNewFile();// ����ļ������ھʹ����ļ�
			FileWriter fw = new FileWriter(companyPath, true);// true��ʾ�����ļ���׷������,���Ḳ��֮ǰ������
			BufferedWriter writer = new BufferedWriter(fw);// ����һ���ַ������������д��ΪString
			writer.write(fileContest);
			writer.newLine();// д��һ���ͻ���
			writer.close();
		} catch (Exception e) {
			System.out.println("д�ļ����ݲ�������");
			e.printStackTrace();
		}
	}

	public static String readFile(String filePath) {
		StringBuffer fileContentSb = new StringBuffer();// �ļ�����
		try {
			File f = new File(filePath);
			if (f.isFile() && f.exists()) {
				InputStream fis = new FileInputStream(f);// �ֽ���
				InputStreamReader read = new InputStreamReader(fis);// �ַ���
																	// //,
																	// "UTF-8"
				BufferedReader reader = new BufferedReader(read);
				String line;
				while ((line = reader.readLine()) != null) {
					fileContentSb.append(line).append("&&");
				}
				reader.close();// �رմ������ͷ����������������ϵͳ��Դ��
				read.close();
				fis.close();
			}
		} catch (Exception e) {
			System.out.println("��ȡ�ļ����ݲ�������");
			e.printStackTrace();
		}
		return fileContentSb.toString();
	}

	public static void writeFileFull(String filePathAndName, String fileContent) {
		try {
			File f = new File(filePathAndName); // ����һ���ļ�ʵ��
			if (!f.exists()) {
				f.createNewFile(); // ����ļ������ھʹ����ļ�
			}
			OutputStream os = new FileOutputStream(f); // ��������ֽ���
			OutputStreamWriter osw = new OutputStreamWriter(os);// ��������ַ���
			BufferedWriter writer = new BufferedWriter(osw); // ����һ���ַ������,����д��Ϊstring
			String companyArr[] = fileContent.split("&&"); // ��һ����˾��Ϣ��ɵ�����˾
			for (int i = 0; i < companyArr.length; i++) {
				String company = companyArr[i]; // ������˾
				if (!"".equals(company)) {
					writer.write(company); // ������д���ļ���
					writer.newLine(); // д��һ���ͻ���
				}
			}
			writer.close();
		} catch (Exception e) {
			System.out.println("д�ļ����ݲ�������");
			e.printStackTrace();
		}
	}

	public static void paohui() {
		String claPath = readFile(classPath);
		String classArr[] = claPath.split("&&");
		for (int i = 0; i < classArr.length; i++) {
			String claArr[] = classArr[i].split(",");
			System.out.println("�༶���ƣ�" + claArr[0]);
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
			System.out.println("�༶�����ڣ�����");
			return;
		}
		writeFileFull(studentPath,newFileContext);
	}
}
