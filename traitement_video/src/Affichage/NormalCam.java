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

public class NormalCam extends Thread {

	task_per task;

	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	static Mat imag = null;

	public NormalCam(int iter, int unit, String exec, String resp, int period, int prio,int dim) {
		task = new task_per(iter, unit, exec, resp, period, prio,dim);
		this.start();
	}

	public static BufferedImage Mat2bufferedImage(Mat image) {
		MatOfByte bytemat = new MatOfByte();
		Highgui.imencode(".jpg", image, bytemat);
		byte[] bytes = bytemat.toArray();
		InputStream in = new ByteArrayInputStream(bytes);
		BufferedImage img = null;
		try {
			img = ImageIO.read(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

	@Override
	public void run() {
		super.run();

		/**
		 * Edit real time parameters
		 */
//
//		Parameters p = new Parameters(1, 65, 1);
//		RtMgr.setSchedThreadParams(this, p);
		RtMgr.TimerStart();

		JFrame jframe = new JFrame("NORMAL");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel vidpanel = new JLabel();
		jframe.setContentPane(vidpanel);
		jframe.setSize(1600, 1080);
		jframe.setVisible(true);

		Mat frame = new Mat();
		Size sz = new Size(1600, 1080);
		VideoCapture camera = new VideoCapture(0);

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
				ImageIcon image = new ImageIcon(Mat2bufferedImage(frame));
				vidpanel.setIcon(image);
				vidpanel.repaint();
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
		System.out.println(task);
		System.exit(0);
	}

	public static void main(String[] args) {

		int period_millis = 100;
		int iteration = 1000;
		int unittime = 2;
		int prio = 0;
		int dim = 1080;
		String exec_path = "exec-cam-nrml-it-1000-per-100-prio-" + prio + "-dim-" + dim;
		String resp_path = "resp-cam-nrml-it-1000-per-100-prio-" + prio + "-dim-" + dim;

		new NormalCam(iteration, unittime, "nrml/" + exec_path, "nrml/" + resp_path, period_millis, prio,dim);
	}
}