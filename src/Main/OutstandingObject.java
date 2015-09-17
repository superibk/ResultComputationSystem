package Main;

public class OutstandingObject {
	
	private  String outstanding;
	private int totalOustandingUnit;
	private int current;
	private int previous;
	
	public OutstandingObject( String outstanding, int unitSum, int current, int previous){
		this.outstanding = outstanding;
		this.totalOustandingUnit = unitSum;
		this.current = current;
		this.previous = previous;
				
	}
	
	public String getOutstanding(){
		return this.outstanding;
	}
	
	public int getUnitTotal(){
		 return this.totalOustandingUnit;
	}
	
	public int getUnitCurrent(){
		return this.current;
	}
	
	public int getUnitPrevious(){
		return  this.previous;
	}
	

}
