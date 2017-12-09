package wenjianduqu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Duqu {
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


