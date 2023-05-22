package shop.mtcoding._core;

import shop.mtcoding.annotation.Controller;
import shop.mtcoding.annotation.RequestMapping;
import shop.mtcoding.annotation.ResponseBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

public class DispatcherServlet {
    // URI 찾기
    public static void findUri(Set<Class> classes, String uri) throws Exception {
        // URI를 찾았는지 여부를 판단하는 변수
        boolean isFind = false;

        for (Class cls : classes) {

            // 해당 클래스가 Controller 어노테이션을 가지고 있을 경우
            if (cls.isAnnotationPresent(Controller.class)) {
                // 해당 클래스 인스턴스
                Object instance = cls.newInstance();

                // 클래스의 메소드 배열에 저장
                Method[] methods = cls.getDeclaredMethods();

                for (Method mt : methods) {
                    // 해당 메소드에 RequestMapping 어노테이션 갖고오기
                    Annotation anno = mt.getDeclaredAnnotation(RequestMapping.class);
                    RequestMapping rm = (RequestMapping) anno;

                    // 매개변수로 전달받은 URI와 RequestMapping의 uri 변수가 동일한지 확인
                    if (rm.uri().equals(uri)) {
                        isFind = true;

                        // ResponseBody 어노테이션이 붙어있으면 메시지 컨버터 발동
                        if (mt.isAnnotationPresent(ResponseBody.class)) {
                            Object result = mt.invoke(instance);
                            useMessageConvert(result);
                        }

                        // 동일하다면 해당 메소드 호출
                        else {
                            String fileName = (String) mt.invoke(instance);
                            ViewResolver.findHTML(fileName);
                        }
                    }
                }
            }
        }
        if(isFind == false){
            System.out.println("404 Not Found");
        }
    }

    private static void useMessageConvert(Object object) {
        System.out.println("MessageConvert : "+MessageConverter.convert(object));
    }
}
