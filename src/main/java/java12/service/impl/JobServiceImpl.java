package java12.service.impl;

import java12.dao.impl.JobDaoImpl;
import java12.models.Job;
import java12.service.JobService;

import java.util.List;

public class JobServiceImpl implements JobService {
    JobDaoImpl jobDao = new JobDaoImpl();
    @Override
    public void createJobTable() {
         jobDao.createJobTable();
    }

    @Override
    public void addJob(Job job) {
    jobDao.addJob(job);
    }

    @Override
    public Job getJobById(Long jobId) {
        return jobDao.getJobById(jobId);
    }

    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
       return jobDao.sortByExperience(ascOrDesc);
    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        return null;
    }

    @Override
    public void deleteDescriptionColumn() {
     jobDao.deleteDescriptionColumn();
    }
}
