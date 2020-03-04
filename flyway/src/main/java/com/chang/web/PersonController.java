package com.chang.web;

import com.chang.datasource.DataSource2Config;
import com.chang.mapper.test1.PersonMapper1;
import com.chang.mapper.test2.PersonMapper2;
import com.chang.model.Person;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonMapper1 mapper1;

    @Autowired
    private PersonMapper2 mapper2;

    @RequestMapping("/test1/getPersons")
    public List<Person> getPersonsFromSource1() {
        List<Person> Persons = mapper1.getAll();
        return Persons;
    }

    @RequestMapping("/test2/getPersons")
    public List<Person> getPersonsFromSource2() {
        List<Person> Persons = mapper2.getAll();
        return Persons;
    }

    @RequestMapping("/test2/migrate")
    public String migrateSource1() {
        String url = "jdbc:mysql://localhost:3307/test2?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=true";
        // Create the Flyway instance and point it to the database
        Flyway flyway = Flyway.configure()
                .dataSource(url, "root", "123456")
                .baselineOnMigrate(true)
                .baselineVersion("6")
                .locations("/db/migration/test2")
                .encoding("UTF-8")
                .load();

        // Start the migration
        flyway.migrate();
        return "ok";
    }

}
