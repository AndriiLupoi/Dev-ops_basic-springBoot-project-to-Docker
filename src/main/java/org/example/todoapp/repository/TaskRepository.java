package org.example.todoapp.repository;/*
    @author Andrii
    @project todo-app
    @class TaskRepository
    @version 1.0.0
    @since 02.03.2026 - 17.43
*/

import org.example.todoapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByDone(boolean done);
}