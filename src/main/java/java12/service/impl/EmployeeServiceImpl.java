package java12.service.impl;

import java12.dao.EmployeeDao;
import java12.dao.impl.EmploeyyDaoImpl;
import java12.models.Employee;
import java12.models.Job;

import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements EmployeeDao {
    EmploeyyDaoImpl emploeyyDao = new EmploeyyDaoImpl();

    @Override
    public void createEmployee() {
        emploeyyDao.createEmployee();
    }

    @Override
    public void addEmployee(Employee employee) {
      emploeyyDao.addEmployee(employee);
    }

    @Override
    public void dropTable() {
       emploeyyDao.dropTable();
    }

    @Override
    public void cleanTable() {
      emploeyyDao.cleanTable();
    }

    @Override
    public void updateEmployee(Long id, Employee employee) {
     emploeyyDao.updateEmployee(id, employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return null;
    }

    @Override
    public Employee findByEmail(String email) {
        return null;
    }

    @Override
    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        return null;
    }

    @Override
    public List<Employee> getEmployeeByPosition(String position) {
        return null;
    }
}
