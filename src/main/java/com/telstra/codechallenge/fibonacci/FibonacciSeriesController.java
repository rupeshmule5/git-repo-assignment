package com.telstra.codechallenge.fibonacci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FibonacciSeriesController {
	
	
	@Autowired
	FibonacciSeriesService fibonacciSeriesService;
	
	
	@RequestMapping(path = "/fibonacci", method = RequestMethod.GET)
	public FibonacciSeries fibonacci(@RequestParam(value = "number", defaultValue = "0") int number) {
		return new FibonacciSeries(fibonacciSeriesService.fibonacci(number));		
	}

}
