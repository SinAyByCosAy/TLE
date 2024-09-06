package DPBootcamp.Templates;

import java.util.*;

public class ImpFunctions {

    //Random value generator from [a, b]
    int rand(int a, int b){
        return (int) ((Math.random() * (b - a)) + a);
    }

    //gcd
    public static int gcd(int a, int b){
        return b == 0 ? a : gcd(b, a % b);
    }

    //comparator
    public static void sortList(List<Integer> arr){
        Collections.sort(arr, (a, b) -> Integer.compare(a, b));
    }

    public static void sortArray(Integer[] arr){
        Arrays.sort(arr, (a, b) -> Integer.compare(a, b)); //requires an object type
    }

    //hashmap iterator
    public static void hashMapIterator(HashMap<Integer, Integer> hm){
        for (Map.Entry<Integer, Integer> ele : hm.entrySet()) {
            System.out.println(ele.getKey() + " " + ele.getValue());
        }
    }


    //Modulo division when mod value is prime
    //(x / y) % mod = [x * y ^ (mod - 2)] % mod
    static int mod = 1000 * 1000 * 1000 + 7;
    public static int mul(int x, int y){
        return (int)((x * 1l * y) % mod);
    }
    public static int power(int x, int y){
        int ans = 1;
        while(y > 0){
            if((y & 1) == 1) ans = mul(ans, x);
            x = mul(x, x);
            y = y >> 1;
        }
        return ans;
    }
    public static int divide(int x, int y){
        return mul(x, power(y, (mod - 2)));
    }


    //A^N, Binary Exponentiation
    public static long recurAPowerN(int a, int n){
        if(n == 0)
            return 1;

        long res = recurAPowerN(a, n / 2);
        if(n % 2 == 1)
            res *= 1l * res * a;
        else
            res *= 1l * res;
        //res %= mod
        return res;
    }

    public static long vanillaAPowerN(int a, int n){
        long res = 1;
        while(n >  0){
            if((n & 1) == 1){
                res *= 1l * a; //res %= mod
            }
            a *= a; //a %= mod
            n >>= 1;
        }
        return res;
    }


    //Euler totient value
    public static int phi(int n){//can be also done using SPF
        //TC: O(sqrt(N))
        int result = n;
        for(int i = 2; i * i <= n; i++){
            if(n % i == 0){
                result -= result / i;
                while(n % i == 0)
                    n /= i;
            }
        }
        if(n > 1)
            result -= result / n;

        return result;
    }

    public static int[] phiOneToN(int n){
        //similar to sieve or SPF construction logic, TC: O(N * log(logN)) ~ O(N)
        int[] phiValues = new int[n + 1];
        for(int i = 1; i <= n; i++)
            phiValues[i] = i;

        for(int i = 2; i <= n; i++){
            if(phiValues[i] == i){//found a prime
                for(int j = i; j <= n; j += i)//contribution to all the multiples of prime
                    phiValues[j] -= phiValues[j] / i;
            }
        }
        return phiValues;
    }
}
