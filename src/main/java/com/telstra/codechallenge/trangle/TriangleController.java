package com.telstra.codechallenge.trangle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TriangleController {
	
	@Autowired
	TriangleSevice triangleSevice;
	
	@RequestMapping(path = "/TriangleType", method = RequestMethod.GET)
	public Triangle Triangle(@RequestParam int a,int b,int c) {
		return new Triangle(triangleSevice.trangleType(a, b, c));		
	}

}
