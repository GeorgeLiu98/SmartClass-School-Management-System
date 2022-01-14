package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.domain.SysStudent;

/**
 * 学生信息 数据层
 * 
 * @author ruoyi
 */
public interface SysStudentMapper
{
    /**
     * 查询学生数据集合
     * 
     * @param student 学生信息
     * @return 学生数据集合
     */
    public List<SysStudent> selectStudentList(SysStudent student);
    /**
     * 新增学生信息
     * @param student 学生信息
     * @return 结果
     */
    public int insertStudent(SysStudent student);
    /**
     * 删除学生信息
     * @param ids 学生号
     * @return 结果
     */
    public int deleteStudentByIds(String ids);
    /**
     * 根据学生号查询信息
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
      * 校验学生id是否唯一
      * @param id 学生id
      * @return 结果
      */
     public SysStudent checkStudentIdUnique(String id);
     /**
      * 修改时 校验学生id是否唯一
      * @param id 学生id
      * @return 结果
      */
     public SysStudent EditStudentIdUnique(String id);

    
}
