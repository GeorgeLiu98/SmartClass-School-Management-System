package com.ruoyi.web.controller.system;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.domain.SysStudent;
import com.ruoyi.system.service.ISysPostService;
import com.ruoyi.system.service.ISysStudentService;

/**
 * 学生信息操作处理
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/student")
public class SysStudentController extends BaseController
{
    private String prefix = "system/student";

    @Autowired
    private ISysStudentService studentService;
    /**
     * 加载 student.html 页面
     */
    @RequiresPermissions("system:student:view")
    @GetMapping()
    public String operlog()
    {
        return prefix + "/student";
    }
    /**
     * 注入信息到前端bootstrap table
     * @param student 
     */
    //@RequiresPermissions("system:studnet:list")
    @PostMapping("/list")
    @ResponseBody  
    public TableDataInfo list(SysStudent student)
    {
        startPage(); // 此方法配合前端完成自动分页
        List<SysStudent> list=studentService.selectStudentList(student);
        return getDataTable(list);
    }
//
//    @Log(title = "岗位管理", businessType = BusinessType.EXPORT)
//    @RequiresPermissions("system:post:export")
//    @PostMapping("/export")
//    @ResponseBody
//    public AjaxResult export(SysPost post)
//    {
//        List<SysPost> list = postService.selectPostList(post);
//        ExcelUtil<SysPost> util = new ExcelUtil<SysPost>(SysPost.class);
//        return util.exportExcel(list, "岗位数据");
//    }
    
    /**
     * 删除学生信息
     * @param ids
     * @return
     */
//    @RequiresPermissions("system:post:remove")
//    @Log(title = "岗位管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        try
        {
            return toAjax(studentService.deleteStudentByIds(ids));
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
    }

    /**
     * 新增学生信息 
     * 返回页面到前端
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存学生
     */
    /*从add.html 模板通过路径传参过来*/
    //@RequiresPermissions("system:post:add")
    //@Log(title = "岗位管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysStudent student)
    {
    	/**
    	 * 先进行判断是否已经存在
    	 */
//        if (UserConstants.POST_NAME_NOT_UNIQUE.equals(postService.checkPostNameUnique(post)))
//        {
//            return error("新增岗位'" + post.getPostName() + "'失败，岗位名称已存在");
//        }
//        else if (UserConstants.POST_CODE_NOT_UNIQUE.equals(postService.checkPostCodeUnique(post)))
//        {
//            return error("新增岗位'" + post.getPostName() + "'失败，岗位编码已存在");
//        }
//         post.setCreateBy(ShiroUtils.getLoginName()); /**得到创建人的用户名信息*/
//        
//        return toAjax(postService.insertPost(post)); /**先进入数据mapper层进行插入。根据返回的int来判断是否插入成功*/
        											/**继承baseController，返回Ajax结果到前台。如果插入成功，前台输出"操作成功"*/
    	
    	 //return toAjax(studentService.insertStuent(student));
    	int result=studentService.insertStuent(student);
    	System.out.println("return result= "+result);
    	return toAjax(result);
    }

    /**
     * 修改学生信息
     */
    @GetMapping("/edit/{num}")
    public String edit(@PathVariable("num") Long num, ModelMap mmap)
    {	
        mmap.put("student", studentService.selectStudentByNum(num));

    	return prefix + "/edit";
    }

    /**
     * 修改保存学生信息
     */
    /*对应edit.html模板，通过路径传参到后台*/
//    @RequiresPermissions("system:post:edit")
//    @Log(title = "岗位管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysStudent student)
    {
//        if (UserConstants.POST_NAME_NOT_UNIQUE.equals(postService.checkPostNameUnique(post)))
//        {
//            return error("修改岗位'" + post.getPostName() + "'失败，岗位名称已存在");
//        }
//        else if (UserConstants.POST_CODE_NOT_UNIQUE.equals(postService.checkPostCodeUnique(post)))
//        {
//        	return error("修改岗位'" + post.getPostName() + "'失败，岗位编码已存在");
//        }
//        }
//        post.setUpdateBy(ShiroUtils.getLoginName());/*得到修改人的用户名信息*/
    	
        return toAjax(studentService.updateStudent(student));/*先进入数据mapper层进行更新。根据返回的int来判断是否更新成功*/
													/*继承baseController，返回Ajax结果到前台。如果更新成功，前台输出"操作成功"*/
        }

    /**
     * 校验新添加的学生id
     * @param student 学生信息
     * @return 结果
    */
    /*对应html模板。模板通过路径调用并传参*/
    @PostMapping("/checkStudentIdUnique")
    @ResponseBody
    public String checkStudentIdUnique(SysStudent student)
    {
        return studentService.checkStudentIdUnique(student);
    }
    /**
     * 修改时校验学生id
     * @param student 学生信息 
     * @return 结果
     */
    @PostMapping("/EditStudentIdUnique")
    @ResponseBody
    public String EditStudentIdUnique(SysStudent student)
    {	
        return studentService.EditStudentIdUnique(student);
    }
}
