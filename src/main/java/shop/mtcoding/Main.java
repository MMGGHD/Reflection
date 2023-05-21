package shop.mtcoding;

import shop.mtcoding._core.ComponentScanner;
import shop.mtcoding._core.DispatcherServlet;

import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws Exception {
        // URI 입력 받기
        Scanner sc = new Scanner(System.in);
        String uri = sc.nextLine();

        // 컴포넌트 스캔
        Set<Class> classes = ComponentScanner.scanClass("shop");

        // 입력받은 URI로 메소드 호출
        DispatcherServlet.findUri(classes, uri);
    }

}