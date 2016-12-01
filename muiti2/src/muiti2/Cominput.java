package muiti2;

import java.util.ArrayList;
import java.util.Scanner;


public class Cominput {
	public String commandinput(String exp)
	{
		System.out.println("Please print in the Command:");
		Scanner in = new Scanner(System.in);
		String command = in.nextLine();//命令
		System.out.printf("输入内容为：%s\n",command);//输出
		in.close();
		return command;
	}
	public int commandjudge(String command,String exp)
	{
		int choice = 0;
		String MyVar1=null;//待赋值变量
		String MyVar2=null;//待求导变量
		if(command.substring(0, 1).equals("!"))
		{
			if(command.substring(1, 4).equals("d/d"))
			{
				MyVar2 = command.substring(5, 6);
				for (int j=1;j<exp.length();j++){
					if (MyVar2.equals(exp.substring(j,j+1)))
					{
						choice = 2;//求导命令
					}
				}
				if(choice!=2)
				{
					System.out.println("Error, no variable");
					choice = 0;
				}
			}
			else if(command.substring(1, 9).equals("simplify"))
				choice = 1;//化简命令
			else
				System.out.printf("Error!Wrong Command!");
		}
		else
			System.out.printf("Error!Wrong Command!");
		return choice;
	}
}
