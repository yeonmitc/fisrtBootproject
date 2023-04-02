package com.boot.fisrt.Form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
// 화면에서만 받아오는  userDate
public class MemberForm {
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String id;
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String pw;
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

}
