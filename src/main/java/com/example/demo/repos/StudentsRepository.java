package com.example.demo.repos;

import com.example.demo.model.Students;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentsRepository extends CrudRepository<Students,Long> {

}
