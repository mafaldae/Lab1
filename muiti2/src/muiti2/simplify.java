package muiti2;

import java.util.ArrayList;

public class simplify {
	//��������ֵ������ʽ
	public String simplifyopt(ArrayList<Polynomial> arr,ArrayList<simpCommand> valueGroup,int num,int k)//arr����ʽ�飬MyValueֵ
	{
		ArrayList<Integer> arrCoef =new ArrayList<Integer>();//ϵ������
		String s = "";
			for(int i = 0;i < arr.size();i++)
			{
				arrCoef.add(arr.get(i).intCoef);
				for(int j = 0;j< arr.get(i).degree;j++){
					arrCoef.set(i, arrCoef.get(i)*valueGroup.get(k).val);//���㻯��������ϵ��
				}
				//�����ĸϵ��������Ҫ����������У�ֱ�����
				if (!(containString(Integer.toString(arrCoef.get(i)),valueGroup))){
					if(i!= arr.size()-1&&arr.get(i+1).intCoef>0)
						s = s + Integer.toString(arrCoef.get(i))+arr.get(i).stringCoef+"+";
					else
						s = s + Integer.toString(arrCoef.get(i))+arr.get(i).stringCoef;//��ʷ�������ⲻ�ܴ��˺�
				}
			}
		return s;
	}
	
	//�ж�Ŀ���ַ��Ƿ�����ڻ����������
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
