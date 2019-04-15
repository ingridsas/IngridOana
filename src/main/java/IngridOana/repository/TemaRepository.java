package IngridOana.repository;

import IngridOana.domain.Tema;
import IngridOana.validation.*;

public class TemaRepository extends AbstractCRUDRepository<String, Tema> {
    public TemaRepository(Validator<Tema> validator) {
        super(validator);
    }
}

