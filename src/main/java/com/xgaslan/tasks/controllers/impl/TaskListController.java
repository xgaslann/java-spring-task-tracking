package com.xgaslan.tasks.controllers.impl;

import com.xgaslan.tasks.controllers.ITaskListController;
import com.xgaslan.tasks.domain.dto.TaskListDto;
import com.xgaslan.tasks.mappers.ITaskListMapper;
import com.xgaslan.tasks.services.ITaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @GetMapping("/{id}")
    @Override
    public Optional<TaskListDto> getById(@PathVariable UUID id) {
        return service.getById(id).map(mapper::toDto);
    }

    @PutMapping("/{id}")
    @Override
    public TaskListDto update(@PathVariable UUID id, @RequestBody TaskListDto dto) {
        var result = service.update(id, mapper.toEntity(dto));
        return mapper.toDto(result);
    }

    @DeleteMapping("/{id}")
    @Override
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
