package org.example;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "USER")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class User {
    @Id //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer userId;
    @Column(name = "User_Name")
    private String userName;

    @Column(name = "User_Role")
    private String userRole;
}
