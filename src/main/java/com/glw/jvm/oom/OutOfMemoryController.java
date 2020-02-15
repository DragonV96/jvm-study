package com.glw.jvm.oom;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author : glw
 * @date : 2020/2/15
 * @time : 0:20
 * @Description : 内存溢出接口
 */
@Api(tags = "内存溢出接口")
@RestController
public class OutOfMemoryController {

    private List<User> userList = new ArrayList<>();
    private List<Class<?>> classList = new ArrayList<>();

    /**
     * -Xmx32M -Xms32M
     * 堆内存溢出
     * @return
     */
    @ApiOperation(value = "堆内存溢出", notes = "堆内存溢出")
    @GetMapping("/heap")
    public String heap() {
        int i = 0;
        while (true) {
            userList.add(new User(i++, UUID.randomUUID().toString()));
        }
    }

    /**
     * -XX:MetaspaceSize=64M -XX:MaxMetaspaceSize=64M
     * 非堆内存溢出
     * @return
     */
    @ApiOperation(value = "非堆内存溢出", notes = "非堆内存溢出")
    @GetMapping("/noheap")
    public String noheap() {
        int i = 0;
        while (true) {
            classList.addAll(Metaspace.createClass());
        }
    }
}
