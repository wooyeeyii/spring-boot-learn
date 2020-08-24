package com.chang.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MockPrivateMethodExample.class)
public class MockPrivateMethodTest {

	// This is the name of the private method which we want to mock
	private static final String METHOD = "iAmPrivate";
	
	@Test
	public void testPrivateMethod() throws Exception {
		MockPrivateMethodExample mockPrivateMethodExample = new MockPrivateMethodExample();
		
		MockPrivateMethodExample spy = PowerMockito.spy(mockPrivateMethodExample);
		PowerMockito.doReturn("Test").when(spy, METHOD);

		// when
		String value = spy.getDetails();
		System.out.println(value);
		
		Assert.assertEquals(value, "Mock private method example: Test");
		PowerMockito.verifyPrivate(spy, Mockito.times(1)).invoke(METHOD);
	}

}
