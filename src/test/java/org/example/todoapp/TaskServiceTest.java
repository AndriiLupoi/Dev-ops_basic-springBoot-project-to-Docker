package org.example.todoapp;/*
    @author Andrii
    @project todo-app
    @class TaskServiceTest
    @version 1.0.0
    @since 02.03.2026 - 17.43
*/

import org.example.todoapp.model.Task;
import org.example.todoapp.repository.TaskRepository;
import org.example.todoapp.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void shouldReturnAllTasks() {
        Task task = new Task();
        task.setTitle("Test task");
        when(taskRepository.findAll()).thenReturn(List.of(task));

        List<Task> result = taskService.getAll();

        assertEquals(1, result.size());
        assertEquals("Test task", result.get(0).getTitle());
    }

    @Test
    void shouldCreateTask() {
        Task task = new Task();
        task.setTitle("New task");
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.create(task);

        assertEquals("New task", result.getTitle());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void shouldThrowWhenTaskNotFound() {
        when(taskRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> taskService.update(99L, new Task()));
    }

    @Test
    void shouldDeleteTask() {
        taskService.delete(1L);
        verify(taskRepository, times(1)).deleteById(1L);
    }
}