package shop.mtcoding.controller;

import shop.mtcoding.annotation.Controller;
import shop.mtcoding.annotation.RequestMapping;

@Controller
public class BoardController {

    @RequestMapping(uri = "/save")
    public void save(){
        System.out.println("save 호출됨");
    }

}
