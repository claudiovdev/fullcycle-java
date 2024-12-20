package com.fullcycle.admin.catalogo.domain.category;

import com.fullcycle.admin.catalogo.domain.AgregateRoot;
import com.fullcycle.admin.catalogo.domain.validation.ValidateHandler;

import java.time.Instant;
import java.util.UUID;

public class Category extends AgregateRoot<CategoryID> {
    private String name;
    private String description;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Category(CategoryID anId,
                    final String aName,
                    final String aDescription,
                    final boolean aisActive,
                    final Instant aCreatedAt,
                    final Instant aUpdateAt,
                    final Instant aDeleteAt
    ) {
        super(anId);
        this.name = aName;
        this.description = aDescription;
        this.active = aisActive;
        this.createdAt = aCreatedAt;
        this.updatedAt = aUpdateAt;
        this.deletedAt = aDeleteAt;
    }

    public static Category newCategory(final String aName, final String aDescription, final boolean aIsActive){
        final var id = CategoryID.unique();
        final var now = Instant.now();
        final var deletedAt = aIsActive ? null : now;
        return new Category(id,aName,aDescription,aIsActive,now,now,deletedAt);
    }

    @Override
    public void validate(ValidateHandler handler) {
        new CategoryValidator(this, handler).validate();
    }

    public Category deactivate() {
        if (getDeletedAt() == null){
            this.deletedAt = Instant.now();
        }

        this.active = false;
        this.updatedAt = Instant.now();
        return this;
    }

    public CategoryID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }


}
