package shop.mtcoding;

import shop.mtcoding.annotation.RequestMapping;
import shop.mtcoding.controller.UserController;

import java.util.Scanner;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Main {

    // 리플렉션을 적용하여 URI 찾기
    public static void findUri(UserController uc, String uri) throws Exception {
        // URI를 찾았는지 여부를 판단하는 변수
        boolean isFind = false;

        // Usercontroller의 선언된 메소드들을 가져오는 메소드
        Method[] methods = uc.getClass().getDeclaredMethods();

        // 메소드를 하나씩 체크하는 반복문
        for (Method mt : methods) {
            // 메소드에 RequestMapping 어노테이션을 가져오는 메소드
            Annotation anno = mt.getDeclaredAnnotation(RequestMapping.class);
            RequestMapping rm = (RequestMapping) anno;

            // RequestMapping의 uri 변수와 입력받은 URI가 동일하다면...
            if (rm.uri().equals(uri)) {
                isFind = true;

                // 해당 메소드 호출
                mt.invoke(uc);
            }
        }
        // URI를 찾았는지 여부를 판단하는 변수가 거짓이라면
        if(isFind == false){
            // 404 출력
            System.out.println("404 Not Found");
        }
    }

    public static void main(String[] args) throws Exception {
        // URI 입력 받기
        Scanner sc = new Scanner(System.in);
        String uri = sc.nextLine();

        // 입력받은 URI에 따라 원하는 메소드 호출
        findUri(new UserController(), uri);

    }

}