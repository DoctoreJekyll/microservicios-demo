package org.generations.playerservices.service;



import org.generations.playerservices.dto.JobDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "job-services")
public interface JobClient {
    @GetMapping("/api/jobs")
    List<JobDTO> getJobs();

    @GetMapping("/api/jobs/{id}")
    JobDTO findById(@PathVariable("id") Integer id);
}
