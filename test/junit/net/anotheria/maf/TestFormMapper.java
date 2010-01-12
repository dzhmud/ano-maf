package net.anotheria.maf;

import net.anotheria.maf.util.ModelObjectMapper;
import net.anotheria.webutils.servlet.request.HttpServletRequestMockImpl;
import net.anotheria.webutils.servlet.request.MockServletRequestFactory;
import org.junit.Assert;
import org.junit.Test;

import javax.print.attribute.standard.Destination;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TestFormMapper {
	private static final String CONTEXT_PATH = "/";
	private static final String SERVER_NAME = "localhost";


	@Test
	public void shouldMapRequestParameterToModelField() {
		// given
		Map<String, String> params = new HashMap<String, String>();
		params.put("a1", "1");
		params.put("a.sizeOfA", "7");
		Map<String, Object> attributes = new HashMap<String, Object>();
		HttpServletRequest req = MockServletRequestFactory.createMockedRequest(params, attributes, CONTEXT_PATH, SERVER_NAME, Locale.ENGLISH, 80);
		CompositeObject destination = new CompositeObject();
		// when
		ModelObjectMapper.map(req, destination);

		// then
		Assert.assertEquals(Integer.valueOf(params.get("a1")), Integer.valueOf(destination.getA1()));
		Assert.assertEquals(Integer.valueOf(params.get("a.sizeOfA")), Integer.valueOf(destination.getA().getSizeOfA()));

	}


	private class CompositeObject {
		private A a;
		private int a1;

		public A getA() {
			return a;
		}

		public void setA(A a) {
			this.a = a;
		}

		private class A {
			private int sizeOfA;

			public int getSizeOfA() {
				return sizeOfA;
			}

			public void setSizeOfA(int sizeOfA) {
				this.sizeOfA = sizeOfA;
			}
		}

		public int getA1() {
			return a1;
		}

		public void setA1(int a1) {
			this.a1 = a1;
		}
	}
}