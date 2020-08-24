package com.chang.conductor.worker;

import com.netflix.conductor.client.automator.TaskRunnerConfigurer;
import com.netflix.conductor.client.http.TaskClient;
import com.netflix.conductor.client.worker.Worker;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class FileExportTest {

    @Test
    public void fileExportTest() {
        TaskClient taskClient = new TaskClient();
        taskClient.setRootURI("http://localhost:8080/api/");        //Point this to the server API

        int threadCount = 2;            //number of threads used to execute workers.  To avoid starvation, should be same or more than number of workers

        Worker worker1 = new FileExportWorker("task_1");

        // Create TaskRunnerConfigurer
        TaskRunnerConfigurer configurer = new TaskRunnerConfigurer.Builder(taskClient, Arrays.asList(worker1))
                .withThreadCount(threadCount)
                .build();

        // Start the polling and execution of tasks
        configurer.init();

        // 让线程执行的任务完成
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
