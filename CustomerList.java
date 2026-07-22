/*客户管理类，完成客户在数组这个结构当中增加，修改，删除，查询四类操作*/
public class CustomerList {
	private Customer[] customers; // 存储客户的数组
    private int total;            // 记录当前客户数量
    
    public CustomerList(int totalCustomer) {
        // TODO 1: 根据参数 totalCustomer 创建 Customer 数组，赋值给成员变量 customers
    	customers = new Customer[totalCustomer];
    	
        // TODO 2: 将 total 初始化为
    	total=0;
    }
    public boolean addCustomer(Customer customer) {
    	    // TODO 3: 判断数组是否已满，如果已满，打印提示并返回 false
    		if(total >= customers.length) {
    			System.out.println("数组已满，无法添加");
    			return false;
    		}else {
    	    
    	    // TODO 4: 将 customer 添加到 customers[total] 位置
    	    customers[total]= customer;
    	    // TODO 5: total 自增 1
    	    total=total+1;
    	    // TODO 6: 返回 true 表示添加成功
    	    return true;
    		}
    }
    //2
    public Customer[] getAllCustomers() {
        // 1. 创建一个 Customer 数组，长度为 total
        Customer[] custs = new Customer[total];
        
        // 2. 遍历 customers 数组，把前 total 个元素复制到 custs 中
        for (int i = 0; i < total; i++) {
            custs[i] = customers[i];
        }
        
        // 3. 返回新数组
        return custs;
        //return customers;
    }
    
    //3
    public Customer getCustomer(int index) {
        // 1. 检查索引是否在有效范围内
        if (index < 0 || index >= total) {
            // 索引无效，返回 null
            System.out.println("索引 " + index + " 无效，当前客户数量为 " + total);
            return null;
        }
        
        // 2. 索引有效，返回对应客户
        return customers[index];
    }
    
    //4
    public boolean replaceCustomer(int index, Customer cust) {
        // 1. 检查索引是否有效
        if (index < 0 || index >= total) {
            System.out.println("索引无效，无法修改");
            return false;
        }
        
        // 2. 检查该位置是否有客户
        if (customers[index] == null) {
            System.out.println("该位置没有客户信息");
            return false;
        }
        
        // 3. 执行替换操作
        customers[index] = cust;
        return true;
    }
    
    //5
    public boolean deleteCustomer(int index) {
        if (index < 0 || index >= total) {
            System.out.println("错误：索引无效，无法删除！");
            return false;
        }

        // 前移操作
        for (int i = index; i < total - 1; i++) {
            customers[i] = customers[i + 1];
        }

        // 尾部置空
        customers[total - 1] = null;
        total--;

        System.out.println("删除成功！");
        return true;
    }
    
    	public static void main(String[] args)	{
    		CustomerList customerList = new CustomerList(2);
    		Customer c1 = new Customer("张三", '男', 25, "13800138000", "zhangsan@email.com");
    		Customer c2 = new Customer("张四", '男', 6, "13800138034", "zhangsan@emaisdfl.com");
    		
    		customerList.addCustomer(c1);
    		customerList.addCustomer(c2);
    		
    		Customer[] custs=customerList.getAllCustomers();
    		for(Customer cust :custs) {
    			System.out.println(cust.getDetails());
    		}
    		System.out.println("==========================");
    		
    		Customer newCust1 =customerList.getCustomer(0);
    		System.out.println(newCust1.getDetails());
    		
    		Customer newCust2 =customerList.getCustomer(1);
    		System.out.println(newCust1.getDetails());
    		
    		Customer newCust3 =customerList.getCustomer(2);
    		System.out.println(newCust1.getDetails());
    		
    		
    		
    		
    	}
    		
    

    }


