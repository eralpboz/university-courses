package lab6;

public class BigInteger implements Comparable<BigInteger> {
    private int[] numbers;

    public BigInteger(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                throw new IllegalArgumentException("Input must have only digits.");
            }
        }
        if (input.length() > 200) {
            input = input.substring(input.length() - 200);
        }

        numbers = new int[input.length()];

        int stringIndex = input.length() - 1;
        for (int i = 0; i < this.numbers.length; i++) {
            this.numbers[i] = Character.getNumericValue(input.charAt(stringIndex));
            stringIndex--;
        }

    }

    private BigInteger(int[] numbers) {
        this.numbers = numbers;
    }
    public int[] getNumbers(){
        return numbers;
    }

    public BigInteger add(BigInteger addend) {
        boolean x = false;
        int max = Math.max(this.numbers.length, addend.numbers.length);
        int bound = Math.min(this.numbers.length, addend.numbers.length);

        int[] sum = new int[max + 1];

        for (int i = 0; i < bound; i++) {
            int digitsSum = this.numbers[i] + addend.numbers[i];
            if (x) {
                digitsSum++;
            }
            if (digitsSum >= 10) {
                sum[i] = digitsSum - 10;
                x = true;
            } else {
                sum[i] = digitsSum;
                x = false;
            }
        }

        int[] longerArray;

        if (this.numbers.length >= addend.numbers.length) {
            longerArray = this.numbers;
        } else {
            longerArray = addend.numbers;
        }

        for (int i = bound; i < max; i++) {
            int val = longerArray[i];
            if (x) {
                val++;
            }
            if (val >= 10) {
                sum[i] = val - 10;
                x = true;
            } else {
                sum[i] = val;
                x = false;
            }
        }

        if (x) {
            sum[max] = 1;
        }

        return makeArrayBigInteger(sum);
    }

    public BigInteger subtract(BigInteger subtrahend) {

        if (this.compareTo(subtrahend) < 0) {
            throw new ArithmeticException("The result cannot be negative.");
        }

        int[] diff = new int[this.numbers.length];
        boolean x = false;

        for (int i = 0; i < this.numbers.length; i++) {
            int val1 = this.numbers[i];
            int val2 = (i < subtrahend.numbers.length) ? subtrahend.numbers[i] : 0;

            if (x)
                val1--;

            if (val1 < val2) {
                val1 = val1 + 10;
                x = true;
            } else {
                x = false;
            }

            diff[i] = val1 - val2;
        }

        return makeArrayBigInteger(diff);
    }

    public BigInteger multiply(BigInteger multiplier) {
        if (this.numbers.length == 1 && this.numbers[0] == 0 || multiplier.numbers.length == 1 && multiplier.numbers[0] == 0){
            return new BigInteger("0");}
        int numberOfDigit = Math.max(this.numbers.length, multiplier.numbers.length);
        if (numberOfDigit <= 20) {
            return multiplyIterative(multiplier);
        } else {
            return multiplyKaratsuba(multiplier);
        }
    }

    public BigInteger multiplyKaratsuba(BigInteger other) {

        int m = Math.max(this.numbers.length, other.numbers.length) / 2;

        BigInteger low1 = this.getLowerHalf(m);
        BigInteger high1 = this.getUpperHalf(m);
        BigInteger low2 = other.getLowerHalf(m);
        BigInteger high2 = other.getUpperHalf(m);

        BigInteger z0 = low1.multiply(low2);
        BigInteger z2 = high1.multiply(high2);

        BigInteger sum1 = low1.add(high1);
        BigInteger sum2 = low2.add(high2);
        BigInteger z1 = sum1.multiply(sum2);

        BigInteger middleTerm = z1.subtract(z2).subtract(z0);

        BigInteger term1 = z2.multiplyTen(2 * m);
        BigInteger term2 = middleTerm.multiplyTen(m);

        return term1.add(term2).add(z0);
    }

    

    private BigInteger multiplyTen(int n) {
        if (n == 0){
            return this;}
        if (this.numbers.length == 1 && this.numbers[0] == 0){
            return this;}

        int[] newNumbers = new int[this.numbers.length + n];
        for (int i = 0; i < this.numbers.length; i++) {
            newNumbers[i + n] = this.numbers[i];
        }

        return makeArrayBigInteger(newNumbers);
    }

    private BigInteger getLowerHalf(int m) {

        int len = Math.min(m, this.numbers.length);

        if (len <= 0){
            return new BigInteger("0");}

        int[] lower = new int[len];

        for (int i = 0; i < len; i++) {
            lower[i] = this.numbers[i];
        }

        return makeArrayBigInteger(lower);
    }

    private BigInteger getUpperHalf(int m) {

        if (this.numbers.length <= m) {
            return new BigInteger("0");
        }

        int len = this.numbers.length - m;
        int[] upper = new int[len];

        for (int i = 0; i < len; i++) {
            upper[i] = this.numbers[i + m];
        }

        return makeArrayBigInteger(upper);
    }

    public BigInteger multiplyIterative(BigInteger multiplier) {
        int n = this.numbers.length;
        int m = multiplier.numbers.length;
        int[] result = new int[n + m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int product = this.numbers[i] * multiplier.numbers[j];
                int multiply = i + j;

                int sum = product + result[multiply];
                result[multiply] = sum % 10;
                result[multiply + 1] += sum / 10;
            }
        }
        return makeArrayBigInteger(result);
    }

    public BigInteger makeArrayBigInteger(int[] arr) {
        
        int lengtWithoutZeros = arr.length;
        while (lengtWithoutZeros > 1 && arr[lengtWithoutZeros - 1] == 0) {
            lengtWithoutZeros--;
        }

        
        int[] trimmedArr = new int[lengtWithoutZeros];

        
        for (int i = 0; i < lengtWithoutZeros; i++) {
            trimmedArr[i] = arr[i];
        }

        return new BigInteger(trimmedArr);
    }

    public int compareTo(BigInteger other) {
        if (this.numbers.length > other.numbers.length) {
            return 1;
        } else if (this.numbers.length < other.numbers.length) {
            return -1;
        }
        for (int i = this.numbers.length - 1; i >= 0; i--) {
            if (this.numbers[i] > other.numbers[i]) {
                return 1;
            } else if (this.numbers[i] < other.numbers[i]) {
                return -1;
            }
        }
        return 0;
    }

    public String toString() {
        String numbers = "";
        boolean leadingZero = true;

        for (int i = this.numbers.length - 1; i >= 0; i--) {
            if (leadingZero && this.numbers[i] == 0 && i > 0) {
                continue;
            }
            leadingZero = false;
            numbers = numbers + this.numbers[i];
        }
        return numbers;
    }
}