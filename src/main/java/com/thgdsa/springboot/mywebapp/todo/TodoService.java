package com.thgdsa.springboot.mywebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

	private static List<Todo> todos = new ArrayList<>();
	static {
		todos.add(new Todo(1, "Tita", "Learn Spring Boot",
				LocalDate.now().plusYears(1),false));
		todos.add(new Todo(2, "Tita", "Learn JUnit5",
				LocalDate.now().plusYears(2),false));
		todos.add(new Todo(3, "Tita", "Learn English",
				LocalDate.now().plusYears(3),false));
	}
	
	public List<Todo> findByUsername(String username){
		
		return todos;
	}
}
