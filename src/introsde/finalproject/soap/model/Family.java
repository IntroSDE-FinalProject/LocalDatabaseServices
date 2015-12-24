package introsde.finalproject.soap.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import introsde.finalproject.soap.dao.LifeCoachDao;

/**
 * The persistent class for the "Family" database table.
 * 
 */
@Entity
@Table(name="Family")
@NamedQuery(name="Family.findAll", query="SELECT f FROM Family f")
public class Family implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    // For sqlite in particular, you need to use the following @GeneratedValue annotation
    // This holds also for the other tables
    // SQLITE implements auto increment ids through named sequences that are stored in a 
    // special table named "sqlite_sequence"
    @GeneratedValue(generator="sqlite_family")
    @TableGenerator(name="sqlite_family", table="sequence",
        pkColumnName="name", valueColumnName="seq",
        pkColumnValue="Family",
        initialValue=1, allocationSize=1)
    @Column(name="idFamily")
    private int idFamily;
    
    @Column(name="firstname")
    private String firstname;
    
    @Column(name="lastname")
    private String lastname;
    
    @Column(name="role")
    private String role;

	/**
	 * @return the idFamily
	 */
	public int getIdFamily() {
		return idFamily;
	}

	/**
	 * @param idFamily the idFamily to set
	 */
	public void setIdFamily(int idFamily) {
		this.idFamily = idFamily;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
	
	// Database operations
    public static Family getFamilyById(int idFamily) {
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        Family p = em.find(Family.class, idFamily);
        LifeCoachDao.instance.closeConnections(em);
        return p;
    }
    
    public static List<Family> getAll() {
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        List<Family> list = em.createNamedQuery("Family.findAll", Family.class).getResultList();
        LifeCoachDao.instance.closeConnections(em);
        return list;
    }
    
    public static Family saveFamily(Family p) {
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(p);
        tx.commit();
        LifeCoachDao.instance.closeConnections(em);
        return p;
    }
    
    public static Family updateFamily(Family p) {
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        p=em.merge(p);
        tx.commit();
        LifeCoachDao.instance.closeConnections(em);
        return p;
    }
    
    public static void removeFamily(Family p) {
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        p=em.merge(p);
        em.remove(p);
        tx.commit();
        LifeCoachDao.instance.closeConnections(em);
    }  
}
