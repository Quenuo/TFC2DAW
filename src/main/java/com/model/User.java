package com.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Name",nullable = false,length = 100)
    private String name;

    @Column(name = "Email",nullable = false,unique = true,length = 100)
    private String email;

    @Column(name = "Password",nullable = false)
    private String password;

    @Column(name = "Salt",nullable = false)
    private String salt;

    @Column(name = "Rol",length = 40)
    private String rol;

    @Column(name="IsBanned")
    private Boolean isBanned;

    @Column(name = "BanExpirationDate")
    private LocalDateTime banExpirationDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Park> parkList;


    public User(){

    }

    public User(String password, String email,String salt,String name){
        this.password=password;
        this.email=email;
        this.salt=salt;
        this.name=name;
    }
    public String getPassword(){
        return password;
    }

    public String getSalt(){
        return salt;
    }

    @Override
    public boolean equals(Object o) {
        //el criterio que he puesto para que dos usuarios sean el mismo es que tengan el mismo email
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }
}
