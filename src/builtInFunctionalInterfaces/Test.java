package builtInFunctionalInterfaces;

import java.util.function.Predicate;

public class Test {
	public static void scary(String animal) {
		Predicate<String> dino = s -> "dino".equals(animal);
		Predicate<String> dragon = s -> "dragon".equals(animal);
		var combined = dino.or(dragon);
		System.out.println(combined.test(animal));
	}

	public static void main(String[] args) {
		scary("dino");
		scary("dragon");
		scary("unicorn");
	}
}
