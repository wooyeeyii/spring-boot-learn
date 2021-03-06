package com.chang.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = true, unique = true)
    private String nickName;

    @Column(nullable = false)
    private String regTime;

//    @Temporal(TemporalType.DATE)
    private Date createTime;

    public User(String userName, String password, String email, String nickName, String regTime, Date createTime) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.nickName = nickName;
        this.regTime = regTime;
        this.createTime = createTime;
    }

}