package ru.sotnikov.football.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.sotnikov.football.models.Footballer;

import java.util.List;

@Component
public class FootballerDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FootballerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Footballer> index() {
        return jdbcTemplate.query("SELECT * FROM Footballer", new BeanPropertyRowMapper<>(Footballer.class));
    }

    public Footballer show(int id) {
        return jdbcTemplate.query("SELECT * FROM Footballer WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Footballer.class))
                .stream().findAny().orElse(null);
    }

    public void save(Footballer footballer) {
        jdbcTemplate.update("INSERT INTO Footballer(name, sex, year_birth, team_name, country) VALUES(?, ?, ?, ?, ?)", footballer.getName(), footballer.getSex(),
                footballer.getYearBirth(), footballer.getTeamName(), footballer.getCountry());
    }

    public void update(int id, Footballer updatedFootballer) {
        jdbcTemplate.update("UPDATE Footballer SET name=?, sex=?, year_birth=?, team_name=?, country=?  WHERE id=?", updatedFootballer.getName(),
                updatedFootballer.getSex(), updatedFootballer.getYearBirth(), updatedFootballer.getTeamName(),
                updatedFootballer.getCountry(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Footballer WHERE id=?", id);
    }

}