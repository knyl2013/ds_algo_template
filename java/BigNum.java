static class BigNum {
        static String add(String a, String b)
        {
            StringBuilder sb = new StringBuilder();
            int i = a.length() - 1, j = b.length() - 1;
            int carry = 0;
            while (i >= 0 || j >= 0 || carry > 0) {
                int av = i >= 0 ? a.charAt(i--) - '0' : 0;
                int bv = j >= 0 ? b.charAt(j--) - '0' : 0;
                int val = av + bv + carry;
                carry = val / 10;
                val %= 10;
                sb.append(val);
            }
            return sb.reverse().toString();
        }
    }
