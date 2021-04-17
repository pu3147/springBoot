package cc.fbksy.edu.springBoot.activit.manager.listener;

import cc.fbksy.edu.springBoot.util.ApplicationContextUtil;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.task.Task;

public class EduTaskListener  implements TaskListener {

    private static  TaskService taskService;

    private static RuntimeService runtimeService ;

    private TaskService getTaskService(){
        if(null == taskService){
            taskService = ApplicationContextUtil.getClassBean(TaskService.class);
            runtimeService = ApplicationContextUtil.getClassBean(RuntimeService.class);
        }

        return  taskService;
    }


    @Override
    public void notify(DelegateTask delegateTask) {
        TaskService taskService = getTaskService();

        String eventName = delegateTask.getEventName();


        //获取流程id
        String exId = delegateTask.getExecutionId();
        String taskId = delegateTask.getId();

        // Sid delegateTask.getTaskDefinitionKey()
        // FlowId delegateTask.getExecution().getProcessDefinitionId()

        if("create".equals(eventName)) {
            taskService.addCandidateUser(taskId,"userId");
            taskService.addCandidateGroup(taskId,"groupCode");
            taskService.setAssignee(taskId,"userId");
        }

        if("comple".equals(eventName)) {
            boolean pass = runtimeService.getVariable(exId, "pass", Boolean.class);
            if (pass) {
                String processInstanceId = delegateTask.getProcessInstanceId();

                //下个任务
                Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
            }

            Integer nrOfInstances = runtimeService.getVariable(exId, "nrOfInstances", Integer.class);
            Integer nrOfCompletedInstances = runtimeService.getVariable(exId, "nrOfCompletedInstances", Integer.class);
            Integer nrOfActiviteInstances = runtimeService.getVariable(exId, "nrOfActiviteInstances", Integer.class);
        }

        /*
        taskService.addCandidateUser(delegateTask.getId(),"1");
        taskService.addCandidateUser(delegateTask.getId(),"2");
        taskService.addCandidateUser(delegateTask.getId(),"3");
        taskService.addCandidateUser(delegateTask.getId(),"4");
        */

        System.out.printf("addCandidateUser:TaskID="+delegateTask.getId()+",processInstanceId="+delegateTask.getProcessInstanceId());
    }
}
