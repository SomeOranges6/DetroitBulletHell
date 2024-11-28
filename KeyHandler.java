package Games;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	public boolean upPressed, downPressed, leftPressed, rightPressed, upleftPressed, uprightPressed, downleftPressed, downrightPressed;

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = true;
			
		}
		if(code == KeyEvent.VK_S) {
			downPressed = true;
			
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
			
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
			
		}
		if(code == KeyEvent.VK_Q) {
			upleftPressed = true;
			
		}
		if(code == KeyEvent.VK_E) {
			uprightPressed = true;
			
		}
		if(code == KeyEvent.VK_C) {
			downrightPressed = true;
			
		}
		if(code == KeyEvent.VK_Z) {
			downleftPressed = true;
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
			
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
			
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
			
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		if(code == KeyEvent.VK_Q) {
			upleftPressed = false;
			
		}
		if(code == KeyEvent.VK_E) {
			uprightPressed = false;
			
		}
		if(code == KeyEvent.VK_C) {
			downrightPressed = false;
			
		}
		if(code == KeyEvent.VK_Z) {
			downleftPressed = false;
			
		}
		
	}

}
