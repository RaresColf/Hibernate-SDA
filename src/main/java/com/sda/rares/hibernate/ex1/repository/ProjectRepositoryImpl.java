package com.sda.rares.hibernate.ex1.repository;

import com.sda.rares.hibernate.ex1.model.Project;
import com.sda.rares.hibernate.ex1.utils.SessionManager;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectRepositoryImpl implements ProjectRepository{
    @Override
    public Optional<Project> findById(Integer id) {
        try (Session session = SessionManager.getSessionFactory().openSession()) {    // try with resource inchide de fiecare data sesiunea, de aia e bine sa il folosim de fiecare data

            Project project = session.find(Project.class, id);
            return Optional.ofNullable(project);       // alegem ofNullable deoarece putem scoate din baza de date ceva care este null si o sa ne arunce o exceptie.

        } catch (Exception e) {
            e.printStackTrace();      // ne asiguram ca ne arata in cazul unei erori de unde a aparut acea eroare
            return Optional.empty();         // returnam un obiect gol, in cazul de fata Optional deoarece obiectul nstru este optional
        }
    }

    @Override
    public void create(Project project) {

        Transaction transaction = null;

        try (Session session = SessionManager.getSessionFactory().openSession()) {
            // fiinca avem operatiuni cu baza de date, avem nevoie de deschiderea unei tranzactii
            transaction = session.beginTransaction();
            session.save(project);

            transaction.commit();


        } catch (Exception e) {
            e.printStackTrace();

            if (transaction != null) {       // verificam daca tranzactia nu s-a facut, si daca nu se da rollback.
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(Project project) {

        Transaction transaction = null;

        try (Session session = SessionManager.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            session.update(project);

            transaction.commit();


        } catch (Exception e) {
            e.printStackTrace();

            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public void delete(Project project) {

        Transaction transaction = null;

        try (Session session = SessionManager.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            session.delete(project);

            transaction.commit();


        } catch (Exception e) {
            e.printStackTrace();

            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public List<Project> findAll() {
        try (Session session = SessionManager.getSessionFactory().openSession()){

              return session.createQuery("select e from Project e", Project.class).list();    // face exact la fel ca si linia de mai sus

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
