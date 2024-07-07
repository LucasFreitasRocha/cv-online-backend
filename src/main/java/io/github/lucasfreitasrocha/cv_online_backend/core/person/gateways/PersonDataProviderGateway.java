package io.github.lucasfreitasrocha.cv_online_backend.core.person.gateways;

import io.github.lucasfreitasrocha.cv_online_backend.core.person.domain.PersonDomain;

public interface PersonDataProviderGateway {

    PersonDomain save(PersonDomain personDomain);

    PersonDomain findByCode(String code);
}
