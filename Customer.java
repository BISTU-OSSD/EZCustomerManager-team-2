
public class Customer {
	
	// 1. 属性（成员变量）
    private String name;   // 客户姓名
    private char gender;   // 客户性别
    private int age;       // 客户年龄
    private String phone;  // 客户电话
    private String email;  // 客户邮箱
    
    //全参数的构造方法
    public Customer(String name, char gender, int age, String phone, String email) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.phone = phone;
		this.email = email;
	}
    
    //get和set
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
	    // 可以加一个简单的校验
	    if (age >= 0 && age <= 150) {
	        this.age = age;
	    } else {
	        System.out.println("年龄不合法！");
	        // 或者抛异常
	    }
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    
	//显示客户的具体信息
	public String getDetails() {
		return name+ "\t" + gender + "\t" + age + "\t" + phone + "\t" + email;
	}
	
	
	
	
	public static void main(String[] args) {
		Customer customer = new Customer("张三", '男', 25, "13800138000", "zhangsan@email.com");
		System.out.println("========== customer 的信息 ==========");
        System.out.println("姓名：" + customer.getName());
        System.out.println("性别：" + customer.getGender());
        System.out.println("年龄：" + customer.getAge());
        System.out.println("电话：" + customer.getPhone());
        System.out.println("邮箱：" + customer.getEmail());
        System.out.println("===================================");
        
        customer.setAge(200);
        System.out.println(customer.getAge());
        
       System.out.println(customer.getDetails());
	}
	

}
