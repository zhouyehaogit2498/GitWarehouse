package wenjianduqu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CompanyService {

	public static List<Company> comList = new ArrayList<>();

	/**
	 * ��ӹ�˾��Ϣ
	 * @param scan
	 */
	public void add(Scanner scan) {
		while (true) {
			System.out.println("������������ӵĹ�˾����:");
			String comName = scan.nextLine();
			System.out.println("������������ӵĹ�˾����:");
			String comTel = scan.nextLine();
			Company comObj = new Company(); // ����һ����˾��ʵ����
			comObj.setName(comName);
			comObj.setTel(comTel);
			// comList.add(comObj);
			// �ѹ�˾��Ϣ���浽�ļ���
			String companyPath = "D:/test/compny.txt";
			String fileContext = comName + "," + comTel;
			// System.out.println("���������,Ҳ����Ҫ���浽�ļ��е�һ����˾����Ϣ: "+fileContext);
			writeFile(companyPath, fileContext);
			System.out.println("��ӳɹ�");
		}
	}
	
	/**
	 * ��ѯ��˾��Ϣ
	 */
	public void query(){
		String companyPath = "D:/test/compny.txt";
		String companyContext = readFile(companyPath);
		String companyContextArr[] = companyContext.split("&&");
		for (int i = 0; i < companyContextArr.length; i++) {
			//System.out.println("��˾��Ϣ:  "+companyContextArr[i]);
			String comInfoArr[] = companyContextArr[i].split(",");
			System.out.println("��˾����: "+comInfoArr[0]+" ��˾�绰��"+comInfoArr[1]);
		}
		
	}

	public static void main(String[] args) {
		CompanyService cs = new CompanyService();
		cs.query();
	}

	/**
	 * ���ļ�����
	 * 
	 * @param filePath
	 * @return
	 */
	public static String readFile(String filePath) {
		StringBuffer fileContentSb = new StringBuffer();// �ļ�����
		try {
			File f = new File(filePath);
			if (f.isFile() && f.exists()) {
				InputStream fis = new FileInputStream(f); // �ֽ���
				// FileInputStream fis = new FileInputStream(f); //�ֽ���
				InputStreamReader read = new InputStreamReader(fis, "UTF-8"); // �ַ���
																				// //,
																				// "UTF-8"
				BufferedReader reader = new BufferedReader(read);
				String line;
				while ((line = reader.readLine()) != null) {
					fileContentSb.append(line).append("&&");
				}
				reader.close();
				read.close();
				fis.close();
			}
		} catch (Exception e) {
			System.out.println("��ȡ�ļ����ݲ�������");
			e.printStackTrace();
		}
		return fileContentSb.toString();
	}

	/**
	 * д�ļ����� ,׷������
	 * 
	 * @param filePathAndName
	 *            �ļ�·��
	 * @param fileContent
	 *            �ļ�����
	 */
	public static void writeFile(String filePathAndName, String fileContent) {
		try {
			File f = new File(filePathAndName); // ����һ���ļ�ʵ��
			if (!f.exists()) {
				f.createNewFile(); // ����ļ������ھʹ����ļ�
			}
			FileWriter fw = new FileWriter(filePathAndName, true); // true��ʾ�����ļ���׷������,���Ḳ��֮ǰ������
			BufferedWriter writer = new BufferedWriter(fw); // ����һ���ַ������,����д��Ϊstring
			writer.write(fileContent); // ������д���ļ���
			writer.newLine(); // д��һ���ͻ���

			writer.close();
		} catch (Exception e) {
			System.out.println("д�ļ����ݲ�������");
			e.printStackTrace();
		}
	}

}
