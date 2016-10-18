package muiti;
import java.util.*;

public class muiti {
	public class Polynomial//多项式的每个小项
	{
		String stringCoef;//字符系数
		int intCoef;//整数系数
		int degree;//幂
	}
	public class valueCommand{//赋值要求中每个命令的小项
		String var;//变量
		int val;//赋值
	}
	public static class varDegree{//结果表达式化简中每个未知量以及它的幂
		String var;
		int val;
	}
	
	//对结果字符串进行输出改进，相同变量以幂次进行输出
	public static String degreeFormat(String str){
		String result="";
		ArrayList<varDegree> varQueue = new ArrayList<varDegree>();
		varDegree tmp = new varDegree();
		int i;
		int pos;//标记变量在队列中的位置
		char ch;
		str = str + "+";//在字符串最后添加加号便于处理
		for (i=0;i<str.length();i++){
			ch = str.charAt(i);
			if ((ch!='*')&&(ch!='+')&&((ch>'9')||(ch<'0'))){//不是数字、加号、乘号，即为字母
				pos = checkVar(varQueue,ch);//检查变量是否已经存在于队列中
				if (pos == -1){//变量不存在于队列中
					varQueue = addQueue(varQueue,ch);//添加变量进入队列
				}
				else{//变量在队列，相应幂加一
					tmp.val = varQueue.get(pos).val+1;
					tmp.var = String.valueOf(ch);
					varQueue.set(pos, tmp);
				}
			}
			else if(ch == '+'){//如果是加号，对当前队列内容进行输出，并清空队列
				for (int j=0;j<varQueue.size();j++){
					result = result + varQueue.get(j).var + "^" + varQueue.get(j).val;
					if (j!=varQueue.size()-1)
						result = result + "*";
				}
				varQueue = emptyQueue(varQueue);//清空队列
				result = result + "+";
			}
			else if((ch=='*')&&(varQueue.size()==0)){//如果是加号且不是字符串末尾的加号，直接输出
				result = result + String.valueOf(ch);
			}
			else if((ch>'0')&&(ch<='9'))//如果是数字直接输出
				result = result + String.valueOf(ch);
		}
		result = result.substring(0,result.length()-1);//截去末尾的加号
		return result;
	}
	
	//检测队列中是否存在目标字符，如果存在返回字符在队列中的位置，如果不存在返回-1
	public static int checkVar(ArrayList<varDegree> varQueue,char ch){
		int pos = -1;
		int i;
		for (i=0;i<varQueue.size();i++){
			if (ch == varQueue.get(i).var.charAt(0))
				pos = i;
		}
		return pos;
	}
	
	//对切片形成的每个小项进行处理，形成合理的数据结构
	public  Polynomial expression(String MyVar,String Mydiv)//MyVar变量，Mydiv切片
	{
		Polynomial p = new Polynomial();
		p.stringCoef ="";
		p.intCoef = 1;
		p.degree = 0;//初始化
		String coef = "";//系数字符串
		String queue = "";//缓冲队列
		int i;
		int intTemp=1;//数字系数的临时变量
		//计算幂
		for (i = 0;i <Mydiv.length();i++)
		{
			if (Mydiv.substring(i,i+1).equals(MyVar))
				p.degree+=1;//如果是变量，幂加一
			else if (!Mydiv.substring(i,i+1).equals("*"))
				coef = coef+(Mydiv.substring(i,i+1));//如果不是变量，加入系数字符串
		}
		//计算系数
		for (i=0;i<coef.length();i++){
			char ch = coef.charAt(i);
//			System.out.printf("第%d次\n",i);
			if (ch>'0'&&ch<'9'){//如果是数字，加入队列
				queue = addQueue(queue,ch);
				if((i==coef.length()-1)&&(queue!="")){//如果到了系数最后一位且队列非空，即最后一项为数字
					intTemp *= Integer.parseInt(queue);//计算系数
					queue = emptyQueue(queue);//清空队列
				}
			}
			else//不是数字
			{
				if(queue!="")//队列非空时
				{
					intTemp *= Integer.parseInt(queue);//计算系数
					queue = emptyQueue(queue);//清空队列
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
			char chr=coef.charAt(i); //返回索引出的char值
		    if((chr<'0'||chr>'9')&& chr!= '*'&&chr!='-')
		    	p.stringCoef = p.stringCoef+String.valueOf(chr); 
		}
//		System.out.printf("degree：%d\n",p.degree);
//		System.out.printf("intCoef:%s\n",p.intCoef);
//		System.out.printf("stringCoef:%s\n",p.stringCoef);
		return p;
	}
	
	//清空队列
	public static String emptyQueue(String queue){
		queue = "";
		return queue;
	}
	
	//清空队列
	public static ArrayList<varDegree> emptyQueue(ArrayList<varDegree> varQueue){
		ArrayList<varDegree> n = new ArrayList<varDegree>();
		varQueue = n;
		return varQueue;
	}
	
	//向队列中加入目标字符
	public static String addQueue(String queue,char ch){
		queue=queue+String.valueOf(ch);
		return queue;
	}
	
	//向队列中加入目标字符
	public static ArrayList<varDegree> addQueue(ArrayList<varDegree> varQueue,char ch){
		varDegree tmp = new varDegree();
		tmp.var = String.valueOf(ch);
		tmp.val = 1;
		varQueue.add(tmp);
		return varQueue;
	}
	
	//根据输入值化简表达式
	public String simplify(ArrayList<Polynomial> arr,ArrayList<valueCommand> valueGroup,int num,int k)//arr多项式组，MyValue值
	{
		ArrayList<Integer> arrCoef =new ArrayList<Integer>();//系数数组
		String s = "";
			for(int i = 0;i < arr.size();i++)
			{
				arrCoef.add(arr.get(i).intCoef);
				for(int j = 0;j< arr.get(i).degree;j++){
					arrCoef.set(i, arrCoef.get(i)*valueGroup.get(k).val);//计算化简后的数字系数
				}
				//如果字母系数不在需要化简变量集中，直接输出
				if (!(containString(Integer.toString(arrCoef.get(i)),valueGroup))){
					if(i!= arr.size()-1&&arr.get(i+1).intCoef>0)
						s = s + Integer.toString(arrCoef.get(i))+arr.get(i).stringCoef+"+";
					else
						s = s + Integer.toString(arrCoef.get(i))+arr.get(i).stringCoef;//历史遗留问题不能带乘号
				}
			}
		return s;
	}
	
	//判断目标字符是否存在于化简变量集中
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
	
	//对目标表达式进行求导
	public String derivative(ArrayList<Polynomial> arr,String var){
		int i;
		String result="";
		Polynomial p = new Polynomial();
		p.degree = 0;
		p.stringCoef ="";
		p.intCoef = 1;
		for (i=0;i<arr.size();i++){
			p.intCoef = arr.get(i).intCoef * arr.get(i).degree;//数字求导结果
			if (p.intCoef != 0)
				p.stringCoef = arr.get(i).stringCoef;//字母求导结果
			else
				p.stringCoef = "";
			p.degree = arr.get(i).degree - 1;//幂求导结果
			if(p.degree == -1)
				p.degree = 0;
			//输出求导结果
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
	
	//对输入字符串以加号或减号为标记进行切片
	public ArrayList<String> stringCut(String exp){
		ArrayList<String> Mydiv = new ArrayList<String>();//切片
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
	
	//对化简命令进行处理，提取待化简变量以及对应的变量值
	public ArrayList<valueCommand> commandDeal(String command){
		ArrayList<valueCommand> valueGroup = new ArrayList<valueCommand>();
		int i;
		int blankNum=0;//空格数与赋值变量数相等
		muiti s = new muiti();
		ArrayList<Integer> blankPos = new ArrayList<Integer>();//标记空格在command字符串中的位置
		blankNum = s.getBlankNum(command);
		blankPos = s.getBlankPos(command);
		for (i=0;i<blankNum;i++){
			valueCommand tmp = new valueCommand();
			tmp.var = command.substring(blankPos.get(i)+1,blankPos.get(i)+2);
			//等号与下一个空格之间的数字切片
			if (i==blankNum-1){//command的最后一组赋值语句之后没有等号
				tmp.val = Integer.parseInt(command.substring(blankPos.get(i)+3));
			}
			else{//其他情况使用当前等号与下一个空格进行截取
				tmp.val = Integer.parseInt(command.substring(blankPos.get(i)+3, blankPos.get(i+1)));
				}
			valueGroup.add(tmp);
		}
		return valueGroup;
	}
	
	//获得目标字符串中的空格个数
	public int getBlankNum(String str){
		int blankNum=0;
		for (int i=0;i<str.length();i++){
			if (str.charAt(i)==32){//判断赋值变量的个数
				blankNum++;
			}
		}
		return blankNum;
	}
	
	//获得目标字符串中空格的位置数组
	public ArrayList<Integer> getBlankPos(String str){
		ArrayList<Integer> blankPos = new ArrayList<Integer>();
		for (int i=0;i<str.length();i++){
			if (str.charAt(i)==32){//判断赋值变量的个数
				blankPos.add(i);
			}
		}
		return blankPos;
	}
	
	//对结果字符串进行合并，输出结果为基本格式
	public static String resultFormat(String str){
		String result="";
		String queue="";
		int i;
		char ch;
		str = str + "+";//末尾添加非数字符号
		for (i=0;i<str.length();i++){
			ch = str.charAt(i);
			if (ch>='0' && ch<='9')//是数字就加入队列
				queue = addQueue(queue,ch);
			else if((ch<'0' || ch>'9')&&(ch != '+')){//不是数字或加号，即为字母
				result = result + queue + "*" + str.substring(i, i+1);//输出队列内容乘号和字母
				queue = emptyQueue(queue);//清空队列
			}
			else if (ch == '+'){
				if (queue == "" && i!=str.length()-1){
					result = result + "+";
				}
				else{
					if (i != str.length()-1){
						result = result + queue + "+";//输出队列内容
						queue = emptyQueue(queue);//清空队列
					}
					else{
						result = result + queue ;//输出队列内容
						queue = emptyQueue(queue);//清空队列
					}
				}
				}
		}
		return result;
	}
	
	//清除输出结果中的0
	public static String zeroClear(String str){
		String result="";
		int i;
		for (i=0;i<str.length();i++){
			if (str.substring(i, i+1).equals("0"));
			else
				result = result + str.substring(i,i+1);
		}
		if (result.substring(0, 1).equals("+"))//截去结果最开始的加号
			result = result.substring(1);
		else if(result.substring(result.length()-1).equals("+"))
			result = result.substring(0, str.length()-2);//截去最后一个加号
		return result;
	}

	public static void main(String[] args)
	{
	System.out.println("Please print in the poly:");
	Scanner in = new Scanner(System.in);
	String exp = in.nextLine();//表达式
	System.out.printf("输入内容为：%s\n",exp);//输出
	ArrayList<String> Mydiv = new ArrayList<String>();//切片
	muiti s = new muiti();

	String command = in.nextLine();//命令
	int choice=0;
	String MyVar1=null;//待赋值变量
	String MyVar2=null;//待求导变量
	ArrayList<valueCommand> valueGroup = new ArrayList<valueCommand>();//化简变量取值
	if(command.substring(0, 1).equals("!"))
	{
		if(command.substring(1, 4).equals("d/d"))
		{
			choice = 2;//求导命令
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
			choice = 1;//化简命令
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
			valueGroup = s.commandDeal(command);//化简请求字符串处理
			for (int i=0;i<num;i++){
				ArrayList<Polynomial> arr = new ArrayList<Polynomial>();
				MyVar1 = valueGroup.get(i).var;
				Mydiv = s.stringCut(ss1);
				for (int k = 0;k<Mydiv.size();k++)
					arr.add(s.expression(MyVar1,Mydiv.get(k)));
				ss1 = s.simplify(arr,valueGroup,num,i);
			}
			ss1 = resultFormat(ss1);//输出结果基本整理
			System.out.printf("%s\n",ss1);
			ss1 = degreeFormat(ss1);//输出结果进一步整理
			System.out.printf("改进的输入：%s\n",ss1);
			break;
		case 2:
			muiti s1 = new muiti();
			ArrayList<Polynomial> arr2 = new ArrayList<Polynomial>();
			Mydiv = s.stringCut(ss1);
			for (int k = 0;k < Mydiv.size();k++)
				arr2.add(s1.expression(MyVar2,Mydiv.get(k)));
			ss2 = s1.derivative(arr2,MyVar2);
			ss2 = zeroClear(ss2);//清除输出结果中的0
			System.out.printf("%s\n", ss2);
			ss2 = degreeFormat(ss2);//输出结果进一步整理
			System.out.printf("改进输入：%s\n",ss2);
			break; 
		default:
			break;
	}
	in.close();
	}
}
