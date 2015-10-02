/**
 * 
 */
package str.govern.service;

import java.util.List;
import java.util.Set;

import str.govern.model.User;

/**
 * @author Jason Holmberg
 *
 */
public interface UserService {
  User findUser(String username);
  List<User> findUsers();
  Set<String> getUserRoles(String username);
  Set<String> getPermissions(String username);
}
