package edu.hw1;

public final class Task7 {
    private Task7() {
    }

    public static int rotateLeft(int n, int shift) {
        return Integer.parseInt(new String(shiftArray(Integer.toBinaryString(n).toCharArray(), shift * -1)), 2);
    }

    public static int rotateRight(int n, int shift) {
        return Integer.parseInt(new String(shiftArray(Integer.toBinaryString(n).toCharArray(), shift)), 2);
    }

    private static char[] shiftArray(char[] incomingArray, int shift) {
        if (shift > 0) {
            for (int n = 0; n < shift; n++) {
                char buffer = incomingArray[0];
                incomingArray[0] = incomingArray[incomingArray.length - 1];

                for (int j = 1; j < incomingArray.length - 1; j++) {
                    incomingArray[incomingArray.length - j] = incomingArray[incomingArray.length - j - 1];
                }
                incomingArray[1] = buffer;
            }
        } else if (shift < 0) {
            for (int i = 0; i > shift; i--) {
                char buffer = incomingArray[incomingArray.length - 1];
                incomingArray[incomingArray.length - 1] = incomingArray[0];

                for (int j = 1; j < incomingArray.length - 1; j++) {
                    incomingArray[j - 1] = incomingArray[j];
                }
                incomingArray[incomingArray.length - 2] = buffer;
            }
        }

        return incomingArray;
    }
}
