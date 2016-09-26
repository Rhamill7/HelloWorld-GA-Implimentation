import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Populate {
	
	public Populate(){
	}
	
	/* Generate population*/
	public List<String> createPopulation(int size){
		List<String> P = new ArrayList<String>();
		
		Random r = new Random();
		
		for(int j = 0; j < size; j++ ){
			char[] entry = new char[12];
			for(int i = 0; i < entry.length; i++){
				entry[i] = (char)(r.nextInt(126 - 32 + 1) + 32); //(max - min + 1) + min
			}
			String change = entry.toString();
			P.add(change);
			//System.out.println(P.get(j));
			System.out.println(entry);
		}
		return P;
		
	}

	/*perform Parents Selection*/
	public List<String> parentSelect(List<String> P){
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
						
						}
					}
				}
		return parents;
		
	}
	
	/*mutate a string*/
	public List<String> mutate(){
		return null;
		}
	
	/*Evaluate Current Population*/
	public HashMap<String,Integer> evaluate(String target, List<String> P){
		HashMap<String, Integer> fitness = new HashMap<String, Integer>();
		int fitnessValue = Integer.MAX_VALUE;
		String fittestGene = "";
		char [] charTarget = target.toCharArray();
	//	System.out.println(P.size());
		for (int k = 0; k<P.size(); k++){
			String gene = P.get(k);
		//	System.out.println(P.get(k).toCharArray());
		//	System.out.println(P.get(k).toString());
			fitnessValue = calculateFitness(gene, charTarget);
		//System.out.println(fitnessValue + " " + gene);
			fitness.put(gene, fitnessValue);
			//System.out.println(fitness.get(key));
		}
		return fitness;
		
		
	}
	
	private int calculateFitness(String gene, char[] target) {
		int fitness = 0;
		char[] geneArray  = gene.toCharArray();
		for (int i = 0; i < geneArray.length; i++) {
			fitness += Math.abs(((int) geneArray[i]) - ((int) target[i]));
		}
		return fitness;
	}
}
