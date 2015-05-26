package expertNet;

import java.util.List;
import java.util.Set;

public interface Relation
{
	public String getRelation();
	
	public void updateRelation(RelationInfo info);
	
	public List<Set<String>> getRelationExpert(String word);

}
