package Main;

public class ObjectOutstanding {
	
	
	private int id;
	private String course;
	private String unit;
	
	public ObjectOutstanding(int id, String course , String unit){
		
		this.id = id;
		this.course = course ;
		this.unit = unit;	
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
    
   
	
	
	
	
	
	@Override
	public String toString() {
		return getCourse()+ "      " + getUnit();
		
	}
	
	
	
	
	
	
	
	
	

}
