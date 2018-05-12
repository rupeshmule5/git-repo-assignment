package com.telstra.codechallenge.fibonacci;

import org.springframework.stereotype.Service;

@Service
public class FibonacciSeriesService {

	public int fibonacci(int number) {

		int n1 = 0, n2 = 1, n3 = 0;

		for (int i = 2; i < number; ++i)// loop starts from 2 because 0 and 1 are already printed
		{
			n3 = n1 + n2;
			n1 = n2;
			n2 = n3;
		}
		return n3;
	}
}
