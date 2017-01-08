package main;

import java.util.ArrayList;
import java.util.List;

public class Helvetinkone {

	public static void main(String[] args) {

		List<ResultObject> results = getCoordinates();

		if (results.size() > 0) {
			for (int i = 0; i < results.size(); i++) {
				ResultObject resultObject = (ResultObject) results.get(i);
				System.out.println(resultObject.N + "  " + resultObject.E + ",   p: " + resultObject.p + " q: " + resultObject.q + "    a-values: " + resultObject.aValues + "\n");
			}
		} else {
			System.out.println("EI TULOKSIA :-( \n");
		}

	}

	private static List<ResultObject> getCoordinates() {
		double a1, a2, a3, a4, a5, a6;
		double northDegrees, eastDegrees;
		double northMinsAndSecs, eastMinsAndSecs;

		List<ResultObject> results = new ArrayList<ResultObject>();

		int multiplierMax = 1000000;
		double pAndQmax = 100000;
		// int multiplierMax = 1000;
		// double pAndQmax = 50;

		double p = 38287;
		double q = 43427;
		// for (double p = getNextPrime(1); p < pAndQmax; p = getNextPrime(p + 1d)) {
		// for (double q = getNextPrime(p + 1d); q < pAndQmax; q = getNextPrime(q + 1d)) {

		System.out.println("P: " + p + ", Q: " + q);

		for (int multiplierA1 = 0; multiplierA1 <= multiplierMax; multiplierA1++) {
			a1 = (multiplierA1 * 1000d) + 60d;
			northDegrees = a1 % 1000d;

			// System.out.println("northDegrees: " + northDegrees);

			if (northDegrees != 60d) {
				break;
			}

			a2 = (a1 * p) % q;
			a3 = (a2 * p) % q;

			northMinsAndSecs = ((a2 % 1000d) + (a3 % 1000d) / 1000d);

			// if (northMinsAndSecs < 14d || northMinsAndSecs > 19d) {
			if (northMinsAndSecs < 16d || northMinsAndSecs > 17d) {
				break;
			}

			// System.out.println("Suitable northMinsAndSecs founds: " + northMinsAndSecs);

			a4 = (a3 * p) % q;
			eastDegrees = a4 % 1000d;

			// System.out.println("eastDegrees: " + eastDegrees);

			// if ((eastDegrees != 24d) && (eastDegrees != 25d)) {
			if (eastDegrees != 24d) {
				break;
			}

			// System.out.println("Suitable eastDegrees founds");

			a5 = (a4 * p) % q;
			a6 = (a5 * p) % q;

			eastMinsAndSecs = ((a5 % 1000d) + ((a6 - 10d) % 1000d) / 1000d);

			// if (eastMinsAndSecs < 0d || eastMinsAndSecs > 60d) {
			if (eastMinsAndSecs < 56d || eastMinsAndSecs > 58d) {
				break;
			}

			// System.out.println("Suitable eastMinsAndSecs founds: " + eastMinsAndSecs);

			// System.out.println("Calculated coordinates: " + northDegrees + " " + northMinsAndSecs + ", " + eastDegrees + " " + eastMinsAndSecs + ", p: " + p + " q: " + q);

			ResultObject resultObject = new ResultObject();
			resultObject.N = "N " + (int) northDegrees + "° " + northMinsAndSecs;
			resultObject.E = "E " + (int) eastDegrees + "° " + eastMinsAndSecs;
			resultObject.p = p;
			resultObject.q = q;
			resultObject.aValues = "A1: " + a1 + ", A2: " + a2 + ", A3: " + a3 + ", A4: " + a4 + ", A5: " + a5 + ", A6: " + a6;

			results.add(resultObject);
		}
		// }
		// }

		return results;
	}

	// Loop max value has to be greater than pAndQmax value to make p and q loops to stop
	// in getCoordinates function
	private static double getNextPrime(double start) {
		for (double i = start; i <= 101000d; i++) {
			if (isPrime(i)) {
				return i;
			}
		}
		return -1;
	}

	private static boolean isPrime(double num) {
		if (num < 3d) {
			return false;
		}

		boolean prime = true;
		for (double i = 2; i < num; i++) {
			prime = prime && (num % i != 0d);

			if (!prime) {
				return false;
			}
		}

		return prime;
	}

	static class ResultObject {
		String N;
		String E;
		double p;
		double q;
		String aValues;
	}
}
