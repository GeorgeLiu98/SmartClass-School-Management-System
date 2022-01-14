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
import com.ruoyi.system.domain.SysClass;
import com.ruoyi.system.service.ISysClassService;
import com.ruoyi.system.service.ISysPostService;

/**
 * 班级信息操作处理
 * 
 */
@Controller
@RequestMapping("/system/class")
public class SysClassController extends BaseController
{
    private String prefix = "system/class";

    @Autowired
    private ISysClassService classService;
    
    /**
     * 加载 class.html 页面
     */
    //@RequiresPermissions("system:post:view")
    @GetMapping()
    public String operlog()
    {
        return prefix + "/class";
    }

    /**
     * 注入信息到前端bootstrap table
     * @param class
     */
    //@RequiresPermissions("system:studnet:list")
    @PostMapping("/list")
    @ResponseBody  
    public TableDataInfo list(SysClass Class)
    {
        startPage(); // 此方法配合前端完成自动分页
        List<SysClass> list=classService.selectClassList(Class);
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
//
    /**
    * 删除班级信息
    * @param ids 带入参数名称（String ids）对应了前端jQuery中的变量名
    * @return
    */
//   @RequiresPermissions("system:post:remove")
//   @Log(title = "岗位管理", businessType = BusinessType.DELETE)
   @PostMapping("/remove")
   @ResponseBody
   public AjaxResult remove(String ids)
   {
       try
       {
    	   //返回“操作成功”字段到前端
           return toAjax(classService.deleteClassByNum(ids));
       }
       catch (Exception e)
       {
           return error(e.getMessage());
       }
   }

   /**
    * 新增班级
    * 返回页面到前端
    */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存班级
     */
    /*从add.html 模板通过路径传参过来*/
//    @RequiresPermissions("system:post:add")
//    @Log(title = "岗位管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysClass Class)
    {    
    	int ClassNum=Integer.valueOf(Class.getClassNum());
    	int grade=Integer.valueOf(Class.getGrade());
    	
    	//判断输入的班级与年级是否匹配
    	if (ClassNum/100!=grade) {
    		
    		return error("新增班级'" + Class.getClassNum() + "'失败，班级与年级不匹配");
    	}
    	
        return toAjax(classService.insertClass(Class)); /*先进入数据mapper层进行插入。根据返回的int来判断是否插入成功*/
        											/*继承baseController，返回Ajax结果到前台。如果插入成功，前台输出"操作成功"*/
    }

    /**
     * 修改班级 返回现有信息到前端
     */
    @GetMapping("/edit/{num}")
    public String edit(@PathVariable("num") String num, ModelMap mmap)
    {
    	System.out.println("num= "+num);
        mmap.put("class", classService.selectClassByNum(num));
           	
    	return prefix + "/edit";
    }

    /**
     * 修改保存班级信息
     */
    /*对应edit.html模板，通过路径传参到后台*/
//    @RequiresPermissions("system:post:edit")
//    @Log(title = "岗位管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysClass Class)
    {
    	int ClassNum=Integer.valueOf(Class.getClassNum());
    	int grade=Integer.valueOf(Class.getGrade());
    	
    	//判断新输入的班级与年级是否匹配
    	if (ClassNum/100!=grade) {
    		
    		return error("新增班级'" + Class.getClassNum() + "'失败，班级与年级不匹配");
    	}
        return toAjax(classService.updateClass(Class));
    }
    /**
     * 校验新添加的班级号
     */
    /*对应html模板。模板通过路径调用并传参*/
    @PostMapping("/checkClassNumUnique")
    @ResponseBody
    public String checkClassNumUnique(SysClass Class)
    {
        return classService.checkClassNameUnique(Class);
    }
    
    /**
     * 修改时校验班级号
     * @param class 班级信息 
     * @return 结果
     */
    @PostMapping("/EditClassNumUnique")
    @ResponseBody
    public String EditClassNumUnique(SysClass Class)
    {	
        return classService.EditClassNumUnique(Class);
    }
}
