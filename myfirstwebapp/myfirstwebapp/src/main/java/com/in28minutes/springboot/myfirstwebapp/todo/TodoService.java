package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
	
	private static List<Todo> todos = new ArrayList<>();
	private static int todosCount = 0;
	
	static {
		todos.add(new Todo(++todosCount, "in28minutes", "Learn spring boot in 3 months", LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++todosCount, "in28minutes", "Learn spring security in 3 months", LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++todosCount, "om", "learn to gym", LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++todosCount, "om", "Learn spring security in 3 months", LocalDate.now().plusYears(1), false));
	}
	
	public List<Todo> findByUsername(String username){
		Predicate<? super Todo> predicate = todo -> todo.getUsername().equals(username);
		return todos.stream().filter(predicate).toList();
	}
	
	public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
		Todo todo = new Todo(++todosCount,username, description, targetDate, done);
		
		todos.add(todo);
	}
	
	public void deleteTodo(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		todos.removeIf(predicate);
	}
	
	public void updateTodo(@Valid Todo todo) {
		deleteTodo(todo.getId());
		todos.add(todo);
	}

	public Todo findById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}
}
