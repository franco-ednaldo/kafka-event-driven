package com.example.orderservice.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class User {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;
}
