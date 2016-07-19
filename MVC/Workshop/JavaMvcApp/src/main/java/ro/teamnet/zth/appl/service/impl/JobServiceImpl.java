package ro.teamnet.zth.appl.service.impl;

import ro.teamnet.zth.api.annotations.MyService;
import ro.teamnet.zth.appl.dao.JobDao;
import ro.teamnet.zth.appl.domain.Job;
import ro.teamnet.zth.appl.service.JobService;

import java.util.List;

/**
 * Created by cizuss94 on 7/16/2016.
 */
@MyService
public class JobServiceImpl implements JobService {
    @Override
    public List<Job> findAllJobs() {
        return new JobDao().getAllJobs();
    }

    @Override
    public Job findOneJob(String id) {
        return new JobDao().getJobById(id);
    }

    @Override
    public void deleteOneJob(String jobId) {
        new JobDao().deleteJob(new JobDao().getJobById(jobId));
    }

    @Override
    public Job saveOneJob(Job newJob) {
        /*Job newJob = new Job();
        newJob.setId(jobId);
        newJob.setJobTitle(jobTitle);
        newJob.setMinSalary(minSalary);
        newJob.setMaxSalary(maxSalary);*/
        return new JobDao().insertJob(newJob);
    }

    @Override
    public Job editOneJob(Job newJob) {
        return new JobDao().updateJob(newJob);
    }
}
