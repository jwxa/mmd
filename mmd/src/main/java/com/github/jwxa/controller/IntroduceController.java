package com.github.jwxa.controller;

import com.github.jwxa.service.IIntroduceService;
import com.github.jwxa.util.CommonConstant;
import com.github.jwxa.interceptor.annotation.SystemControllerLog;
import com.github.jwxa.model.IntroduceVO;
import com.github.jwxa.model.PageVO;
import com.github.jwxa.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 软件简介
 * Created by Jwxa on 2015/2/8.
 */
@Controller
@Slf4j
public class IntroduceController {
//    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(IntroduceController.class);

    @Resource
    private IIntroduceService introduceService;

    @RequestMapping("/introduce")
    public String showIntroduce(){
        return "introduce/introduce";
    }

    @RequestMapping("/submitIntroduce")
    @ResponseBody
    public Map<String,Object> submitIntroduce(IntroduceVO introduceVO,HttpServletRequest request){
        //提交软件简介
        HttpSession session = request.getSession();
        Map <String,Object> map = new HashMap<String, Object>();
        UserInfo userInfo = (UserInfo)session.getAttribute(CommonConstant.USER_INFO);
        //设置作者id
        introduceVO.setAuthorId(userInfo.getId());
        UUID randomUUID = UUID.randomUUID();
        introduceVO.setUuid(randomUUID.toString());
        //表示未审核
        introduceVO.setStatus(CommonConstant.ARTICLE_STATUS_CODE[0]);
        String resultFlag = "failed";
        try {
            Map introduceMap = introduceService.addIntroduce(introduceVO);
            if(introduceMap!=null&&introduceMap.get("infoNum")!=null&&introduceMap.get("detailNum")!=null){
                log.info("插入软件简介信息成功{}条,详情{}条",introduceMap.get("infoNum"),introduceMap.get("detailNum"));
                resultFlag = "success";
            }else{
                map.put("resultMsg","插入软件简介信息失败");
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            map.put("resultMsg",e.getMessage());
        }
        map.put("resultFlag",resultFlag);
        return map;
    }

    /**
     * 获取未提交审核的所有软件简介
     * @param request
     * @return
     */
    //TODO 由于是前台分页 如果数据十分大会导致效率低下 下面写一个AJAX分页的例子
    @RequestMapping("/myIntroduce")
    public String showMyIntroduce(HttpServletRequest request){
        String ip = request.getRemoteAddr();
        log.info("ip为{}的主机访问showMyIntroduce方法，获取未提交审核的所有软件简介",ip);
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo)session.getAttribute("user_info");
        List<IntroduceVO> introduceVOList = introduceService.queryIntroduceByUserId(userInfo, CommonConstant.ARTICLE_STATUS_CODE[0]);
        request.setAttribute("introduceVOList",introduceVOList);
        return "introduce/myIntroduce";
    }


    /**
     * 获取未提交审核的软件简介页面
     * @param request
     * @return
     */
    @RequestMapping("/myIntroduceAjaxTable")
    public String showMyIntroduceTable(HttpServletRequest request){
        String ip = request.getRemoteAddr();
        log.info("ip为{}的主机访问showMyIntroduceTable方法，获取未提交审核的软件简介页面",ip);
        return "introduce/myIntroduceAjaxTable";
    }

    /**
     * 通过AJAX获取未提交审核的软件简介
     * @param request
     * @return
     */
    @RequestMapping("/myIntroduceAjax")
    @ResponseBody
    @SystemControllerLog(description = "通过AJAX获取未提交审核的软件简介")
    public String showMyIntroduceAjax(HttpServletRequest request,String aoData,String test){
        System.out.println(aoData);
        System.out.println(test);
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo)session.getAttribute("user_info");
        JSONArray jsonarray = JSONArray.fromObject(aoData);
        String sEcho = null;
        int iDisplayStart = 0; // 起始索引
        int iDisplayLength = 0; // 页容量
        for (int i = 0; i < jsonarray.size(); i++) {
            JSONObject obj = (JSONObject) jsonarray.get(i);
            if (obj.get("name").equals("sEcho"))
                sEcho = obj.get("value").toString();

            if (obj.get("name").equals("iDisplayStart"))
                iDisplayStart = obj.getInt("value");

            if (obj.get("name").equals("iDisplayLength"))
                iDisplayLength = obj.getInt("value");
        }
        System.out.println("开始行数："+iDisplayStart+",页容量："+iDisplayLength);
        PageVO pageVO =new PageVO(iDisplayStart,iDisplayLength);
        List<IntroduceVO> introduceVOList = introduceService.queryIntroduceByUserIdAndPage(userInfo, CommonConstant.ARTICLE_STATUS_CODE[0],pageVO);
        List<String[]> lst = new ArrayList<String[]>();
        for (IntroduceVO i : introduceVOList) {
            String[] d = { i.getTitle(), i.getAuthor(), i.getStatusName(), i.getCreateTime(),
                    "<a><i class=\"fa fa-edit\">&nbsp;</i>修改</a>&nbsp;&nbsp;\n" +
                            "                                        <a href=\"#\" onclick=\"submitToAudit('" +
                            i.getUuid() +
                            "')\"><i class=\"fa fa-file\">&nbsp;</i>提交</a>&nbsp;&nbsp;\n" +
                            "                                        <a><i class=\"fa fa-trash-o\">&nbsp;</i>删除</a>" };
            lst.add(d);
        }
        int count = introduceService.queryIntroduceCountByUserId(userInfo,CommonConstant.ARTICLE_STATUS_CODE[0]);
        JSONObject getObj = new JSONObject();
        getObj.put("sEcho", sEcho);// 不知道这个值有什么用,有知道的请告知一下
        getObj.put("iTotalRecords", count);//数据源的总行数
        getObj.put("iTotalDisplayRecords",count);//显示的行数,这个要和上面写的一样 过滤之后，实际的行数。
        getObj.put("aaData", lst);//要以JSON格式返回//数组的数组，表格中的实际数据
        return getObj.toString();
    }


    /**
     * AJAX将提交审核的简介提交工作流审核
     * @param request
     * @param uuid
     * @return
     */
    @ResponseBody
    @RequestMapping("/submitToAuditIntroduce")
    public Map submitToAuditIntroduce(HttpServletRequest request,String uuid){
        String ip = request.getRemoteAddr();
        log.info("ip为{}的主机访问submitToAuditIntroduce方法，AJAX将提交审核的简介提交工作流审核",ip);
        Map<String,Object> map = new HashMap<String, Object>();
        String resultFlag = "failed";
        if(uuid==null||uuid.equals("")){
            map.put("resultFlag",resultFlag);
            map.put("resultMsg","传入参数不能为空");
            return map;
        }
        //1.通过uuid查找软件简介实体类
        IntroduceVO introduceVO = introduceService.queryIntroduceByUuid(uuid);
        //2.比较author_id是否和session中的一致
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo)session.getAttribute("user_info");
        if(introduceVO.getAuthorId()!=(userInfo.getId())){
            map.put("resultFlag",resultFlag);
            map.put("resultMsg","非创建用户无权限提交审核");
            return map;
        }else{
            //TODO TEST
            map.put("resultFlag",resultFlag);
            map.put("resultMsg","测试通过");
            return map;
            //TODO TEST END
        }

        //3.进入工作流审核逻辑
//        resultFlag = introduceService.submitToAuditIntroduce(introduceVO);
//        return null;
    }

}
