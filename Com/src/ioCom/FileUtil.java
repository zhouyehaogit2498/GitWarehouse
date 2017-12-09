package ioCom;

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

public class FileUtil {

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
			fw.close();
		} catch (Exception e) {
			System.out.println("д�ļ����ݲ�������");
			e.printStackTrace();
		}
	}
	
	/**
	 * д�ļ�����
	 * ,���帲������
	 * 
	 * @param filePathAndName
	 *            �ļ�·��
	 * @param fileContent
	 *            �ļ�����
	 */
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

}
