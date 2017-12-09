package ioCom;

import java.util.Scanner;

public class CompanyService {

	String companyPath = "D:/test/compny.txt";
	
	/**
	 * 添加公司信息
	 * @param scan
	 */
	public void add(Scanner scan) {
		System.out.println("请输入你所添加的公司名称:");
		String comName = scan.nextLine();
		System.out.println("请输入你所添加的公司传真:");
		String comTel = scan.nextLine();
		// 把公司信息保存到文件中
		String fileContext = comName + "," + comTel;
		// System.out.println("输入的内容,也就是要保存到文件中的一个公司的信息: "+fileContext);
		FileUtil.writeFile(companyPath, fileContext);
		System.out.println("添加成功");
	}
	
	/**
	 * 查询公司信息
	 */
	public void query(){
		String companyContext = FileUtil.readFile(companyPath);
		System.out.println("--------文件内容:      "+companyContext);
		String companyContextArr[] = companyContext.split("&&");
		for (int i = 0; i < companyContextArr.length; i++) {
			//System.out.println("公司信息:  "+companyContextArr[i]);
			String oneCompanyInfo = companyContextArr[i];
			String comInfoArr[] = oneCompanyInfo.split(",");
			String name = comInfoArr[0]; //名称
			String tel = comInfoArr[1]; //电话
			System.out.println("公司名称: "+name+" 公司电话："+tel);
		}
	}
	

	/**
	 * 修改公司信息
	 * @param scan
	 */
	public void update(Scanner scan){
		System.out.println("请输入要修改的公司名称:");
		String comName = scan.nextLine();
		System.out.println("请输入新的公司名称:");
		String newComName = scan.nextLine();
		System.out.println("请输入新的公司传真:");
		String newComTel = scan.nextLine();
		// 新的公司信息  修改
		String fileContext = newComName + "," + newComTel;
		
		modify(comName, fileContext);
		System.out.println("修改成功");
	}


	
	/**
	 * 删除公司信息
	 * @param scan
	 */
	public void del(Scanner scan){
		System.out.println("请输入要删除的公司名称:");
		String comName = scan.nextLine();
		//   删除旧的公司信息,也就是把旧的信息换成空
		String fileContext = "";
		
		modify(comName, fileContext);
		System.out.println("删除成功");
	}

	private void modify(String comName, String fileContext) {
		String newFileContext = null;	
		//aaa,12344566&&fff,123456789&&ccc,13400000000&&dddd,13500000000
		//到文件中把所有的公司信息读取出来   
		String companyContext = FileUtil.readFile(companyPath);
		//aaa,12344566&&bbb,13411111111&&ccc,13400000000&&dddd,13500000000
		System.out.println("公司文件中的内容(所有公司都在一起):"+companyContext);
		String companyContextArr[] = companyContext.split("&&");//把每个公司信息拆出来成为数组,这时每个下标中的值就是一个公司中信息
		boolean isExsit = false;
		for (int i = 0; i < companyContextArr.length; i++) {//循环公司数组
			String oneCompanyInfo = companyContextArr[i]; //拿到每一个公司信息,这时公司是每个信息是用,隔开的
			/*
			 * aaa,12344566
				bbb,13411111111
				ccc,13400000000
				dddd,13500000000
			 */
			System.out.println("拆出来的每一个公司信息 :   "+oneCompanyInfo);
			String comInfoArr[] = oneCompanyInfo.split(","); //用,拆分信息信息,得到单个公司名称和公司电话
			String name = comInfoArr[0]; //公司的名称
			if (comName.equals(name)) { //判断拆公司的公司名是否和输入的要修改的公司是否相等
				newFileContext = companyContext.replace(oneCompanyInfo, fileContext); //用新的值(fileContext)替换旧的值(oneCompanyInfo)  
				isExsit = true;
				break;
			}
		}
		if (isExsit == false) {
			System.out.println("要修改的公司不存在,不能修改");
			return;
		}
		
		FileUtil.writeFileFull(companyPath, newFileContext);
	}

}
