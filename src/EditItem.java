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

public class EditItem extends JFrame {

	public String itemNumber;
	String 상품고유번호;
	DB_Connector dbConn = new DB_Connector();
	int ret = 1;	//파일 선택 여부를 알려주는 변수
	JButton bookAddButton;
	private JPanel contentPane;
	private JTextField itemName;
	private JTextField itemCategory;
	private JTextField itemPrice;
	private JTextField itemTotal;
	private JTextField itemImage;
	private JTextField itemNumberTextField;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public EditItem(String 상품고유번호, ItemInfo itemInfo) {
		this.상품고유번호 = 상품고유번호;
		
		setTitle("꽃관리 프로그램 - 상품수정");
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
		panel_1.setBounds(12, 31, 805, 305);
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
		// 책 가격 텍스트필드
		itemPrice = new JTextField();
		itemPrice.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		itemPrice.setColumns(10);
		itemPrice.setBounds(136, 109, 157, 26);
		panel_1.add(itemPrice);

		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(91, 202, 225, 15);
		panel_1.add(lblNewLabel);
		
		JLabel bookPriceLabel_1 = new JLabel("재고량");
		bookPriceLabel_1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookPriceLabel_1.setBounds(12, 155, 104, 26);
		panel_1.add(bookPriceLabel_1);
		
		itemTotal = new JTextField();
		itemTotal.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		itemTotal.setColumns(10);
		itemTotal.setBounds(136, 155, 157, 26);
		panel_1.add(itemTotal);
		
		JLabel bookPriceLabel_1_1 = new JLabel("상품 이미지 URL");
		bookPriceLabel_1_1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookPriceLabel_1_1.setBounds(12, 202, 131, 26);
		panel_1.add(bookPriceLabel_1_1);
		
		itemImage = new JTextField();
		itemImage.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		itemImage.setColumns(10);
		itemImage.setBounds(136, 202, 407, 26);
		panel_1.add(itemImage);
		
		JLabel bookPriceLabel_1_1_1 = new JLabel("상품고유번호");
		bookPriceLabel_1_1_1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookPriceLabel_1_1_1.setBounds(12, 252, 131, 26);
		panel_1.add(bookPriceLabel_1_1_1);
		
		itemNumberTextField = new JTextField();
		itemNumberTextField.setEnabled(false);
		itemNumberTextField.setEditable(false);
		itemNumberTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		itemNumberTextField.setColumns(10);
		itemNumberTextField.setBounds(136, 252, 407, 26);
		panel_1.add(itemNumberTextField);
		
		JButton itemEditButton = new JButton("수정하기");
		itemEditButton.setBounds(297, 485, 243, 54);

		// 꽃 수정하는 버튼
		itemEditButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isSuccess = false;

				String query = "UPDATE 상품\r\n"
						+ "SET 상품명 = ?, 카테고리 = ?, 가격 = ?, 재고 = ?, 이미지 = ?\r\n"
						+ "WHERE 상품고유번호 = ?";
					
				try { // DB 접근
					dbConn.DB_Connect();
					PreparedStatement ps = dbConn.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					
					ps.setString(1, itemName.getText());
					ps.setString(2,  itemCategory.getText());
					ps.setString(3,  itemPrice.getText());
					ps.setString(4,  itemTotal.getText());
					ps.setString(5,  itemImage.getText());
					ps.setString(6,  상품고유번호);
					
					
					int count = ps.executeUpdate();
					if (count == 0) {
						JOptionPane.showMessageDialog(null, "상품 수정에 실패하였습니다.", "상품 수정 실패",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "상품 수정에 성공하였습니다.", "상품 수정 성공",
								JOptionPane.NO_OPTION);
					}
				}
				catch (NumberFormatException e1) {
					e1.printStackTrace();	//에러 추적
					JOptionPane.showMessageDialog(null, "가격에 숫자만 입력가능합니다.", "입력 오류", JOptionPane.ERROR_MESSAGE);	//가격에 문자 입력시 메시지 호출
				}
				catch(SQLIntegrityConstraintViolationException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "입력한 ISBN이 이미 존재합니다.", "입력 오류", JOptionPane.ERROR_MESSAGE);	//가격에 문자 입력시 메시지 호출
				}
				catch (SQLException e1) {
					e1.printStackTrace();	//에러 추적
					System.out.println("도서추가 화면에서 SQL 실행 에러");
				}
				try {
					itemInfo.getItemInfo();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				dispose();
			}
		});
		contentPane.add(itemEditButton);
		
		getItemInfo();
	
	}
	
	public void getItemInfo() {
		try {
			String query = "SELECT 상품고유번호, 상품명, 카테고리, 가격, 재고, 이미지 FROM 상품 WHERE 상품고유번호 = ?";
			dbConn.DB_Connect();
			PreparedStatement ps = dbConn.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setString(1, this.상품고유번호);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {

				
				itemName.setText(rs.getString("상품명")); 
				itemCategory.setText(rs.getString("카테고리"));
				itemPrice.setText(rs.getString("가격"));
				itemTotal.setText(rs.getString("재고")); 
				itemImage.setText(rs.getString("이미지"));
				itemNumberTextField.setText(rs.getString("상품고유번호"));

				break;
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}