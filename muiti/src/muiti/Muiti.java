package muiti;

import java.util.ArrayList;
import java.util.Scanner;

/** .
* set default mock parameter������˵����
* var additionalParameters parameter additional(��������)
* val additionalParameters parameter additional(��������)
* @return data manager(����ֵ˵��)
* @throws Exception if has error(�쳣˵��)
*/
public class Muiti { 
	public class Polynomial { 
    private String stringCoef;
    private int intCoef;
    private int degree;
	}
	/** .
	* set default mock parameter������˵����
	* var additionalParameters parameter additional(��������)
	* val additionalParameters parameter additional(��������)
	* @return data manager(����ֵ˵��)
	* @throws Exception if has error(�쳣˵��)
	*/
	public class valueCommand { //��ֵҪ����ÿ�������С��
		private String var; //����
		private int val; //��ֵ
    }
	/** .
	* set default mock parameter������˵����
	* var additionalParameters parameter additional(��������)
	* val additionalParameters parameter additional(��������)
	* @return data manager(����ֵ˵��)
	* @throws Exception if has error(�쳣˵��)
	*/
	public static class varDegree { //������ʽ������ÿ��δ֪���Լ�������
		private String var;
		private int val; //checkstyle
	}
	
	//�Խ���ַ�����������Ľ�����ͬ�������ݴν������
	/** .
	* set default mock parameter������˵����
	* @param str additionalParameters parameter additional(��������)
	* @return data manager(����ֵ˵��)
	* @throws Exception if has error(�쳣˵��)
	*/
	public static String degreeFormat(String str) {
		String result = "";
		ArrayList<varDegree> varQueue = new ArrayList<varDegree>();
		final varDegree tmp = new varDegree();
		int i;
		int pos; //��Ǳ����ڶ����е�λ��
        char ch;
		str = str + "+"; //���ַ��������ӼӺű��ڴ���
        for (i = 0; i < str.length(); i++) {
			ch = str.charAt(i);
			if (ch != '*' && ch != '+' && (ch > '9' || ch < '0')) { //��ĸ
				pos = checkVar(varQueue, ch); //�������Ƿ��Ѿ������ڶ�����
				if (pos == -1) { //�����������ڶ�����
					varQueue = addQueue(varQueue, ch); //��ӱ����������
				} else { 
						tmp.val = varQueue.get(pos).val + 1;
						tmp.var = String.valueOf(ch);
						varQueue.set(pos, tmp);
				} 
			} else if (ch == '+') { //����ǼӺţ��Ե�ǰ�������ݽ������������ն���
				for (int j = 0; j < varQueue.size(); j++) {
					result = result + varQueue.get(j).var + "^" 
				+ varQueue.get(j).val;
					if (j != varQueue.size() - 1) {
						result = result + "*";
					}
				}
				varQueue = emptyQueue(varQueue); //��ն���
				result = result + "+";
			} else if (ch == '*' && varQueue.size() == 0) { //��������ַ���ĩβ�ļӺ�
				result = result + String.valueOf(ch);
			} else if (ch > '0' && ch <= '9') { //���������ֱ�����
				result = result + String.valueOf(ch);
			}
		}
		result = result.substring(0, result.length() - 1); //��ȥĩβ�ļӺ�
		return result;
	}
	
	//���������Ƿ����Ŀ���ַ���������ڷ����ַ��ڶ����е�λ�ã���������ڷ���-1
	/** .
	* set default mock parameter������˵����
	* @param varQueue additionalParameters parameter additional(��������)
	* @param ch additionalParameters parameter additional(��������)
	* @return data manager(����ֵ˵��)
	* @throws Exception if has error(�쳣˵��)
	*/
	public static int checkVar(final ArrayList<varDegree> varQueue, 
			final char ch) { //
		int pos = -1;
		int i;
		for (i = 0; i < varQueue.size(); i++) {
			if (ch == varQueue.get(i).var.charAt(0)) {
				pos = i;
			}
		}
		return pos;
	}
	
	//����Ƭ�γɵ�ÿ��С����д����γɺ�������ݽṹ
	/** .
	* set default mock parameter������˵����
	* @param mydiv additionalParameters parameter additional(��������)
	* @param myVar additionalParameters parameter additional(��������)
	* @return data manager(����ֵ˵��)
	* @throws Exception if has error(�쳣˵��)
	*/
	public final Polynomial expression(final String myVar, final String mydiv) {
		Polynomial p = new Polynomial();
		p.stringCoef = "";
		p.intCoef = 1;
		p.degree = 0; //��ʼ��
		String coef = ""; //ϵ���ַ���
		String queue = ""; //�������
		int i;
		int intTemp = 1; //����ϵ������ʱ����
		//������
		for (i = 0; i < mydiv.length(); i++) {
			if (mydiv.substring(i, i + 1).equals(myVar)) {
				p.degree += 1;
			} else if (!mydiv.substring(i, i + 1).equals("*")) {
				coef = coef + (mydiv.substring(i, i + 1));
			} //������Ǳ���������ϵ���ַ���
		}
		//����ϵ��
		for (i = 0; i < coef.length(); i++) {
			char ch = coef.charAt(i);
//			System.out.printf("��%d��\n",i);
			if (ch > '0' && ch < '9') { //��������֣��������
				queue = addQueue(queue, ch);
				if (i == coef.length() - 1 && !queue.equals("")) { //������һ��Ϊ����
					intTemp *= Integer.parseInt(queue); //����ϵ��
					queue = emptyQueue(queue); //��ն���
				}
			} else {
				if (queue != "") { //���зǿ�
					intTemp *= Integer.parseInt(queue); //����ϵ��
					queue = emptyQueue(queue); //��ն���
				}
			}
			p.intCoef = intTemp;
		}
		for (i = 0; i < coef.length(); i++) {
			char chr = coef.charAt(i);
			if (chr == '-') {
				p.intCoef = 0 - p.intCoef;
			}
		}
		for (i = 0; i < coef.length(); i++) {
			char chr = coef.charAt(i); //������������charֵ // NOPMD by zhangxiaokai on 16-10-25 ����8:48
		    if ((chr < '0' || chr > '9') && chr != '*' && chr != '-') {
		    	p.stringCoef = p.stringCoef + String.valueOf(chr);
		    }
		}
		return p;
	}
	
	//��ն���
	/** .
	* set default mock parameter������˵����
	* @param queue additionalParameters parameter additional(��������)
	* @return data manager(����ֵ˵��)
	* @throws Exception if has error(�쳣˵��)
	*/
	public static String emptyQueue(String queue) { //0
		queue = "";
		return queue;
	}
	
	//��ն���
	/** .
	* set default mock parameter������˵����
	* @param varQueue additionalParameters parameter additional(��������)
	* @return data manager(����ֵ˵��)
	* @throws Exception if has error(�쳣˵��)
	*/
	public static ArrayList<varDegree> 
	emptyQueue(ArrayList<varDegree> varQueue) {
		ArrayList<varDegree> n = new ArrayList<varDegree>();
		varQueue = n;
		return varQueue;
	}
	
	//������м���Ŀ���ַ�
	/** .
	* set default mock parameter������˵����
	* @param queue additionalParameters parameter additional(��������)
	* @param ch additionalParameters parameter additional(��������)
	* @return data manager(����ֵ˵��)
	* @throws Exception if has error(�쳣˵��)
	*/
	public static String addQueue(String queue, final char ch) {
		queue = queue + String.valueOf(ch); 
		return queue;
	}
	
	//������м���Ŀ���ַ�
	/** .
	* set default mock parameter������˵����
	* @param ch additionalParameters parameter additional(��������)
	* @param varQueue additionalParameters parameter additional(��������)
	* @return data manager(����ֵ˵��)
	* @throws Exception if has error(�쳣˵��)
	*/
	public static ArrayList<varDegree> addQueue(
			final ArrayList<varDegree> varQueue, final char ch) {
		varDegree tmp = new varDegree();
		tmp.var = String.valueOf(ch);
		tmp.val = 1;
		varQueue.add(tmp);
		return varQueue;
	}
	
	//��������ֵ������ʽ
	/** .
	* set default mock parameter������˵����
	* @param arr additionalParameters parameter additional(��������)
	* @param valueGroup additionalParameters parameter additional(��������)
	* @param k additionalParameters parameter additional(��������)
	* @param num additionalParameters parameter additional(��������)
	* @return data manager(����ֵ˵��)
	* @throws Exception if has error(�쳣˵��)
	*/
	public final String simplify(final ArrayList<Polynomial> arr, 
			final ArrayList<valueCommand> valueGroup, 
			final int num, final int k) {
		ArrayList<Integer> arrCoef = new ArrayList<Integer>(); //ϵ������
		String s = "";
			for (int i = 0; i < arr.size(); i++) {
				arrCoef.add(arr.get(i).intCoef); 
				for (int j = 0; j < arr.get(i).degree; j++) {
					arrCoef.set(i, arrCoef.get(i) * valueGroup.get(k).val);
				}
				//�����ĸϵ��������Ҫ����������У�ֱ�����
				if (!(containString(Integer.toString(arrCoef.get(i)), 
						valueGroup))) {
					if (i != arr.size() - 1 && arr.get(i + 1).intCoef > 0) {
						s = s + Integer.toString(arrCoef.get(i)) 
						+ arr.get(i).stringCoef + "+";
					} else {
						s = s + Integer.toString(arrCoef.get(i)) 
						+ arr.get(i).stringCoef; //��ʷ�������ⲻ�ܴ��˺�
					}
				}
			}
		return s;
	}
	
	//�ж�Ŀ���ַ��Ƿ�����ڻ����������
	/** .
	* set default mock parameter������˵����
	* @param str additionalParameters parameter additional(��������)
	* @param valueGroup additionalParameters parameter additional(��������)
	* @return data manager(����ֵ˵��)
	* @throws Exception if has error(�쳣˵��)
	*/
	public final boolean containString(final String str, 
			final ArrayList<valueCommand> valueGroup) { //!!!!!!!!!!!!!!!!
		boolean contain = false;
		int i;
		for (i = 0; i < valueGroup.size(); i++) { 
			if (str.equals(valueGroup.get(i).var)) { 
				contain = true;
				break;
			}
		}
		return contain;
	}
	
	//��Ŀ����ʽ������
	/** .
	* set default mock parameter������˵����
	* @param var additionalParameters parameter additional(��������)
	* @param arr additionalParameters parameter additional(��������)
	* @return data manager(����ֵ˵��)
	* @throws Exception if has error(�쳣˵��)
	*/
	public final String derivative(final ArrayList<Polynomial> arr, 
			final String var) {
		int i;
		String result = ""; 
		Polynomial p = new Polynomial();
		p.degree = 0;
		p.stringCoef = "";
		p.intCoef = 1;
		for (i = 0; i < arr.size(); i++) {
			p.intCoef = arr.get(i).intCoef * arr.get(i).degree; //�����󵼽��
			if (p.intCoef != 0) {
				p.stringCoef = arr.get(i).stringCoef; 
			} else {
				p.stringCoef = "";
			}
			p.degree = arr.get(i).degree - 1; //���󵼽��
			if (p.degree == -1) {
				p.degree = 0;
			}
			//����󵼽��
			result = result + Integer.toString(p.intCoef);
			if (p.stringCoef.equals("")) {
			} else {
				for (int k = 0; k < p.stringCoef.length(); k++) {
					if (k != p.stringCoef.length() - 1) {
						result = result + "*" 
					+ p.stringCoef.substring(k, k + 1);
					} else {
						result = result + "*" + p.stringCoef.substring(k);
					}
				}
			}
			if (p.degree != 0) {
				for (int k = 0; k < p.degree; k++) {
					result = result + "*" + var;
				}
			}
				//System.out.print(var+"^"+p.degree);
//				result = result + var+"^"+Integer.toString(p.degree);
			if (i < arr.size() - 1 && arr.get(i + 1).intCoef > 0) {
				result = result + "+";
			}
		}
		//System.out.println("\n");
		return result;
	}
	
	//�������ַ����ԼӺŻ����Ϊ��ǽ�����Ƭ
	/** .
	* set default mock parameter������˵����
	* @param exp additionalParameters parameter additional(��������)
	* @return data manager(����ֵ˵��)
	* @throws Exception if has error(�쳣˵��)
	*/
	public final ArrayList<String> stringCut(final String exp) {
		ArrayList<String> mydiv = new ArrayList<String>(); //��Ƭ
		int len = exp.length();
		int start = 0, end = -1;
		int j = 0;
		for (int i = 0; i < len; i++) {
			start = end + 1; 
			if (exp.substring(i, i + 1).equals("+") 
					|| exp.substring(i, i + 1).equals("-")) {

				if (exp.substring(i, i + 1).equals("+")) {
					end = i;
					mydiv.add(exp.substring(start, end));
					System.out.printf("%s\n", mydiv.get(j));
					j++;
				} else if (exp.substring(i, i + 1).equals("-") && i != 0) {
					end = i;
					mydiv.add(exp.substring(start, end));
					System.out.printf("%s\n", mydiv.get(j));
					j++;
					end = end - 1;
				}
			} else if (i == len - 1) {
				mydiv.add(exp.substring(start, len));
				System.out.printf("%s\n", mydiv.get(j));
			}
		}
		return mydiv;
	}
	
	//�Ի���������д�����ȡ����������Լ���Ӧ�ı���ֵ
	/** .
	* set default mock parameter������˵����
	* @param command additionalParameters parameter additional(��������)
	* @return data manager(����ֵ˵��)
	* @throws Exception if has error(�쳣˵��)
	*/
	public final ArrayList<valueCommand> commandDeal(final String command) {
		ArrayList<valueCommand> valueGroup = new ArrayList<valueCommand>();
		int i;
		int blankNum = 0; //�ո����븳ֵ���������
		Muiti s = new Muiti();
		ArrayList<Integer> blankPos = new ArrayList<Integer>(); //��ǿո��λ��
		blankNum = s.getBlankNum(command);
		blankPos = s.getBlankPos(command);
		for (i = 0; i < blankNum; i++) {
			valueCommand tmp = new valueCommand();
			tmp.var = command.substring(blankPos.get(i) + 1,
					blankPos.get(i) + 2);
			//�Ⱥ�����һ���ո�֮���������Ƭ
			if (i == blankNum - 1) { //command�����һ�鸳ֵ���֮��û�еȺ�
				tmp.val = Integer.parseInt(command.substring(blankPos.get(i) 
						+ 3));
			} else { //�������ʹ�õ�ǰ�Ⱥ�����һ���ո���н�ȡ
				tmp.val = Integer.parseInt(command.substring(blankPos.get(i) 
						+ 3, blankPos.get(i + 1)));
				}
			valueGroup.add(tmp);
		}
		return valueGroup;
	}
	
	//���Ŀ���ַ����еĿո����
	/** .
	* set default mock parameter������˵����
	* @param str additionalParameters parameter additional(��������)
	* @return data manager(����ֵ˵��)
	* @throws Exception if has error(�쳣˵��)
	*/
	public final int getBlankNum(final String str) {
		int blankNum = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == 32) { //�жϸ�ֵ�����ĸ���
				blankNum++;
			}
		}
		return blankNum;
	}
	
	//���Ŀ���ַ����пո��λ������
	/** .
	* set default mock parameter������˵����
	* @param str additionalParameters parameter additional(��������)
	* @return data manager(����ֵ˵��)
	* @throws Exception if has error(�쳣˵��)
	*/
	public final ArrayList<Integer> getBlankPos(final String str) {
		ArrayList<Integer> blankPos = new ArrayList<Integer>();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == 32) { //�жϸ�ֵ�����ĸ���
				blankPos.add(i);
			}
		}
		return blankPos;
	}
	
	//�Խ���ַ������кϲ���������Ϊ������ʽ
	/** .
	* set default mock parameter������˵����
	* @param str additionalParameters parameter additional(��������)
	* @return data manager(����ֵ˵��)
	* @throws Exception if has error(�쳣˵��)
	*/
	public static String resultFormat(String str) {
		String result = "";
		String queue = "";
		int i;
		char ch;
		str = str + "+"; //ĩβ��ӷ����ַ���
		for (i = 0; i < str.length(); i++) {
			ch = str.charAt(i);
			if (ch >= '0' && ch <= '9') { //�����־ͼ������
				queue = addQueue(queue, ch);
			} else if ((ch < '0' || ch > '9') && (ch != '+')) { //�������ֻ�Ӻţ���Ϊ��ĸ
				result = result + queue + "*" 
			+ str.substring(i, i + 1); //����������ݳ˺ź���ĸ
				queue = emptyQueue(queue); //��ն���
			} else if (ch == '+') {
				if (queue == "" && i != str.length() - 1) {
					result = result + "+";
				} else {
					if (i != str.length() - 1) {
						result = result + queue + "+"; //�����������
						queue = emptyQueue(queue); //��ն���
					} else {
						result = result + queue; //�����������
						queue = emptyQueue(queue); //��ն���
					}
				}
				}
		}
		return result;
	}
	
	/** .
	* set default mock parameter������˵����
	* @param str additionalParameters parameter additional(��������)
	* @return data manager(����ֵ˵��)
	* @throws Exception if has error(�쳣˵��)
	*/
	public static String zeroClear(final String str) {
		String result = "";
		int i;
		for (i = 0; i < str.length(); i++) {
			if (str.substring(i, i + 1).equals("0")) {
			
			} else {
				result = result + str.substring(i, i + 1);
			}
		}
		if (result.substring(0, 1).equals("+")) {
			result = result.substring(1);
		} else if (result.substring(result.length() - 1).equals("+")) {
			result = result.substring(0, str.length() - 2); //��ȥ���һ���Ӻ�
		}
		return result;
	}

	/** .
	* set default mock parameter������˵����
	* @param args additionalParameters parameter additional(��������)
	* data manager(����ֵ˵��)
	* @throws Exception if has error(�쳣˵��)
	*/
	public static void main(final String[] args) {
	System.out.println("Please print in the poly:");
	Scanner in = new Scanner(System.in);
	String exp = in.nextLine(); //���ʽ
	System.out.printf("��������Ϊ��%s\n", exp); //���
	ArrayList<String> mydiv = new ArrayList<String>(); //��Ƭ
	Muiti s = new Muiti();

	String command = in.nextLine(); //����
	int choice = 0;
	String myVar1 = null; //����ֵ����
	String myVar2 = null; //���󵼱���
	ArrayList<valueCommand> valueGroup = new ArrayList<valueCommand>(); //�������ȡֵ
	if (command.substring(0, 1).equals("!")) {
		if (command.substring(1, 4).equals("d/d")) {
			choice = 2; //������
			myVar2 = command.substring(5, 6);
			for (int j = 0; j < command.length(); j++) {
				if (myVar2.equals(exp.substring(j, j + 1)) 
						&& (j != command.length() - 1)) {
					
				} else if (myVar2.equals(exp.substring(j)) 
						&& (j == command.length() - 1)) {
					
				} else {
					System.out.println("Error, no variable");
					choice = 0;
					break;
				}
			}
		} else if (command.substring(1, 9).equals("simplify")) {
			choice = 1; //��������
		} else {
			System.out.printf("Error!Wrong Command!");
		}
	} else {
		System.out.printf("Wrong Command!");
	}
	String ss1 = exp;
	String ss2 = "";
	switch(choice) {
		case 1:
			int num = s.getBlankNum(command);
			valueGroup = s.commandDeal(command); //���������ַ�������
			for (int i = 0; i < num; i++) {
				ArrayList<Polynomial> arr = new ArrayList<Polynomial>();
				myVar1 = valueGroup.get(i).var;
				mydiv = s.stringCut(ss1);
				for (int k = 0; k < mydiv.size(); k++) {
					arr.add(s.expression(myVar1, mydiv.get(k)));
				}
				ss1 = s.simplify(arr, valueGroup, num, i);
			}
			ss1 = resultFormat(ss1); //��������������
			System.out.printf("%s\n", ss1);
			ss1 = degreeFormat(ss1); //��������һ������
			System.out.printf("�Ľ������룺%s\n", ss1);
			break;
		case 2:
			Muiti s1 = new Muiti();
			ArrayList<Polynomial> arr2 = new ArrayList<Polynomial>();
			mydiv = s.stringCut(ss1);
			for (int k = 0; k < mydiv.size(); k++) {
				arr2.add(s1.expression(myVar2, mydiv.get(k)));
			}
			ss2 = s1.derivative(arr2, myVar2);
			ss2 = zeroClear(ss2); //����������е�0
			System.out.printf("%s\n", ss2);
			ss2 = degreeFormat(ss2); //��������һ������
			System.out.printf("�Ľ����룺%s\n", ss2);
			break; 
		default:
			break;
	}
	in.close();
	}
}
