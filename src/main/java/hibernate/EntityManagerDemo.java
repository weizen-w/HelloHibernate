package hibernate;

import hibernate.domain.Event;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * 12/09/2023 HelloHibernate
 *
 * @author Wladimir Weizen
 */
public class EntityManagerDemo {

  static EntityManager em;

  public static void main(String[] args) {
    EntityManagerFactory factory = new Configuration().configure("hibernate.cfg.xml")
        .buildSessionFactory();
    // Из фабрики создаем EntityManager
    em = factory.createEntityManager();

    Event event1 = findById(1);
    System.out.println(event1);

//    add(new Event("Rock concert", "Berlin"));
//    Event event5 = findById(5);
//    System.out.println(event5);

//    event5.setCity("Prague");
//    event5 = update(event5);
//    System.out.println(event5);

//    delete(event4);
    List<Event> events = findAll();
    for (Event event : events) {
      System.out.println(event);
    }
  }

  static List<Event> findAll() {
    return em.createNamedQuery("findAll", Event.class).getResultList();
  }

  static Event findById(int id) {
    return em.find(Event.class, id);
  }

  static void add(Event event) {
    em.getTransaction().begin();
    em.persist(event);
    em.getTransaction().commit();
  }

  static Event update(Event event) {
    em.getTransaction().begin();
    em.merge(event);
    em.getTransaction().commit();
    return event;
  }

  static void delete(Event event) {
    em.getTransaction().begin();
    em.remove(event);
    em.getTransaction().commit();
  }
}
