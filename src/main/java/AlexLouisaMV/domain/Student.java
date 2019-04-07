package AlexLouisaMV.domain;

import AlexLouisaMV.repository.HasID;

import java.util.Objects;

public class Student implements HasID<String> {
    private String idStudent;
    private String nume;
    private int grupa;
    private String email;

    /**
     * Class Constructor
     * @param idStudent - id-ul studentului
     * @param nume - numele studentului
     * @param grupa - grupa studentului
     * @param email - emailul unui student
     */
    public Student(String idStudent, String nume, int grupa, String email) {
        this.idStudent = idStudent;
        this.nume = nume;
        this.grupa = grupa;
        this.email = email;
    }

    /**
     * @return id-ul unui student
     */
    public String getID() {
        return idStudent;
    }

    /**
     * modifica id-ul unui student
     * @param ID - noul id al studentului
     */
    public void setID(String ID) {
        this.idStudent = ID;
    }

    /**
     * @return numele studentului
     */
    public String getNume() {
        return nume;
    }

    /**
     * modifica numele studentului
     * @param nume - noul nume
     */
    public void setNume(String nume) {
        this.nume = nume;
    }

    /**
     * @return grupa studentului
     */
    public int getGrupa() {
        return grupa;
    }

    /**
     * modifica grupa studentului
     * @param grupa - noua grupa
     */
    public void setGrupa(int grupa) {
        this.grupa = grupa;
    }

    /**
     * @return emai-ul studentului
     */
    public String getEmail() {
        return email;
    }

    /**
     * modifica emailul studentului
     * @param email - noul email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return un obiect de tip Student sub forma de string
     */
    @Override
    public String toString() {
        return idStudent + "," + nume + "," + grupa + "," + email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return grupa == student.grupa &&
                Objects.equals(idStudent, student.idStudent) &&
                Objects.equals(nume, student.nume) &&
                Objects.equals(email, student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStudent, nume, grupa, email);
    }
}
