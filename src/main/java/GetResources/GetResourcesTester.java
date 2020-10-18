package GetResources;

import java.net.URL;

/**
 *
 * resource0 = file:/Users/cuiguiyang/open-source/min2phase/target/classes/ui/MainProgram.class
 * resource1 = file:/Users/cuiguiyang/open-source/min2phase/target/classes/cs/min2phase/Tools.class
 * ================= resource2 =
 * file:/Users/cuiguiyang/open-source/min2phase/target/classes/GetResources/ resource3 =
 * file:/Users/cuiguiyang/open-source/min2phase/target/classes/ resource4 =
 * file:/Users/cuiguiyang/open-source/min2phase/target/classes/ resource5 = null =================
 * resource0 = file:/Users/cuiguiyang/open-source/min2phase/target/classes/GetResources/ resource1 =
 * file:/Users/cuiguiyang/open-source/min2phase/target/classes/ resource2 =
 * file:/Users/cuiguiyang/open-source/min2phase/target/classes/ resource3 = null
 *
 * @author cuiguiyang
 * @since 2020/10/18 21:38
 */
public class GetResourcesTester {

    public static void main(String[] args){
        GetResourcesTester tester = new GetResourcesTester();
        tester.testGetResources();

        System.out.println(" ================= ");

        URL resource0 = GetResourcesTester.class.getResource("");
        System.out.println("resource0 = " + resource0);
        URL resource1 = GetResourcesTester.class.getResource("/");
        System.out.println("resource1 = " + resource1);
        URL resource2 = GetResourcesTester.class.getClassLoader().getResource("");
        System.out.println("resource2 = " + resource2);
        URL resource3 = GetResourcesTester.class.getClassLoader().getResource("/");
        System.out.println("resource3 = " + resource3);
    }

    public void testGetResources() {
        URL resource0 = this.getClass().getResource("../ui/MainProgram.class");
        System.out.println("resource0 = " + resource0);
        URL resource1 = this.getClass().getResource("/cs/min2phase/Tools.class");
        System.out.println("resource1 = " + resource1);

        System.out.println(" ================= ");

        URL resource2 = this.getClass().getResource("");
        System.out.println("resource2 = " + resource2);
        URL resource3 = this.getClass().getResource("/");
        System.out.println("resource3 = " + resource3);

        URL resource4 = this.getClass().getClassLoader().getResource("");
        System.out.println("resource4 = " + resource4);
        URL resource5 = this.getClass().getClassLoader().getResource("/");
        System.out.println("resource5 = " + resource5);
    }
}
