package builtInFunctionalInterfaces;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
	public static void scary(String animal) {
		Predicate<String> dino = s -> "dino".equals(animal);
		Predicate<String> dragon = s -> "dragon".equals(animal);
		var combined = dino.or(dragon);
		System.out.println(combined.test(animal));
	}

	public static void main(String[] args) {
		
		List<Employee> employees = Arrays.asList(
			    new Employee(1, "Alice", "HR", 60000.0),
			    new Employee(2, "Bob", "IT", 80000.0),
			    new Employee(3, "Charlie", "HR", 70000.0),
			    new Employee(4, "David", "IT", 75000.0),
			    new Employee(5, "Eve", "Finance", 90000.0),
			    new Employee(6, "Frank", "Finance", 85000.0)
			);

		
		employees.stream().collect(Collectors.toMap(Employee::getDepartment, Employee::getSalary, 
				Double::max
				)).forEach((k,v) -> System.out.println(k+v));;
	}

}

class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }
}
