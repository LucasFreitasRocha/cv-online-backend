package io.github.lucasfreitasrocha.cv_online_backend.dataprovider.person;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity(name = "person")
@Getter
@Setter
public class PersonEntity {
    @Id
    private UUID id;
    @Column(unique = true, length=12)
    private String code;
    private String address;
    private String phone;
    private String name;
    private String jobTitle;
    private String email;
    private String password;

}
