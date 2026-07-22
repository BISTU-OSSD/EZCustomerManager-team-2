public class CustomerView{
	private CustomerList customerList = new CustomerList(14);
	//显示主菜单
	public void listMainMenu() {
		boolean flag=true;
		do {
			System.out.println("-----------------客户信息管理软件-----------------");
			System.out.println("                  1 添加客户");
			System.out.println("                  2 修改客户");
			System.out.println("                  3 删除客户");
			System.out.println("                  4 客户列表");
			System.out.println("                  5 退  出");
			System.out.print("                  请输入(1-5)：");
			
			char cMenu=Utility.readMenuSelection();
			switch(cMenu) {
			case'1':
				//进入添加客户方法
				addNewCustomer();
				break;
			case'2':
				//进入修改客户方法
				upOldCustomer();
				break;
			case'3':
				//进入删除客户方法
				delCustomer();
				break;
			case'4':
				//客户列表
				// 获取所有客户对象
			    Customer[] custs = customerList.getAllCustomers();
			    // 打印表头
			    System.out.println("---------------------客户列表----------------------");
			    System.out.println("编号\t姓名\t性别\t年龄\t电话\t\t邮箱");
			    // 遍历数组并打印每个客户的信息
			    for (int i = 0; i < custs.length; i++) {
			        // 编号为索引+1，然后调用Customer对象的getDetails方法
			        System.out.println((i + 1) + "\t" + custs[i].getDetails());
			    }
			    System.out.println("---------------------客户列表结束------------------");
			    
				break;
			case'5':
				//退出软件，进行二次确认，不确认则返回重新显示菜单
				System.out.println("                  确认是否退出软件Y/N");
				char cConfirm=Utility.readConfirmSelection();
				if(cConfirm!='Y') {
					//让循环标志为false
					flag=false;
					//重新显示菜单
				}
				break;
			}
		}while(flag);
	}
	
	//1add
	public void addNewCustomer() {
		System.out.println("请输入客户姓名");
		String name=Utility.readString(4);
		System.out.println("请输入客户性别");
		char gender = Utility.readString(1).charAt(0);
		System.out.println("请输入客户年龄");
		int age = Utility.readInt();
		System.out.println("请输入客户手机");
		String phone=Utility.readString(11);
		System.out.println("请输入客户邮箱");
		String email= Utility.readString(20);
		
		Customer cust=new Customer(name,gender,age,phone,email);
		boolean addFlag=customerList.addCustomer(cust);
		if(addFlag){
			System.out.println("添加成功");
		}
		
		
	}
	
	//2update
	public void upOldCustomer() {
		System.out.println("请输入客户编号");
		int index=Utility.readInt();
		//找到这个位置的客户，如果不为空再键盘输入5个属性信息，否则不予运行
		Customer oldCust=customerList.getCustomer(index-1);
		if(oldCust!=null) {
		System.out.println("请输入客户姓名");
		String name=Utility.readString(4);
		System.out.println("请输入客户性别");
		char gender = Utility.readString(1).charAt(0);
		System.out.println("请输入客户年龄");
		int age = Utility.readInt();
		System.out.println("请输入客户手机");
		String phone=Utility.readString(11);
		System.out.println("请输入客户邮箱");
		String email= Utility.readString(20);
		
		Customer newCust=new Customer(name,gender,age,phone,email);	
		boolean updFlag=customerList.replaceCustomer(index-1, newCust);
		if(updFlag) {
			System.out.println("修改成功");
		}
		}else {
			System.out.println("数据不存在");
		}
		
	}
	
	//3
	public void delCustomer() {
		System.out.println("请输入客户编号");
		int index =Utility.readInt();
		
		Customer cust =customerList.getCustomer(index-1);
		if(cust!=null) {
			System.out.println("确定删除此客户吗？");
			char cConfirm = Utility.readConfirmSelection();
			if(cConfirm=='Y') {
				boolean deFlag =customerList.deleteCustomer(index-1);
				if(deFlag)
				{
					System.out.println("删除成功");
				}
			}
		}
	}
	public static void main(String[] args) {
		
		CustomerView customerView =new CustomerView();
		customerView.listMainMenu();
	}
}