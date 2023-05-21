package shop.mtcoding;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // 컨트롤러 메모리에 띄우기
        UserController uc = new UserController();

        // URI 입력 받기
        Scanner sc = new Scanner(System.in);
        System.out.println("URI를 입력하세요.");
        String path = sc.nextLine();

        // 입력받은 URI에 따라 원하는 메소드 호출
        if(path.equals("/login")){
            uc.login();
        }else if(path.equals("/join")){
            uc.join();
        }
    }
}