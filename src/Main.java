/**
 * Created with IntellJ IDEA.
 * User: lhl
 * Date: 2019-02-20
 * Time: 18:58
 * Description:
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int ret = 0;
        while (in.hasNextInt()) {
            int x = in.nextInt();
            int y = in.nextInt();
            ret = x + y;
            System.out.println(ret);
        }
    }
}
