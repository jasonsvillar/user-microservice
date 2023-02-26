package com.jasonvillar.userapi.user;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Document("user")
public class User {
    @Id
    private Long id;
    private String name;
    private String surname;
    private Date birthdate;
    private String colorFavorite;
    private String telephoneNumber;
    private Date createdAt = new Date();
}