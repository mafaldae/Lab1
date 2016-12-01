package muiti2;

import java.util.ArrayList;

public class dervative {
	//对目标表达式进行求导
	public String derivativeopt(ArrayList<Polynomial> arr,String var){
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
			if (p.intCoef==0);
			else
				result = result + "+" + Integer.toString(p.intCoef);
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
				if (i<arr.size()-1&&arr.get(i+1).intCoef>0)
					result = result + "+";
			}
			
		}
		if (result.substring(result.length()-1).equals("+"))
			result=result.substring(0, result.length()-1);
		result = result.substring(1);
		System.out.println(result);
		//判断结果字符串
		for(int judge = 0;judge<result.length();judge++)
		{
			char chr = result.charAt(judge);
			if((chr>='a'&&chr<='z')||(chr>='0'&&chr<='9')||(chr=='+')||(chr=='-')||(chr=='*')||(chr==32)||(chr==9))
				;
			else
			{
				//System.out.printf("wrongpoly!");
				String r2 = "wrongpoly!";
				return r2;
			}
		}
		return result;
	}
}
