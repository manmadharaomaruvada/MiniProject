package com.oneable.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneable.exception.CourseNotFoundException;
import com.oneable.model.Course;
import com.oneable.repo.CourseRepository;
import com.oneable.util.CourseUtil;


@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository repository;
	@Autowired
	private CourseUtil util;
	@Override
	public Integer saveCourse(Course c) {
		util.calcaulateDynamics(c);
		c = repository.save(c);
		
		return c.getCid();
	}

	@Override
	public Course getOneCourse(Integer id) {
		return repository.findById(id).orElseThrow(
				()->new CourseNotFoundException("Course not exist"));
		
	}

	@Override
	public List<Course> getAllCourse() {
		List<Course> list=repository.findAll();
		return list;
	}

}
