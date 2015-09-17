package Main;

public class SummaryObject {

	private String passFail;
	private int totalCredit;
	private int totalCreditPrevious;
	private int totalCreditCurrent;
	private String cgpa;
	private String cgpaPrevious;
	private String cgpaCurrent;
	private int tup;
	private int tupPrevious;
	private int tupCurrent;
	private int tlu;
	private int tluPrevious;
	private int tluCurrent;
	private boolean noResult;

	public SummaryObject(String passFail, int totalCredit, String cgpa) {

		this.passFail = passFail;
		this.totalCredit = totalCredit;
		this.cgpa = cgpa;
	}

	public String getTotalCredit() {
		
		return Integer.toString(this.totalCredit);
	}
	
   public String getTotalCreditPrevious() {
		
		return Integer.toString(this.totalCreditPrevious);
	}
   
   public String getTotalCreditCurrent() {
		
		return Integer.toString(this.totalCreditCurrent);
	}

	public String getPassFail() {

		return this.passFail;
	}

	public String getCgpa() {
		
		return  this.cgpa;
	}
	
	public String getCgpaPrevious() {
		
		return  this.cgpaPrevious;
	}

  public String getCgpaCurrent() {
	
	return  this.cgpaCurrent;
   }
	
	public void setNoResult(boolean result){
		this.noResult = result;
		
	}
	
	public boolean getNoResult(){
		return this.noResult;
	}
	
	public void setTotalUnit(int unit){
		this.tlu = unit;
		
	}
	
	public void setTotalUnitPrevious(int unit){
		this.tluPrevious = unit;
		
	}
	
	public void setTotalUnitCurrent(int unit){
		this.tluCurrent = unit;
		
	}
	
	public String getTLU(){
		return Integer.toString(this.tlu);
	}
	
	public String getTLUPrevious(){
		return Integer.toString(this.tluPrevious);
	}
	
	public String getTLUCurrent(){
		return Integer.toString(this.tluCurrent);
	}
	
	
	public String getTUP(){
		return Integer.toString(this.tup);
	}
	
	public String getTUPPrevious(){
		return Integer.toString(this.tupPrevious);
	}
	
	public String getTUPCurrent(){
		return Integer.toString(this.tupCurrent);
	}
	
	public void setCgpaPrevious(String cgpa){
		this.cgpaPrevious = cgpa;
		
	}
	
	public void setCgpaCurrent(String cgpa){
		this.cgpaCurrent = cgpa;
		
	}
	
	public void setTotalCreditPrevious(int totalCreditPrevious){
		this.totalCreditPrevious = totalCreditPrevious;
		
	}
	
	public void setTotalCreditCurrent(int totalCreditCurrent){
		this.totalCreditCurrent = totalCreditCurrent;
		
	}
	
	public void setTUP(int tup){
		this.tup = tup;
		
	}
	
	public void setTUPPrevious(int tupPrevious){
		this.tupPrevious = tupPrevious;
		
	}
	
	public void setTUPCurrent(int tupCurrent){
		this.tupCurrent = tupCurrent;
		
	}

	
	

}
