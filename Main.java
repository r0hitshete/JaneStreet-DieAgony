import java.util.ArrayList;

public class Main {
    private static double[] die;
    private static ArrayList<Integer> path;
    private static int solution;
    

    public static void main(String[] args) {
        die = new double[6];
        path = new ArrayList<>();
        solution = 0;
        int arr[][] = { { 57, 33, 132, 268, 492, 732 }, { 81, 123, 240, 443, 353, 508 },
                { 186, 42, 195, 704, 452, 228 }, { -7, 2, 357, 452, 317, 395 }, { 5, 23, -4, 592, 445, 620 },
                { 0, 77, 32, 403, 337, 452 } };
        int copy[][] = copy(arr);

        findDie(arr, 5, 0, 0, 'n', 0, 0, 0, 0, 0, 0, 0, copy, new ArrayList<>());
        System.out.print("Dice values: ");
        for (double x : die) {
            System.out.print(x + " ");
        }
        System.out.println("");
        System.out.print("Path: ");
        for (Integer x: path) {
            System.out.print(x + " ");
        }
        System.out.println("");
        System.out.print("Solution: ");
        System.out.println(solution);

    }

    public static void findDie(int[][] A, int i, int j, int m, char prev, double score, double curr, double up,
            double down, double left, double right, double bottom, int[][] B, ArrayList<Integer> p) {
        int n = A.length;
        B = copy(B);
        p = new ArrayList<>(p);
        if (i >= 0 && i < n && j >= 0 && j < n) {
            if (prev == 'r') {
                double temp = curr;
                curr = left;
                left = bottom;
                bottom = right;
                right = temp;
            }
            if (prev == 'u') {
                double temp = curr;
                curr = down;
                down = bottom;
                bottom = up;
                up = temp;
            }
            if (prev == 'l') {
                double temp = curr;
                curr = right;
                right = bottom;
                bottom = left;
                left = temp;
            }
            if (prev == 'd') {
                double temp = curr;
                curr = up;
                up = bottom;
                bottom = down;
                down = temp;
            }
            if (i == 0 && j == n - 1) {
                die[0] = curr;
                die[1] = up;
                die[2] = down;
                die[3] = left;
                die[4] = right;
                die[5] = bottom;
                p.add(A[i][j]);
                B[i][j] = 0;
                for (int[] q: B) {
                    for (int w: q) {
                        solution += w;
                    }
                }
                path = p;
            } else {
                if (curr != 0.0 && score + m * curr == A[i][j]) {
                    p.add(A[i][j]);
                    B[i][j] = 0;
                    findDie(A, i, j + 1, m + 1, 'r', score + m * curr, curr, up, down, left, right, bottom, B, p);
                    findDie(A, i - 1, j, m + 1, 'u', score + m * curr, curr, up, down, left, right, bottom, B, p);
                    findDie(A, i, j - 1, m + 1, 'l', score + m * curr, curr, up, down, left, right, bottom, B, p);
                    findDie(A, i + 1, j, m + 1, 'd', score + m * curr, curr, up, down, left, right, bottom, B, p);
                }

                if (curr == 0.0) {
                    if (prev != 'n') {
                        curr = ((double) (A[i][j] - score)) / (m);
                    }
                    p.add(A[i][j]);
                    B[i][j] = 0;
                    findDie(A, i, j + 1, m + 1, 'r', score + m * curr, curr, up, down, left, right, bottom, B, p);
                    findDie(A, i - 1, j, m + 1, 'u', score + m * curr, curr, up, down, left, right, bottom, B, p);
                    findDie(A, i, j - 1, m + 1, 'l', score + m * curr, curr, up, down, left, right, bottom, B, p);
                    findDie(A, i + 1, j, m + 1, 'd', score + m * curr, curr, up, down, left, right, bottom, B, p);
                } 
            }
        }
    }
    public static int[][] copy(int[][] A) {

        int [][] B = new int[A.length][];
        for(int i = 0; i < A.length; i++) {
            B[i] = A[i].clone();
        }
        return B;
    } 

}