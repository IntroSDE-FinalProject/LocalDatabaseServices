package introsde.finalproject.soap.ws;

import introsde.finalproject.soap.model.Doctor;
import introsde.finalproject.soap.model.Family;
import introsde.finalproject.soap.model.Measure;
import introsde.finalproject.soap.model.MeasureDefinition;
import introsde.finalproject.soap.model.Person;
import introsde.finalproject.soap.model.Reminder;
import introsde.finalproject.soap.model.Target;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

//Service Implementation

@WebService(endpointInterface = "introsde.finalproject.soap.ws.People",
    serviceName="PeopleService")
public class PeopleImpl implements People {

	//***Person***
    
	// ---------------- START CRUD - PERSON --------------------
    @Override
    public int addPerson(Person person) {
    	try{
    		person = Person.savePerson(person);
    		return person.getIdPerson();
    	}catch(Exception e){
    		System.out.println("Person not saved due the exeception: " + e);
    		return -1;
    	}
    }
    
    @Override
    public Person readPerson(int id) {
        System.out.println("---> Reading Person by id = "+id);
        Person p = Person.getPersonById(id);
        if (p!=null) {
            System.out.println("---> Found Person by id = "+id+" => "+p.getFirstname());
        } else {
            System.out.println("---> Didn't find any Person with  id = "+id);
        }
        return p;
    }
    
    @Override
    public int updatePerson(Person person) {
    	try{
    		int id = person.getIdPerson();
    		if(id!= 0){
    			if(Person.getPersonById(id) != null){
    				Person.updatePerson(person);
    				return person.getIdPerson();
    			}else{
    				System.out.println("Does not exist a Person with id: " + id);
    				return -2;
    			}
    		}else{
    			System.out.println("personId is equals to " + id );
    			return -2;
    		}
    	}catch(Exception e){
    		System.out.println("Person not updated due the exception: " + e);
    		return -1;
    	}
        
    }

    @Override
    public int deletePerson(int id) {
    	try{
        Person p = Person.getPersonById(id);
        if (p!=null) {
            Person.removePerson(p);
            return 1;
        } else {
        	System.out.println("Does not exist a Person with id: " + id);
            return -2;
        }
    	}catch(Exception e){
    		System.out.println(e);
    		return -1;
    	}
    }
    
    // ---------------- END CRUD - Doctor --------------------
    
    @Override
    public List<Person> getPeople() {
        return Person.getAll();
    }
  
    
    //***Doctor***
    
    // ---------------- START CRUD - Doctor --------------------
    @Override
    public int addDoctor(Doctor doctor){
    	try{
    		Doctor d = Doctor.saveDoctor(doctor);
            return d.getIdDoctor();
    	}catch(Exception e){
    		System.out.println("Doctor not saved due the exception: " + e);
    		return -1;
    	}
    }
    
    @Override
    public Doctor readDoctor(int id) {
        System.out.println("---> Reading Doctor by id = "+id);
        Doctor p = Doctor.getDoctorById(id);
        if (p!=null) {
            System.out.println("---> Found Doctor by id = "+id+" => "+p.getFirstname());
        } else {
            System.out.println("---> Didn't find any Doctor with  id = "+id);
        }
        return p;
    }
    
    
    @Override
    public int updateDoctor(Doctor doctor) {
    	try{
    		int id = doctor.getIdDoctor();
    		if(id!= 0){
    			if(Doctor.getDoctorById(id) != null){
    				Doctor.updateDoctor(doctor);
    				return doctor.getIdDoctor();
    			}else{
    				System.out.println("Does not exist a Doctor with id: " + id);
    				return -2;
    			}
    		}else{
    			System.out.println("doctorId is equals to " + id );
    			return -2;
    		}
    	}catch(Exception e){
    		System.out.println("Doctor not updated due the exception: " + e);
    		return -1;
    	}
    }
    
    
    @Override
    public int deleteDoctor(int id) {
    	try{
    		Doctor d = Doctor.getDoctorById(id);
    		if(d!=null){
    			Doctor.removeDoctor(d);
    			return 1;
    		}else{
    			System.out.println("Does not exist a Doctor with id: " + id);
                return -2;
    		}
    	}catch(Exception e){
    		System.out.println("Error deleting the Doctor due the exception: " + e);
    		return -1;
    	}
    }
    // ---------------- END CRUD - DOCTOR --------------------
    
    
    //***Family***
    
    public Family readFamily(int id){
    	System.out.println("---> Reading Family by id = "+id);
    	Family f = Family.getFamilyById(id);
        if (f!=null) {
            System.out.println("---> Found Family by id = "+id+" => "+f.getFirstname());
        } else {
            System.out.println("---> Didn't find any Family with  id = "+id);
        }
        return f;
    }
    
    
    //***Reminder***
    
    
    /**
     * This method is used to add a reminder
     * 
     * @param reminder
     * @return the idReminder just created
     */
    public int addReminder(Reminder reminder){
    	Reminder r = Reminder.saveReminder(reminder);
        return r.getIdReminder();
    }
    
    /**
     * This method is used to read the reminders of a specified person passed through id
     * 
     * @param id
     * @return a list of reminders for the person identified by personId
     */
    @Override
    public List<Reminder> readReminder(int personId) {
    	Person p = Person.getPersonById(personId);
    	System.out.println("---> Reading Person by id = "+personId);
    	List<Reminder> reminderList = null;
    	reminderList = Reminder.getReminderByPersonId(p); 
    	return reminderList;

    	/*
        if (p!=null) {
            System.out.println("---> Found Person by id = "+id+" => "+p.getFirstname());
        } else {
            System.out.println("---> Didn't find any Person with  id = "+id);
        }
    	 */
    }
    
    /**
     * This method is used to update a specified reminder passed as parameter
     * 
     * @param reminder
     * @return idReminder just updated
     */
	@Override
	public int updateReminder(Reminder reminder) {
		Reminder.updateReminder(reminder);
		return reminder.getIdReminder();
	}

	/**
	 * This method is used to delete a reminder
	 * 
	 * @param idReminder
	 * @return 0 or 1 , 1 if the method succeed 0 otherwise
	 */
	@Override
	public int deleteReminder(int idReminder) {
		Reminder r = Reminder.getReminderById(idReminder);
		if (r!=null) {
			Reminder.removeReminder(r);
			return 0;
		} else {
			return -1;
		}
	}
    
    
	
	//***Target***
	

	/**
	 * This method is used to add a target
	 * 
	 * @param target
	 * @return
	 */
	@Override
	public int addTarget(Target target) {
		Target.saveTarget(target);
        return target.getIdTarget();
	}

	
	/**
     * This method is used to update a specified reminder passed as parameter
     * 
     * @param reminder
     * @return idReminder just updated
     */
	@Override
	public int updateTarget(Target target) {
		Target.updateTarget(target);
		return target.getIdTarget();
	}

	/**
	 * This method is used to delete a reminder
	 * 
	 * @param idReminder
	 * @return 0 or 1 , 1 if the method succeed 0 otherwise
	 */
	@Override
	public int deleteTarget(int idTarget) {
		Target t = Target.getTargetById(idTarget);
		if (t!=null) {
			Target.removeTarget(t);
			return 0;
		} else {
			return -1;
		}
	}
	
	
	
	/**
	 * This method is used to get a list of targets for a specified personId
	 * 
	 * @param id
	 * @return list of targets for a specified person identified by a personId
	 */
	@Override
	public List<Target> getTargetList(int id) {
		Person p = Person.getPersonById(id);
		List<Target> targetList = null;
        if (p!=null) {
            System.out.println("---> Found Person by id = "+id+" => "+p.getIdPerson());
            targetList = Target.getTargetByPersonId(p);
            return targetList;
        } else {
            System.out.println("---> Didn't find any Person with  id = "+id);
        }
		return targetList;
	}
	
	
  
    
    @Override
	public List<Target> getTargetByMeasure(int idPerson, int idMeasureDef) {
		Person p = Person.getPersonById(idPerson);
		MeasureDefinition m = MeasureDefinition.getMeasureDefinitionById(idMeasureDef);
		List<Target> targetList = null;
		if( (p != null) && (m != null) ){
			System.out.println("---> Found Person by id = "+idPerson+" => "+p.getIdPerson());
			System.out.println("---> Found MeasureDefinition by id = "+idMeasureDef+" => "+m.getIdMeasureDef());
			targetList = Target.getTargetByMeasure(p, m);
            return targetList;
        }else{
            System.out.println("Is empty or null");
        }
		
		return targetList;
		
	}
	
	//**END TARGET***
	

	/**
	 * This method is used to retrieve all patients associated to a specified doctor 
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public List<Person> getPersonByDoctor(int idDoctor) {
		Doctor d = Doctor.getDoctorById(idDoctor);
		return d.getPatients();
	}

	
	/**
	 * This method is used to retrieve vital signs of a person
	 * 
	 * @param idMeasureDef
	 * @return
	 */
	@Override
	public List<Measure> getVitalSigns(int idPerson) {
		Person p = Person.getPersonById(idPerson);
		List<Measure> vitalSignList = null;
		int min = 4;
		int max = 5;
		MeasureDefinition m1 = MeasureDefinition.getMeasureDefinitionById(min);
		MeasureDefinition m2 = MeasureDefinition.getMeasureDefinitionById(max);
		if (p!=null) {
            System.out.println("---> Found Person by id = "+idPerson+" => "+p.getIdPerson());
            vitalSignList = Measure.getVitalSigns(p,m1,m2);
            return vitalSignList;
        } else {
            System.out.println("---> Didn't find any Person with  id = "+idPerson);
        }
    	//return history;
        //return Integer.parseInt(measureList.get(0).getValue());
		return vitalSignList;
	}


	/**
	 * 
	 * @return
	 */
	@Override
	public List<MeasureDefinition> getMeasureDefinition() {
		return MeasureDefinition.getAll();
	}
	
	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public List<Measure> getMeasure(int id) {
		Person p = Person.getPersonById(id);
		return p.getMeasure();
		/*
        List<Measure> measureList = null;
        if (p!=null) {
            System.out.println("---> Found Person by id = "+id+" => "+p.getIdPerson());
            measureList = Measure.getMeasureByPersonId(p);
            return measureList;
        } else {
            System.out.println("---> Didn't find any Person with  id = "+id);
        }
    	//return history;
        //return Integer.parseInt(measureList.get(0).getValue());
		return measureList;
		*/
	}

	/**
	 * 
	 * @param measure
	 * @return
	 */
	@Override
	public int addMeasure(Measure measure) {
		Measure.saveMeasure(measure);
        return measure.getIdMeasure();
	}
	
	/**
	 * 
	 * @param measure
	 * @return
	 */
	@Override
	public int updateMeasure(Measure measure) {
		Measure.updateMeasure(measure);
		return measure.getIdMeasure();
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public int deleteMeasure(int id) {
		Measure m = Measure.getMeasureById(id);
		if (m!=null) {
			Measure.removeMeasure(m);
			return 0;
		} else {
			return -1;
		}
	}

	

}