package ru.sotnikov.football.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Footballer")
public class Footballer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "name")
    private String name;

    @Column(name = "sex")
    private String sex;

    @Min(value = 1900, message = "Year should be greater than 1900")
    @Column(name = "year_birth")
    private int yearBirth;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "country")
    private String country;

    public Footballer() {
    }

    public Footballer(int id, String name, String sex, int yearBirth, String teamName, String country) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.yearBirth = yearBirth;
        this.teamName = teamName;
        this.country = country;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getYearBirth() {
        return yearBirth;
    }

    public void setYearBirth(int yearBirth) {
        this.yearBirth = yearBirth;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

