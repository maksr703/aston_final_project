package org.example.model;

public class User {
    private String name, password, email;

    private User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public static class Builder {
        private String name, password, email;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public User create() {
            return new User(name, password, email);
        }
    }
    public boolean isPasswordEven() {
    try {
        int passwordValue = Integer.parseInt(this.password);
        return passwordValue % 2 == 0;
    } catch (NumberFormatException e) {
        return false;
    }
}

    @Override
    public String toString() {
        return name + " " + password + " " + email;
    }
}
