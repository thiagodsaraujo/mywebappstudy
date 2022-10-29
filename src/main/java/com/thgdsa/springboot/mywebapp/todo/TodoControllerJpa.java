package com.thgdsa.springboot.mywebapp.todo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {
	
	@Autowired
	private TodoRepository repository;

	//list-todos
	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		var username = getName(model);


		List<Todo> todos = repository.findByUsername(username);
		model.addAttribute("todos", todos);
		return "listTodos";
	}

	@RequestMapping(value="add-todo", method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		var username = getName(model);
		Todo todo = new Todo(0,username, "", LocalDate.now().plusYears(1),false);
		model.put("todo",todo );
		return "todo";
	}

	private String getName(ModelMap model) {
		var authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

	@RequestMapping(value="add-todo", method = RequestMethod.POST)
	public String addNewTodoPage(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()){
			return "todo";
		}

		var username = getName(model);
		todo.setUsername(username);
		repository.save(todo);
//		service.addTodo(username,todo.getDescription(), todo.getTargetDate(), todo.isDone());
		//
		return "redirect:list-todos";
	}

	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {

		repository.deleteById(id);
//		service.deleteById(id);

		return "redirect:list-todos";
	}

	@RequestMapping(value="update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = repository.findById(id).get();
		model.addAttribute("todo",todo);
		return "todo";
	}

	@RequestMapping(value="update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()){
			return "todo";
		}
		var username = getName(model);
		todo.setUsername(username);
		repository.save(todo);
//		service.updateTodo(todo);
		return "redirect:list-todos";
	}
}
