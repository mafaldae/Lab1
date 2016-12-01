package muiti2;

import java.util.ArrayList;

public class ResultOutput {
//	private VarDegree tmp;
//	public VarDegree getVarDegree()
//	{
//		return tmp;
//	}
//	public void setVarDegree()
//	{
//		this.tmp = tmp;
//	}
	public String resultout(String ss)
	{
		ss = new Result().resultdeal(ss);
		System.out.printf("¸Ä½øÊä³ö£º%s\n",ss);
		return ss;
	}
}
