package com.douzone.bookmall.main;

import java.util.Scanner;

import com.douzone.bookmall.test.BookDaoTest;
import com.douzone.bookmall.test.CartDaoTest;
import com.douzone.bookmall.test.CategoryDaoTest;
import com.douzone.bookmall.test.MemberDaoTest;
import com.douzone.bookmall.test.OrderDaoTest;

public class BookMall {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		while (true) {
			displayMain();
			int num = sc.nextInt();
			System.out.println();
			if (num == 0) {
				System.out.println("리스트 보기를 종료합니다.");
				break;
			}
			
			switch (num) {
			case 1:
				MemberDaoTest.findAll();
				System.out.println();
				break;

			case 2:
				CategoryDaoTest.findAll();
				System.out.println();
				break;
				
			case 3:
				BookDaoTest.findAll();
				System.out.println();
				break;
				
			case 4:
				CartDaoTest.findAll();
				System.out.println();
				break;
				
			case 5:
				OrderDaoTest.findAll();
				System.out.println();
				break;

			case 6:
				OrderDaoTest.findBookAll();
				System.out.println();
				break;

			default:
				System.out.println("올바른 번호를 눌러주세요");
				break;
			
			}
		}
		

	}

	private static void displayMain() {
		System.out.println("==============================");
		System.out.println("1. 회원 리스트 보기");
		System.out.println("2. 카테고리 리스트 보기");
		System.out.println("3. 상품 리스트 보기");
		System.out.println("4. 카트 리스트 보기");
		System.out.println("5. 주문 리스트 보기");
		System.out.println("6. 주문 도서 리스트 보기");
		System.out.println("0. 그만 보기");
		System.out.println("==============================");
		System.out.print("확인하고 싶은 리스트의 번호를 입력하세요>");
	}
}
