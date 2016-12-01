package muiti2;

import java.util.*;

public class VarDegree {
	public String var;
	public int val;
	
//	public String getvar(){
//		return var;
//	}
//	public void setvar(){
//		this.var = var;
//	}
//	public int  getval(){
//		return val;
//	}
//	public void setval(){
//		this.val = val;
//	}
	public ArrayList<VarDegree> addQueue(ArrayList<VarDegree> varQueue,char ch){
		VarDegree tmp = new VarDegree();
		tmp.var = String.valueOf(ch);
		tmp.val = 1;
		varQueue.add(tmp);
		return varQueue;
	}
	public ArrayList<VarDegree> emptyQueue(ArrayList<VarDegree> varQueue){
		ArrayList<VarDegree> n = new ArrayList<VarDegree>();
		varQueue = n;
		return varQueue;
	}
}
