package muiti2;
import java.util.*;
public class muiti2 {

	//�������ַ����ԼӺŻ����Ϊ��ǽ�����Ƭ
	public ArrayList<String> stringCut(String exp){
		ArrayList<String> Mydiv = new ArrayList<String>();//��Ƭ
		int len = exp.length();
		int start = 0,end = -1;
		int j=0;
		for (int i = 0;i < len;i++)
		{
			start = end+1;
			if( exp.substring(i,i+1).equals("+")||exp.substring(i,i+1).equals("-"))
			{

				if(exp.substring(i,i+1).equals("+"))
				{
					end = i;
					Mydiv.add(exp.substring(start,end));
//					System.out.printf("%s\n",Mydiv.get(j));
					j++;
				}
				else if(exp.substring(i,i+1).equals("-")&&i!=0)
				{
					end = i;
					Mydiv.add(exp.substring(start,end));
//					System.out.printf("%s\n",Mydiv.get(j));
					j++;
					end = end-1;
				}
			}
			else if (i==len-1)
			{
				Mydiv.add(exp.substring(start,len));
//				System.out.printf("%s\n",Mydiv.get(j));
			}
		}
		return Mydiv;
	}

	public static void main(String[] args)
	{
	String exp = new Polyinput().polynomialinput();
	ArrayList<String> Mydiv = new ArrayList<String>();//��Ƭ
	muiti2 s = new muiti2();
	
	String command = new Cominput().commandinput(exp);
	int choice = new Cominput().commandjudge(command,exp);
	
	ArrayList<simpCommand> valueGroup = new ArrayList<simpCommand>();//�������ȡֵ
	String MyVar1=null;//����ֵ����
	String MyVar2=null;//���󵼱���
	String ss1 = exp;
	String ss2 = "";
	
	double start = System.currentTimeMillis();
	
	switch(choice)
	{
		case 1:
			int num = new simpCommand().getBlankNum(command);
			valueGroup = new simpCommand().commandDealS(command);//���������ַ�������
			if (command.length()==9)
				System.out.println(exp);
			else{
				for (int i=0;i<num;i++){
					ArrayList<Polynomial> arr = new ArrayList<Polynomial>();
					MyVar1 = valueGroup.get(i).var;
					Mydiv = s.stringCut(ss1);
					for (int k = 0;k<Mydiv.size();k++)
						arr.add(new Polynomial().expression(MyVar1,Mydiv.get(k)));
						ss1 = new simplify().simplifyopt(arr,valueGroup,num,i);
				}
				ss1 = new Result().resultFormat(ss1);//��������������
				System.out.printf("��������%s\n",ss1);
				new ResultOutput().resultout(ss1);
			}
			break;
		case 2:
			ArrayList<Polynomial> arr2 = new ArrayList<Polynomial>();
			MyVar2 = command.substring(5, 6);
			Mydiv = s.stringCut(ss1);
			for (int k = 0;k < Mydiv.size();k++)
				arr2.add(new Polynomial().expression(MyVar2,Mydiv.get(k)));
			if (ss2 != "wrongpoly!"){
				ss2 = new dervative().derivativeopt(arr2,MyVar2);
				System.out.printf("�󵼽����%s\n", ss2);
				new ResultOutput().resultout(ss2);
			}
			break; 
		default:
			break;
	}
	
	double end = System.currentTimeMillis();
	System.out.println("��������ʱ�䣺" + (end-start)/1000 + "��");
	}
	
}
