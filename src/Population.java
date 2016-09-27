import java.util.Arrays;
import java.util.Random;

public class Population {

	public Chromosome[] p;
	public int tournamentSize = 3;
	public Random rand = new Random();
	float elitism;
	float mutation;
	float crossover;

	public Population() {
	}

	public void createPopulation(int populationSize, float crossoverRatio, float elitismRatio, float mutationRatio) {

		this.crossover = crossoverRatio;
		this.elitism = elitismRatio;
		this.mutation = mutationRatio;
		this.p = new Chromosome[populationSize]; // set population to required
													// size
		for (int i = 0; i < populationSize; i++) {
			this.p[i] = Chromosome.generateRandom();
		}
		// Sort in order of fitness
		Arrays.sort(p);
	}

	/* create copy of current population and return it */
	public Chromosome[] getPopulation() {
		Chromosome[] currentPopulation = new Chromosome[p.length];
		System.arraycopy(p, 0, currentPopulation, 0, p.length);
		return currentPopulation;
	}

	public void evolve() {
		Chromosome[] pDash = new Chromosome[p.length];
		// Determine how much of the population you would like to keep
		int idx = Math.round(p.length * elitism);
		System.arraycopy(p, 0, pDash, 0, idx);
		// Perform crossover and mutation if size isnt equal
		while (idx < pDash.length) {
			// Check to see if we should perform a crossover.
			if (rand.nextFloat() <= crossover) {

				// Select the parents and mate to get their children
				Chromosome[] parents = selectParents();
				Chromosome[] children = parents[0].mate(parents[1]);
				//Add first child
				pDash[idx++] = children[0];

				// Add for the second child, if there is room.
				if (idx < pDash.length) {
					pDash[idx] = children[1];
				}
			} else { 
				// Determine if mutation should occur.
				if (rand.nextFloat() <= mutation) {
					pDash[idx] = p[idx].mutate();
				} else {
					pDash[idx] = p[idx];
				}
			}
			idx++;
		}
		Arrays.sort(pDash); //sort based on fitness 
		p = pDash; // P<-P'
	}

	private Chromosome[] selectParents() {
		Chromosome[] parents = new Chromosome[2];
		// Randomly select two parents via tournament selection.
		for (int i = 0; i < 2; i++) {
			parents[i] = p[rand.nextInt(p.length)]; //get random possible parent
			for (int j = 0; j < tournamentSize; j++) { //compare with other 3  possibles
				int idx = rand.nextInt(p.length);
				if (p[idx].compareTo(parents[i]) < 0) {
					parents[i] = p[idx];
				}
			}
		}

		return parents;
	}

	// /* Generate population */
	// public List<String> createPopulation(int size) {
	// List<String> P = new ArrayList<String>();
	// Random r = new Random();
	// for (int j = 0; j < size; j++) {
	// char[] entry = new char[12];
	// for (int i = 0; i < entry.length; i++) {
	// entry[i] = (char) (r.nextInt(126 - 32 + 1) + 32); // (max - min + 1) +
	// min
	// }
	// String change = entry.toString();
	// P.add(change);
	// // System.out.println(P.get(j));
	// System.out.println(entry);
	// }
	// return P;
	// }
	//
	// /* Evaluate Current Population */
	// public HashMap<String, Integer> evaluate(String target, List<String> P) {
	// HashMap<String, Integer> fitness = new HashMap<String, Integer>();
	// int fitnessValue = Integer.MAX_VALUE;
	// String fittestGene = "";
	// char[] charTarget = target.toCharArray();
	// // System.out.println(P.size());
	// for (int k = 0; k < P.size(); k++) {
	// String gene = P.get(k);
	// // System.out.println(P.get(k).toCharArray());
	// // System.out.println(P.get(k).toString());
	// fitnessValue = calculateFitness(gene, charTarget);
	// // System.out.println(fitnessValue + " " + gene);
	// fitness.put(gene, fitnessValue);
	// // System.out.println(fitness.get(key));
	// }
	// return fitness;
	//
	// }
	//
	// /* Calculate the fitness of an entry in P */
	// private int calculateFitness(String gene, char[] target) {
	// int fitness = 0;
	// char[] geneArray = gene.toCharArray();
	// for (int i = 0; i < geneArray.length; i++) {
	// fitness += Math.abs(((int) geneArray[i]) - ((int) target[i]));
	// }
	// return fitness;
	// }

}
