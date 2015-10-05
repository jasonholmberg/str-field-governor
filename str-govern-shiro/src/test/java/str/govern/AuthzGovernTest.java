package str.govern;

import static org.junit.Assert.*;

import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AuthzGovernTest implements AuthzGovernable {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		ThreadContext.unbindSubject();
		ThreadContext.unbindSecurityManager();
	}

	@Test
	public final void test() {
		fail("Not yet implemented"); // TODO
	}
	
	class MockService {
		
		private final String value;
		
		public MockService(String value) {
			this.value = value;
		}
		
		@Governed
		@Authorized("secret")
		public String getValue() {
			return value;
		}
		
	}
	
}
