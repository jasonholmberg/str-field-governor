/**
 * 
 */
package str.govern.model;

import str.govern.data.Status;

/**
 * @author Jason Holmberg
 *
 */
public interface User {
  int getId();
  String getDob();
  String getFirstname();
  String getLastname();
  String getUsername();
  byte[] getPassword();
  Status getStatus();
}
