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

public class UserManage extends JFrame {
	
	String user_phone = "";
	Boolean manager = false;
	private JPanel contentPane;
	private JTextField searchTextField;
	private JTable table;
	private Main mainFrame;


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
		
		
		
		
		
		
		// 검색 패널
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 245));
		panel.setBounds(22, 57, 819, 38);
		contentPane.add(panel);
		panel.setLayout(null);

		// 검색 목록 콤보박스
		JComboBox searchComboBox = new JComboBox();
		searchComboBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 15));
		searchComboBox.setModel(new DefaultComboBoxModel(
				new String[] { "\uC804\uCCB4", "\uC774\uB984", "\uC804\uD654\uBC88\uD638", "\uC774\uBA54\uC77C" }));
		searchComboBox.setBackground(Color.WHITE);
		searchComboBox.setBounds(0, 0, 129, 38);
		panel.add(searchComboBox);

		// 검색 입력 텍스트필드
		searchTextField = new JTextField();
		searchTextField.setBounds(129, 0, 569, 38);
		panel.add(searchTextField);
		searchTextField.setColumns(10);

		// 검색 버튼
		JButton searchButton = new JButton("검색");
//		searchButton.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				try {
//					String search_how = "전체"; // 검색 조건이 들어갈 search_how (이름, 전화번호..)
//					ResultSet rs;
//					switch (searchComboBox.getSelectedItem().toString()) {
//					case "이름": // 검색조건이 "이름"일 때
//						search_how = "NAME";
//						break;
//					case "전화번호": // 검색조건이 "전화번호"일 때
//						search_how = "PHONE";
//						break;
//					case "이메일": // 검색조건이 "이메일"일 때
//						search_how = "MAIL";
//						break;
//					case "전체": // 검색조건이 "전체"일 때
//						search_how = "NAME LIKE '" + searchTextField.getText() + "%' OR USER_PHONE LIKE '"
//								+ searchTextField.getText() + "%' OR USER_MAIL";
//						break;
//					}
//					//rs = dbConn.executeQuery(
//						//	"SELECT USER_NAME, USER_MAIL, USER_PHONE, USER_BIRTH, USER_SUSPENSION, USER_OUT_DATE\r\n"
//							//		+ "FROM USER " + "WHERE (USER_" + search_how +" LIKE '" + searchTextField.getText()
//								//	+ "%')AND USER_MANAGER = false;"); // DB에서 검생창에 입력된 값으로 유저 정보 검색
//					if (rs != null) // 검색결과가 있으면
//					{
//						System.out.println("검색완료");
//						set_table(rs); // 테이블 재구성
//					} else // 없으면
//						System.out.println("검색결과가 없습니다.");
//				} catch (SQLException e2) {
//					e2.printStackTrace();
//					System.out.println("SQL 실행 에러");
//				}
//			}
//		});
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

		String[] columns = { "이름", "전화번호", "등급"}; // 테이블의 구성
		String[][] data;
		DefaultTableModel model = new DefaultTableModel(null, columns);
		table.setModel(model); // 테이블 세팅

//		try { // DB 접근
//			ResultSet rs = dbConn.executeQuery(
//					"SELECT USER_NAME, USER_MAIL, USER_PHONE, USER_BIRTH, USER_SUSPENSION, USER_OUT_DATE FROM USER WHERE USER_MANAGER =false;");
//			set_table(rs); // 관리자를 제외한 회원들의 정보로 테이블을 구성
//		} catch (SQLException e) {
//			System.out.println("회원검색창 초기 테이블 구성중 SQL 실행 에러");
//		}
		// 간격 조절
		table.getColumnModel().getColumn(0).setPreferredWidth(110);
		table.getColumnModel().getColumn(1).setPreferredWidth(101);
		table.getColumnModel().getColumn(2).setPreferredWidth(106);
		table.getColumnModel().getColumn(3).setPreferredWidth(115);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(115);
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
				data[i][0] = rs.getString("USER_NAME"); // 회원 이름
				data[i][1] = rs.getString("USER_MAIL"); // 회원 메일
				data[i][2] = rs.getString("USER_PHONE"); // 회원 전화번호
				data[i][3] = rs.getString("USER_BIRTH"); // 회원 생일
				// 회원 정지여부
				if (rs.getString("USER_SUSPENSION") == null) {
					data[i][4] = "N";
				} else {
					data[i][4] = rs.getString("USER_SUSPENSION").substring(0, 16);
				}

				// 회원 탈퇴여부
				if (rs.getString("USER_OUT_DATE") == null) {
					data[i][5] = "N";
				} else {
					data[i][5] = rs.getString("USER_OUT_DATE").substring(0, 10);
				}
				i++;
			}
			String[] columns = { "이름", "이메일", "전화번호", "생년월일", "정지여부", "탈퇴여부" }; // 테이블의 구성
			table.setModel(new DefaultTableModel(data, columns)); // 테이들 다시 세팅
		} catch (SQLException e) {
			System.out.println("회원검색창에서 테이블 구성중 SQL 실행 에러");
		}
	}

}
