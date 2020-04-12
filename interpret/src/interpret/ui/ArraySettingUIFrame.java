package interpret.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;

public class ArraySettingUIFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArraySettingUIFrame frame = new ArraySettingUIFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ArraySettingUIFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel header_panel = new JPanel();
		contentPane.add(header_panel, BorderLayout.NORTH);
		header_panel.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblNewLabel = new JLabel("New label");
		header_panel.add(lblNewLabel);

		JPanel arraySize_panel = new JPanel();
		header_panel.add(arraySize_panel);
		arraySize_panel.setLayout(new GridLayout(1, 0, 0, 0));

		JSpinner inputArraySize = new JSpinner();
		arraySize_panel.add(inputArraySize);

		JButton btnDecideArraySize = new JButton("Decide Size");
		arraySize_panel.add(btnDecideArraySize);

		JPanel content_panel = new JPanel();
		contentPane.add(content_panel, BorderLayout.CENTER);
	}

}
