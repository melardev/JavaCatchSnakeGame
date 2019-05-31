package com.melardev.game.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard extends KeyAdapter {

	private volatile boolean requestingExit;
	
	public KeyBoard(){
		setRequestingExit(false);
	}
	
	@Override
	public void keyPressed(KeyEvent ke) {
		int keyCode = ke.getKeyCode();
		if( keyCode == KeyEvent.VK_ESCAPE || keyCode == KeyEvent.VK_Q ||  keyCode == KeyEvent.VK_END ||
	             keyCode == KeyEvent.VK_C && ke.isControlDown())
			setRequestingExit(true);
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		int code = ke.getKeyCode();
		if(code == KeyEvent.VK_ESCAPE)
			setRequestingExit(true);
	}

	public boolean isRequestingExit() {
		return requestingExit;
	}
	public void setRequestingExit(boolean requestingExit) {
		this.requestingExit = requestingExit;
	}
	

}
