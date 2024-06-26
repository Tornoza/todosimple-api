package com.lucasmoreira.todosimple.repositories;

import com.lucasmoreira.todosimple.models.Task;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "SELECT * FROM task t WHERE t.user_id = :id", nativeQuery = true)
    List<Task> findByUser_Id(@Param("id")Long id);


}
