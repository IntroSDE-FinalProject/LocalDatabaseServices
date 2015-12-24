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
 * The persistent class for the "Doctor" database table.
 * 
 */
@Entity
@Table(name="Doctor")
@NamedQuery(name="Doctor.findAll", query="SELECT f FROM Doctor f")
public class Doctor implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    // For sqlite in particular, you need to use the following @GeneratedValue annotation
    // This holds also for the other tables
    // SQLITE implements auto increment ids through named sequences that are stored in a 
    // special table named "sqlite_sequence"
    @GeneratedValue(generator="sqlite_doctor")
    @TableGenerator(name="sqlite_doctor", table="sequence",
        pkColumnName="name", valueColumnName="seq",
        pkColumnValue="Doctor",
        initialValue=1, allocationSize=1)
    @Column(name="idDoctor")
    private int idDoctor;
    
    @Column(name="firstname")
    private String firstname;
    
    @Column(name="lastname")
    private String lastname;
    
    @Column(name="specialization")
    private String specialization;
    
    @Column(name="city")
    private String city;

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
