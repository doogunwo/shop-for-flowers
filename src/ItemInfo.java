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
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.DropMode;
import javax.swing.ImageIcon;

import java.awt.Panel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import java.awt.Window.Type;
import javax.swing.UIManager;

public class ItemInfo extends JFrame {
	DB_Connector dbConn = new DB_Connector();
	String 상품고유번호;
	int bookReviewCnt = 0;
	int bookReviewGrade = 0;
	private JPanel contentPane;
	JLabel bookPictureLabel;
	JLabel flowerNameLabel;
	JLabel flowerNumber;
	JLabel flowerCategory ;
	JLabel flowerPrice;
	JLabel flowerInventory;
	JLabel flowerTotalOrder;
	JLabel flowerComposition;
	
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ItemInfo(String 상품고유번호){
		this.상품고유번호 = 상품고유번호;
		EditItem editItemFrame = new EditItem(상품고유번호, this);
		setTitle("꽃관리 프로그램 - 상품정보");
		setBounds(100, 100, 848, 681);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(12, 31, 181, 239);
		contentPane.add(panel);
		panel.setLayout(null);

		// 책 사진 라벨
		bookPictureLabel = new JLabel("상품사진");
		bookPictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookPictureLabel.setBounds(12, 10, 157, 170);
		panel.add(bookPictureLabel);

		// 책 삭제 버튼
		JButton itemDeleteButton = new JButton("\uC0AD\uC81C");
		itemDeleteButton.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		itemDeleteButton.setBounds(92, 210, 77, 19);
		itemDeleteButton.addMouseListener(new MouseAdapter() {
			// 삭제하기 버튼을 클릭했을 때
			@Override
			public void mouseClicked(MouseEvent e) {

				String query = "DELETE FROM 상품\r\n"
						+ "WHERE 상품고유번호 = ?";
				
				try {
					PreparedStatement ps = dbConn.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					ps.setString(1, 상품고유번호);
					int count = ps.executeUpdate();
					if (count == 0) {
						JOptionPane.showMessageDialog(null, "상품 삭제에 실패하였습니다.", "상품 삭제 실패",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "상품 삭제에 성공하였습니다.", "상품 삭제 성공",
								JOptionPane.NO_OPTION);

					}
					
				} catch (SQLException e1) {
					e1.printStackTrace(); // 에러 추적
					System.out.println("상품 삭제 과정에서 SQL 실행 에러");
				}
			}
		});
		panel.add(itemDeleteButton);
		

		// 책 정보 패널
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(205, 31, 612, 291);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		// 책 이름 라벨
		flowerNameLabel = new JLabel("상품이름");
		flowerNameLabel.setFont(new Font("\uD568\uCD08\uB86C\uB3CB\uC6C0", flowerNameLabel.getFont().getStyle() | Font.BOLD,
				flowerNameLabel.getFont().getSize() + 10));
		flowerNameLabel.setBounds(12, 10, 178, 46);
		panel_1.add(flowerNameLabel);

		// 책 저자 라벨
		 flowerNumber = new JLabel("상품고유번호 : 123123");
		flowerNumber.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		flowerNumber.setHorizontalAlignment(SwingConstants.LEFT);
		flowerNumber.setBounds(12, 67, 305, 26);
		panel_1.add(flowerNumber);

		// 책 출판사 라벨
		flowerCategory = new JLabel("카테고리 : ㅁㄴㅇ");
		flowerCategory.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		flowerCategory.setBounds(12, 102, 193, 26);
		panel_1.add(flowerCategory);

		// 책 가격 라벨
		flowerPrice = new JLabel("\uAC00\uACA9 : 28000\uC6D0");
		flowerPrice.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		flowerPrice.setBounds(12, 138, 193, 26);
		panel_1.add(flowerPrice);

		// 책 ISBN 라벨
		flowerInventory = new JLabel("재고량 : 12");
		flowerInventory.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		flowerInventory.setBounds(12, 173, 193, 26);
		panel_1.add(flowerInventory);
		
		flowerTotalOrder = new JLabel("총 주문수 : ");
		flowerTotalOrder.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		flowerTotalOrder.setBounds(12, 209, 193, 26);
		panel_1.add(flowerTotalOrder);
		
		flowerComposition = new JLabel("구성 꽃 : ");
		flowerComposition.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		flowerComposition.setBounds(12, 245, 193, 26);
		panel_1.add(flowerComposition);


		// 책 수정 버튼
		JButton bookEditButton = new JButton("\uC218\uC815");
		bookEditButton.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		bookEditButton.setBounds(12, 210, 77, 19);
		bookEditButton.addMouseListener(new MouseAdapter() {
			// 수정하기 버튼을 클릭했을 때
			@Override
			public void mouseClicked(MouseEvent e) {

				editItemFrame.setLocationRelativeTo(null); // 화면중앙에 출력
				editItemFrame.setResizable(false); // 창 크기 고정
				editItemFrame.setVisible(true); // 책 정보창 띄움
			}
		});
		panel.add(bookEditButton);
		try {
			
			getItemInfo();
		}catch (SQLException e1) {
			e1.printStackTrace(); // 에러 추적
			
		}
	}
	
	
	public void getItemInfo() throws SQLException{

		try { // DB 접근
			
			String query = "SELECT 상품고유번호, 상품명, 카테고리, 가격, 재고, 이미지 FROM 상품 WHERE 상품고유번호 = ?";
			dbConn.DB_Connect();
			
			PreparedStatement ps = dbConn.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setString(1, 상품고유번호);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {

				flowerNameLabel.setText(rs.getString("상품명")); 
				flowerNumber.setText("상품고유번호 : " + rs.getString("상품고유번호"));
				flowerCategory.setText("카테고리 : " + rs.getString("카테고리"));
				flowerPrice.setText("가격 : " + rs.getString("가격")); 
				flowerInventory.setText("재고 : " + rs.getString("재고"));

				// 책 이미지 설정
				String src = rs.getString("이미지"); // 이미지를 읽어옴
				try {
					//Image img = ImageIO.read(inputStream); // 읽어온 이미지를 img에 저장
					//Image resize_img = img.getScaledInstance(170, 170, Image.SCALE_SMOOTH); // 이미지 크기 170x170로 크기 조절하여
																							// resize_img에 저장
					ImageIcon icon = new ImageIcon(new URL(src)); // 조절한 크기의 이미지를 icon에 저장
					Image img = icon.getImage();
					Image changeImg = img.getScaledInstance(170, 220, Image.SCALE_SMOOTH);
					ImageIcon changeIcon = new ImageIcon(changeImg);
					bookPictureLabel.setIcon(changeIcon); // 책 이미지 설정
					bookPictureLabel.setBorder(new LineBorder(Color.black, 1, false)); // 이미지 레이블 테두리 검은색으로 설정
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			System.out.println("SQL 실행 에러");
		}
	}
}