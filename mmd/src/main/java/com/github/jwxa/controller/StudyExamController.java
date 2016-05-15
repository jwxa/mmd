package com.github.jwxa.controller;

import com.github.jwxa.interceptor.annotation.SystemControllerLog;
import com.github.jwxa.model.QuestionVO;
import com.github.jwxa.service.IStudyExamService;
import com.github.jwxa.util.CommonConstant;
import com.github.jwxa.model.AliasVO;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 五十音图 学习测试 Controller层
 * Created by Jwxa on 2015/6/22.
 */
@Controller
@Slf4j
@RequestMapping("/study")
public class StudyExamController {

    @Resource
    private IStudyExamService studyExamService;

    private static final double questionRate = 2.0/5.0;
    /**
     * 展示五十音图
     * @return
     */
    @SystemControllerLog(description = "展示五十音图")
    @RequestMapping("alphabet")
    public ModelAndView showJapaneseAlphabetPage(){
        ModelAndView mv = new ModelAndView("baseJapaneseAlphabet");
        Multimap<String, AliasVO> alphabetMap = studyExamService.queryAliaListsByKind(null);
        mv.addObject("hiraganaList",alphabetMap.get(CommonConstant.ALPHABET_ALIAS_KIND[0]));
        mv.addObject("kataganaList",alphabetMap.get(CommonConstant.ALPHABET_ALIAS_KIND[1]));
        return mv;
    }


    @RequestMapping("selectExamLevel")
    public ModelAndView showSelectExamLevelPage(HttpServletRequest request){
        String ip = request.getRemoteAddr();
        log.info("ip为{}的主机访问showSelectExamLevelPage方法，选择测试内容页面",ip);
        ModelAndView mv = new ModelAndView("selectExamLevelPage");
        mv.addObject("levelOptionMap",CommonConstant.ALPHABET_ALIAS_KIND_MAP);
        return mv;
    }
    /**
     * 创建基础考题
     * @param aliasType 假名类型
     * @param createType 创建类型
     * @param request 请求
     * @return
     */
    @SystemControllerLog(description = "显示基础测试题目页面")
    @RequestMapping("baseExam")
    @ResponseBody
    public ModelAndView showBaseExamPage(String aliasType,String createType,HttpServletRequest request){
        String path =request.getContextPath();
        HttpSession session = request.getSession();
        String sessionKey = "study_base_question|" +aliasType+"|" + createType;
        ModelAndView mv = new ModelAndView("study/baseExam");
        List<QuestionVO> questionVOList = (List<QuestionVO>) session.getAttribute(sessionKey);
        if(questionVOList==null){
            //从缓存获取五十音图
            Multimap<String, AliasVO> alphabetMap = studyExamService.queryAliaListsByKind(null);
            List<AliasVO> testAliasList = Lists.newArrayList();
            if (CommonConstant.ALPHABET_ALIAS_KIND[2].equals(aliasType)) {
                testAliasList.addAll(alphabetMap.values());
            }else if(!Arrays.asList(CommonConstant.ALPHABET_ALIAS_KIND).contains(aliasType)){
                //如果没有这种类型
                testAliasList = Lists.newArrayList();
            } else {
                testAliasList = (List<AliasVO>) alphabetMap.get(aliasType);
            }
            //测试题目数量为20
            int questionNum = 20;//(int) (testAliasList.size() * questionRate);
            questionVOList = studyExamService.createQuestionByAliasList(testAliasList, createType, questionNum);

            session.setAttribute(sessionKey, questionVOList);
        }
        mv.addObject("questionVOList",questionVOList);
        mv.addObject("aliasType",aliasType);
        mv.addObject("createType",createType);
        mv.addObject("path",path);
        return mv;
    }

    /**
     * 清理题目缓存
     * @param aliasType
     * @param createType
     * @param request
     * @return
     */
    @SystemControllerLog(description = "清理题目缓存")
    @RequestMapping("clearExamQuestionSession")
    @ResponseBody
    public Map clearExamQuestionSession(String aliasType,String createType,HttpServletRequest request){
        HttpSession session = request.getSession();
        String sessionKey = "study_base_question|" +aliasType+"|" + createType;
        Map<String,String> map = Maps.newHashMap();
        session.removeAttribute(sessionKey);
        map.put(sessionKey, "true");
        return map;
    }

    @SystemControllerLog(description = "查看答案")
    @RequestMapping("showBaseExamAnswer")
    @ResponseBody
    public Map showBaseExamAnswer(String aliasType,String createType,String answers ,HttpServletRequest request){
        HttpSession session = request.getSession();
        String sessionKey = "study_base_question|" +aliasType+"|" + createType;
        log.info("难度:[{}]，答案：[{}]",sessionKey,answers);
        //获取原题目
        List<QuestionVO> questionVOList = (List<QuestionVO>) session.getAttribute(sessionKey);
        Map<String,Object> map = Maps.newHashMap();
        int score = 0;
        Iterable<String> split = Splitter.on(",").omitEmptyStrings().trimResults().split(answers);
        Iterator it = split.iterator();
        int index = 0;
        List<Integer> errorOptionSeq = Lists.newArrayList();
        while(it.hasNext()){
            String answer = (String) it.next();
            QuestionVO questionVO = questionVOList.get(index);
            questionVO.setAnswerSeq(Integer.valueOf(answer));
            if(questionVO.cmpAnswer()){
                score = score + 5;
            }else{
                errorOptionSeq.add(index);
            }
            index++;
        }
        map.put("score",score);
        map.put("errorOptionSeq",errorOptionSeq);
        map.put("questionVOList",questionVOList);
        //清理session中该难度的题目
        session.removeAttribute(sessionKey);
        return map;
    }


}
