//Q135807 숫자 카드 나누기
import java.util.Arrays;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;

        int aMin = getMin(arrayA);
        int aGcd = getArraysGcd(arrayA, aMin);

        int bMin = getMin(arrayB);
        int bGcd = getArraysGcd(arrayB, bMin);

        answer = getAnswer(aGcd, arrayB, answer);
        answer = getAnswer(bGcd, arrayA, answer);

        return answer;
    }

    private static int getAnswer(int gcd, int[] array, int answer) {
        for (int num : array) {
            if (num % gcd == 0) {
                return Math.max(answer, 0);//무슨 논리지?왜 바로 리턴하지?
            }
        }
        return Math.max(answer, gcd);//num % gcd != 0이 되어 여기까지 오면 answer과 gcd중 더 큰 값을 리턴.
    }

    private static int getMin(int[] array) {
        return Arrays.stream(array)
                .reduce(Math::min)
                .getAsInt();
    }

    private static int getArraysGcd(int[] array, int min) {
        int gcd = 1;
        for (int i = 2; i <= min; i++) {
            if (isGcd(array, i)) {
                gcd = i;
            }
        }
        return gcd;
    }

    private static boolean isGcd(int[] array, int i) {
        for (int num : array) {
            if (num % i != 0) {
                return false;
            }
        }
        return true;
    }
}