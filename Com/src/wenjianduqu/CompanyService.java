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
	 * 添加公司信息
	 * @param scan
	 */
	public void add(Scanner scan) {
		while (true) {
			System.out.println("请输入你所添加的公司名称:");
			String comName = scan.nextLine();
			System.out.println("请输入你所添加的公司传真:");
			String comTel = scan.nextLine();
			Company comObj = new Company(); // 创建一个公司的实体类
			comObj.setName(comName);
			comObj.setTel(comTel);
			// comList.add(comObj);
			// 把公司信息保存到文件中
			String companyPath = "D:/test/compny.txt";
			String fileContext = comName + "," + comTel;
			// System.out.println("输入的内容,也就是要保存到文件中的一个公司的信息: "+fileContext);
			writeFile(companyPath, fileContext);
			System.out.println("添加成功");
		}
	}
	
	/**
	 * 查询公司信息
	 */
	public void query(){
		String companyPath = "D:/test/compny.txt";
		String companyContext = readFile(companyPath);
		String companyContextArr[] = companyContext.split("&&");
		for (int i = 0; i < companyContextArr.length; i++) {
			//System.out.println("公司信息:  "+companyContextArr[i]);
			String comInfoArr[] = companyContextArr[i].split(",");
			System.out.println("公司名称: "+comInfoArr[0]+" 公司电话："+comInfoArr[1]);
		}
		
	}

	public static void main(String[] args) {
		CompanyService cs = new CompanyService();
		cs.query();
	}

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
		} catch (Exception e) {
			System.out.println("写文件内容操作出错");
			e.printStackTrace();
		}
	}

}
