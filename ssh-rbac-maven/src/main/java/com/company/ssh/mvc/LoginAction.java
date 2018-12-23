package com.company.ssh.mvc;

import com.company.ssh.domain.Employee;
import com.company.ssh.service.IEmployeeService;

public class LoginAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    private String name;
    private String password;
    private IEmployeeService employeeService ;

    @Override
    public String execute() {
        Employee emp = employeeService.login(name, password);
        System.out.println(emp);
        if (emp == null) {
            return LOGIN;
        }
        return SUCCESS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmployeeService(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public IEmployeeService getEmployeeService() {
        return employeeService;
    }
}
