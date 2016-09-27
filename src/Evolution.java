import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Evolution {

	/*Create new generation of population*/
	public List<String> createNewPopulation(List<String> P) {
		List<String> pDash = new ArrayList<String>(P.subList(0,P.size()/2)); 
		while (pDash.size() < P.size() ){
			List<String> parents= parentSelect(P);
			List<String> children = crossover(parents);
		}
		
		return pDash;
	}
		

	/* perform Parents Selection */
	public List<String> parentSelect(List<String> P) {
		// Randomly select two parents via tournament selection.
		Random rand = new Random();
		int tournamentSize = 4;
		List<String> parents = new ArrayList<String>();
		for (int i = 0; i < 2; i++) {
			parents.add(P.get(rand.nextInt(P.size())));
			for (int j = 0; j < tournamentSize; j++) {
				int idx = rand.nextInt(P.size());
				if (P.get(idx).compareTo(parents.get(i)) < 0) {
					parents.remove(i);
					parents.add(P.get(idx));
				}
			}
		}
		return parents;
	}
	
	public List<String> crossover(List<String> parents){
		List<String> children = new ArrayList<String>();
		
		
		return children;
		
	}

	
	/*mutate a string*/
	public List<String> mutate() {
		List<String> mutation = new ArrayList<String>();
		
		return mutation;
	}
	
//	public

//	while(
//
//	nextPopulation too small)
//	{
//	    Members tournament = randomly choose x members from currentPopulation
//
//	    if(crossover){
//	        Member parents = select best two members from tournament
//	        Member children = crossover(parents)
//	        nextPopulation.add(children);
//	


}
