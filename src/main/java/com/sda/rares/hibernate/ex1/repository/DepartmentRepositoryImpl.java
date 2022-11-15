package com.sda.rares.hibernate.ex1.repository;

import com.sda.rares.hibernate.ex1.model.Department;
import com.sda.rares.hibernate.ex1.model.Employee;
import com.sda.rares.hibernate.ex1.utils.SessionManager;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartmentRepositoryImpl implements DepartmentRepository {
    @Override
    public Optional<Department> findById(Integer id) {
        try (Session session = SessionManager.getSessionFactory().openSession()) {    // try with resource inchide de fiecare data sesiunea, de aia e bine sa il folosim de fiecare data

            Department department = session.find(Department.class, id);
            System.out.println(department.getEmployees().size());    // aici fortam citirea in interiorul sesiunii deoarece avem nevoie de aceste date in metoda de findById din clasa Main linia 61
            return Optional.ofNullable(department);       // alegem ofNullable deoarece putem scoate din baza de date ceva care este null si o sa ne arunce o exceptie.

        } catch (Exception e) {
            e.printStackTrace();      // ne asiguram ca ne arata in cazul unei erori de unde a aparut acea eroare
            return Optional.empty();         // returnam un obiect gol, in cazul de fata Optional deoarece obiectul nstru este optional
        }
    }

    @Override
    public void create(Department department) {

        Transaction transaction = null;

        try (Session session = SessionManager.getSessionFactory().openSession()) {
            // fiinca avem operatiuni cu baza de date, avem nevoie de deschiderea unei tranzactii
            transaction = session.beginTransaction();
            session.save(department);

            transaction.commit();


        } catch (Exception e) {
            e.printStackTrace();

            if (transaction != null) {       // verificam daca tranzactia nu s-a facut, si daca nu se da rollback.
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(Department department) {

        Transaction transaction = null;

        try (Session session = SessionManager.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            session.update(department);

            transaction.commit();


        } catch (Exception e) {
            e.printStackTrace();

            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public void delete(Department department) {

        Transaction transaction = null;

        try (Session session = SessionManager.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            session.delete(department);

            transaction.commit();


        } catch (Exception e) {
            e.printStackTrace();

            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public List<Department> findAll() {
        try (Session session = SessionManager.getSessionFactory().openSession()){

//            return session.createQuery("from Employee", Employee.class).list()      // dupa ce am creat querry trebuie sa punem .list ca sa ne puna toate rezultatele intr-o lista
              return session.createQuery("select e from Employee e", Department.class).list();    // face exact la fel ca si linia de mai sus

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
