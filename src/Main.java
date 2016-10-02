import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		int size = 2048;
		Populate pop = new Populate();
		Evolution evolve = new Evolution();
		String target = "Hello World!";
	//	double fitnessEval = 0.0;
		String phrase = "";
		List<String> P = pop.createPopulation(size);
		
		
		while (phrase != target) {
			HashMap <String, Integer> evaluated = pop.evaluate(target, P);
			
			List <String> pDash = evolve.createNewPopulation(P); 
			P = pDash;

			System.exit(0);
		}
			
	}
	

}
