package git.hyeonsoft.uscEdit.uscBackgroundEditor.tools;

import java.awt.FileDialog;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;

public class VideoToImage {
	public int numberOfThreads = 8;
	public JFrame f;
	JLabel labelProgress;
	public VideoToImage() throws FileNotFoundException, IOException, JCodecException{
		f = new JFrame();
		FileDialog dialog = new FileDialog(f, "Load Video", FileDialog.LOAD);
		dialog.setVisible(true);
		String videoPath = dialog.getDirectory()+'\\'+dialog.getFile();
		File videoFile = new File(videoPath);
		FrameGrab grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(videoFile));
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Select Folder to Save Images");
	    fc.setSize(800, 600);
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
        fc.showSaveDialog(f);
        String savePath = fc.getSelectedFile()+"/";
        int numberOfFrames=grab.getVideoTrack().getMeta().getTotalFrames();
        EncodingThread[] encodingThread = new EncodingThread[numberOfThreads];
        for(int i=0;i<numberOfThreads;i++) {
        	encodingThread[i]=new EncodingThread(videoPath, savePath, numberOfFrames*i/numberOfThreads, numberOfFrames*(i+1)/numberOfThreads);
        	encodingThread[i].start();
        }
        f = new JFrame();
        f.setSize(300, 100);
        f.setTitle("converting progress");
        labelProgress = new JLabel("");
        Thread progress = new Thread(new Thread() {
        	@Override
        	public void run() {
        		while(this.isInterrupted()==false) {
        			Double percent = 0.0;
        			for(EncodingThread x : encodingThread) {
            			percent+=x.getProgress();
            		}
        			percent=100-percent/numberOfThreads*100;
        			try {f.remove(labelProgress);}catch(Exception E) {}
        			labelProgress = new JLabel(percent.toString()+"%");
        			f.add(labelProgress);
        			f.revalidate();
        			try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
		        		for(EncodingThread x : encodingThread) {
		        			x.interrupt();
		        		}
					}
        		}
        		for(EncodingThread x : encodingThread) {
        			x.interrupt();
        		}
        	}
        });
        f.addWindowListener(new WindowListener(){
			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				progress.interrupt();
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}
			
		});
        progress.start();
        f.setVisible(true);
	}
	public class EncodingThread extends Thread{
		private String videoPath;
		private int startFrame;
		private int endFrame;
		private int nowFrame;
		private String savePath;
		public EncodingThread(String videoPath, String savePath, int startFrame, int endFrame){
			this.videoPath=videoPath;
			this.endFrame=endFrame;
			this.startFrame=startFrame;
			this.nowFrame=startFrame;
			this.savePath = savePath;
		}
		public double getProgress() {
			return (endFrame-nowFrame)*1.0/(endFrame-startFrame);
		}
		public void run() {
			try {
				File videoFile = new File(videoPath);
				Picture picture;
				for(;nowFrame<endFrame&&!isInterrupted();nowFrame++) {
					picture = FrameGrab.getFrameFromFile(videoFile, nowFrame);
					FileOutputStream imageout = new FileOutputStream(savePath+"frame"+String.format("%05d", nowFrame)+".jpg");
					ImageIO.write(AWTUtil.toBufferedImage(picture), "jpg", imageout);
					imageout.close();
				}
			}catch(Exception e) {
			}
			
		}
	}
}
