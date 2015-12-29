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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlTransient;

import introsde.finalproject.soap.dao.LifeCoachDao;

/**
 * The persistent class for the "Doctor" database table.
 * 
 */
@Entity
@Table(name="Doctor")
@NamedQuery(name="Doctor.findAll", query="SELECT f FROM Doctor f")
public class Doctor implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(generator="sqlite_doctor")
    @TableGenerator(name="sqlite_doctor", table="sequence",
        pkColumnName="name", valueColumnName="seq",
        pkColumnValue="Doctor",
        initialValue=1, allocationSize=1)
    @Column(name="idDoctor", nullable=false)
    private int idDoctor;
    
    @Column(name="firstname", nullable=false)
    private String firstname;
    
    @Column(name="lastname", nullable=false)
    private String lastname;
    
    @Column(name="specialization", nullable=false)
    private String specialization;
    
    @Column(name="city", nullable=false)
    private String city;
    
    @OneToMany(mappedBy="doctor")
    private List<Person> patients;
    
	/**
	 * @return the idDoctor
	 */
	public int getIdDoctor() {
		return idDoctor;
	}

	/**
	 * @param idDoctor the idDoctor to set
	 */
	public void setIdDoctor(int idDoctor) {
		this.idDoctor = idDoctor;
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
	 * @return the specialization
	 */
	public String getSpecialization() {
		return specialization;
	}

	/**
	 * @param specialization the specialization to set
	 */
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	@XmlTransient
	public List<Person> getPatients() {
	    return this.patients;
	}

	public void setPatients(List<Person> param) {
	    this.patients = param;
	}
	
	// database operations
		public static Doctor getDoctorById(int personId) {
			EntityManager em = LifeCoachDao.instance.createEntityManager();
			Doctor p = em.find(Doctor.class, personId);
			LifeCoachDao.instance.closeConnections(em);
			return p;
		}
		
		public static List<Doctor> getAll() {
			EntityManager em = LifeCoachDao.instance.createEntityManager();
		    List<Doctor> list = em.createNamedQuery("Doctor.findAll", Doctor.class).getResultList();
		    LifeCoachDao.instance.closeConnections(em);
		    return list;
		}
		
		public static Doctor saveDoctor(Doctor p) {
			EntityManager em = LifeCoachDao.instance.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(p);
			tx.commit();
		    LifeCoachDao.instance.closeConnections(em);
		    return p;
		}
		
		public static Doctor updateDoctor(Doctor p) {
			EntityManager em = LifeCoachDao.instance.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			p=em.merge(p);
			tx.commit();
		    LifeCoachDao.instance.closeConnections(em);
		    return p;
		}
		
		public static void removeDoctor(Doctor p) {
			EntityManager em = LifeCoachDao.instance.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
		    p=em.merge(p);
		    em.remove(p);
		    tx.commit();
		    LifeCoachDao.instance.closeConnections(em);
		}
}
