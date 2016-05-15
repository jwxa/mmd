package com.github.jwxa.service.impl;

import com.github.jwxa.service.IWorkflowService;
import com.github.jwxa.dao.IIntroduceDao;
import com.github.jwxa.model.IntroduceVO;
import com.github.jwxa.model.PageVO;
import com.github.jwxa.model.UserInfo;
import com.github.jwxa.service.IIntroduceService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jwxa on 2015/2/9.
 * 添加软件简介Service层
 */
@Slf4j
@Service
public class IntroduceServiceImpl implements IIntroduceService {
    private static final String BPMN_PATH = "diagrams/introduce.bpmn";
    private static final String  PROCESS_NAME = "添加软件简介审核流程";
    private static final String PROCESS_DEFINITION_KEY="introduceProcess";
    @Resource
    private IIntroduceDao introduceDao;

    @Resource
    private IWorkflowService workflowService;


    @Transactional(rollbackFor = RuntimeException.class)
    public Map addIntroduce(IntroduceVO introduceVO) {
        int infoNum =0;
        int detailNum = 0;
        Map map =new HashMap();
        try {
            infoNum = introduceDao.addIntroduceInfo(introduceVO);
            detailNum = introduceDao.addIntroduceDetail(introduceVO);
            map.put("infoNum",infoNum);
            map.put("detailNum",detailNum);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new RuntimeException("插入软件简介信息出错");
        }

        return map;
    }

    public List<IntroduceVO> queryIntroduceByUserId(UserInfo userInfo, String statusCode) {
        List<IntroduceVO> introduceVOList = null;
        try {
            introduceVOList = introduceDao.queryIntroduceByUserId(userInfo, statusCode);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return introduceVOList;
    }

    public List<IntroduceVO> queryIntroduceByUserIdAndPage(UserInfo userInfo, String statusCode, PageVO pageVO) {
        List<IntroduceVO> introduceVOList = null;
        try {
            introduceVOList = introduceDao.queryIntroduceByUserIdAndPage(userInfo, statusCode,pageVO);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return introduceVOList;
    }

    public int queryIntroduceCountByUserId(UserInfo userInfo, String statusCode) {
        return introduceDao.queryIntroduceCountByUserId(userInfo,statusCode);
    }

    public IntroduceVO queryIntroduceByUuid(String uuid) {
        IntroduceVO introduceVO = null;
        try {
            introduceVO = introduceDao.queryIntroduceByUuid(uuid);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return introduceVO;
    }

    public String submitToAuditIntroduce(IntroduceVO introduceVO) {
        String resultFlag = "failed";
        //1.工作流开始
        workflowService.deploymentProcessDefinition(BPMN_PATH,PROCESS_NAME);
        Map<String,Object> variables = new HashMap<String, Object>();
        variables.put("userID",introduceVO.getAuthorId());//作者ID作为参数
        ProcessInstance pi = workflowService.startProcessInstance(PROCESS_DEFINITION_KEY, variables);
        log.info("启动流程实例,流程定义id:" + pi.getProcessDefinitionId());

        return resultFlag;
    }


}


