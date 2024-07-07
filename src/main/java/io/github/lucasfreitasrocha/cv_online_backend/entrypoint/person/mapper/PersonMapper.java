package io.github.lucasfreitasrocha.cv_online_backend.entrypoint.person.mapper;

import io.github.lucasfreitasrocha.cv_online_backend.core.person.domain.PersonDomain;
import io.github.lucasfreitasrocha.cv_online_backend.entrypoint.person.dto.in.CreatePerson;
import io.github.lucasfreitasrocha.cv_online_backend.entrypoint.person.dto.out.PersonCreated;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mapping(target = "id", ignore = true)
    public PersonDomain toDomain(CreatePerson createPerson);

    public PersonCreated toPersonCreated(PersonDomain domain);
}
