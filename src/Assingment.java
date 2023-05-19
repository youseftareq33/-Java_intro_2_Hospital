import java.util.*;

public class Assingment{
	public static void main(String[] args) {
		System.out.println("--------------------------- Hospital ---------------------------\n");
		
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter the number of days that a patient has entered the hospital: ");
		int days = in.nextInt();
		System.out.println("------------------------------------------------------------------------");
		double[][] temp_arrays = new double [days][];
		double[] summaryData = new double [3];
		int[] avgCount = new int [2];
		int t;
		double temp;
		for (int i = 0; i < days; i++) {
			System.out.print("\n"+"please enter number of times that nurse read the temperature of the patient in celsius: " + (i+1) + " : ");
			t = in.nextInt();
			temp_arrays[i] = new double[t];
			for (int j = 0; j < temp_arrays[i].length; j++) {
				System.out.print("Please enter the " + (j+1) + " temp: ");
				temp = in.nextDouble();
				while (temp < 30 || temp > 45) {
					System.out.println("You Entered the wrong number, please enter number between 30 and 45");
					System.out.print("Please enter the " + (j+1) + " temp: ");
					temp = in.nextDouble();
				}
				temp_arrays[i][j] = temp;
			}
		}
		summary(temp_arrays,summaryData);
		System.out.println("\n=====================================================");
		System.out.println("the maximum tempreture is: "+ summaryData[0]);
		System.out.println("the minimum tempreture is: "+ summaryData[1]);
		System.out.println("the average is: "+ summaryData[2]+"\n");
		countbelowAboveAverage(temp_arrays,avgCount,summaryData[2]);
		System.out.println("The number of temps above avg = "+ avgCount[1]);
		System.out.println("The number of temps below or eq. avg  = "+ avgCount[0]);
		sortArray(temp_arrays);
		System.out.println("\n"+"The array after sorting: ");
		printArray(temp_arrays);

		System.out.println("");
		if (leaveHospital(temp_arrays,days)) 
			System.out.println("Your average is Stable, You can leave");		
		else 
			System.out.println("Your average is not Stable, You cannot leave");
		in.close();
	}
	
	
	
	public static void summary(double[][] temp_arr, double[] summaryData) {
		double max = temp_arr[0][0],min = temp_arr[0][0],sum=0,avg=0;
		int counter = 0;
		for (int i = 0; i < temp_arr.length; i++) {
			for (int j = 0; j < temp_arr[i].length; j++) {
				if (max <= temp_arr[i][j])
					max = temp_arr[i][j];
				if (min >= temp_arr[i][j])
					min = temp_arr[i][j];
				sum += temp_arr[i][j];
				counter++;
			}
		}
		avg = sum/counter;
		summaryData[0] = max;
		summaryData[1] = min;
		summaryData[2] = avg;
	}

	
	
	public static void countbelowAboveAverage(double[][] temp_arr, int[] avgCount, double avg) {
	    for (int i = 0; i < temp_arr.length; i++) {
	        for (int j = 0; j < temp_arr[i].length; j++) {
	            if (temp_arr[i][j] <= avg) {
	                avgCount[0]++;
	            } else {
	                avgCount[1]++;
	            }
	        }
	    }
	}
	
	
	
	public static void sortArray(double[][] temp_arr) {
		double temp;
		for (int i = 0; i < temp_arr.length; i++) {
			for (int j = 0; j < temp_arr[i].length; j++) {
				for (int u = j; u > 0; u--) {
					if (temp_arr[i][j] < temp_arr[i][j-1]) {
						temp = temp_arr[i][j];
						temp_arr[i][j] = temp_arr[i][j-1];
						temp_arr[i][j-1] = temp;
					}
				}
			}
		}
	}
	
	
	
	public static void printArray(double[][] temp_arr) {
		System.out.println("number of times:                    temps: ");
		for (int i = 0; i < temp_arr.length; i++) {
			System.out.print("        "+temp_arr[i].length + "                           ");
			for (int j = 0 ; j < temp_arr[i].length;j++) {
				System.out.print(temp_arr[i][j] + "\t");	
			}
			System.out.println();
		}
	}
	
	public static boolean leaveHospital(double[][] temp_arr,int days) {
	    int pre;
	    if (days == 1) {
	        pre = temp_arr.length - 1;
	        if (pre >= 0 && temp_arr[pre].length > 0) {
	            double max = temp_arr[pre][0];
	            double max2 = 0;
	            for (int i = 0; i < temp_arr[pre].length; i++) {
	                if (temp_arr[pre][i] > max) 
	                    max = temp_arr[pre][i];
	            }
	            if (pre + 1 < temp_arr.length && temp_arr[pre + 1].length > 0) {
	                for (int i = 0; i < temp_arr[pre + 1].length; i++) {
	                    if (temp_arr[pre + 1][i] > max2) 
	                        max2 = temp_arr[pre + 1][i];
	                }
	                if ((max2 + max) / 2 >= 35.5 && (max2 + max) / 2 <= 36.5)
	                    return true;
	            }
	        }
	    } else {
	        pre = temp_arr.length - 2;
	        if (pre >= 0 && temp_arr[pre].length > 0 && pre + 1 < temp_arr.length && temp_arr[pre + 1].length > 0) {
	            double max = temp_arr[pre][0];
	            double max2 = temp_arr[pre + 1][0];
	            for (int i = 0; i < temp_arr[pre].length; i++) {
	                if (temp_arr[pre][i] > max) 
	                    max = temp_arr[pre][i];
	            }
	            for (int i = 0; i < temp_arr[pre + 1].length; i++) {
	                if (temp_arr[pre + 1][i] > max2) 
	                    max2 = temp_arr[pre + 1][i];
	            }
	            if ((max2 + max) / 2 >= 35.5 && (max2 + max) / 2 <= 36.5)
	                return true;
	        }
	    }
	    return false;
	}

}