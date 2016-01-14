package introsde.finalproject.soap.model;

import introsde.finalproject.soap.dao.LifeCoachDao;
import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;


/**
 * The persistent class for the "MeasureDefinition" database table.
 * 
 */
@Entity
@Table(name="MeasureDefinition")
@NamedQuery(name="MeasureDefinition.findAll", query="SELECT m FROM MeasureDefinition m")
public class MeasureDefinition implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="sqlite_measuredef")
	@TableGenerator(name="sqlite_measuredef", table="sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="MeasureDefinition")
	@Column(name="idMeasureDef", nullable=false)
	private int idMeasureDef;

	@Column(name="measureName", nullable=false)
	private String measureName;

	@Column(name="measureType")
	private String measureType;
	
	@Column(name="startValue")
	private String startValue;
	
	@Column(name="endValue")
	private String endValue;
	
	@Column(name="alarmLevel")
	private String alarmLevel;

	public MeasureDefinition() {
	}
	
	@XmlElement(required=true)
	public int getIdMeasureDef() {
		return this.idMeasureDef;
	}

	public void setIdMeasureDef(int idMeasureDef) {
		this.idMeasureDef = idMeasureDef;
	}

	@XmlElement(required=true)
	public String getMeasureName() {
		return this.measureName;
	}

	public void setMeasureName(String measureName) {
		this.measureName = measureName;
	}

	public String getMeasureType() {
		return this.measureType;
	}

	public void setMeasureType(String measureType) {
		this.measureType = measureType;
	}

	public String getStartValue() {
		return this.startValue;
	}

	public void setStartValue(String startValue) {
		this.startValue = startValue;
	}
	
	public String getEndValue() {
		return this.endValue;
	}

	public void setEndValue(String endValue) {
		this.endValue = endValue;
	}
	
	public String getAlarmLevel() {
		return this.alarmLevel;
	}

	public void setAlarmLevel(String alarmLevel) {
		this.alarmLevel = alarmLevel;
	}

	// database operations
	public static MeasureDefinition getMeasureDefinitionById(int personId) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		em.getEntityManagerFactory().getCache().evictAll();
		MeasureDefinition p = em.find(MeasureDefinition.class, personId);
		LifeCoachDao.instance.closeConnections(em);
		return p;
	}
	
	public static List<MeasureDefinition> getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		em.getEntityManagerFactory().getCache().evictAll();
	    List<MeasureDefinition> list = em.createNamedQuery("MeasureDefinition.findAll", MeasureDefinition.class).getResultList();
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static MeasureDefinition saveMeasureDefinition(MeasureDefinition p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(p);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return p;
	}
	
	public static MeasureDefinition updateMeasureDefinition(MeasureDefinition p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		p=em.merge(p);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return p;
	}
	
	public static void removeMeasureDefinition(MeasureDefinition p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    p=em.merge(p);
	    em.remove(p);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
}
