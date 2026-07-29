package ng.pharmacy.dto.response.authServiceResponse;

public class LoginChemistResponse {
    private String userName;
    private boolean isLoggedIn;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}
