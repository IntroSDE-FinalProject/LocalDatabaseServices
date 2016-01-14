package introsde.finalproject.soap.wrapper;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import introsde.finalproject.soap.model.MeasureDefinition;
/**
 * Wrapper used when are listened the measureName of all the MeasureDefinition
 *
 */
@XmlRootElement(name="measureTypes")
public class ListMeasureDefinitionWrapper {
	
	@XmlElement(name="measureType")
	@JsonProperty("measureTypes")
	public List<introsde.finalproject.soap.model.MeasureDefinition> measureDefinition = new ArrayList<MeasureDefinition>();
	
	public void setMeasureDefinition(List<MeasureDefinition> definitions) {
		this.measureDefinition = definitions;
	}
	
	
}
