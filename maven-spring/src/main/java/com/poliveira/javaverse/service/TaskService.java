package com.poliveira.javaverse.service;

import static com.poliveira.javaverse.model.Status.TODO;
import static java.lang.System.currentTimeMillis;
import static java.util.Objects.nonNull;

import com.poliveira.javaverse.model.SimpleTaskVO;
import com.poliveira.javaverse.model.TaskVO;
import com.poliveira.javaverse.repository.TaskRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

  private final TaskRepository taskRepository;

  public List<TaskVO> getAllTasks() {
    return taskRepository.findAll();
  }

  public TaskVO getTaskById(Long id) {
    return taskRepository.findById(id).orElse(null);
  }

  public TaskVO createTask(SimpleTaskVO simpleTaskVO) {
    TaskVO task =
        TaskVO.builder()
            .title(simpleTaskVO.getTitle())
            .description(simpleTaskVO.getDescription())
            .status(TODO)
            .createdAt(currentTimeMillis())
            .updatedAt(currentTimeMillis())
            .build();
    return taskRepository.save(task);
  }

  public TaskVO updateTask(Long id, SimpleTaskVO task) {
    TaskVO existingTask = taskRepository.findById(id).orElse(null);
    if (nonNull(existingTask)) {
      existingTask.setTitle(task.getTitle());
      existingTask.setDescription(task.getDescription());
      existingTask.setUpdatedAt(currentTimeMillis());
      return taskRepository.save(existingTask);
    }
    return null;
  }

  public void deleteTask(Long id) {
    taskRepository.deleteById(id);
  }
}
