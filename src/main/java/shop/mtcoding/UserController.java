package shop.mtcoding;

public class UserController {

    @RequestMapping(uri = "/login")
    public void login(){
        System.out.println("login() 호출됨");
    }

    @RequestMapping(uri = "/join")
    public void join(){
        System.out.println("join() 호출됨");
    }
}
