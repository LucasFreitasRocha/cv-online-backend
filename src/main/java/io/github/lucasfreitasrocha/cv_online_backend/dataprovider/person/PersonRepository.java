package io.github.lucasfreitasrocha.cv_online_backend.dataprovider.person;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<PersonEntity, UUID> {
    PersonEntity findByCode(String code);
}
