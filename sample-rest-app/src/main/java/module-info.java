module com.saurabh.samplerestapp {
	requires spring.core;
	requires spring.beans;
	requires spring.context;
	requires spring.aop;
	requires spring.web;
	requires spring.expression;

	requires spring.boot;
	requires spring.boot.autoconfigure;

	requires com.saurabh.lib;

	exports com.saurabh.samplerestapp;
	exports com.saurabh.samplerestapp.resources;

	opens com.saurabh.samplerestapp;
	opens com.saurabh.samplerestapp.resources;
}