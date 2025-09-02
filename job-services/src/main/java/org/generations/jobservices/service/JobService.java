package org.generations.jobservices.service;

import org.generations.jobservices.dto.JobDTO;
import org.generations.jobservices.mapper.JobMapper;
import org.generations.jobservices.repository.JobRepository;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    public JobService(JobRepository jobRepository, JobMapper jobMapper) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
    }


    public List<JobDTO> findAllJobs() {
        return jobRepository.findAll()
                .stream()
                .map(jobMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    public JobDTO findById(Integer id) {
        return jobMapper.
                mapToDTO(jobRepository.findById(id)
                        .orElseThrow(
                                () -> new ExpressionException("Job not found with id " + id)));
    }

    public JobDTO save(JobDTO jobDTO) {
        return jobMapper.mapToDTO(jobRepository.save(jobMapper.mapToEntity(jobDTO)));
    }

    public void deleteById(Integer id) {
        jobRepository.deleteById(id);
    }
}
