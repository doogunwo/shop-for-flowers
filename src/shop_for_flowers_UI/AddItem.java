package shop_for_flowers_UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.DropMode;
import javax.swing.ImageIcon;

import java.awt.Panel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import java.awt.Window.Type;
import javax.swing.UIManager;
import javax.swing.JTextField;

public class AddItem extends JFrame {
	DB_Connector dbConn = new DB_Connector();

	public String user_phone="";	//유저 PK 정보를 저장할 변수
	public Boolean manager = false;	//유저가 관리자인지 확인할 변수
	int ret = 1;	//파일 선택 여부를 알려주는 변수
	JButton bookAddButton;
	private JPanel contentPane;
	private JTextField itemName;
	private JTextField itemCategory;
	private JTextField itemPrice;
	private JTextField itemTotal;
	private JTextField itemImage;
	private JTextField itemNumber;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public AddItem() {

		
		setTitle("꽃관리 프로그램 - 상품추가");
		setBounds(100, 100, 848, 622);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//이미지 파일 필터
		JFileChooser book_img = new JFileChooser();
		FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("All Images", "jpg","jpge","png","gif");
		book_img.setFileFilter(fileFilter);
		

		// 책 정보 패널
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(12, 31, 805, 293);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		//책 이릅 라벨
		JLabel bookNameLabel = new JLabel("상품명");
		bookNameLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookNameLabel.setBounds(12, 10, 94, 46);
		panel_1.add(bookNameLabel);

		// 책 이름 텍스트필드
		itemName = new JTextField();
		itemName.setFont(new Font("함초롬돋움", Font.BOLD, 22));
		itemName.setBounds(136, 17, 407, 26);
		panel_1.add(itemName);
		itemName.setColumns(10);


		// 책 저자 라벨
		JLabel bookHeaderLabel_1 = new JLabel("카테고리");
		bookHeaderLabel_1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookHeaderLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		bookHeaderLabel_1.setBounds(12, 60, 72, 26);
		panel_1.add(bookHeaderLabel_1);

		// 책 저자 텍스트필드
		itemCategory = new JTextField();
		itemCategory.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		itemCategory.setColumns(10);
		itemCategory.setBounds(136, 60, 157, 26);
		panel_1.add(itemCategory);


		// 책 가격 라벨
		JLabel bookPriceLabel = new JLabel("가격");
		bookPriceLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookPriceLabel.setBounds(12, 109, 104, 26);
		panel_1.add(bookPriceLabel);
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(318, 120, 225, 15);
		panel_1.add(lblNewLabel_1);
		
		// 책 가격 텍스트필드
		itemPrice = new JTextField();
		itemPrice.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		itemPrice.setColumns(10);
		itemPrice.setBounds(136, 109, 157, 26);
		itemPrice.addKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent ke) {
	             String value = itemPrice.getText();
	             int l = value.length();
	             if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
	            	 itemPrice.setEditable(true);
	            	 lblNewLabel_1.setText("");
	             } else {
	            	 itemPrice.setEditable(false);
	            	 lblNewLabel_1.setText("가격은 숫자만 가능합니다.");
	             }
	          }
	       });
		panel_1.add(itemPrice);
	
		
		JLabel bookPriceLabel_1 = new JLabel("재고량");
		bookPriceLabel_1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookPriceLabel_1.setBounds(12, 155, 104, 26);
		panel_1.add(bookPriceLabel_1);
		
	    // 재고량
		itemTotal = new JTextField();
		itemTotal.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		itemTotal.setColumns(10);
		itemTotal.setBounds(136, 155, 157, 26);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBounds(318, 155, 225, 15);
		panel_1.add(lblNewLabel_2);
		
		itemTotal.addKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent ke) {
	             String value = itemTotal.getText();
	             int l = value.length();
	             if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
	            	 itemTotal.setEditable(true);
	            	 lblNewLabel_2.setText("");
	             } else {
	            	 itemTotal.setEditable(false);
	            	 lblNewLabel_2.setText("재고량은 숫자만 가능합니다.");
	             }
	          }
	       });
		panel_1.add(itemTotal);
		
		JLabel bookPriceLabel_1_1 = new JLabel("상품 이미지 URL");
		bookPriceLabel_1_1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookPriceLabel_1_1.setBounds(12, 202, 130, 26);
		panel_1.add(bookPriceLabel_1_1);
		
		itemImage = new JTextField();
		itemImage.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		itemImage.setColumns(10);
		itemImage.setBounds(136, 202, 407, 26);
		panel_1.add(itemImage);
		
		JLabel bookPriceLabel_1_1_1 = new JLabel("상품고유번호");
		bookPriceLabel_1_1_1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookPriceLabel_1_1_1.setBounds(12, 248, 130, 26);
		panel_1.add(bookPriceLabel_1_1_1);
		
		itemNumber = new JTextField();
		itemNumber.setText("00000000");
		itemNumber.setEnabled(false);
		itemNumber.setEditable(false);
		itemNumber.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		itemNumber.setColumns(10);
		itemNumber.setBounds(136, 248, 407, 26);
		panel_1.add(itemNumber);

		// 책 추가 버튼
		bookAddButton = new JButton("상품 추가");
		bookAddButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 15));
		bookAddButton.setBounds(348, 511, 132, 48);
		//책 추가 버튼을 누르면 호출되는 메소드 연결
		bookAddButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isSuccess = false;

					String query = "insert into 상품(\r\n"
							+ "상품고유번호,\r\n"
							+ "상품명,\r\n"
							+ "카테고리,\r\n"
							+ "가격,\r\n"
							+ "재고,\r\n"
							+ "이미지\r\n"
							+ ")values(\r\n"
							+ "?, ?, ?, ?, ?, ?)";
					
				try { // DB 접근
					dbConn.DB_Connect();
					PreparedStatement ps = dbConn.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					
					ps.setString(1, itemNumber.getText());			
					ps.setString(2, itemName.getText());			
					ps.setString(3, itemCategory.getText());		
					ps.setString(4, itemPrice.getText());		
					ps.setString(5, itemTotal.getText());	
					ps.setString(6, itemImage.getText());

					
					int count = ps.executeUpdate();
					if(count==0) {	
						JOptionPane.showMessageDialog(null,"상품 등록에 실패했습니다.", "상품등록 실패", JOptionPane.ERROR_MESSAGE);
					}
					else {		
						JOptionPane.showMessageDialog(null,"상품 등록에 성공하였습니다.", "상품등록 성공", JOptionPane.NO_OPTION);
						isSuccess = true;
					}
				}
				catch (NumberFormatException e1) {
					e1.printStackTrace();	//에러 추적
					JOptionPane.showMessageDialog(null, "가격에 숫자만 입력가능합니다.", "입력 오류", JOptionPane.ERROR_MESSAGE);	//가격에 문자 입력시 메시지 호출
				}
				catch(SQLIntegrityConstraintViolationException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "입력한 상품이 이미 존재합니다.", "입력 오류", JOptionPane.ERROR_MESSAGE);	//가격에 문자 입력시 메시지 호출
				}
				catch (SQLException e1) {
					e1.printStackTrace();	//에러 추적
					System.out.println("상품추가 화면에서 SQL 실행 에러");
				}
				if(isSuccess)
					dispose();	//추가 후 창 닫기
			}
		});
		contentPane.add(bookAddButton);	
		
	
	}
}