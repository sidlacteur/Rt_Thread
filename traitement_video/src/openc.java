//
//import java.awt.BorderLayout;
//import java.awt.Canvas;
//import java.awt.EventQueue;
//import java.util.List;
//
//import javax.swing.JFrame;
//import javax.swing.SwingWorker;
//
//import org.opencv.core.*;
//import org.opencv.highgui.Highgui;
//import org.opencv.highgui.VideoCapture;
//
//public class openc {
//
//private JFrame frame;
//
///**
// * Launch the application.
// */
//public static void main(String[] args) {
//    EventQueue.invokeLater(new Runnable() {
//        public void run() {
//            try {
//                openc window = new openc();
//                window.frame.setVisible(true);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    });
//}
//
///**
// * Create the application.
// */
//public openc() {
//    initialize();
//}
//
///**
// * Initialize the contents of the frame.
// */
//private void initialize() {
//    frame = new JFrame();
//    frame.setBounds(100, 100, 450, 300);
//    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//    Canvas canvas = new Canvas();
//    frame.getContentPane().add(canvas, BorderLayout.CENTER);
//
//            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//            VideoCapture camera = new VideoCapture(0);
//            if(!camera.isOpened()){
//                System.out.println("Error");
//            }
//            else {
//                Mat iframe = new Mat();
//                SwingWorker<Void, Mat> worker = new SwingWorker<Void, Mat>() {
//                    @Override
//                    protected Void doInBackground() throws Exception {            
//                        while(!isCancelled()) {
//                            if (camera.read(iframe)) {
//                                publish(iframe);
//                            }
//                            Thread.sleep(500); // prudential time to avoid block the event queue
//                        }            
//                        return null;
//                    }
//
//                    @Override
//                    protected void process(List<Mat> chunks) {
//                        Mat lastFrame = chuncks.get(chunks.size() - 1);
//                        Highgui.imwrite(canvas+"", lastFrame);
//                    }        
//                };
//
//                worker.execute();
//            }
//            camera.release();
//
//}
//
//}
//
