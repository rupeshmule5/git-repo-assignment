package com.telstra.codechallenge.trangle;

import org.springframework.stereotype.Service;

@Service
public class TriangleSevice {
	public String trangleType(int a, int b, int c) {
		if (a == b && b == c)
			return "Equilatateral";
		else {
		   if ((a == b && b != c) || (a != b && c == a) || (c == b && c != a))
				return "Isosceles";
			if (a != b && b != c && c != a)
				return "Scalene";
			else
				return "Sorry";
		}
	}
}
