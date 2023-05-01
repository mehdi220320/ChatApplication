// Message class
package com.fsb.chatapplication.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long mid;
    private String senderEmail;
    private Date time = new Date(System.currentTimeMillis());
    private String replymessage;
    private String firstUsername;
    private String secondUsername;
    @ManyToOne
    @JsonIgnore
    private Chat chat;
}
