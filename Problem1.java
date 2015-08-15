import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @author vivekaditya.purella
 *
 */
public class Problem1 {

	public static void main(String[] args) throws Exception {
		// 	File Read
		BufferedReader bw = null;
		try {
			bw = new BufferedReader(new FileReader("p081_matrix.txt"));
		} catch (Exception e) {
			System.out.println("File Not Found");
		}
		// 	Build Matrix
		String line = bw.readLine();
		int matrixSize = line.split(",").length;
		int[][] matrix = new int[matrixSize][matrixSize];
		int i = 0;

		while (line != null) {
			String s[] = line.split(",");
			for (int j = 0; j < s.length; j++) {
				matrix[i][j] = Integer.parseInt(s[j]);
			}
			i++;
			line = bw.readLine();
		}
		bw.close();
		// 	Finding Solution using Dynamic Programming , Find solutions of small parts 
		for (int k = matrixSize - 2; k >= 0; k--) {
			matrix[matrixSize - 1][k] += matrix[matrixSize - 1][k + 1];
			matrix[k][matrixSize - 1] += matrix[k + 1][matrixSize - 1];
		}
		// 	Finding the optimal path cost 
		for (int a = matrixSize - 2; a >= 0; a--) {
			for (int b = matrixSize - 2; b >= 0; b--) {
				matrix[a][b] += Math.min(matrix[a + 1][b], matrix[a][b + 1]);
			}
		}

		System.out.println("Minimal path sum is " + matrix[0][0]);
	}
}