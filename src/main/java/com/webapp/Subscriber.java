package com.webapp;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Subscriber /*implements UserDetails */{

    private static final long serialVersionUID = 1L;

    @Id
    private String username;
    private String fullname;
    private String password;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //UserDetails Security Methods
/*
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("SUBSCRIBER"));
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
*/
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Subscriber{");
        sb.append("fullname='").append(fullname).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
