package net.anotheria.maf.mocks;

import javax.servlet.http.HttpSession;

/**
 * Creates Mocked session implementation.
 *
 * @author h3llka
 */
public class MockHttpSessionFactory {

	/**
	 * Create mocked session.
	 *
	 * @return HttpSession
	 */
	public HttpSession createMockedSession() {
		return new HttpSessionMockImpl();
	}

}
