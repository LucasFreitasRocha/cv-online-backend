package io.github.lucasfreitasrocha.cv_online_backend.core.person.gateways;

import io.github.lucasfreitasrocha.cv_online_backend.core.person.domain.PersonDomain;

public interface PersonUseCasesGateway {

    public PersonDomain createPerson(PersonDomain personDomain);
}
