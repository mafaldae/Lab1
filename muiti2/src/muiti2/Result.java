package muiti2;

import java.util.*;

public class Result {
	//private String ss;
	public String resultdeal(String ss)
	{
		ss = zeroClear(ss);//����������е�0
		ss = degreeFormat(ss);//��������һ������
		System.out.printf("�Ľ������%s\n",ss);
		return ss;
	}
	
	//��ն���
	private String emptyQueue(String queue){
		queue = "";
		return queue;
	}
	
	
	//������м���Ŀ���ַ�
	private String addQueue(String queue,char ch){
		queue=queue+String.valueOf(ch);
		return queue;
	}
	
	//�Խ���ַ������кϲ���������Ϊ������ʽ
	public String resultFormat(String str){
		String result="";
		String queue="";
		int i;
		char ch;
		str = str + "+";//ĩβ��ӷ����ַ���
		for (i=0;i<str.length();i++){
			ch = str.charAt(i);
			if (ch>='0' && ch<='9')//�����־ͼ������
				queue = addQueue(queue,ch);
			else if((ch<'0' || ch>'9')&&(ch != '+')&&(ch != '-')&&(ch != 32)){//���ǿո����֡��ӺŻ���ţ���Ϊ��ĸ
				result = result + queue + "*" + str.substring(i, i+1);//����������ݳ˺ź���ĸ
				queue = emptyQueue(queue);//��ն���
			}
			else if ((ch == '+')||(ch == '-')){
				if (queue == "" && i!=str.length()-1){
					result = result + String.valueOf(ch);
				}
				else{
					if (i != str.length()-1){
						result = result + queue + String.valueOf(ch);//�����������
						queue = emptyQueue(queue);//��ն���
					}
					else{
						result = result + queue ;//�����������
						queue = emptyQueue(queue);//��ն���
					}
				}
				}
		}
		if (result.substring(0,1).equals("*"))
			result = result.substring(1);
		return result;
	}
	//����������е�0
	private String zeroClear(String str){
		String result="";
		int i;
		for (i=0;i<str.length();i++){
			if (str.substring(i, i+1).equals("0"));
			else
				result = result + str.substring(i,i+1);
		}
		if (result.substring(0, 1).equals("+"))//��ȥ����ʼ�ļӺ�
			result = result.substring(1);
		if(result.substring(result.length()-1).equals("+"))
			result = result.substring(0, str.length()-1);//��ȥ���һ���Ӻ�
		return result;
	}
	
	//���������Ƿ����Ŀ���ַ���������ڷ����ַ��ڶ����е�λ�ã���������ڷ���-1
	private int checkVar(ArrayList<VarDegree> varQueue,char ch){
		int pos = -1;
		int i;
		for (i=0;i<varQueue.size();i++){
			if (ch == varQueue.get(i).var.charAt(0))
				pos = i;
		}
		return pos;
	}
	
	//�Խ���ַ�����������Ľ�����ͬ�������ݴν������
	private  String degreeFormat(String str){
		String result="";
		ArrayList<VarDegree> varQueue = new ArrayList<VarDegree>();
		
		int i;
		int pos;//��Ǳ����ڶ����е�λ��
		char ch;
		str = str + "+";//���ַ��������ӼӺű��ڴ���
		for (i=0;i<str.length();i++){
			VarDegree tmp = new VarDegree();
			ch = str.charAt(i);
			if ((ch!='*')&&(ch!='+')&&(ch!='-')&&((ch>'9')||(ch<'0'))&&(ch != 32)){//���ǿո����֡��Ӻš����š��˺ţ���Ϊ��ĸ
				pos = checkVar(varQueue,ch);//�������Ƿ��Ѿ������ڶ�����
				if (pos == -1){//�����������ڶ�����
					varQueue = new VarDegree().addQueue(varQueue,ch);//��ӱ����������
				}
				else{//�����ڶ��У���Ӧ�ݼ�һ
					tmp.val = varQueue.get(pos).val+1;
					tmp.var = String.valueOf(ch);
					varQueue.set(pos, tmp);
				}
			}
			else if((ch == '+')||(ch == '-')){//����ǼӺŻ���ţ��Ե�ǰ�������ݽ������������ն���
				for (int j=0;j<varQueue.size();j++){
					result = result + varQueue.get(j).var + "^" + varQueue.get(j).val;
					if (j!=varQueue.size()-1)
						result = result + "*";
				}
				varQueue = new VarDegree().emptyQueue(varQueue);//��ն���
				result = result + String.valueOf(ch);
			}
			else if((ch=='*')&&(varQueue.size()==0)){//����ǼӺ��Ҳ����ַ���ĩβ�ļӺţ�ֱ�����
				result = result + String.valueOf(ch);
			}
			else if((ch>='0')&&(ch<='9'))//���������ֱ�����
				result = result + String.valueOf(ch);
		}
		result = result.substring(0,result.length()-1);//��ȥ��ͷ�ļӺ�
		if (result.substring(0,1).equals("*"))
			result = result.substring(1);
		return result;
	}
}
