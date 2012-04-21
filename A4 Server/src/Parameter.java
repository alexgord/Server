/*********************************Parameter CLASS INFO*****************************************

This class is responsible for creating a parameter, converting a parameter to a string, to 
a value, and getting the parameter name.  

*********************************************************************************************/

public class Parameter {
	private String name;
	private String value;
	
	public Parameter(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public String toString() {
		return this.name + " = " + this.value;
	}
	
	public String getName()	{
		return name;
	}
	
	public String getValue() {
		return value;
	}
}
