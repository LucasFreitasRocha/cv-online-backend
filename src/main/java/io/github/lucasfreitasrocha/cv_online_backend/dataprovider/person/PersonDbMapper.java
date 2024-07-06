package io.github.lucasfreitasrocha.cv_online_backend.dataprovider.person;

import io.github.lucasfreitasrocha.cv_online_backend.core.person.domain.PersonDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Objects;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface PersonDbMapper {
    @Mapping(source = "id", target = "id", qualifiedByName = "stringToUUID")
    PersonEntity toEntity(PersonDomain domain);
    @Mapping(source = "id", target = "id", qualifiedByName = "UUIDToString")
    PersonDomain toDomain(PersonEntity entity);


    @Named(value = "stringToUUID")
    static UUID stringToUUID(String id) {
        return (Objects.isNull(id)) ? null  : UUID.fromString(id);

    }

    @Named(value = "UUIDToString")
    static String uuidToString(UUID uuid){
        return (Objects.isNull(uuid)) ? null  : uuid.toString();
    }
}
