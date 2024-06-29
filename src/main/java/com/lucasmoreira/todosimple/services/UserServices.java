package com.lucasmoreira.todosimple.services;

import com.lucasmoreira.todosimple.models.User;
import com.lucasmoreira.todosimple.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.RuntimeErrorException;
import java.util.Optional;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;

    public User findById(Long id){
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(()->new RuntimeException(
                "Usuário não encontrado! Id:  " + id + ", Tipo: " + User.class.getName()
        ));
    }
    @Transactional
    public User create(User obj){
        obj.setId(null);
        obj = this.userRepository.save(obj);
        return obj;
    }
    @Transactional
    public User update(User obj){
        User newObj = findById(obj.getId());
        newObj.setPassoword(obj.getPassoword());
        return this.userRepository.save(newObj);

    }

    public void delete(Long id){
        findById(id);
        try {
            this.userRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");
        }
    }

}
