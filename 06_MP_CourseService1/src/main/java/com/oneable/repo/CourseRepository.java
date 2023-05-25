package com.oneable.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oneable.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
