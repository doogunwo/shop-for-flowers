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

public class ItemManage extends JFrame {
	
	String user_phone = "";
	Boolean manager = false;
	private JPanel contentPane;
	private JTextField searchTextField;
	private JTable table;
	private Main mainFrame;
	
	OrderManage orderManageFrame;
	UserManage userManageFrame;
	ItemManage itemManageFrame;
	FlowerManage flowerManageFrame;
	ItemSearch itemSearchFrame;
	AddItem addItemFrame;


	public ItemManage(String user_phone, Boolean manager) {
		this.user_phone = user_phone;
		this.manager = manager;

		setTitle("꽃관리 프로그램 - 상품관리");
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
				homeIconMenu.setForeground(Color.WHITE);
				ImageIcon icon5 = new ImageIcon("D:\\\uC0C8 \uD3F4\uB354\\pngegg.png");
				Image img5 = icon5.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				ImageIcon changeIcon5 = new ImageIcon(img5);
				homeIconMenu.setIcon(changeIcon5);
				menuBar.add(homeIconMenu);

				// 주문 관리 메뉴
				JMenu orderManage = new JMenu("주문관리"); // 메뉴 - 도서찾기
				orderManage.setBackground(Color.DARK_GRAY);
				orderManage.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						orderManageFrame = new OrderManage(user_phone, manager);
						orderManageFrame.setVisible(true);
						setVisible(false);
					}
				});

				orderManage.setForeground(Color.WHITE);
				orderManage.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
				menuBar.add(orderManage);

				// 고객 관리 메뉴
				JMenu userManage = new JMenu("고객관리");
				userManage.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						userManageFrame = new UserManage(user_phone, manager);
						userManageFrame.setLocationRelativeTo(null); // 중앙에 출력
						userManageFrame.setVisible(true);
						setVisible(false);
					}
				});
				userManage.setForeground(Color.WHITE);
				userManage.setBackground(new Color(230, 230, 250));
				userManage.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
				menuBar.add(userManage);
				
				// 상품 관리 메뉴
				JMenu itemManage = new JMenu("상품관리");
				itemManage.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						itemManageFrame = new ItemManage(user_phone, manager);
						itemManageFrame.setLocationRelativeTo(null); // 중앙에 출력
						itemManageFrame.setVisible(true);
						setVisible(false);
					}
				});
				itemManage.setForeground(Color.WHITE);
				itemManage.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
				itemManage.setBackground(new Color(230, 230, 250));
				menuBar.add(itemManage);
				
				// 꽃 관리 메뉴
				JMenu flowerManage = new JMenu("꽃관리");
				flowerManage.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						flowerManageFrame = new FlowerManage(user_phone, manager);
						flowerManageFrame.setLocationRelativeTo(null); // 중앙에 출력
						flowerManageFrame.setVisible(true);
						setVisible(false);
					}
				});
				flowerManage.setForeground(Color.WHITE);
				flowerManage.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
				flowerManage.setBackground(new Color(230, 230, 250));
				menuBar.add(flowerManage);
				
				// 매출 통계 메뉴
				JMenu salesStatistics = new JMenu("매출통계");
				salesStatistics.setForeground(Color.WHITE);
				salesStatistics.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
				salesStatistics.setBackground(new Color(230, 230, 250));
				menuBar.add(salesStatistics);
		
		JButton btnNewButton = new JButton("상품 검색");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				itemSearchFrame = new ItemSearch(user_phone, manager);
				itemSearchFrame.setLocationRelativeTo(null); // 중앙에 출력
				itemSearchFrame.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(272, 151, 319, 73);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("상품 추가");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addItemFrame = new AddItem();
				addItemFrame.setLocationRelativeTo(null); // 중앙에 출력
				addItemFrame.setResizable(false); // 창 크기 고정
				addItemFrame.setVisible(true);
				
				
				addItemFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						e.getWindow().dispose();
					}
				});
			}
		});
		btnNewButton_1.setBounds(272, 306, 319, 73);
		contentPane.add(btnNewButton_1);
		
		
	}
}
