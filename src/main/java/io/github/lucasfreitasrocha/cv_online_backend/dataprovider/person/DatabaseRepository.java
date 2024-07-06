package io.github.lucasfreitasrocha.cv_online_backend.dataprovider.person;

import com.fasterxml.uuid.Generators;
import io.github.lucasfreitasrocha.cv_online_backend.core.person.domain.PersonDomain;
import io.github.lucasfreitasrocha.cv_online_backend.core.person.gateways.PersonDataProviderGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class DatabaseRepository implements PersonDataProviderGateway {

    private final PersonRepository repository;
    private final PersonDbMapper mapper;

    @Override
    public PersonDomain save(PersonDomain personDomain) {
        PersonEntity entity = mapper.toEntity(personDomain);
        entity.setId(Generators.timeBasedEpochGenerator().generate());
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public PersonDomain findByCode(String code) {
        return mapper.toDomain(repository.findByCode(code));
    }
}
