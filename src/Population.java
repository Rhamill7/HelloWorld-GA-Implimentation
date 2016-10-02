import java.util.Arrays;
import java.util.Random;

public class Population {

	public Chromosome[] p;
	public int tournamentSize = 3;
	public Random rand = new Random();
	float mutation;
	float crossover;

	public Population() {
	}

	public void createPopulation(int populationSize, float crossoverRatio, float mutationRatio) {

		this.crossover = crossoverRatio;
		this.mutation = mutationRatio;
		this.p = new Chromosome[populationSize];

		for (int i = 0; i < populationSize; i++) {
			this.p[i] = Chromosome.generateRandom();
		}
		/* Sort in order of fitness */
		Arrays.sort(p); // comment for hill climber
	}

	/* create copy of current population and return it */
	public Chromosome[] getPopulation() {
		Chromosome[] currentPopulation = new Chromosome[p.length];
		System.arraycopy(p, 0, currentPopulation, 0, p.length);
		return currentPopulation;
	}

	public void evolve() {
		Chromosome[] pDash = new Chromosome[p.length];

		int index = 0;
		// Perform crossover and mutation if size isnt equal
		while (index < pDash.length) {
			// Check to see if we should perform a crossover.
			if (rand.nextFloat() <= crossover) {

				Chromosome[] parents = selectParents();
				Chromosome[] children = parents[0].crossover(parents[1]);

				pDash[index++] = children[0];
				// If space in array add 2nd child
				if (index < pDash.length) {
					pDash[index] = children[1];
				}
			}
			else if (rand.nextFloat() <= mutation) {
					pDash[index] = p[index].mutate();
				} else {
					pDash[index] = p[index];
				}
			
			index++;
		}
		Arrays.sort(pDash); // sort based on fitness
		p = pDash; // P<-P'
	}

	/* Selection done here TOURNAMENT STYLE! FIGHTTT!! */
	private Chromosome[] selectParents() {
		Chromosome[] parents = new Chromosome[2];
		// Randomly select two parents via tournament selection.
		for (int i = 0; i < 2; i++) {
			parents[i] = p[rand.nextInt(p.length)]; // get random parent
			for (int j = 0; j < tournamentSize; j++) { // compare with others
				int index = rand.nextInt(p.length);
				if (p[index].compareTo(parents[i]) < 0) {
					parents[i] = p[index];
				}
			}
		}
		return parents;
	}

	/* Implements HillClimber */
	public Chromosome hillClimber() {
		Chromosome best = null;

		for (int j = 0; j < 10; j++) {
			int index = rand.nextInt(p.length);
			best = p[index];

			for (int i = index; i < index + 10; i++) {
				if (p[index].getFitness() > p[i].getFitness()) {
					best = p[i];
				}
				if (i == 99) {
					break;
				}
			}

			for (int i = index; i > index - 10; i--) {

				if (p[index].getFitness() > p[i].getFitness()) {
					best = p[i];
				}
				if (i == 0) {
					break;
				}
			}
		}

		return best;

	}

	/* implements random algorithm */
	public void random() {
		Chromosome[] randomPopulation = new Chromosome[p.length];
		for (int i = 0; i < p.length; i++) {
			randomPopulation[i] = Chromosome.generateRandom();
		}
		/* Sort in order of fitness */
		Arrays.sort(randomPopulation);
		p = randomPopulation;
	}

}
