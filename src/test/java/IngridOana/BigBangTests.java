package IngridOana;

import IngridOana.domain.Nota;
import IngridOana.domain.Pair;
import IngridOana.domain.Student;
import IngridOana.domain.Tema;
import org.junit.Before;
import org.junit.Test;
import IngridOana.repository.*;
import IngridOana.service.Service;
import IngridOana.validation.NotaValidator;
import IngridOana.validation.StudentValidator;
import IngridOana.validation.TemaValidator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BigBangTests {
    private StudentRepository studentRepository;
    private TemaRepository temaRepository;
    private NotaRepository notaRepository;
    private Service service;


    @Before
    public void setUp() {
        studentRepository = new StudentRepository(new StudentValidator());
        temaRepository = new TemaRepository(new TemaValidator());
        notaRepository = new NotaRepository(new NotaValidator());

        //

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(new StudentValidator(), "C:\\Users\\ynghy\\Desktop\\lab1ssvv\\src\\main\\java\\IngridOana\\studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(new TemaValidator(), "C:\\Users\\ynghy\\Desktop\\lab1ssvv\\src\\main\\java\\IngridOana\\teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(new NotaValidator(), "C:\\Users\\ynghy\\Desktop\\lab1ssvv\\src\\main\\java\\IngridOana\\note.xml");

        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @Test
    public void addStudent() {
        List<Student> students = StreamSupport.stream(studentRepository.findAll().spliterator(), false).collect(Collectors.toList());
        assertEquals(students.size(), 0);
        studentRepository.save(new Student("1", "Oanaaaa", 936));
        students = StreamSupport.stream(studentRepository.findAll().spliterator(), false).collect(Collectors.toList());
        assertEquals(students.size(), 1);
    }

    @Test
    public void shouldAddAssignment() {
        List<Tema> homeworks = StreamSupport.stream(temaRepository.findAll().spliterator(), false).collect(Collectors.toList());
        assertEquals(homeworks.size(), 0);
        temaRepository.save(new Tema("1", "Oanaaaa", 3, 1));
        homeworks = StreamSupport.stream(temaRepository.findAll().spliterator(), false).collect(Collectors.toList());
        assertEquals(homeworks.size(), 1);
        Tema tema = temaRepository.findOne("1");
        assertNotNull(tema.toString(), "");
    }

    @Test
    public void shouldAddGrade() {
        List<Nota> note = StreamSupport.stream(notaRepository.findAll().spliterator(), false).collect(Collectors.toList());
        assertEquals(note.size(), 0);
        notaRepository.save(new Nota(new Pair<>("1", "nota1"), 7.5, 3, "Smart Girl"));
        note = StreamSupport.stream(notaRepository.findAll().spliterator(), false).collect(Collectors.toList());
        assertEquals(note.size(), 1);
    }

    @Test
    public void shouldAddStudent() {
        List<Student> studenti = StreamSupport.stream(service.findAllStudents().spliterator(), false).collect(Collectors.toList());
        assertEquals(studenti.size(), 0);
        service.saveStudent("10", "Oana", 936);
        assertEquals(studenti.size() + 1, 1);
    }

    @Test
    public void shouldAddNewGrade() {
        List<Nota> note = StreamSupport.stream(service.findAllNote().spliterator(), false).collect(Collectors.toList());
        assertEquals(note.size(), 0);
        service.saveNota("10", "3", 10.0, 1, "Oana este frumoasa si desteapta");
        assertEquals(note.size() + 1, 1);
    }

    @Test
    public void shouldFailAddNewGrade() {
        List<Nota> note = StreamSupport.stream(service.findAllNote().spliterator(), false).collect(Collectors.toList());
        assertEquals(note.size(), 0);
        assertEquals(-1, service.saveNota("5", "3", 10.0, 1, "Oana este frumoasa si desteapta"));
        assertEquals(note.size(), 1);
    }

    @Test
    public void shouldAddAssignement() {
        List<Tema> teme = StreamSupport.stream(service.findAllTeme().spliterator(), false).collect(Collectors.toList());
        assertEquals(teme.size(), 0);
        service.saveTema("4", "Sth", 4, 2);
        assertEquals(teme.size() + 1, 1);
    }

    @Test
    public void bigBang() {
        shouldAddStudent();
        shouldAddNewGrade();
        shouldAddAssignement();
    }

    @Test
    public void incrementerA() {
        shouldAddStudent(); //incrementer A
    }

    @Test
    public void incrementerAB() {
        shouldAddStudent(); //incrementer A
        shouldAddNewGrade();//incrementer B
    }

}
