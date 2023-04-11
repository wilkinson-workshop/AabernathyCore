package org.ww.adt.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.ww.adt.AabernathyComponent;

public class DAOEngine extends AabernathyComponent {
    private SessionFactory sessionFactory;

    public DAOEngine()
    {
        super();
        configureSessionFactory();
    }

    /**
     * Creates a SessionFactory object which creates sessions for database
     * transactions
     * @return
     */
    private void configureSessionFactory()
    {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    /**
     * Creates a Session object used for database transactions.
     * @return
     */
    private Session openSession()
    {
        return sessionFactory.openSession();
    }
}
