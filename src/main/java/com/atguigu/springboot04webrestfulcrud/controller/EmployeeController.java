package com.atguigu.springboot04webrestfulcrud.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.atguigu.springboot04webrestfulcrud.dao.DepartmentDao;
import com.atguigu.springboot04webrestfulcrud.dao.EmployeeDao;
import com.atguigu.springboot04webrestfulcrud.entities.Department;
import com.atguigu.springboot04webrestfulcrud.entities.Employee;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeDao employeeDao;
	@Autowired
	DepartmentDao departmentDao;

	// 查询所有员工列表页面
	@GetMapping("/emps")
	public String list(Model model) {
		Collection<Employee> employees = employeeDao.getAll();

		// 放在请求域中
		model.addAttribute("emps", employees);
		// thymeleaf默认就好拼串
		// classpath:/templates/xxxx.html
		return "emp/list";
	}

	// 来到员工添加页面
	@GetMapping("/emp")
	public String toAddPage(Model model) {
		// 来到添加页面，查出所有的部门，在页面显示
		Collection<Department> departments = departmentDao.getDepartments();
		model.addAttribute("depts", departments);
		// 来到添加页面
		return "emp/add";
	}

	// 员工添加
	// SpringMVC自动将请求参数和入参对象的属性进行--绑定;要求了请求参数的名字和javaBean入参的对象里面的属性名是一样的
	@PostMapping("/emp")
	public String addEmp(Employee employee) {
		// 来到员工列表页面
		System.out.println("保存员工的信息" + employee);
		//保存员工
		employeeDao.save(employee);
		// redirect:表示重定向到一个地址
		// forward:表示转发到一个地址
		return "redirect:/emps";
	}
	
	//来到修改页面,查出当前员工，在页面回显
	@GetMapping("/emp/{id}")
	public String toEditPage(@PathVariable("id")Integer id,Model model) {
	Employee employee=	employeeDao.get(id);
		model.addAttribute("emp",employee);
		//回到修改页面(add是一个修改添加二合一的页面)
		System.err.print("employee-->"+employee);
		return "emp/add";
	}
}
