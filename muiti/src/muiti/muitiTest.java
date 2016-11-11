package muiti;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import muiti.muiti.Polynomial;
import muiti.muiti.valueCommand;

public class muitiTest {
	private String exp;
	private String result;
	//private muitiTest test = new muitiTest();
	private ArrayList<String> Mydiv = new ArrayList<String>();//ÇÐÆ¬
	private ArrayList<Polynomial> arr = new ArrayList<Polynomial>();
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSimplify() {
		exp = "2xx+3xy";
		muiti m = new muiti();
		ArrayList<valueCommand> valueGroup = new ArrayList<valueCommand>();
		result = "2xx+9x";
		Mydiv =m.stringCut(exp);
		String command = "!simplify y=3";
		int num = muiti.getBlankNum(command);
		valueGroup = muiti.commandDeal(command);
		String var = "y";
		int i=0;
		for (int k = 0;k < Mydiv.size();k++)
			arr.add(m.expression(var,Mydiv.get(k)));
		String testresult = m.simplify(arr,valueGroup,num,i);
		assertEquals(result, testresult);
	}
}

