package com.shoponeo.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Created by Tomasz on 20.10.2016.
 */
@Entity
@Table(name = "ROLES")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "ID")
    private int id;

    @Column(name = "AUTHORITY")
    private String authority;

    @Column(name = "GIVEN")
    private boolean given;

    @Column(name = "OWNER")
    private String owner;

    public Role() {
    }

    public Role(String authority, boolean given, String owner) {
        this.authority = authority;
        this.given = given;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isGiven() {
        return given;
    }

    public void setGiven(boolean given) {
        this.given = given;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Role{" +
                "owner='" + owner + '\'' +
                ", given=" + given +
                ", name='" + authority + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public String getAuthority() {
        if (given) {
            return authority;
        } else {
            return null;
        }
    }
}