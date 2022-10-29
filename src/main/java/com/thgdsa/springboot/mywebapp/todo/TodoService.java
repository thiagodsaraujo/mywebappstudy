package com.thgdsa.springboot.mywebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import jakarta.validation.Valid;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

	private static List<Todo> todos = new ArrayList<>();

	private static int todosCount = 0;

	static {
		todos.add(new Todo(++todosCount, "Tita", "Learn Spring Boot",
				LocalDate.now().plusYears(1),false));
		todos.add(new Todo(++todosCount, "Tita", "Learn JUnit5",
				LocalDate.now().plusYears(2),false));
		todos.add(new Todo(++todosCount, "Tita", "Learn English",
				LocalDate.now().plusYears(3),false));
	}
	
	public List<Todo> findByUsername(String username){
		Predicate<Todo> predicate =
				todo -> todo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList();
	}

	public void addTodo(String username, String description, LocalDate targetDate, boolean done){
		Todo todo = new Todo(++todosCount, username,description,targetDate,done);
		todos.add(todo);
	}
	
	public void deleteById(int id){
		// todo.getId() == id
		// todo -> todo.getId() == id
		// Se tiver 10 itens na lista, usará o lambda e verificará se o id do objeto bate com o da entrada e exclui
		Predicate<Todo> predicate = todo -> todo.getId() == id;
		todos.removeIf(predicate);
	}

	public Todo findById(int id) {
		Predicate<Todo> predicate = todo -> todo.getId() == id;
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}

	public void updateTodo(@Valid Todo todo) {
		deleteById(todo.getId());
		todos.add(todo);
	}
}
