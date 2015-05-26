package recommend;

import java.util.Comparator;

public class RecommendInfoComparator implements Comparator<RecomendInfo>
{

	@Override
	public int compare(RecomendInfo o1, RecomendInfo o2)
	{
		int c = o2.compareTo(o1);
		return c==0?o2.getKeyWords().size() - o1.getKeyWords().size():c;
	}

}
