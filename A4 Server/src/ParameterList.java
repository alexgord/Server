import java.util.ArrayList;


public class ParameterList {
	private ArrayList<Parameter> parameters;

	public ParameterList() {
		parameters = new ArrayList<Parameter>();
	}

	public void GenerateParameters(ArrayList<String> input) {
		for (int i = 0; i < input.size(); i++) {
			parameters.add(new Parameter(input.get(0), input.get(1)));
			input.remove(0);
			input.remove(0);
		}
	}

	public int getSize() {
		return parameters.size();
	}

	public String toString() {
		String r = "";

		for (int i = 0; i < getSize(); i++) {
			r += parameters.get(i).toString() + "<br />";
		}
		return r;
	}

	public String getValueFromParameterName(String s) {
		String r = "";

		for (int i = 0; i < getSize(); i++) {
			String currName = parameters.get(i).getName();
			if (currName.equals(s))	{
				r = parameters.get(i).getValue();
			}
		}

		return r;
	}

	public boolean ParameterExists(String name)	{
		boolean r = false;
		for (int i = 0; i < getSize(); i++)	{
			String currName = parameters.get(i).getName();
			if (currName.equals(name)) {
				r = true;
				break;
			}
		}
		return r;
	}
}
