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
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

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

public class EditFlower extends JFrame {
	DB_Connector dbConn = new DB_Connector();
	
	public String 이름="";	//유저 PK 정보를 저장할 변수
	public Boolean manager = false;	//유저가 관리자인지 확인할 변수
	int ret = 1;	//파일 선택 여부를 알려주는 변수
	JButton flowerEditButton;
	private JPanel contentPane;
	private JTextField flowerNameTextField;
	private JTextField flowerKindTextField;
	private JTextField flowerOriginTextField;
	private JTextField flowerPlanterTextField;
	private JTextField flowerHorseTextField;
	private JTextField flowerLinkTextField;
	private JTextField flowerDescriptionTextField;
	private JTextField flowerBloomTextField;
	FlowerInfo flowerInfo;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public EditFlower(String 이름, FlowerInfo flowerInfo) {
		this.이름 = 이름;
		this.flowerInfo = flowerInfo;
		
		setTitle("꽃관리 프로그램 - 꽃수정");
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
		panel_1.setBounds(12, 31, 805, 269);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		//책 이릅 라벨
		JLabel bookNameLabel = new JLabel("이름 :");
		bookNameLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookNameLabel.setBounds(12, 10, 46, 46);
		panel_1.add(bookNameLabel);

		// 책 이름 텍스트필드
		flowerNameTextField = new JTextField();
		flowerNameTextField.setFont(new Font("함초롬돋움", Font.BOLD, 22));
		flowerNameTextField.setBounds(92, 20, 407, 26);
		panel_1.add(flowerNameTextField);
		flowerNameTextField.setColumns(10);
		flowerNameTextField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				    changed();
				  }
				  public void removeUpdate(DocumentEvent e) {
				    changed();
				  }
				  public void insertUpdate(DocumentEvent e) {
				    changed();
				  }

				  public void changed() {
				     if (flowerHorseTextField.getText().equals("")){
				    	 flowerEditButton.setEnabled(false);
				     }
				     else {
				    	 flowerEditButton.setEnabled(true);
				    }

				  }
				});

		// 책 저자 라벨
		JLabel bookHeaderLabel_1 = new JLabel("종류 :");
		bookHeaderLabel_1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookHeaderLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		bookHeaderLabel_1.setBounds(12, 70, 46, 26);
		panel_1.add(bookHeaderLabel_1);

		// 책 저자 텍스트필드
		flowerKindTextField = new JTextField();
		flowerKindTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		flowerKindTextField.setColumns(10);
		flowerKindTextField.setBounds(92, 70, 157, 26);
		panel_1.add(flowerKindTextField);
		flowerKindTextField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				    changed();
				  }
				  public void removeUpdate(DocumentEvent e) {
				    changed();
				  }
				  public void insertUpdate(DocumentEvent e) {
				    changed();
				  }

				  public void changed() {
				     if (flowerHorseTextField.getText().equals("")){
				    	 flowerEditButton.setEnabled(false);
				     }
				     else {
				    	 flowerEditButton.setEnabled(true);
				    }

				  }
				});

		// 책 출판사 라벨
		JLabel bookHeaderLabel_2 = new JLabel("원산지 :");
		bookHeaderLabel_2.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookHeaderLabel_2.setBounds(270, 70, 60, 26);
		panel_1.add(bookHeaderLabel_2);

		// 책 출판사 텍스트필드
		flowerOriginTextField = new JTextField();
		flowerOriginTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		flowerOriginTextField.setColumns(10);
		flowerOriginTextField.setBounds(342, 70, 157, 26);
		panel_1.add(flowerOriginTextField);
		flowerOriginTextField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				    changed();
				  }
				  public void removeUpdate(DocumentEvent e) {
				    changed();
				  }
				  public void insertUpdate(DocumentEvent e) {
				    changed();
				  }

				  public void changed() {
				     if (flowerHorseTextField.getText().equals("")){
				    	 flowerEditButton.setEnabled(false);
				     }
				     else {
				    	 flowerEditButton.setEnabled(true);
				    }

				  }
				});

		// 책 관련링크 라벨
		JLabel bookLinkLabel = new JLabel("꽃 이미지 URL");
		bookLinkLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookLinkLabel.setBounds(12, 216, 117, 26);
		panel_1.add(bookLinkLabel);

		// 책 관련링크 텍스트필드
		flowerLinkTextField = new JTextField();
		flowerLinkTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		flowerLinkTextField.setColumns(10);
		flowerLinkTextField.setBounds(141, 216, 358, 26);
		panel_1.add(flowerLinkTextField);

		// 책 가격 라벨
		JLabel bookPriceLabel = new JLabel("파종기 :");
		bookPriceLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookPriceLabel.setBounds(12, 118, 68, 26);
		panel_1.add(bookPriceLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(91, 145, 225, 15);
		panel_1.add(lblNewLabel_1);
		// 책 가격 텍스트필드
		flowerPlanterTextField = new JTextField();
		flowerPlanterTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		flowerPlanterTextField.setColumns(10);
		flowerPlanterTextField.setBounds(92, 118, 157, 26);
		panel_1.add(flowerPlanterTextField);

		


		// 책 ISBN 라벨
		JLabel bookISBNLabel = new JLabel("꽃말 :");
		bookISBNLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookISBNLabel.setBounds(12, 170, 58, 26);
		panel_1.add(bookISBNLabel);
		

		// 책 ISBN 텍스트필드
		flowerHorseTextField = new JTextField();
		flowerHorseTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		flowerHorseTextField.setColumns(10);
		flowerHorseTextField.setBounds(141, 170, 358, 26);
		panel_1.add(flowerHorseTextField);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(91, 202, 225, 15);
		panel_1.add(lblNewLabel);
		
		JLabel bookPriceLabel_1 = new JLabel("개화기 :");
		bookPriceLabel_1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookPriceLabel_1.setBounds(270, 118, 68, 26);
		panel_1.add(bookPriceLabel_1);
		
		flowerBloomTextField = new JTextField();
		flowerBloomTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		flowerBloomTextField.setColumns(10);
		flowerBloomTextField.setBounds(342, 118, 157, 26);
		panel_1.add(flowerBloomTextField);
		

		// 책 줄거리 패널
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(12, 310, 805, 177);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(128, 128, 128), 1, true));
		scrollPane.setBounds(0, 0, 805, 177);
		panel_2.add(scrollPane);

		// 책 줄거리 텍스트필드
		flowerDescriptionTextField = new JTextField("설명을 입력하세요");
		scrollPane.setViewportView(flowerDescriptionTextField);
		flowerDescriptionTextField.setColumns(10);

		// 꽃 수정 버튼
		flowerEditButton = new JButton("꽃 수정하기");
		flowerEditButton.setEnabled(false);
		flowerEditButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 15));
		flowerEditButton.setBounds(348, 511, 132, 48);
		
		// 꽃 수정하는 버튼
		flowerEditButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isSuccess = false;

				String query = "UPDATE 꽃\r\n"
						+ "SET 이름 = ?, 종류 = ?, 원산지 = ?, 파종기 = ?, 개화기 = ?, 꽃말 = ?, 설명 = ?, 이미지주소 = ?\r\n"
						+ "WHERE 이름 = ?";
					
				try { // DB 접근
					dbConn.DB_Connect();
					PreparedStatement ps = dbConn.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					
					ps.setString(1, flowerNameTextField.getText());			//ISBN
					ps.setString(2, flowerKindTextField.getText());			//책 제목
					ps.setString(3, flowerOriginTextField.getText());		//책 저자
					ps.setString(4, flowerPlanterTextField.getText());		//책 출판사
					ps.setString(5, flowerBloomTextField.getText());	//책 가격	(숫자만 가능)
					ps.setString(6, flowerHorseTextField.getText());
					ps.setString(7, flowerDescriptionTextField.getText());	
					ps.setString(8, flowerLinkTextField.getText());			//책 관련링크
					ps.setString(9, 이름);			//책 관련링크
					
					
					int count = ps.executeUpdate();
					if (count == 0) {
						JOptionPane.showMessageDialog(null, "꽃 수정에 실패하였습니다.", "꽃 수정 실패",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "꽃 수정에 성공하였습니다.", "꽃 수정 성공",
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
					flowerInfo.getFlowerInfo();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				dispose();
			}
		});
		contentPane.add(flowerEditButton);	//책 추가 버튼 부착
		getFlowerInfo();
	
	}
	
	public void getFlowerInfo() {
		try {
			String query = "SELECT 이름, 종류, 원산지, 파종기, 개화기, 꽃말, 설명, 이미지주소 FROM 꽃 WHERE 이름 = ?";
			dbConn.DB_Connect();
			PreparedStatement ps = dbConn.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setString(1, this.이름);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {

				flowerNameTextField.setText(rs.getString("이름")); 
				flowerKindTextField.setText(rs.getString("종류"));
				flowerOriginTextField.setText(rs.getString("원산지"));
				flowerPlanterTextField.setText(rs.getString("파종기")); 
				flowerBloomTextField.setText(rs.getString("개화기"));
				flowerHorseTextField.setText(rs.getString("꽃말"));
				flowerDescriptionTextField.setText(rs.getString("설명"));
				flowerLinkTextField.setText(rs.getString("이미지주소"));
				break;
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}