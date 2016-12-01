package muiti2;

import java.util.Scanner;

public class Polyinput {
	public String polynomialinput()
	{
		System.out.println("Please print in the poly:");
		Scanner in = new Scanner(System.in);
		String exp = in.nextLine();//表达式
		System.out.printf("输入内容为：%s\n",exp);//输出
		return exp;
	}
}
