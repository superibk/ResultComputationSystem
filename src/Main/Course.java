package Main;

public class Course {
	
	
	private int id;
	private String course;
	private String unit;
	private String grade;
	
	public Course(int id, String course , String unit, String grade){
		
		this.id = id;
		this.course = course ;
		this.unit = unit;
		this.grade = grade;	
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
    
    public String getGrade(){
		
		return this.grade;
	}
	
	
	
	
	
	@Override
	public String toString() {
		return getCourse()+ "                       " + getGrade() + "                       " + getUnit();
		
	}
	
	
	
	
	
	
	
	
	

}
