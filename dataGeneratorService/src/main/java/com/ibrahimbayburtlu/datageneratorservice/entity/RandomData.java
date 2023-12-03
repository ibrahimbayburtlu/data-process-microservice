package com.ibrahimbayburtlu.datageneratorservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.sql.Timestamp;
import java.util.UUID;


@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class RandomData {

    @Id
    private String id;

    @Field
    private Timestamp timestamp;

    @Field
    private int randomNumber;

    @Field
    private String lastTwoCharacters;

    @Field
    private String md5Hash;

    @Field
    private String data;

    public RandomData(Timestamp timestamp, int randomNumber, String lastTwoCharacters, String md5Hash, String data) {
        this.id = UUID.randomUUID().toString();
        this.timestamp = timestamp;
        this.randomNumber = randomNumber;
        this.lastTwoCharacters = lastTwoCharacters;
        this.md5Hash = md5Hash;
        this.data = data;
    }
}
