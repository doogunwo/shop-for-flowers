package shop_for_flowers_UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JScrollBar;
import javax.swing.AbstractListModel;
import javax.swing.border.CompoundBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.SystemColor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ItemSearch extends JFrame {
	DB_Connector dbConn = new DB_Connector();
	
	public String user_phone = "";
	public Boolean manager = false;
	private JPanel contentPane;
	private JTextField searchTextField;
	private JTable table;
	JComboBox searchComboBox;
	JCheckBox[] jcb = new JCheckBox[10];
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	JRadioButton headerRadioButton;
	JRadioButton recentRadioButton ;
	JRadioButton popularityRadioButton;
	List<RowSorter.SortKey> sortKeys;
	private int t = 0;
	private Main mainFrame;
	private int checkNum = 0;
	
	//필터링 변수
	HashMap<Object, RowFilter<Object, Object>> borrowFilter = new HashMap<>();
	HashMap<Object, RowFilter<Object, Object>> categoryFilter = new HashMap<>();	//RowFilter 오브젝트 주소가 동적으로 바껴서 해시맵으로 키값으로 필터 처리
	TableRowSorter<TableModel> trs;
	
	OrderManage orderManageFrame;
	UserManage userManageFrame;
	ItemManage itemManageFrame;
	FlowerManage flowerManageFrame;
	/**
	 * 도서 찾기 메인화면
	 */
	public ItemSearch(String user_phone, Boolean manager) {
		this.user_phone = user_phone;
		this.manager = manager;
		setTitle("꽃관리 프로그램 - 상품검색");
		setBounds(100, 100, 881, 706);
		contentPane = new JPanel(); // 메인 프레임
		contentPane.setBackground(SystemColor.menu);
		setContentPane(contentPane);
		contentPane.setLayout(null);
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
		panel.setBounds(22, 57, 819, 38);
		contentPane.add(panel);
		panel.setLayout(null);

		// 검색 항목 콤보박스
		searchComboBox = new JComboBox();
		searchComboBox.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 14));
		searchComboBox.setModel(new DefaultComboBoxModel(new String[] {"전체", "상품고유번호", "상품명", "카테고리", "가격", "재고"}));
		searchComboBox.setBackground(Color.WHITE);
		searchComboBox.setBounds(0, 0, 129, 38);
		panel.add(searchComboBox);

		// 검색 입력 영역 텍스트필드
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
		searchButton.setFont(new Font("함초롬돋움", Font.BOLD, 14));
		searchButton.setBounds(690, 0, 129, 38);
		panel.add(searchButton);

		// 검색 목록 패널
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(169, 112, 672, 520);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		// 검색 목록 스크롤 패널
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 672, 520);
		panel_1.add(scrollPane);

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
			String query = "SELECT 상품고유번호, 상품명, 카테고리, 가격, 재고, 이미지 FROM 상품";
			dbConn.DB_Connect();
			Statement stmt = dbConn.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		            ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(query);
			
			set_table(rs);
			
			//setTrs();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 실행 에러");
		}

		// 검색 세부사항 패널
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.text);
		panel_2.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel_2.setBounds(22, 112, 135, 520);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		

		
		
		
		

		// 분류 라벨
		JLabel sortLabel = new JLabel("\uC815\uB82C");
		sortLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 15));
		sortLabel.setBounds(12, 10, 70, 20);
		panel_2.add(sortLabel);
		
		

		sortKeys = new ArrayList<>();
		
		//상품명순 라디오버튼
		headerRadioButton = new JRadioButton("상품명순");
		
		buttonGroup_1.add(headerRadioButton);
		headerRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					try {
						
					String query = "SELECT 상품고유번호, 상품명, 카테고리, 가격, 재고, 이미지 FROM 상품 ORDER BY 상품명 ASC";
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
		headerRadioButton.setSelected(false);
		headerRadioButton.setSelected(true);
		
		headerRadioButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		headerRadioButton.setBackground(Color.WHITE);
		headerRadioButton.setBounds(12, 50, 113, 23);
		panel_2.add(headerRadioButton);
		
		// 최신순 라디오버튼
		recentRadioButton = new JRadioButton("인기순");
		recentRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					try {
						
					String query = "SELECT 상품고유번호, 상품명, 카테고리, 가격, 재고, 이미지 FROM 상품";
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
		
		recentRadioButton.setBackground(Color.WHITE);
		buttonGroup_1.add(recentRadioButton);
		recentRadioButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		recentRadioButton.setBounds(12, 115, 113, 23);
		panel_2.add(recentRadioButton);

		// 재고순 라디오버튼
		popularityRadioButton = new JRadioButton("재고순");
		popularityRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					try {
						
					String query = "SELECT 상품고유번호, 상품명, 카테고리, 가격, 재고, 이미지 FROM 상품 ORDER BY 재고 ASC";
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
		popularityRadioButton.setBackground(Color.WHITE);
		buttonGroup_1.add(popularityRadioButton);
		popularityRadioButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		popularityRadioButton.setBounds(12, 147, 113, 23);
		panel_2.add(popularityRadioButton);
		
		// 최신순
		JRadioButton popularityRadioButton_1 = new JRadioButton("최신순");
		popularityRadioButton_1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		popularityRadioButton_1.setBackground(Color.WHITE);
		popularityRadioButton_1.setBounds(12, 83, 113, 23);
		panel_2.add(popularityRadioButton_1);
		
		popularityRadioButton_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					try {
						
					String query = "SELECT 상품고유번호, 상품명, 카테고리, 가격, 재고, 이미지 FROM 상품 ";
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
		
		buttonGroup_1.add(popularityRadioButton_1);
	}
	
	//AndFilter와 OrFilter 결합 함수
	public void combineOrAndFilters() {
		RowFilter<Object, Object> or;
		RowFilter<Object, Object> and;
		ArrayList<RowFilter<Object, Object>> finalFilters = new ArrayList<RowFilter<Object, Object>>();
		
		if (categoryFilter.size() > 0) {
			or = RowFilter.orFilter(categoryFilter.values());	//카테고리 필터는 or필터로 적용
			finalFilters.add(or);
		}
		if (borrowFilter.size() > 0) {
			and = RowFilter.andFilter(borrowFilter.values());	//대출 필터는 and필터로 적용
			finalFilters.add(and);
		}
		trs.setRowFilter(RowFilter.andFilter(finalFilters)); //카테고리와 대출 필터는 and관계
	}
	
	//테이블 필터링을 위한 초기화 함수
//	public void setTrs() {
//		trs = new TableRowSorter<>(table.getModel()); 	
//		table.setRowSorter(trs);
//		trs.setComparator(5, new Comparator<Object>(){	//JTable은 기본적으로 문자열로 정렬하기 때문에 두자리수 이상부터 적용이안되서 따로 비교하는 함수를 작성
//			        public int compare(Object o1, Object o2){
//			        	Integer a = Integer.parseInt((String) o1);
//			        	Integer b = Integer.parseInt((String) o2);
//			            return a.compareTo(b);
//			        }
//			    });
//				
//				trs.setComparator(6, new Comparator<Object>(){	//JTable은 기본적으로 문자열로 정렬하기 때문에 두자리수 이상부터 적용이안되서 따로 비교하는 함수를 작성
//			        public int compare(Object o1, Object o2){
//			        	Integer a = Integer.parseInt((String) o1);
//			        	Integer b = Integer.parseInt((String) o2);
//			            return a.compareTo(b);
//			        }
//			    });
//	}
	
	//검색 결과 도출하는 이벤트 함수
	public void search_event() {
		
		try { // DB 접근
			String search_how = "전체"; // 검색 조건이 들어갈 search_how (제목, 저자..)
			ResultSet rs;
			System.out.println(searchComboBox.getSelectedItem().toString());
			switch (searchComboBox.getSelectedItem().toString()) {
			case "상품고유번호": // 검색조건이 "제목"일 때
				search_how = "상품고유번호";
				break;
			case "상품명": // 검색조건이 "저자"일 때
				search_how = "상품명";
				break;
			case "카테고리": // 검색조건이 "출판사"일 때
				search_how = "카테고리";
				break;
			case "가격":
				search_how = "가격";
			case "재고":
				search_how = "재고";
			case "전체": // 검색조건이 "전체"일 때
				search_how = "(상품고유번호 || 상품명 || 카테고리 || 가격 || 재고)";
				break;
			}
			dbConn.DB_Connect();
			String query = "SELECT 상품고유번호, 상품명, 카테고리, 가격, 재고\r\n"
					+ "FROM 상품\r\n" + "WHERE " + search_how + " LIKE '%" + searchTextField.getText()
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
	
	
//	public void setFilter() {
//		if(canborrowRadioButton.isSelected()) {
//			borrowFilter.put(canborrowRadioButton.getText(), RowFilter.regexFilter(canborrowRadioButton.getText(), 4));	//테이블 5행에 있는 대출여부명을 필터항목에 추가시켜 해시맵에 삽입
//		}else if(borrowingNewRadioButton.isSelected()) {
//			borrowFilter.put(borrowingNewRadioButton.getText(), RowFilter.regexFilter(borrowingNewRadioButton.getText(), 4));	//테이블 5행에 있는 대출여부명을 필터항목에 추가시켜 해시맵에 삽입
//		}
//		
//		for(int i = 0; i < 8; i++) {
//			if(jcb[i].isSelected()) {
//				categoryFilter.put(jcb[i].getText(), RowFilter.regexFilter(jcb[i].getText(), 3));	//테이블 3행에 있는 카테고리명을 필터항목에 추가시켜 해시맵에 삽입
//				checkNum++;
//			}
//			combineOrAndFilters();
//			
//			if(headerRadioButton.isSelected()) {
//				sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
//			}
//			else if(recentRadioButton.isSelected()) {
//				sortKeys.add(new RowSorter.SortKey(7, SortOrder.DESCENDING));
//			}else if(popularityRadioButton.isSelected()) {
//				sortKeys.add(new RowSorter.SortKey(6, SortOrder.DESCENDING));
//			}else if(gradeRadioButton.isSelected()) {
//				sortKeys.add(new RowSorter.SortKey(5, SortOrder.DESCENDING));
//			}
//			
//			trs.setSortKeys(sortKeys);
//			
//			
//			
//		}
//	}

	// ResultSet을 받아 테이블 재구성하는 함수
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