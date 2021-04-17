package cc.fbksy.edu.springBoot.activit.controller;

import cc.fbksy.edu.springBoot.activit.controller.vo.CompleteTaskVo;
import cc.fbksy.edu.springBoot.activit.controller.vo.StartWorkFlowVo;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ActivitController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;


    @PostMapping("/act/startWorkFlow")
    public Object startWorkFlow(@RequestBody StartWorkFlowVo workFlowKey){

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(workFlowKey.getWorkFlowKey(),workFlowKey.getBusinessKey(),workFlowKey.getReqParam());

        System.out.println("流程部署的ID："+processInstance.getDeploymentId());
        System.out.println("流程定义的ID："+processInstance.getProcessDefinitionId());
        System.out.println("流程实例的ID："+processInstance.getProcessInstanceId());

        return "SUCCESS";
    }

    @PostMapping("/act/completeTask")
    public Object startWorkFlow(@RequestBody CompleteTaskVo completeTaskVo){

        //设置当前任务的处理人
        taskService.setAssignee(completeTaskVo.getTaskId(),completeTaskVo.getUserId());

        //完成任务
        taskService.complete(completeTaskVo.getTaskId(),completeTaskVo.getVariables());

        return "SUCCESS";
    }

    @PostMapping("/act/queryMYTask")
    public Object queryMYTask(@RequestBody CompleteTaskVo completeTaskVo){

        //查询条件,办理人或委托人
        List<Task> list = taskService.createTaskQuery().taskCandidateOrAssigned(completeTaskVo.getUserId()).list();

        //查询条件,s委托人组
        List<Task> list2 = taskService.createTaskQuery().taskCandidateGroupIn(Arrays.asList()).list();

        return "SUCCESS";
    }

    @PostMapping("/act/myTaskDetail")
    public Object myTaskDetail(@RequestBody CompleteTaskVo completeTaskVo){

        //查询条件,办理人或委托人
        Task task = taskService.createTaskQuery().taskId(completeTaskVo.getTaskId()).singleResult();

        task.getTaskLocalVariables();

        return "SUCCESS";
    }

    @PostMapping("/act/queryHistoryTask")
    public Object queryHistoryTask(@RequestBody CompleteTaskVo completeTaskVo){

        //查询条件,办理人
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(completeTaskVo.getUserId())
                .list();

        return "SUCCESS";
    }

}
