package com.github.jwxa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <BR>
 * User: Jwxa<BR>
 * Date: 2015/2/23  Time: 15:02<BR>
 */
@Controller
public class ImagesViewController {

    @RequestMapping("imagesView")
    public String showImagesView(){
        return "imagesview/imagesView";
    }

}
