package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.SysClass;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.domain.SysStudent;

/**
 *班级信息 服务层
 * 
 */
public interface ISysClassService
{
    /**
     * 查询班级信息集合
     * 
     * @param Class 班级信息 
     * @return 班级信息集合
     */
    public List<SysClass> selectClassList(SysClass Class);
    
    /**
     * 新增班级信息
     * @param Class 班级信息
     * @return 结果
     */
    public int insertClass(SysClass Class);
    /**
     * 删除班级信息
     * 
     * @param num 需要删除的数据num
     * @return 结果
     * @throws Exception 异常
     */
    public int deleteClassByNum(String ids) throws Exception;
    /**
     * 根据序号查询信息
     * 
     * @param num 需要查询的序号
     * @return SysClass 班级信息
     * 
     */
    public SysClass selectClassByNum(String num);
   /**
    * 更新班级信息
    * @param Class 班级信息
    * @return 结果
    */
    public int updateClass(SysClass Class);
    /**
     * 校验新添加的班级号
    * @param class 班级信息
    * @return 结果 
     */
    public String checkClassNameUnique(SysClass Class);
    /**
     * 修改时校验班级号
     * @param class 班级信息
     * @return 结果 
     */
    public String EditClassNumUnique(SysClass Class);

}
