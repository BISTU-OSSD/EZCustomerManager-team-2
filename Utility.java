
import java.util.*;

public class Utility {
    private static Scanner scanner = new Scanner(System.in);

	public static char readMenuSelection() {
        char c;
        for (; ; ) {
            String str = readKeyBoard(1, false);
            c = str.charAt(0);
            if (c != '1' && c != '2' && c != '3' && c != '4'&& c != '5') {
                System.out.print("选择错误，请重新输入：");
            } 
            else 
            	break;
        }
        return c;
    }

    public static void readReturn() {
        System.out.print("按回车键继续...");
        readKeyBoard(100, true);
    }

    public static int readInt() {
        int n;
        for (; ; ) {
            String str = readKeyBoard(2, false);
            try {
                n = Integer.parseInt(str);
                if(n > 0)
                	break;
                else
                	System.out.print("数字需要大于0，请重新输入：");
            } catch (NumberFormatException e) {
                System.out.print("数字输入错误，请重新输入：");
            }
        }
        return n;
    }
    
    public static double readDouble() {
        double d;
        for (; ; ) {
            String str = readKeyBoard(4, false);
            try {
                d = Double.parseDouble(str);
                break;
            } catch (NumberFormatException e) {
                System.out.print("浮点输入错误，请重新输入：");
            }
        }
        return d;
    }
    
  	public static String readString(int limit) {
        return readKeyBoard(limit, false);	//表示必须输入数据且字符串长度不大于limit
    }

    public static char readConfirmSelection() {
        char c;
        for (; ; ) {
            String str = readKeyBoard(1, false).toUpperCase();
            c = str.charAt(0);
            if (c == 'Y' || c == 'N') {
                break;
            } else {
                System.out.print("选择错误，请重新输入：");
            }
        }
        return c;
    }

    private static String readKeyBoard(int limit, boolean blankReturn) {
        String line = "";

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.length() == 0) {
                if (blankReturn) 
					return line;
                else 
					continue;
            }

            if (line.length() < 1 || line.length() > limit) {
                System.out.print("输入长度（不大于" + limit + "）错误，请重新输入：");
                continue;
            }
            break;
        }

        return line;
    }
}