package com.douzone.bookmall.test;

import java.util.List;
import java.util.Scanner;

import com.douzone.bookmall.dao.MemberDao;
import com.douzone.bookmall.vo.MemberVo;

public class MemberDaoTest {

	public static void main(String[] args) {
		// 멤버 추가
		//insertMember();
		
		// 멤버 삭제
		deleteMember();
		
		//멤버 리스트
		findAll();
	}


	public static void deleteMember() {
		Scanner sc = new Scanner(System.in);
		System.out.print("삭제할 회원 번호를 입력하세요>");
		
		while (true) {
			int memNo = sc.nextInt();
			MemberVo vo = new MemberDao().findById(memNo);
			if (vo == null) {
				System.out.print("삭제할 회원 번호를 올바르게 입력 해주세요 >");
			} else {
				MemberDao.deleteMember(memNo);
				System.out.println(memNo + "번 회원의 정보가 삭제되었습니다.");
				break;
			}
			
		}
		
	}

	public static void findAll() {
		List<MemberVo> list = new MemberDao().findAll();
		System.out.println("--------------------- 회원 리스트 ---------------------");
		for (MemberVo memberVo : list) {
			System.out.println("이름:" + memberVo.getName() +"\t 전화번호:" + memberVo.getPhoneNumber() + "\t 이메일:" + memberVo.getEmail() + "\t 비밀번호:" + memberVo.getPassword());
		}
		System.out.println("----------------------------------------------------");
	}

	private static void insertMember() {
		MemberVo vo = new MemberVo();
		
		vo.setName("박서준");
		vo.setEmail("seojun@handsome.com");
		vo.setPhoneNumber("010-1234-1234");
		vo.setPassword("handsome");
		new MemberDao().insert(vo);

		vo.setName("강승윤");
		vo.setEmail("seongyoon@winner.com");
		vo.setPhoneNumber("010-1234-4333");
		vo.setPassword("winner");
		new MemberDao().insert(vo);
	}

}
