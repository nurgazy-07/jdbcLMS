package java12.dao.impl;

import java12.convig.jdbcConfig;
import java12.dao.EmployeeDao;
import java12.models.Employee;
import java12.models.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void cleanTable() {
        String query = "alter table employees drop column id, drop column first_name, drop column last_name, drop column age, drop column email, drop column job_id";
        try (Statement statement = connection.createStatement()) {
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
        List<Employee> employees = new ArrayList<>();
        String query = "select * from employees";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setAge(resultSet.getInt("age"));
                employee.setEmail(resultSet.getString("email"));
                employee.setJobId(resultSet.getInt("job_id"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employees;
    }

    @Override
    public Employee findByEmail(String email) {
        Employee employee = new Employee();
        String query = "select * from employees where email = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employee.setId(resultSet.getLong("id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setAge(resultSet.getInt("age"));
                employee.setEmail(resultSet.getString("email"));
                employee.setJobId(resultSet.getInt("job_id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employee;
    }

    @Override
    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        Map<Employee, Job> employeeJobMap = new HashMap<>();
        String query = """
                  select * from employees e join jobs j on e.job_id = j.id where e.id = ?;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, employeeId
            );
            try (ResultSet resultSet = preparedStatement.
                    executeQuery()) {
                if (resultSet.next()) {
                    Employee employee = new Employee();
                    Job job = new Job();
                    employee.setId(resultSet.getLong("id"));
                    employee.setFirstName(resultSet.getString("first_name"));
                    employee.setLastName(resultSet.getString("last_name"));
                    employee.setAge(resultSet.getInt("age"));
                    employee.setEmail(resultSet.getString("email"));
                    employee.setJobId(resultSet.getInt("job_id"));
                    job.setId(resultSet.getLong("job_id"));
                    job.setPosition(resultSet.getString("position"));
                    job.setProfession(resultSet.getString("profession"));
                    job.setDescription(resultSet.getString("description"));
                    job.setExperience(resultSet.getInt("experience"));
                    employeeJobMap.put(employee, job);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return employeeJobMap;
    }

    @Override
    public List<Employee> getEmployeeByPosition(String position) {
      List<Employee> employees = new ArrayList<>();
      String query = """
    SELECT * FROM employees e INNER JOIN jobs j ON e.job_id = j.id WHERE j.position = ?;
""";
      try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setString(1, position);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
          while (resultSet.next()) {
            Employee employee = new Employee();  // Перемещено внутрь цикла
            employee.setId(resultSet.getLong("id"));
            employee.setFirstName(resultSet.getString("first_name"));
            employee.setLastName(resultSet.getString("last_name"));
            employee.setAge(resultSet.getInt("age"));
            employee.setEmail(resultSet.getString("email"));
            employee.setJobId(resultSet.getInt("job_id"));
            employees.add(employee);
          }
        }
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
      return employees;
    }
}
