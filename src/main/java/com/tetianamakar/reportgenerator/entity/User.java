package com.tetianamakar.reportgenerator.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
    })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    private String password;

//    @OneToMany(mappedBy = "user")
//    private List<Article> articles = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(String username, String password) {
        this.name = username;
        this.password = password;
    }
}
