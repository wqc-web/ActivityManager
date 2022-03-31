package com.zhongzhou.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhongzhou.api.entity.Attachment;
import com.zhongzhou.api.entity.Permission;
import com.zhongzhou.api.entity.Role;
import com.zhongzhou.api.mapper.PermissionMapper;
import com.zhongzhou.api.service.IPermissionService;
import com.zhongzhou.common.utils.Constants;
import com.zhongzhou.common.vo.MenuVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wj
 * @since 2020-06-28
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    private static final long serialVersionUID = -4008611604441401016L;
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private RoleServiceImpl roleService;
    @Resource
    private AttachmentServiceImpl attachmentService;

    @Override
    public void initList() {
        List<Permission> permissionList = list();
        permissionList.forEach(permission -> {
            Constants.MAP_PERMISSION.put(permission.getId(), permission.getName());
        });
    }

    @Override
    public List<MenuVO> findMenuListBySysUserId(Long sysUserId) {
        List<MenuVO> list = new ArrayList<>();
        Role role = roleService.findBySysUserId(sysUserId);
        List<Permission> permissionList = permissionMapper.findListByRoleId(role.getId(), 0L, 0, null);
        if (null != permissionList && permissionList.size() > 0) {
            permissionList.forEach(permission -> {
                MenuVO menuVO = new MenuVO();
                menuVO.setTitle(permission.getName());
                menuVO.setName(permission.getUrl());
                menuVO.setJump(permission.getJump());
                menuVO.setIcon(permission.getIcon());
                if (Constants.MAP_MENU_ICON.containsKey(permission.getId())){
                    menuVO.setIconUrl(Constants.MAP_MENU_ICON.get(permission.getId()).getUrl());
                }
                menuVO.setSeq(permission.getSeq());
                menuVO.setSpread(false);
                TreeSet<MenuVO> menuVOList = getMenuVOList(role.getId(), permission.getId(), 1, null);
                menuVO.setList(menuVOList);
                list.add(menuVO);
            });
        }
        return list;
    }

    @Override
    public List<Permission> findInterfaceListByRoleIdWithChecked(Long roleId) {
        return permissionMapper.findListByRoleId(roleId, null, 2, null);
    }

    @Override
    public List<Permission> findPermissionTree() {
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", 0);
        wrapper.orderByDesc("seq");
        List<Permission> parentPermissionList = list(wrapper);
        return findPermissionChildrenList(parentPermissionList);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public boolean savePermission(Permission permission) {
        if (save(permission)) {
            if (null != permission.getIconImgs() && permission.getIconImgs().size() > 0) {
                for (Long iconImg : permission.getIconImgs()) {
                    Attachment attachment = attachmentService.getById(iconImg);
                    attachment.setSourceId(permission.getId());
                    attachmentService.updateById(attachment);
                }
            }
            initList();
            return true;
        } else {
            throw new RuntimeException("权限菜单添加失败");
        }
    }

    @Override
    public boolean updatePermission(Permission permission) {
        if (updateById(permission)) {
            //删除原有图标的关联关系
            UpdateWrapper<Attachment> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("source_id", permission.getId());
            updateWrapper.set("source_id", null);
            attachmentService.update(updateWrapper);
            //添加新的图标关联关系
            if (iconImgIsNotBlank(null != permission.getIconImgs(), permission.getIconImgs().size())) {
                for (Long iconImg : permission.getIconImgs()) {
                    Attachment attachment = attachmentService.getById(iconImg);
                    attachment.setSourceId(permission.getId());
                    attachmentService.updateById(attachment);
                }
            }
            initList();
            return true;
        } else {
            throw new RuntimeException("权限菜单编辑失败");
        }
    }

    @Override
    public Permission getDetailById(Long id) {
        Permission permission = getById(id);
        if (null != permission) {
            if (!Constants.NUM_ZERO.equals(permission.getParentId())) {
                permission.setParentName(Constants.MAP_PERMISSION.get(permission.getId()));
            }
            QueryWrapper<Attachment> attachmentQueryWrapper = new QueryWrapper<>();
            attachmentQueryWrapper.eq("source_id", id);
            List<Attachment> attachmentList = attachmentService.list(attachmentQueryWrapper);
            permission.setIconAttachments(attachmentList);
        }
        return permission;
    }

    private boolean iconImgIsNotBlank(boolean b, int size) {
        return b && size > 0;
    }

    /**
     * 递归权限菜单树
     *
     * @param parentPermissionList 父级权限菜单信息
     * @return 子级权限菜单列表
     */
    private List<Permission> findPermissionChildrenList(List<Permission> parentPermissionList) {
        if (null != parentPermissionList && parentPermissionList.size() > 0) {
            parentPermissionList.forEach(parent -> {
                QueryWrapper<Permission> wrapper = new QueryWrapper<>();
                wrapper.eq("parent_id", parent.getId());
                wrapper.orderByDesc("seq");
                List<Permission> permissionList = list(wrapper);
                if (null != permissionList && permissionList.size() > 0) {
                    parent.setChildren(permissionList);
                    findPermissionChildrenList(permissionList);
                }
            });
        }
        return parentPermissionList;
    }

    /**
     * 递归查询菜单权限列表
     *
     * @param roleId   角色ID
     * @param parentId 父级ID
     * @param type     类型
     * @param level    级别
     * @return 权限菜单列表
     */
    private TreeSet<MenuVO> getMenuVOList(Long roleId, Long parentId, Integer type, Integer level) {
        TreeSet<MenuVO> result = new TreeSet<>();
        List<Permission> menusList = permissionMapper.findListByRoleId(roleId, parentId, type, level);
        if (null != menusList && menusList.size() > 0) {
            menusList.forEach(menu -> {
                MenuVO menuVO = new MenuVO();
                menuVO.setTitle(menu.getName());
                menuVO.setName(menu.getUrl());
                menuVO.setIcon(menu.getIcon());
                if (Constants.MAP_MENU_ICON.containsKey(menu.getId())){
                    menuVO.setIconUrl(Constants.MAP_MENU_ICON.get(menu.getId()).getUrl());
                }
                menuVO.setJump(menu.getJump());
                menuVO.setSeq(menu.getSeq());
                menuVO.setSpread(false);
                menuVO.setList(getMenuVOList(roleId, menu.getId(), type, level));
                result.add(menuVO);
            });
        }
        return result;
    }

}
