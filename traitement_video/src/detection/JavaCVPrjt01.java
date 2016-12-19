package detection;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;

public class JavaCVPrjt01 {
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public static void main(String[] args) {
		Mat frame = new Mat();
		Size sz = new Size(640, 480);
		VideoCapture camera = new VideoCapture(0);
		while (true) {
			if (camera.read(frame)) {
				Imgproc.resize(frame, frame, sz);
			}
		}
	}
}