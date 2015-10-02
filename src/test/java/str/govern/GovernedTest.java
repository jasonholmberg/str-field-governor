/**
 * 
 */
package str.govern;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import str.govern.data.Users;
import str.govern.model.User;
import str.govern.service.UserService;
import str.govern.service.impl.MockUserService;

/**
 * @author Jason Holmberg
 *
 */
public class GovernedTest {

  private static final Logger log = LoggerFactory.getLogger(GovernedTest.class);
  
  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void test() {
    UserService userService = new MockUserService();
    User user = userService.findUser(Users.batman.username);
    log.debug(user.getDob());
  }

}
