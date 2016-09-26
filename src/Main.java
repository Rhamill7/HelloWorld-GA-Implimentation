import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		int size = 100;
		Populate pop = new Populate();
		String target = "Hello World!";
	//	double fitnessEval = 0.0;
		String phrase = "";
		List<String> P = pop.createPopulation(size);
		
		while (phrase != target) {
			
			 pop.evaluate(target, P);
		//	System.out.println(fittest);
			System.exit(0);
		}
			
	}
	

}
