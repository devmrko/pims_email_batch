package pims_email_batch;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;

public class PropertiesTest extends TestCase {

	private static final Logger logger = LoggerFactory.getLogger(PropertiesTest.class);

	@Test
	public void testOfTest() {
		String testValue = "111";
		assertEquals("111", testValue);
	}

}
