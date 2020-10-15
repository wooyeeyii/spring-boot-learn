package com.chang.multi.datasource.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beetl.sql.core.annotatoin.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dog")
public class Dog {

    private Integer id;

    private String name;

    private String category;
}
