package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestParam;
import ro.teamnet.zth.appl.domain.Job;
import ro.teamnet.zth.appl.service.impl.JobServiceImpl;

import java.util.List;

/**
 * Created by cizuss94 on 7/15/2016.
=======

/**
 * Created by user on 7/14/2016.
>>>>>>> 855c1a6880e16f18104918fdd2e8cbca3602e0f4
 */
@MyController(urlPath = "/jobs")
public class JobController {
    @MyRequestMethod(urlPath = "/all")
    public List<Job> getAllJobs() {
        return new JobServiceImpl().findAllJobs();
    }
    @MyRequestMethod(urlPath = "/one")
    public Job getOneJob(@MyRequestParam(name = "id") String jobId) {
        return new JobServiceImpl().findOneJob(jobId);
    }
    @MyRequestMethod(urlPath = "/one", methodType = "DELETE")
    public void deleteOneJob(@MyRequestParam(name = "id") String jobId) {
        new JobServiceImpl().deleteOneJob(jobId);
    }
    @MyRequestMethod(urlPath = "/one", methodType = "POST")
    public Job saveOneJob(Job newJob)
    {
        return new JobServiceImpl().saveOneJob(newJob);
    }
    @MyRequestMethod(urlPath = "/one", methodType = "PUT")
    public Job editOneJob(Job newJob) {
        return new JobServiceImpl().editOneJob(newJob);
    }
}
