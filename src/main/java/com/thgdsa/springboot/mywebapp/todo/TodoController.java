package com.thgdsa.springboot.mywebapp.todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class TodoController {
	
	@Autowired
	private TodoService service;

	//list-todos
	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		List<Todo> todos = service.findByUsername("tita");
		model.addAttribute("todos", todos);
		return "listTodos";
	}

	@RequestMapping(value="add-todo", method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		var username = (String) model.get("name");
		Todo todo = new Todo(0,username, "", LocalDate.now().plusYears(1),false);
		model.put("todo",todo );
		return "todo";
	}

	@RequestMapping(value="add-todo", method = RequestMethod.POST)
	public String addNewTodoPage(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()){
			return "todo";
		}
		var username = (String) model.get("name");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		service.addTodo(username,todo.getDescription(), todo.getTargetDate(), false);
		//
		return "redirect:list-todos";
	}

	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {
		service.deleteById(id);
		return "redirect:list-todos";
	}

	@RequestMapping(value="update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = service.findById(id);
		model.addAttribute("todo",todo);
		return "todo";
	}

	@RequestMapping(value="update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()){
			return "todo";
		}
		var username = (String) model.get("name");
		todo.setUsername(username);
		service.updateTodo(todo);
		return "redirect:list-todos";
	}
}
