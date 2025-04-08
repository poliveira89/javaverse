package com.poliveira.javaverse;

import static com.poliveira.javaverse.model.Status.TODO;
import static java.lang.System.currentTimeMillis;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.poliveira.javaverse.model.SimpleTaskVO;
import com.poliveira.javaverse.model.TaskVO;
import com.poliveira.javaverse.repository.TaskRepository;
import com.poliveira.javaverse.service.TaskService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TasksServiceTest {

  @Mock private TaskRepository taskRepository;
  @InjectMocks private TaskService taskService;

  @Test
  public void testCreateTask() {
    SimpleTaskVO task =
        SimpleTaskVO.builder().title("New Task").description("This is a new task").build();
    TaskVO mockTaskVO =
        TaskVO.builder()
            .id(1L)
            .title(task.getTitle())
            .description(task.getDescription())
            .status(TODO)
            .createdAt(currentTimeMillis())
            .updatedAt(currentTimeMillis())
            .build();

    when(taskRepository.save(any(TaskVO.class))).thenReturn(mockTaskVO);

    TaskVO taskVO = taskService.createTask(task);
    assertTrue(taskVO.getId() > 0);
    assertEquals(task.getTitle(), taskVO.getTitle());
    assertEquals(task.getDescription(), taskVO.getDescription());
    assertEquals(TODO, taskVO.getStatus());
  }

  @Test
  public void testFindAll() {
    TaskVO task =
        TaskVO.builder()
            .id(1L)
            .title("Test Task")
            .description("This is a test task")
            .status(TODO)
            .createdAt(currentTimeMillis())
            .updatedAt(currentTimeMillis())
            .build();

    when(taskRepository.findAll()).thenReturn(List.of(task));

    List<TaskVO> tasks = taskService.getAllTasks();
    assertEquals(1, tasks.size());
    assertEquals(task.getTitle(), tasks.getFirst().getTitle());
  }

  @Test
  public void testUpdateTask() {
    TaskVO mockExistingTask =
        TaskVO.builder()
            .id(1L)
            .title("Old Task")
            .description("This is an old task")
            .status(TODO)
            .createdAt(currentTimeMillis())
            .updatedAt(currentTimeMillis())
            .build();
    TaskVO mockUpdatedTask =
        TaskVO.builder()
            .id(1L)
            .title("Updated Task")
            .description("This is an updated task")
            .status(TODO)
            .createdAt(currentTimeMillis())
            .updatedAt(currentTimeMillis())
            .build();

    when(taskRepository.findById(1L)).thenReturn(Optional.of(mockExistingTask));
    when(taskRepository.save(any(TaskVO.class))).thenReturn(mockUpdatedTask);

    SimpleTaskVO task =
        SimpleTaskVO.builder().title("Updated Task").description("This is an updated task").build();
    TaskVO updatedTask = taskService.updateTask(1L, task);
    assertEquals(task.getTitle(), updatedTask.getTitle());
    assertEquals(task.getDescription(), updatedTask.getDescription());
  }
}
