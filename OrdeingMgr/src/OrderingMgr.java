/**
 * @Description： PACKAGE_NAME
 * @Author：LiPeng,YangJiHan
 * @creationTime：2021/11/14
 * @JDK 1.8
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
