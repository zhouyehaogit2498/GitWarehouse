package ioCom;

import java.util.Scanner;

public class CompanyService {

	String companyPath = "D:/test/compny.txt";
	
	/**
	 * ��ӹ�˾��Ϣ
	 * @param scan
	 */
	public void add(Scanner scan) {
		System.out.println("������������ӵĹ�˾����:");
		String comName = scan.nextLine();
		System.out.println("������������ӵĹ�˾����:");
		String comTel = scan.nextLine();
		// �ѹ�˾��Ϣ���浽�ļ���
		String fileContext = comName + "," + comTel;
		// System.out.println("���������,Ҳ����Ҫ���浽�ļ��е�һ����˾����Ϣ: "+fileContext);
		FileUtil.writeFile(companyPath, fileContext);
		System.out.println("��ӳɹ�");
	}
	
	/**
	 * ��ѯ��˾��Ϣ
	 */
	public void query(){
		String companyContext = FileUtil.readFile(companyPath);
		System.out.println("--------�ļ�����:      "+companyContext);
		String companyContextArr[] = companyContext.split("&&");
		for (int i = 0; i < companyContextArr.length; i++) {
			//System.out.println("��˾��Ϣ:  "+companyContextArr[i]);
			String oneCompanyInfo = companyContextArr[i];
			String comInfoArr[] = oneCompanyInfo.split(",");
			String name = comInfoArr[0]; //����
			String tel = comInfoArr[1]; //�绰
			System.out.println("��˾����: "+name+" ��˾�绰��"+tel);
		}
	}
	

	/**
	 * �޸Ĺ�˾��Ϣ
	 * @param scan
	 */
	public void update(Scanner scan){
		System.out.println("������Ҫ�޸ĵĹ�˾����:");
		String comName = scan.nextLine();
		System.out.println("�������µĹ�˾����:");
		String newComName = scan.nextLine();
		System.out.println("�������µĹ�˾����:");
		String newComTel = scan.nextLine();
		// �µĹ�˾��Ϣ  �޸�
		String fileContext = newComName + "," + newComTel;
		
		modify(comName, fileContext);
		System.out.println("�޸ĳɹ�");
	}


	
	/**
	 * ɾ����˾��Ϣ
	 * @param scan
	 */
	public void del(Scanner scan){
		System.out.println("������Ҫɾ���Ĺ�˾����:");
		String comName = scan.nextLine();
		//   ɾ���ɵĹ�˾��Ϣ,Ҳ���ǰѾɵ���Ϣ���ɿ�
		String fileContext = "";
		
		modify(comName, fileContext);
		System.out.println("ɾ���ɹ�");
	}

	private void modify(String comName, String fileContext) {
		String newFileContext = null;	
		//aaa,12344566&&fff,123456789&&ccc,13400000000&&dddd,13500000000
		//���ļ��а����еĹ�˾��Ϣ��ȡ����   
		String companyContext = FileUtil.readFile(companyPath);
		//aaa,12344566&&bbb,13411111111&&ccc,13400000000&&dddd,13500000000
		System.out.println("��˾�ļ��е�����(���й�˾����һ��):"+companyContext);
		String companyContextArr[] = companyContext.split("&&");//��ÿ����˾��Ϣ�������Ϊ����,��ʱÿ���±��е�ֵ����һ����˾����Ϣ
		boolean isExsit = false;
		for (int i = 0; i < companyContextArr.length; i++) {//ѭ����˾����
			String oneCompanyInfo = companyContextArr[i]; //�õ�ÿһ����˾��Ϣ,��ʱ��˾��ÿ����Ϣ����,������
			/*
			 * aaa,12344566
				bbb,13411111111
				ccc,13400000000
				dddd,13500000000
			 */
			System.out.println("�������ÿһ����˾��Ϣ :   "+oneCompanyInfo);
			String comInfoArr[] = oneCompanyInfo.split(","); //��,�����Ϣ��Ϣ,�õ�������˾���ƺ͹�˾�绰
			String name = comInfoArr[0]; //��˾������
			if (comName.equals(name)) { //�жϲ�˾�Ĺ�˾���Ƿ�������Ҫ�޸ĵĹ�˾�Ƿ����
				newFileContext = companyContext.replace(oneCompanyInfo, fileContext); //���µ�ֵ(fileContext)�滻�ɵ�ֵ(oneCompanyInfo)  
				isExsit = true;
				break;
			}
		}
		if (isExsit == false) {
			System.out.println("Ҫ�޸ĵĹ�˾������,�����޸�");
			return;
		}
		
		FileUtil.writeFileFull(companyPath, newFileContext);
	}

}
