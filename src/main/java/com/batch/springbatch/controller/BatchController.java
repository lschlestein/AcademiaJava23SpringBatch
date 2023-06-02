package com.batch.springbatch.controller;

import com.batch.springbatch.model.Pessoa;
import com.batch.springbatch.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
@AllArgsConstructor
public class BatchController {
    private JobLauncher jobLauncher;
    private Job job;
    private PessoaRepository pessoaRepository;//

    @PostMapping("/importPessoas")
    public void importCsvToDBJob() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("batchIniciadoAs", System.currentTimeMillis()).toJobParameters();

        try {
            jobLauncher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | RuntimeException |
                 JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public Pessoa buscarPessoaId(@PathVariable ("id") Long id){
        return pessoaRepository.findById(id).get();
    }
    @GetMapping("/listar")
    public List<Pessoa> buscarPessoaId(){
        return pessoaRepository.findAll();
    }

    @PostMapping("/cadastrar")
    public Pessoa inserirPessoa(@RequestBody Pessoa pessoa){
        pessoaRepository.save(pessoa);
        return pessoa;
    }

}

