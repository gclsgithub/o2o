package com.hytc.o2o.controller.superAdmin;

import com.hytc.o2o.entity.HeadLine;
import com.hytc.o2o.service.HeadLineService;
import com.hytc.o2o.util.HttpRequestUtil;
import com.sun.tools.javac.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class RestMain {

    @Autowired
    private HeadLineService headLineService;

    @PostMapping("/getHeadLine")
    public Map<String, Object> getHeadLines(HttpServletRequest request) {
        Map<String, Object> output = new HashMap<>();

        String enableStatus =HttpRequestUtil.getString(request,"enableStatus");

        String status = null;
        if ("ALL".equals(enableStatus)){
            status = null;
        } else if ("1".equals(enableStatus)){
            status = "1";
        } else if ("0".equals(enableStatus)) {
            status = "0";
        }

        try {
            List<HeadLine> headLines = headLineService.getAllHeadLines(status);
            output.put("headLines",headLines);
            output.put("success",true);
        } catch (Exception e) {
            output.put("success",false);
            e.printStackTrace();
        }
        return output;
    }
}
