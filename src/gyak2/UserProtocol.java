/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gyak2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Ádám
 */
class User {

    private String userName, password;

    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "userName=" + userName + ", password=" + password + '}';
    }

}

class UserProtocol {

    private List<User> users = new ArrayList<>();
    private String userName = null, newUsername, newPassword;
    private int state = 0;
    private final int WAITING = 0;
    private final int GETUSERNAME = 1;
    private final int GETPASSWORD = 2;
    private final int LOGGEDIN = 3;
    private final int NEWUSER = 4;
    private final int GETNEWPASSWORD = 5;
    private final int CONFIRMPASSWORD = 6;

    public UserProtocol() {
        users.add(new User("bela", "bela"));
    }

    public String processInput(String theInput) {
        System.out.println("processInput");
        String theOutput = null;
        if (WAITING == state) {
            theOutput = "Username: ";
            state = GETUSERNAME;
        } else if (GETUSERNAME == state) {
            if (checkUserName(theInput)) {
                this.userName = theInput;
                theOutput = "Password: ";
                state = GETPASSWORD;
            } else {
                theOutput = "? Username: ?";
            }
        } else if (GETPASSWORD == state) {
            if (login(new User(userName, theInput))) {
                theOutput = ">> (n)ew, (l)ogout";
                state = LOGGEDIN;
            } else {
                theOutput = "? Username: ?";
                userName = null;
                state = GETUSERNAME;
            }
        } else if (LOGGEDIN == state) {
            if (theInput.equalsIgnoreCase("n")) {
                theOutput = "New name: ";
                state = NEWUSER;
            }
            if (theInput.equalsIgnoreCase("l")) {
                theOutput = "Bye. Please press enter...";
                this.userName = null;
                state = WAITING;
            }
        } else if (NEWUSER == state) {
            if (!checkUserName(theInput)) {
                this.newUsername = theInput;
                theOutput = "User password: ";
                state = GETNEWPASSWORD;
            } else {
                theOutput = "Username already in use. New username: ";
            }
        } else if (GETNEWPASSWORD == state) {
            if (theInput != null) {
                this.newPassword = theInput;
                theOutput = "Confirm password: ";
                state = CONFIRMPASSWORD;
            } else {
                theOutput = "User password: ";
            }
        } else if (CONFIRMPASSWORD == state) {
            if (newPassword.equals(theInput)) {
                users.add(new User(newUsername, newPassword));
                newUsername = "";
                newPassword = "";
                theOutput = ">> (n)ew, (l)ogout";
            } else {
                theOutput = "Password must match. (n)ew, (l)ogout";
            }
            state = LOGGEDIN;
        }

        return theOutput;
    }

    private boolean checkUserName(String userName) {
        if (userName == null) {
            return false;
        }
        int i = 0;
        while (i < users.size() && !users.get(i).getUserName().equals(userName)) {
            i++;
        }
        return i < users.size();
    }

    private boolean login(User user) {
        return users.contains(user);
    }
}
