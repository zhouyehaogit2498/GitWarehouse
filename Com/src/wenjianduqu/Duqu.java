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
		StringBuffer fileContentSb = new StringBuffer();// 文件内容
		try {
			File f = new File(filePath);
			if (f.isFile() && f.exists()) {
				InputStream fis = new FileInputStream(f); // 字节流
				// FileInputStream fis = new FileInputStream(f); //字节流
				InputStreamReader read = new InputStreamReader(fis, "UTF-8"); // 字符流
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
			System.out.println("读取文件内容操作出错");
			e.printStackTrace();
		}
		return fileContentSb.toString();
	}
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
		} catch (Exception e) {
			System.out.println("写文件内容操作出错");
			e.printStackTrace();
		}
	}
}


