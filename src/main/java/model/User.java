package model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Name",nullable = false,unique = true,length = 100)
    private String name;

    @Column(name = "Email",nullable = false,unique = true,length = 100)
    private String email;

    @Column(name = "Password",nullable = false)
    private String password;

    @Column(name = "Salt",nullable = false)
    private String salt;

    @Column(name = "Rol",nullable = false,length = 40)
    private String rol;

    @Column(name="IsBanned")
    private Boolean isBanned;

    @Column(name = "BanExpirationDate")
    private LocalDateTime banExpirationDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Park> parkList;





}
