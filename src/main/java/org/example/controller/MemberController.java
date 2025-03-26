package org.example.controller;

import org.example.util.Util;
import org.example.dto.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController extends Controller {

    private Scanner sc;
    private List<Member> members;
    private String cmd;

    int lastMemberId = 3;
    Member nowMember = null;

    public MemberController(Scanner sc) {
        this.sc = sc;
        members = new ArrayList<>();

    }

    public void doAction(String cmd, String actionMethodName) {
        this.cmd = cmd;

        switch (actionMethodName) {
            case "join":
                doJoin();
                break;
            case "login":
                doLogin();
                break;
            case "logout":
                doLogout();
                break;
            default:
                System.out.println("Unknown action method");
                break;
        }
    }

    private void doLogout() {
        System.out.println(nowMember.getName() +"님 정말 로그아웃 하시겠습니까? 'y/n'");
        String answer = sc.nextLine();
        if (answer.equals("y")) {
            nowMember = null;
        }try{
            System.out.println(nowMember.getName());
        }catch (NullPointerException e){
            System.out.println("로그아웃 되셨습니다.");
        }
    }

    private void doLogin() {

        while (true) {
            System.out.println("아이디입력해봐");
            String loginId = sc.nextLine().trim();
            System.out.println("비번입력해봐");
            String loginPw = sc.nextLine().trim();
            int idcheck = 0;
            for (Member member : members) {
                if (loginId.equals(member.getLoginId())) {
                    nowMember = member;
                    idcheck++;
                    if (loginPw.equals(member.getPassword())) {
                        System.out.println(nowMember.getName() + "님 반갑습니다.로그인 성공");
                        idcheck++;
                        break;
                    }
                }
            }if (idcheck == 0) {
                System.out.println("아이디를 잘못");
                continue;
            }else if (idcheck == 1) {
                System.out.println("비번이 잘못");
                continue;
            }break;
        }
    }

    private void doJoin() {
        System.out.println("==회원가입==");
        int id = lastMemberId + 1;
        String regDate = Util.getNowStr();
        String loginId = null;
        while (true) {
            System.out.print("로그인 아이디 : ");
            loginId = sc.nextLine().trim();
            if (isJoinableLoginId(loginId) == false) {
                System.out.println("이미 사용중이야");
                continue;
            }
            break;
        }
        String password = null;
        while (true) {
            System.out.print("비밀번호 : ");
            password = sc.nextLine().trim();
            System.out.print("비밀번호 확인: ");
            String passwordConfirm = sc.nextLine().trim();

            if (password.equals(passwordConfirm) == false) {
                System.out.println("비번 확인해");
                continue;
            }
            break;
        }

        System.out.print("이름 : ");
        String name = sc.nextLine().trim();

        Member member = new Member(id, regDate, loginId, password, name);
        members.add(member);

        System.out.println(id + "번 회원이 가입되었습니다");
        lastMemberId++;
    }

    private boolean isJoinableLoginId(String loginId) {
        for (Member member : members) {
            if (member.getLoginId().equals(loginId)) {
                return false;
            }
        }
        return true;
    }

    public void makeTestData() {
        System.out.println("==회원 테스트 데이터 생성==");
        members.add(new Member(1, Util.getNowStr(), "test1", "test1", "test1"));
        members.add(new Member(2, Util.getNowStr(), "test2", "test2", "test2"));
        members.add(new Member(3, Util.getNowStr(), "test3", "test3", "test3"));
    }
}