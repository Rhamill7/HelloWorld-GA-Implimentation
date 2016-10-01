
public class Main {

	public static void main(String[] args) {

		/* Modify Variables to adjust results */
		int populationSize = 100;
		int numberOfGenerations = 1;
		float crossoverRatio = 0.3f; // probability of crossover  0.1f = 10%
		float mutationRatio = 0.2f; // probability of mutation for any gene

		Population p = new Population(); // create initial population
		p.createPopulation(populationSize, crossoverRatio, mutationRatio);
		Chromosome bestGene = p.getPopulation()[0];
		while (bestGene.getFitness() != 0) { // repeat until stopping condition
			System.out.println("Gene Number: " +numberOfGenerations + " Best Gene: "+
								bestGene.getGene() + " Current Fitness " + bestGene.getFitness());
			//p.evolve(); // evolve ( Selection, Crossover, Mutation, Termination	// )
			//p.random();
			bestGene = p.hillClimber();
			//bestGene = p.getPopulation()[0]; // get first entry of population as
			numberOfGenerations++;
		}
		
		
		
		
		System.out.println("The target " + bestGene.getGene() + " was reached! Huzzah!");
	}

}
