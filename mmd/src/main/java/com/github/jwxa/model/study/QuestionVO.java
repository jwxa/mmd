package com.github.jwxa.model.study;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 选择题题目实体类
 * Created by Jwxa on 2015/7/1.
 */
@Getter
@Setter
@AllArgsConstructor
public class QuestionVO implements Serializable {
    /**
     * 题目序号
     */
    private int id;
    /**
     * 题目
     */
    private String question;
    /**
     * 选项
     */
    private String[] options;
    /**
     * 正确答案的序号
     */
    private Integer correctAnswerSeq;
    /**
     * 答案序号
     */
    private Integer answerSeq;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("QuestionVO{");
        sb.append("id=").append(id);
        sb.append(", question='").append(question).append('\'');
        sb.append(", options=").append(options == null ? "null" : Arrays.asList(options).toString());
        sb.append(", correctAnswerSeq=").append(correctAnswerSeq);
        sb.append(", answerSeq=").append(answerSeq);
        sb.append('}');
        return sb.toString();
    }

    /**
     * 比较答案是否正确
     * @return
     */
    public boolean cmpAnswer(){
            return answerSeq==correctAnswerSeq;
    }
}
