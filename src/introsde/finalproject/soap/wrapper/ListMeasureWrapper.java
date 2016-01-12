package introsde.finalproject.soap.wrapper;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import  introsde.finalproject.soap.model.Measure;

@XmlRootElement(name="Measurements")
public class ListMeasureWrapper {
	
	@XmlElement(name="measure")
	@JsonProperty("measure")
	public List<Measure> measure = new ArrayList<Measure>();
	
	public void setMeasure(List<Measure> measures) {
		this.measure = measures;
	}
	
	
}
