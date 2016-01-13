package introsde.finalproject.soap.wrapper;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import  introsde.finalproject.soap.model.Person;

@XmlRootElement(name="People")
public class ListPersonWrapper {
	
	@XmlElement(name="person")
	@JsonProperty("person")
	public List<Person> person = new ArrayList<Person>();
	
	public void setPerson(List<Person> persons) {
		this.person = persons;
	}
	
	
}
