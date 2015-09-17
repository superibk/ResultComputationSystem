package Main;

public class Students {
	private String firstName,lastName, middleName, matric, birth,
				religion, sex, maritalStatus, phoneNumber,department, nextOfKin, nextOfKinNumber, 
				homeAddress,	guidianName, localGov, state, level, nationality  ;
	private long id;

	
	
	public Students(
			long id,
			String firstName, 
			String lastName,
			String middleName,
			String matric, 
			String birth,
			String religion,
			String sex,
			String maritalStatus,
			String phoneNumber,
			String department,
			String nextOfKin,
			String nextOfKinNumber,
			String homeAddress,
			String guiadianName,
			String localGov,
			String state,
			String level,
			String nationality
			){
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName; 
		this.middleName = middleName;
		this.matric = matric;
		this.birth = birth;
		this.religion = religion;
		this.sex = sex;
		this.maritalStatus = maritalStatus;
		this.phoneNumber = phoneNumber;
		this.department = department;
		this.nextOfKin = nextOfKin;
		this.nextOfKinNumber = nextOfKinNumber;
		this.homeAddress = homeAddress;
		this.localGov = localGov;
		this.state = state;
		this.level = level;
		this.nationality = nationality;
	}
	
	
	public long getId(){
		return this.id;		
	}
	
	public String getFirstName(){
		return this.firstName;
	}
		
	public String getLastName(){
		return this.lastName;
	}
	
	public String getMiddleName(){
		return this.middleName;
			
	}
	public String getMatric(){
		return this.matric;
	}
	
	public String getBirth(){
		return this.birth;
	}
	
	public String getReligion(){
		return this.religion;
	}
	
	public String getSex(){
		return this.sex;
	}
	
	public String getMaritalStatus(){
		return this.maritalStatus;
	}
	
	public String getPhoneNumber(){
		return this.phoneNumber;
	}
	
	public String getNextOfKin(){
		return this.nextOfKin;
	}
	
	public String getNextOfKinNumber(){
		return this.nextOfKinNumber;
	}
	
	public String gethomeAdress(){
		return this.homeAddress;
	}
	
	public String getLocalGov(){
		return this.localGov;
	}
	
	public String getState(){
		return this.state;
	}
	
	public String getLevel(){
		return this.level;
	}
	
	public String getNationality(){
		return this.nationality;
	}
	
	public String getDepartment(){
		return this.department;
	}
	@Override
	public String toString() {
		return this.firstName +" "+this.lastName +" (" + this.matric + " )";
	}
	
	public String getNextOfKinAddress(){
		return this.guidianName;
	}
}
