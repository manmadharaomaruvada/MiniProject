package com.oneable.util;

import org.springframework.stereotype.Component;

import com.oneable.model.Course;
@Component
public class CourseUtil {

	public void calcaulateDynamics(Course c) {
		Double fee = c.getCfee();
		var discount=fee*10.0/100;
		var gst=fee*18.0/100;
		c.setDiscount(discount);
		c.setGst(gst);
		
	}

}
