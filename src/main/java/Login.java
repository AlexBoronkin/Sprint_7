import org.apache.commons.lang3.RandomStringUtils;

public class Login {

    private String login;
    private String password;


    public Login(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public  Login() {

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Login getRandomLogin() {
        return new Login(
                RandomStringUtils.randomAlphanumeric(10) + "",
                RandomStringUtils.randomAlphanumeric(10) + ""
        );
    }


}
