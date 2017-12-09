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
		System.out.println("������༶���֣�");
		String name = scan.nextLine();
		String claPath = readFile(classPath);
		String classArr[] = claPath.split("&&");
		for(int i=0;i<classArr.length;i++){
			if(name.equals(classArr[i])){
				System.out.println("�༶�Ѵ��ڲ�������ظ���");
				return;
			}
		}
		writeFile(classPath,name);
		System.out.println("��ӳɹ�");
	}

	public void query() {
		String claPath = readFile(classPath);
		String classArr[] = claPath.split("&&");
		for(int i =0;i<classArr.length;i++){
			String claArr[] = classArr[i].split(",");
			System.out.println("�༶���ƣ�"+claArr[0]);
		}
	}

	public void update(Scanner scan) {
		System.out.println("������Ҫ�޸ĵİ༶���֣�");
		String classname = scan.nextLine();
		System.out.println("�������µİ༶���֣�");
		String name = scan.nextLine();
		String fileComtext = name;
		paohui(classname,fileComtext);
		System.out.println("�޸ĳɹ�");
	}

	public void del(Scanner scan) {
		System.out.println("������Ҫɾ���İ༶���֣�");
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
			System.out.println("�༶�����ڣ�����");
			return;
		}
		writeFileFull(classPath,newFileContext);
		System.out.println("ɾ���ɹ�");
	}
	
	public void isExist(String comName) throws MyException{
		//�ж�װ��˾�����list�Ƿ��й�˾����
		List<Company> comList = DataTools.comList;
		if (null == comList || comList.isEmpty()) {
			throw new MyException("��˾�����ڣ�������ӹ�˾");
		}
		boolean isExist = false;//Ĭ�Ϲ�˾�ǲ����� 
		for (Company company : comList) { //ѭ����˾listȥ�жϴ������Ĺ�˾������list���Ƿ����
			String name = company.getName();
			if (name.equals(comName)) { //�����ȱ�ʾ�������Ĺ�˾����list���Ǵ��ڵ�
				isExist = true;
				break;
			}
		}
		if (!isExist) { //���û�ҵ�������ȡ����ͻ���true
			throw new MyException("��˾�����ڣ�������ӹ�˾");
		}
	}
	public static void writeFile(String companyPath,String fileContest){
		try{
			File f = new File(companyPath);//����һ���ļ�ʵ��
				f.createNewFile();//����ļ������ھʹ����ļ�
			FileWriter fw = new FileWriter(companyPath,true);//true��ʾ�����ļ���׷������,���Ḳ��֮ǰ������
			BufferedWriter writer = new BufferedWriter(fw);//����һ���ַ������������д��ΪString
			writer.write(fileContest);
			writer.newLine();//д��һ���ͻ���
			writer.close();
		}catch(Exception e){
			System.out.println("д�ļ����ݲ�������");
			e.printStackTrace();
		}
	}
	public static String readFile(String filePath){
		StringBuffer fileContentSb = new StringBuffer();//�ļ�����
		try{
			File f = new File(filePath);
			if(f.isFile() && f.exists()){
				InputStream fis = new FileInputStream(f);//�ֽ���
				InputStreamReader read =new InputStreamReader(fis);// �ַ���
																 // //,
																 // "UTF-8"
				BufferedReader reader = new BufferedReader(read);
				String line;
				while((line = reader.readLine()) !=null ){
					fileContentSb.append(line).append("&&");
				}
				reader.close();//�رմ������ͷ����������������ϵͳ��Դ��
				read.close();
				fis.close();
			}
		}catch(Exception e){
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
			OutputStream os = new FileOutputStream(f); //��������ֽ���
			OutputStreamWriter osw = new OutputStreamWriter(os);//��������ַ���
			BufferedWriter writer = new BufferedWriter(osw); // ����һ���ַ������,����д��Ϊstring
			String companyArr[] = fileContent.split("&&"); //��һ����˾��Ϣ��ɵ�����˾
			for (int i = 0; i < companyArr.length; i++) {
				String company = companyArr[i]; //������˾
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
			System.out.println("�༶�����ڣ�����");
			return;
		}
		writeFileFull(classPath,newFileContext);
	}
}
