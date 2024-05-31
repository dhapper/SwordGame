package battle.graphics;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import battle.GetMove;
import battle.PlayerBattleState;
import main.DrawMenu;

public class DrawButtons {
	
	DrawBattle drawBattle;
	
	public DrawButtons(DrawBattle drawBattle) {
		this.drawBattle = drawBattle;
		
		
		
		
		try {
			drawBattle.swingButton.setBounds(drawBattle.buttonX1, drawBattle.buttonY1, drawBattle.buttonWidth, drawBattle.buttonHeight);
			drawBattle.add(drawBattle.swingButton);
	    	Image swingButtonImage = ImageIO.read(new File("res/buttons/button - swing.png"));
			ImageIcon swingButtonIcon = new ImageIcon(swingButtonImage.getScaledInstance(drawBattle.buttonWidth, drawBattle.buttonHeight, Image.SCALE_SMOOTH));
			drawBattle.swingButton.setIcon(swingButtonIcon);
	    	
			drawBattle.jabButton.setBounds(drawBattle.buttonX1, drawBattle.buttonY2, drawBattle.buttonWidth, drawBattle.buttonHeight);
			drawBattle.add(drawBattle.jabButton);
	    	Image jabButtonImage = ImageIO.read(new File("res/buttons/button - jab.png"));
			ImageIcon jabButtonIcon = new ImageIcon(jabButtonImage.getScaledInstance(drawBattle.buttonWidth, drawBattle.buttonHeight, Image.SCALE_SMOOTH));
			drawBattle.jabButton.setIcon(jabButtonIcon);
	    	
			drawBattle.blockButton.setBounds(drawBattle.buttonX2, drawBattle.buttonY1, drawBattle.buttonWidth, drawBattle.buttonHeight);
			drawBattle.add(drawBattle.blockButton);
	    	Image blockButtonImage = ImageIO.read(new File("res/buttons/button - block.png"));
			ImageIcon blockButtonIcon = new ImageIcon(blockButtonImage.getScaledInstance(drawBattle.buttonWidth, drawBattle.buttonHeight, Image.SCALE_SMOOTH));
			drawBattle.blockButton.setIcon(blockButtonIcon);
	    	
			drawBattle.chargeButton.setBounds(drawBattle.buttonX2, drawBattle.buttonY2, drawBattle.buttonWidth, drawBattle.buttonHeight);
			drawBattle.add(drawBattle.chargeButton);
	    	Image chargeButtonImage = ImageIO.read(new File("res/buttons/button - charge.png"));
			ImageIcon chargeButtonIcon = new ImageIcon(chargeButtonImage.getScaledInstance(drawBattle.buttonWidth, drawBattle.buttonHeight, Image.SCALE_SMOOTH));
			drawBattle.chargeButton.setIcon(chargeButtonIcon);
			
			drawBattle.swapSwordsButton.setBounds(drawBattle.buttonX1-drawBattle.altButtonWidth, drawBattle.buttonY1+drawBattle.buttonHeight/2, drawBattle.altButtonWidth, drawBattle.buttonHeight);
			drawBattle.add(drawBattle.swapSwordsButton);
	    	Image swapSwordsButtonImage = ImageIO.read(new File("res/buttons/button - swap swords.png"));
			ImageIcon swapSwordsButtonIcon = new ImageIcon(swapSwordsButtonImage.getScaledInstance(drawBattle.altButtonWidth, drawBattle.buttonHeight, Image.SCALE_SMOOTH));
			drawBattle.swapSwordsButton.setIcon(swapSwordsButtonIcon);
	    	
			drawBattle.forfeitButton.setBounds(drawBattle.buttonX2 + drawBattle.buttonWidth, drawBattle.buttonY1 + drawBattle.buttonHeight/2, drawBattle.altButtonWidth, drawBattle.buttonHeight);
			drawBattle.add(drawBattle.forfeitButton);
	    	Image forfeitButtonImage = ImageIO.read(new File("res/buttons/button - forfeit.png"));
			ImageIcon forfeitButtonIcon = new ImageIcon(forfeitButtonImage.getScaledInstance(drawBattle.altButtonWidth, drawBattle.buttonHeight, Image.SCALE_SMOOTH));
			drawBattle.forfeitButton.setIcon(forfeitButtonIcon);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
		drawBattle.swingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	drawBattle.moveChosen = true;
            	drawBattle.setNextMove("SWING");
            }
        });
    	
		drawBattle.jabButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	drawBattle.moveChosen = true;
            	drawBattle.setNextMove("JAB");
            }
        });
    	
		drawBattle.blockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	drawBattle.moveChosen = true;
            	drawBattle.setNextMove("BLOCK");
            }
        });
    	
		drawBattle.chargeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	drawBattle.moveChosen = true;
            	drawBattle.setNextMove("CHARGE");
            }
        });
    	
		drawBattle.swapSwordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	drawBattle.moveChosen = true;
            	drawBattle.setNextMove("SWAP_SWORDS");
            }
        });
    	
		drawBattle.swapSwordsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	drawBattle.hoveringSwapSwordsButton = true;
            	drawBattle.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	drawBattle.hoveringSwapSwordsButton = false;
            	drawBattle.repaint();
            }
        });
    	
		drawBattle.forfeitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	
            	// count as loss
            	
            	DrawMenu menu = new DrawMenu(drawBattle.frame);
            }
        });
		
		PlayerBattleState A = drawBattle.battleManager.getPbsA();
		
		GetMove getMove = new GetMove(drawBattle.battleManager);
		
		
		/*if(getMove.disableMove("SWING", A)) {
			drawBattle.swingButton.setVisible(false);
		}else if(getMove.disableMove("JAB", A)) {
			drawBattle.jabButton.setVisible(false);
		}else if(getMove.disableMove("BLOCK", A)) {
			drawBattle.blockButton.setVisible(false);
		}else if(getMove.disableMove("CHARGE", A)) {
			drawBattle.chargeButton.setVisible(false);
		}else if(getMove.disableMove("SWAP_SWORDS", A)) {
			drawBattle.swapSwordsButton.setVisible(false);
		}*/
	}
	
	public void initDrawSwingAttack() {
	
	}
	
}
