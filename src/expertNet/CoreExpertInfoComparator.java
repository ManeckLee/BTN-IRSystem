package expertNet;

import java.util.Comparator;

public class CoreExpertInfoComparator implements Comparator<CoreExpertInfo>
{

	@Override
	public int compare( CoreExpertInfo o1,  CoreExpertInfo o2)
	{
		return o1.compareTo(o2);
	}
	

}
