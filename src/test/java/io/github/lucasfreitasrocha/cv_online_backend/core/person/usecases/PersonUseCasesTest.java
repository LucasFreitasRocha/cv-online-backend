package io.github.lucasfreitasrocha.cv_online_backend.core.person.usecases;

import io.github.lucasfreitasrocha.cv_online_backend.core.exception.ContentError;
import io.github.lucasfreitasrocha.cv_online_backend.core.exception.CustomExceptionDomain;
import io.github.lucasfreitasrocha.cv_online_backend.core.exception.HandlerError;
import io.github.lucasfreitasrocha.cv_online_backend.core.person.domain.PersonDomain;
import io.github.lucasfreitasrocha.cv_online_backend.core.person.gateways.PersonDataProviderGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonUseCasesTest {

    public static final String TESTE = "teste";
    private static final String EMAIL = "teste@ts.com";
    @InjectMocks
    private PersonUseCases personUseCases;
    @Mock
    private  PersonDataProviderGateway gateway;
    @Mock
    private  HandlerError handlerError;


    @Test
    void createPersonSucess() {
        PersonDomain personDomain = new PersonDomain();
        personDomain.setCode(TESTE);
        personDomain.setEmail(EMAIL);
        when(gateway.findByCode(TESTE)).thenReturn(null);
        when(handlerError.getCustomExceptionDomain()).thenReturn(new CustomExceptionDomain());
        personUseCases.createPerson(personDomain);
        verify(handlerError, never()).handle();
        verify(handlerError, never()).addFieldError(any(),any());
    }

    @Test
    void createPersonErros() {
        PersonDomain personDomain = new PersonDomain();
        personDomain.setCode("1234567890101");
        personDomain.setEmail("Teste");
        when(gateway.findByCode("1234567890101")).thenReturn(new PersonDomain());
        CustomExceptionDomain customExceptionDomain = new CustomExceptionDomain();
        customExceptionDomain.getErrors().add(new ContentError());
        when(handlerError.getCustomExceptionDomain()).thenReturn(customExceptionDomain);
        personUseCases.createPerson(personDomain);
        verify(handlerError, times(3)).addFieldError(any(),any());
        verify(handlerError).handle();
    }
}