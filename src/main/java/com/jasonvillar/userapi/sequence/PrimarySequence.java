package com.jasonvillar.userapi.sequence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document("primary_sequence")
public class PrimarySequence {
    @Id
    private String id;

    private long seq;
}