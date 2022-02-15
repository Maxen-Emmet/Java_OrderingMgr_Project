/**
 * @Description： PACKAGE_NAME
 * @Author：LiPeng , YJ
 * @creationTime：2021/11/14
 */
public class OrderingMgr {
    public static void main(String[] args) {
        OrderingUtil OU = new OrderingUtil();
        OU.startMenu();
        OU.initial();
        OU.add();
        OU.sign();
        OU.delete();
        OU.praise();
        OU.display();
    }
}
