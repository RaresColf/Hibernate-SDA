package com.sda.rares.hibernate.ex1.repository;

import com.sda.rares.hibernate.ex1.model.Employee;
import com.sda.rares.hibernate.ex1.utils.SessionManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    @Override
    public Optional<Employee> findById(Integer id) {
        try (Session session = SessionManager.getSessionFactory().openSession()) {    // try with resource inchide de fiecare data sesiunea, de aia e bine sa il folosim de fiecare data

            Employee employee = session.find(Employee.class, id);
            if (employee != null) {     // facem verificarea sa vedem daca avem sau nu un employee ca sa nu primim nullpointerexception
                System.out.println(employee.getProjects().size());   // aici fortam citirea in interiorul sesiunii deoarece avem nevoie de aceste date in metoda de findById din clasa Main linia 79

            }
            return Optional.ofNullable(employee);       // alegem ofNullable deoarece putem scoate din baza de date ceva care este null si o sa ne arunce o exceptie.

        } catch (Exception e) {
            e.printStackTrace();      // ne asiguram ca ne arata in cazul unei erori de unde a aparut acea eroare
            return Optional.empty();         // returnam un obiect gol, in cazul de fata Optional deoarece obiectul nstru este optional
        }
    }

    @Override
    public void create(Employee employee) {

        Transaction transaction = null;

        try (Session session = SessionManager.getSessionFactory().openSession()) {
            // fiinca avem operatiuni cu baza de date, avem nevoie de deschiderea unei tranzactii
            transaction = session.beginTransaction();
            session.save(employee);

            transaction.commit();


        } catch (Exception e) {
            e.printStackTrace();

            if (transaction != null) {       // verificam daca tranzactia nu s-a facut, si daca nu se da rollback.
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(Employee employee) {

        Transaction transaction = null;

        try (Session session = SessionManager.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            session.update(employee);

            transaction.commit();


        } catch (Exception e) {
            e.printStackTrace();

            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public void delete(Employee employee) {

        Transaction transaction = null;

        try (Session session = SessionManager.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            session.delete(employee);

            transaction.commit();


        } catch (Exception e) {
            e.printStackTrace();

            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public List<Employee> findAll() {
        try (Session session = SessionManager.getSessionFactory().openSession()){

//            return session.createQuery("from Employee", Employee.class).list()      // dupa ce am creat querry trebuie sa punem .list ca sa ne puna toate rezultatele intr-o lista
              return session.createQuery("select e from Employee e", Employee.class).list();    // face exact la fel ca si linia de mai sus

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Employee> findAllWithNameStartingWithJ() {
        try (Session session = SessionManager.getSessionFactory().openSession()){

            return session.createQuery("select e from Employee e where e.firstName like 'J%'", Employee.class).list();

        }catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    @Override
    public List<Employee> findAllWithSalaryGreaterThan(Integer salary) {
        try(Session session = SessionManager.getSessionFactory().openSession()) {

            String hqlQuerry = "select e from Employee e where e.salary > :biggerSalary";
            Query<Employee> query = session.createQuery(hqlQuerry, Employee.class);
            query.setParameter("biggerSalary", salary);

            return query.list();

        }catch (Exception e) {
        e.printStackTrace();
        return new ArrayList<>();
    }
    }
}
