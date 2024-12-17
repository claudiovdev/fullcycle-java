package com.fullcycle.admin.catalogo.domain.validation;

public abstract class Validator {

    private final ValidateHandler handler;

    protected Validator(ValidateHandler aHandler){
        this.handler = aHandler;
    }

    public abstract void validate();

    protected  ValidateHandler validateHandler(){
        return this.handler;
    }
}
