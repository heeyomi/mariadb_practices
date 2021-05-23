package com.douzone.bookmall.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.douzone.bookmall.dao.BookDao;
import com.douzone.bookmall.dao.MemberDao;
import com.douzone.bookmall.dao.OrderDao;
import com.douzone.bookmall.vo.BookVo;
import com.douzone.bookmall.vo.MemberVo;
import com.douzone.bookmall.vo.OrderVo;

public class OrderDaoTest {

	public static void main(String[] args) {
		//바로 주문하기
		insertOrder();

		//주문 보기
		findAll();

		//주문 도서 보기
		findBookAll();

	}

	public static void findAll() {
		List<OrderVo> list = new OrderDao().findAll();
		if (list.isEmpty()) {
			System.out.println("주문 리스트가 없습니다.");
		} else {
			System.out.println("--------------------- 주문 리스트 ---------------------");
			System.out.println("주문번호" + "\t 주문자(이름/이메일)" + "\t\t\t 결제금액" +"\t\t 배송지");
			for (OrderVo orderVo : list) {
				System.out.println("[" + orderVo.getOrderNo() +"] \t " + orderVo.getOrderer()+"\t " + orderVo.getPayPrice() +"원 \t" + orderVo.getShipDestination());
			}
		}

	}

	private static void insertOrder() {
		//임시 멤버
		MemberVo tempMemNo = MemberDao.findById(2);
		List<BookVo> list = new ArrayList<>();
		
		Scanner sc = new Scanner(System.in);
		int price = 0;
		while (true) {
			BookDaoTest.findAll();
			System.out.print("주문할 도서 번호를 선택해주세요>");
			int bookNo = sc.nextInt();

			BookVo vo = BookDao.findByBookNo(bookNo);
			while (true) {
				if (vo == null) {
					System.out.println("해당 하는 도서 번호가 없습니다. 다시 입력해주세요");
					System.out.print("주문할 도서 번호를 선택해주세요>");
					bookNo = sc.nextInt();
				} else {
					break;
				}
			}

			System.out.print("구매 할 수량>");
			int quantity = sc.nextInt();
			sc.nextLine();
			price = price + (quantity * vo.getPrice());
			
			BookVo bookVo = new BookVo();
			bookVo.setTitle(vo.getTitle());
			bookVo.setPrice(quantity);
			bookVo.setCategory(vo.getCategory());
			bookVo.setBookNo(vo.getBookNo());
			
			list.add(bookVo);
			
			System.out.println("다른 도서도 같이 구매하기");
			System.out.println("1. 예");
			System.out.println("2. 아니오");
			int num = sc.nextInt();
			if (num == 2) {
				break;
			}
		}
		System.out.print("주소를 입력하세요>");
		sc.nextLine();
		String address  = sc.nextLine();
		
		OrderDao.insertOrder(tempMemNo.getMemNo(), price, address);
		OrderDao.insertOrderBook(list);
		System.out.println("도서 주문이 완료되었습니다.");
	}


	public static void findBookAll() {
		List<OrderVo> list = new OrderDao().findBookAll();
		if (list.isEmpty()) {
			System.out.println("주문 도서 리스트가 없습니다.");
		} else {
			System.out.println("--------------------- 주문 도서 리스트 ---------------------");
			System.out.println("도서번호" + "\t 도서제목" + "\t\t 수량");
			for (OrderVo orderVo : list) {
				System.out.println("[" + orderVo.getBookNo() +"] \t " + orderVo.getTitle()+"\t " + orderVo.getQuantity() +"권");
			}
		}
	}

}
