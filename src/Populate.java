import java.util.ArrayList;
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

	/*perform crossover*/
	public List<String> crossover(List<String> P){
	
		return crossP;
		
	}
	
	/*mutate a string*/
	public List<String> mutate(){
		return null;
		}
	
	/*Evaluate Current Population*/
	public int evaluate(String target, List<String> P){
		int fittest = Integer.MAX_VALUE;
		String fittestGene = "";
		char [] charTarget = target.toCharArray();
	//	System.out.println(P.size());
		for (int k = 0; k<P.size(); k++){
			String gene = P.get(k);
		//	System.out.println(P.get(k).toCharArray());
		//	System.out.println(P.get(k).toString());
			int result = calculateFitness(gene, charTarget);
			if (result < fittest){
				fittest = result;
				fittestGene = gene;
			}
			if (fittest == 0) {
				break;
			}
		//	System.out.println(fittest);
			
		}
		if (fittest !=0){
			List<String> crossP = this.crossover(P);
		}
		return fittest;
		
		
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
