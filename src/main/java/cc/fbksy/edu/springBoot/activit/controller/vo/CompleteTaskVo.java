package cc.fbksy.edu.springBoot.activit.controller.vo;

import lombok.Data;

import java.util.Map;

@Data
public class CompleteTaskVo {

    private String taskId;

    protected String processInstanceId;

    private String userId;

    /**
     * 流程参数
     */
    private Map<String, Object> variables;

}
