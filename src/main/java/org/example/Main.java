package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Article> articles = new ArrayList<>();
    static List<사람> join = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("==프로그램 시작==");

        int lastArticleId = 4;
        int joinId = 4;
        사람 now = null;

        makeTestData();
        makeTestData2();

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

            if (cmd.equals("article write")) {
                System.out.println("==게시글 작성==");
                int id = lastArticleId + 1;
                String regDate = Util.getNowStr();
                String updateDate = Util.getNowStr();
                System.out.print("제목 : ");
                String title = sc.nextLine().trim();
                System.out.print("내용 : ");
                String body = sc.nextLine().trim();

                Article article = new Article(id, regDate, updateDate, title, body);
                articles.add(article);

                System.out.println(id + "번 글이 작성되었습니다");
                lastArticleId++;
            } else if (cmd.equals("article list")) {
                System.out.println("==게시글 목록==");
                if (articles.size() == 0) {
                    System.out.println("아무것도 없어");
                } else {
                    System.out.println("   번호    /     날짜       /   제목     /    내용   ");
                    for (int i = articles.size() - 1; i >= 0; i--) {
                        Article article = articles.get(i);
                        if (Util.getNowStr().split(" ")[0].equals(article.getRegDate().split(" ")[0])) {
                            System.out.printf("  %d   /    %s        /    %s     /    %s   \n", article.getId(), article.getRegDate().split(" ")[1], article.getTitle(), article.getBody());
                        } else {
                            System.out.printf("  %d   /    %s        /    %s     /    %s   \n", article.getId(), article.getRegDate().split(" ")[0], article.getTitle(), article.getBody());
                        }

                    }
                }
            } else if (cmd.contains("article list ")) {
                String check = cmd.split(" ")[2];
                Article foundArticle = null;
                List<Article> articles1 = new ArrayList<>();// 기존 버전으로 사용하면 번호 / 날짜 / 제목/ 내용을 찍기가 어려워서 그냥 리스트를 새로 마나 짜서 검색어에 걸리는 객체들 다시 저장 시킴
                for (int i = articles.size() - 1; i >= 0; i--) {
                    Article article = articles.get(i);
                    if (article.getTitle().contains(check)) {
                        articles1.add(article);
                        foundArticle = article;
                    }
                }
                if (foundArticle != null) {
                    System.out.println("   번호    /     날짜       /   제목     /    내용   ");
                    for (Article article : articles1) {
                        if (Util.getNowStr().split(" ")[0].equals(article.getRegDate().split(" ")[0])) {
                            System.out.printf("  %d   /    %s        /    %s     /    %s   \n", article.getId(), article.getRegDate().split(" ")[1], article.getTitle(), article.getBody());
                        } else {
                            System.out.printf("  %d   /    %s        /    %s     /    %s   \n", article.getId(), article.getRegDate().split(" ")[0], article.getTitle(), article.getBody());
                        }
                    }
                } else {
                    System.out.println("해당 글이 존재하지 않습니다.");
                }
            } else if (cmd.startsWith("article detail")) {
                System.out.println("==게시글 상세보기==");

                int id = Integer.parseInt(cmd.split(" ")[2]);
                Article foundArticle = Util.fu1(articles, id);


                if (foundArticle == null) {
                    System.out.println("해당 게시글은 없습니다");
                    continue;
                }
                System.out.println("번호 : " + foundArticle.getId());
                System.out.println("작성날짜 : " + foundArticle.getRegDate());
                System.out.println("수정날짜 : " + foundArticle.getUpdateDate());
                System.out.println("제목 : " + foundArticle.getTitle());
                System.out.println("내용 : " + foundArticle.getBody());

            } else if (cmd.startsWith("article delete")) {
                System.out.println("==게시글 삭제==");

                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = Util.fu1(articles, id);

                if (foundArticle == null) {
                    System.out.println("해당 게시글은 없습니다");
                    continue;
                }
                articles.remove(foundArticle);
                System.out.println(id + "번 게시글이 삭제되었습니다");
            } else if (cmd.startsWith("article modify")) {
                System.out.println("==게시글 수정==");

                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = Util.fu1(articles, id);

                if (foundArticle == null) {
                    System.out.println("해당 게시글은 없습니다");
                    continue;
                }
                System.out.println("기존 제목 : " + foundArticle.getTitle());
                System.out.println("기존 내용 : " + foundArticle.getBody());
                System.out.print("새 제목 : ");
                String newTitle = sc.nextLine().trim();
                System.out.print("새 내용 : ");
                String newBody = sc.nextLine().trim();

                foundArticle.setTitle(newTitle);
                foundArticle.setBody(newBody);

                foundArticle.setUpdateDate(Util.getNowStr());

                System.out.println(id + "번 게시글이 수정되었습니다");
                //중복확인 해야함
            } else if (cmd.equals("회원가입")) {
                int id = joinId + 1;
                String regDate = Util.getNowStr();
                String loginId;
                String loginPw;
                String loginPwCheck;
                String name;

                System.out.println("회원가입할 id를 입력해주세요");
                while (true) {
                    int idCheck = 0;
                    loginId = sc.nextLine().trim();
                    for (사람 person : join) {
                        if (person.getLoginId().equals(loginId)) {
                            System.out.println("중복된 아이디 입니다.  다시 입력해 주세요");
                            idCheck = 1;
                        }
                    }
                    if (idCheck == 0) {
                        System.out.println("사용 가능 아이디");
                        break;
                    }
                }

                System.out.println("회원가입할 비밀번호를 입력해주세요");
                loginPw = sc.nextLine().trim();
                while (true) {
                    System.out.println("비밀번호 확인을 위해 동일한 비밀번호를 다시 입력해주세요");
                    loginPwCheck = sc.nextLine().trim();
                    if (loginPw.equals(loginPwCheck)) {
                        System.out.println("비밀번호가 동일합니다.");
                        break;
                    } else {
                        System.out.println("비밀번호가 동일하지 않습니다.");
                    }
                }
                System.out.println("이름을 입력해주세요.");
                name = sc.nextLine().trim();
                사람 person = new 사람(id, regDate, loginId, loginPw, name);
                join.add(person);
            } else if (cmd.equals("정보")) {
                for (사람 person : join) {
                    System.out.println("회원 번호 : " + person.getId() + "  회원 아이디 : " + person.getLoginId() + "  회원 비밀번호 : " + person.getLoginPw() + "  회원 이름" + person.getName() + "  회원 가입 날짜 : " + person.getRegDate());
                }

            } else if (cmd.equals("로그인 정보")) {
                System.out.println("회원 번호 : " + now.getId() + "  회원 아이디 : " + now.getLoginId() + "  회원 비밀번호 : " + now.getLoginPw() + "  회원 이름 " + now.getName() + "  회원 가입 날짜 : " + now.getRegDate());
            } else if (cmd.equals("login")) {
                while (true) {
                    System.out.print("아이디 입력 : ");
                    String logid = sc.nextLine().trim();
                    System.out.print("비밀번호 입력 :");
                    String logpw = sc.nextLine().trim();
                    int idCheck = 0;
                    for (사람 person : join) {
                        if (person.getLoginId().equals(logid) && person.getLoginPw().equals(logpw)) {
                            now = person;
                            idCheck = 1;
                            System.out.println(now.name);
                        }


                    }
                    if (idCheck == 0) {
                        System.out.println("로그인 정보가 틀립니다 다시 입력");
                        continue;
                    }
                    break;

                }
                System.out.println(now.name + "님 로그인 되셨습니다.");
            } else if (cmd.equals("logout")) {
                System.out.println(now.name + "님 정말 로그아웃 하시겠습니까? 'y/n'");
                String answer = sc.nextLine().trim();
                if (answer.equals("y")) {
                    now = null;
                    try {
                        System.out.println(now.name);
                    } catch (NullPointerException e) {
                        System.out.println("로그아웃 되었습니다.");
                    }
                }

            } else {
                System.out.println("사용할 수 없는 명령어입니다");
            }
        }
        System.out.println("==프로그램 끝==");
        sc.close();

    }

    /**
     * 테스트 데이터 생성 함수
     **/
    private static void makeTestData() {
        System.out.println("==테스트 데이터 생성==");
        articles.add(new Article(1, "2024-12-12 12:12:12", "2024-12-12 12:12:12", "제목1", "내용1"));
        articles.add(new Article(2, Util.getNowStr(), Util.getNowStr(), "목2", "내용2"));
        articles.add(new Article(3, Util.getNowStr(), Util.getNowStr(), "제목3", "내용3"));
        articles.add(new Article(4, Util.getNowStr(), Util.getNowStr(), "4", "내용4"));

    }

    private static void makeTestData2() {
        System.out.println("==테스트 데이터 생성==");
        join.add(new 사람(1, "2024-12-12 12:12:12", "asd", "123", "김영수"));
        join.add(new 사람(2, Util.getNowStr(), "dsa", "123", "김철수"));
        join.add(new 사람(3, Util.getNowStr(), "fff", "123", "개똥이"));
        join.add(new 사람(4, Util.getNowStr(), "ccc", "123", "소똥이"));

    }
}

class 사람 {
    int id;
    String regDate;
    String loginId;
    String loginPw;
    String name;

    public 사람(int id, String regDate, String loginId, String loginPw, String name) {
        this.id = id;
        this.regDate = regDate;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginPw() {
        return loginPw;
    }

    public void setLoginPw(String loginPw) {
        this.loginPw = loginPw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

class Article {
    private int id;
    private String regDate;
    private String updateDate;
    private String title;
    private String body;

    public Article(int id, String regDate, String updateDate, String title, String body) {
        this.id = id;
        this.regDate = regDate;
        this.updateDate = updateDate;
        this.title = title;
        this.body = body;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}