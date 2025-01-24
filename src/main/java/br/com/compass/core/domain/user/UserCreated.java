package br.com.compass.core.domain.user;

public class UserCreated {
    private final User user;
    private final String accountType;

    public UserCreated(User user, String accountType) {
        this.user = user;
        this.accountType = accountType;
    }

    public User getUser() {
        return user;
    }

    public String getAccountType() {
        return accountType;
    }
}
