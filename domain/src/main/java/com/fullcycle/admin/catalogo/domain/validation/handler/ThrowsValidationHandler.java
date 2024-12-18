package com.fullcycle.admin.catalogo.domain.validation.handler;

import com.fullcycle.admin.catalogo.domain.exceptions.DomainException;
import com.fullcycle.admin.catalogo.domain.validation.Error;
import com.fullcycle.admin.catalogo.domain.validation.ValidateHandler;

import java.util.List;

public class ThrowsValidationHandler implements ValidateHandler {
    @Override
    public ValidateHandler append(Error anError) {
        throw DomainException.with(List.of(anError));
    }

    @Override
    public ValidateHandler append(ValidateHandler anHandler) {
        throw DomainException.with(anHandler.getErrors());
    }

    @Override
    public ValidateHandler append(Validation anValidation) {
       try{
           anValidation.validate();
       }catch (final Exception ex){
           throw DomainException.with(new Error(ex.getMessage()));
       }

        return this;
    }

    @Override
    public List<Error> getErrors() {
        return List.of();
    }
}
