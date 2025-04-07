package com.poliveira.javaverse.service;

import com.poliveira.javaverse.model.TaskVO;
import com.poliveira.javaverse.repository.TaskRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

  private TaskRepository taskRepository;

  public List<TaskVO> getAllTasks() {
    return taskRepository.findAll();
  }

  public TaskVO getTaskById(Long id) {
    return taskRepository.findById(id).orElse(null);
  }

  public TaskVO createTask(TaskVO task) {
    return taskRepository.save(task);
  }

  public TaskVO updateTask(Long id, TaskVO task) {
    TaskVO existingTask = taskRepository.findById(id).orElse(null);
    if (existingTask != null) {
      existingTask.setTitle(task.getTitle());
      existingTask.setDescription(task.getDescription());
      return taskRepository.save(existingTask);
    }
    return null;
  }

  public void deleteTask(Long id) {
    taskRepository.deleteById(id);
  }
}
