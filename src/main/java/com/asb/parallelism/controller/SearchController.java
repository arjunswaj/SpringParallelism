package com.asb.parallelism.controller;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller
 * Created by arjun on 09/04/17.
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    @Data
    @Builder
    private static class Result {
        private String response;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public Result search() {
        return Result.builder()
                     .response("Lol")
                     .build();
    }

}
