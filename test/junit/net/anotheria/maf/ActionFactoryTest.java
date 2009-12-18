package net.anotheria.maf;

import org.junit.Test;
import static org.junit.Assert.*;

public class ActionFactoryTest {
	@Test public void testSingleton() throws ActionFactoryException{
		Action a1 = ActionFactory.getInstanceOf("net.anotheria.maf.TestAction");
		Action a2 = ActionFactory.getInstanceOf("net.anotheria.maf.TestAction");
		assertSame(a1,a2);
	}
	
	@Test(expected=ActionFactoryException.class) public void nonExisting() throws ActionFactoryException{
		ActionFactory.getInstanceOf("nonexisting");
		fail("exception expected");
	}
}
