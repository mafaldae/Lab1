package muiti2;

import java.util.*;

public class simpCommand {
    public String var;
    public int val;
    /**
     * 
     */
  //�Ի���������д�����ȡ����������Լ���Ӧ�ı���ֵ
  	public  ArrayList<simpCommand> commandDealS(String command){
  		ArrayList<simpCommand> sCommand = new ArrayList<simpCommand>();
  		int i;
  		int blankNum=0;//�ո����븳ֵ���������
  		simpCommand s = new simpCommand();
  		ArrayList<Integer> blankPos = new ArrayList<Integer>();//��ǿո���command�ַ����е�λ��
  		blankNum = getBlankNum(command);
  		blankPos = s.getBlankPos(command);
  		for (i=0;i<blankNum;i++){
  			simpCommand tmp = new simpCommand();
  			tmp.var = command.substring(blankPos.get(i)+1,blankPos.get(i)+2);
  			//�Ⱥ�����һ���ո�֮���������Ƭ
  			if (i==blankNum-1){//command�����һ�鸳ֵ���֮��û�еȺ�
  				tmp.val = Integer.parseInt(command.substring(blankPos.get(i)+3));
  			}
  			else{//�������ʹ�õ�ǰ�Ⱥ�����һ���ո���н�ȡ
  				tmp.val = Integer.parseInt(command.substring(blankPos.get(i)+3, blankPos.get(i+1)));
  				}
  			sCommand.add(tmp);
  		}
  		return sCommand;
  	}
    /**
     * 
     */
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

    /**
     * 
     */
  //���Ŀ���ַ����пո��λ������
  	private ArrayList<Integer> getBlankPos(String str){
  		ArrayList<Integer> blankPos = new ArrayList<Integer>();
  		for (int i=0;i<str.length();i++){
  			if (str.charAt(i)==32){//�жϸ�ֵ�����ĸ���
  				blankPos.add(i);
  			}
  		}
  		return blankPos;
  	}

}
