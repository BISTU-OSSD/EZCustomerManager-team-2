import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class HttpServerHandler {

    private static CustomerList customerList = new CustomerList(14);

    public static void main(String[] args) throws IOException {
        //新增：初始化 CustomerList 并加载历史数据
        customerList = new CustomerList(14);
        // 创建 HTTP 服务器，监听 8080 端口
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // 处理根路径：返回 HTML 页面
        server.createContext("/", new HtmlHandler());

        // 处理 API 请求
        server.createContext("/api/add", new AddHandler());
        server.createContext("/api/list", new ListHandler());
        server.createContext("/api/delete", new DeleteHandler());
        server.createContext("/api/update", new UpdateHandler());

        server.setExecutor(null);
        server.start();
        System.out.println("服务器已启动: http://localhost:8080");
        System.out.println("请在浏览器打开上面的地址");
    }

    // 返回 HTML 页面
    static class HtmlHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String html = """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <title>客户管理系统</title>
                    <style>
                        body { font-family: Arial; padding: 20px; }
                        .container { max-width: 900px; margin: 0 auto; }
                        .menu { margin: 20px 0; }
                        .menu button { padding: 10px 20px; margin: 5px; }
                        .form-group { margin: 10px 0; }
                        .form-group label { display: inline-block; width: 80px; }
                        .form-group input { padding: 5px; width: 200px; }
                        table { border-collapse: collapse; width: 100%; margin-top: 20px; }
                        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
                        th { background: #4CAF50; color: white; }
                        .hidden { display: none; }
                        #result { margin-top: 20px; padding: 15px; background: #f0f0f0; border-radius: 5px; }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <h1>📋 客户信息管理系统</h1>
                        
                        <div class="menu">
                            <button onclick="showAdd()">➕ 添加客户</button>
                            <button onclick="listCustomers()">📋 客户列表</button>
                            <button onclick="showDelete()">🗑️ 删除客户</button>
                            <button onclick="showUpdate()">✏️ 修改客户</button>
                        </div>
                        
                        <!-- 添加客户 -->
                        <div id="addForm" class="hidden">
                            <h3>添加客户</h3>
                            <div class="form-group"><label>姓名：</label><input id="addName" placeholder="张三"></div>
                            <div class="form-group"><label>性别：</label><input id="addGender" placeholder="男/女"></div>
                            <div class="form-group"><label>年龄：</label><input id="addAge" type="number" placeholder="25"></div>
                            <div class="form-group"><label>电话：</label><input id="addPhone" placeholder="13800138000"></div>
                            <div class="form-group"><label>邮箱：</label><input id="addEmail" placeholder="xxx@email.com"></div>
                            <button onclick="addCustomer()">提交添加</button>
                            <button onclick="hideAll()">取消</button>
                        </div>
                        
                        <!-- 删除客户 -->
                        <div id="deleteForm" class="hidden">
                            <h3>删除客户</h3>
                            <div class="form-group"><label>编号：</label><input id="deleteIndex" type="number" placeholder="1"></div>
                            <button onclick="deleteCustomer()">确认删除</button>
                            <button onclick="hideAll()">取消</button>
                        </div>
                        
                        <!-- 修改客户 -->
                        <div id="updateForm" class="hidden">
                            <h3>修改客户</h3>
                            <div class="form-group"><label>编号：</label><input id="updateIndex" type="number" placeholder="1"></div>
                            <div class="form-group"><label>新姓名：</label><input id="updateName" placeholder="李四"></div>
                            <div class="form-group"><label>新性别：</label><input id="updateGender" placeholder="男/女"></div>
                            <div class="form-group"><label>新年龄：</label><input id="updateAge" type="number" placeholder="30"></div>
                            <div class="form-group"><label>新电话：</label><input id="updatePhone" placeholder="13900139000"></div>
                            <div class="form-group"><label>新邮箱：</label><input id="updateEmail" placeholder="lisi@email.com"></div>
                            <button onclick="updateCustomer()">提交修改</button>
                            <button onclick="hideAll()">取消</button>
                        </div>
                        
                        <div id="result"></div>
                    </div>
                    
                    <script>
                        function showAdd() { hideAll(); document.getElementById('addForm').className = ''; }
                        function showDelete() { hideAll(); document.getElementById('deleteForm').className = ''; }
                        function showUpdate() { hideAll(); document.getElementById('updateForm').className = ''; }
                        function hideAll() {
                            document.getElementById('addForm').className = 'hidden';
                            document.getElementById('deleteForm').className = 'hidden';
                            document.getElementById('updateForm').className = 'hidden';
                        }
                        
                        async function addCustomer() {
                            const data = {
                                name: document.getElementById('addName').value,
                                gender: document.getElementById('addGender').value.charAt(0),
                                age: parseInt(document.getElementById('addAge').value),
                                phone: document.getElementById('addPhone').value,
                                email: document.getElementById('addEmail').value
                            };
                            const resp = await fetch('/api/add', {
                                method: 'POST',
                                headers: {'Content-Type': 'application/json'},
                                body: JSON.stringify(data)
                            });
                            const result = await resp.text();
                            document.getElementById('result').innerHTML = '<strong>结果：</strong>' + result;
                            hideAll();
                        }
                        
                        async function listCustomers() {
                            const resp = await fetch('/api/list');
                            const html = await resp.text();
                            document.getElementById('result').innerHTML = html;
                        }
                        
                        async function deleteCustomer() {
                            const index = document.getElementById('deleteIndex').value;
                            const resp = await fetch('/api/delete?index=' + index);
                            const result = await resp.text();
                            document.getElementById('result').innerHTML = '<strong>结果：</strong>' + result;
                            hideAll();
                        }
                        
                        async function updateCustomer() {
                            const data = {
                                index: parseInt(document.getElementById('updateIndex').value),
                                name: document.getElementById('updateName').value,
                                gender: document.getElementById('updateGender').value.charAt(0),
                                age: parseInt(document.getElementById('updateAge').value),
                                phone: document.getElementById('updatePhone').value,
                                email: document.getElementById('updateEmail').value
                            };
                            const resp = await fetch('/api/update', {
                                method: 'POST',
                                headers: {'Content-Type': 'application/json'},
                                body: JSON.stringify(data)
                            });
                            const result = await resp.text();
                            document.getElementById('result').innerHTML = '<strong>结果：</strong>' + result;
                            hideAll();
                        }
                    </script>
                </body>
                </html>
            """;

            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            byte[] bytes = html.getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(200, bytes.length);
            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
        }
    }

    // 添加客户
    static class AddHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            // 简单解析 JSON（不引入第三方库，手工提取）
            String name = extract(body, "name");
            char gender = extract(body, "gender").isEmpty() ? '男' : extract(body, "gender").charAt(0);
            int age = Integer.parseInt(extract(body, "age"));
            String phone = extract(body, "phone");
            String email = extract(body, "email");

            Customer cust = new Customer(name, gender, age, phone, email);
            boolean success = customerList.addCustomer(cust);
            String response = success ? "✅ 添加成功！" : "❌ 添加失败（数组已满）";
            sendResponse(exchange, response);
        }
    }

    // 客户列表
    static class ListHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            Customer[] custs = customerList.getAllCustomers();
            StringBuilder sb = new StringBuilder();
            sb.append("<h3>📋 客户列表</h3>");
            sb.append("<table><tr><th>编号</th><th>姓名</th><th>性别</th><th>年龄</th><th>电话</th><th>邮箱</th></tr>");
            for (int i = 0; i < custs.length; i++) {
                Customer c = custs[i];
                sb.append("<tr><td>").append(i+1).append("</td>");
                sb.append("<td>").append(c.getName()).append("</td>");
                sb.append("<td>").append(c.getGender()).append("</td>");
                sb.append("<td>").append(c.getAge()).append("</td>");
                sb.append("<td>").append(c.getPhone()).append("</td>");
                sb.append("<td>").append(c.getEmail()).append("</td></tr>");
            }
            sb.append("</table>");
            sb.append("<p>共 ").append(custs.length).append(" 位客户</p>");
            sendResponse(exchange, sb.toString());
        }
    }

    // 删除客户
    static class DeleteHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String query = exchange.getRequestURI().getQuery();
            String indexStr = query.split("=")[1];
            int index = Integer.parseInt(indexStr) - 1;
            boolean success = customerList.deleteCustomer(index);
            String response = success ? "✅ 删除成功！" : "❌ 删除失败（编号无效）";
            sendResponse(exchange, response);
        }
    }

    // 修改客户
    static class UpdateHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            int index = Integer.parseInt(extract(body, "index")) - 1;
            String name = extract(body, "name");
            char gender = extract(body, "gender").isEmpty() ? '男' : extract(body, "gender").charAt(0);
            int age = Integer.parseInt(extract(body, "age"));
            String phone = extract(body, "phone");
            String email = extract(body, "email");

            Customer cust = new Customer(name, gender, age, phone, email);
            boolean success = customerList.replaceCustomer(index, cust);
            String response = success ? "✅ 修改成功！" : "❌ 修改失败（编号无效）";
            sendResponse(exchange, response);
        }
    }

    // 辅助：简单提取 JSON 中的值
    private static String extract(String json, String key) {
        String search = "\"" + key + "\":\"";
        int start = json.indexOf(search);
        if (start == -1) {
            search = "\"" + key + "\":";
            start = json.indexOf(search);
            if (start == -1) return "";
            start += search.length();
            int end = json.indexOf(",", start);
            if (end == -1) end = json.indexOf("}", start);
            return json.substring(start, end).trim();
        }
        start += search.length();
        int end = json.indexOf("\"", start);
        return json.substring(start, end);
    }

    private static void sendResponse(HttpExchange exchange, String response) throws IOException {
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(200, bytes.length);
        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }
}