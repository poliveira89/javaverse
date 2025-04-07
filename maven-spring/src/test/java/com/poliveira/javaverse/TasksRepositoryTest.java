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

    TaskVO taskVO = taskRepository.save(mockTaskVO);
    assertTrue(taskVO.getId() > 0);
    assertEquals(task.getTitle(), taskVO.getTitle());
    assertEquals(task.getDescription(), taskVO.getDescription());
    assertEquals(TODO, taskVO.getStatus());
  }
}
