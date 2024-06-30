package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {

	public TodoControllerJpa( TodoRepository todoRepository) {
		super();
		this.todoRepository = todoRepository;
	}
	
	private TodoRepository todoRepository;

	@RequestMapping("todo-jsp")
	public String TodoApp(ModelMap model){
		String username = getLoggedinUsername(model);
		List<Todo> todos = todoRepository.findByUsername(username);
		
		model.addAttribute("todos", todos);
		
		return "todo";
	}
	
	@RequestMapping(value="add-todo", method = RequestMethod.GET)
	public String addTodo(ModelMap model){
		String username = getLoggedinUsername(model);
		Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);
		return "addtodo";
	}
	
	@RequestMapping(value="add-todo", method = RequestMethod.POST)
	public String Addtodoa(ModelMap model,@Valid Todo todo, BindingResult result){
		String username = getLoggedinUsername(model);
		if(result.hasErrors()) {
			return "addtodo";
		}
		todo.setUsername(username);
		todoRepository.save(todo);
		
		//todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), false);
		return "redirect:todo-jsp";
	}
	
	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id){
		todoRepository.deleteById(id);
		return "redirect:todo-jsp";
	}
	
	@RequestMapping(value="update-todo", method = RequestMethod.GET)
	public String updateTodo(@RequestParam int id, ModelMap model){
		Todo todo = todoRepository.findById(id).get(); 
		model.addAttribute("todo",todo);
		return "addtodo";
	}
	
	@RequestMapping(value="update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model,@Valid Todo todo, BindingResult result){
		if(result.hasErrors()) {
			return "addtodo";
		}

		String username = getLoggedinUsername(model);
		todo.setUsername(username);
		todoRepository.save(todo);
		return "redirect:todo-jsp";
	}
	
	private String getLoggedinUsername(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		return authentication.getName();
	}
}
