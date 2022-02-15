import java.util.Scanner;

/**
 * @Description： PACKAGE_NAME
 * @Author：LiPeng,YangJiHan
 * @creationTime：
 * @JDK 1.8
 */
public class OrderingUtil {
    Scanner input = new Scanner(System.in);
    String[] dishNames = new String[]{"红烧带鱼", "鱼香肉丝", "时令鲜蔬"};
    double[] prices = new double[]{38.0, 20.0, 10.0};
    int[] praiseNums = new int[3];
    OrderingSet oSet = new OrderingSet();

    public void initial() {
        oSet.names[0] = "彭新宇";
        oSet.dishMegs[0] = "红烧牛肉 2份";
        oSet.times[0] = 3;
        oSet.addresses[0] = "深圳宝安";
        oSet.states[0] = 1;
        oSet.sumPrice[0] = 76.0;
        oSet.names[1] = "刘志绫";
        oSet.dishMegs[1] = "鱼香肉丝 2份";
        oSet.times[1] = 10;
        oSet.addresses[1] = "深圳宝安";
        oSet.states[1] = 0;
        oSet.sumPrice[1] = 45.0;
        System.out.println("name\tdishMegs\ttimes\taddresses\tstates\tsumPrices");
        System.out.println(oSet.names[0] + "\t" + oSet.dishMegs[0] + "\t" + oSet.times[0] + "\t" + oSet.addresses[0] + "\t" + oSet.states[0] + "\t" + oSet.sumPrice[0]);
        System.out.println(oSet.names[1] + "\t" + oSet.dishMegs[1] + "\t" + oSet.times[1] + "\t" + oSet.addresses[1] + "\t" + oSet.states[1] + "\t" + oSet.sumPrice[1]);
    }

    public void startMenu() {
        int num;
        boolean isExit = false;
        System.out.println("欢迎使用“吃货联盟订单系统”");
        do {
            System.out.println("***********************");
            System.out
                    .println("1.我要订餐\n2.查看餐袋\n3.签收订单\n4.删除订单\n5.我要点赞\n6.退出系统");
            System.out.println("***********************");
            System.out.println("请选择:");
            num = input.nextInt();
            switch (num) {
                case 1:
                    System.out.println("***我要订餐***");
                    add();
                    break;
                case 2:
                    System.out.println("***查看餐袋***");
                    display();
                    break;
                case 3:
                    System.out.println("***签收订单***");
                    sign();
                    break;
                case 4:
                    System.out.println("***删除订单***");
                    delete();
                    break;
                case 5:
                    System.out.println("***我要点赞***");
                    praise();
                    break;
                default:
                    System.out.println("谢谢使用,欢迎下次光临!");
                    isExit = true;
                    break;
            }
            if (!isExit) {
                System.out.println("输入0返回:");
                num = input.nextInt();
            } else {
                break;
            }
        } while (num == 0);
    }

    public void display() {
        System.out.println("序号\t订餐人\t餐品信息\t\t送餐时间\t送餐地址\t\t总金额\t订单状态");
        for (int i = 0; i < oSet.names.length; i++) {
            if (oSet.names[i] != null) {
                String state = (oSet.states[i] == 0) ? "已预订" : "已完成";
                String date = oSet.times[i] + "点";
                String sumPrice = oSet.sumPrice[i] + "元";
                System.out.println((i + 1) + "\t" + oSet.names[i] + "\t"
                        + oSet.dishMegs[i] + "\t" + date + "\t"
                        + oSet.addresses[i] + "\t" + sumPrice + "\t" + state);
            }
        }
    }

    public boolean add() {
        boolean isAdd = false;
        for (int j = 0; j < oSet.names.length; j++) {
            if (oSet.names[j] == null) {
                isAdd = true;
                System.out.println("请输入订餐人姓名:");
                String name = input.next();
                System.out.println("序号\t菜名\t单价\t点赞数");
                for (int i = 0; i < dishNames.length; i++) {
                    String price = prices[i] + "元";
                    String priaiseNum = (praiseNums[i] > 0 ? praiseNums[i]
                            + "赞" : "0");
                    System.out.println((i + 1) + "\t" + dishNames[i] + "\t"
                            + price + "\t" + priaiseNum);
                }
                System.out.println("请选择您要点的菜品编号:");
                int chooseDish = input.nextInt();
                System.out.println("请选择您需要的份数:");
                int number = input.nextInt();
                String dishMeg = dishNames[chooseDish - 1] + " " + number + "份";
                double sumPrice = prices[chooseDish - 1] * number;
                double deliCharge = (sumPrice > 50) ? 0 : 5;
                System.out.println("请输入送餐时间(送餐时间是10点至20点间整点送餐:)");
                int time = input.nextInt();
                while (time < 10 || time > 20) {
                    System.out.println("您的输入有误,请输入10~20间的整数!");
                    time = input.nextInt();
                }
                System.out.println("请输入送餐地址:");
                String address = input.next();
                System.out.println("订餐成功!");
                System.out.println("您订的是:" + dishMeg);
                System.out.println("送餐时间:" + time + "点");
                System.out.println("餐费:" + sumPrice + "元,送餐费:" + deliCharge
                        + "总计:" + (+sumPrice + deliCharge) + "元");
                oSet.names[j] = name;
                oSet.dishMegs[j] = dishMeg;
                oSet.times[j] = time;
                oSet.addresses[j] = address;
                oSet.sumPrice[j] = sumPrice;
                break;
            }
        }
        if (!isAdd) {
            System.out.println("对不起,您的餐袋已满");
            return false;
        }
        return true;
    }

    public void sign() {
        boolean isSignFind = false;
        System.out.println("请选择要签收的订单号:");
        int signOrderId = input.nextInt();
        for (int i = 0; i < oSet.names.length; i++) {
            if (oSet.names != null && oSet.states[i] == 0
                    && i == signOrderId - 1) {
                oSet.states[i] = 1;
                System.out.println("订单签收成功");
                isSignFind = true;
            } else if (oSet.names != null && oSet.states[i] == 1
                    && i == signOrderId - 1) {
                System.out.println("您选择的订单已签收,不能再次签收!");
                isSignFind = true;
            }
        }
        if (!isSignFind) {
            System.out.println("您选择的订单不存在!");
        }
    }

    public void delete() {
        boolean isDelFind = false;
        System.out.println("请输入要删除的订单序号:");
        int delId = input.nextInt();
        for (int i = 0; i < oSet.names.length; i++) {
            if (oSet.states[i] == 1 && delId == i + 1 && oSet.names[i] != null) {
                isDelFind = true;
                for (int j = 0; j < oSet.names.length; j++) {
                    oSet.names[j] = oSet.names[j + 1];
                    oSet.dishMegs[j] = oSet.dishMegs[j + 1];
                    oSet.times[j] = oSet.times[j + 1];
                    oSet.addresses[j] = oSet.addresses[j + 1];
                    oSet.states[j] = oSet.states[j + 1];
                    oSet.sumPrice[j] = oSet.sumPrice[j + 1];
                }
                int endIndex = oSet.names.length - 1;
                oSet.names[endIndex] = null;
                oSet.dishMegs[endIndex] = null;
                oSet.times[endIndex] = 0;
                oSet.addresses[endIndex] = null;
                oSet.states[endIndex] = 0;
                oSet.sumPrice[endIndex] = 0;
                System.out.println("删除订单成功");
                break;
            } else if (oSet.states[i] == 0 && delId == i + 1 && oSet.names != null) {
                System.out.println("您选择的订单未签收,不能删除!");
                isDelFind = true;
                break;
            }
        }
        if (!isDelFind) {
            System.out.println("您要删除的订单不存在!");
        }
    }

    public void praise() {
        System.out.println("序号\t菜名\t单价");
        for (int i = 0; i < dishNames.length; i++) {
            String price = prices[i] + "元";
            String priaiseNum = (praiseNums[i] > 0 ? praiseNums[i] + "赞" : " ");
            System.out.println((i + 1) + "\t" + dishNames[i] + "\t" + prices[i]);
        }
        System.out.println("请选择您要点赞的菜品编号:");
        int priaiseNum = input.nextInt();
        praiseNums[priaiseNum - 1]++;
        System.out.println("点赞成功!");
    }
}