package cc.fbksy.edu.springBoot.activit.controller.vo;

import lombok.Data;

import java.util.Map;

@Data
public class StartWorkFlowVo {

    private String workFlowKey;

    private String businessKey;

    private Map<String,Object> reqParam;
}
