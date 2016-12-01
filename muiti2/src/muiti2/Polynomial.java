package muiti2;

public class Polynomial {
    public String stringCoef;
    public int intCoef;
    public int degree;
  //对切片形成的每个小项进行处理，形成合理的数据结构
  	public  Polynomial expression(String MyVar,String Mydiv)//MyVar变量，Mydiv切片
  	{

  		Polynomial p = new Polynomial();
  		p.stringCoef ="";
  		p.intCoef = 1;
  		p.degree = 0;//初始化
  		String coef = "";//系数字符串
  		String queue = "";//缓冲队列
  		int i;
  		int intTemp=1;//数字系数的临时变量
  		//计算幂
  		for (i = 0;i <Mydiv.length();i++)
  		{
  			if (Mydiv.substring(i,i+1).equals(MyVar))
  				p.degree+=1;//如果是变量，幂加一
  			else //if (!Mydiv.substring(i,i+1).equals("*"))
  				coef = coef+(Mydiv.substring(i,i+1));//如果不是变量，加入系数字符串
  		}
  		//计算系数
  		for (i=0;i<coef.length();i++){
  			char ch = coef.charAt(i);
//  			System.out.printf("第%d次\n",i);
  			if (ch>='0'&&ch<='9'){//如果是数字，加入队列
  				queue = addQueue(queue,ch);
  				if((i==coef.length()-1)&&(queue!="")){//如果到了系数最后一位且队列非空，即最后一项为数字
  					intTemp *= Integer.parseInt(queue);//计算系数
  					queue = emptyQueue(queue);//清空队列
  				}
  			}
  			else//不是数字
  			{
  				if(queue!="")//队列非空时
  				{
  					intTemp *= Integer.parseInt(queue);//计算系数
  					queue = emptyQueue(queue);//清空队列
  				}
  			}
  			p.intCoef = intTemp;
  		}
  		for(i=0;i<coef.length();i++)
  		{
  			char chr = coef.charAt(i);
  			if(chr=='-')
  			{
  				p.intCoef = 0-p.intCoef;
  			}
  		}
  		for(i=0;i<coef.length();i++){
  			char chr=coef.charAt(i); //返回索引出的char值
  		    if((chr<'0'||chr>'9')&& chr!= '*'&&chr!='-')
  		    	p.stringCoef = p.stringCoef+String.valueOf(chr); 
  		}
//  		System.out.printf("degree：%d\n",p.degree);
//  		System.out.printf("intCoef:%s\n",p.intCoef);
//  		System.out.printf("stringCoef:%s\n",p.stringCoef);
  		return p;
  	}
  //清空队列
  	private static String emptyQueue(String queue){
  		queue = "";
  		return queue;
  	}
  //向队列中加入目标字符
  	private static String addQueue(String queue,char ch){
  		queue=queue+String.valueOf(ch);
  		return queue;
  	}
}
