package com.poliveira.javaverse;

import static java.lang.System.currentTimeMillis;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.poliveira.javaverse.entities.StatusEntity;
import com.poliveira.javaverse.entities.TaskEntity;
import com.poliveira.javaverse.repositories.TaskRepository;
import com.poliveira.javaverse.utils.TimeUtils;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TasksRepositoryTest {

  @Mock private TaskRepository taskRepository;

  private TimeUtils timeUtils = new TimeUtils();
  private StatusEntity open =
      StatusEntity.builder()
          .id(UUID.fromString("D6127F26-55B6-408D-A010-322FBEC9B1B1"))
          .name("Open")
          .build();

  @BeforeEach
  public void setUp() {}

  @Test
  public void testCreateTask() {
    UUID taskId = UUID.randomUUID();
    TaskEntity mockTaskEntity =
        TaskEntity.builder()
            .id(taskId)
            .name("New Task")
            .description("This is a new task")
            .status(open)
            .status(open)
            .status(open)
            .createdAt(timeUtils.toLocalDateTime(currentTimeMillis()))
            .updatedAt(timeUtils.toLocalDateTime(currentTimeMillis()))
            .build();

    when(taskRepository.save(any(TaskEntity.class))).thenReturn(mockTaskEntity);

    TaskEntity taskEntity = taskRepository.save(mockTaskEntity);
    assertEquals(taskEntity.getId(), taskId);
    assertEquals(mockTaskEntity.getName(), taskEntity.getName());
    assertEquals(mockTaskEntity.getDescription(), taskEntity.getDescription());
    assertEquals(open.getId(), taskEntity.getStatus().getId());
    assertEquals(open.getName(), taskEntity.getStatus().getName());
  }

  @Test
  public void testFindAllTasks() {
    UUID taskId1 = UUID.randomUUID();
    TaskEntity task1 =
        TaskEntity.builder()
            .id(taskId1)
            .name("Task 1")
            .description("Description 1")
            .createdAt(timeUtils.toLocalDateTime(currentTimeMillis()))
            .updatedAt(timeUtils.toLocalDateTime(currentTimeMillis()))
            .build();

    UUID taskId2 = UUID.randomUUID();
    TaskEntity task2 =
        TaskEntity.builder()
            .id(taskId2)
            .name("Task 2")
            .description("Description 2")
            .createdAt(timeUtils.toLocalDateTime(currentTimeMillis()))
            .updatedAt(timeUtils.toLocalDateTime(currentTimeMillis()))
            .build();

    when(taskRepository.findAll()).thenReturn(List.of(task1, task2));

    List<TaskEntity> tasks = taskRepository.findAll();
    assertEquals(2, tasks.size());
    assertEquals(task1.getName(), tasks.get(0).getName());
    assertEquals(task2.getName(), tasks.get(1).getName());
  }

  @Test
  public void testFindById() {
    UUID taskId = UUID.randomUUID();
    TaskEntity task1 =
        TaskEntity.builder()
            .id(taskId)
            .name("Task 1")
            .description("Description 1")
            .status(open)
            .createdAt(timeUtils.toLocalDateTime(currentTimeMillis()))
            .updatedAt(timeUtils.toLocalDateTime(currentTimeMillis()))
            .build();

    when(taskRepository.findById(taskId)).thenReturn(Optional.of(task1));

    TaskEntity foundTask = taskRepository.findById(taskId).orElse(null);
    assertNotNull(foundTask);
    assertEquals(task1.getName(), foundTask.getName());
    assertEquals(task1.getDescription(), foundTask.getDescription());
  }

  @Test
  public void testUpdateTask() {
    UUID taskId = UUID.randomUUID();
    TaskEntity mockTaskEntity =
        TaskEntity.builder()
            .id(taskId)
            .name("New Task")
            .description("This is an new task")
            .status(open)
            .createdAt(timeUtils.toLocalDateTime(currentTimeMillis()))
            .updatedAt(timeUtils.toLocalDateTime(currentTimeMillis()))
            .build();

    when(taskRepository.save(any(TaskEntity.class))).thenReturn(mockTaskEntity);

    TaskEntity taskEntity1 = taskRepository.save(mockTaskEntity);
    assertEquals(taskEntity1.getId(), taskId);
    assertEquals(mockTaskEntity.getName(), taskEntity1.getName());
    assertEquals(mockTaskEntity.getDescription(), taskEntity1.getDescription());
    assertEquals(open.getId(), taskEntity1.getStatus().getId());
    assertEquals(open.getName(), taskEntity1.getStatus().getName());

    // update the task
    mockTaskEntity.setName("Updated Task");
    when(taskRepository.save(any(TaskEntity.class))).thenReturn(mockTaskEntity);

    TaskEntity taskEntity2 = taskRepository.save(mockTaskEntity);
    assertEquals(taskEntity2.getId(), taskId);
    assertEquals(mockTaskEntity.getName(), taskEntity2.getName());
    assertEquals(mockTaskEntity.getDescription(), taskEntity2.getDescription());
    assertEquals(open.getId(), taskEntity2.getStatus().getId());
    assertEquals(open.getName(), taskEntity2.getStatus().getName());
  }
}
