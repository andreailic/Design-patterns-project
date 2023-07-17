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

public class LineModificationDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnConfirm;
	private JButton btnCancel;
	private JLabel lblX;
	private JTextField txtStartX;
	private JTextField txtStartY;
	private JTextField txtEndX;
	private JTextField txtEndY;
	private boolean isConfirmed;
	private JButton btnColor;

	public LineModificationDialog() {
		
		setBounds(100, 100, 450, 300);
		setTitle("Modify line");
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
		txtStartX = new JTextField();
		txtStartX.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtStartX.setColumns(10);
		txtStartY = new JTextField();
		txtStartY.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtStartY.setColumns(10);
		JLabel lblTitle = new JLabel("Line");
		
		btnColor = new JButton("Choose color");
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(null, "Izaberite boju", btnColor.getBackground());
				if(color != null) {
					 btnColor.setBackground(color);
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("Start Point");
		
		JLabel lblEndPoint = new JLabel("End Point");
		
		JLabel lblX_1 = new JLabel("X:");
		lblX_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblY_1 = new JLabel("Y:");
		lblY_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtEndX = new JTextField();
		txtEndX.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtEndX.setColumns(10);
		
		txtEndY = new JTextField();
		txtEndY.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtEndY.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(24)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblX)
								.addComponent(lblY))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtStartX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtStartY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(155)
							.addComponent(btnColor)))
					.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblX_1, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(txtEndX, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblY_1, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(txtEndY, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(214, Short.MAX_VALUE)
					.addComponent(lblTitle)
					.addGap(191))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(84)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
					.addComponent(lblEndPoint, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addGap(78))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblTitle)
					.addGap(19)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblEndPoint))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblX)
								.addComponent(txtStartX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblY)
								.addComponent(txtStartY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
							.addComponent(btnColor)
							.addGap(29))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(3)
									.addComponent(lblX_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtEndX, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(3)
									.addComponent(lblY_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtEndY, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel pnlSouth = new JPanel();
			getContentPane().add(pnlSouth, BorderLayout.SOUTH);
			{
				btnConfirm = new JButton("Confirm");
				btnConfirm.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (txtStartX.getText().trim().equals("") || txtStartY.getText().trim().equals("")) {
							getToolkit().beep();
							JOptionPane.showMessageDialog(null, "Some fields are empty", "Error",
									JOptionPane.ERROR_MESSAGE, null);
							isConfirmed = false;
							return;
						}

						try {
							validate(txtStartX.getText(), txtStartY.getText());
						} catch (NumberFormatException exc) {
							getToolkit().beep();
							JOptionPane.showMessageDialog(null, "Invalid data type", "Error", JOptionPane.ERROR_MESSAGE,
									null);
							isConfirmed = false;
							return;
						}
						if (Integer.parseInt(txtStartX.getText()) < 1 || Integer.parseInt(txtStartY.getText()) < 1) {
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
		return txtStartX.getText();
	}

	public void setTxtStartX(String txtX) {
		this.txtStartX.setText(txtX);
	}

	public String getTxtStartY() {
		return txtStartY.getText();
	}

	public void setTxtStartY(String txtY) {
		this.txtStartY.setText(txtY);
	}
	
	public String getTxtEndX() {
		return txtEndX.getText();
	}

	public void setTxtEndX(String txtX) {
		this.txtEndX.setText(txtX);
	}

	public String getTxtEndY() {
		return txtEndY.getText();
	}

	public void setTxtEndY(String txtY) {
		this.txtEndY.setText(txtY);
	}
	
	public Color getColor() {
		return btnColor.getBackground();
	}
	
	public void setColor(Color color) {
		btnColor.setBackground(color);
	}
}