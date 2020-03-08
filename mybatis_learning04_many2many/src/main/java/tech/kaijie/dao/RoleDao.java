package tech.kaijie.dao;

import tech.kaijie.domain.Role;

import java.util.List;

public interface RoleDao {
    /**
     * 查询角色下的所有用户信息
     * @return
     */
    List<Role> findAllRole();
}
