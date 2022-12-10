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
import javax.swing.table.DefaultTableModel;

import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javax.swing.JTable;

import java.awt.Window.Type;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserInfo extends JFrame {
	DB_Connector dbConn = new DB_Connector();
	
	String 전화번호 = "";
	

	private JPanel contentPane;

	int userPoint = 0;
	boolean userSus = false;
	long diffDays = 0; // 연체일을 나타내는 변수
	boolean overdueUser = false;
	JLabel userExpend;
	Date susDate = null;
	Date returnDate;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date nowDate; 
	
	JLabel userName;
	JLabel phoneNumber;
	JLabel userLevel;
	JLabel totalOrder;
	
	private JTable table;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 */
	public UserInfo(String 전화번호) {
		this.전화번호 = 전화번호; // 객체가 생성될때 매개변수로 받은 book_ISBN을 저장
		// EditFlower editFlowerFrame = new EditFlower(이름, this);

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
		panel_1.setBounds(12, 31, 805, 232);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		// 책 이름 라벨
		userName = new JLabel("이름");
		userName.setFont(new Font("\uD568\uCD08\uB86C\uB3CB\uC6C0", userName.getFont().getStyle() | Font.BOLD,
				userName.getFont().getSize() + 10));
		userName.setBounds(12, 10, 535, 46);
		panel_1.add(userName);

		// 책 저자 라벨
		totalOrder = new JLabel("총 주문수 : ");
		totalOrder.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		totalOrder.setHorizontalAlignment(SwingConstants.LEFT);
		totalOrder.setBounds(12, 142, 330, 26);
		panel_1.add(totalOrder);

		// 책 평점 라벨
		 userExpend = new JLabel("사용금액 : ");
		userExpend.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		userExpend.setBounds(12, 178, 193, 26);
		panel_1.add(userExpend);
		
		phoneNumber = new JLabel("전화번호 : 00");
		phoneNumber.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		phoneNumber.setBounds(12, 70, 225, 26);
		panel_1.add(phoneNumber);
		
		userLevel = new JLabel("고객등급 : 00");
		userLevel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		userLevel.setBounds(12, 106, 225, 26);
		panel_1.add(userLevel);
				
						JScrollPane scrollPane = new JScrollPane();
						scrollPane.setBounds(12, 285, 805, 294);
						contentPane.add(scrollPane);
						scrollPane.setViewportBorder(new LineBorder(new Color(128, 128, 128), 1, true));
						
								// 책 줄거리 패널
								JPanel panel_2 = new JPanel();
								scrollPane.setColumnHeaderView(panel_2);
								panel_2.setBackground(new Color(255, 255, 255));
								panel_2.setLayout(null);
				try {
					
					getUserInfo();
				}catch (SQLException e1) {
					e1.printStackTrace(); // 에러 추적
					
				}
				
				// 테이블
				table = new JTable();
				// 테이블을 클릭하면 호출되는 메소드
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int row_index = table.getSelectedRow(); // 선택한 row 
						int col_index = table.getSelectedColumn(); // 선택한 col 
						//정렬 되어 보이지만 실제 데이터는 정렬되어 있지 않음
						int row = table.convertRowIndexToModel(row_index);		// 실제 모델에 저장되어 있는 인덱스 저장
						int col = table.convertColumnIndexToModel(col_index);	// 실제 모델에 저장되어 있는 인덱스 저장
						String itemNumber = table.getModel().getValueAt(row, 0).toString(); 
							
								
							ItemInfo itemInfo = new ItemInfo(itemNumber); // 책 정보창 객체 생성 (매개변수 : 클릭한 책의 ISBN)
							// 책정보창에서 창을 닫으면 호출되는 메소드
							itemInfo.addWindowListener(new WindowAdapter() {
								@Override
								public void windowClosing(WindowEvent e) {
									try { // DB 접근
										String query = "SELECT 상품고유번호, 상품명, 카테고리, 가격, 재고, 이미지 FROM 상품";
										dbConn.DB_Connect();
										Statement stmt = dbConn.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
									            ResultSet.CONCUR_UPDATABLE);
										ResultSet rs = stmt.executeQuery(query);
										
										set_table(rs);
									} catch (SQLException e1) {
										e1.printStackTrace();
										System.out.println("도서 검색창 테이블 구성중 SQL 실행 에러");
									}
									e.getWindow().dispose();
								}
							});
							
							itemInfo.setLocationRelativeTo(null); // 화면중앙에 출력
							itemInfo.setResizable(false); // 창 크기 고정
							itemInfo.setVisible(true); // 책 정보창 띄움
							
						}
				});

				table.setBackground(Color.WHITE);
				scrollPane.setViewportView(table);
				table.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 14));

				
				table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"New column", "\uC81C\uBAA9", "\uC800\uC790", "\uCD9C\uD310\uC0AC", "\uCE74\uD14C\uACE0\uB9AC", "\uB300\uCD9C\uC5EC\uBD80", "\uD3C9\uC810", "\uB300\uC5EC\uD69F\uC218", "\uB3C4\uC11C\uCD94\uAC00\uB0A0\uC9DC"
					}
				));
				table.getColumnModel().getColumn(1).setPreferredWidth(110);
				table.getColumnModel().getColumn(2).setPreferredWidth(101);
				table.getColumnModel().getColumn(3).setPreferredWidth(106);
				table.getColumnModel().getColumn(4).setPreferredWidth(115);
				table.getColumnModel().getColumn(5).setPreferredWidth(115);
				table.getColumnModel().getColumn(6).setPreferredWidth(115);
				table.getColumnModel().getColumn(7).setPreferredWidth(115);
				table.getColumnModel().getColumn(8).setPreferredWidth(115);
				
				try { // DB 접근
					//db에 있는 책 정보 검색
					String query = "SELECT a.상품고유번호, a.상품명, a.카테고리, a.가격, a.재고 FROM 상품 a, 주문 b, 주문고객 c where a.상품고유번호 = b.상품고유번호 and b.전화번호 = c.전화번호 and c.전화번호 = ?";
					dbConn.DB_Connect();
					PreparedStatement ps = dbConn.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					ps.setString(1, this.전화번호);
					ResultSet rs = ps.executeQuery();
					
					set_table(rs);
					
					//setTrs();
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("SQL 실행 에러");
				}
				
				try { // DB 접근
					getUserInfo();
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("SQL 실행 에러");
				}
				
	}
	
	public void getUserInfo() throws SQLException{

		try { // DB 접근
			
			String query = "SELECT 전화번호, 이름, 고객등급 FROM 주문고객 WHERE 전화번호 = ?";
			dbConn.DB_Connect();
			PreparedStatement ps = dbConn.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setString(1, this.전화번호);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getString("이름"));
				
				userName.setText(rs.getString("이름")); 
				phoneNumber.setText("전화번호 : " + rs.getString("전화번호"));
				userLevel.setText("고객등급 : " + rs.getString("고객등급"));

				break;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			System.out.println("SQL 실행 에러");
		}
	}
	
	public void set_table(ResultSet rs) throws SQLException {
		try {

			int row = 0;
			if (rs.last()) { // 커서를 마지막으로 이동
				row = rs.getRow(); // row에 현재 row의 인덱스를 저장(총 row의 개수를 저장)
				rs.beforeFirst(); // 다시 앞으로 이동시킴
			}

			String[][] data = new String[row][5]; // 테이블에 넣을 데이터를 저장할 배열
			int i = 0;
			// 일은 데이터로 테이블 구성
			while (rs.next()) {
				data[i][0] = rs.getString("상품고유번호");
				data[i][1] = rs.getString("상품명");
				data[i][2] = rs.getString("카테고리");
				data[i][3] = rs.getString("가격");
				data[i][4] = rs.getString("재고");
				
				i++;
			}
			String[] columns = { "상품고유번호", "상품명", "카테고리", "가격", "재고"}; // 테이블의 구성
			table.setModel(new DefaultTableModel(data, columns)); // 테이들 다시 세팅
			

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 실행 에러");
		}
	
	}
}