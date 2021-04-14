package cc.fbksy.edu.springBoot.activit;

import cc.fbksy.edu.springBoot.activit.vo.StartWorkFlowVo;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivitController {

    @Autowired
    private RuntimeService runtimeService;

    @PostMapping("/act/startWorkFlow")
    public Object startWorkFlow(@RequestBody StartWorkFlowVo workFlowKey){

        ProcessInstance res = runtimeService.startProcessInstanceByKey(workFlowKey.getWorkFlowKey(),workFlowKey.getReqParam());
        return "SUCCESS";
    }

}
