package build;

public class ReBuilder implements Builder
{

	public static void reBuild()
	{
		Unbuilder ub = new Unbuilder();
		ub.unBuild();
		
		InitBuilder ib = new InitBuilder();
		ib.build();
	}

	@Override
	public void doBuild() {
		this.reBuild();
		
	}
	


}
