package cn.mrdear.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by shining on 2018/7/3.
 */
@Data
public class User implements Serializable{

    private Long id;
    private String name;
    private  int age;
    private String gender;

    public User(Long id, String name, int age, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public User() {
    }
}
