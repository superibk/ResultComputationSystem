package Main;

public class AddCourseObject {
	
	
	private int id;
	private String course;
	private String unit;
	private String title;
	
	public AddCourseObject(int id, String course , String unit, String title){
		
		this.id = id;
		this.course = course ;
		this.unit = unit;
		this.title = title;	
	}
	
	public int getId(){
		
		return this.id;
	}
  
	  public String  getCourse(){
		
		return this.course;
	}
	  
    public String getUnit(){
			
			return this.unit;
		}
    
    public String getCourseTitle(){
		
		return this.title;
	}
	
	
	@Override
	public String toString() {
		String formatedString = String.format("%1$-10s %2$-10s %3$-20s",getCourse(),getCourseTitle(), getUnit() );
		return formatedString;
		
	}
	

}
