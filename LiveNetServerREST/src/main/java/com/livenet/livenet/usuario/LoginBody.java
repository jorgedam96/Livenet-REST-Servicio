package com.livenet.livenet.usuario;


import java.io.Serializable;

public class LoginBody implements Serializable {

    private String alias;
    private String pass;

    public LoginBody(String alias, String pass) {
        this.alias = alias;
        this.pass = pass;
    }

    public String getalias() {
        return alias;
    }

    public void setalias(String alias) {
        this.alias = alias;
    }

    public String getpass() {
        return pass;
    }

    public void setpass(String pass) {
        this.pass = pass;
    }
}