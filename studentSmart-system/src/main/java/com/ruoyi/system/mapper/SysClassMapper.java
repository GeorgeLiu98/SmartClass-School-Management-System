package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.SysClass;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.domain.SysStudent;

/**
 * 班级信息 数据层
 * 
 */
public interface SysClassMapper
{
    /**
     * 查询班级数据集合
     * 
     * @param class 班级信息
     * @return 班级数据集合
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
     * @param num 班级序列号
     * @return 结果
     */
    public int deleteClassByNum(String num);
    /**
     * 根据班级序号查询信息
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
      * 校验班级号是否唯一
      * @param classNum 班级号
      * @return 结果
      */
     public SysClass checkClassNameUnique(String classNum);
     /**
      * 修改时 校验班级号是否唯一
      * @param classNum 班级号
      * @return 结果
      */
     public SysClass EditClassNumUnique(String classNum);

    
}
