package com.fsb.chatapplication.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
public class User {
    @Id
    @Column(nullable = false,name = "user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userName;
}
