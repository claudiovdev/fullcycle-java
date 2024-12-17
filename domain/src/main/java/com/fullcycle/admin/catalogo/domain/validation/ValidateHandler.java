package com.fullcycle.admin.catalogo.domain.validation;

import java.util.List;

public interface ValidateHandler {

    ValidateHandler append(Error anError);

    ValidateHandler append(ValidateHandler anHandler);

    ValidateHandler append(Validation anValidation);

    default boolean hasError(){
        return getErrors() != null && !getErrors().isEmpty();
    }

    List<Error> getErrors();
    public interface Validation{
        void validate();
    }
}
