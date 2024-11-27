package Games;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
    	//set up frame
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Game Prototype");
        //set up panel
        GamePanel gamepanel = new GamePanel();
        window.add(gamepanel);
        //fit the panel to the size of the window
        window.pack();
        //more panel stuff
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamepanel.startGame();

    }

}