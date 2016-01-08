package introsde.finalproject.soap.ws;
import introsde.finalproject.soap.model.*;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebResult;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
public interface People {
	
	//***User***
	
    @WebMethod(operationName="getPerson")
    @WebResult(name="person") 
    public Person readPerson(@WebParam(name="personId") int id);
 
    @WebMethod(operationName="getPeopleList")
    @WebResult(name="people") 
    public List<Person> getPeople();
 
    @WebMethod(operationName="createPerson")
    @WebResult(name="personId") 
    public int addPerson(@WebParam(name="person") Person person);
 
    @WebMethod(operationName="updatePerson")
    @WebResult(name="personId") 
    public int updatePerson(@WebParam(name="person") Person person);
    
    @WebMethod(operationName="deletePerson")
    @WebResult(name="responsePersonCode") 
    public int deletePerson(@WebParam(name="personId") int id);
    
    
    @WebMethod(operationName="getPersonByDoctor")
    @WebResult(name="patientList") 
    public List<Person> getPersonByDoctor(@WebParam(name="idDoctor") int idDoctor);
    
    
    @WebMethod(operationName="getVitalSigns")
    @WebResult(name="vitalSigns") 
    public List<Measure> getVitalSigns(@WebParam(name="personId") int id);
    
    @WebMethod(operationName="createTarget")
    @WebResult(name="targets") 
    public int addTarget(@WebParam(name="target") Target target);
 

    @WebMethod(operationName="updateTarget")
    @WebResult(name="targetId") 
    public int updateTarget(@WebParam(name="target") Target target);
    
    @WebMethod(operationName="deleteTarget")
    @WebResult(name="responseTargetCode") 
    public int deleteTarget(@WebParam(name="idTarget") int idTarget);
    
    
    @WebMethod(operationName="getTargetList")
    @WebResult(name="targets") 
    public List<Target> getTargetList(@WebParam(name="personId") int id);
    
    
    @WebMethod(operationName="getTarget")
    @WebResult(name="targets") 
    public List<Target> getTargetByMeasure(@WebParam(name="personId") int id, @WebParam(name="idMeasureDef") int idMeasureDef);
    
    
    //***Doctor***
    
    @WebMethod(operationName="createDoctor")
    @WebResult(name="doctorId") 
    public int addDoctor(@WebParam(name="doctor") Doctor doctor);
    
    @WebMethod(operationName="getDoctor")
    @WebResult(name="doctor") 
    public Doctor readDoctor(@WebParam(name="doctorId") int id);
    
    @WebMethod(operationName="deleteDoctor")
    @WebResult(name="responseDoctorCode") 
    public int deleteDoctor(@WebParam(name="doctorId") int id);
    
    @WebMethod(operationName="updateDoctor")
    @WebResult(name="doctorId") 
    public int updateDoctor(@WebParam(name="doctor") Doctor doctor);
    
    //****Family***
    
    @WebMethod(operationName="getFamily")
    @WebResult(name="family") 
    public Family readFamily(@WebParam(name="familyId") int id);
    
    //***Reminder***
   
    @WebMethod(operationName="setReminder")
    @WebResult(name="reminder") 
    public int addReminder(@WebParam(name="reminder") Reminder reminder);
    
    @WebMethod(operationName="updateReminder")
    @WebResult(name="updateReminder") 
    public int updateReminder(@WebParam(name="reminder") Reminder reminder);
    
    @WebMethod(operationName="deleteReminder")
    @WebResult(name="responseReminderCode") 
    public int deleteReminder(@WebParam(name="idReminder") int idReminder);
    
    /**
     * Get the reminders for a specified personId
     * 
     * @param id
     * @return
     */
    @WebMethod(operationName="getReminder")
    @WebResult(name="reminder") 
    public List<Reminder> readReminder(@WebParam(name="personId") int personId);
    
    
    //***Measure***
    
    @WebMethod(operationName="getMeasureDefinition")
    @WebResult(name="measureDefinition") 
    public List<MeasureDefinition> getMeasureDefinition();
    
    
    @WebMethod(operationName="getMeasure")
    @WebResult(name="measure") 
    public List<Measure> getMeasure(@WebParam(name="personId") int personId);
    
    
    @WebMethod(operationName="setMeasure")
    @WebResult(name="measure") 
    public int addMeasure(@WebParam(name="measure") Measure measure);
    
    @WebMethod(operationName="updateMeasure")
    @WebResult(name="idUpdatedMeasure") 
    public int updateMeasure(@WebParam(name="measure") Measure measure);
    
    @WebMethod(operationName="deleteMeasure")
    @WebResult(name="responseMeasureCode") 
    public int deleteMeasure(@WebParam(name="idMeasure") int idMeasure);
    
    
    
}