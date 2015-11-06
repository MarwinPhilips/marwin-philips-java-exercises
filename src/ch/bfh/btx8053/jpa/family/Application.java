package ch.bfh.btx8053.jpa.family;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Application {

  private static final String PERSISTENCE_UNIT_NAME = "family";
  private EntityManager em;

  public Application() {
    em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
  }
  
  public void setUp() {
    // Begin a new local transaction so that we can persist a new entity
    EntityTransaction transaction = em.getTransaction();
    transaction.begin();

    // Read the existing entries
    Query q = em.createQuery("select m from Person m"); // Person table should be empty the first time

    // Do we have entries?
    boolean createNewEntries = (q.getResultList().size() == 0);

    // No? so lets create new entries
    if (createNewEntries) {
      Family family = new Family();
      em.persist(family);
      family.setDescription("Family for the Knopfs");
      for (int i = 0; i < 40; i++) {
        Person person = new Person();
        person.setFirstName("Jim_" + i);
        person.setLastName("Knopf_" + i);
        em.persist(person);
        person.setFamily(family);
        // Now persists the family person relationship
        family.getMembers().add(person);
        em.persist(person);
        em.persist(family);
      }
    }

    // Commit the transaction, which will cause the entity to be stored in the
    // database
    em.flush();
    transaction.commit();
  }

  public void checkAvailablePeople() {
    // Perform a simple query for all the Message entities
    Query q = em.createQuery("select m from Person m");

    // We should have 40 Persons in the database
    System.out.println("40 people, right? " + q.getResultList().size());
  }

  public void checkFamily() {
     // Go through each of the entities and print out each of their
    // messages, as well as the date on which it was created
    Query q = em.createQuery("select f from Family f");

    // We should have one family with 40 persons
    System.out.println("1 familiy, right? " + q.getResultList().size());
    System.out.println("40 people, right? " + ((Family) q.getSingleResult()).getMembers().size());
  }

  public void close () {
    // It is always good practice to close the EntityManager so that resources
    // are conserved.
	EntityTransaction transaction = em.getTransaction();
	transaction.begin();
	Query q = em.createNativeQuery("SHUTDOWN COMPACT");
	q.executeUpdate();
    em.flush();
    transaction.commit();
    em.close();
  }
  
  public static void main(String[] args) {
    Application app = new Application();
    app.setUp();
    app.checkAvailablePeople();
    app.checkFamily();
    app.close();
  }
}
