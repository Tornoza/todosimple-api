package com.lucasmoreira.todosimple.services;

import com.lucasmoreira.todosimple.models.Task;
import com.lucasmoreira.todosimple.models.User;
import com.lucasmoreira.todosimple.repositories.TaskRepository;
import com.lucasmoreira.todosimple.repositories.UserRepository;
import com.lucasmoreira.todosimple.services.exceptions.DataBidingViolationException;
import com.lucasmoreira.todosimple.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.Descriptor;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServices {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserServices userServices;

    public Task findById(Long id){
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(() -> new ObjectNotFoundException(
                "Tarefa não encontrada! Id:" + id + ", Tipo: " + Task.class.getName()
        ));
    }

    public List<Task> findByAllUserId(Long userId){
        List<Task> tasks = this.taskRepository.findByUser_Id(userId);
        return tasks;
    }


    @Transactional
    public Task create(Task obj){
        User user = this.userServices.findById(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user);
        obj = this.taskRepository.save(obj);
        return obj;

    }

    @Transactional
    public Task uptade(Task obj){
        Task newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return this.taskRepository.save(newObj);
    }

    @Transactional
    public void delete(Long id){
        findById(id);
        try{
            this.taskRepository.deleteById(id);
        }catch(Exception e){
            throw new DataBidingViolationException("Não é possível excluir pois há entidades relacionadas!");
        }
    }


}
