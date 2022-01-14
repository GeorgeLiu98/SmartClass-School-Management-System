package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysClass;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.domain.SysStudent;
import com.ruoyi.system.mapper.SysClassMapper;
import com.ruoyi.system.mapper.SysPostMapper;
import com.ruoyi.system.mapper.SysStudentMapper;
import com.ruoyi.system.mapper.SysUserPostMapper;
import com.ruoyi.system.service.ISysClassService;
import com.ruoyi.system.service.ISysPostService;
import com.ruoyi.system.service.ISysStudentService;

/**
 * 班级信息 服务层处理
 * 
 */
@Service
public class SysClassServiceImpl implements ISysClassService
{
    @Autowired
    private SysClassMapper classMapper;
    
    @Autowired
    private SysStudentMapper studentMapper;

    /**
     * 查询班级信息集合
     * 
     * @param class 班级信息
     * @return 班级信息集合
     */
    @Override
    public List<SysClass> selectClassList(SysClass Class)
    {
        return classMapper.selectClassList(Class);
    }
    
    /** 
     * 新增班级信息
     * @param Class 班级信息
     * @return 结果
     */
    @Override
    public int insertClass(SysClass Class) 
    {
    	return classMapper.insertClass(Class);
    }
    /**
     * 删除班级信息
     * @param num 需要删除的数据num
     * @return 结果
     * @throws Exception 异常 
     */
    @Override
    public int deleteClassByNum(String num) throws BusinessException
    {
    	return classMapper.deleteClassByNum(num);
    }
    
    /**
     * 根据班级序号查询信息
     * 
     * @param num 需要查询的序号
     * @return SysClass 班级信息
     * 
     */
    @Override
    public SysClass selectClassByNum(String num) {
    	
    	return classMapper.selectClassByNum(num);
    }
    /**
     * 更新班级信息
     * @param Class 班级信息
     * @return 结果
     */
     @Override
     public int updateClass(SysClass Class) {
    	 
    	 return classMapper.updateClass(Class);
     }
     /**
      * 校验班级号是否唯一
      * @param class 班级信息
      * @return 结果
      */
     @Override
     public String checkClassNameUnique(SysClass Class) {
    	 SysClass returnInfo=classMapper.checkClassNameUnique(Class.getClassNum());
    	 
    	 if (returnInfo!=null){
    		 return UserConstants.POST_NAME_NOT_UNIQUE;
    	 }
    	 return UserConstants.POST_NAME_UNIQUE;
     }
     /**
      * 修改时 校验班级号是否唯一
      * @param class 班级信息
      * @return 结果
      */
     @Override
     public String EditClassNumUnique(SysClass Class) {
    	 SysClass returnInfo=classMapper.EditClassNumUnique(Class.getClassNum());
    	 
    	 if (returnInfo==null){
    		 //返回null，无记录
    		 return UserConstants.POST_NAME_UNIQUE;
    		 
    	 }
    	 else if (returnInfo.getNum().equals(Class.getNum())) {
    		//查询到该记录本身，未对班级号进行修改
    		 return UserConstants.POST_NAME_UNIQUE;
    	 }
    	 return UserConstants.POST_NAME_NOT_UNIQUE;
     }
}
