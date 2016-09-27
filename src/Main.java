
public class Main {

	public static void main(String[] args) {

		int populationSize = 1000;
		int numberOfGenerations = 1;
		float crossoverRatio = 0.8f; // probability of crossover
		float elitismRatio = 0.8f; // population retained without change eg.
									// 0.1f = 10%
		float mutationRatio = 0.01f; // probability of mutation for any gene

		Population p = new Population(); // create initial population
		p.createPopulation(populationSize, crossoverRatio, elitismRatio, mutationRatio);
		Chromosome bestGene = p.getPopulation()[0];
		while (bestGene.getFitness() != 0) { // repeat until stopping condition
			System.out.println(numberOfGenerations + " " + bestGene.getGene() + " " + bestGene.getFitness());
			p.evolve(); // evolve ( Selection, Crossover, Mutation, Termination
						// )
			bestGene = p.getPopulation()[0]; // get first entry of population as
												// it is sorted by fitness
			numberOfGenerations++;
		}
		System.out.println("The target " + bestGene.getGene() + " was reached! Huzzah!");
	}

}
