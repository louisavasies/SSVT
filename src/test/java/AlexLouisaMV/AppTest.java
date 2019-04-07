package AlexLouisaMV;

import static org.junit.Assert.assertTrue;

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
    public void test5() {
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

        Service service = new Service(null, null, temaXMLRepo, temaValidator, null, null);

        Tema tema = new Tema("aaa", "aaa", 3, 3);
        tema.setID("aaa");
        service.addTema(tema);
        service.deleteTema(tema.getNrTema());
    }
}