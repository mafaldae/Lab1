package muiti2;

public class Polynomial {
    public String stringCoef;
    public int intCoef;
    public int degree;
  //����Ƭ�γɵ�ÿ��С����д����γɺ�������ݽṹ
  	public  Polynomial expression(String MyVar,String Mydiv)//MyVar������Mydiv��Ƭ
  	{

  		Polynomial p = new Polynomial();
  		p.stringCoef ="";
  		p.intCoef = 1;
  		p.degree = 0;//��ʼ��
  		String coef = "";//ϵ���ַ���
  		String queue = "";//�������
  		int i;
  		int intTemp=1;//����ϵ������ʱ����
  		//������
  		for (i = 0;i <Mydiv.length();i++)
  		{
  			if (Mydiv.substring(i,i+1).equals(MyVar))
  				p.degree+=1;//����Ǳ������ݼ�һ
  			else //if (!Mydiv.substring(i,i+1).equals("*"))
  				coef = coef+(Mydiv.substring(i,i+1));//������Ǳ���������ϵ���ַ���
  		}
  		//����ϵ��
  		for (i=0;i<coef.length();i++){
  			char ch = coef.charAt(i);
//  			System.out.printf("��%d��\n",i);
  			if (ch>='0'&&ch<='9'){//��������֣��������
  				queue = addQueue(queue,ch);
  				if((i==coef.length()-1)&&(queue!="")){//�������ϵ�����һλ�Ҷ��зǿգ������һ��Ϊ����
  					intTemp *= Integer.parseInt(queue);//����ϵ��
  					queue = emptyQueue(queue);//��ն���
  				}
  			}
  			else//��������
  			{
  				if(queue!="")//���зǿ�ʱ
  				{
  					intTemp *= Integer.parseInt(queue);//����ϵ��
  					queue = emptyQueue(queue);//��ն���
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
  			char chr=coef.charAt(i); //������������charֵ
  		    if((chr<'0'||chr>'9')&& chr!= '*'&&chr!='-')
  		    	p.stringCoef = p.stringCoef+String.valueOf(chr); 
  		}
//  		System.out.printf("degree��%d\n",p.degree);
//  		System.out.printf("intCoef:%s\n",p.intCoef);
//  		System.out.printf("stringCoef:%s\n",p.stringCoef);
  		return p;
  	}
  //��ն���
  	private static String emptyQueue(String queue){
  		queue = "";
  		return queue;
  	}
  //������м���Ŀ���ַ�
  	private static String addQueue(String queue,char ch){
  		queue=queue+String.valueOf(ch);
  		return queue;
  	}
}
