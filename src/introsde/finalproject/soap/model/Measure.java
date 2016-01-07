package introsde.finalproject.soap.model;

import introsde.finalproject.soap.dao.LifeCoachDao;
import introsde.finalproject.soap.model.MeasureDefinition;

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
import javax.xml.bind.annotation.XmlTransient;
import javax.persistence.OneToOne;

/**
 * The persistent class for the "Measure" database table.
 * 
 */
@Entity
@Table(name = "Measure")
//@NamedQuery(name = "Measure.findAll", query = "SELECT l FROM Measure l")
@NamedQueries({
	@NamedQuery(name = "Measure.findAll", query = "SELECT m FROM Measure m"),
	@NamedQuery(name="Measure.findByPersonId", query="SELECT m FROM Measure m WHERE m.person = ?1 "),
	@NamedQuery(name="Measure.findVitalSigns", query="SELECT m FROM Measure m WHERE m.person = ?1 AND ((m.measureDefinition = ?2) OR (m.measureDefinition = ?3) ) ")
})
public class Measure implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="sqlite_measure")
	@TableGenerator(name="sqlite_measure", table="sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="Measure")
	@Column(name = "idMeasure", nullable=false)
	private int idMeasure;

	@Column(name = "value", nullable=false)
	private String value;
	
	@Temporal(TemporalType.DATE)
    @Column(name="timestamp", nullable=false)
    private Date timestamp;
	
	@OneToOne
	@JoinColumn(name = "idMeasureDef", referencedColumnName = "idMeasureDef", nullable=false, insertable = true, updatable = true)
	private MeasureDefinition measureDefinition;
	
	@ManyToOne
	@JoinColumn(name="idPerson",referencedColumnName="idPerson", nullable=false)
	private Person person;
	
	public Measure() {
	}

	public int getIdMeasure() {
		return this.idMeasure;
	}

	public void setIdMeasure(int idMeasure) {
		this.idMeasure = idMeasure;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(this.timestamp);
	}

	/**
	 * @param timestamp the timestamp to set
	 * @throws ParseException 
	 */
	public void setTimestamp(String timestamp) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = format.parse(timestamp);
        this.timestamp = date;
	}
	
	public MeasureDefinition getMeasureDefinition() {
		return measureDefinition;
	}

	public void setMeasureDefinition(MeasureDefinition param) {
		this.measureDefinition = param;
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
	public static Measure getMeasureById(int MeasureId) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Measure p = em.find(Measure.class, MeasureId);
		LifeCoachDao.instance.closeConnections(em);
		return p;
	}
	
	public static List<Measure> getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
	    List<Measure> list = em.createNamedQuery("Measure.findAll", Measure.class).getResultList();
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Measure saveMeasure(Measure p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(p);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return p;
	}
	
	public static Measure updateMeasure(Measure p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		p=em.merge(p);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return p;
	}
	
	public static void removeMeasure(Measure p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    p=em.merge(p);
	    em.remove(p);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
	
	
	public static List<Measure> getMeasureByPersonId(Person p) {
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        List<Measure> list = em.createNamedQuery("Measure.findByPersonId", Measure.class)
        		.setParameter(1, p)
        		.getResultList();
        LifeCoachDao.instance.closeConnections(em);
        return list;
    }
	
	public static List<Measure> getVitalSigns(Person p,MeasureDefinition m1, MeasureDefinition m2) {
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        List<Measure> list = em.createNamedQuery("Measure.findVitalSigns", Measure.class)
        		.setParameter(1, p)
        		.setParameter(2, m1)
        		.setParameter(3, m2)
        		.getResultList();
        LifeCoachDao.instance.closeConnections(em);
        return list;
    }
	
	
	

}
