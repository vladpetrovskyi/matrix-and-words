import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println(finder(reader()));
    }

    private static String finder(String response) {
        String[] respArray = response.split(", ");
        int matrixSize = (int) Math.sqrt(respArray[0].length());
        char[][] charMatrix = new char[matrixSize][matrixSize];
        int counter = 0;
        for (int i = 0; i < charMatrix.length; i++) {
            for (int j = 0; j < charMatrix.length; j++) {
                charMatrix[i][j] = respArray[0].charAt(counter);
                counter++;
            }
        }

        StringBuilder answer = new StringBuilder();
        int charNum = 0;
        do {
            char current = respArray[1].charAt(charNum);
            if (charNum == respArray[1].length() - 1) {
                if (checkIfNear(charMatrix,
                        respArray[1].charAt(charNum - 1), current)) {
                    answer.append("[")
                            .append(getIndexes(charMatrix, current))
                            .append("]");
                }
            } else {
                if (checkIfNear(charMatrix, current,
                        respArray[1].charAt(charNum + 1))) {
                    answer.append("[")
                            .append(getIndexes(charMatrix, current))
                            .append("]->");
                }
            }
            charNum++;
        } while (charNum < respArray[1].length());
        return answer.toString();
    }

    private static String reader() {
        System.out.println("Please input your request like [word, word]: ");
        return new Scanner(System.in).nextLine();
    }

    private static boolean checkIfNear(char[][] array, char current, char next) {
        String[] curString = getIndexes(array, current).split(",");
        String[] nextString = getIndexes(array, next).split(",");

        int currentIndex1 = Integer.parseInt(curString[0]);
        int currentIndex2 = Integer.parseInt(curString[1]);
        int nextIndex1 = Integer.parseInt(nextString[0]);
        int nextIndex2 = Integer.parseInt(nextString[1]);

        return (nextIndex1 == currentIndex1 - 1 && nextIndex2 == currentIndex2)
                || (nextIndex1 == currentIndex1 + 1 && nextIndex2 == currentIndex2)
                || (nextIndex1 == currentIndex1
                && (nextIndex2 == currentIndex2 - 1
                || nextIndex2 == currentIndex2 + 1));
    }

    private static String getIndexes(char[][] array, char c) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] == c) {
                    return i + "," + j;
                }
            }
        }
        return "";
    }
}
