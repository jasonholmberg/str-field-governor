package str.govern.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import str.govern.data.Roles;
import str.govern.data.RolesPerms;
import str.govern.data.UserPerms;
import str.govern.data.Users;
import str.govern.data.UsersRoles;
import str.govern.model.MockUser;
import str.govern.model.User;
import str.govern.service.UserService;

public class MockUserService implements UserService {

  @Override
  public User findUser(String username) {
    User user = new MockUser(Users.valueOf(username));
    return user;
  }

  @Override
  public List<User> findUsers() {
    List<User> users = new ArrayList<>();
    for (Users u : Users.values()) {
      users.add(new MockUser(u));
    }
    return users;
  }

  @Override
  public Set<String> getUserRoles(String username) {
    UsersRoles usersRoles = UsersRoles.valueOf(username);
    Set<String> roles = new HashSet<>();
    for (Roles role : usersRoles.roles) {
      roles.add(role.name());
    }
    return roles;
  }

  @Override
  public Set<String> getPermissions(String username) {
    Set<String> permissions = new HashSet<>();
    for (Roles role : UsersRoles.valueOf(username).roles) {
      for (String perm : RolesPerms.valueOf(role.name()).perms) {
        permissions.add(perm);
      }
    }

    for (String perm : UserPerms.valueOf(username).perms) {
      permissions.add(perm);
    }
    return permissions;
  }

}