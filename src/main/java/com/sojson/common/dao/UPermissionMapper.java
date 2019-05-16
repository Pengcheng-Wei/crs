package com.sojson.common.dao;

import java.util.List;
import java.util.Set;

import com.sojson.common.model.UPermission;
import com.sojson.permission.bo.UPermissionBo;

/**
 * @author allen
 */
public interface UPermissionMapper {
    /**
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @param record
     * @return
     */
    int insert(UPermission record);

    /**
     *
     * @param record
     * @return
     */
    int insertSelective(UPermission record);

    /**
     *
     * @param id
     * @return
     */
    UPermission selectByPrimaryKey(Long id);

    /**
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(UPermission record);

    /**
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(UPermission record);

	List<UPermissionBo> selectPermissionById(Long id);

    /**
     * <p>根据用户ID获取权限的Set集合</p>
     * @param id
     * @return
     */
	Set<String> findPermissionByUserId(Long id);
}