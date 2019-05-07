package AlexLouisaMV;

import static org.junit.Assert.assertTrue;

import AlexLouisaMV.domain.Nota;
import AlexLouisaMV.repository.NotaXMLRepo;
import org.junit.Test;
import AlexLouisaMV.curent.Curent;
import AlexLouisaMV.domain.Student;
import AlexLouisaMV.domain.Tema;
import AlexLouisaMV.repository.StudentXMLRepo;
import AlexLouisaMV.repository.TemaXMLRepo;
import AlexLouisaMV.service.Service;
import AlexLouisaMV.validation.StudentValidator;
import AlexLouisaMV.validation.TemaValidator;
import AlexLouisaMV.validation.ValidationException;
import AlexLouisaMV.validation.NotaValidator;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {

        assertTrue(true);
    }

    @Test
    public void test1() {
        System.out.println("Test 1");

        StudentValidator studentValidator = new StudentValidator();
        Student student;

        student = new Student("", "aaa", 999, "bbb");
        try {
            studentValidator.validate(student);
            throw new RuntimeException("Invalid state!");
        } catch (ValidationException v) {
            // nothing to do here
        }
        student = new Student(null, "aaa", 999, "bbb");
        try {
            studentValidator.validate(student);
            throw new RuntimeException("Invalid state!");
        } catch (ValidationException v) {
            // nothing to do here
        }
        student = new Student("1", "aaa", 999, "bbb");
        studentValidator.validate(student);
        student = new Student("11", "aaa", 999, "bbb");
        studentValidator.validate(student);


        student = new Student("aaa", "", 999, "bbb");
        try {
            studentValidator.validate(student);
            throw new RuntimeException("Invalid state!");
        } catch (ValidationException v) {
            // nothing to do here
        }
        student = new Student("aaa", null, 999, "bbb");
        try {
            studentValidator.validate(student);
            throw new RuntimeException("Invalid state!");
        } catch (ValidationException v) {
            // nothing to do here
        }
        student = new Student("aaa", "a", 999, "bbb");
        studentValidator.validate(student);
        student = new Student("aaa", "aa", 999, "bbb");
        studentValidator.validate(student);
    }

    @Test
    public void test2() {
        System.out.println("Test 2");

        int week = Curent.getCurrentWeek();
        assert (week >= 1 && week <= 14);
    }

    @Test
    public void test3() {
        System.out.println("Test 3");

        StudentValidator studentValidator = new StudentValidator();
        Student student;

        student = new Student("aaa", "aaa", 999, "");
        try {
            studentValidator.validate(student);
            throw new RuntimeException("Invalid state!");
        } catch (ValidationException v) {
            // nothing to do here
        }
        student = new Student("aaa", "aaa", 999, null);
        try {
            studentValidator.validate(student);
            throw new RuntimeException("Invalid state!");
        } catch (ValidationException v) {
            // nothing to do here
        }
        student = new Student("aaa", "aaa", 999, "a");
        studentValidator.validate(student);
        student = new Student("aaa", "aaa", 999, "aa");
        studentValidator.validate(student);

        student = new Student("aaa", "aaa", -1, "bbb");
        try {
            studentValidator.validate(student);
            throw new RuntimeException("Invalid state!");
        } catch (ValidationException v) {
            // nothing to do here
        }
        student = new Student("aaa", "aaa", 0, "bbb");
        studentValidator.validate(student);
        student = new Student("1", "aaa", 1, "bbb");
        studentValidator.validate(student);
        student = new Student("1", "aaa", 7000, "bbb");
        studentValidator.validate(student);

        // no need to check that the student group is not null, since it's a primitive type. nice programming!
    }

    @Test
    public void test4() {
        System.out.println("Test 4");

        StudentValidator studentValidator = new StudentValidator();
        StudentXMLRepo studentRepository = new StudentXMLRepo("fisiere/TestStudenti.xml");

        Service service = new Service(studentRepository, studentValidator, null, null, null, null);

        Student student = new Student("111", "1111", 1111, "1111");
        service.addStudent(student);
        assert (service.findStudent(student.getID()).equals(student));
        service.deleteStudent(student.getID()); // to not fill up the file with junk, we inadvertently also test that deleting works properly
    }

    @Test
    public void test5() { // this will just test the validator
        System.out.println("Test 5");

        // need to test : ID, descriere, deadline, primire
        TemaValidator temaValidator = new TemaValidator();

        Tema tema;

        tema = new Tema("aaa", "aaa", 3, 3);

        tema.setID("aaa");
        temaValidator.validate(tema);

        tema.setID(null);
        try {
            temaValidator.validate(tema);
            throw new RuntimeException("This should go on the catch block boss");
        } catch (ValidationException ignored) {
        }

        tema.setID("");
        try {
            temaValidator.validate(tema);
            throw new RuntimeException("This should go on the catch block boss");
        } catch (ValidationException ignored) {
        }

        tema.setID("aaa");
        tema.setDescriere("");
        try {
            temaValidator.validate(tema);
            throw new RuntimeException("This should go on the catch block boss");
        } catch (ValidationException ignored) {
        }

        tema.setDescriere("aaa");
        tema.setDeadline(0);
        try {
            temaValidator.validate(tema);
            throw new RuntimeException("This should go on the catch block boss");
        } catch (ValidationException ignored) {
        }
        tema.setDeadline(3);
        tema.setPrimire(0);
        try {
            temaValidator.validate(tema);
            throw new RuntimeException("This should go on the catch block boss");
        } catch (ValidationException ignored) {
        }
    }

    @Test
    public void test6() {
        System.out.println("Test 6");

        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepo temaXMLRepo = new TemaXMLRepo("fisiere/TestTeme.xml");

        Service service;

        //= Subtest 1 - Fail because ID is null or empty ======================================
        service = new Service(null, null, temaXMLRepo, temaValidator, null, null);

        try {
            service.addTema(new Tema(null, "aaa", 3, 3));
            throw new RuntimeException("This should go on the catch block boss");
        } catch (ValidationException ignored) {
        }

        try {
            service.addTema(new Tema("", "aaa", 3, 3));
            throw new RuntimeException("This should go on the catch block boss");
        } catch (ValidationException ignored) {
        }
        //=====================================================================================

        //= Subtest 2 - Fail because description is empty =====================================
        service = new Service(null, null, temaXMLRepo, temaValidator, null, null);

        try {
            service.addTema(new Tema("aaa", "", 3, 3));
            throw new RuntimeException("This should go on the catch block boss");
        } catch (ValidationException ignored) {
        }
        //=====================================================================================

        //= Subtest 4 - Fail because deadline is not good =====================================
        service = new Service(null, null, temaXMLRepo, temaValidator, null, null);

        try {
            service.addTema(new Tema("aaa", "aaa", 0, 3));
            throw new RuntimeException("This should go on the catch block boss");
        } catch (ValidationException ignored) {
        }

        try {
            service.addTema(new Tema("aaa", "aaa", 15, 3));
            throw new RuntimeException("This should go on the catch block boss");
        } catch (ValidationException ignored) {
        }
        //=====================================================================================

        //= Subtest 5 - Fail because primire is not good ======================================
        service = new Service(null, null, temaXMLRepo, temaValidator, null, null);

        try {
            service.addTema(new Tema("aaa", "aaa", 3, 0));
            throw new RuntimeException("This should go on the catch block boss");
        } catch (ValidationException ignored) {
        }

        try {
            service.addTema(new Tema("aaa", "aaa", 3, 15));
            throw new RuntimeException("This should go on the catch block boss");
        } catch (ValidationException ignored) {
        }
        //=====================================================================================

        //= Subtest 6 - Success ===============================================================
        temaXMLRepo = new TemaXMLRepo("fisiere/TestTeme_1.xml");
        service = new Service(null, null, temaXMLRepo, temaValidator, null, null);

        Tema st6_tema = new Tema("aaa", "aaa", 3, 3);

        try {
            service.addTema(st6_tema);
        } catch (ValidationException ignored) {
            throw new RuntimeException("This should go on the catch block boss");
        }

        int size = 0;
        for (Tema t : service.getAllTeme()) {
            ++size;

            assert st6_tema.getID().equals(t.getID());
            assert st6_tema.getDescriere().equals(t.getDescriere());
            assert st6_tema.getDeadline() == t.getDeadline();
            assert st6_tema.getPrimire() == t.getPrimire();
        }

        assert size == 1;
        //=====================================================================================

        //= Subtest 7 - assignment already exits
        service.addTema(st6_tema);
        assert (Collections.singletonList(service.getAllTeme()).size() == 1);

        service.deleteTema(st6_tema.getNrTema());
    }

    @Test
    public void testGrade() {
        StudentXMLRepo studentXMLRepo = new StudentXMLRepo("fisiere/TestStudenti_2.xml");
        TemaXMLRepo temaXMLRepo = new TemaXMLRepo("fisiere/TestTeme_2.xml");
        NotaXMLRepo notaXMLRepo = new NotaXMLRepo("fisiere/TestNote_2.xml");

        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        NotaValidator notaValidator = new NotaValidator(studentXMLRepo, temaXMLRepo);

        Service service = new Service(studentXMLRepo, studentValidator, temaXMLRepo, temaValidator, notaXMLRepo, notaValidator);

        Student student = new Student("111", "1111", 1111, "1111");
        service.addStudent(student);
        assert (service.findStudent(student.getID()).equals(student));

        Tema tema = new Tema("aaa", "aaa", 10, 10);
        service.addTema(tema);
        assert (Arrays.asList(service.getAllTeme()).size() == 1);

        // Today's catch: the XML Repo will ONLY create the file once you actually write something to it. Just creating an instance of the xml repo ain't enough man!!

        Nota nota = new Nota("1", student.getID(), tema.getID(), 10, LocalDate.now());
        service.addNota(nota, "EXCELLENT");

        service.deleteNota(nota.getID());

        nota = new Nota("1", student.getID(), tema.getID(), 10, LocalDate.of(2012, 1, 1));
        try {
            service.addNota(nota, "bad");
        } catch (ValidationException ignored) {// nothing to test here
        }

        service.deleteStudent(student.getID());
        service.deleteTema(tema.getNrTema());
    }

    @Test
    public void BigBang() {
        test3();
        test4();
        test5();
        test6();
        testGrade();
    }

    @Test
    public void Incremental() {
        test3();
        test4();
    }

    @Test
    public void SecondIncremental() {
        test3();
        test4();

        test5();
        test6();
    }
}