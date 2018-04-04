package com.saurabh.samplerestapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SampleRestAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleRestAppApplication.class, args);
	}
}

/*
 http://localhost:8000/calculation/sqrt/144

 {
    "function": "sqrt",
    "input": [
        "144"
    ],
    "output": [
        "12.0"
    ]
}

http://localhost:8000/calculation/power?base=2&exponent=4

{
    "function": "power",
    "input": [
        "2",
        "4"
    ],
    "output": [
        "16.0"
    ]
}
*/