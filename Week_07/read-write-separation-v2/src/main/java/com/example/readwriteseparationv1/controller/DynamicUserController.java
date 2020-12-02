package com.example.readwriteseparationv1.controller;



import com.example.readwriteseparationv1.service.DynamicUserService;
import com.example.readwriteseparationv1.utils.ResultCode;
import com.example.readwriteseparationv1.utils.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Map;

//@CrossOrigin
@RestController
@Slf4j
public class DynamicUserController {

    @Autowired
    private DynamicUserService dynamicUserService;

    @GetMapping("/duser/eidt")
    public ResultResponse edit(@RequestParam("id") BigInteger id) {
        return ResultResponse.builderResponse(ResultCode.SUCCESS.getCode(),"编辑成功!",true,"",dynamicUserService.editPrimary(id));

    }
    @GetMapping("/duser/eidts")
    public ResultResponse eidtsecond(@RequestParam("id") BigInteger id) {
        log.info("eidtsecond--->");
        return ResultResponse.builderResponse(ResultCode.SUCCESS.getCode(),"编辑成功!",true,"",dynamicUserService.editSecond(id));

    }
    @GetMapping("/user/delete")
    public ResultResponse deleteById(@RequestParam("id") BigInteger id) {

        return ResultResponse.builderResponse(ResultCode.SUCCESS.getCode(),"删除成功!",true,"",dynamicUserService.delete(id));

    }

}
