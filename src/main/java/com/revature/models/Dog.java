package com.revature.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "dogs")
@Component
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    private int age;

    @ManyToOne
    @JoinColumn(name = "ownerId")
    private Owner owner;

    public Dog() {
    }

    public Dog(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Dog(int id, String name, int age, Owner owner) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", owner=" + owner +
                '}';
    }
}
