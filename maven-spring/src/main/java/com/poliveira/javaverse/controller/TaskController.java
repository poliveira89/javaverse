package com.poliveira.javaverse.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.poliveira.javaverse.model.SimpleTaskVO;
import com.poliveira.javaverse.model.TaskVO;
import com.poliveira.javaverse.service.TaskService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

  private final TaskService taskService;

  @PostMapping
  @ResponseStatus(CREATED)
  public TaskVO createTask(@RequestBody SimpleTaskVO taskVO) {
    return taskService.createTask(taskVO);
  }

  @GetMapping("/{id}")
  public TaskVO readTask(@PathVariable Long id) {
    return taskService.getTaskById(id);
  }

  @PatchMapping("/{id}")
  public TaskVO updateTask(@PathVariable Long id, @RequestBody SimpleTaskVO taskVO) {
    return taskService.updateTask(id, taskVO);
  }

  @DeleteMapping("/{id}")
  public void deleteTask(@PathVariable Long id) {
    // @TODO return object with delete status
    taskService.deleteTask(id);
  }

  @GetMapping
  public List<TaskVO> listTasks() {
    return taskService.getAllTasks();
  }

  // Search Task
  public void searchTask() {
    // Implementation for searching a task
  }

  // Filter Task
  public void filterTask() {
    // Implementation for filtering tasks
  }
}
