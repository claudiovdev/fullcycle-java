package com.fullcycle.admin.catalogo.domain;

public class AgregateRoot<ID extends Identifier> extends Entity<ID>{
    protected AgregateRoot(final ID id) {
        super(id);
    }
}
