package com.thgdsa.springboot.mywebapp.todo;

import java.time.LocalDate;
import java.util.List;

public class TodoService {

	private static List<Todo> todos;
	static {
		todos.add(new Todo(1, "Tita", "Learn Spring Boot",
				LocalDate.now().plusYears(1),false));
		todos.add(new Todo(2, "Tita", "Learn JUnit5",
				LocalDate.now().plusYears(1),false));
		todos.add(new Todo(3, "Tita", "Learn English",
				LocalDate.now().plusYears(1),false));
	}
}
