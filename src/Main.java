package shop_for_flowers_UI;

import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.event.MenuListener;


import javax.swing.event.MenuEvent;
import javax.imageio.ImageIO;
import java.awt.*;

public class Main extends JFrame {
	public String user_phone = ""; // 유저 PK 정보를 저장할 변수
	public Boolean manager = false; // 유저가 관리자인지 확인할 변수
	private JPanel contentPane;
	int t = 0;
	
	OrderManage orderManageFrame;
	UserManage userManageFrame;
	ItemManage itemManageFrame;
	FlowerManage flowerManageFrame;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setLocationRelativeTo(null); // 중앙에 출력
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {

		setTitle("꽃관리  프로그램 - 메인");
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
		
		
		

		
		
		
		// 인기도서 라벨
		JLabel popularBookLabel = new JLabel("인기상품");
		popularBookLabel.setForeground(new Color(51, 51, 51));
		popularBookLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		popularBookLabel.setBounds(22, 63, 123, 40);
		contentPane.add(popularBookLabel);

		// 인기도서 패널
		JPanel popularBookPanel = new JPanel();
		popularBookPanel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		popularBookPanel.setBackground(Color.WHITE);
		popularBookPanel.setBounds(22, 101, 820, 125);
		popularBookPanel.setLayout(null);

		JLabel[] popularBookLabel_array = new JLabel[6];
		// 패널에 출력할 책 이미지를 담을 Label 6개 생성
		JLabel popular_img1 = new JLabel("popular_img1");
		popular_img1.setBounds(12, 10, 105, 105);
		popularBookPanel.add(popular_img1);
		popularBookLabel_array[0] = popular_img1;

		JLabel popular_img2 = new JLabel("popular_img2");
		popular_img2.setBounds(150, 10, 105, 105);
		popularBookPanel.add(popular_img2);
		popularBookLabel_array[1] = popular_img2;

		JLabel popular_img3 = new JLabel("popular_img3");
		popular_img3.setBounds(288, 10, 105, 105);
		popularBookPanel.add(popular_img3);
		popularBookLabel_array[2] = popular_img3;

		JLabel popular_img4 = new JLabel("popular_img4");
		popular_img4.setBounds(426, 10, 105, 105);
		;
		popularBookPanel.add(popular_img4);
		popularBookLabel_array[3] = popular_img4;

		JLabel popular_img5 = new JLabel("popular_img5");
		popular_img5.setBounds(564, 10, 105, 105);
		popularBookPanel.add(popular_img5);
		popularBookLabel_array[4] = popular_img5;

		JLabel popular_img6 = new JLabel("popular_img6");
		popular_img6.setBounds(703, 10, 105, 105);
		popularBookPanel.add(popular_img6);
		popularBookLabel_array[5] = popular_img6;

		// 신간도서 라벨
		JLabel newlyBookLabel = new JLabel("\uC2E0\uAC04\uB3C4\uC11C");
		newlyBookLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		newlyBookLabel.setBounds(22, 260, 123, 40);
		contentPane.add(newlyBookLabel);

		// 신간도서 패널
		JPanel newlyBookPanel = new JPanel();
		newlyBookPanel.setBackground(Color.WHITE);
		newlyBookPanel.setForeground(new Color(255, 255, 255));
		newlyBookPanel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		newlyBookPanel.setBounds(22, 298, 820, 125);
		newlyBookPanel.setLayout(null);

		JLabel[] newlyBookLabel_array = new JLabel[6];
		// 패널에 출력할 책 이미지를 담을 Label 6개 생성
		JLabel newly_img1 = new JLabel("newly_img1");
		newly_img1.setBounds(12, 10, 105, 105);
		newlyBookPanel.add(newly_img1);
		newlyBookLabel_array[0] = newly_img1;

		JLabel newly_img2 = new JLabel("newly_img2");
		newly_img2.setBounds(150, 10, 105, 105);
		newlyBookPanel.add(newly_img2);
		newlyBookLabel_array[1] = newly_img2;

		JLabel newly_img3 = new JLabel("newly_img3");
		newly_img3.setBounds(288, 10, 105, 105);
		newlyBookPanel.add(newly_img3);
		newlyBookLabel_array[2] = newly_img3;

		JLabel newly_img4 = new JLabel("newly_img4");
		newly_img4.setBounds(426, 10, 105, 105);
		newlyBookPanel.add(newly_img4);
		newlyBookLabel_array[3] = newly_img4;

		JLabel newly_img5 = new JLabel("newly_img5");
		newly_img5.setBounds(564, 10, 105, 105);
		newlyBookPanel.add(newly_img5);
		newlyBookLabel_array[4] = newly_img5;

		JLabel newly_img6 = new JLabel("newly_img6");
		newly_img6.setBounds(703, 10, 105, 105);
		newlyBookPanel.add(newly_img6);
		newlyBookLabel_array[5] = newly_img6;

		// 즐겨찾기 라벨
		JLabel favoriteBookLabel = new JLabel("\uC990\uACA8\uCC3E\uAE30");
		favoriteBookLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		favoriteBookLabel.setBounds(22, 462, 123, 40);
		contentPane.add(favoriteBookLabel);

		// 즐겨찾기 패널
		JPanel favoriteBookPanel = new JPanel();
		favoriteBookPanel.setBackground(Color.WHITE);
		favoriteBookPanel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		favoriteBookPanel.setBounds(22, 502, 820, 125);
		favoriteBookPanel.setLayout(null);
		
		JLabel[] favorite_BookLabel_array = new JLabel[6];
		// 패널에 출력할 책 이미지를 담을 Label 6개 생성
		JLabel favorite_img1 = new JLabel("");
		favorite_img1.setBounds(12, 10, 105, 105);
		favoriteBookPanel.add(favorite_img1);
		favorite_BookLabel_array[0] = favorite_img1;

		JLabel favorite_img2 = new JLabel("");
		favorite_img2.setBounds(150, 10, 105, 105);
		favoriteBookPanel.add(favorite_img2);
		favorite_BookLabel_array[1] = favorite_img2;

		JLabel favorite_img3 = new JLabel("");
		favorite_img3.setBounds(288, 10, 105, 105);
		favoriteBookPanel.add(favorite_img3);
		favorite_BookLabel_array[2] = favorite_img3;

		JLabel favorite_img4 = new JLabel("");
		favorite_img4.setBounds(426, 10, 105, 105);
		favoriteBookPanel.add(favorite_img4);
		favorite_BookLabel_array[3] = favorite_img4;

		JLabel favorite_img5 = new JLabel("");
		favorite_img5.setBounds(564, 10, 105, 105);
		favoriteBookPanel.add(favorite_img5);
		favorite_BookLabel_array[4] = favorite_img5;

		JLabel favorite_img6 = new JLabel("");
		favorite_img6.setBounds(703, 10, 105, 105);
		favoriteBookPanel.add(favorite_img6);
		favorite_BookLabel_array[5] = favorite_img6;

	}
}