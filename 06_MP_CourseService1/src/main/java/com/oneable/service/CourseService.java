package com.oneable.service;

import java.util.List;

import com.oneable.model.Course;

public interface CourseService {

	public Integer saveCourse(Course c);
	public Course getOneCourse(Integer id);
	public List<Course> getAllCourse();
}
