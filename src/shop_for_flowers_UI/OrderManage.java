package shop_for_flowers_UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;

public class OrderManage extends JFrame {
	
	String user_phone = "";
	Boolean manager = false;
	private JPanel contentPane;
	private JTextField searchTextField;
	private JTable table;
	private Main mainFrame;


	public OrderManage(String user_phone, Boolean manager) {
		this.user_phone = user_phone;
		this.manager = manager;

		setTitle("\uB3C4\uC11C \uAD00\uB9AC \uD504\uB85C\uADF8\uB7A8 - \uBA54\uC778");
		setBounds(100, 100, 881, 694);
		contentPane = new JPanel(); // 메인 프레임
		contentPane.setBackground(SystemColor.menu);
		setContentPane(contentPane);
		contentPane.setLayout(null); // 메인 프레임 레이아웃 null로 설정
		setResizable(false); // 창 크기 고정
		setLocationRelativeTo(null); // 중앙에 출력

		// 메뉴바
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setBackground(new Color(51, 51, 51));
		menuBar.setBounds(0, 0, 865, 47);
		contentPane.add(menuBar);

		// 홈아이콘 메뉴
		JMenu homeIconMenu = new JMenu(" \uD648 ");
		homeIconMenu.setBackground(Color.DARK_GRAY);
		homeIconMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		homeIconMenu.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		homeIconMenu.setForeground(new Color(0, 0, 0));
		ImageIcon icon5 = new ImageIcon("D:\\\uC0C8 \uD3F4\uB354\\pngegg.png");
		Image img5 = icon5.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon changeIcon5 = new ImageIcon(img5);
		homeIconMenu.setIcon(changeIcon5);
		menuBar.add(homeIconMenu);

		// 도서 찾기 메뉴
		JMenu findBookMenu = new JMenu("주문관리"); // 메뉴 - 도서찾기
		findBookMenu.setBackground(Color.DARK_GRAY);
		findBookMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//searchBookFrame = new SearchBook(user_phone, manager);
				//searchBookFrame.setVisible(true);
				setVisible(false);

			}
		});

		findBookMenu.setForeground(Color.BLACK);
		findBookMenu.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		menuBar.add(findBookMenu);

		// 회원 정보 메뉴
		JMenu userInfoMenu = new JMenu("고객관리");
		userInfoMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//userInfoFrame = new UserInfo(user_phone, manager);
				//userInfoFrame.setLocationRelativeTo(null); // 중앙에 출력
				//userInfoFrame.setVisible(true);
				setVisible(false);
			}
		});
		userInfoMenu.setForeground(Color.BLACK);
		userInfoMenu.setBackground(new Color(230, 230, 250));
		userInfoMenu.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		menuBar.add(userInfoMenu);
		
		// 상품 관리 메뉴
		JMenu userInfoMenu_1 = new JMenu("상품관리");
		userInfoMenu_1.setForeground(Color.BLACK);
		userInfoMenu_1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		userInfoMenu_1.setBackground(new Color(230, 230, 250));
		menuBar.add(userInfoMenu_1);
		
		// 꽃 관리 메뉴
		JMenu userInfoMenu_1_1 = new JMenu("꽃관리");
		userInfoMenu_1_1.setForeground(Color.BLACK);
		userInfoMenu_1_1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		userInfoMenu_1_1.setBackground(new Color(230, 230, 250));
		menuBar.add(userInfoMenu_1_1);
		
		// 매출 통계 메뉴
		JMenu userInfoMenu_1_1_1 = new JMenu("매출통계");
		userInfoMenu_1_1_1.setForeground(Color.BLACK);
		userInfoMenu_1_1_1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		userInfoMenu_1_1_1.setBackground(new Color(230, 230, 250));
		menuBar.add(userInfoMenu_1_1_1);
		
		JButton btnNewButton = new JButton("주문 검색");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(272, 151, 319, 73);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("주문 추가");
		btnNewButton_1.setBounds(272, 306, 319, 73);
		contentPane.add(btnNewButton_1);
		
		
	}
}
