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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;

public class OrderSearch extends JFrame {
	
	String user_phone = "";
	Boolean manager = false;
	private JPanel contentPane;
	private JTextField searchTextField;
	private JTable table;
	private Main mainFrame;
	DB_Connector dbConn = new DB_Connector();
	
	OrderManage orderManageFrame;
	UserManage userManageFrame;
	ItemManage itemManageFrame;
	FlowerManage flowerManageFrame;
	JComboBox searchComboBox;


	public OrderSearch(String user_phone, Boolean manager) {
		this.user_phone = user_phone;
		this.manager = manager;

		setTitle("꽃관리 프로그램 - 주문검색");
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
		searchComboBox.setModel(new DefaultComboBoxModel(
				new String[] { "\uC804\uCCB4", "\uC774\uB984", "\uC804\uD654\uBC88\uD638", "\uC774\uBA54\uC77C" }));
		searchComboBox.setBackground(Color.WHITE);
		searchComboBox.setBounds(0, 0, 129, 38);
		panel.add(searchComboBox);

		// 검색 입력 텍스트필드
		searchTextField = new JTextField();
		searchTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 검색 버튼 눌렀을때 작동되는 리스너
			
				search_event();
			}
		});
		searchTextField.setBounds(129, 0, 569, 38);
		panel.add(searchTextField);
		searchTextField.setColumns(10);

		// 검색 버튼
		JButton searchButton = new JButton("검색");
	searchButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//검색창 입력 후 엔터 입력시 작동되는 리스너
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
		panel_1.setBounds(22, 112, 819, 520);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		// 유저 리스트 스크롤 패널
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 819, 520);
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
		// 테이블을 클릭하면 호출되는 메소드
//		table.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				int row = table.getSelectedRow(); // 선택한 row
//				int col = table.getSelectedColumn(); // 선택한 col
//				String clicked_user_phone = table.getModel().getValueAt(row, 2).toString(); // 클릭한 열의 회원 전화번호를 저장
//				// 유저 정보창 객체 생성 (매개변수 : 클릭한 유저의 phone)
//				//EditableUserInfo manager_user_info = new EditableUserInfo(clicked_user_phone);
//
//				// 유저정보창에서 창을 닫으면 호출되는 메소드
//				//manager_user_info.addWindowListener(new WindowAdapter() {
//					//@Override
//					//public void windowClosing(WindowEvent e) {
//						//e.getWindow().dispose();
////						try { // DB 접근
////							ResultSet rs = dbConn.executeQuery(
////									"SELECT USER_NAME, USER_MAIL, USER_PHONE, USER_BIRTH, USER_SUSPENSION, USER_OUT_DATE FROM USER WHERE USER_MANAGER =false;");
////							set_table(rs); // 관리자를 제외한 회원들의 정보로 테이블을 구성
////						} catch (SQLException e1) {
////							System.out.println("회원검색창 초기 테이블 구성중 SQL 실행 에러");
////						}
//					}
//				});
//
//				manager_user_info.setLocationRelativeTo(null); // 화면중앙에 출력
//				manager_user_info.setVisible(true); // 유저 정보창 띄움
//			}
//		});

		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		table.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 14));

		String[] columns = { "이름", "전화번호", "상품명", "주문개수", "주소", "주문날짜"}; // 테이블의 구성
		String[][] data;
		DefaultTableModel model = new DefaultTableModel(null, columns);
		table.setModel(model); // 테이블 세팅

		try { // DB 접근
			String query = "select b.이름, b.전화번호, 상품명, 주문개수, 주소, 주문날짜 from 주문 a, 주문고객 b, 상품 c where a.전화번호=b.전화번호 and a.상품고유번호 = c.상품고유번호";
			
			dbConn.DB_Connect();
			Statement stmt = dbConn.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		            ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(query);
			set_table(rs); // 관리자를 제외한 회원들의 정보로 테이블을 구성
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 간격 조절
		table.getColumnModel().getColumn(0).setPreferredWidth(110);
		table.getColumnModel().getColumn(1).setPreferredWidth(101);
		table.getColumnModel().getColumn(2).setPreferredWidth(106);
		table.getColumnModel().getColumn(3).setPreferredWidth(115);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(115);
	}

	// ResultSet을 받아 테이블 재구성하는 함수
	public void set_table(ResultSet rs) throws SQLException {
		try {

			int row = 0;
			if (rs.last()) { // 커서를 마지막으로 이동
				row = rs.getRow(); // row에 현재 row의 인덱스를 저장(총 row의 개수를 저장)
				rs.beforeFirst(); // 다시 앞으로 이동시킴
			}

			String[][] data = new String[row][6]; // 테이블에 넣을 데이터를 저장할 배열
			int i = 0;
			// 일은 데이터로 테이블 구성
			while (rs.next()) {
				data[i][0] = rs.getString("이름"); // 회원 이름
				data[i][1] = rs.getString("전화번호"); // 회원 메일
				data[i][2] = rs.getString("상품명"); // 회원 전화번호
				data[i][3] = rs.getString("주문개수"); // 회원 생일
				data[i][4] = rs.getString("주소"); // 회원 생일
				data[i][5] = rs.getString("주문날짜"); // 회원 생일
				i++;
			}
			String[] columns = { "이름", "전화번호", "상품명", "주문개수", "주소", "주문날짜" }; // 테이블의 구성
			table.setModel(new DefaultTableModel(data, columns)); // 테이들 다시 세팅
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
public void search_event() {
		
		try { // DB 접근
			String search_how = "전체"; // 검색 조건이 들어갈 search_how (제목, 저자..)
			ResultSet rs;
			System.out.println(searchComboBox.getSelectedItem().toString());
			switch (searchComboBox.getSelectedItem().toString()) {
			case "이름": // 검색조건이 "제목"일 때
				search_how = "b.이름";
				break;
			case "전화번호": // 검색조건이 "저자"일 때
				search_how = "b.전화번호";
				break;
			case "상품명": // 검색조건이 "출판사"일 때
				search_how = "상품명";
				break;
			case "주문날짜":
				search_how = "주문날짜";
			case "전체": // 검색조건이 "전체"일 때
				search_how = "(b.이름 || b.전화번호 || 상품명 || 주문날짜)";
				break;
			}
			dbConn.DB_Connect();
			String query = "SELECT b.이름, b.전화번호, 상품명, 주문개수, 주소, 주문날짜\r\n"
					+ "FROM 주문 a, 주문고객 b, 상품 c\r\n" + "WHERE a.전화번호 = b.전화번호 and a.상품고유번호 = c.상품고유번호 and " + search_how + " LIKE '%" + searchTextField.getText()
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

}
