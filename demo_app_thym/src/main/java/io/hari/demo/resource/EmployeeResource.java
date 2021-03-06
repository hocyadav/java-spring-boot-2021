package io.hari.demo.resource;

import io.hari.demo.dao.EmployeeDao;
import io.hari.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

/**
 * @Author Hariom Yadav
 * @create 31-03-2021
 */
@Controller
public class EmployeeResource {
    public static final String OPEN_HOME_PAGE = "index2-view";
    public static final String OPEN_ADD_EMP_PAGE = "add_emp";
    public static final String REDIRECT_TO_HOME = "redirect:/" + OPEN_HOME_PAGE;

    @Autowired
    EmployeeDao employeeDao;

    @GetMapping("/")
    public String findAllEmployees(Model model) {
        final List<Employee> employees = employeeDao.findAll();
        System.out.println("employees = " + employees);
        model.addAttribute("employees", employees);
        model.addAttribute("name", "hariom");
        return "index";//return view name
    }
    @GetMapping("/hello")
    public String helloWorld(Model model) {
        model.addAttribute("hello", "hello world");
        return "hello";
    }

    @GetMapping("/hello2")
    public String helloWorldWithoutModel() {
        return "hello2";
    }

    @GetMapping("/style")
    public String style() {
        return "css-js-page";
    }

    @GetMapping("/bootstrap")
    public String bootstratTest() {
        return "bootstrap-test";
    }

    //show all emp data
    @GetMapping("/" + "index2")
    public String getAllEmployeesBy(Model model) {
        model.addAttribute("employees", employeeDao.findAll());
        return "index2-view";
    }

    @GetMapping("/controllerMethod1")
    public String saveEmployees1(Model model) {
        System.err.println("EmployeeResource.saveEmployees1");
        final Map<String, Object> stringObjectMap = model.asMap();
        System.out.println("stringObjectMap = " + stringObjectMap);
//        model.addAttribute("employees", employeeDao.findAll());//if we comment than model will not contain any key value
        return "index2-view";
    }

    //create a empty placeholder for saving new emp data
    @GetMapping("/controllerMethod2")
    public String saveEmployees2(Model model) {
        System.err.println("EmployeeResource.saveEmployees2");
        final Map<String, Object> stringObjectMap = model.asMap();
        System.out.println("stringObjectMap = " + stringObjectMap);//empty

        final Employee employee = new Employee();//empty object and send to front end to set value in its field
        model.addAttribute("employee", employee);
        return "add_emp";
    }

    //save new emp data from frontend
    @PostMapping("/controllerMethod3")//1. post to accept form data
    public String controllerMethod3(@ModelAttribute("employee") Employee employee) {//2. accept data from model attribute and type cast to employee
        System.err.println("EmployeeResource.controllerMethod3");
        System.out.println("employee = " + employee);

        employeeDao.save(employee);
//        return REDIRECT_TO + OPEN_HOME_PAGE;
        return "redirect:/" + "index2";//return to controller end point and that controller will return to html view
    }
}
