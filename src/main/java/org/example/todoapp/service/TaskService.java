package org.example.todoapp.service;/*
    @author Andrii
    @project todo-app
    @class TaskService
    @version 1.0.0
    @since 02.03.2026 - 17.43
*/

import lombok.RequiredArgsConstructor;
import org.example.todoapp.model.Task;
import org.example.todoapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {


    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public List<Task> getByStatus(boolean done) {
        return taskRepository.findByDone(done);
    }

    public Task create(Task task) {
        return taskRepository.save(task);
    }

    public Task update(Long id, Task updated) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found: " + id));
        task.setTitle(updated.getTitle());
        task.setDone(updated.isDone());
        return taskRepository.save(task);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}