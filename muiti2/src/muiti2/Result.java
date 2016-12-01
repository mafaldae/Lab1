package muiti2;

import java.util.*;

public class Result {
	//private String ss;
	public String resultdeal(String ss)
	{
		ss = zeroClear(ss);//清除输出结果中的0
		ss = degreeFormat(ss);//输出结果进一步整理
		System.out.printf("改进输出：%s\n",ss);
		return ss;
	}
	
	//清空队列
	private String emptyQueue(String queue){
		queue = "";
		return queue;
	}
	
	
	//向队列中加入目标字符
	private String addQueue(String queue,char ch){
		queue=queue+String.valueOf(ch);
		return queue;
	}
	
	//对结果字符串进行合并，输出结果为基本格式
	public String resultFormat(String str){
		String result="";
		String queue="";
		int i;
		char ch;
		str = str + "+";//末尾添加非数字符号
		for (i=0;i<str.length();i++){
			ch = str.charAt(i);
			if (ch>='0' && ch<='9')//是数字就加入队列
				queue = addQueue(queue,ch);
			else if((ch<'0' || ch>'9')&&(ch != '+')&&(ch != '-')&&(ch != 32)){//不是空格、数字、加号或减号，即为字母
				result = result + queue + "*" + str.substring(i, i+1);//输出队列内容乘号和字母
				queue = emptyQueue(queue);//清空队列
			}
			else if ((ch == '+')||(ch == '-')){
				if (queue == "" && i!=str.length()-1){
					result = result + String.valueOf(ch);
				}
				else{
					if (i != str.length()-1){
						result = result + queue + String.valueOf(ch);//输出队列内容
						queue = emptyQueue(queue);//清空队列
					}
					else{
						result = result + queue ;//输出队列内容
						queue = emptyQueue(queue);//清空队列
					}
				}
				}
		}
		if (result.substring(0,1).equals("*"))
			result = result.substring(1);
		return result;
	}
	//清除输出结果中的0
	private String zeroClear(String str){
		String result="";
		int i;
		for (i=0;i<str.length();i++){
			if (str.substring(i, i+1).equals("0"));
			else
				result = result + str.substring(i,i+1);
		}
		if (result.substring(0, 1).equals("+"))//截去结果最开始的加号
			result = result.substring(1);
		if(result.substring(result.length()-1).equals("+"))
			result = result.substring(0, str.length()-1);//截去最后一个加号
		return result;
	}
	
	//检测队列中是否存在目标字符，如果存在返回字符在队列中的位置，如果不存在返回-1
	private int checkVar(ArrayList<VarDegree> varQueue,char ch){
		int pos = -1;
		int i;
		for (i=0;i<varQueue.size();i++){
			if (ch == varQueue.get(i).var.charAt(0))
				pos = i;
		}
		return pos;
	}
	
	//对结果字符串进行输出改进，相同变量以幂次进行输出
	private  String degreeFormat(String str){
		String result="";
		ArrayList<VarDegree> varQueue = new ArrayList<VarDegree>();
		
		int i;
		int pos;//标记变量在队列中的位置
		char ch;
		str = str + "+";//在字符串最后添加加号便于处理
		for (i=0;i<str.length();i++){
			VarDegree tmp = new VarDegree();
			ch = str.charAt(i);
			if ((ch!='*')&&(ch!='+')&&(ch!='-')&&((ch>'9')||(ch<'0'))&&(ch != 32)){//不是空格、数字、加号、减号、乘号，即为字母
				pos = checkVar(varQueue,ch);//检查变量是否已经存在于队列中
				if (pos == -1){//变量不存在于队列中
					varQueue = new VarDegree().addQueue(varQueue,ch);//添加变量进入队列
				}
				else{//变量在队列，相应幂加一
					tmp.val = varQueue.get(pos).val+1;
					tmp.var = String.valueOf(ch);
					varQueue.set(pos, tmp);
				}
			}
			else if((ch == '+')||(ch == '-')){//如果是加号或减号，对当前队列内容进行输出，并清空队列
				for (int j=0;j<varQueue.size();j++){
					result = result + varQueue.get(j).var + "^" + varQueue.get(j).val;
					if (j!=varQueue.size()-1)
						result = result + "*";
				}
				varQueue = new VarDegree().emptyQueue(varQueue);//清空队列
				result = result + String.valueOf(ch);
			}
			else if((ch=='*')&&(varQueue.size()==0)){//如果是加号且不是字符串末尾的加号，直接输出
				result = result + String.valueOf(ch);
			}
			else if((ch>='0')&&(ch<='9'))//如果是数字直接输出
				result = result + String.valueOf(ch);
		}
		result = result.substring(0,result.length()-1);//截去开头的加号
		if (result.substring(0,1).equals("*"))
			result = result.substring(1);
		return result;
	}
}
