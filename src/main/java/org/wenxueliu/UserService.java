package org.wenxueliu;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserService {

  public boolean login(String username, String password) {
    if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
      throw new IllegalArgumentException("Username and password must not be null or empty");
    } else if ("admin".equals(username) && "password123".equals(password)) {
      return true;
    }
    return false;
  }

  public boolean changePassword(long userId, String oldPassword, String newPassword) {
    if (userId == 1 && StringUtils.isNotBlank(newPassword)
        && !newPassword.equals(oldPassword)) {
      return true;
    }
    return false;
  }

  public boolean resetPassword(long userId) {
    List<Long> existingUsers = new ArrayList<>(Arrays.asList(1L, 2L, 3L));
    if (existingUsers.contains(userId)) {
      return true;
    }
    return false;
  }

  public boolean logout(long userId) {
    List<Long> existingUsers = new ArrayList<>(Arrays.asList(1L, 2L, 3L));
    if (existingUsers.contains(userId)) {
      // do whatever
    }
    return true;
  }
}
