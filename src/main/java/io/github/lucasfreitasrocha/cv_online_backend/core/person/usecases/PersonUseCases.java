package io.github.lucasfreitasrocha.cv_online_backend.core.person.usecases;

import io.github.lucasfreitasrocha.cv_online_backend.core.exception.HandlerError;
import io.github.lucasfreitasrocha.cv_online_backend.core.person.gateways.PersonDataProviderGateway;
import io.github.lucasfreitasrocha.cv_online_backend.core.person.gateways.PersonUseCasesGateway;
import io.github.lucasfreitasrocha.cv_online_backend.core.person.domain.PersonDomain;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public class PersonUseCases implements PersonUseCasesGateway {

    private final PersonDataProviderGateway gateway;
    private final HandlerError handlerError;

    @Override
    public PersonDomain createPerson(PersonDomain personDomain) {

        if(foundByCode(personDomain.getCode())){
            handlerError.init().addFieldError("code", "must be unique").handle();
        }
        return gateway.save(personDomain);
    }

    private boolean foundByCode(String code){
        return !Objects.isNull(gateway.findByCode(code));
    }
}
