package muiti2;

import java.util.*;

public class simpCommand {
    public String var;
    public int val;
    /**
     * 
     */
  //对化简命令进行处理，提取待化简变量以及对应的变量值
  	public  ArrayList<simpCommand> commandDealS(String command){
  		ArrayList<simpCommand> sCommand = new ArrayList<simpCommand>();
  		int i;
  		int blankNum=0;//空格数与赋值变量数相等
  		simpCommand s = new simpCommand();
  		ArrayList<Integer> blankPos = new ArrayList<Integer>();//标记空格在command字符串中的位置
  		blankNum = getBlankNum(command);
  		blankPos = s.getBlankPos(command);
  		for (i=0;i<blankNum;i++){
  			simpCommand tmp = new simpCommand();
  			tmp.var = command.substring(blankPos.get(i)+1,blankPos.get(i)+2);
  			//等号与下一个空格之间的数字切片
  			if (i==blankNum-1){//command的最后一组赋值语句之后没有等号
  				tmp.val = Integer.parseInt(command.substring(blankPos.get(i)+3));
  			}
  			else{//其他情况使用当前等号与下一个空格进行截取
  				tmp.val = Integer.parseInt(command.substring(blankPos.get(i)+3, blankPos.get(i+1)));
  				}
  			sCommand.add(tmp);
  		}
  		return sCommand;
  	}
    /**
     * 
     */
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

    /**
     * 
     */
  //获得目标字符串中空格的位置数组
  	private ArrayList<Integer> getBlankPos(String str){
  		ArrayList<Integer> blankPos = new ArrayList<Integer>();
  		for (int i=0;i<str.length();i++){
  			if (str.charAt(i)==32){//判断赋值变量的个数
  				blankPos.add(i);
  			}
  		}
  		return blankPos;
  	}

}
