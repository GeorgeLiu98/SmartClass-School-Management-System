package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.domain.SysStudent;

/**
 * 岗位信息 服务层
 * 
 * @author ruoyi
 */
public interface ISysStudentService
{
    /**
     * 查询学生信息集合
     * 
     * @param student 学生信息 
     * @return 学生信息集合
     */
    public List<SysStudent> selectStudentList(SysStudent student);
    
    /**
     * 新增学生信息
     * @param student 学生信息
     * @return 结果
     */
    public int insertStuent(SysStudent student);
    /**
     * 删除学生信息
     * 
     * @param ids 需要删除的数据id
     * @return 结果
     * @throws Exception 异常
     */
    public int deleteStudentByIds(String ids) throws Exception;
    /**
     * 根据序号查询信息
     * 
     * @param num 需要查询的序号
     * @return SysStudent 学生信息
     * 
     */
    public SysStudent selectStudentByNum(Long num);
   /**
    * 更新学生信息
    * @param student 学生信息
    * @return 结果
    */
    public int updateStudent(SysStudent student);
    /**
     * 校验新添加的学生id
    * @param student 学生信息
    * @return 结果 
     */
    public String checkStudentIdUnique(SysStudent student);
    /**
     * 校验时校验学生id
     * @param student 学生信息
     * @return 结果 
     */
    public String EditStudentIdUnique(SysStudent student);

}
