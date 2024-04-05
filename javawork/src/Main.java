import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++)  a[i] = sc.nextInt();
        int x = sc.nextInt();
        Arrays.sort(a);
        int ret = 0;
        int l = 0, r = n - 1;
        while (l < r) {
            if(a[l] + a[r] < x) l++;
            else if(a[l] + a[r] > x) r--;
            else {r--; ret++;}
        }
        System.out.println(ret);
    }
}
/**
 * 이분탐색
 * dp 개념익히는데 시간쓰고
 * 시뮬레이션은 나중에 시험직전에 이해하면서 처보자
 */