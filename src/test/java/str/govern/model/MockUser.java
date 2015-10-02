/**
 * 
 */
package str.govern.model;

import str.govern.Governable;
import str.govern.Governed;
import str.govern.data.Status;
import str.govern.data.Users;
import str.govern.governors.StringMaskingGovernor;

public class MockUser implements User, Governable {

  final int id;
  final String dob;
  final String firstname;
  final String lastname;
  final String username;
  final byte[] password;
  final Status status;
  
  public MockUser(Users user) {
    this.id = user.id;
    this.dob = user.dob;
    this.firstname = user.name();
    this.lastname = user.lastname;
    this.username = user.name();
    this.password = user.password.getBytes();
    this.status = user.status;
  }
  
  @Override
  public int getId() {
    return id;
  }
  
  @Override
  @Governed(governor = StringMaskingGovernor.class)
  public String getDob() {
    return govern(dob, String.class);
  }
  
  @Override
  public String getFirstname() {
    return firstname;
  }

  @Override
  public String getLastname() {
    return govern(lastname, String.class);
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public byte[] getPassword() {
    return password;
  }

  @Override
  public Status getStatus() {
    return status;
  }
  
  @Override
  public String toString() {
    return username;
  }
}