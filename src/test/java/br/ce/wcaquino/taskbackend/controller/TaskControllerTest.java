package br.ce.wcaquino.taskbackend.controller;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;

public class TaskControllerTest {
	
	@Mock
	private TaskRepo repo;
	
	@InjectMocks
	private TaskController controller;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test(expected = ValidationException.class)
	public void naoDeveSalvarTarefaSemDescricao() throws ValidationException {
		Task todo = new Task();
		todo.setDueDate(LocalDate.now());
		controller.save(todo);
	}
	
	@Test(expected = ValidationException.class)
	public void naoDeveSalvarTarefaSemData() throws ValidationException {
		Task todo = new Task();
		todo.setTask("teste");
		controller.save(todo);
	}
	
	@Test(expected = ValidationException.class)
	public void naoDeveSalvarTarefaComDataPassada() throws ValidationException {
		Task todo = new Task();
		todo.setDueDate(LocalDate.of(2001, 01, 01));
		controller.save(todo);
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() throws ValidationException {
		Task todo = new Task();
		todo.setTask("teste");
		todo.setDueDate(LocalDate.now());
		controller.save(todo);
		
		Mockito.verify(repo).save(todo);
	}
}
