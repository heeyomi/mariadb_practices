package bookshop.main;

import java.util.List;
import java.util.Scanner;

import bookshop.dao.BookDao;
import bookshop.vo.BookVo;

public class BookShop {

	public static void main(String[] args) {
		displayBookInfo();

		Scanner scanner = new Scanner(System.in);
		System.out.print("대여 하고 싶은 책의 번호를 입력하세요:");

		Long no= scanner.nextLong();
		new BookDao().update(no, "대여중");

		displayBookInfo();

	}
	private static void displayBookInfo() {
		System.out.println("*****도서 정보 출력하기******");

		List<BookVo> list = new BookDao().findAll();
		for(BookVo vo : list) {
			System.out.println("[" + vo.getNo() + "] 제목:" + vo.getTitle() +"\t 저자:" + vo.getAuthorName() + "\t 대여유무:" + vo.getStatus());
		}
	}
}
