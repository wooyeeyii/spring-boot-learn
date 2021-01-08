package com.chang.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Data
@Component
public class TitleProperties {

    @Value("${com.chang.title}")
    private String title;

    @Value("${com.chang.description}")
    private String description;

    @Value("#{'${com.chang.headers}'.split(',')}")
    private List<String> headers;

    @PostConstruct
    public void init() {
        System.out.println("title: " + title);
        System.out.println("description: " + description);
        headers.forEach(h -> System.out.println("header: " + h));
    }

}
