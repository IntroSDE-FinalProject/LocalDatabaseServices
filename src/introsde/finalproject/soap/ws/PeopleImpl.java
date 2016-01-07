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
    public List<Person> getPeople() {
        return Person.getAll();
    }

    @Override
    public int addPerson(Person person) {
        Person.savePerson(person);
        return person.getIdPerson();
    }

    @Override
    public int updatePerson(Person person) {
        Person.updatePerson(person);
        return person.getIdPerson();
    }

    @Override
    public int deletePerson(int id) {
        Person p = Person.getPersonById(id);
        if (p!=null) {
            Person.removePerson(p);
            return 0;
        } else {
            return -1;
        }
    }
    
    
    @Override
	public Target getTargetByMeasure(int id, String measureName) {
		// TODO Auto-generated method stub
		return null;
	}
    
    
    
    /*
    @Override
    public int updatePersonHP(int id, Measure hp) {
        Measure ls = Measure.getMeasureById(hp.getIdMeasure());
        if (ls.getPerson().getIdPerson() == id) {
            Measure.updateMeasure(hp);
            return hp.getIdMeasure();
        } else {
            return -1;
        }
    }*/
    
    //***Doctor***
    
    @Override
    public int addDoctor(Doctor doctor){
    	Doctor d = Doctor.saveDoctor(doctor);
        return d.getIdDoctor();
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
    public int deleteDoctor(int id) {
        Doctor d = Doctor.getDoctorById(id);
        if (d!=null) {
            Doctor.removeDoctor(d);
            return 0;
        } else {
            return -1;
        }
    }
    
    @Override
    public int updateDoctor(Doctor doctor) {
    	Doctor.updateDoctor(doctor);
        return doctor.getIdDoctor();
    }
    
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
    
    
    public int addReminder(Reminder reminder){
    	Reminder r = Reminder.saveReminder(reminder);
        return r.getIdReminder();
    }

	@Override
	public int addTarget(Target target) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Target> getTargets(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> getPersonByDoctor(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Measure> getVitalSigns(int id, int idMeasureDef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reminder> readReminder(int id) {
		Person p = Person.getPersonById(id);
		System.out.println("---> Reading Person by id = "+id);
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

	@Override
	public List<MeasureDefinition> getMeasureDefinition() {
		return MeasureDefinition.getAll();
	}
	
	
	
	
	@Override
	public List<Measure> getMeasure(int id) {
		Person p = Person.getPersonById(id);
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
	}

	@Override
	public int addMeasure(Measure measure) {
		// TODO Auto-generated method stub
		return 0;
	}

}