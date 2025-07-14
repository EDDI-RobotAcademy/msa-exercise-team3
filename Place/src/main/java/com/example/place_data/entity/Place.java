package com.example.place_data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long place_id;

    private String title;
    private String content;
    private String category;
    private String location;
    private String address;

    private Long accountId;

    public Place(String title, String content, String category, String location, String address) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.location = location;
        this.address = address;
    }

    public Place(String title, String content, String category, String location, String address, Long accountId) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.location = location;
        this.address = address;
        this.accountId = accountId;
    }
}
