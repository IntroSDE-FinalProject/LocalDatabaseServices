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
    @WebResult(name="personId") 
    public int deletePerson(@WebParam(name="personId") int id);
    
    @WebMethod(operationName="getTarget")
    @WebResult(name="targets") 
    public Target getTargetByMeasure(@WebParam(name="personId") int id, @WebParam(name="measureName") String measureName);
    
    /*
    @WebMethod(operationName="updatePersonHealthProfile")
    @WebResult(name="hpId") 
    public int updatePersonHP(@WebParam(name="personId") int id, @WebParam(name="healthProfile") Measure hp);
    */
    //***Doctor***
    
    @WebMethod(operationName="createDoctor")
    @WebResult(name="doctorId") 
    public int addDoctor(@WebParam(name="doctor") Doctor doctor);
    
    @WebMethod(operationName="getDoctor")
    @WebResult(name="doctor") 
    public Doctor readDoctor(@WebParam(name="doctorId") int id);
    
    @WebMethod(operationName="deleteDoctor")
    @WebResult(name="doctorId") 
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
}