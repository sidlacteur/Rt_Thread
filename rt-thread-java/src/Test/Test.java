package Test;

import Printer.FilePrinter;
import period.PeriodJNI;

class TimeSpec {

	public enum Unit {
		second, milli, micro, nano
	};

	public long sec;
	public long nano;

	public void add(TimeSpec s) {

	}

	public void subs(TimeSpec s) {

	}

	public void from(double t, Unit unit) {

	}

	public double to(Unit unit) {
		return 0;
	}

}

/**
 * JavaWorld: Real-time Java Application Development For Multi-core Systems.
 * This code is public domain.
 */
public class Test {

	static final int LOG2_N = 14;
	static final int N = 1 << LOG2_N;
	static final int K = 1000; // Number of frames.

	public Test(String nom1, String nom2) {

		Complex[][] frames = new Complex[K][N];
		Complex[][] results = new Complex[K][N];
		for (int i = 0; i < K; i++) {
			for (int j = 0; j < N; j++) {
				frames[i][j] = new Complex(Math.random(), Math.random());
			}
		}
		FilePrinter filewriter = new FilePrinter();
		FilePrinter filewriter1 = new FilePrinter();
		// long max = 0, sum = 0;
		final int n = 1000;
		String timeStirng = "";
		String timeStirng1 = "";
		int unittime = 2;
		for (int i = 0; i < n; i++) {

			// TimeSpec time_spec1 = new TimeSpec();
			// TimeSpec time_spec2 = new TimeSpec();

			long time = PeriodJNI.getExactClockTime(unittime);
			long time1 = PeriodJNI.getClockTime(unittime);

			fft(frames[i % K], results[i % K]);

			// time = PeriodJNI.timesub(PeriodJNI.getExactClockTime(unittime),
			// time, unittime);

			time1 = PeriodJNI.getClockTime(unittime) - time1;
			time = PeriodJNI.getExactClockTime(unittime) - time;

			// time1 = PeriodJNI.timesub(PeriodJNI.getClockTime(unittime),
			// time1, unittime);

			timeStirng += ((double) time / 1000) + "\n";
			timeStirng1 += ((double) time1 / 1000) + "\n";

		}

		filewriter.fileprinter(timeStirng1 + "", nom1);
		filewriter1.fileprinter(timeStirng + "", nom2);

	}

	static void fft(Complex[] a, Complex[] A) {
		for (int i = 0; i < N; ++i) {
			A[reverseBits(i)] = a[i];
		}
		for (int s = 1; s <= LOG2_N; ++s) {
			int m = 1 << s;
			Complex w = new Complex(1, 0);
			Complex wm = new Complex(Math.cos(2 * Math.PI / m), Math.sin(2 * Math.PI / m));
			for (int j = 0; j < m / 2; ++j) {
				for (int k = j; k < N; k += m) {
					Complex t = w.times(A[k + m / 2]);
					Complex u = A[k];
					A[k] = u.plus(t);
					A[k + m / 2] = u.minus(t);
				}
				w = w.times(wm);
			}
		}
	}

	static int reverseBits(int x) {
		int n = 0;
		for (int i = 0; i < LOG2_N; i++) {
			n <<= 1;
			n |= (x & 1);
			x >>= 1;
		}
		return n;
	}

	static class Complex {

		double _real, _imag;

		Complex(double real, double imag) {
			_real = real;
			_imag = imag;
		}

		Complex plus(Complex that) {
			return new Complex(this._real + that._real, this._imag + that._real);
		}

		Complex minus(Complex that) {
			return new Complex(this._real - that._real, this._imag - that._real);
		}

		Complex times(Complex that) {
			return new Complex(this._real * that._real - this._imag * that._imag,
					this._real * that._imag + this._imag * that._real);
		}
	}
}
