package shop_for_flowers_UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class UserManage extends JFrame {
	
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
	
	DB_Connector dbConn = new DB_Connector();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	JComboBox searchComboBox;


	public UserManage(String user_phone, Boolean manager) {
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
		
		// 검색 패널
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 245));
		panel.setBounds(22, 57, 819, 38);
		contentPane.add(panel);
		panel.setLayout(null);

		// 검색 목록 콤보박스
		searchComboBox = new JComboBox();
		searchComboBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 15));
		searchComboBox.setModel(new DefaultComboBoxModel(new String[] {"전체", "전화번호", "이름", "고객등급"}));
		searchComboBox.setBackground(Color.WHITE);
		searchComboBox.setBounds(0, 0, 129, 38);
		panel.add(searchComboBox);

		// 검색 입력 텍스트필드
		searchTextField = new JTextField();
		searchTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//검색창 입력 후 엔터 입력시 작동되는 리스너
				search_event();
			}
		});
		searchTextField.setBounds(129, 0, 569, 38);
		panel.add(searchTextField);
		searchTextField.setColumns(10);

		// 검색 버튼
		JButton searchButton = new JButton("검색");
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 검색 버튼 눌렀을때 작동되는 리스너
			
				search_event();
			}
		});
		searchButton.setBackground(new Color(220, 220, 220));
		searchButton.setFont(new Font("한컴산뜻돋움", Font.BOLD, 14));
		searchButton.setBounds(690, 0, 129, 38);
		panel.add(searchButton);

		// 유저 리스트 패널
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(128, 128, 128), 2));
		panel_1.setBounds(180, 112, 661, 520);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		// 유저 리스트 스크롤 패널
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 661, 520);
		panel_1.add(scrollPane);

		// 유저 리스트 테이블
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//UserInfo userinfo = new UserInfo(user_phone, manager);
				//userinfo.setVisible(true);
			}
		});

		// 테이블
		table = new JTable();
		 //테이블을 클릭하면 호출되는 메소드
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow(); // 선택한 row
				int col = table.getSelectedColumn(); // 선택한 col
				String phoneNumber = table.getModel().getValueAt(row, 0).toString(); // 클릭한 열의 회원 전화번호를 저장
				// 유저 정보창 객체 생성 (매개변수 : 클릭한 유저의 phone)
				UserInfo userInfoFrame = new UserInfo(phoneNumber);

				// 유저정보창에서 창을 닫으면 호출되는 메소드
				userInfoFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						try { // DB 접근
							String query = "SELECT 전화번호, 이름, 고객등급 FROM 주문고객";
							dbConn.DB_Connect();
							Statement stmt = dbConn.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						            ResultSet.CONCUR_UPDATABLE);
							ResultSet rs = stmt.executeQuery(query);
							
							set_table(rs);
						} catch (SQLException e1) {
							e1.printStackTrace();
							System.out.println("주문고객 검색창 테이블 구성중 SQL 실행 에러");
						}
						e.getWindow().dispose();
					}
				});

				userInfoFrame.setLocationRelativeTo(null); // 화면중앙에 출력
				userInfoFrame.setVisible(true); // 유저 정보창 띄움
			}
		});

		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		table.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 14));

		String[] columns = { "이름", "전화번호", "등급"}; // 테이블의 구성
		String[][] data;
		DefaultTableModel model = new DefaultTableModel(null, columns);
		table.setModel(model); // 테이블 세팅
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(22, 112, 135, 520);
		contentPane.add(panel_2);
		
		JLabel sortLabel = new JLabel("고객등급");
		sortLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 15));
		sortLabel.setBounds(12, 10, 70, 20);
		panel_2.add(sortLabel);
		
		JRadioButton headerRadioButton = new JRadioButton("노말");
		headerRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					try {
						
					String query = "SELECT 전화번호, 이름, 고객등급 from 주문고객 where 고객등급 = '노말'";
					dbConn.DB_Connect();
					Statement stmt = dbConn.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				            ResultSet.CONCUR_UPDATABLE);
					ResultSet rs = stmt.executeQuery(query);
					
					set_table(rs);
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
				}else {

				}
				//trs.setSortKeys(sortKeys);
			}
		});
		headerRadioButton.setSelected(true);
		headerRadioButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		headerRadioButton.setBackground(Color.WHITE);
		headerRadioButton.setBounds(12, 50, 113, 23);
		panel_2.add(headerRadioButton);
		
		JRadioButton rdbtnVvip = new JRadioButton("VVIP");
		rdbtnVvip.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					try {
						
					String query = "SELECT 전화번호, 이름, 고객등급 from 주문고객 where 고객등급 = 'VVIP'";
					dbConn.DB_Connect();
					Statement stmt = dbConn.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				            ResultSet.CONCUR_UPDATABLE);
					ResultSet rs = stmt.executeQuery(query);
					
					set_table(rs);
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
				}else {

				}
				//trs.setSortKeys(sortKeys);
			}
		});
		rdbtnVvip.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		rdbtnVvip.setBackground(Color.WHITE);
		rdbtnVvip.setBounds(12, 115, 113, 23);
		panel_2.add(rdbtnVvip);
		
		JRadioButton popularityRadioButton_1 = new JRadioButton("VIP");
		popularityRadioButton_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					try {
						
					String query = "SELECT 전화번호, 이름, 고객등급 from 주문고객 where 고객등급 = 'VIP'";
					dbConn.DB_Connect();
					Statement stmt = dbConn.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				            ResultSet.CONCUR_UPDATABLE);
					ResultSet rs = stmt.executeQuery(query);
					
					set_table(rs);
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
				}else {

				}
				//trs.setSortKeys(sortKeys);
			}
		});
		popularityRadioButton_1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		popularityRadioButton_1.setBackground(Color.WHITE);
		popularityRadioButton_1.setBounds(12, 83, 113, 23);
		panel_2.add(popularityRadioButton_1);
		
		buttonGroup_1.add(headerRadioButton);
		buttonGroup_1.add(popularityRadioButton_1);
		buttonGroup_1.add(rdbtnVvip);

		try { // DB 접근
			String query = "SELECT 전화번호, 이름, 고객등급 FROM 주문고객";
			dbConn.DB_Connect();
			Statement stmt = dbConn.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		            ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(query);

			set_table(rs); // 관리자를 제외한 회원들의 정보로 테이블을 구성
		} catch (SQLException e) {
			System.out.println("회원검색창 초기 테이블 구성중 SQL 실행 에러");
		}
		// 간격 조절
		table.getColumnModel().getColumn(0).setPreferredWidth(110);
		table.getColumnModel().getColumn(1).setPreferredWidth(101);
		table.getColumnModel().getColumn(2).setPreferredWidth(106);
	}
	
	public void search_event() {
		
		try { // DB 접근
			String search_how = "전체"; // 검색 조건이 들어갈 search_how (제목, 저자..)
			ResultSet rs;
			
			switch (searchComboBox.getSelectedItem().toString()) {
			case "전화번호": // 
				search_how = "전화번호";
				break;
			case "이름": // 
				search_how = "이름";
				break;
			case "고객등급": //
				search_how = "고객등급";
				break;
			case "전체": // 검색조건이 "전체"일 때
				search_how = "(전화번호 || 이름 || 고객등급)";
				break;
			}
			
			dbConn.DB_Connect();
			String query = "SELECT 전화번호, 이름, 고객등급\r\n"
					+ "FROM 주문고객\r\n" + "WHERE " + search_how + " LIKE '%" + searchTextField.getText()
					+ "%'";
			PreparedStatement ps = dbConn.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = ps.executeQuery();
			
			if (rs != null) // 검색결과가 있으면
			{
				//나중에 체크박스 원상복구시키는 코드도 여기 작성  
				System.out.println("검색완료");
				set_table(rs); // 테이블 재구성
				//setTrs();	//테이블을 재구성을 했으므로 필터링 초기화도 같이해줌
			}
			else // 없으면
				System.out.println("검색결과가 없습니다.");
		} catch (SQLException e2) {
			e2.printStackTrace();
			System.out.println("SQL 실행 에러");
		}
	}	

	// ResultSet을 받아 테이블 재구성하는 함수
	public void set_table(ResultSet rs) throws SQLException {
		try {

			int row = 0;

			if (rs.last()) { // 커서를 마지막으로 이동
				row = rs.getRow(); // row에 현재 row의 인덱스를 저장(총 row의 개수를 저장)
				rs.beforeFirst(); // 다시 앞으로 이동시킴
			}

			String[][] data = new String[row][3]; // 테이블에 넣을 데이터를 저장할 배열
			int i = 0;
			// 일은 데이터로 테이블 구성
			while (rs.next()) {
				data[i][0] = rs.getString("전화번호"); // 회원 이름
				data[i][1] = rs.getString("이름"); // 회원 메일
				data[i][2] = rs.getString("고객등급"); // 회원 전화번호
//				// 회원 정지여부
//				if (rs.getString("USER_SUSPENSION") == null) {
//					data[i][4] = "N";
//				} else {
//					data[i][4] = rs.getString("USER_SUSPENSION").substring(0, 16);
//				}
//
//				// 회원 탈퇴여부
//				if (rs.getString("USER_OUT_DATE") == null) {
//					data[i][5] = "N";
//				} else {
//					data[i][5] = rs.getString("USER_OUT_DATE").substring(0, 10);
//				}
				i++;
			}
			String[] columns = { "전화번호", "이름", "고객등급"}; // 테이블의 구성
			table.setModel(new DefaultTableModel(data, columns)); // 테이들 다시 세팅
		} catch (SQLException e) {
			System.out.println("회원검색창에서 테이블 구성중 SQL 실행 에러");
		}
	}
}
