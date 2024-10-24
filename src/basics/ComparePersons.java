package basics;

import static basics.PrintUtils.printLine;
import static java.util.Comparator.comparing;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ComparePersons {
	public static void main(String[] args) {
		final List<Person> people = Arrays.asList(new Person("John", 20), new Person("Sara", 21),
				new Person("Jane", 21), new Person("Greg", 35), new Person("Alia", 35));

		printLine();

		people.stream().sorted(Person::ageDifference).toList().forEach(System.out::println);

		Comparator<Person> compageAgeAsc = (p1, p2) -> p1.ageDifference(p2);

		Comparator<Person> compageDesc = compageAgeAsc.reversed();

		printLine();
		people.stream().sorted(compageAgeAsc).toList().forEach(System.out::println);

		printLine();
		people.stream().sorted(compageDesc).toList().forEach(System.out::println);

		printLine("Find youngest Person " + people.stream().min(compageAgeAsc).get().toString());

		printLine("Find oldest Person " + people.stream().min(compageDesc).get().toString());

		printLine("Multiple and Fluent Comparisons");

		people.stream().sorted(comparing(Person::getAge)).forEach(System.out::println);

		printLine("Comparing age then name");

		people.stream().sorted(comparing(Person::getAge).thenComparing(Person::getName)).forEach(System.out::println);
	}
}
