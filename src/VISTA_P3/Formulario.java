package VISTA_P3;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.BufferedWriter;


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
import java.io.File;

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
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


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
		txtCodigo.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char limitation = e.getKeyChar();
				String pato = txtCodigo.getText();
				if (!(Character.isDigit(limitation))||pato.length()>=3||(pato.length() == 0 && limitation != '1')){
				 e.consume();
				}
			}
		});
		txtCodigo.setBounds(92, 15, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char limitation = e.getKeyChar();
				String pati = txtNombre.getText();
				if (!(Character.isAlphabetic(limitation))||(pati.length()== 0 && Character.isUpperCase(limitation)==false)) {
					e.consume();
				}
			}
		});
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
		rdbtnGENE_M.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				while (rdbtnGene_F.isSelected()==true) {
					rdbtnGene_F.setSelected(false);
				}
			}
		});
		rdbtnGENE_M.setBounds(55, 190, 55, 23);
		contentPane.add(rdbtnGENE_M);
		
		rdbtnGene_F = new JRadioButton("F");
		rdbtnGene_F.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				while  (rdbtnGENE_M.isSelected()==true) {
					rdbtnGENE_M.setSelected(false);
				}
			}
		});
		rdbtnGene_F.setBounds(130, 190, 48, 23);
		contentPane.add(rdbtnGene_F);
		
		JLabel lblIndiGene = new JLabel("Genero");
		lblIndiGene.setBounds(10, 169, 46, 14);
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
		btnModificar.setBounds(294, 336, 89, 23);
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
		txtEdad.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char limitation = e.getKeyChar();
				String pato = txtEdad.getText();
				if (!(Character.isDigit(limitation))||pato.length()>=2) {
					e.consume();
				}
			}
		});
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
		btnPurge.setBounds(419, 336, 89, 23);
		contentPane.add(btnPurge);
		
		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveguarda();
			}
		});
		btnGuardar.setBounds(294, 190, 89, 23);
		contentPane.add(btnGuardar);
		
		JButton btnCargar = new JButton("CARGAR");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lectura();
			}
		});
		btnCargar.setBounds(399, 190, 89, 23);
		contentPane.add(btnCargar);
		
		/*
		 * JButton btnOrden = new JButton("Ordenar M a Me");
		 * btnOrden.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { Mascota aux; for (int i=101; i
		 * <100+lista.size()-1;i++) { for (int j = 101; j<100+lista.size();j++) { if
		 * (lista.get(j+1).getCodigo()<lista.get(j).getCodigo()) { aux = lista.get(j+1);
		 * lista.put(j+1, lista.get(j)); lista.put(j, aux); } } } cargar(); } });
		 * btnOrden.setBounds(518, 336, 120, 23); contentPane.add(btnOrden);
		 */
		
		/*
		 * JButton btnOrdenarMeA = new JButton("Ordenar Me a M");
		 * btnOrdenarMeA.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { Mascota aux; for (int i=101; i
		 * <100+lista.size()-1;i++) { for (int j = 101; j<100+lista.size()-1;j++) { if
		 * (lista.get(j+1).getCodigo()<lista.get(j).getCodigo()) { aux = lista.get(j+1);
		 * lista.put(j+1, lista.get(j)); lista.put(j, aux); } } } cargar(); } });
		 * btnOrdenarMeA.setBounds(648, 336, 120, 23); contentPane.add(btnOrdenarMeA);
		 */
		
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
		int prom = 0;
		if (!(lista.size()==0)) {
			while (e.hasMoreElements()) {
				Mascota m1 = e.nextElement();
				numedad = numedad + m1.getEdad();
			}
			prom = numedad / (lista.size());
		}
		String numGAT = String.valueOf(prom);
		lblPromEdad.setText(numGAT);
		}
	
	void purge() {
		lista.clear();
		}
	
	
	void saveguarda() {
	    JFileChooser f1 = new JFileChooser(".") {
	        private static final long serialVersionUID = 1L;

	        public void approveSelection() {
	            File f = getSelectedFile();
	            if (f.exists() && getDialogType() == SAVE_DIALOG) {
	                int result = JOptionPane.showConfirmDialog(this, "El archivo existe, desea sobreescribir?", "Verificar archivo", JOptionPane.YES_NO_CANCEL_OPTION);
	                switch (result) {
	                    case JOptionPane.YES_OPTION:
	                        super.approveSelection();
	                        return;
	                    case JOptionPane.NO_OPTION:
	                        return;
	                    case JOptionPane.CLOSED_OPTION:
	                        return;
	                    case JOptionPane.CANCEL_OPTION:
	                        cancelSelection();
	                        return;
	                }
	            }
	            super.approveSelection();
	        }
	    };
		
	    FileFilter filtrox = new FileNameExtensionFilter("Archivos de texto (.txt, .csv, .doc)", "txt", "csv", "doc");
	    f1.setFileFilter(filtrox);
	    f1.setDialogTitle("Especifique archivo a guardar.");
	    int selected = f1.showSaveDialog(this);

	    try {
	        if (selected == JFileChooser.APPROVE_OPTION) {
	            File archsaved = f1.getSelectedFile();
	            String csvFilePath = archsaved.getAbsolutePath() + ".csv";
	            FileWriter alone = new FileWriter(csvFilePath);
	            BufferedWriter diff = new BufferedWriter(alone);
	            Enumeration<Mascota> e = lista.elements();
	            while (e.hasMoreElements()) {
	                Mascota ot = e.nextElement();
	                String line = ot.getCodigo() + "," + ot.getNombre() + "," + ot.getTipo() + "," + ot.revisionSexo() + "," + ot.getEdad() + "," + ot.listadoHobbies();
	                diff.write(line);
	                diff.newLine();
	            }
	            diff.close();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	void lectura() {
		JFileChooser fileChooser = new JFileChooser(".");      
        FileFilter filtro = new FileNameExtensionFilter("Archivos CSV (.csv)", "csv"); 
        fileChooser.setFileFilter(filtro);
        int valor = fileChooser.showOpenDialog(fileChooser);
        if (valor == JFileChooser.APPROVE_OPTION) {
            String nombreArchivo = fileChooser.getSelectedFile().getAbsolutePath();
    		Path ruta = Paths.get(nombreArchivo);
    		try (BufferedReader br = Files.newBufferedReader(ruta, StandardCharsets.US_ASCII)) {
    			String linea = br.readLine();
    			String mensaje = "";
    			while (linea != null) {
    				String[] campos = linea.split(",");
    				for(int i = 0;i<campos.length; i++) {
    					mensaje += campos[i];
    				}
    				linea = br.readLine();
    			}
    			
    			JOptionPane.showConfirmDialog(null, mensaje);
    			
    		} catch (IOException ioe) {
    			ioe.printStackTrace();
    		}
        } else {
        	JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún fichero");
        }	
	}	
}