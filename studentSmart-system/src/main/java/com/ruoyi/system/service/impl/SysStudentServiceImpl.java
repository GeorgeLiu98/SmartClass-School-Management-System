package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.domain.SysStudent;
import com.ruoyi.system.mapper.SysPostMapper;
import com.ruoyi.system.mapper.SysStudentMapper;
import com.ruoyi.system.mapper.SysUserPostMapper;
import com.ruoyi.system.service.ISysPostService;
import com.ruoyi.system.service.ISysStudentService;

/**
 * 学生信息 服务层处理
 * 
 * @author ruoyi
 */
@Service
public class SysStudentServiceImpl implements ISysStudentService
{
    @Autowired
    private SysStudentMapper studentMapper;

    /**
     * 查询学生信息集合
     * 
     * @param student 学生信息
     * @return 学生信息集合
     */
    @Override
    public List<SysStudent> selectStudentList(SysStudent student)
    {
        return studentMapper.selectStudentList(student);
    }
    
    /** 
     * 新增学生信息
     * @param student 学生信息
     * @return 结果
     */
    @Override
    public int insertStuent(SysStudent student) 
    {
    	return studentMapper.insertStudent(student);
    }
    /**
     * 删除学生信息
     * @param ids 需要删除的数据id
     * @return 结果
     * @throws Exception 异常 
     */
    @Override
    public int deleteStudentByIds(String ids) throws BusinessException
    {
    	return studentMapper.deleteStudentByIds(ids);
    }
    
    /**
     * 根据学生号查询信息
     * 
     * @param num 需要查询的序号
     * @return SysStudent 学生信息
     * 
     */
    @Override
    public SysStudent selectStudentByNum(Long num) {
    	
    	return studentMapper.selectStudentByNum(num);
    }
    /**
     * 更新学生信息
     * @param student 学生信息
     * @return 结果
     */
     @Override
     public int updateStudent(SysStudent student) {
    	 
    	 return studentMapper.updateStudent(student);
     }
     /**
      * 校验学生id是否唯一
      * @param student 学生信息
      * @return 结果
      */
     @Override
     public String checkStudentIdUnique(SysStudent student) {
    	 SysStudent returnInfo=studentMapper.checkStudentIdUnique(student.getId());
    	 
    	 if (returnInfo!=null){
    		 return UserConstants.POST_NAME_NOT_UNIQUE;
    	 }
    	 return UserConstants.POST_NAME_UNIQUE;
     }
     /**
      * 修改时 校验学生id是否唯一
      * @param student 学生信息
      * @return 结果
      */
     @Override
     public String EditStudentIdUnique(SysStudent student) {
    	 SysStudent returnInfo=studentMapper.EditStudentIdUnique(student.getId());
    	 
    	 if (returnInfo==null){
    		 //返回null，无记录
    		 return UserConstants.POST_NAME_UNIQUE;
    		 
    	 }
    	 else if (returnInfo.getNum().equals(student.getNum())) {
    		//查询到该记录本身，未对学生id进行修改
    		 return UserConstants.POST_NAME_UNIQUE;
    	 }
    	 return UserConstants.POST_NAME_NOT_UNIQUE;
     }
}
