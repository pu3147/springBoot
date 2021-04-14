package cc.fbksy.edu.springBoot.activit.vo;

import lombok.Data;

import java.util.Map;

@Data
public class StartWorkFlowVo {
    private String workFlowKey;
    private Map<String,Object> reqParam;
}
