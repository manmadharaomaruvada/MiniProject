package com.oneable.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oneable.exception.CourseNotFoundException;
import com.oneable.model.Course;
import com.oneable.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseRestController {

	private static final Logger LOG=LoggerFactory.getLogger(CourseRestController.class);
	@Autowired
	private CourseService service;
	//1. save course
	
	@PostMapping("/save")
	public ResponseEntity<String> saveCourse(@RequestBody Course c){
		LOG.info("Entered into SAVE COURSE method");
		ResponseEntity<String> resp=null;
		try {
			Integer id = service.saveCourse(c);
			LOG.info("COURSE SAVED {}"+id);
//			resp=ResponseEntity.ok("Course '"+id+"' Created");
			resp=new ResponseEntity<String>("Course '"+id+"' Created", 
					HttpStatus.CREATED);
		} catch (Exception e) {
			LOG.info("UNABLE TO SAVE COURSE {}",e.getMessage());
			resp=new ResponseEntity<String>("Unable to save course ", 
					HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		LOG.info("RETURNING BACK FROM SAVE COURSE METHOD");
		return resp;
		
	}
	
	//2. Fetch course By Id
	@GetMapping("/data/{id}")
	public ResponseEntity<?> getCourseById(@PathVariable Integer id){
		LOG.info("Entered into GET ONE COURSE method");
		ResponseEntity<?> resp=null;
		
		try {
			Course course=service.getOneCourse(id);
			resp=ResponseEntity.ok(course);
		}catch (CourseNotFoundException e) {
			throw e; //re-throw--> ExceptionHandler
		}catch (Exception e) {
			LOG.info("UNABLE TO SAVE COURSE {}",e.getMessage());
			resp=new ResponseEntity<String>("Unable to FETCH course ", 
					HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		LOG.info("RETURNING BACK FROM GET COURSE METHOD");
		return resp;
	}
	//3. Fetch All Courses
	// http://ojas-dd460.corp.ojas-it.com:8090/course/all?page=2&size=1&sort=cname
	@GetMapping("/all")
	public ResponseEntity<?> getAllCourses(
			@PageableDefault(page = 0,size = 3) Pageable pageable){
		LOG.info("Entered into GET ALL COURSE method");
		ResponseEntity<?> resp=null;
		try {
			Page<Course> page=service.getAllCourse(pageable);
			resp=ResponseEntity.ok(page);
			LOG.info("COURSE OBJECT IS SELECTED {} ", page);
		} catch (Exception e) {
			LOG.info("UNABLE TO FETCH COURSES {}",e.getMessage());
			resp=new ResponseEntity<String>("Unable to FETCH course ", 
					HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		LOG.info("RETURNING BACK FROM GET ALL COURSE METHOD");
		return resp;
		 
	}
}
