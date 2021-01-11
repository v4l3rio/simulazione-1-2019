package e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int NUM_OF_TRY = 5;
	private Map<JButton, Pair<Integer, Integer>> cords = new HashMap<>();
	private List<Pair<Integer, Integer>> placedBoat;
	private int masterCounter = 0;
	private int boatCounter = 0;

	public GUI(int size, int boat) {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 500);
		JPanel panel = new JPanel(new GridLayout(size, size));

		this.getContentPane().add(BorderLayout.CENTER, panel);

		ActionListener al = (e) -> {
			final JButton bt = (JButton) e.getSource();
			for (int i = 0; i < boat; i++) {
				if (cords.get(bt).equals(placedBoat.get(i))) {
					bt.setText("X");
					bt.setEnabled(false);
					boatCounter++;
					masterCounter++;
					if(boatCounter==boat) {
						System.out.println("You Win!");
						System.exit(1);
					}
					return;
				}
			}
			bt.setText("o");
			bt.setEnabled(false);
			masterCounter++;
			if(masterCounter==NUM_OF_TRY) {
				System.out.println("You Lost!");
				System.exit(1);
			}
		};

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				final JButton jb = new JButton();
				cords.put(jb, new Pair<Integer, Integer>(i, j));
				jb.addActionListener(al);
				panel.add(jb);
			}
		}
		placedBoat = placeBoat(boat, size);
		this.setVisible(true);
	}

	private List<Pair<Integer, Integer>> placeBoat(int boat, int size) {
		List<Pair<Integer, Integer>> boatCords = new ArrayList<>();
		Random rnd = new Random();
		int randomX = rnd.nextInt(size);
		int randomY = rnd.nextInt(size);
		int orientation = rnd.nextInt(2);

		if (orientation == 0) { // vado in alto o in basso
			if (randomY > size / 2) { // vado in alto
				for (int i = 0; i < boat; i++) {
					boatCords.add(new Pair<Integer, Integer>(randomX, randomY - i));
				}
			} else { // vado in basso
				for (int i = 0; i < boat; i++) {
					boatCords.add(new Pair<Integer, Integer>(randomX, randomY + i));
				}
			}
		} else { // vado a destra o sinistra
			if (randomX > size / 2) { // vado a sinistra
				for (int i = 0; i < boat; i++) {
					boatCords.add(new Pair<Integer, Integer>(randomX - i, randomY));
				}
			} else { // vado a destra
				for (int i = 0; i < boat; i++) {
					boatCords.add(new Pair<Integer, Integer>(randomX + i, randomY));
				}
			}
		}

		System.out.println("La nave è stata posizionata in: " + boatCords);

		return boatCords;

	}

}
