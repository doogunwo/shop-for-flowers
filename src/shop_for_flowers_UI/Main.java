package shop_for_flowers_UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 653, 524);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 10, 613, 465);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("꽃 가게");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 38));
		lblNewLabel.setBounds(0, 10, 613, 71);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("구매하기");
		btnNewButton.setBounds(20, 91, 121, 49);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("내 구매목록");
		btnNewButton_1.setBounds(20, 150, 121, 52);
		panel.add(btnNewButton_1);
	}
}
