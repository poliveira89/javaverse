package com.poliveira.javaverse.services;

import static com.poliveira.javaverse.models.Status.TODO;
import static java.lang.System.currentTimeMillis;

import com.poliveira.javaverse.entities.TaskEntity;
import com.poliveira.javaverse.models.SimpleTaskVO;
import com.poliveira.javaverse.models.TaskVO;
import com.poliveira.javaverse.processors.MappingService;
import com.poliveira.javaverse.repositories.TaskRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

  private final TaskRepository taskRepository;
  private final MappingService mappingService;

  public List<TaskVO> getAllTasks() {
    return taskRepository.findAll().stream().map(mappingService::toVO).toList();
  }

  public TaskVO getTaskById(UUID id) {
    TaskEntity taskEntity = taskRepository.findById(id).orElseThrow();
    return mappingService.toVO(taskEntity);
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
    TaskEntity taskEntity = mappingService.toEntity(task);
    return mappingService.toVO(taskRepository.save(taskEntity));
  }

  public TaskVO updateTask(UUID id, SimpleTaskVO task) {
    TaskEntity existingTask = taskRepository.findById(id).orElseThrow();
    TaskVO taskVo = mappingService.toVO(existingTask);
    taskVo.setTitle(task.getTitle());
    taskVo.setDescription(task.getDescription());
    taskVo.setUpdatedAt(currentTimeMillis());
    return mappingService.toVO(taskRepository.save(mappingService.toEntity(taskVo)));
  }

  public boolean deleteTask(UUID id) {
    try {
      taskRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
