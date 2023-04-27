package tamin.library.test1;

import com.google.gson.Gson;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
@Named
@SessionScoped
@Transactional
public class AccountBean implements Serializable {


    private User user=new User();
    private boolean loggedIn;

    private boolean admin;
    private String password1;
    private String password2;
    private boolean rememberMe;


    public User getUser() {
        return user;
    }

    public AccountBean setUser(User user) {
        this.user = user;
        return this;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public AccountBean setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
        return this;
    }

    public boolean isAdmin() {
        return admin;
    }

    public AccountBean setAdmin(boolean admin) {
        this.admin = admin;
        return this;
    }

    public String getPassword1() {
        return password1;
    }

    public AccountBean setPassword1(String password1) {
        this.password1 = password1;
        return this;
    }

    public String getPassword2() {
        return password2;
    }

    public AccountBean setPassword2(String password2) {
        this.password2 = password2;
        return this;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public AccountBean setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
        return this;
    }


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
