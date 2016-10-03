
public class Main {

	public static void main(String[] args) {

		/* Modify Variables to adjust results */
		int populationSize = 100; //100
		int numberOfGenerations = 1;
		float crossoverRatio = 0.9f;// 0.2 // 0.1f = 10%
		float mutationRatio = 0.15f; //0.9// probability of mutation for any gene

		Population p = new Population(); // create initial population
		p.createPopulation(populationSize, crossoverRatio, mutationRatio);
		Chromosome bestGene = p.getPopulation()[0];
		while (bestGene.getFitness() != 0 && numberOfGenerations <10000) { // repeat until stopping condition
			
			System.out.println("Gen Number: " + numberOfGenerations + " Best Gene: " + bestGene.getGene()
					+ " Current Fitness " + bestGene.getFitness());
			p.evolve(); // evolve( Selection, Crossover, Mutation, Termination)
		
			// p.random();
			// bestGene = p.hillClimber();
			 bestGene = p.getPopulation()[0];
			numberOfGenerations++;

		}

		System.out.println("The best gene " + bestGene.getGene() + " was reached! Huzzah!");
	}

}
