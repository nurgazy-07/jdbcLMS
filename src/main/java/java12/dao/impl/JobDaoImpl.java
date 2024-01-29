package java12.dao.impl;

import java12.convig.jdbcConfig;
import java12.dao.JobDao;
import java12.models.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDaoImpl implements JobDao {
    Connection connection = jdbcConfig.getConnection();

    @Override
    public void createJobTable() {
        String query = """
                create table jobs(
                id serial primary key,
                position varchar,
                profession varchar,
                description varchar,
                experience int);
                """;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void addJob(Job job) {
        String query = """
                insert into jobs(position, profession, description, experience)
                values(?, ?, ?, ?);
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, job.getPosition());
            preparedStatement.setString(2, job.getProfession());
            preparedStatement.setString(3, job.getDescription());
            preparedStatement.setInt(4, job.getExperience());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Job getJobById(Long jobId) {
        String query = "select * from jobs where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, jobId);
            return getJobs(preparedStatement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
        List<Job> jobs = new ArrayList<>();
        if (ascOrDesc.equals("asc") || ascOrDesc.equals("desc")) {
            String query = "select * from jobs order by experience " + ascOrDesc + ";";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Job job = new Job();
                    job.setId(resultSet.getLong("id"));
                    job.setPosition(resultSet.getString("position"));
                    job.setProfession(resultSet.getString("profession"));
                    job.setDescription(resultSet.getString("description"));
                    job.setExperience(resultSet.getInt("experience"));
                    jobs.add(job);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Неверный порядок сортировки");
        }
        return jobs;
    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        String query = "select * from jobs where employee = ?";
        return null;
    }

    @Override
    public void deleteDescriptionColumn() {
      String query = "alter table jobs drop column description;";
        try {
          Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    private Job getJobs(PreparedStatement preparedStatement) throws SQLException {
        Job job = new Job();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            job.setId(resultSet.getLong("id"));
            job.setPosition(resultSet.getString("position"));
            job.setProfession(resultSet.getString("profession"));
            job.setDescription(resultSet.getString("description"));
            job.setExperience(resultSet.getInt("experience"));
        }
        return job;
    }
}
