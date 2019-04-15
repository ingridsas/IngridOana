package IngridOana;

import domain.Student;
import domain.Tema;
import org.junit.Before;
import org.junit.Test;
import repository.StudentRepository;
import repository.TemaRepository;
import validation.StudentValidator;
import validation.TemaValidator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private StudentRepository studentRepository;
    private TemaRepository temaRepository;

    @Before
    public void setUp() {
        studentRepository = new StudentRepository(new StudentValidator());
        temaRepository = new TemaRepository(new TemaValidator());
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void addGrade() {
        assertTrue(true);
    }

    @Test
    public void addHomework() {
        assertTrue(true);
    }

    @Test
    public void addStudent() {
        List<Student> students = StreamSupport.stream(studentRepository.findAll().spliterator(), false).collect(Collectors.toList());
        assertEquals(students.size(), 0);
        studentRepository.save(new Student("1", "Oanaaaa", 936));
        students = StreamSupport.stream(studentRepository.findAll().spliterator(), false).collect(Collectors.toList());
        assertEquals(students.size(), 1);
        Student student = studentRepository.findOne("1");
        assertEquals(student.getNume(), "Oanaaaa");
        assertEquals(student.getGrupa(), 936);
    }

    @Test
    public void shouldFailForNullId() {
        try {
            studentRepository.findOne(null);
            fail();
        } catch (Exception e) {
            assertEquals("ID-ul nu poate fi null!", e.getMessage().trim());

        }
    }

    @Test
    public void shouldFailInvalidGroup() {
        Student student = studentRepository.save(new Student("123", "lala", 1000));
        assertEquals(student, null);
    }

    @Test
    public void shouldFailInvalidGroupNegative() {
        Student student = studentRepository.save(new Student("123", "lala", -100));
        assertEquals(student, null);
    }

    @Test
    public void shouldFailInvalidName() {
        Student student = studentRepository.save(new Student("123", "", 310));
        assertEquals(student, null);
    }

    @Test
    public void shouldFailInvalidId() {
        Student student = studentRepository.save(new Student("", "Ingriduta", 3100));
        assertEquals(student, null);
    }


    @Test
    public void shouldAddAssignment() {
        List<Tema> homeworks = StreamSupport.stream(temaRepository.findAll().spliterator(), false).collect(Collectors.toList());
        assertEquals(homeworks.size(), 0);
        temaRepository.save(new Tema("1", "Oanaaaa", 3, 1));
        homeworks = StreamSupport.stream(temaRepository.findAll().spliterator(), false).collect(Collectors.toList());
        assertEquals(homeworks.size(), 1);
    }

    @Test
    public void shouldFailNullId() {
        Tema temaIdEmpty = temaRepository.save(new Tema(null, "Ingriduta", 3, 2));
        assertEquals(temaIdEmpty, null);
    }

    @Test
    public void shouldFailEmptyId() {
        Tema temaIdEmpty = temaRepository.save(new Tema("", "Ingriduta", 3, 2));
        assertEquals(temaIdEmpty, null);
    }


    @Test
    public void shouldFailNullDescription() {
        Tema temaDescEmpty = temaRepository.save(new Tema("12", null, 3, 2));
        assertEquals(temaDescEmpty, null);
    }

    @Test
    public void shouldFailEmptyDescription() {
        Tema temaDescEmpty = temaRepository.save(new Tema("12", "", 3, 2));
        assertEquals(temaDescEmpty, null);
    }

    @Test
    public void shouldFailNegativeDeadline() {
        Tema negativeDeadline = temaRepository.save(new Tema("12", "desc", -1, 2));
        assertEquals(negativeDeadline, null);
    }

    @Test
    public void shouldFailDeadlineTooBig() {
        Tema deadlineTooBig = temaRepository.save(new Tema("17", "desc", 19, 2));
        assertEquals(deadlineTooBig, null);
    }

    @Test
    public void shouldFailDeadlineSmallerThanStart() {
        Tema deadlineSmaller = temaRepository.save(new Tema("17", "desc", 3, 5));
        assertEquals(deadlineSmaller, null);
    }

    @Test
    public void shouldFailStartLineNegative() {
        Tema starlineNegative = temaRepository.save(new Tema("12", "desc", 4, -4));
        assertEquals(starlineNegative, null);
    }

    @Test
    public void shouldFailStartlineTooBig() {
        Tema startLineTooBig = temaRepository.save(new Tema("17", "desc", 3, 25));
        assertEquals(startLineTooBig, null);
    }

    @Test
    public void shouldFailStartlineGreaterThanDeadline() {
        Tema startLineTooBig = temaRepository.save(new Tema("17", "desc", 3, 25));
        assertEquals(startLineTooBig, null);
    }
}
