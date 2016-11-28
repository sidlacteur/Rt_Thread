package Test;

import Printer.FilePrinter;
import RtMgrpackage.RtMgr;

/**
 * JavaWorld: Real-time Java Application Development For Multi-core Systems.
 * This code is public domain.
 */
public class TestWithLibWithIncrGC {

	static final int LOG2_N = 14;
	static final int N = 1 << LOG2_N;
	static final int K = 1000; // Number of frames.

	public TestWithLibWithIncrGC() {

		Complex[][] frames = new Complex[K][N];
		Complex[][] results = new Complex[K][N];
		for (int i = 0; i < K; i++) {
			for (int j = 0; j < N; j++) {
				frames[i][j] = new Complex(Math.random(), Math.random());
			}
		}
		FilePrinter filewriter = new FilePrinter();
		long max = 0, sum = 0;
		final int n = 1000;
		int unittime = 2;
		String timeStirng = "";
		for (int i = 0; i < n; i++) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} // To limit garbage flow.
			long time = RtMgr.getExactClockTime(unittime);
			fft(frames[i % K], results[i % K]);
			time = RtMgr.getSubOfTime(RtMgr.getExactClockTime(unittime), time);

			if (time > max) {
				max = time;
			}
			sum = RtMgr.getAddOfTime(sum, time);
			// sum += time;
			timeStirng += ((double) time / 1000) + "\n";

			filewriter.fileprinter(timeStirng + "", "with_lib_incrGC_1000_milli");

		}
		System.out.println("Maximum Execution Time: " + ((double) max / 1000) + " ms");
		System.out.println("Average Execution Time: " + ((double) sum / n / 1000) + " ms");
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
