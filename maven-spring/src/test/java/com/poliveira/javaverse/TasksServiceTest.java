package com.poliveira.javaverse;

import static com.poliveira.javaverse.models.Status.OPEN;
import static java.lang.System.currentTimeMillis;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.poliveira.javaverse.entities.StatusEntity;
import com.poliveira.javaverse.entities.TaskEntity;
import com.poliveira.javaverse.models.SimpleTaskVO;
import com.poliveira.javaverse.models.TaskVO;
import com.poliveira.javaverse.processors.MappingService;
import com.poliveira.javaverse.repositories.TaskRepository;
import com.poliveira.javaverse.services.TaskService;
import com.poliveira.javaverse.utils.TimeUtils;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TasksServiceTest {

  private final StatusEntity open =
      StatusEntity.builder()
          .id(UUID.fromString("D6127F26-55B6-408D-A010-322FBEC9B1B1"))
          .name("Open")
          .build();

  @Mock private TimeUtils timeUtils;
  @Mock private MappingService mappingService;
  @Mock private TaskRepository taskRepository;
  @InjectMocks private TaskService taskService;

  @Test
  public void testCreateTask() {
    SimpleTaskVO task =
        SimpleTaskVO.builder().name("New Task").description("This is a new task").build();
    UUID taskId = UUID.randomUUID();
    long now = currentTimeMillis();
    TaskEntity mockTaskEntity =
        TaskEntity.builder()
            .id(taskId)
            .name("New Task")
            .description("This is a new task")
            .status(open)
            .createdAt(timeUtils.toLocalDateTime(now))
            .updatedAt(timeUtils.toLocalDateTime(now))
            .build();
    TaskVO mockTaskVO =
        TaskVO.builder()
            .id(taskId)
            .name("New Task")
            .description("This is a new task")
            .status(OPEN)
            .createdAt(now)
            .updatedAt(now)
            .build();

    when(mappingService.toVO(any(TaskEntity.class))).thenReturn(mockTaskVO);
    when(mappingService.toEntity(any(TaskVO.class))).thenReturn(mockTaskEntity);
    when(taskRepository.save(any(TaskEntity.class))).thenReturn(mockTaskEntity);

    TaskVO taskVO = taskService.createTask(task);
    assertEquals(taskVO.getId(), taskId);
    assertEquals("New Task", taskVO.getName());
    assertEquals("This is a new task", taskVO.getDescription());
    assertEquals(OPEN, taskVO.getStatus());
  }

  @Test
  public void testFindAll() {
    TaskVO mockTaskVO =
        TaskVO.builder()
            .id(UUID.randomUUID())
            .name("Test Task")
            .description("This is a test task")
            .status(OPEN)
            .createdAt(currentTimeMillis())
            .updatedAt(currentTimeMillis())
            .build();
    TaskEntity task =
        TaskEntity.builder()
            .id(UUID.randomUUID())
            .name("Test Task")
            .description("This is a test task")
            .status(open)
            .createdAt(timeUtils.toLocalDateTime(currentTimeMillis()))
            .updatedAt(timeUtils.toLocalDateTime(currentTimeMillis()))
            .build();

    when(mappingService.toVO(any(TaskEntity.class))).thenReturn(mockTaskVO);
    when(taskRepository.findAll()).thenReturn(List.of(task));

    List<TaskVO> tasks = taskService.getAllTasks();
    assertEquals(1, tasks.size());
    assertEquals(task.getName(), tasks.getFirst().getName());
  }

  @Test
  public void testUpdateTask() {
    UUID taskId = UUID.randomUUID();
    TaskEntity mockExistingTask =
        TaskEntity.builder()
            .id(taskId)
            .name("Old Task")
            .description("This is an old task")
            .status(open)
            .createdAt(timeUtils.toLocalDateTime(currentTimeMillis()))
            .updatedAt(timeUtils.toLocalDateTime(currentTimeMillis()))
            .build();
    TaskVO mockExistingTaskVO =
        TaskVO.builder()
            .id(taskId)
            .name("Old Task")
            .description("This is an old task")
            .status(OPEN)
            .createdAt(currentTimeMillis())
            .updatedAt(currentTimeMillis())
            .build();
    TaskEntity mockUpdatedTask =
        TaskEntity.builder()
            .id(taskId)
            .name("Updated Task")
            .description("This is an updated task")
            .status(open)
            .createdAt(timeUtils.toLocalDateTime(currentTimeMillis()))
            .updatedAt(timeUtils.toLocalDateTime(currentTimeMillis()))
            .build();

    when(taskRepository.findById(taskId)).thenReturn(Optional.of(mockExistingTask));
    when(mappingService.toVO(any(TaskEntity.class))).thenReturn(mockExistingTaskVO);
    when(mappingService.toEntity(any(TaskVO.class))).thenReturn(mockUpdatedTask);
    when(taskRepository.save(any(TaskEntity.class))).thenReturn(mockUpdatedTask);

    SimpleTaskVO task =
        SimpleTaskVO.builder().name("Updated Task").description("This is an updated task").build();
    TaskVO updatedTask = taskService.updateTask(taskId, task);
    assertEquals(task.getName(), updatedTask.getName());
    assertEquals(task.getDescription(), updatedTask.getDescription());
  }
}
