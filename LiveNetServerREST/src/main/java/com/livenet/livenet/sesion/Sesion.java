package com.livenet.livenet.sesion;

import javax.persistence.*;

@Entity
@Table(name="sesiones")
public class Sesion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "alias", nullable = false,unique = true)
    private String alias;

    @Column(name = "token", nullable = false,unique = true)
    private String token;

    @Column(name = "loggedin", nullable = false)
    private long loggedin;

    @Column(name = "loggedout", nullable = false)
    private long loggedout;


    public Sesion(){}

    public Sesion(String alias, String token, long loggedin, long loggedout){
        this.alias = alias;
        this.token = token;
        this.loggedin = loggedin;
        this.loggedout = loggedout;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getLoggedin() {
        return loggedin;
    }

    public void setLoggedin(long loggedin) {
        this.loggedin = loggedin;
    }

    public long getLoggedout() {
        return loggedout;
    }

    public void setLoggedout(long loggedout) {
        this.loggedout = loggedout;
    }
}
