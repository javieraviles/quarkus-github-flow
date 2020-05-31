package com.javieraviles;

import java.util.List;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Developer extends PanacheEntity{
    @NotBlank
    public String name;
    @Email
    public String email;

    public static List<Developer> search(String word) {
        return list("name like ?1", "%" + word + "%");
    }

}