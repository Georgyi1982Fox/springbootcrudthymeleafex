package com.georgyi.springbootcrud.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles", joinColumns = {@JoinColumn(name = "users_id")},
            inverseJoinColumns = {@JoinColumn(name = "roles_id")})
    private Set<Role> roles;

    public User(){}

    public User(Long id){
        this.id = id;
    }

    public User(Long id, String name, String password, String email){
        this.id = id;
        this.userName = name;
        this.password = password;
        this.email = email;
    }

    public User(Long id, String name, String password, String email, Set<Role>roles){
        this.id = id;
        this.userName = name;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public User(Long id, String name, String email, Set<Role> roles){
        this.id = id;
        this.userName = name;
        this.email = email;
        this.roles = roles;

    }

    public User(String name, String password, String email){
        this.userName = name;
        this.password = password;
        this.email = email;
    }

    public User(String name, String password,Set<Role> roles ){
        this.userName = name;
        this.password = password;
        this.roles = roles;
    }

    public User(Long id, String name, String email){
        this.id = id;
        this.userName = name;
        this.email = email;

    }

    public User(String name, String email){
        this.userName = name;
        this.email = email;
    }

    public User(String username) {
        this.userName = username;
    }

    public User(String username, String password, String email, Set<Role> roles) {
        this.userName=username;
        this.password=password;
        this.email=email;
        this.roles=roles;
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

    public void setUsername(String username) {
        this.userName = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {

        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }
}
