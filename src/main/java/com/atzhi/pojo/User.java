package com.atzhi.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int pri;
    private String username;
    private String passWord;
    private String Captcha;
    private String inputCaptcha;
}
