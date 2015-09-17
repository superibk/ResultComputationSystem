package Main;

public class DepartmentObject {
	
	
	private int id;
	private String department;

	
	
	public DepartmentObject(int id, String department){
		
		this.id = id;
		this.department= department;
		
	}
	
	public int getId(){
		
		return this.id;
	}
  
	  public String  getDepartment(){
		
		return this.department;
	}
	  
 
    
  
	
	
	@Override
	public String toString() {
		
		return this.department.toUpperCase();
		
	}
	

}
