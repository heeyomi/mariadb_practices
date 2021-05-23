package com.douzone.bookmall.test;

import java.util.List;

import com.douzone.bookmall.dao.CategoryDao;
import com.douzone.bookmall.vo.CategoryVo;

public class CategoryDaoTest {

	public static void main(String[] args) {
		// 카테고리 생성
		insertTest();
		
		// 카테고리 리스트
		findAll();
	}

	public static void findAll() {
		List<CategoryVo> list = new CategoryDao().findAll();
		System.out.println("---------- 카테고리 리스트 ----------");
		for (CategoryVo categoryVo : list) {
			System.out.println("["+categoryVo.getNo()+"] " + categoryVo.getCategory());
		}
		System.out.println("--------------------------------");
	}

	private static void insertTest() {
		CategoryVo vo = new CategoryVo();

		vo.setCategory("소설");
		new CategoryDao().insert(vo);

		vo.setCategory("인문");
		new CategoryDao().insert(vo);
		
		vo.setCategory("미술");
		new CategoryDao().insert(vo);
	}

}
