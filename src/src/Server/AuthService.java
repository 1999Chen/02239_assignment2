package Server;
import java.util.HashMap;

public class AuthService {
    private HashMap<String,String> users = new HashMap<>();

    public AuthService() {
        users.put("admin1","password1");
        users.put("admin2","password2");
    }
//检查用户名和密码是否匹配
    public boolean Authenticate(String username, String password) {
        if (users.containsKey(username)) {
            if (users.get(username).equals(password)) {
                return true;
            }
        }
        return false;
    }
}
