/**
 *
 */
module com.saurabh.restsample {
	requires spring.core;
	requires spring.beans;
	requires spring.context;
	requires spring.aop;
	requires spring.web;
	requires spring.expression;

	requires spring.boot;
	requires spring.boot.autoconfigure;

	requires com.saurabh.lib;

	exports com.saurabh.restsample;
	exports com.saurabh.restsample.resources;

	opens com.saurabh.restsample;
	opens com.saurabh.restsample.resources;
}