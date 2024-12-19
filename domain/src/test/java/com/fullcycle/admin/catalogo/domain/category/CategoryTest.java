package com.fullcycle.admin.catalogo.domain.category;

import com.fullcycle.admin.catalogo.domain.exceptions.DomainException;
import com.fullcycle.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    public void givenAValidParams_whenCallNewCategory_thenInstantiateACategory(){
        final var expectedName = "Filmes";
        final var expectedDescription = "A categproa mais assistida";
        final var expectedIsActive = true;
        final var actualCategory =  Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        assertNotNull(actualCategory);
        assertNotNull(actualCategory.getId());
        assertEquals(expectedName, actualCategory.getName());
        assertEquals(expectedDescription, actualCategory.getDescription());
        assertEquals(expectedIsActive, actualCategory.isActive());
        assertNotNull(actualCategory.getCreatedAt());
        assertNotNull(actualCategory.getUpdatedAt());
        assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAnInvelidNullName_whenCallNewCategoryAnValidate_thenShouldError(){
        final String expectedName = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedDescription = "A categproa mais assistida";
        final var expectedIsActive = true;
        final var actualCategory =  Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException = assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        assertEquals(expectedErrorCount,actualException.getErrors().size());
        assertEquals(expectedErrorMessage,actualException.getErrors().get(0).message());

    }

    @Test
    public void givenAnInvelidEmptyName_whenCallNewCategoryAnValidate_thenShouldError(){
        final String expectedName = "  ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";
        final var expectedDescription = "A categproa mais assistida";
        final var expectedIsActive = true;
        final var actualCategory =  Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException = assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        assertEquals(expectedErrorCount,actualException.getErrors().size());
        assertEquals(expectedErrorMessage,actualException.getErrors().get(0).message());

    }

    @Test
    public void givenAnInvelidNameLengthLessThan3_whenCallNewCategoryAnValidate_thenShouldError(){
        final String expectedName = "Fi ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
        final var expectedDescription = "A categproa mais assistida";
        final var expectedIsActive = true;
        final var actualCategory =  Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException = assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        assertEquals(expectedErrorCount,actualException.getErrors().size());
        assertEquals(expectedErrorMessage,actualException.getErrors().get(0).message());

    }

    @Test
    public void givenAnInvelidNameLengthMoreThan255_whenCallNewCategoryAnValidate_thenShouldError(){
        final String expectedName = " Pensando mais a longo prazo, a constante divulgação das informações representa uma abertura para a melhoria dos conhecimentos estratégicos para atingir a excelência. Todavia, o entendimento das metas propostas assume importantes posições no estabelecimento do levantamento das variáveis envolvidas. Gostaria de enfatizar que a consulta aos diversos militantes é uma das consequências do orçamento setorial. As experiências acumuladas demonstram que a consolidação das estruturas exige a precisão e a definição das condições financeiras e administrativas exigidas.";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
        final var expectedDescription = "A categproa mais assistida";
        final var expectedIsActive = true;
        final var actualCategory =  Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException = assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        assertEquals(expectedErrorCount,actualException.getErrors().size());
        assertEquals(expectedErrorMessage,actualException.getErrors().get(0).message());

    }

    @Test
    public void givenAValidEmptyDescription_whenCallNewCategoryAnValidate_thenShouldError(){
        final var expectedName = "Filmes";
        final var expectedDescription = "   ";
        final var expectedIsActive = true;
        final var actualCategory =  Category.newCategory(expectedName, expectedDescription, expectedIsActive);


        assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));
        assertNotNull(actualCategory);
        assertNotNull(actualCategory.getId());
        assertEquals(expectedName, actualCategory.getName());
        assertEquals(expectedDescription, actualCategory.getDescription());
        assertEquals(expectedIsActive, actualCategory.isActive());
        assertNotNull(actualCategory.getCreatedAt());
        assertNotNull(actualCategory.getUpdatedAt());
        assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAValidFlaseIsActive_whenCallNewCategoryAnValidate_thenShouldError(){
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = false;
        final var actualCategory =  Category.newCategory(expectedName, expectedDescription, expectedIsActive);


        assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));
        assertNotNull(actualCategory);
        assertNotNull(actualCategory.getId());
        assertEquals(expectedName, actualCategory.getName());
        assertEquals(expectedDescription, actualCategory.getDescription());
        assertEquals(expectedIsActive, actualCategory.isActive());
        assertNotNull(actualCategory.getCreatedAt());
        assertNotNull(actualCategory.getUpdatedAt());
        assertNotNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAValidActivateCategory_whenCallDeactivate_thenReturnCategoryInactivated(){
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = false;

        final var aCategory =  Category.newCategory(expectedName, expectedDescription, true);
        assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

        final var updatedAt = aCategory.getUpdatedAt();

        assertNull(aCategory.getDeletedAt());
        assertTrue(aCategory.isActive());

        final var actualCategory = aCategory.deactivate();

        assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        assertEquals(aCategory.getId(),actualCategory.getId());
        assertEquals(expectedName, actualCategory.getName());
        assertEquals(expectedDescription, actualCategory.getDescription());
        assertEquals(expectedIsActive, actualCategory.isActive());
        assertNotNull(actualCategory.getCreatedAt());
        assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        assertNotNull(actualCategory.getDeletedAt());
    }


}