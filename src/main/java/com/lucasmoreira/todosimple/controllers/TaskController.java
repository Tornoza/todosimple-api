package com.lucasmoreira.todosimple.controllers;

import com.lucasmoreira.todosimple.models.Task;
import com.lucasmoreira.todosimple.services.TaskServices;
import com.lucasmoreira.todosimple.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/task")
@Validated
public class TaskController {
    @Autowired
    private TaskServices taskServices;
    private UserServices userServices;

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id){
        Task obj = this.taskServices.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> findByAllUserId(@PathVariable Long userId){
        this.userServices.findById(userId);
        List<Task> objs = this.taskServices.findByAllUserId(userId);
        return ResponseEntity.ok().body(objs);
    }

    @PostMapping
    @Validated
    public ResponseEntity<Void> create(@Valid @RequestBody Task obj){
        this.taskServices.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody Task obj, @PathVariable Long id){
        obj.setId(id);
        this.taskServices.uptade(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.taskServices.delete(id);
        return ResponseEntity.noContent().build();
    }




}
