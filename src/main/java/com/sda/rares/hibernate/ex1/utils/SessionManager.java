package com.sda.rares.hibernate.ex1.utils;

import com.sda.rares.hibernate.ex1.model.Account;
import com.sda.rares.hibernate.ex1.model.Department;
import com.sda.rares.hibernate.ex1.model.Employee;
import com.sda.rares.hibernate.ex1.model.Project;
import com.sda.rares.hibernate.utils.AbstractSessionManager;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Trebuie sa ne asiguram ca aceasta clasa are un pattern de Singleton
 *     - Constructorul in patternul Sigleton trebuie sa fie private ca sa putem controla de cate ori il instantiem
 *     - Instantiem " private static final SessionManager INSTANCE  = new SessionManager(); " si o facem constanta si o facem sa fie una singura prin Static,
 *        si vrem sa fie si final ca sa nu o putem modifica
 *      - cel mai simplu mod de a face o constanta.
 *      CRUD = create, read, update, delete.
 */

public class SessionManager extends AbstractSessionManager {

    private static final SessionManager INSTANCE  = new SessionManager();

    private SessionManager(){
    }

    public static SessionFactory getSessionFactory(){
        return INSTANCE.getSessionFactory("ex1_hibernate");   // prin asta ne oferim acces ca sa accesam Session Factory
    }

    public static void shutDown(){
        INSTANCE.shutdownSessionManager();    // metoda prin care inchdem sesiunea.
    }


    @Override
    protected void setAnnotatedClasses(Configuration configuration) {
        //Hibernate model will be added here
        configuration.addAnnotatedClass(Employee.class);  // pentru fiecare entitate adaugam o linie noua pentru a spune hibernatului ca asta e modelul de care el trebe sa stie
        configuration.addAnnotatedClass(Account.class);
        configuration.addAnnotatedClass(Department.class);
        configuration.addAnnotatedClass(Project.class);
    }
}
