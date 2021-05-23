package com.douzone.bookmall.test;

import java.util.List;

import com.douzone.bookmall.dao.BookDao;
import com.douzone.bookmall.vo.BookVo;

public class BookDaoTest {

	public static void main(String[] args) {
		// 도서 생성
		insertTest();

		// 도서 리스트
		findAll();
	}

	public static void findAll() {
		List<BookVo> list = new BookDao().findAll();
		System.out.println("-------------------- 도서 리스트 --------------------");
		for (BookVo bookVo : list) {
			System.out.println("도서 제목:" + bookVo.getTitle() + "\t 가격:" + bookVo.getPrice() +"원\t 카테고리:" + bookVo.getCategory() +"\t 도서번호:" + bookVo.getBookNo());
		}
		System.out.println("--------------------------------------------------");
	}

	private static void insertTest() {
		BookVo vo = new BookVo();

		vo.setTitle("냄새의 심리학");
		vo.setPrice(15750);
		vo.setBookNo(2);
		new BookDao().insert(vo);

		vo.setTitle("미드나잇 라이브러리");
		vo.setPrice(14220);
		vo.setBookNo(1);
		new BookDao().insert(vo);

		vo.setTitle("명화를 보는 눈");
		vo.setPrice(19800);
		vo.setBookNo(3);
		new BookDao().insert(vo);

	}

}
