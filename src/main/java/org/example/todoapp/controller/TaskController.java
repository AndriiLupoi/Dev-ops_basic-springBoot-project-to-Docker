package org.example.todoapp.controller;/*
    @author Andrii
    @project todo-app
    @class TaskController
    @version 1.0.0
    @since 02.03.2026 - 17.43
*/

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.todoapp.model.Task;
import org.example.todoapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAll(@RequestParam(required = false) Boolean done) {
        if (done != null) return taskService.getByStatus(done);
        return taskService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@Valid @RequestBody Task task) {
        return taskService.create(task);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @Valid @RequestBody Task task) {
        return taskService.update(id, task);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }
}