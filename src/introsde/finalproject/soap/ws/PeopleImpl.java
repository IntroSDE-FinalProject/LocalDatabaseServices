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
	
	/**
	 * This method is used to add a Person
	 * 
	 * @param person
	 * @return idPerson the id of the Person created
	 */
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
    
    /**
     * This method is used to read the information of the Person
     * specified in the id
     * 
     * @param id idPerson
     * @return p Person requested
     */
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
    
    /**
     * This method is used to update the Person specified as paramater
     * 
     * @param person the person to update
     * @return personId the id of the updated person
     */
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

    /**
     * This method is used to delete the Person with a specified id
     * 
     * @param id the personId of the Person to delete
     * @return 1 if ok, -2 if the resource is not found, -1 if there is an Error 
     */
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
    		System.out.println("Person not deleted due the exception: " + e);
    		return -1;
    	}
    }
    
    // ---------------- END CRUD - Doctor --------------------
    
    /**
     * This method is used to retrieve the list of Person saved in the database
     * 
     * @return List of Person
     */
    @Override
    public List<Person> getPeople() {
        return Person.getAll();
    }
  
    
    //***Doctor***
    
    // ---------------- START CRUD - Doctor --------------------
    
    /**
     * This method is used to add a Doctor 
     * 
     * @param doctor the doctor to add
     * @return doctorId the id of the Doctor just added
     */
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
    
    
    /**
     * This method is used to read the information of the Doctor
     * specified in the id
     * 
     * @param id of the Doctor  
     * @return idDoctor
     */
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
    
    /**
     * This method is used to update the Doctor specified as paramater
     * 
     * @param doctor to update
     * @return personId the id of the updated person
     */
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
    
    /**
     * This method is used to delete the Person with a specified id
     * 
     * @param id
     * @return 1 if ok, -2 if the resource is not found, -1 if there is an Error
     */
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
    
    /**
     * This method is used read the information of the Family
     * 
     * @param id
     * @return idFamily
     */
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
    
    // ---------------- START CRUD - REMINDER --------------------
    /**
     * This method is used to add a reminder
     * 
     * @param reminder
     * @return the idReminder just created
     */
    public int addReminder(Reminder reminder){
    	try{
    		Reminder r = Reminder.saveReminder(reminder);
    		return r.getIdReminder();
    	}catch(Exception e){
    		System.out.println("Reminder not saved due the exeception: " + e);
    		return -1;
    	}
    }
    
    /**
     * This method is used to read the reminders of a specified person passed through id.
     * If the Person is not null read the reminders of the specified person otherwise 
     * returns a null List
     * 
     * @param id
     * @return a list of reminders for the person identified by personId
     */
    @Override
    public List<Reminder> readReminder(int personId) {
    	Person p = Person.getPersonById(personId);
    	if(p != null){
    	//System.out.println("---> Reading Person by id = "+personId);
    	List<Reminder> reminderList = null;
    	reminderList = Reminder.getReminderByPersonId(p);
    	System.out.println("Valore reminderList: " + reminderList);
    	return reminderList;
    	}else{
    		List<Reminder> reminderListException = null;
    		System.out.println("Not exists personId return value of reminderList: " + reminderListException);
    		return reminderListException;
    	}
    }
    
    /**
     * This method is used to update a specified reminder passed as parameter
     * 
     * @param reminder
     * @return idReminder just updated
     */
	@Override
	public int updateReminder(Reminder reminder) {
		try{
    		int id = reminder.getIdReminder();
    		if(id!= 0){
    			if(Reminder.getReminderById(id) != null){
    				Reminder.updateReminder(reminder);
    				return reminder.getIdReminder();
    			}else{
    				System.out.println("Does not exist a Reminder with id: " + id);
    				return -2;
    			}
    		}else{
    			System.out.println("reminderId is equals to " + id );
    			return -2;
    		}
    	}catch(Exception e){
    		System.out.println("Reminder not updated due the exception: " + e);
    		return -1;
    	}
	}

	/**
	 * This method is used to delete a reminder
	 * 
	 * @param idReminder
	 * @return 0 or 1 , 1 if the method succeed 0 otherwise
	 */
	@Override
	public int deleteReminder(int idReminder) {
		try{
			Reminder r = Reminder.getReminderById(idReminder);
			if (r!=null) {
				Reminder.removeReminder(r);
				return 1;
			} else {
				return -2;
			}
		}catch(Exception e){
			System.out.println("Reminder not deleted due the exception: " + e);
			return -1;
		}
		
	}
    
    // ---------------- END CRUD - REMINDER --------------------
	
	
	
	//***Target***
	
    // ---------------- START CRUD - TARGET --------------------

	/**
	 * This method is used to add a target
	 * 
	 * @param target
	 * @return
	 */
	@Override
	public int addTarget(Target target) {
		try{
			Target.saveTarget(target);
	        return target.getIdTarget();
		}catch(Exception e){
			System.out.println("Target not saved due the exception: " + e);
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
            return targetList;
        }
	}
	
	
	
	/**
     * This method is used to update a specified reminder passed as parameter
     * 
     * @param reminder
     * @return idReminder just updated
     */
	@Override
	public int updateTarget(Target target) {
		try{
			int id = target.getIdTarget();
			if(id!= 0){
    			if(Target.getTargetById(id) != null){
    				Target.updateTarget(target);
    				return target.getIdTarget();
    			}else{
    				System.out.println("Does not exist a Target with id: " + id);
    				return -2;
    			}
    		}else{
    			System.out.println("targetId is equals to " + id );
    			return -2;
    		}
		}catch(Exception e){
			System.out.println("Target not updated due the exception: " + e);
			return -1;
		}
	}

	/**
	 * This method is used to delete a reminder
	 * 
	 * @param idReminder
	 * @return 0 or 1 , 1 if the method succeed 0 otherwise
	 */
	@Override
	public int deleteTarget(int idTarget) {
		try{
			Target t = Target.getTargetById(idTarget);
			if (t!=null) {
				Target.removeTarget(t);
				return 1;
			} else {
				return -2;
			}
		}catch(Exception e){
			System.out.println("Target not deleted due the exception: " + e);
			return -1;
		}
	}
	
    /**
     * This method is used to retrieve information about 
     * target for a specified person and measure
     * Example: check if there are target about weight for the personId 1
     * 
     * @param idPerson
     * @param idMeasureDef
     * @return targetList list of target
     */
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
            return targetList;
        }
		
	}
    
    // ---------------- END CRUD - TARGET --------------------
	
	//**END TARGET***
	

    //TODO manage method exception and return values
    
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