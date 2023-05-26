package com.oneable.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.oneable.model.Course;

public interface CourseService {

	public Integer saveCourse(Course c);
	public Course getOneCourse(Integer id);
	public Page<Course> getAllCourse(Pageable p);
}
