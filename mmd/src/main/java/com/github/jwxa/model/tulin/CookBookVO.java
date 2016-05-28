package com.github.jwxa.model.tulin;

import com.github.jwxa.util.BaiduApiUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Random;

/**
 * 标题<br>
 * User: Jwxa<br>
 * Date: 2016/5/28.
 */
@Getter
@Setter
public class CookBookVO extends TulinRespVO {

    private List<CookBookDetailVO> list;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(super.getText()).append(":\n");
        Random r = new Random();
        int num  = r.nextInt(list.size());
        CookBookDetailVO cookBookDetailVO = list.get(num);
        sb.append("菜名：").append(cookBookDetailVO.getName()).append("\n")
                .append("菜谱：").append(cookBookDetailVO.getInfo()).append("\n")
                .append("详情链接：").append(BaiduApiUtil.transShortUrlFromDwz(cookBookDetailVO.getDetailurl()));
        return sb.toString();
    }
}
