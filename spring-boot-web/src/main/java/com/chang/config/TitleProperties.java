package com.chang.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class TitleProperties {

    @Value("${com.chang.title}")
    private String title;

    @Value("${com.chang.description}")
    private String description;

}
