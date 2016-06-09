package com.github.jwxa.model.study;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 包含平假名片假名罗马音的整体<br>
 * User: Jwxa<br>
 * Date: 2016/6/9.
 */
@Getter
@Setter
public class WordVO implements Serializable {

    private Map<String,WordPartVO> aliasVOMap;

    private List<WordPartVO> shownList;

    private List<WordPartVO> unshownList;
}
