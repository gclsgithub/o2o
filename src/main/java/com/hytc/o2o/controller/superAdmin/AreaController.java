package com.hytc.o2o.controller.superAdmin;

import com.hytc.o2o.entity.Area;
import com.hytc.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/superadmin")
public class AreaController {


    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/listarea",method = RequestMethod.GET)
    public Map<String,Object> listArea() {
        Map<String, Object> modelMap = new HashMap<>();
        List<Area> areaList = new ArrayList<>();
        try {
            areaList = areaService.getAreaList();
            modelMap.put("rows",areaList);
            modelMap.put("total",areaList.size());

        }catch (Exception ex){
            ex.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",ex.toString());
        }
        return modelMap;
    }
}
