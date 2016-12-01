package muiti2;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class muiti2Test {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		String exp;
		String result;
		//private muitiTest test = new muitiTest();
		ArrayList<String> Mydiv = new ArrayList<String>();//ÇÐÆ¬
		ArrayList<Polynomial> arr = new ArrayList<Polynomial>();
		simpCommand sc = new simpCommand();
		Polynomial p = new Polynomial();
		simplify s = new simplify();
		
		exp = "2xx+3xy";
		muiti2 m = new muiti2();
		ArrayList<simpCommand> valueGroup = new ArrayList<simpCommand>();
		result = "2xx+9x";
		Mydiv =m.stringCut(exp);
		String command = "!simplify y=3";
		int num = sc.getBlankNum(command);
		valueGroup = sc.commandDealS(command);
		String var = "y";
		int i=0;
		for (int k = 0;k < Mydiv.size();k++)
			arr.add(p.expression(var,Mydiv.get(k)));
		String testresult = s.simplifyopt(arr,valueGroup,num,i);
		assertEquals(result, testresult);
	}

}
