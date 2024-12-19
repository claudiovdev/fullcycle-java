package com.fullcycle.admin.catalogo.domain.category;

import com.fullcycle.admin.catalogo.domain.validation.Error;
import com.fullcycle.admin.catalogo.domain.validation.ValidateHandler;
import com.fullcycle.admin.catalogo.domain.validation.Validator;

public class CategoryValidator extends Validator {

    public static final int NAME_MAX_LENGTH = 255;
    public static final int NAME_MIN_LENGTH = 3;
    private final Category category;

    protected CategoryValidator(final Category category, final ValidateHandler aHandler) {
        super(aHandler);
        this.category = category;
    }

    @Override
    public void validate() {
        checkNameConstraint();
    }

    private void checkNameConstraint() {
        final var name = this.category.getName();
        if (name == null){
            this.validateHandler().append(new Error("'name' should not be null"));
            return;
        }

        if (name.isBlank()){
            this.validateHandler().append(new Error("'name' should not be empty"));
            return;
        }
        final var legth = name.trim().length();
        if (legth > NAME_MAX_LENGTH || legth < NAME_MIN_LENGTH){
            this.validateHandler().append(new Error("'name' must be between 3 and 255 characters"));
            return;
        }
    }
}
