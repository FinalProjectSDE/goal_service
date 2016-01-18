package introsde.beemindergoals.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


//XmlRootElement defines the root element of the XML tree to which this class will be serialized
//--> <person> ... </person> 
@XmlRootElement(name = "goal")	
//XmlType can optionally define the order in which the fields of person are written
@XmlType(propOrder = { "slug", "title", "goal_type", "graph_url", "panic" , "losedate" , "goaldate" , "rate" , "updated_at" })
//XmlAccessorType indicates what to use to create the mapping: either FIELDS, PROPERTIES (i.e., getters/setters), PUBLIC_MEMBER or NONE (which means, you should indicate manually)
@XmlAccessorType(XmlAccessType.FIELD)
public class Goal {
	private String slug;
	private String title;
	private String goal_type;
	private String graph_url;
	private Long panic;
	private Long losedate;
	private Long goaldate;
	private Long rate;
	private Long updated_at;
	
	public Goal() {
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGoal_type() {
		return goal_type;
	}

	public void setGoal_type(String goal_type) {
		this.goal_type = goal_type;
	}

	public String getGraph_url() {
		return graph_url;
	}

	public void setGraph_url(String graph_url) {
		this.graph_url = graph_url;
	}

	public Long getPanic() {
		return panic;
	}

	public void setPanic(Long panic) {
		this.panic = panic;
	}

	public Long getLosedate() {
		return losedate;
	}

	public void setLosedate(Long losedate) {
		this.losedate = losedate;
	}

	public Long getGoaldate() {
		return goaldate;
	}

	public void setGoaldate(Long goaldate) {
		this.goaldate = goaldate;
	}

	public Long getRate() {
		return rate;
	}

	public void setRate(Long rate) {
		this.rate = rate;
	}

	public Long getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Long updated_at) {
		this.updated_at = updated_at;
	}

	
}
