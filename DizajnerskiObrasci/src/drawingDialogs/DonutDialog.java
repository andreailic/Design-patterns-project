package drawingDialogs;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class DonutDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel pnlCenter = new JPanel();
	private JButton btnConfirm;
	private JButton btnCancel;
	private JTextField txtRadius;

	private boolean isConfirmed;
	private JTextField txtInnerRadius;

	public DonutDialog() {
		setBounds(100, 100, 450, 300);
		setTitle("Draw donut");
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		pnlCenter.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlCenter, BorderLayout.CENTER);

		JLabel lblRadius = new JLabel("Radius:");

		txtRadius = new JTextField();
		txtRadius.setColumns(10);

		JLabel lblDonut = new JLabel("Donut");
		
		JLabel lblInnerRadius = new JLabel("Inner radius");
		
		txtInnerRadius = new JTextField();
		txtInnerRadius.setColumns(10);
		GroupLayout gl_pnlCenter = new GroupLayout(pnlCenter);
		gl_pnlCenter.setHorizontalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addGap(114)
					.addComponent(lblDonut)
					.addContainerGap(284, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_pnlCenter.createSequentialGroup()
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlCenter.createSequentialGroup()
							.addGap(63)
							.addComponent(lblRadius))
						.addGroup(gl_pnlCenter.createSequentialGroup()
							.addGap(52)
							.addComponent(lblInnerRadius)))
					.addPreferredGap(ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtInnerRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(119))
		);
		gl_pnlCenter.setVerticalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addGap(27)
					.addComponent(lblDonut)
					.addGap(74)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRadius))
					.addGap(18)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtInnerRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblInnerRadius))
					.addContainerGap(52, Short.MAX_VALUE))
		);
		pnlCenter.setLayout(gl_pnlCenter);
		{
			JPanel pnlSouth = new JPanel();
			getContentPane().add(pnlSouth, BorderLayout.SOUTH);
			{
				btnConfirm = new JButton("Confirm");
				btnConfirm.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (txtRadius.getText().trim().equals("")) {
							getToolkit().beep();
							JOptionPane.showMessageDialog(null, "Please insert radius!", "Error",
									JOptionPane.ERROR_MESSAGE, null);
							isConfirmed = false;
							return;
						}
						try {
							validate(txtRadius.getText());
						} catch (NumberFormatException exc) {
							getToolkit().beep();
							JOptionPane.showMessageDialog(null, "Invalid data type for radius!", "Error",
									JOptionPane.ERROR_MESSAGE, null);
							isConfirmed = false;
							return;
						}
						
						try {
							validate(txtInnerRadius.getText());
						} catch (NumberFormatException exc) {
							getToolkit().beep();
							JOptionPane.showMessageDialog(null, "Invalid data type for inner radius!", "Error",
									JOptionPane.ERROR_MESSAGE, null);
							isConfirmed = false;
							return;
						}
						
						if (Integer.parseInt(txtRadius.getText()) < 0) {
							getToolkit().beep();
							JOptionPane.showMessageDialog(null, "Radius must be greater than 0!", "Error",
									JOptionPane.ERROR_MESSAGE, null);
							isConfirmed = false;
							return;
						} else if (Integer.parseInt(txtInnerRadius.getText()) < 0) {
							JOptionPane.showMessageDialog(null, "Inner radius must be greater than 0!", "Error",
									JOptionPane.ERROR_MESSAGE, null);
							isConfirmed = false;
							return;
						} else if (Integer.parseInt(txtInnerRadius.getText()) >= Integer.parseInt(txtRadius.getText())) {
							JOptionPane.showMessageDialog(null, "Inner radius must be smaller than radius!", "Error",
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
				getRootPane().setDefaultButton(btnConfirm);
			}
			{
				btnCancel = new JButton("Cancel");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
				btnCancel.setActionCommand("Cancel");
			}
			GroupLayout gl_pnlSouth = new GroupLayout(pnlSouth);
			gl_pnlSouth.setHorizontalGroup(
					gl_pnlSouth.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlSouth.createSequentialGroup()
							.addGap(73).addComponent(btnConfirm).addGap(161).addComponent(btnCancel).addGap(90)));
			gl_pnlSouth.setVerticalGroup(gl_pnlSouth.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_pnlSouth.createSequentialGroup().addGap(5)
							.addGroup(gl_pnlSouth.createParallelGroup(Alignment.BASELINE).addComponent(btnCancel)
									.addComponent(btnConfirm))));
			pnlSouth.setLayout(gl_pnlSouth);
		}
		
		setVisible(true);
	}

	public void validate(String radius) {
		String supp = "^(([+-])?([1-9]{1})([0-9]+)?)$";
		if (!radius.matches(supp)) {
			throw new NumberFormatException();
		}
	}

	public boolean isConfirmed() {
		return isConfirmed;
	}

	public void setConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtRadius(JTextField txtRadius) {
		this.txtRadius = txtRadius;
	}
	
	public JTextField getTxtInnerRadius() {
		return txtInnerRadius;
	}

	public void setTxtInnerRadius(JTextField txtRadius) {
		this.txtInnerRadius = txtRadius;
	}
}
