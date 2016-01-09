package introsde.finalproject.soap.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

import introsde.finalproject.soap.dao.LifeCoachDao;

/**
 * The persistent class for the "Reminder" database table.
 * 
 */
@Entity
@Table(name="Reminder")
//@NamedQuery(name="Reminder.findAll", query="SELECT f FROM Reminder f")
@NamedQueries({
	@NamedQuery(name="Reminder.findAll", query="SELECT f FROM Reminder f"),
	@NamedQuery(name="Reminder.findByPersonId", query="SELECT r FROM Reminder r WHERE r.person = ?1 ")
})
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
    @Column(name="expireReminder", nullable=false)
    private Date expireReminder;
    
    @Column(name="autocreate", nullable=false)
    private Boolean autocreate;
    
    @Column(name="relevanceLevel", nullable=false)
    private int relevanceLevel;
    
    @ManyToOne
	@JoinColumn(name="idPerson",referencedColumnName="idPerson", nullable=false)
	private Person person;

	/**
	 * @return the idReminder
	 */
    //@XmlAttribute(name="idReminder")
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
	public String getCreateReminder() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(this.createReminder);
	}

	/**
	 * @param createReminder the createReminder to set
	 * @throws ParseException 
	 */
	public void setCreateReminder(String createReminder) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = format.parse(createReminder);
        this.createReminder = date;
	}

	/**
	 * @return the expireReminder
	 */
	public String getExpireReminder() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(this.expireReminder);
	}

	/**
	 * @param expireReminder the expireReminder to set
	 * @throws ParseException 
	 */
	public void setExpireReminder(String expireReminder) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = format.parse(expireReminder);
		this.expireReminder = date;
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
	public int getRelevanceLevel() {
		return relevanceLevel;
	}

	/**
	 * @param relevanceLevel the relevanceLevel to set
	 */
	public void setRelevanceLevel(int relevanceLevel) {
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
    
 // Database operations 
    public static List<Reminder> getReminderByPersonId(Person p) {
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        List<Reminder> list = em.createNamedQuery("Reminder.findByPersonId", Reminder.class)
        		.setParameter(1, p)
        		.getResultList();
        LifeCoachDao.instance.closeConnections(em);
        return list;
    }
    
    /*
    public static List<HealthMeasureHistory> getHistory(Person p, String measureType) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
	    List<HealthMeasureHistory> list = em.createNamedQuery("Person.readHistory", HealthMeasureHistory.class)
	    		.setParameter(1, p)
	    		.setParameter(2, measureType)
	    		.getResultList();
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
    */
    
    
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
