package com.poliveira.javaverse;

import static com.poliveira.javaverse.model.Status.TODO;
import static java.lang.System.currentTimeMillis;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.poliveira.javaverse.model.SimpleTaskVO;
import com.poliveira.javaverse.model.TaskVO;
import com.poliveira.javaverse.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TasksRepositoryTest {

  @Mock private TaskRepository taskRepository;

  @BeforeEach
  public void setUp() {}

  @Test
  public void testCreateTask() {
    TaskVO mockTaskVO =
        TaskVO.builder()
            .id(1L)
            .title("New Task")
            .description("This is a new task")
            .status(TODO)
            .createdAt(currentTimeMillis())
            .updatedAt(currentTimeMillis())
            .build();

    when(taskRepository.save(any(TaskVO.class))).thenReturn(mockTaskVO);

    TaskVO taskVO = taskRepository.save(mockTaskVO);
    assertTrue(taskVO.getId() > 0);
    assertEquals(mockTaskVO.getTitle(), taskVO.getTitle());
    assertEquals(mockTaskVO.getDescription(), taskVO.getDescription());
    assertEquals(TODO, taskVO.getStatus());
  }

  @Test
  public void testFindAllTasks() {
    TaskVO task1 =
        TaskVO.builder()
            .id(1L)
            .title("Task 1")
            .description("Description 1")
            .status(TODO)
            .createdAt(currentTimeMillis())
            .updatedAt(currentTimeMillis())
            .build();

    TaskVO task2 =
        TaskVO.builder()
            .id(2L)
            .title("Task 2")
            .description("Description 2")
            .status(TODO)
            .createdAt(currentTimeMillis())
            .updatedAt(currentTimeMillis())
            .build();

    when(taskRepository.findAll()).thenReturn(List.of(task1, task2));

    List<TaskVO> tasks = taskRepository.findAll();
    assertEquals(2, tasks.size());
    assertEquals(task1.getTitle(), tasks.get(0).getTitle());
    assertEquals(task2.getTitle(), tasks.get(1).getTitle());
  }

  @Test
  public void testFindById() {
    TaskVO task1 =
            TaskVO.builder()
                    .id(1L)
                    .title("Task 1")
                    .description("Description 1")
                    .status(TODO)
                    .createdAt(currentTimeMillis())
                    .updatedAt(currentTimeMillis())
                    .build();

    when(taskRepository.findById(1L)).thenReturn(java.util.Optional.of(task1));

    TaskVO foundTask = taskRepository.findById(1L).orElse(null);
    assertNotNull(foundTask);
    assertEquals(task1.getTitle(), foundTask.getTitle());
    assertEquals(task1.getDescription(), foundTask.getDescription());
  }

  @Test
  public void testUpdateTask() {
    TaskVO mockTaskVO =
        TaskVO.builder()
            .id(1L)
            .title("New Task")
            .description("This is an new task")
            .status(TODO)
            .createdAt(currentTimeMillis())
            .updatedAt(currentTimeMillis())
            .build();

    when(taskRepository.save(any(TaskVO.class))).thenReturn(mockTaskVO);

    TaskVO taskVO1 = taskRepository.save(mockTaskVO);
    assertTrue(taskVO1.getId() > 0);
    assertEquals(mockTaskVO.getTitle(), taskVO1.getTitle());
    assertEquals(mockTaskVO.getDescription(), taskVO1.getDescription());
    assertEquals(TODO, taskVO1.getStatus());

    // update the task
    mockTaskVO.setTitle("Updated Task");
    when(taskRepository.save(any(TaskVO.class))).thenReturn(mockTaskVO);

    TaskVO taskVO2 = taskRepository.save(mockTaskVO);
    assertTrue(taskVO2.getId() > 0);
    assertEquals(mockTaskVO.getTitle(), taskVO2.getTitle());
    assertEquals(mockTaskVO.getDescription(), taskVO2.getDescription());
    assertEquals(TODO, taskVO2.getStatus());
  }
}
