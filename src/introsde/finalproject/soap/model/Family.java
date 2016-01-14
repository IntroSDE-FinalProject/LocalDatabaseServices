package introsde.finalproject.soap.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

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
    @GeneratedValue(generator="sqlite_family")
    @TableGenerator(name="sqlite_family", table="sequence",
        pkColumnName="name", valueColumnName="seq",
        pkColumnValue="Family",
        initialValue=1, allocationSize=1)
    @Column(name="idFamily", nullable=false)
    private int idFamily;
    
    @Column(name="firstname", nullable=false)
    private String firstname;
    
    @Column(name="lastname", nullable=false)
    private String lastname;
    
    @Column(name="role", nullable=false)
    private String role;
    
    @OneToOne
	@JoinColumn(name="idPerson",referencedColumnName="idPerson", nullable=false)
    private Person person;
    
	/**
	 * @return the idFamily
	 */
    @XmlElement(required=true)
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
	@XmlElement(required=true)
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
	@XmlElement(required=true)
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
	@XmlElement(required=true)
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
	
	@XmlTransient
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	// Database operations
    public static Family getFamilyById(int idFamily) {
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        em.getEntityManagerFactory().getCache().evictAll();
        Family p = em.find(Family.class, idFamily);
        LifeCoachDao.instance.closeConnections(em);
        return p;
    }
    
    public static List<Family> getAll() {
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        em.getEntityManagerFactory().getCache().evictAll();
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
