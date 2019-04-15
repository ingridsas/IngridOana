package IngridOana.repository;

import IngridOana.domain.*;
import IngridOana.validation.*;

public class NotaRepository extends AbstractCRUDRepository<Pair<String, String>, Nota> {
    public NotaRepository(Validator<Nota> validator) {
        super(validator);
    }
}
