package wenjianduqu;

/**
 * 公司实体类
 * 
 */
public class Company {
	 private String name;
	 private String tel;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "公司信息 [名称=" + name + ", 电话=" + tel + "]";
	}
}
