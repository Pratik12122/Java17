package concurrency;

interface Greeting {
	void greet();
}

public class AnonymousVsLamba {

	static String name = "Pratik";

	public static void main(String[] args) {
		new AnonymousVsLamba().foo();
	}

	public void foo() {
		for (int i = 0; i < 3; i++) {
			Greeting greetingAnonymous = new Greeting() {
				@Override
				public void greet() {
					System.out.println("Hello By Anonymous " + this);
				}
			};

			greetingAnonymous.greet();

			Greeting greetingLambda = () -> {
				System.out.println("Hello From Lambda " + this);
			};

			greetingLambda.greet();

			System.out.println("greetingAnonymous hashcode " + greetingAnonymous.hashCode());
			System.out.println("greetingLambda hashcode " + greetingLambda.hashCode());
			System.out.println();

		}
	}

}
