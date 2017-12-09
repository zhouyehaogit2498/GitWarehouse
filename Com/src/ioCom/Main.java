package ioCom;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		CompanyService cs = new CompanyService();
		while (true) {
			System.out.println("1公司添加      2公司查询     3修改公司   4删除公司");
			Scanner c = new Scanner(System.in);
			String str = c.nextLine();
			if ("1".equals(str)) {
				cs.add(c);
			} else if ("2".equals(str)) {
				cs.query();
			} else if ("3".equals(str)) {
				cs.update(c);
			} else if ("4".equals(str)) {
				cs.del(c);
			}
		}
	}

}
