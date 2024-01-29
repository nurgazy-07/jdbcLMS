package java12.dao.impl;

import java12.convig.jdbcConfig;
import java12.dao.EmployeeDao;
import java12.models.Employee;
import java12.models.Job;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class EmploeyyDaoImpl implements EmployeeDao {
  Connection connection = jdbcConfig.getConnection();
    @Override
    public void createEmployee() {
      String query = """
              create table employees(
              id serial primary key ,
              first_name varchar,
              last_name varchar,
              age int,
              email varchar,
              job_id int references jobs(id))
              """;
      try {
       Statement statement = connection.createStatement();
        statement.executeUpdate(query);
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
    }

    @Override
    public void addEmployee(Employee employee) {
      String query = """
              insert into employees(first_name, last_name, age, email, job_id)
              values(?, ?, ?, ?, ?);
              """;
      try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
        preparedStatement.setString(1, employee.getFirstName());
        preparedStatement.setString(2, employee.getLastName());
        preparedStatement.setInt(3, employee.getAge());
        preparedStatement.setString(4, employee.getEmail());
        preparedStatement.setInt(5, employee.getJobId());
        preparedStatement.executeUpdate();

      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
    }

    @Override
    public void dropTable() {
       String query = "drop table employees;";
      try(Statement statement = connection.createStatement()) {
        statement.executeUpdate(query);
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }

    }

    @Override
    public void cleanTable() {
      String query = "alter table employees drop column id, drop column first_name, drop column last_name, drop column age, drop column email, drop column job_id";
      try (Statement statement = connection.createStatement()){
           statement.executeUpdate(query);
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
    }

    @Override
    public void updateEmployee(Long id, Employee employee) {
      String query = """
              update employees set first_name = ?,
              last_name = ?,
              age = ?,
              email = ?,
              job_id = ?
              where id = ?
              """;
      try {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, employee.getFirstName());
        preparedStatement.setString(2, employee.getLastName());
        preparedStatement.setInt(3, employee.getAge());
        preparedStatement.setString(4, employee.getEmail());
        preparedStatement.setInt(5, employee.getJobId());
        preparedStatement.setLong(6, id);
        preparedStatement.executeUpdate();
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
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
