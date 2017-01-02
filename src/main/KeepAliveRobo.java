package main;

import java.awt.Robot;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.*;
import main.MainFrame;

class KeepAliveRobo{
	private Robot robot;
	private Dimension screenSize;
	private List<Integer> x;
	private List<Integer> y;
	
	public KeepAliveRobo() throws Exception{
		robot=new Robot();
		x=new ArrayList<>();
		y=new ArrayList<>();
		screenSize= Toolkit.getDefaultToolkit().getScreenSize();
	}
	private void moveMouseInCircle(){
			Iterator<Integer> xItr=x.listIterator();
			Iterator<Integer> yItr=y.listIterator();
			for(int j=0;j<x.size();j++){
				robot.mouseMove(x.get(j),y.get(j));
				robot.delay(500);
				if(j==x.size()-1)
					j=0;
			}
	}
	private void go(int side){
		
		int centerx,centery;
		centerx=(int)screenSize.getWidth()/2;
		centery=(int)screenSize.getHeight()/2;
		int sx=centerx+side;
		int sy=centery-side;
		
		//left edge
		for(int i=0;i<side;i++){
			x.add(sx);
			y.add(sy);
			sy+=3;
		}
		//top edge
		for(int i=0;i<side;i++){
			x.add(sx);
			y.add(sy);
			sx+=3;
		}
		//right edge
		for(int i=0;i<side;i++){
			sy-=3;
			x.add(sx);
			y.add(sy);
		}
		//bottom edge
		for(int i=0;i<side;i++){
			sx-=3;
			x.add(sx);
			y.add(sy);
		}
		
		/**
		Does a paint job. Opens MS Paint and starts drawing random shit.
		*/
		private void drawRandomShit(){
			robot.keyPress(KeyEvent.VK_WINDOWS);
			robot.keyPress(KeyEvent.VK_R);
			robot.keyRelease(KeyEvent.VK_R);
			robot.keyRelease(KeyEvent.VK_WINDOWS);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(new StringSelection("mspaint"));
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			//press and release enter
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			//center
			keepAliveRobo.go(0);
		}
		
		
		
		
		
	}
	public static void main(String args[]) throws Exception{
		KeepAliveRobo keepAliveRobo=new KeepAliveRobo();
		keepAliveRobo.go(Integer.parseInt(args[0]));
		keepAliveRobo.moveMouseInCircle();
	}
}
