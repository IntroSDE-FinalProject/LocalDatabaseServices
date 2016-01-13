package introsde.finalproject.soap.wrapper;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import  introsde.finalproject.soap.model.Reminder;

@XmlRootElement(name="Reminders")
public class ListReminderWrapper {
	
	@XmlElement(name="reminder")
	@JsonProperty("reminder")
	public List<Reminder> reminder = new ArrayList<Reminder>();
	
	public void setMeasure(List<Reminder> reminders) {
		this.reminder = reminders;
	}
	
	
}
