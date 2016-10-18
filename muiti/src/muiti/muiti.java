package muiti;
import java.util.*;

public class muiti {
	public class Polynomial//����ʽ��ÿ��С��
	{
		String stringCoef;//�ַ�ϵ��
		int intCoef;//����ϵ��
		int degree;//��
	}
	public class valueCommand{//��ֵҪ����ÿ�������С��
		String var;//����
		int val;//��ֵ
	}
	public static class varDegree{//������ʽ������ÿ��δ֪���Լ�������
		String var;
		int val;
	}
	
	//�Խ���ַ�����������Ľ�����ͬ�������ݴν������
	public static String degreeFormat(String str){
		String result="";
		ArrayList<varDegree> varQueue = new ArrayList<varDegree>();
		varDegree tmp = new varDegree();
		int i;
		int pos;//��Ǳ����ڶ����е�λ��
		char ch;
		str = str + "+";//���ַ��������ӼӺű��ڴ���
		for (i=0;i<str.length();i++){
			ch = str.charAt(i);
			if ((ch!='*')&&(ch!='+')&&((ch>'9')||(ch<'0'))){//�������֡��Ӻš��˺ţ���Ϊ��ĸ
				pos = checkVar(varQueue,ch);//�������Ƿ��Ѿ������ڶ�����
				if (pos == -1){//�����������ڶ�����
					varQueue = addQueue(varQueue,ch);//��ӱ����������
				}
				else{//�����ڶ��У���Ӧ�ݼ�һ
					tmp.val = varQueue.get(pos).val+1;
					tmp.var = String.valueOf(ch);
					varQueue.set(pos, tmp);
				}
			}
			else if(ch == '+'){//����ǼӺţ��Ե�ǰ�������ݽ������������ն���
				for (int j=0;j<varQueue.size();j++){
					result = result + varQueue.get(j).var + "^" + varQueue.get(j).val;
					if (j!=varQueue.size()-1)
						result = result + "*";
				}
				varQueue = emptyQueue(varQueue);//��ն���
				result = result + "+";
			}
			else if((ch=='*')&&(varQueue.size()==0)){//����ǼӺ��Ҳ����ַ���ĩβ�ļӺţ�ֱ�����
				result = result + String.valueOf(ch);
			}
			else if((ch>'0')&&(ch<='9'))//���������ֱ�����
				result = result + String.valueOf(ch);
		}
		result = result.substring(0,result.length()-1);//��ȥĩβ�ļӺ�
		return result;
	}
	
	//���������Ƿ����Ŀ���ַ���������ڷ����ַ��ڶ����е�λ�ã���������ڷ���-1
	public static int checkVar(ArrayList<varDegree> varQueue,char ch){
		int pos = -1;
		int i;
		for (i=0;i<varQueue.size();i++){
			if (ch == varQueue.get(i).var.charAt(0))
				pos = i;
		}
		return pos;
	}
	
	//����Ƭ�γɵ�ÿ��С����д����γɺ�������ݽṹ
	public  Polynomial expression(String MyVar,String Mydiv)//MyVar������Mydiv��Ƭ
	{
		Polynomial p = new Polynomial();
		p.stringCoef ="";
		p.intCoef = 1;
		p.degree = 0;//��ʼ��
		String coef = "";//ϵ���ַ���
		String queue = "";//�������
		int i;
		int intTemp=1;//����ϵ������ʱ����
		//������
		for (i = 0;i <Mydiv.length();i++)
		{
			if (Mydiv.substring(i,i+1).equals(MyVar))
				p.degree+=1;//����Ǳ������ݼ�һ
			else if (!Mydiv.substring(i,i+1).equals("*"))
				coef = coef+(Mydiv.substring(i,i+1));//������Ǳ���������ϵ���ַ���
		}
		//����ϵ��
		for (i=0;i<coef.length();i++){
			char ch = coef.charAt(i);
//			System.out.printf("��%d��\n",i);
			if (ch>'0'&&ch<'9'){//��������֣��������
				queue = addQueue(queue,ch);
				if((i==coef.length()-1)&&(queue!="")){//�������ϵ�����һλ�Ҷ��зǿգ������һ��Ϊ����
					intTemp *= Integer.parseInt(queue);//����ϵ��
					queue = emptyQueue(queue);//��ն���
				}
			}
			else//��������
			{
				if(queue!="")//���зǿ�ʱ
				{
					intTemp *= Integer.parseInt(queue);//����ϵ��
					queue = emptyQueue(queue);//��ն���
				}
			}
			p.intCoef = intTemp;
		}
		for(i=0;i<coef.length();i++)
		{
			char chr = coef.charAt(i);
			if(chr=='-')
			{
				p.intCoef = 0-p.intCoef;
			}
		}
		for(i=0;i<coef.length();i++){
			char chr=coef.charAt(i); //������������charֵ
		    if((chr<'0'||chr>'9')&& chr!= '*'&&chr!='-')
		    	p.stringCoef = p.stringCoef+String.valueOf(chr); 
		}
//		System.out.printf("degree��%d\n",p.degree);
//		System.out.printf("intCoef:%s\n",p.intCoef);
//		System.out.printf("stringCoef:%s\n",p.stringCoef);
		return p;
	}
	
	//��ն���
	public static String emptyQueue(String queue){
		queue = "";
		return queue;
	}
	
	//��ն���
	public static ArrayList<varDegree> emptyQueue(ArrayList<varDegree> varQueue){
		ArrayList<varDegree> n = new ArrayList<varDegree>();
		varQueue = n;
		return varQueue;
	}
	
	//������м���Ŀ���ַ�
	public static String addQueue(String queue,char ch){
		queue=queue+String.valueOf(ch);
		return queue;
	}
	
	//������м���Ŀ���ַ�
	public static ArrayList<varDegree> addQueue(ArrayList<varDegree> varQueue,char ch){
		varDegree tmp = new varDegree();
		tmp.var = String.valueOf(ch);
		tmp.val = 1;
		varQueue.add(tmp);
		return varQueue;
	}
	
	//��������ֵ������ʽ
	public String simplify(ArrayList<Polynomial> arr,ArrayList<valueCommand> valueGroup,int num,int k)//arr����ʽ�飬MyValueֵ
	{
		ArrayList<Integer> arrCoef =new ArrayList<Integer>();//ϵ������
		String s = "";
			for(int i = 0;i < arr.size();i++)
			{
				arrCoef.add(arr.get(i).intCoef);
				for(int j = 0;j< arr.get(i).degree;j++){
					arrCoef.set(i, arrCoef.get(i)*valueGroup.get(k).val);//���㻯��������ϵ��
				}
				//�����ĸϵ��������Ҫ����������У�ֱ�����
				if (!(containString(Integer.toString(arrCoef.get(i)),valueGroup))){
					if(i!= arr.size()-1&&arr.get(i+1).intCoef>0)
						s = s + Integer.toString(arrCoef.get(i))+arr.get(i).stringCoef+"+";
					else
						s = s + Integer.toString(arrCoef.get(i))+arr.get(i).stringCoef;//��ʷ�������ⲻ�ܴ��˺�
				}
			}
		return s;
	}
	
	//�ж�Ŀ���ַ��Ƿ�����ڻ����������
	public boolean containString(String str,ArrayList<valueCommand> valueGroup){//!!!!!!!!!!!!!!!!
		boolean contain = false;
		int i;
		for (i=0;i<valueGroup.size();i++){
			if (str.equals(valueGroup.get(i).var)){
				contain = true;
				break;
			}
		}
		return contain;
	}
	
	//��Ŀ����ʽ������
	public String derivative(ArrayList<Polynomial> arr,String var){
		int i;
		String result="";
		Polynomial p = new Polynomial();
		p.degree = 0;
		p.stringCoef ="";
		p.intCoef = 1;
		for (i=0;i<arr.size();i++){
			p.intCoef = arr.get(i).intCoef * arr.get(i).degree;//�����󵼽��
			if (p.intCoef != 0)
				p.stringCoef = arr.get(i).stringCoef;//��ĸ�󵼽��
			else
				p.stringCoef = "";
			p.degree = arr.get(i).degree - 1;//���󵼽��
			if(p.degree == -1)
				p.degree = 0;
			//����󵼽��
			result = result + Integer.toString(p.intCoef);
			if (p.stringCoef.equals(""));
			else{
				for (int k=0;k<p.stringCoef.length();k++){
					if (k!=p.stringCoef.length()-1)
						result = result + "*" + p.stringCoef.substring(k, k+1);
					else
						result = result + "*" + p.stringCoef.substring(k);
				}
			}
			if (p.degree != 0){
				for (int k=0;k<p.degree;k++){
					result = result + "*" + var;
				}
			}
				//System.out.print(var+"^"+p.degree);
//				result = result + var+"^"+Integer.toString(p.degree);
			if (i<arr.size()-1&&arr.get(i+1).intCoef>0)
				result = result + "+";
		}
		//System.out.println("\n");
		return result;
	}
	
	//�������ַ����ԼӺŻ����Ϊ��ǽ�����Ƭ
	public ArrayList<String> stringCut(String exp){
		ArrayList<String> Mydiv = new ArrayList<String>();//��Ƭ
		int len = exp.length();
		int start = 0,end = -1;
		int j = 0;
		for (int i = 0;i < len;i++)
		{
			start = end+1;
			if( exp.substring(i,i+1).equals("+")||exp.substring(i,i+1).equals("-"))
			{

				if(exp.substring(i,i+1).equals("+"))
				{
					end = i;
					Mydiv.add(exp.substring(start,end));
					System.out.printf("%s\n",Mydiv.get(j));
					j++;
				}
				else if(exp.substring(i,i+1).equals("-")&&i!=0)
				{
					end = i;
					Mydiv.add(exp.substring(start,end));
					System.out.printf("%s\n",Mydiv.get(j));
					j++;
					end = end-1;
				}
			}
			else if (i==len-1)
			{
				Mydiv.add(exp.substring(start,len));
				System.out.printf("%s\n",Mydiv.get(j));
			}
		}
		return Mydiv;
	}
	
	//�Ի���������д�����ȡ����������Լ���Ӧ�ı���ֵ
	public ArrayList<valueCommand> commandDeal(String command){
		ArrayList<valueCommand> valueGroup = new ArrayList<valueCommand>();
		int i;
		int blankNum=0;//�ո����븳ֵ���������
		muiti s = new muiti();
		ArrayList<Integer> blankPos = new ArrayList<Integer>();//��ǿո���command�ַ����е�λ��
		blankNum = s.getBlankNum(command);
		blankPos = s.getBlankPos(command);
		for (i=0;i<blankNum;i++){
			valueCommand tmp = new valueCommand();
			tmp.var = command.substring(blankPos.get(i)+1,blankPos.get(i)+2);
			//�Ⱥ�����һ���ո�֮���������Ƭ
			if (i==blankNum-1){//command�����һ�鸳ֵ���֮��û�еȺ�
				tmp.val = Integer.parseInt(command.substring(blankPos.get(i)+3));
			}
			else{//�������ʹ�õ�ǰ�Ⱥ�����һ���ո���н�ȡ
				tmp.val = Integer.parseInt(command.substring(blankPos.get(i)+3, blankPos.get(i+1)));
				}
			valueGroup.add(tmp);
		}
		return valueGroup;
	}
	
	//���Ŀ���ַ����еĿո����
	public int getBlankNum(String str){
		int blankNum=0;
		for (int i=0;i<str.length();i++){
			if (str.charAt(i)==32){//�жϸ�ֵ�����ĸ���
				blankNum++;
			}
		}
		return blankNum;
	}
	
	//���Ŀ���ַ����пո��λ������
	public ArrayList<Integer> getBlankPos(String str){
		ArrayList<Integer> blankPos = new ArrayList<Integer>();
		for (int i=0;i<str.length();i++){
			if (str.charAt(i)==32){//�жϸ�ֵ�����ĸ���
				blankPos.add(i);
			}
		}
		return blankPos;
	}
	
	//�Խ���ַ������кϲ���������Ϊ������ʽ
	public static String resultFormat(String str){
		String result="";
		String queue="";
		int i;
		char ch;
		str = str + "+";//ĩβ��ӷ����ַ���
		for (i=0;i<str.length();i++){
			ch = str.charAt(i);
			if (ch>='0' && ch<='9')//�����־ͼ������
				queue = addQueue(queue,ch);
			else if((ch<'0' || ch>'9')&&(ch != '+')){//�������ֻ�Ӻţ���Ϊ��ĸ
				result = result + queue + "*" + str.substring(i, i+1);//����������ݳ˺ź���ĸ
				queue = emptyQueue(queue);//��ն���
			}
			else if (ch == '+'){
				if (queue == "" && i!=str.length()-1){
					result = result + "+";
				}
				else{
					if (i != str.length()-1){
						result = result + queue + "+";//�����������
						queue = emptyQueue(queue);//��ն���
					}
					else{
						result = result + queue ;//�����������
						queue = emptyQueue(queue);//��ն���
					}
				}
				}
		}
		return result;
	}
	
	//����������е�0
	public static String zeroClear(String str){
		String result="";
		int i;
		for (i=0;i<str.length();i++){
			if (str.substring(i, i+1).equals("0"));
			else
				result = result + str.substring(i,i+1);
		}
		if (result.substring(0, 1).equals("+"))//��ȥ����ʼ�ļӺ�
			result = result.substring(1);
		else if(result.substring(result.length()-1).equals("+"))
			result = result.substring(0, str.length()-2);//��ȥ���һ���Ӻ�
		return result;
	}

	public static void main(String[] args)
	{
	System.out.println("Please print in the poly:");
	Scanner in = new Scanner(System.in);
	String exp = in.nextLine();//���ʽ
	System.out.printf("��������Ϊ��%s\n",exp);//���
	ArrayList<String> Mydiv = new ArrayList<String>();//��Ƭ
	muiti s = new muiti();

	String command = in.nextLine();//����
	int choice=0;
	String MyVar1=null;//����ֵ����
	String MyVar2=null;//���󵼱���
	ArrayList<valueCommand> valueGroup = new ArrayList<valueCommand>();//�������ȡֵ
	if(command.substring(0, 1).equals("!"))
	{
		if(command.substring(1, 4).equals("d/d"))
		{
			choice = 2;//������
			MyVar2 = command.substring(5, 6);
			for (int j=0;j<command.length();j++){
				if (MyVar2.equals(exp.substring(j,j+1))&&(j!=command.length()-1));
				else if(MyVar2.equals(exp.substring(j))&&(j==command.length()-1));
				else{
					System.out.println("Error, no variable");
					choice = 0;
					break;
				}
			}
		}
		else if(command.substring(1, 9).equals("simplify"))
			choice = 1;//��������
		else
			System.out.printf("Error!Wrong Command!");
	}
	else
		System.out.printf("Wrong Command!");
	String ss1 = exp;
	String ss2 = "";
	switch(choice)
	{
		case 1:
			int num = s.getBlankNum(command);
			valueGroup = s.commandDeal(command);//���������ַ�������
			for (int i=0;i<num;i++){
				ArrayList<Polynomial> arr = new ArrayList<Polynomial>();
				MyVar1 = valueGroup.get(i).var;
				Mydiv = s.stringCut(ss1);
				for (int k = 0;k<Mydiv.size();k++)
					arr.add(s.expression(MyVar1,Mydiv.get(k)));
				ss1 = s.simplify(arr,valueGroup,num,i);
			}
			ss1 = resultFormat(ss1);//��������������
			System.out.printf("%s\n",ss1);
			ss1 = degreeFormat(ss1);//��������һ������
			System.out.printf("�Ľ������룺%s\n",ss1);
			break;
		case 2:
			muiti s1 = new muiti();
			ArrayList<Polynomial> arr2 = new ArrayList<Polynomial>();
			Mydiv = s.stringCut(ss1);
			for (int k = 0;k < Mydiv.size();k++)
				arr2.add(s1.expression(MyVar2,Mydiv.get(k)));
			ss2 = s1.derivative(arr2,MyVar2);
			ss2 = zeroClear(ss2);//����������е�0
			System.out.printf("%s\n", ss2);
			ss2 = degreeFormat(ss2);//��������һ������
			System.out.printf("�Ľ����룺%s\n",ss2);
			break; 
		default:
			break;
	}
	in.close();
	}
}
