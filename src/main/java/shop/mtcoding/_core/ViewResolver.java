package shop.mtcoding._core;


import java.io.*;
import java.net.URL;

public class ViewResolver {

    public static void findHTML(String fileName) throws Exception {

        // 현재 패키지 위치를 받아옴
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        // 매개변수로 전달받은 패키지 위치
        URL packageUrl = classLoader.getResource("shop");

        // 지정한 패키지의 모든 파일 불러오기
        File packageDirectory = new File(packageUrl.toURI());

        // 상위 패키지로 이동
        File resourceDirectory = packageDirectory.getParentFile().getParentFile().getParentFile().getParentFile();

        // 패키지의 클래스 스캔
        scanPackage("resource/main/templates", resourceDirectory,fileName+".html");

    }

    private static void scanPackage(String pkg, File directory, String htmlName) throws Exception {

        for (File file : directory.listFiles()) {
            String fileName = file.getName();

            // 해당 파일이 패키지일경우
            if (file.isDirectory()) {

                // 재귀적으로 사용하여 하위 패키지를 스캔한다.
                scanPackage(pkg + "." + fileName, file, htmlName);

            // 해당 파일이 html 일경우
            } else if (fileName.endsWith(".html")) {

                if (htmlName.equals(fileName)) {
                    System.out.println(convert(file.getAbsolutePath()));
                }

            }
        }

    }

    private static String convert(String HTMLFilePath) throws Exception {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(HTMLFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        }
        return contentBuilder.toString();
    }
}
