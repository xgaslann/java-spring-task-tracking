package com.xgaslan.tasks.controllers.impl;

import com.xgaslan.tasks.controllers.ITaskListController;
import com.xgaslan.tasks.domain.dto.TaskListDto;
import com.xgaslan.tasks.mappers.ITaskListMapper;
import com.xgaslan.tasks.services.ITaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/task-lists")
public class TaskListController implements ITaskListController {

    private final ITaskListService service;

    private final ITaskListMapper mapper;

    public TaskListController(ITaskListService taskListService, ITaskListMapper taskListMapper) {
        this.service = taskListService;
        this.mapper = taskListMapper;
    }

    @GetMapping
    @Override
    public List<TaskListDto> getAll() {
        return service.getAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @PostMapping
    @Override
    public TaskListDto create(@RequestBody TaskListDto dto) {
        var result = service.create(mapper.toEntity(dto));
        return mapper.toDto(result);
    }
}
