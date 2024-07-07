package io.github.lucasfreitasrocha.cv_online_backend.entrypoint.person.dto.in;

public record CreatePerson(
        String code,
        String address,
        String phone,
        String name,
        String jobTitle,
        String email,
        String password
        ) {
}
