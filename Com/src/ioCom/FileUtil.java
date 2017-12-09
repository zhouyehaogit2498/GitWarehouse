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
	 * 读文件内容
	 * 
	 * @param filePath
	 * @return
	 */
	public static String readFile(String filePath) {
		StringBuffer fileContentSb = new StringBuffer();// 文件内容
		try {
			File f = new File(filePath);
			if (f.isFile() && f.exists()) {
				InputStream fis = new FileInputStream(f); // 字节流
				// FileInputStream fis = new FileInputStream(f); //字节流
				InputStreamReader read = new InputStreamReader(fis, "UTF-8"); // 字符流
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
			System.out.println("读取文件内容操作出错");
			e.printStackTrace();
		}
		return fileContentSb.toString();
	}

	/**
	 * 写文件内容 ,追加内容
	 * 
	 * @param filePathAndName
	 *            文件路径
	 * @param fileContent
	 *            文件内容
	 */
	public static void writeFile(String filePathAndName, String fileContent) {
		try {
			File f = new File(filePathAndName); // 构建一个文件实例
			if (!f.exists()) {
				f.createNewFile(); // 如果文件不存在就创建文件
			}
			FileWriter fw = new FileWriter(filePathAndName, true); // true表示是往文件中追加内容,不会覆盖之前的内容
			BufferedWriter writer = new BufferedWriter(fw); // 构建一个字符输出流,可以写出为string
			writer.write(fileContent); // 将内容写到文件中
			writer.newLine(); // 写完一句后就换行
			writer.close();
			fw.close();
		} catch (Exception e) {
			System.out.println("写文件内容操作出错");
			e.printStackTrace();
		}
	}
	
	/**
	 * 写文件内容
	 * ,整体覆盖内容
	 * 
	 * @param filePathAndName
	 *            文件路径
	 * @param fileContent
	 *            文件内容
	 */
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

}
