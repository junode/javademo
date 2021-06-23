package com.spring.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spring.cloud.common.model.ResultMessage;
import spring.cloud.common.model.UserInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description 客户信息控制器
 * @Author junode
 * @Date 2020/12/27
 */
@Controller
@RequestMapping("/user")
public class UserInfoController {

    /**
     * 模拟获取用户信息
     * @param id -- 用户编号
     * @param request
     * @return
     */
    @GetMapping("/info/{id}")
    @ResponseBody
    public UserInfo getUser(@PathVariable("id") Long id, HttpServletRequest request){
        System.out.println("请求的端口是多少？："+request.getServerPort());
        UserInfo userInfo = new UserInfo(1L,"user_name_"+id,"note_"+id);
        return userInfo;
    }

    @PutMapping("/info")
    @ResponseBody
    public UserInfo putUser(@RequestBody UserInfo userInfo){
        return userInfo;
    }

    // feign常见传参方式

    /**
     * 以url?ids=xxx的形式传递参数
     *
     * @param ids 参数列表
     * @return 用户信息列表
     */
    @GetMapping("/infoes2")
    @ResponseBody
    public ResponseEntity<List<UserInfo>> findUsers2(@RequestParam("ids") Long[] ids){
        List<UserInfo> userList = new ArrayList<>();
        Arrays.stream(ids).forEach(id->userList.add(new UserInfo(id,"user_name"+id,"note_"+id)));
        ResponseEntity<List<UserInfo>> response = new ResponseEntity<>(userList, HttpStatus.OK);
        return response;
    }

    /**
     * 删除用户
     * @param id 使用请求头传递参数
     * @return return
     */
    @DeleteMapping("/info")
    @ResponseBody
    public ResultMessage deleteUser(@RequestHeader("id") Long id){
        boolean success = id != null;
        String msg = success ?"删除成功" :"删除失败";
        return new ResultMessage(success,msg);
    }

    /**
     * 传递文件
     * @param file 文件
     * @return 成败结果
     */
    @PostMapping(value="/upload")
    @ResponseBody
    public ResultMessage uploadFile(@RequestPart("file")MultipartFile file){
        boolean success = file != null && file.getSize()>0;
        String message = success ? "文件上传成功" : "文件上传失败";
        return new ResultMessage(success,message);
    }
}
