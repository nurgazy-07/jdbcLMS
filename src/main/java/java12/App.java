package java12;

import java12.models.Employee;
import java12.models.Job;
import java12.service.impl.EmployeeServiceImpl;
import java12.service.impl.JobServiceImpl;

import java.util.Scanner;

public class App
{
    public static void main( String[] args )
    {
        JobServiceImpl jobService = new JobServiceImpl();
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        //1
//        jobService.createJobTable();
        //2
//        jobService.addJob(new Job("jar povar", "povar", "tamak kylat", 7));
//        jobService.addJob(new Job("ar povar", "povr", "tamak kylat", 2));
        //3
//        System.out.println(jobService.getJobById(2L));
        //4
//        System.out.println("Введите asc or desc");
//        System.out.println(jobService.sortByExperience(new Scanner(System.in).nextLine()));
        //5

        //6
//        jobService.deleteDescriptionColumn();
        //7
//        employeeService.createEmployee();
        //8
//        employeeService.addEmployee(new Employee("nurgazy", "temiraliev", 20, "nur@gmail.com", 1));
//        employeeService.addEmployee(new Employee("nurbolot", "temiraliev", 20, "nur@gmail.com", 1));
        //9
//        employeeService.dropTable();
        //10
//          employeeService.cleanTable();
        //11
//        employeeService.updateEmployee(1L, new Employee("nurbolot","temiraliev", 20, "nur@gmail.com", 1));
        //12
//        System.out.println(employeeService.getAllEmployees());
        //13
//        System.out.println(employeeService.findByEmail("nur@gmail.com"));
        //14
//        System.out.println(employeeService.getEmployeeById(1L));
        //15
//        System.out.println(employeeService.getEmployeeByPosition("ar povar"));


    }
}
