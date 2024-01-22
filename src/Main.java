import java.util.Arrays;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		int[] number = getArray(1_000_000);
		int[] number2 = Arrays.copyOf(number, number.length);
		int[] number3 = Arrays.copyOf(number, number.length);

//		System.out.println(getBefore(number));
		long nano = System.nanoTime();
		sort(number);
		long x = System.nanoTime() - nano;
//		System.out.println(getAfter(number, x));

		nano = System.nanoTime();
		sort2(number2);
		long x2 = System.nanoTime() - nano;
//		System.out.println(getAfter(number2, x2));

		nano = System.nanoTime();
		quickSort(number3);
		long x3 = System.nanoTime() - nano;
//		System.out.println(getAfter(number3, x3));

		System.out.printf("x = %d, x2 = %d, x3 = %d", x, x2, x3);
		System.out.println(getWinner(x, x2, x3));
	}


	private static int[] getArray(int length) {
		int[] number = new int[length];
		Random random = new Random();

		for (int i = 0; i < length; i++) {
			number[i] = random.nextInt(0, length);
		}
		return number;
	}


	private static int[] getArray() {
		return new int[] { 8, 11, 7, 10, 6, 0, 12, 5, 1, 9, 4, 2, 3 };
	}


	private static String getWinner(long x, long x2, long x3) {
		if (x < x2 && x < x3)
			return "sort is winner";
		else if (x2 < x && x2 < x3)
			return "sort2 is winner";
		else if (x3 < x && x3 < x2)
			return "quickSort is winner";
		else
			return "no winner";
	}


	private static String getAfter(int[] number, long x) {
		return "After %,d nanoseconds\n%s".formatted(x, Arrays.toString(number));
	}


	private static String getBefore(int[] number) {
		return "Before:\n%s".formatted(Arrays.toString(number));
	}


	public static void sort(int[] array) {
		for (int i = array.length - 1; i >= 0 ; i--) {
			for (int j = 0; j < i; j++) {
				int next = j + 1;
				if (array[j] > array[next]) {
					array[next] = array[next] + array[j];
					array[j] = array[next] - array[j];
					array[next] = array[next] - array[j];
				}
//				System.out.printf("j = %2d | array = %s\n", j, Arrays.toString(array));
			}
		}
	}


	public static void sort3(int[] number) {
		for (int i = 0; i < number.length; i++) {
			for (int j = i + 1; j < number.length; j++) {
				if (number[i] > number[j]) {
					int temp = number[i];
					number[i] = number[j];
					number[j] = temp;
				}
			}
		}
	}


	public static void sort2(int[] number) {
		for (int i = 0; i < number.length - 1; i++) {
			for (int j = number.length - 2 ; j >= i; j--) {
				if (number[j] > number[j + 1]) {
					number[j + 1] = number[j + 1] + number[j];
					number[j] = number[j + 1] - number[j];
					number[j + 1] = number[j + 1] - number[j];
				}
			}
		}
	}

	public static void quickSort(int[] array, int low, int high) {
		if (array.length == 0 || low >= high)
			return;

		int middle = low + (high - low) / 2;
		int middleElement = array[middle];

		int i = low, j = high;
		while (i <= j) {
			while (array[i] < middleElement) {
				i++;
			}

			while (array[j] > middleElement) {
				j--;
			}

			if (i <= j) {
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				i++;
				j--;
			}
		}

		if (low < j)
			quickSort(array, low, j);

		if (high > i)
			quickSort(array, i, high);
	}

	public static void quickSort(int[] array) {
		quickSort(array, 0, array.length - 1);
	}
}
