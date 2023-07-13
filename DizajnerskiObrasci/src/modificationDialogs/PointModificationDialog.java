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
import javax.swing.border.EmptyBorder;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PointModificationDialog  extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnConfirm;
	private JButton btnCancel;
	private JLabel lblX;
	private JTextField txtX;
	private JTextField txtY;
	private boolean isConfirmed;
	private JButton btnColor;

	public PointModificationDialog() {
		
		setBounds(100, 100, 450, 300);
		setTitle("Modify point");
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
		JLabel lblTitle = new JLabel("Point");
		
		btnColor = new JButton("Choose color");
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(null, "Izaberite boju", btnColor.getBackground());
				if(color != null) {
					 btnColor.setBackground(color);
				}
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(55)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblX)
								.addComponent(lblY))
							.addGap(105)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(105)
							.addComponent(lblTitle))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(155)
							.addComponent(btnColor)))
					.addContainerGap(113, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(23)
					.addComponent(lblTitle)
					.addGap(28)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblX))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblY)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
					.addComponent(btnColor)
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

	public String getTxtX() {
		return txtX.getText();
	}

	public void setTxtX(String txtX) {
		this.txtX.setText(txtX);
	}

	public String getTxtY() {
		return txtY.getText();
	}

	public void setTxtY(String txtY) {
		this.txtY.setText(txtY);
	}
	
	public Color getColor() {
		return btnColor.getBackground();
	}
	
	public void setColor(Color color) {
		btnColor.setBackground(color);
	}

}