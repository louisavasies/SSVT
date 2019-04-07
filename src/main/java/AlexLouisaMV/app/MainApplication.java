package AlexLouisaMV.app;


import AlexLouisaMV.repository.NotaFileRepository;
import AlexLouisaMV.repository.StudentFileRepository;
import AlexLouisaMV.repository.StudentXMLRepo;
import AlexLouisaMV.repository.TemaXMLRepo;
import AlexLouisaMV.repository.NotaXMLRepo;
import AlexLouisaMV.repository.TemaFileRepository;
import AlexLouisaMV.service.Service;
import AlexLouisaMV.validation.NotaValidator;
import AlexLouisaMV.validation.StudentValidator;
import AlexLouisaMV.validation.TemaValidator;
import AlexLouisaMV.view.UI;



public class MainApplication {

    public static void main(String[] args) {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        //StudentFileRepository studentFileRepository = new StudentFileRepository(filenameStudent);
        //TemaFileRepository temaFileRepository = new TemaFileRepository(filenameTema);
        //NotaValidator notaValidator = new NotaValidator(studentFileRepository, temaFileRepository);
        //NotaFileRepository notaFileRepository = new NotaFileRepository(filenameNota);

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
        UI ui = new UI(service);
        ui.run();
    }

}
