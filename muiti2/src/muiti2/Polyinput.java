package muiti2;

import java.util.Scanner;

public class Polyinput {
	public String polynomialinput()
	{
		System.out.println("Please print in the poly:");
		Scanner in = new Scanner(System.in);
		String exp = in.nextLine();//���ʽ
		System.out.printf("��������Ϊ��%s\n",exp);//���
		return exp;
	}
}
