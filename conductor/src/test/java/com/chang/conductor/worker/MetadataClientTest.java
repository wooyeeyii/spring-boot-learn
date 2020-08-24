package com.chang.conductor.worker;

import com.netflix.conductor.client.http.MetadataClient;
import com.netflix.conductor.client.http.WorkflowClient;
import com.netflix.conductor.common.metadata.tasks.TaskDef;
import com.netflix.conductor.common.metadata.workflow.StartWorkflowRequest;
import com.netflix.conductor.common.metadata.workflow.WorkflowDef;
import com.netflix.conductor.common.metadata.workflow.WorkflowTask;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MetadataClientTest {

    @Test
    public void metadataClientRegisterTest() {
        MetadataClient client = new MetadataClient();
        client.setRootURI("http://localhost:8080/api/");

        List<TaskDef> taskDefs = new ArrayList<>();
        TaskDef task1 = new TaskDef("task_1", "description of task_1", "task_1@wx.com",
                3, 600, 600);
        taskDefs.add(task1);
        client.registerTaskDefs(taskDefs);


        WorkflowDef workflowDef = new WorkflowDef();
        workflowDef.setName("workflow_1");
        workflowDef.setOwnerEmail("workflow_1@wx.com");

        List<WorkflowTask> workflowTasks = new ArrayList<>();
        WorkflowTask workflowTask = new WorkflowTask();
        workflowTask.setName("task_1");
        workflowTask.setTaskReferenceName("step_1_task_1");
        workflowTasks.add(workflowTask);

        workflowDef.setTasks(workflowTasks);

        client.registerWorkflowDef(workflowDef);
    }

    @Test
    public void workFlowRunTest() {
        WorkflowClient client = new WorkflowClient();
        client.setRootURI("http://localhost:8080/api/");

        StartWorkflowRequest request = new StartWorkflowRequest();
        request.setName("workflow_1");
        request.setVersion(1);
        client.startWorkflow(request);
    }

}
