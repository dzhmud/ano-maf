package net.anotheria.maf;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(value=Suite.class)
@SuiteClasses({ActionFactoryTest.class, TestMappings.class, TestEmptyFilterBehavior.class})
public class AnoMafTestSuite {
	
}
