package com.douzone.bookmall.test;

import java.util.List;
import java.util.Scanner;

import com.douzone.bookmall.dao.BookDao;
import com.douzone.bookmall.dao.CartDao;
import com.douzone.bookmall.dao.MemberDao;
import com.douzone.bookmall.vo.BookVo;
import com.douzone.bookmall.vo.CartVo;
import com.douzone.bookmall.vo.MemberVo;

public class CartDaoTest {

	public static void main(String[] args) {
		//카트에 넣기
		insert();

		//카트 삭제하기
		deleteCart();
		
		//카트 리스트
		findAll();

	}

	private static void deleteCart() {
		List<MemberVo> list = new MemberDao().findAll();
		int tempMemNo = list.get(1).getMemNo(); //임시 멤버 번호
		
		Scanner sc = new Scanner(System.in);
		System.out.print("삭제할 도서 제목을 입력하세요 >");
		String title = sc.nextLine();
		List<BookVo> books = new BookDao().findAll();
		
		boolean flag = false;
		while (true) {
			if (flag) {
				System.out.println("해당 카트의 도서가 삭제되었습니다.");
				break;
			} else {
				System.out.print("삭제할 도서 제목을 올바르게 입력해주세요 >");
				title = sc.nextLine();
			}
			for (BookVo bookVo : books) {
				if (bookVo.getTitle().equals(title)) {
					CartDao.deleteCart(tempMemNo, bookVo.getBookNo());
					flag = true;
				}
			}
		}
		
	}

	private static void insert() {
		List<MemberVo> list = new MemberDao().findAll();
		int tempMemNo = list.get(1).getMemNo(); //임시 멤버 번호

		Scanner sc = new Scanner(System.in);
		System.out.println("카트 담기");
		BookDaoTest.findAll();
		
		System.out.print("카트에 담을 도서 번호>");
		int num = sc.nextInt();
		while (true) {
			BookVo vo = BookDao.findByBookNo(num);
			if (vo == null) {
				System.out.println("해당 하는 도서 번호가 없습니다. 다시 입력해주세요");
				System.out.print("카트에 담을 도서 번호>");
				num = sc.nextInt();
			} else {
				break;
			}
		}

		System.out.print("담을 수량>");
		int quantity = sc.nextInt();

		CartDao.insertCart(num, quantity, tempMemNo);

		BookVo vo = BookDao.findByBookNo(num);
		System.out.println("도서[" + vo.getTitle() +"] " +  "(도서번호:" + vo.getBookNo() + ") " +quantity +"권을 카트에 담았습니다.");

	}

	public static void findAll() {
		System.out.println("-------------------- 카트 리스트 --------------------");
		List<CartVo> list = new CartDao().findAll();
		for (CartVo cartVo : list) {
			System.out.println("회원명:" + cartVo.getName() + "\t 도서제목:"+cartVo.getTitle() + "\t 수량:" + cartVo.getCount() + "권 \t 가격 :" + cartVo.getPrice()+"원");
		}
		System.out.println("--------------------------------------------------");
	}

}
