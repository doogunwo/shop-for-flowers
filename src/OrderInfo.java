package shop_for_flowers_UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.DropMode;
import javax.swing.ImageIcon;

import java.awt.Panel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import java.awt.Window.Type;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderInfo extends JFrame {
	DB_Connector dbConn = new DB_Connector();
	
	String 주문번호 = "";
	

	private JPanel contentPane;

	int userPoint = 0;
	boolean userSus = false;
	long diffDays = 0; // 연체일을 나타내는 변수
	boolean overdueUser = false;
	JLabel userName;
	Date susDate = null;
	Date returnDate;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date nowDate; 
	
	JLabel orderNumber;
	JLabel orderAddress;
	JLabel orderCount;
	JLabel orderDate;
	JLabel itemName;
	JLabel userLevel;
	JLabel orderPrice;
	JLabel lblVip;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 */
	public OrderInfo(String 주문번호) {
		this.주문번호 = 주문번호; // 객체가 생성될때 매개변수로 받은 book_ISBN을 저장

		setTitle("꽃관리 프로그램 - 꽃정보");
		setBounds(100, 100, 848, 681);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 책 정보 패널
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(12, 31, 805, 376);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		// 책 이름 라벨
		orderNumber = new JLabel("주문번호");
		orderNumber.setFont(new Font("\uD568\uCD08\uB86C\uB3CB\uC6C0", orderNumber.getFont().getStyle() | Font.BOLD,
				orderNumber.getFont().getSize() + 10));
		orderNumber.setBounds(12, 10, 535, 46);
		panel_1.add(orderNumber);

		// 책 저자 라벨
		itemName = new JLabel("상품명 : 00");
		itemName.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		itemName.setHorizontalAlignment(SwingConstants.LEFT);
		itemName.setBounds(12, 178, 330, 26);
		panel_1.add(itemName);

		// 책 출판사 라벨
		orderDate = new JLabel("주문날짜 : 00");
		orderDate.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		orderDate.setBounds(12, 142, 225, 26);
		panel_1.add(orderDate);

		// 책 평점 라벨
		 userName = new JLabel("고객명 : 00");
		userName.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		userName.setBounds(12, 214, 193, 26);
		panel_1.add(userName);
		
		orderAddress = new JLabel("주소 : 00");
		orderAddress.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		orderAddress.setBounds(12, 70, 225, 26);
		panel_1.add(orderAddress);
		
		orderCount = new JLabel("주문개수 : 00");
		orderCount.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		orderCount.setBounds(12, 106, 225, 26);
		panel_1.add(orderCount);
		
		userLevel = new JLabel("고객등급 : 00");
		userLevel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		userLevel.setBounds(12, 253, 193, 26);
		panel_1.add(userLevel);
		
		orderPrice = new JLabel("가격 : 00");
		orderPrice.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		orderPrice.setBounds(12, 289, 193, 26);
		panel_1.add(orderPrice);
		
		lblVip = new JLabel("VIP 등급 : 3% 할인");
		lblVip.setForeground(Color.RED);
		lblVip.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		lblVip.setBounds(12, 316, 193, 26);
		panel_1.add(lblVip);
				try {
					
					getOrderInfo();
				}catch (SQLException e1) {
					e1.printStackTrace(); // 에러 추적
					
				}
	}
	
	public void getOrderInfo() throws SQLException{

		try { // DB 접근
			
			String query = "SELECT a.주문번호, b.이름, b.고객등급, 상품명, 주문개수, 주소, 주문날짜\r\n"
					+ "FROM 주문 a, 주문고객 b, 상품 c\r\n" + "WHERE a.전화번호 = b.전화번호 and a.상품고유번호 = c.상품고유번호 and a.주문번호 = ?";
			dbConn.DB_Connect();
			PreparedStatement ps = dbConn.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setString(1, this.주문번호);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {

				orderNumber.setText("주문번호 : " + rs.getString("주문번호")); 
				orderAddress.setText("주소 : " + rs.getString("주소"));
				orderCount.setText("주문개수 : " + rs.getString("주문개수"));
				orderDate.setText("주문날짜 : " + rs.getString("주문날짜")); 
				itemName.setText("상품명 : " + rs.getString("상품명"));
				userName.setText("고객명 : " + rs.getString("이름"));
				userLevel.setText("고객등급 : " + rs.getString("고객등급"));
				
				
				break;
			}
			System.out.println(rs.getString("고객등급"));
			if(rs.getString("고객등급").equals("노말")) {					
				lblVip.setText("노말등급 할인 적용 X");
			}else if(rs.getString("고객등급").equals("VIP")) {
				lblVip.setText("VIP 등급 3% 할인");
			}else {
				lblVip.setText("VIP 등급 5% 할인");
			}
			// P_1 저장 프로시저 호출
			CallableStatement cstmt = dbConn.con.prepareCall("{call P_1(?, ?)}");
			cstmt.setString(1, 주문번호);
			cstmt.registerOutParameter(2, Types.INTEGER);
			cstmt.executeQuery();
			orderPrice.setText("가격 : " + cstmt.getInt(2));
			

			
		} catch (SQLException e2) {
			e2.printStackTrace();
			System.out.println("SQL 실행 에러");
		}
	}
}