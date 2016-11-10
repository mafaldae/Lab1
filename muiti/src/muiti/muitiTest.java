package muiti;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import muiti.muiti.Polynomial;

public class muitiTest {
	private String exp;
	private String result;
	//private muitiTest test = new muitiTest();
	private ArrayList<String> Mydiv = new ArrayList<String>();//切片
	private ArrayList<Polynomial> arr = new ArrayList<Polynomial>();
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDerivative() {
		System.out.println("求导功能测试 用例1");
		exp = "2*x+3*x*y";
		muitiTest test = new muitiTest();
		muiti m = new muiti();
		result = "2+3*y";
		Mydiv =m.stringCut(exp);
		String var = "x";
		for (int k = 0;k < Mydiv.size();k++)
			arr.add(m.expression(var,Mydiv.get(k)));
		String testresult = m.derivative(arr, var);
		assertEquals(result, testresult);
		//Assert.assertEquals(i+1, muiti.expression(""));
		//fail("Not yet implemented");
	}

}
