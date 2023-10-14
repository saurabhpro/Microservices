package com.saurabh.departmentweb.init;

import com.department.api.models.Department;
import com.saurabh.departmentweb.services.DepartmentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Application implements CommandLineRunner {

    private final ApplicationContext applicationContext;
    private final DepartmentService departmentService;

    public Application(ApplicationContext applicationContext,
                       DepartmentService departmentService) {
        this.applicationContext = applicationContext;
        this.departmentService = departmentService;
    }


    @Override
    public void run(String... args) {

        Department department = new Department();
        department.setDepartmentName("Saurabh");
        department.setCapacity(31);
        departmentService.saveDepartment(department);

//        System.out.println(applicationContext.getDisplayName());
//        System.out.println(applicationContext.getId());
//
//        MyBean myBean = applicationContext.getBean(MyBean.class);
//        System.out.println(myBean.getApplicationId());
    }
}