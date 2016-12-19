package Affichage;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;

import Printer.FilePrinter;
import RtMgrpackage.Parameters;
import RtMgrpackage.RtMgr;
import period.PeriodJNI;

public class binarisation extends Thread {

	task_per task;
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	static Mat imag = null;

	public binarisation(int iter, int unit, String exec, String resp, int period, int prio, int dim) {

		task = new task_per(iter, unit, exec, resp, period, prio, dim);
		this.start();
	}

	public static BufferedImage Mat2bufferedImage(Mat image) throws InterruptedException {
		MatOfByte bytemat = new MatOfByte();
		Highgui.imencode(".jpg", image, bytemat);
		byte[] bytes = bytemat.toArray();
		InputStream in = new ByteArrayInputStream(bytes);
		BufferedImage img = null;
		try {
			img = ImageIO.read(in);
			Thread.sleep(30);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

	public void run() {
		super.run();

		/**
		 * Edit real time parameters
		 */

//		Parameters p = new Parameters(1, task.prio, 1);
//		RtMgr.setSchedThreadParams(this, p);
		RtMgr.TimerStart();

		JFrame jframe = new JFrame("Binarisation");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel vidpanel = new JLabel();
		jframe.setContentPane(vidpanel);
		jframe.setSize(640, 480);
		jframe.setVisible(true);

		Mat frame = new Mat();
		Mat outerBox = new Mat();
		Mat diff_frame = null;
		Mat tempon_frame = null;
		VideoCapture camera = new VideoCapture(0);
		Size sz = new Size(1600, 1080);
		int i = 0;

		String StringExec = "", StringResp = "";
		FilePrinter filewriter = new FilePrinter();
		FilePrinter filewriter1 = new FilePrinter();
		long end_exec, end_resp, begin_resp, begin_exec = RtMgr.getExactClockTime(task.unittime);
		double executionTime_exec, executionTime_resp;

		while (task.iteration > 0) {

			begin_exec = RtMgr.getExactClockTime(task.unittime);
			begin_resp = RtMgr.getClockTime(task.unittime);

			long next = begin_exec / 1000 + task.periodForEachThread;

			System.out.println(task.iteration);

			if (camera.read(frame)) {
				Imgproc.resize(frame, frame, sz);
				imag = frame.clone();
				outerBox = new Mat(frame.size(), CvType.CV_8UC1);
				Imgproc.cvtColor(frame, outerBox, Imgproc.COLOR_BGR2GRAY);
				Imgproc.GaussianBlur(outerBox, outerBox, new Size(3, 3), 0);

				if (i == 0) {
					jframe.setSize(frame.width(), frame.height());
					diff_frame = new Mat(outerBox.size(), CvType.CV_8UC1);
					tempon_frame = new Mat(outerBox.size(), CvType.CV_8UC1);
					diff_frame = outerBox.clone();
				}

				if (i == 1) {
					Core.subtract(outerBox, tempon_frame, diff_frame);
					Imgproc.adaptiveThreshold(diff_frame, diff_frame, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C,
							Imgproc.THRESH_BINARY_INV, 5, 2);

				}

				i = 1;

				ImageIcon image = null;
				try {
					image = new ImageIcon(Mat2bufferedImage(diff_frame));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				vidpanel.setIcon(image);
				vidpanel.repaint();
				tempon_frame = outerBox.clone();

			}
			end_exec = RtMgr.getExactClockTime(task.unittime);
			end_resp = RtMgr.getClockTime(task.unittime);

			executionTime_exec = (double) end_exec / 1000 - (double) begin_exec / 1000;
			StringExec = StringExec + executionTime_exec + "\n";

			executionTime_resp = (double) end_resp / 1000 - (double) begin_resp / 1000;
			StringResp = StringResp + executionTime_resp + "\n";

			PeriodJNI.endInstance(next, 1);
			task.iteration--;
		}
		filewriter.fileprinter(StringExec + "", task.exec);
		filewriter1.fileprinter(StringResp + "", task.resp);
		System.exit(0);
	}

	public static void main(String[] args) {

		int period_millis = 100;
		int iteration = 1000;
		int unittime = 2;
		int prio = 0;
		int dim = 1080;
		String exec_path = "exec-cam-bin-it-1000-per-100-prio-" + prio + "-dim-" + dim;
		String resp_path = "resp-cam-bin-it-1000-per-100-prio-" + prio + "-dim-" + dim;

		new binarisation(iteration, unittime, "bin/" + exec_path, "bin/" + resp_path, period_millis, prio, dim);
	}
}