package com.github.jwxa.model.tulin;

import com.github.jwxa.util.BaiduApiUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Random;

/**
 * 新闻响应实体类<br>
 * User: Jwxa<br>
 * Date: 2016/5/28.
 */
@Getter
@Setter
public class NewsRespVO extends TulinRespVO {

    List<NewsDetailRespVO> list;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(super.getText()).append(":\n");
        Random r = new Random();
        int num  = r.nextInt(list.size());
        NewsDetailRespVO detailRespVO = list.get(num);
        if (StringUtils.isNotBlank(detailRespVO.getArticle()))
            sb.append("标题：").append(detailRespVO.getArticle()).append("\n");
        sb.append("来源：").append(detailRespVO.getSource()).append("\n")
                .append("链接：").append(BaiduApiUtil.transShortUrlFromDwz(detailRespVO.getDetailurl())).append("\n\n");
        return sb.toString();
    }
}
