package org.example.artcleManager;

import org.example.controller.ArticleController;
import org.example.controller.Controller;
import org.example.controller.MemberController;

import java.util.Scanner;

public class App {

    public void run() {

        Scanner sc = new Scanner(System.in);

        System.out.println("==프로그램 시작==");

        MemberController memberController = new MemberController(sc);
        ArticleController articleController = new ArticleController(sc);

        articleController.makeTestData();
        memberController.makeTestData();

        Controller controller = null;

        while (true) {
            System.out.print("명령어) ");
            String cmd = sc.nextLine().trim();

            if (cmd.length() == 0) {
                System.out.println("명령어를 입력하세요");
                continue;
            }
            if (cmd.equals("exit")) {
                break;
            }

            String[] cmdBits = cmd.split(" "); //명령어 받아서 자름

            String controllerName = cmdBits[0]; //어디로 갈건지 정하는거 ex)Article 이냐 Member이냐

            if (cmdBits.length == 1) {// 잘못된 입력하면 걸러냄
                System.out.println("명령어 확인 필요");
                continue;
            }

            String actionMethodName = cmdBits[1]; // 행동 정하는거 ex)list나 write 같은거
// article이냐 member이냐 만 가르고 나머지 명령어는 해당 컨트롤러로 들어가서 작업하는 형식
            if (controllerName.equals("article")) {
                controller = articleController;
            } else if (controllerName.equals("member")) {
                controller = memberController;
            } else {
                System.out.println("지원하지 않는 기능입니다");
                continue;
            }

            controller.doAction(cmd, actionMethodName);
        }

        System.out.println("==프로그램 끝==");
        sc.close();
    }
}