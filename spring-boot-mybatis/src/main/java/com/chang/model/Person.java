package com.chang.model;


import com.chang.enums.UserSexEnum;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String userName;
    private String passWord;
    private UserSexEnum userSex;
    private String nickName;

}