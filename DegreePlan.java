package beans;

public class DegreePlan {
	private String degreeCode;
	private String description;
	private String hours;
	private String type;
	private String courses;
	private String degreeName;
	private String forecast;
	private String gradSchool;
	public String getGradSchool() {
		return gradSchool;
	}
	public void setGradSchool(String gradSchool) {
		this.gradSchool = gradSchool;
	}
	public String getDegreeCode() {
		return degreeCode;
	}
	public void setDegreeCode(String degreeCode) {
		this.degreeCode = degreeCode;
	}
	public String getDescription() {
		return description;
	}
	public String getDegreeName() {
		return degreeName;
	}
	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}
	public String getForecast() {
		return forecast;
	}
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCourses() {
		return courses;
	}
	public void setCourses(String courses) {
		this.courses = courses;
	}
}
