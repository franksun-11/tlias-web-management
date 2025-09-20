package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Dept;
import org.example.pojo.Result;
import org.example.service.DeptService;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
public class DeptController {
    /*private static final Logger log = LoggerFactory.getLogger(DeptController.class);*/

    @Autowired
    private DeptService deptService;
    /*@RequestMapping(value = "/depts", method = RequestMethod.GET)*/ // method: 指定请求方式
    @GetMapping("/depts")
    public Result list(){
        /*System.out.println("查询全部部门数据");*/
        log.info("查询全部部门数据");
        List<Dept> deptList =deptService.findAll();
        return Result.success(deptList);
    }
    /**
     * 删除部门 - 方式1：HttpsServiceRequest 获取请求参数
     */
/*    @DeleteMapping("/depts")
    public Result delete(HttpServletRequest request){
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        System.out.println("根据ID删除部门：" + id);
        return Result.success();
    }*/

    /**
     * 删除部门 - 方式2：@RequestParam 获取请求参数
     * 注意事项： 一旦声明了@RequestParam， 该参数在请求时必须传递， 如果不传递会报错（默认 required = true）
     */
/*    @DeleteMapping("/depts")
    public Result delete(@RequestParam("id") Integer deptId){
        System.out.println("根据ID删除部门：" + deptId);
        return Result.success();
    }*/

    /**
     * 删除部门  - 方式3： 省略@RequestParam
     * 注意事项：
     *  1. 省略@RequestParam时，参数名必须和前端传递的参数名一致
     *  2. 省略@RequestParam时，参数必须是简单类型（String、int、Integer等）
     */
    @DeleteMapping("depts")
    public Result delete(Integer id){
        /*System.out.println("根据ID删除部门: " + id);*/
        log.info("根据ID删除部门：{}", id);
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 新增部门
     */
    @PostMapping("/depts")
    // @requestBody 将json数据绑定到形参的实体类中
    public Result add(@RequestBody Dept dept){
        /*System.out.println("新增部门：{}");*/
        log.info("新增部门：{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 根据id查询部门
     */
    @GetMapping("/depts/{id}")
    public Result getInfo(@PathVariable("id") Integer deptId){
        /*System.out.println("根据ID查询部门：" + deptId);*/
        log.info("根据ID查询部门：{}", deptId);
        Dept dept = deptService. getById(deptId);
        return Result.success(dept);
    }
    /**
     * 修改部门
     */
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept){
        /*System.out.println("修改部门：" + dept);*/
        log.info("修改部门：{}", dept);
        deptService.update(dept);
        return Result.success();
    }

}
