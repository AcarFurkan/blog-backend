package com.acar.project.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;


@Table(name = "app_user")
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String userName;
    String password;
}
/*
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/blog?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey
spring.datasource.username=root
spring.datasource.password=F123123.
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

*/
