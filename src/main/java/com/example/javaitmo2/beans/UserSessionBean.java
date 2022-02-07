package com.example.javaitmo2.beans;

public class UserSessionBean {

    private String userUuid;

    public void doMyInit() {
        System.out.println("Bean init");
    }

    public void doMyDestroy() {
        System.out.println("Bean destroy");
    }

    public void setUserUuid(String userUuid) {
        System.out.println("Bean set uid " + userUuid);

        this.userUuid = userUuid;
    }

    public String getUserUuid() {
        return userUuid;
    }
}
