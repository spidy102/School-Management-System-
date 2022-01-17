

public class User {
    private String username;
    private String password;
    private String role;
    private String name;

    public static double TOTAL_DAYS = 150;
    public static double TOTAL_MARKS = 100;

    public User(String username, String password, String role) {
        this.username = username; // Revisit
        this.password = password; // Revisit
//        this.name = name;
        if (isValidRole(role)) {
            this.role = role;
        } else {
            throw new IllegalArgumentException("User not valid!");
        }
    }

    private Boolean isValidRole(String role) {
        switch (role){
            case "teacher":
            case "student":
            case "admin":
                return true;
            default:
                return false;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (isValidRole(role)) {
            this.role = role;
        } else {
            throw new IllegalArgumentException("User not valid!");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "[ " + "username: " + username + " " + "name: " + name + " " + "role: " + role + " ]";
    }

}
