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
import javax.swing.JOptionPane;
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
	private DefaultTableModel tableModel;
	private JCheckBox chckbxHiperactivo;
	private JComboBox<String> cmbTipo;
	private JRadioButton rdbtnGENE_M;
	private JRadioButton rdbtnGene_F;
	private JTextField txtEdad;
	private JLabel lblGM;
	private JLabel lblGF;
	private JLabel lblPromEdad;
	private JLabel lblNumGat;
	private JLabel lblNumDog;
	private int codigoActual;
	
	
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
					codigoActual = Integer.parseInt(txtCodigo.getText());
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
					txtEdad.setText(String.valueOf(m11.getEdad()));
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
		lblNewLabel_2.setBounds(10, 131, 72, 14);
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
		cmbTipo.setBounds(92, 131, 86, 22);
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
		        int edad = Integer.parseInt(txtEdad.getText());
		        
		        switch(tipo) {
		            case 1:
		                lista.put(codigo, new Perro(codigo, nombre, new boolean[] {male, female}, new boolean[] {x, y, z}, edad));
		                clear();
		                break;
		            case 2:
		                lista.put(codigo, new Gato(codigo, nombre, new boolean[] {male, female}, new boolean[] {x, y, z}, edad));
		                clear();
		                break;
		        }
		        cargar();
		        numeroM();
		        numeroF();
		        numeroGatos();
		        numeroPerros();
		        promEdades();
		    }
		});
		btnAgregar.setBounds(47, 336, 89, 23);
		contentPane.add(btnAgregar);
		
		chckbxComidaSelectiva = new JCheckBox("Comida Selectiva (A)");
		chckbxComidaSelectiva.setBounds(47, 231, 178, 23);
		contentPane.add(chckbxComidaSelectiva);
		
		chckbxPaseosFamiliares = new JCheckBox("Paseos Familiares (B)");
		chckbxPaseosFamiliares.setBounds(47, 259, 168, 23);
		contentPane.add(chckbxPaseosFamiliares);
		
		chckbxHiperactivo = new JCheckBox("Hiperactivo (C)");
		chckbxHiperactivo.setBounds(47, 285, 154, 23);
		contentPane.add(chckbxHiperactivo);

		rdbtnGENE_M = new JRadioButton("M");
		rdbtnGENE_M.setBounds(55, 190, 55, 23);
		contentPane.add(rdbtnGENE_M);
		
		rdbtnGene_F = new JRadioButton("F");
		rdbtnGene_F.setBounds(130, 190, 48, 23);
		contentPane.add(rdbtnGene_F);
		
<<<<<<< HEAD
		JLabel lblIndiGene = new JLabel("Género");
		lblIndiGene.setBounds(10, 152, 46, 14);
=======
		JLabel lblIndiGene = new JLabel("Genero");
		lblIndiGene.setBounds(10, 169, 46, 14);
>>>>>>> bd68bbfa47cccf02557560efdd1944de754e2955
		contentPane.add(lblIndiGene);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int codigo = Integer.parseInt(txtCodigo.getText());
		        String nombre = txtNombre.getText();
		        int tipo = cmbTipo.getSelectedIndex();
		        boolean x = chckbxComidaSelectiva.isSelected();
		        boolean y = chckbxPaseosFamiliares.isSelected();
		        boolean z = chckbxHiperactivo.isSelected();
		        boolean male = rdbtnGENE_M.isSelected();
		        boolean female = rdbtnGene_F.isSelected();
		        int edad = Integer.parseInt(txtEdad.getText());
		        
		        if (codigo != codigoActual) {
		            JOptionPane.showMessageDialog(null, "No se puede modificar el código.", "Advertencia", JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		        switch (tipo) {
		            case 1:
		                lista.put(codigo, new Perro(codigo, nombre, new boolean[]{male, female}, new boolean[]{x, y, z}, edad));
		                break;
		            case 2:
		                lista.put(codigo, new Gato(codigo, nombre, new boolean[]{male, female}, new boolean[]{x, y, z}, edad));
		                break;
		        }
		        clear();
		        cargar();
		        numeroM();
		        numeroF();
		        numeroGatos();
		        numeroPerros();
		        promEdades();
		    }
		});
		btnModificar.setBounds(330, 336, 89, 23);
		contentPane.add(btnModificar);
		
		JLabel lbl_edad_P = new JLabel("Promedio de Edad:");
		lbl_edad_P.setBounds(584, 89, 112, 14);
		contentPane.add(lbl_edad_P);
		
		JLabel lblgeneroM = new JLabel("Genero Masculino:");
		lblgeneroM.setBounds(584, 55, 112, 14);
		contentPane.add(lblgeneroM);
		
		JLabel lblgenero_F = new JLabel("Genero Femenino:");
		lblgenero_F.setBounds(584, 18, 103, 14);
		contentPane.add(lblgenero_F);

		JLabel lblgato = new JLabel("Gatos:");
		lblgato.setBounds(584, 127, 48, 14);
		contentPane.add(lblgato);
		
		JLabel lblperros = new JLabel("Perros:");
		lblperros.setBounds(584, 157, 55, 14);
		contentPane.add(lblperros);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (!txtCodigo.getText().isEmpty()) {
		            int codigo = Integer.parseInt(txtCodigo.getText());
		            Mascota mascota = buscar(codigo);
		            if (mascota != null) {
		                int confirmar = JOptionPane.showConfirmDialog(null, "¿Está seguro?", "Eliminar", JOptionPane.YES_NO_OPTION);
		                if (confirmar == JOptionPane.YES_OPTION) {
		                    lista.remove(codigo);
		                    clear();
		                    cargar();
		                    numeroM();
		                    numeroF();
		                    numeroGatos();
		                    numeroPerros();
		                    promEdades();
		                    JOptionPane.showMessageDialog(null, mascota.getNombre() + " ha sido eliminado");
		                }
		            } else {
		                JOptionPane.showMessageDialog(null, "El código " + codigo + " no existe");
		            }
		        } else {
		            txtCodigo.requestFocus();
		        }
		    }
		});
		btnEliminar.setBounds(172, 336, 89, 23);
		contentPane.add(btnEliminar);
		
		txtEdad = new JTextField();
		txtEdad.setBounds(92, 86, 86, 20);
		contentPane.add(txtEdad);
		txtEdad.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Edad:");
		lblNewLabel_3.setBounds(10, 89, 86, 14);
		contentPane.add(lblNewLabel_3);
		
		lblGF = new JLabel("0");
		lblGF.setBounds(693, 18, 46, 14);
		contentPane.add(lblGF);
		
		lblGM = new JLabel("0");
		lblGM.setBounds(693, 55, 46, 14);
		contentPane.add(lblGM);
		
		lblPromEdad = new JLabel("0");
		lblPromEdad.setBounds(693, 89, 46, 14);
		contentPane.add(lblPromEdad);
		
		lblNumGat = new JLabel("0");
		lblNumGat.setBounds(624, 127, 46, 14);
		contentPane.add(lblNumGat);
		
		lblNumDog = new JLabel("0");
		lblNumDog.setBounds(634, 157, 46, 14);
		contentPane.add(lblNumDog);
		
		JButton btnPurge = new JButton("Limpiar");
		btnPurge.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        purge();
		        cargar();
		        numeroM();
		        numeroF();
		        numeroGatos();
		        numeroPerros();
		        promEdades();
		    }
		});
		btnPurge.setBounds(499, 336, 89, 23);
		contentPane.add(btnPurge);
		
		lista.put(101, new Perro(101, "Firulais", new boolean[] {true, false}, new boolean[] {true, true, false}, 5));
		lista.put(102, new Gato(102, "Michelina", new boolean[] {false, true},new boolean[] {false, false, true}, 9));
		lista.put(103, new Perro(103, "Lazzy Lin", new boolean[] {false, true},new boolean[] {false, true, true}, 3));
		lista.put(104, new Gato(104, "Garfield", new boolean[] {true, false}  ,new boolean[] {true, true, false}, 7));
		
		cargar();
		numeroM();
		numeroF();
		numeroGatos();
		numeroPerros();
		promEdades();
	}
	void cargar() {
	    DefaultTableModel model = new DefaultTableModel(
	        new String[] {"Codigo", "Nombre", "Tipo", "Sexo", "Edad", "A", "B", "C"}, 0) {
	        private static final long serialVersionUID = 1L;
	        Class<?>[] columnTypes = new Class[] {
	            Integer.class,
	            String.class,
	            String.class,
	            String.class,
	            Integer.class,
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
	        if (m instanceof Perro) {
	            Perro perro = (Perro) m;
	            model.addRow(new Object[] {
	                perro.getCodigo(),
	                perro.getNombre(),
	                perro.getTipo(),
	                perro.revisionSexo(),
	                perro.getEdad(),
	                perro.getHobbies()[0],
	                perro.getHobbies()[1],
	                perro.getHobbies()[2]
	            });
	        } else if (m instanceof Gato) {
	            Gato gato = (Gato) m;
	            model.addRow(new Object[] {
	                gato.getCodigo(),
	                gato.getNombre(),
	                gato.getTipo(),
	                gato.revisionSexo(),
	                gato.getEdad(),
	                gato.getHobbies()[0],
	                gato.getHobbies()[1],
	                gato.getHobbies()[2]
	            });
	        }
	    }
	    table.setModel(model);
	}
	void clear() {
		txtCodigo.setText("");
		txtNombre.setText("");
		cmbTipo.setSelectedIndex(0);
		chckbxComidaSelectiva.setSelected(false);
		chckbxPaseosFamiliares.setSelected(false);
		chckbxHiperactivo.setSelected(false);
		table.clearSelection();
		txtCodigo.requestFocus();
		cmbTipo.setSelectedIndex(0);
	    txtCodigo.setText("");
	    txtNombre.setText("");
	    cmbTipo.setSelectedIndex(0);
	    chckbxComidaSelectiva.setSelected(false);
	    chckbxPaseosFamiliares.setSelected(false);
	    chckbxHiperactivo.setSelected(false);
	    rdbtnGENE_M.setSelected(false);
	    rdbtnGene_F.setSelected(false);
	    table.clearSelection();
	    txtCodigo.requestFocus();
	    txtEdad.setText("");
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
	
	void numeroM() {
		Enumeration<Mascota> e = lista.elements();
		int nummacho = 0;
		while (e.hasMoreElements()) {
			Mascota m1 = e.nextElement();
			if (m1.getGenero()[0]==true) {
				nummacho = nummacho +1;
			}
		}
		String numM = String.valueOf(nummacho);
		lblGM.setText(numM);
		}
	
	void numeroF() {
		Enumeration<Mascota> e = lista.elements();
		int numf = 0;
		while (e.hasMoreElements()) {
			Mascota m1 = e.nextElement();
			if (m1.getGenero()[1]==true) {
				numf = numf +1;
			}
		}
		String numF = String.valueOf(numf);
		lblGF.setText(numF);
		}
	
	void numeroGatos() {
		Enumeration<Mascota> e = lista.elements();
		int numcat = 0;
		while (e.hasMoreElements()) {
			Mascota m1 = e.nextElement();
			if (m1.getTipo().equals("Gato")) {
				numcat = numcat +1;
			}
		}
		String numGAT = String.valueOf(numcat);
		lblNumGat.setText(numGAT);
		}
	
	void numeroPerros() {
		Enumeration<Mascota> e = lista.elements();
		int numcat = 0;
		while (e.hasMoreElements()) {
			Mascota m1 = e.nextElement();
			if (m1.getTipo().equals("Perro")) {
				numcat = numcat +1;
			}
		}
		String numGAT = String.valueOf(numcat);
		lblNumDog.setText(numGAT);
		}
	
	void promEdades() {
		Enumeration<Mascota> e = lista.elements();
		int numedad = 0;
		while (e.hasMoreElements()) {
			Mascota m1 = e.nextElement();
			numedad = numedad + m1.getEdad();
		}
		int prom = numedad / (lista.size());
		String numGAT = String.valueOf(prom);
		lblPromEdad.setText(numGAT);
		}
	
	void purge() {
		lista.clear();
		}
	}
	
	
