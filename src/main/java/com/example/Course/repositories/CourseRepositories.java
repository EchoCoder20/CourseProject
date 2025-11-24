package com.example.Course.repositories;

import com.example.Course.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepositories extends JpaRepository<Course, Integer> {

}

