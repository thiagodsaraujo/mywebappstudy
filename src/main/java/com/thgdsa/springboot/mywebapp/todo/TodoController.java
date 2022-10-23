package com.thgdsa.springboot.mywebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
	public String showNewTodoPage() {
		return "todo";
	}

	@RequestMapping(value="add-todo", method = RequestMethod.POST)
	public String addNewTodoPage(@RequestParam String description,ModelMap model) {
		var username = (String) model.get("name");
		service.addTodo(username,description, LocalDate.now().plusYears(1), false);
		//
		return "redirect:list-todos";
	}

	//GET, POST
}
