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
 * The persistent class for the "Target" database table.
 * 
 */
@Entity
@Table(name="Target")
//@NamedQuery(name="Target.findAll", query="SELECT t FROM Target t")
@NamedQueries({
	@NamedQuery(name="Target.findAll", query="SELECT t FROM Target t"),
	@NamedQuery(name="Target.findByPersonId", query="SELECT t FROM Target t WHERE t.person = ?1 "),
	@NamedQuery(name="Target.findByIdPersonAndIdMeasure", query="SELECT t FROM Target t WHERE t.person = ?1 AND t.measureDefinition = ?2 ")
})
public class Target implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="sqlite_target")
	@TableGenerator(name="sqlite_target", table="sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="Target")
	@Column(name="idTarget", nullable=false)
	private int idTarget;
	
	@Column(name="value", nullable=false)
	private int value;
	
	@Temporal(TemporalType.DATE)
	@Column(name="startDateTarget")
	private Date startDateTarget;
	
	@Temporal(TemporalType.DATE)
	@Column(name="endDateTarget", nullable=false)
	private Date endDateTarget;
	
	@Column(name="conditionTarget", nullable=false)
	private String conditionTarget;
	
	@Column(name="achieved")
	private Boolean achieved;
	
	@ManyToOne
	@JoinColumn(name="idPerson",referencedColumnName="idPerson", nullable=false)
	private Person person;
	
	@ManyToOne
	@JoinColumn(name = "idMeasureDef", referencedColumnName = "idMeasureDef")
	private MeasureDefinition measureDefinition;
	
	public Target() {
    }
    
    @XmlAttribute(name="idTarget")
    public int getIdTarget() {
        return idTarget;
    }
   
    public void setIdTarget(int idTarget) {
        this.idTarget = idTarget;
    }
    
    /**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @return the startDateTarget
	 */
	public String getStartDateTarget() {
		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	     return df.format(this.startDateTarget);
	}

	/**
	 * @param startDateTarget the startDateTarget to set
	 * @throws ParseException 
	 */
	public void setStartDateTarget(String startDateTarget) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date date = format.parse(startDateTarget);
		this.startDateTarget = date;
	}

	/**
	 * @return the endDateTarget
	 */
	public String getEndDateTarget() {
		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	     return df.format(this.endDateTarget);
	}

	/**
	 * @param endDateTarget the endDateTarget to set
	 * @throws ParseException 
	 */
	public void setEndDateTarget(String endDateTarget) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date date = format.parse(endDateTarget);
		this.endDateTarget = date;
	}

	/**
	 * @return the conditionTarget
	 */
	public String getConditionTarget() {
		return conditionTarget;
	}

	/**
	 * @param conditionTarget the conditionTarget to set
	 */
	public void setConditionTarget(String conditionTarget) {
		this.conditionTarget = conditionTarget;
	}

	/**
	 * @return the achieved
	 */
	public Boolean getAchieved() {
		return achieved;
	}

	/**
	 * @param achieved the achieved to set
	 */
	public void setAchieved(Boolean achieved) {
		this.achieved = achieved;
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
		
	// database operations
		public static Target getTargetById(int personId) {
			EntityManager em = LifeCoachDao.instance.createEntityManager();
			Target p = em.find(Target.class, personId);
			LifeCoachDao.instance.closeConnections(em);
			return p;
		}
		
		public static List<Target> getAll() {
			EntityManager em = LifeCoachDao.instance.createEntityManager();
		    List<Target> list = em.createNamedQuery("Target.findAll", Target.class).getResultList();
		    LifeCoachDao.instance.closeConnections(em);
		    return list;
		}
		
		public static Target saveTarget(Target p) {
			EntityManager em = LifeCoachDao.instance.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(p);
			tx.commit();
		    LifeCoachDao.instance.closeConnections(em);
		    return p;
		}
		
		public static Target updateTarget(Target p) {
			EntityManager em = LifeCoachDao.instance.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			p=em.merge(p);
			tx.commit();
		    LifeCoachDao.instance.closeConnections(em);
		    return p;
		}
		
		public static void removeTarget(Target p) {
			EntityManager em = LifeCoachDao.instance.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
		    p=em.merge(p);
		    em.remove(p);
		    tx.commit();
		    LifeCoachDao.instance.closeConnections(em);
		}
		
		
		
		public static List<Target> getTargetByPersonId(Person p) {
	        EntityManager em = LifeCoachDao.instance.createEntityManager();
	        List<Target> list = em.createNamedQuery("Target.findByPersonId", Target.class)
	        		.setParameter(1, p)
	        		.getResultList();
	        LifeCoachDao.instance.closeConnections(em);
	        return list;
	    }
		
		
		public static List<Target> getTargetByMeasure(Person p, MeasureDefinition m) {
	        EntityManager em = LifeCoachDao.instance.createEntityManager();
	        List<Target> list = em.createNamedQuery("Target.findByIdPersonAndIdMeasure", Target.class)
	        		.setParameter(1, p)
	        		.setParameter(2, m)
	        		.getResultList();
	        LifeCoachDao.instance.closeConnections(em);
	        return list;
	    }
		
		
		
}
