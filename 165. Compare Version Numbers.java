public class Solution {
    public int compareVersion(String version1, String version2) {
        version1 = version1.replace(".", " ");
        version2 = version2.replace(".", " ");
        String[] versions1 = version1.split(" ");
        String[] versions2 = version2.split(" ");
        int length1 = versions1.length;
        int length2 = versions2.length;

        int max = Math.max(length1, length2);
        int index = 0;

        while (index < max) {
            int beta1 = 0;
            int beta2 = 0;

            if (index < length1) {
                beta1 = Integer.valueOf(versions1[index]);
            }
            if (index < length2) {
                beta2 = Integer.valueOf(versions2[index]);
            }

            if (beta1 > beta2) {
                return 1;
            } else if (beta1 < beta2) {
                return -1;
            } else {
                index++;
            }
        }
        return 0;
    }
}