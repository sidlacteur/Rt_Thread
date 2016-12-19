//
//import javax.swing.JFrame;
//
//import Affichage.NormalCam;
//import Affichage.ReconnaissanceForme;
//import Affichage.binarisation;
//import Affichage.gris;
//
//public class Main {
//	public static void main(String[] args) throws InterruptedException {
//
////		new binarisation();
////		new gris();
////		new NormalCam();
//
//		Thread thread = new Thread() {
//			public void run() {
//				System.out.println("Reconnaisance des formes");
//				new ReconnaissanceForme();
//			}
//		};
//		thread.start();
//
//		Thread thread1 = new Thread() {
//			public void run() {
//				System.out.println("Binarisation d'une video");
//				new binarisation();
//			}
//		};
//		thread1.start();
//
//		Thread thread2 = new Thread() {
//			public void run() {
//				System.out.println("video en noir et blanc");
//				new gris();
//			}
//		};
//		thread2.start();
//
//		Thread thread3 = new Thread() {
//			public void run() {
//				System.out.println("Video normale");
//				new NormalCam();
//			}
//		};
//		thread3.start();
//	
//	
//		
////	       JFrame f1 = new JFrame("f1 title");
////	        JFrame f2 = new JFrame("f2 title");
////
////	        Thread t1 = new Thread(new TestTwoFrames(f1));
////	        Thread t2 = new Thread(new TestTwoFrames(f2));
////
////	        t1.start();
////	        t2.start();
//		
//		
//	thread.join();
//	thread1.join();
//	thread2.join();
//	thread3.join();
//	
//	
//	}
//	
//	
//	
//	
//	
//	public class TestTwoFrames implements Runnable{
//
//	    JFrame theFrame;
//
//
//	    public TestTwoFrames(JFrame f) {
//	        this.theFrame = f;
//	    }
//
//
//	
//
//	    @Override
//	    public void run() {
//	        theFrame.setSize(200, 200);
//	        theFrame.setVisible(true);
//
//	        // Attention: This closes the app, and therefore both frames!
//	        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	    }
//
//	} // end class TestTwoFrames
//
//
//
//}