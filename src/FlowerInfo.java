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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

public class FlowerInfo extends JFrame {
	DB_Connector dbConn = new DB_Connector();
	
	String 이름 = "";
	

	private JPanel contentPane;

	int userPoint = 0;
	boolean userSus = false;
	long diffDays = 0; // 연체일을 나타내는 변수
	boolean overdueUser = false;
	JLabel flowerHorse;
	Date susDate = null;
	Date returnDate;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date nowDate; 
	
	JLabel flowerNameLabel;
	JLabel flowerKind;
	JLabel flowerOrigin;
	JLabel flowerPlanter;
	JLabel flowerBloom;
	JTextArea flowerDescriptionLabel;
	JLabel bookPictureLabel;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 */
	public FlowerInfo(String 이름) {
		this.이름 = 이름; // 객체가 생성될때 매개변수로 받은 book_ISBN을 저장
		EditFlower editFlowerFrame = new EditFlower(이름, this);

		setTitle("꽃관리 프로그램 - 꽃정보");
		setBounds(100, 100, 848, 681);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(12, 31, 181, 275);
		contentPane.add(panel);
		panel.setLayout(null);

		// 꽃 사진 라벨
		bookPictureLabel = new JLabel("꽃 사진");
		bookPictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookPictureLabel.setBounds(12, 10, 157, 204);
		panel.add(bookPictureLabel);
		
		JButton flowerEditButton = new JButton("수정");

		flowerEditButton.addMouseListener(new MouseAdapter() {
			// 수정하기 버튼을 클릭했을 때
			@Override
			public void mouseClicked(MouseEvent e) {

				editFlowerFrame.setLocationRelativeTo(null); // 화면중앙에 출력
				editFlowerFrame.setResizable(false); // 창 크기 고정
				editFlowerFrame.setVisible(true); // 책 정보창 띄움
			}
		});
		flowerEditButton.setBounds(12, 224, 70, 41);
		panel.add(flowerEditButton);
		
		JButton flowerDeleteButton = new JButton("삭제");
		flowerDeleteButton.addMouseListener(new MouseAdapter() {
			// 삭제하기 버튼을 클릭했을 때
			@Override
			public void mouseClicked(MouseEvent e) {

				String query = "DELETE FROM 꽃\r\n"
						+ "WHERE 이름 = ?";
				
				try {
					PreparedStatement ps = dbConn.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					ps.setString(1, 이름);
					int count = ps.executeUpdate();
					if (count == 0) {
						JOptionPane.showMessageDialog(null, "꽃 삭제에 실패하였습니다.", "꽃 삭제 실패",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "꽃 삭제에 성공하였습니다.", "꽃 삭제 성공",
								JOptionPane.NO_OPTION);

					}
					
				} catch (SQLException e1) {
					e1.printStackTrace(); // 에러 추적
					System.out.println("꽃 삭제 과정에서 SQL 실행 에러");
				}
			}
		});
		flowerDeleteButton.setBounds(99, 224, 70, 41);
		panel.add(flowerDeleteButton);

		// 책 정보 패널
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(205, 31, 612, 275);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		// 책 이름 라벨
		flowerNameLabel = new JLabel("꽃이름");
		flowerNameLabel.setFont(new Font("\uD568\uCD08\uB86C\uB3CB\uC6C0", flowerNameLabel.getFont().getStyle() | Font.BOLD,
				flowerNameLabel.getFont().getSize() + 10));
		flowerNameLabel.setBounds(12, 10, 535, 46);
		panel_1.add(flowerNameLabel);

		// 책 저자 라벨
		flowerBloom = new JLabel("개화기 : 00");
		flowerBloom.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		flowerBloom.setHorizontalAlignment(SwingConstants.LEFT);
		flowerBloom.setBounds(12, 178, 330, 26);
		panel_1.add(flowerBloom);

		// 책 출판사 라벨
		flowerPlanter = new JLabel("파종기 : 00");
		flowerPlanter.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		flowerPlanter.setBounds(12, 142, 225, 26);
		panel_1.add(flowerPlanter);

		// 책 평점 라벨
		 flowerHorse = new JLabel("꽃말 : 00");
		flowerHorse.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		flowerHorse.setBounds(12, 214, 193, 26);
		panel_1.add(flowerHorse);
		
		flowerKind = new JLabel("종류 : 00");
		flowerKind.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		flowerKind.setBounds(12, 70, 225, 26);
		panel_1.add(flowerKind);
		
		flowerOrigin = new JLabel("원산지 : 00");
		flowerOrigin.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		flowerOrigin.setBounds(12, 106, 225, 26);
		panel_1.add(flowerOrigin);
		
				flowerDescriptionLabel = new JTextArea();
				flowerDescriptionLabel.setBounds(12, 340, 801, 239);
				contentPane.add(flowerDescriptionLabel);
				flowerDescriptionLabel.setBackground(new Color(255, 255, 255));
				flowerDescriptionLabel.setText(
						"꽃 설명\r\n");
				flowerDescriptionLabel.setLineWrap(true);
				flowerDescriptionLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
				
						JScrollPane scrollPane = new JScrollPane();
						scrollPane.setBounds(12, 340, 805, 239);
						contentPane.add(scrollPane);
						scrollPane.setViewportBorder(new LineBorder(new Color(128, 128, 128), 1, true));
						
								// 책 줄거리 패널
								JPanel panel_2 = new JPanel();
								scrollPane.setColumnHeaderView(panel_2);
								panel_2.setBackground(new Color(255, 255, 255));
								panel_2.setLayout(null);
				try {
					
					getFlowerInfo();
				}catch (SQLException e1) {
					e1.printStackTrace(); // 에러 추적
					
				}
	}
	
	public void getFlowerInfo() throws SQLException{

		try { // DB 접근
			
			String query = "SELECT 이름, 종류, 원산지, 파종기, 개화기, 꽃말, 설명, 이미지주소 FROM 꽃 WHERE 이름 = ?";
			dbConn.DB_Connect();
			PreparedStatement ps = dbConn.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setString(1, this.이름);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {

				flowerNameLabel.setText(rs.getString("이름")); 
				flowerKind.setText("종류 : " + rs.getString("종류"));
				flowerOrigin.setText("원산지 : " + rs.getString("원산지"));
				flowerPlanter.setText("파종기 : " + rs.getString("파종기")); 
				flowerBloom.setText("개화기 : " + rs.getString("개화기"));
				flowerHorse.setText("꽃말 : " + rs.getString("꽃말"));
				flowerDescriptionLabel.setText(rs.getString("설명"));

				// 책 이미지 설정
				String src = rs.getString("이미지주소"); // 이미지를 읽어옴
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
				
				
				flowerDescriptionLabel.setEnabled(false); // 도서 설명
				break;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			System.out.println("SQL 실행 에러");
		}
	}
}