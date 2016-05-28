package com.github.jwxa.model.tulin;

/**
 * 标题<br>
 * User: Jwxa<br>
 * Date: 2016/5/28.
 */
public enum RespCode {
    Text("100000",TextRespVO.class),
    Url("200000",UrlRespVO.class),
    News("302000",NewsRespVO.class),
    CookBook("308000",CookBookVO.class)
    ;

    private String code;

    private Class clazz;

    RespCode(String code, Class clazz) {
        this.code = code;
        this.clazz = clazz;
    }

    public static Class getClass(String code){
        if(code!=null){
            for(RespCode respCode: RespCode.values()){
                if(respCode.code.equals(code)){
                    return respCode.clazz;
                }
            }
        }
        return null;
    }

}
