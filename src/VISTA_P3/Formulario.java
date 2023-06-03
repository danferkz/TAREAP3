package VISTA_P3;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.util.Enumeration;
import java.util.Hashtable;

import MODELO_P3.*;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;



import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;


public class Formulario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private Hashtable<Integer, Mascota> lista = new Hashtable<Integer, Mascota>();
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JCheckBox chckbxComidaSelectiva;
	private JCheckBox chckbxPaseosFamiliares;
	private JCheckBox chckbxHiperactivo;
	private JComboBox<String> cmbTipo;
	private JRadioButton rdbtnGENE_M;
	private JRadioButton rdbtnGene_F;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Formulario frame = new Formulario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	public Formulario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 821, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(204, 11, 370, 160);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int fila = table.getSelectedRow();
				if (fila>=0) {
					int valuex = Integer.parseInt(table.getValueAt(fila, 0).toString());
					Mascota m11 = buscar(valuex);
					txtCodigo.setText(String.valueOf(m11.getCodigo()));
					txtNombre.setText(String.valueOf(m11.getNombre()));
					String type = m11.getTipo();
					cmbTipo.setSelectedItem(type);
					if (m11.getHobbies()[0] == true) {
						chckbxComidaSelectiva.setSelected(true);
					}
					else if(m11.getHobbies()[0]==false) {
						chckbxComidaSelectiva.setSelected(false);
					}
					if (m11.getHobbies()[1] == true) {
						chckbxPaseosFamiliares.setSelected(true);
					}
					else if(m11.getHobbies()[1]==false) {
						chckbxPaseosFamiliares.setSelected(false);
					}
					if (m11.getHobbies()[2] == true) {
						chckbxHiperactivo.setSelected(true);
					}
					else if(m11.getHobbies()[2]==false) {
						chckbxHiperactivo.setSelected(false);
					}
					if (m11.getGenero()[0]==true) {
						rdbtnGENE_M.setSelected(true);
					}
					else if (m11.getGenero()[0]==false) {
						rdbtnGENE_M.setSelected(false);
					}
					
					if (m11.getGenero()[1]==true) {
						rdbtnGene_F.setSelected(true); 
					}
					else if (m11.getGenero()[1]==false) {
						rdbtnGene_F.setSelected(false); 
					}
				}
			}
		});



		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Codigo:");
		lblNewLabel.setBounds(10, 18, 72, 14);
		contentPane.add(lblNewLabel);


		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(10, 55, 72, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Tipo:");
		lblNewLabel_2.setBounds(10, 89, 72, 14);
		contentPane.add(lblNewLabel_2);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(92, 15, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(92, 52, 86, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		cmbTipo = new JComboBox<String>();
		cmbTipo.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Perro", "Gato"}));
		cmbTipo.setBounds(92, 89, 86, 22);
		contentPane.add(cmbTipo);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int codigo = Integer.parseInt(txtCodigo.getText());
				String nombre = txtNombre.getText();
				int tipo = cmbTipo.getSelectedIndex();
				boolean x = chckbxComidaSelectiva.isSelected();
				boolean y = chckbxPaseosFamiliares.isSelected();
				boolean z = chckbxHiperactivo.isSelected();
				boolean male = rdbtnGENE_M.isSelected();
				boolean female = rdbtnGene_F.isSelected();
				
				switch(tipo) {
				case 1:
					lista.put(codigo, new Perro(codigo, nombre, new boolean[] {male, female} ,new boolean[] {x, y, z}));
					limpiar();
					break;
				case 2:
					lista.put(codigo, new Gato(codigo, nombre,new boolean[] {male, female} ,new boolean[] {x, y, z}));
					limpiar();
					break;
				}
				cargar();
			}
		});
		btnAgregar.setBounds(47, 336, 89, 23);
		contentPane.add(btnAgregar);
		
		chckbxComidaSelectiva = new JCheckBox("Comida Selectiva");
		chckbxComidaSelectiva.setBounds(47, 303, 134, 23);
		contentPane.add(chckbxComidaSelectiva);
		
		chckbxPaseosFamiliares = new JCheckBox("Paseos Familiares");
		chckbxPaseosFamiliares.setBounds(44, 251, 134, 23);
		contentPane.add(chckbxPaseosFamiliares);
		
		chckbxHiperactivo = new JCheckBox("Hiperactivo");
		chckbxHiperactivo.setBounds(47, 277, 97, 23);
		contentPane.add(chckbxHiperactivo);


		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCodigo.setText("");
				txtNombre.setText("");
				cmbTipo.setSelectedItem(0);
			}
		});

		btnLimpiar.setBounds(483, 336, 89, 23);
		contentPane.add(btnLimpiar);

		rdbtnGENE_M = new JRadioButton("M");
		rdbtnGENE_M.setBounds(55, 148, 55, 23);
		contentPane.add(rdbtnGENE_M);
		
		rdbtnGene_F = new JRadioButton("F");
		rdbtnGene_F.setBounds(130, 148, 48, 23);
		contentPane.add(rdbtnGene_F);
		
		JLabel lblIndiGene = new JLabel("Genero");
		lblIndiGene.setBounds(10, 127, 46, 14);
		contentPane.add(lblIndiGene);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(330, 336, 89, 23);
		contentPane.add(btnModificar);


		
		
		
		
		JLabel lbl_edad_P = new JLabel("Promedio de Edad:");
		lbl_edad_P.setBounds(204, 281, 111, 14);
		contentPane.add(lbl_edad_P);
		
		JLabel lblgeneroM = new JLabel("Genero Masculino:");
		lblgeneroM.setBounds(204, 240, 89, 14);
		contentPane.add(lblgeneroM);
		
		JLabel lblgenero_F = new JLabel("Genero Femenino:");
		lblgenero_F.setBounds(342, 240, 97, 14);
		contentPane.add(lblgenero_F);
		

		JLabel lblgato = new JLabel("Gatos:");
		lblgato.setBounds(342, 281, 46, 14);
		contentPane.add(lblgato);
		
		JLabel lblperros = new JLabel("Perros:");
		lblperros.setBounds(455, 281, 46, 14);
		contentPane.add(lblperros);
		
		JButton btneliminar = new JButton("Eliminar");
		btneliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btneliminar.setBounds(172, 336, 89, 23);
		contentPane.add(btneliminar);

		
		lista.put(101, new Perro(101, "Firulais", new boolean[] {true, false},new boolean[] {true, true, false}));
		lista.put(102, new Gato(102, "Michelina", new boolean[] {false, true},new boolean[] {false, false, true}));
		lista.put(103, new Perro(103, "Lazzy Lin", new boolean[] {false, true},new boolean[] {false, true, true}));
		lista.put(104, new Gato(104, "Garfield", new boolean[] {true, false}  ,new boolean[] {true, true, false}));
		
		cargar();
	}
	void cargar() {
		DefaultTableModel model = new DefaultTableModel(
			new String[] {"Codigo", "Nombre", "Tipo","Sexo", "A", "B", "C"}, 0){
			private static final long serialVersionUID = 1L;
			Class<?>[] columnTypes = new Class[] {
					Integer.class,
					String.class,
					String.class,
					String.class,
					Boolean.class,
					Boolean.class,
					Boolean.class
				};
				public Class<?> getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};

		Enumeration<Mascota> e = lista.elements();

		while(e.hasMoreElements()) {
			Mascota m = e.nextElement();
			model.addRow(new Object[] {
						m.getCodigo(),
						m.getNombre(),
						m.getTipo(),
						m.revisionSexo(),
						m.getHobbies()[0],
						m.getHobbies()[1],
						m.getHobbies()[2]
					});
		}
		table.setModel(model);
	}
	void limpiar() {
		txtCodigo.setText("");
		txtNombre.setText("");
		cmbTipo.setSelectedIndex(0);
		chckbxComidaSelectiva.setSelected(false);
		chckbxPaseosFamiliares.setSelected(false);
		chckbxHiperactivo.setSelected(false);
		table.clearSelection();
		txtCodigo.requestFocus();
	}
	
	Mascota buscar (int codigo) {
		Enumeration<Mascota> e = lista.elements();
		while (e.hasMoreElements()) {
			Mascota m = e.nextElement();
			if(m.getCodigo()==codigo) {
				return m;
			}
		}
		return null;
	}
}