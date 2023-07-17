package modificationDialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class DonutModificationDialog  extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnConfirm;
	private JButton btnCancel;
	private JLabel lblX;
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtRadius;
	private JTextField txtInnerRadius;
	private boolean isConfirmed;
	private JButton btnBorderColor;
	private JButton btnAreaColor;

	public DonutModificationDialog() {
		
		setBounds(100, 100, 452, 300);
		setTitle("Modify donut");
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblX = new JLabel("X:");
			lblX.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		JLabel lblY = new JLabel("Y:");
		lblY.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtX = new JTextField();
		txtX.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtX.setColumns(10);
		txtY = new JTextField();
		txtY.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtY.setColumns(10);
		JLabel lblTitle = new JLabel("Donut");
		
		btnBorderColor = new JButton("Border color");
		btnBorderColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(null, "Izaberite boju", btnBorderColor.getBackground());
				if(color != null) {
					 btnBorderColor.setBackground(color);
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("Center");
		
		JLabel lblWidth = new JLabel("Radius:");
		lblWidth.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblY_1 = new JLabel("Inner radius");
		lblY_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtRadius = new JTextField();
		txtRadius.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtRadius.setColumns(10);
		
		txtInnerRadius = new JTextField();
		txtInnerRadius.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtInnerRadius.setColumns(10);
		
		btnAreaColor = new JButton("Area color");
		btnAreaColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(null, "Izaberite boju", btnAreaColor.getBackground());
				if(color != null) {
					btnAreaColor.setBackground(color);
				}
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(24)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblX)
										.addComponent(lblY))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED, 9, Short.MAX_VALUE)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblTitle)
										.addComponent(lblWidth)
										.addComponent(lblY_1))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(txtInnerRadius, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(121)
									.addComponent(btnBorderColor)
									.addGap(40)
									.addComponent(btnAreaColor, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)))
							.addGap(52))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(70)
							.addComponent(lblNewLabel)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(8)
					.addComponent(lblTitle)
					.addGap(18)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblX)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWidth, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblY)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtInnerRadius, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblY_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAreaColor)
						.addComponent(btnBorderColor))
					.addGap(29))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel pnlSouth = new JPanel();
			getContentPane().add(pnlSouth, BorderLayout.SOUTH);
			{
				btnConfirm = new JButton("Confirm");
				btnConfirm.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (txtX.getText().trim().equals("") || txtY.getText().trim().equals("")) {
							getToolkit().beep();
							JOptionPane.showMessageDialog(null, "Some fields are empty", "Error",
									JOptionPane.ERROR_MESSAGE, null);
							isConfirmed = false;
							return;
						}

						try {
							validate(txtX.getText(), txtY.getText());
						} catch (NumberFormatException exc) {
							getToolkit().beep();
							JOptionPane.showMessageDialog(null, "Invalid data type", "Error", JOptionPane.ERROR_MESSAGE,
									null);
							isConfirmed = false;
							return;
						}
						if (Integer.parseInt(txtX.getText()) < 1 || Integer.parseInt(txtY.getText()) < 1) {
							getToolkit().beep();
							JOptionPane.showMessageDialog(null, "Width and height must be positive!", "Error",
									JOptionPane.ERROR_MESSAGE, null);
							isConfirmed = false;
							return;
						} else {
							isConfirmed = true;
							dispose();
						}
					}
				});
				btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 15));
				btnConfirm.setActionCommand("OK");
				getRootPane().setDefaultButton(btnConfirm);
			}
			{
				btnCancel = new JButton("Cancel");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
				btnCancel.setActionCommand("Cancel");
			}
			GroupLayout gl_pnlSouth = new GroupLayout(pnlSouth);
			gl_pnlSouth.setHorizontalGroup(
					gl_pnlSouth.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlSouth.createSequentialGroup()
							.addGap(111).addComponent(btnConfirm).addGap(132).addComponent(btnCancel).addGap(81)));
			gl_pnlSouth.setVerticalGroup(gl_pnlSouth.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_pnlSouth.createSequentialGroup().addGap(5)
							.addGroup(gl_pnlSouth.createParallelGroup(Alignment.BASELINE).addComponent(btnConfirm)
									.addComponent(btnCancel))));
			pnlSouth.setLayout(gl_pnlSouth);
		}
		
	}

	public void validate(String width, String height) {
		String supp = "^(([+-])?([1-9]{1})([0-9]+)?)$";
		if (!width.matches(supp) || !height.matches(supp)) {
			throw new NumberFormatException();
		}
	}

	public boolean isConfirmed() {
		return isConfirmed;
	}

	public void setConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public String getTxtStartX() {
		return txtX.getText();
	}

	public void setTxtStartX(String txtX) {
		this.txtX.setText(txtX);
	}

	public String getTxtStartY() {
		return txtY.getText();
	}

	public void setTxtStartY(String txtY) {
		this.txtY.setText(txtY);
	}
	
	public String getTxtRadius() {
		return txtRadius.getText();
	}

	public void setTxtRadius(String txtX) {
		this.txtRadius.setText(txtX);
	}

	public String getTxtInnerRadius() {
		return txtInnerRadius.getText();
	}

	public void setTxtInnerRadius(String txtY) {
		this.txtInnerRadius.setText(txtY);
	}
	
	public Color getBorderColor() {
		return btnBorderColor.getBackground();
	}
	
	public void setBorderColor(Color color) {
		btnBorderColor.setBackground(color);
	}
	
	public Color getAreaColor() {
		return btnAreaColor.getBackground();
	}
	
	public void setAreaColor(Color color) {
		btnAreaColor.setBackground(color);
	}
}