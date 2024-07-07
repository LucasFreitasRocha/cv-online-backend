package io.github.lucasfreitasrocha.cv_online_backend.entrypoint.person.service;

import io.github.lucasfreitasrocha.cv_online_backend.core.person.domain.PersonDomain;
import io.github.lucasfreitasrocha.cv_online_backend.core.person.gateways.PersonUseCasesGateway;
import io.github.lucasfreitasrocha.cv_online_backend.entrypoint.person.dto.in.CreatePerson;
import io.github.lucasfreitasrocha.cv_online_backend.entrypoint.person.dto.out.PersonCreated;
import io.github.lucasfreitasrocha.cv_online_backend.entrypoint.person.mapper.PersonMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonService {
    private final PersonUseCasesGateway personUseCases;
    private final PersonMapper personMapper;

    public PersonCreated createPerson(CreatePerson createPerson){
        PersonDomain domain =  personUseCases.createPerson(personMapper.toDomain(createPerson));
        return personMapper.toPersonCreated(domain);
    }
}
