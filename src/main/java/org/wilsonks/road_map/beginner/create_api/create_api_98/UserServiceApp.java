package org.wilsonks.road_map.beginner.create_api.create_api_98;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.wilsonks.road_map.beginner.create_api.create_api_98.model.Employee;

import java.util.*;
import java.util.stream.Collectors;


@SpringBootApplication
public class UserServiceApp {


    public static ArrayList<Employee> employees = new ArrayList<>();





    public static void main(String[] args) {
        SpringApplication.run(UserServiceApp.class, args);
        System.out.println("Main Starter and Spring Application Run took over");


    }

    @Bean
    public CommandLineRunner init1() {
        return args -> {
            System.out.println("Application Command Line Runner Started");
            employees.add(new Employee(1, "Adam", "IT", 40000 ));
            employees.add(new Employee(2, "John", "IT", 50000 ));
            employees.add(new Employee(3, "Ronaldo", "HR", 80000 ));
            employees.add(new Employee(4, "Messy", "EXEC", 90000 ));
            employees.add(new Employee(5, "Henry", "HR", 60000 ));

            System.out.println("Data is initialized");

            Map<String, List<Employee>> collect = employees.stream()
                    .collect(Collectors.groupingBy(
                            Employee::department
                    ));

            Optional<Double> first = employees.stream()
                    .map(Employee::salary)
                    .sorted(Comparator.reverseOrder())
                    .skip(1)
                    .findFirst();

            first.ifPresent(element -> System.out.println(element));
        };
    }

}
