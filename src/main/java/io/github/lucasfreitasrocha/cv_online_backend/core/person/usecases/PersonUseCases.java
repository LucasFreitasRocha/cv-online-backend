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
        validateCreatePerson(personDomain);
        return gateway.save(personDomain);
    }

    private void validateCreatePerson(PersonDomain personDomain){
        handlerError.init();
        if(!Objects.isNull(gateway.findByCode(personDomain.getCode()))){
            handlerError.addFieldError("code", "must be unique");
        }

        if(personDomain.getCode().length() > 12){
            handlerError.addFieldError("code", "must be less than 12 characters");
        }

        if(!validateEmail(personDomain.getEmail())){
            handlerError.addFieldError("email", "must be valid");
        }
        if(!handlerError.getCustomExceptionDomain().getErrors().isEmpty()){
            handlerError.handle();
        }
    }

    private boolean validateEmail(String email){
        if(email==null) return false;
        if(email.contains(" ")) return false;
        return email.matches("[\\w\\S]+[@]+[\\w\\S]+[.]+[\\w\\S]+");
    }

}
