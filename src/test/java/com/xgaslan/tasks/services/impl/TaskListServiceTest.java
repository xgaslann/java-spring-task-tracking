package com.xgaslan.tasks.services.impl;

import com.xgaslan.tasks.domain.entities.TaskList;
import com.xgaslan.tasks.repositories.ITaskListRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class TaskListServiceTest {

    @Mock
    private ITaskListRepository repository;

    @InjectMocks
    private TaskListService service;

    @Test
    public void create_ShouldReturnSavedTaskList_WhenValidEntityGiven(){
        var toCreate = new TaskList(
                null,
                "Title",
                "Description",
                null,
                LocalDateTime.now(),
                LocalDateTime.now());

        var saved = new TaskList(
                UUID.randomUUID(),
                "Title",
                "Description",
                null,
                LocalDateTime.now(),
                LocalDateTime.now());

        Mockito.when(repository.save(any(TaskList.class))).thenReturn(saved);

        var result = service.create(toCreate);

        assertNotNull(result.getId());
        assertEquals("Title", result.getTitle());
        assertEquals("Description", result.getDescription());
        Mockito.verify(repository).save(any(TaskList.class));

    }
}
