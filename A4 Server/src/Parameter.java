
public class Parameter
{
	private String name;
	private String value;
	
	public Parameter(String name, String value)
	{
		this.name = name;
		this.value = value;
	}
	
	public String toString()
	{
		return this.name + " = " + this.value;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getValue()
	{
		return value;
	}
}
