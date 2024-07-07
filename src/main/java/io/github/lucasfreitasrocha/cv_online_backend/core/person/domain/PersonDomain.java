package io.github.lucasfreitasrocha.cv_online_backend.core.person.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonDomain {

    private String id;
    private String code;
    private String address;
    private String phone;
    private String name;
    private String jobTitle;
    private String email;
    private String password;

}
