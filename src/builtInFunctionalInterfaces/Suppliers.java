package builtInFunctionalInterfaces;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class Suppliers {

	public static void main(String[] args) {
		supplierstest();
	}

	
	
	
	static void supplierstest() {

		Supplier<String> orderIdSupplier = () -> "Order_" + new Random().nextInt(Integer.MAX_VALUE);

		IntStream.range(0, 10).forEach(i -> System.out.println(orderIdSupplier.get()));

	}
}




