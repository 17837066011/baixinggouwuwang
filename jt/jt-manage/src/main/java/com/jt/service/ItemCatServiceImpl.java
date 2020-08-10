package com.jt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.vo.EasyUI_Tree;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	private ItemCatMapper itemCatMapper;
	
	@Override
	public String findItemCatNameById(Long itemCatId) {
		
		ItemCat itemCat = 
				itemCatMapper.selectById(itemCatId);
		return itemCat.getName();
	}

	/**
	 * List<EasyUI_Tree> 返回的是VO对象集合
	 * EasyUI_Tree:id/text/state
	 * 
	 * List<ItemCat> 	  返回ItemCat集合对象
	 * ItemCat: id/name/判断是否为父级
	 */
	
	
	@Override
	public List<EasyUI_Tree> findItemCatByParentId(Long parentId) {
		List<EasyUI_Tree> treeList = new ArrayList<>();
		//1.获取数据库数据
		List<ItemCat> itemCatList = findItemCatList(parentId);
		for (ItemCat itemCat : itemCatList) {
			Long   id = itemCat.getId();
			String text = itemCat.getName();
			//一级二级菜单 closed 三级菜单 open
			String state = 
			itemCat.getIsParent()?"closed":"open";
			EasyUI_Tree tree = 
					new EasyUI_Tree(id, text, state);
			treeList.add(tree);
		}
		return treeList;
	}
	
	public List<ItemCat> findItemCatList(Long parentId) {
		QueryWrapper<ItemCat> queryWrapper 
						= new QueryWrapper<ItemCat>();
		queryWrapper.eq("parent_id", parentId);
		return itemCatMapper.selectList(queryWrapper);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
