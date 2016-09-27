import java.util.Random;

public class Chromosome implements Comparable<Chromosome> {

	/* What we are aiming for */
	public static char[] target = "Hello world!".toCharArray();
	public static Random rand = new Random();
	private String gene;
	private int fitness;

	public Chromosome(String gene) {
		this.gene = gene;
		this.fitness = calculateFitness(gene);
	}

	public String getGene() {
		return gene;
	}

	public int getFitness() {
		return fitness;
	}
	
	static Chromosome generateRandom() {
		char[] gene = new char[target.length];
		for (int i = 0; i < gene.length; i++) {
			gene[i] = (char) (rand.nextInt(90) + 32);
		}
		return new Chromosome(String.valueOf(gene));
	}


	/* calculate fitness using fitness function*/
	private static int calculateFitness(String gene) {
		int fitness = 0;
		char[] geneChars = gene.toCharArray();
		for (int i = 0; i < geneChars.length; i++) {
			//work out how close each letter is by subtracting their ASCII values 
			//add the results together, if it is the same this will equal 0.
			fitness += Math.abs(((int) geneChars[i]) - ((int) target[i]));
		}
		return fitness;
	}

	/* mutate */
	public Chromosome mutate() {
		char[] geneChar = gene.toCharArray();
		int ranChar = rand.nextInt(geneChar.length);
		int delta = (rand.nextInt() % 90) + 32;
		geneChar[ranChar] = (char) ((geneChar[ranChar] + delta) % 122);

		return new Chromosome(String.valueOf(geneChar));
	}

	public Chromosome[] mate(Chromosome mate) {
		// Convert the genes to arrays to make thing easier.
		char[] arr1 = gene.toCharArray();
		char[] arr2 = mate.gene.toCharArray();

		// Select a random pivot point for the mating
		int pivot = rand.nextInt(arr1.length);

		// Provide a container for the child gene data
		char[] child1 = new char[gene.length()];
		char[] child2 = new char[gene.length()];

		// Copy the data from each gene to the first child.
		System.arraycopy(arr1, 0, child1, 0, pivot);
		System.arraycopy(arr2, pivot, child1, pivot, (child1.length - pivot));

		// Repeat for the second child, but in reverse order.
		System.arraycopy(arr2, 0, child2, 0, pivot);
		System.arraycopy(arr1, pivot, child2, pivot, (child2.length - pivot));

		return new Chromosome[] { new Chromosome(String.valueOf(child1)), new Chromosome(String.valueOf(child2)) };
	}

	
	@Override
	public int compareTo(Chromosome gene) {
		if (fitness < gene.fitness) {
			return -1;
		} else if (fitness > gene.fitness) {
			return 1;
		}
		return 0;
	}

	

	
}
