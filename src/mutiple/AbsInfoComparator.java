package mutiple;

import java.util.Comparator;

public class AbsInfoComparator implements Comparator<AbsInfo>
{

	@Override
	public int compare(AbsInfo o1, AbsInfo o2)
	{
		return o2.compareTo(o1);
	}
	

}
