package muiti2;

import java.util.ArrayList;

public class simplify {
	//根据输入值化简表达式
	public String simplifyopt(ArrayList<Polynomial> arr,ArrayList<simpCommand> valueGroup,int num,int k)//arr多项式组，MyValue值
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
	private boolean containString(String str,ArrayList<simpCommand> valueGroup){//!!!!!!!!!!!!!!!!
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

}
