package IngridOana.repository;

import IngridOana.domain.Student;
import IngridOana.validation.*;

public class StudentRepository extends AbstractCRUDRepository<String, Student> {
    public StudentRepository(Validator<Student> validator) {
        super(validator);
    }
}

