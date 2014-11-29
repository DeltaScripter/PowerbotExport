package oldschooldeltaminer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;



public class DeltaMiner extends JFrame {
	public DeltaMiner() {
		initComponents();
	}

	private void strBtnActionPerformed(ActionEvent e) {
		String chosen = rockTypeCombo.getSelectedItem().toString();
		
		if(chosen.contains("Tin")){
			System.out.println("Not supported");
		}else if(chosen.contains("Copper")){
			
			DeltaMinerBody.rockType = DeltaMinerData.copperRocks;
			
		}else if(chosen.contains("Iron")){
			System.out.println("Not supported");
		}
		
		DeltaMinerBody.start = true;
		this.dispose();
	}

	private void initComponents() {
		rockTypeCombo = new JComboBox<String>();
		label = new JLabel();
		label1 = new JLabel();
		LocationCombo = new JComboBox<String>();
		strBtn = new JButton();

		//======== this ========
		setTitle("Delta Miner");
		Container contentPane = getContentPane();

		//---- rockTypeCombo ----
		rockTypeCombo.setModel(new DefaultComboBoxModel<String>(new String[] {
			"Tin",
			"Copper",
			"Iron"
		}));

		//---- label ----
		label.setText("Type of rock to mine");

		//---- label1 ----
		label1.setText("Location");

		//---- LocationCombo ----
		LocationCombo.setModel(new DefaultComboBoxModel<String>(new String[] {
			"Lumbridge"
		}));

		//---- strBtn ----
		strBtn.setText("START");
		strBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				strBtnActionPerformed(e);
			}
		});

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(contentPaneLayout.createParallelGroup()
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGroup(contentPaneLayout.createParallelGroup()
								.addComponent(label)
								.addComponent(label1))
							.addGap(18, 18, 18)
							.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(rockTypeCombo, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
								.addComponent(LocationCombo, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
							.addGap(0, 1, Short.MAX_VALUE))
						.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
							.addGap(0, 219, Short.MAX_VALUE)
							.addComponent(strBtn, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addGap(29, 29, 29)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label)
						.addComponent(rockTypeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label1)
						.addComponent(LocationCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
					.addComponent(strBtn)
					.addContainerGap())
		);
		pack();
		setLocationRelativeTo(getOwner());
	}

	private JComboBox<String> rockTypeCombo;
	private JLabel label;
	private JLabel label1;
	private JComboBox<String> LocationCombo;
	private JButton strBtn;
}
