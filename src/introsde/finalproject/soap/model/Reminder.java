package introsde.finalproject.soap.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

import introsde.finalproject.soap.dao.LifeCoachDao;

/**
 * The persistent class for the "Reminder" database table.
 * 
 */
@Entity
@Table(name="Reminder")
@NamedQuery(name="Reminder.findAll", query="SELECT f FROM Reminder f")
public class Reminder implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    // For sqlite in particular, you need to use the following @GeneratedValue annotation
    // This holds also for the other tables
    // SQLITE implements auto increment ids through named sequences that are stored in a 
    // special table named "sqlite_sequence"
    @GeneratedValue(generator="sqlite_reminder")
    @TableGenerator(name="sqlite_reminder", table="sequence",
        pkColumnName="name", valueColumnName="seq",
        pkColumnValue="Reminder",
        initialValue=1, allocationSize=1)
    @Column(name="idReminder", nullable=false)
    private int idReminder;

    @Column(name="text", nullable=false)
    private String text;
    
    @Temporal(TemporalType.DATE)
    @Column(name="createReminder", nullable=false)
    private Date createReminder;
    
    @Temporal(TemporalType.DATE)
    @Column(name="expireReminder")
    private Date expireReminder;
    
    @Column(name="autocreate", nullable=false)
    private Boolean autocreate;
    
    @Column(name="relevanceLevel", nullable=false)
    private Boolean relevanceLevel;
    
    @ManyToOne
	@JoinColumn(name="idPerson",referencedColumnName="idPerson", nullable=false)
	private Person person;

	/**
	 * @return the idReminder
	 */
	public int getIdReminder() {
		return idReminder;
	}

	/**
	 * @param idReminder the idReminder to set
	 */
	public void setIdReminder(int idReminder) {
		this.idReminder = idReminder;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the createReminder
	 */
	public Date getCreateReminder() {
		return createReminder;
	}

	/**
	 * @param createReminder the createReminder to set
	 */
	public void setCreateReminder(Date createReminder) {
		this.createReminder = createReminder;
	}

	/**
	 * @return the expireReminder
	 */
	public Date getExpireReminder() {
		return expireReminder;
	}

	/**
	 * @param expireReminder the expireReminder to set
	 */
	public void setExpireReminder(Date expireReminder) {
		this.expireReminder = expireReminder;
	}

	/**
	 * @return the autocreate
	 */
	public Boolean getAutocreate() {
		return autocreate;
	}

	/**
	 * @param autocreate the autocreate to set
	 */
	public void setAutocreate(Boolean autocreate) {
		this.autocreate = autocreate;
	}

	/**
	 * @return the relevanceLevel
	 */
	public Boolean getRelevanceLevel() {
		return relevanceLevel;
	}

	/**
	 * @param relevanceLevel the relevanceLevel to set
	 */
	public void setRelevanceLevel(Boolean relevanceLevel) {
		this.relevanceLevel = relevanceLevel;
	}
    
	// we make this transient for JAXB to avoid and infinite loop on serialization
	@XmlTransient
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
		
	// Database operations
    public static Reminder getReminderById(int idReminder) {
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        Reminder p = em.find(Reminder.class, idReminder);
        LifeCoachDao.instance.closeConnections(em);
        return p;
    }
    
    public static List<Reminder> getAll() {
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        List<Reminder> list = em.createNamedQuery("Reminder.findAll", Reminder.class).getResultList();
        LifeCoachDao.instance.closeConnections(em);
        return list;
    }
    
    public static Reminder saveReminder(Reminder p) {
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(p);
        tx.commit();
        LifeCoachDao.instance.closeConnections(em);
        return p;
    }
    
    public static Reminder updateReminder(Reminder p) {
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        p=em.merge(p);
        tx.commit();
        LifeCoachDao.instance.closeConnections(em);
        return p;
    }
    
    public static void removeReminder(Reminder p) {
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        p=em.merge(p);
        em.remove(p);
        tx.commit();
        LifeCoachDao.instance.closeConnections(em);
    }  
    
}
