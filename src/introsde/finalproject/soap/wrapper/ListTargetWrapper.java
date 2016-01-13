package introsde.finalproject.soap.wrapper;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import introsde.finalproject.soap.model.Target;

@XmlRootElement(name="Targets")
public class ListTargetWrapper {
	
	@XmlElement(name="target")
	@JsonProperty("target")
	public List<Target> target = new ArrayList<Target>();
	
	public void setTarget(List<Target> targets) {
		this.target = targets;
	}
	
	
}
