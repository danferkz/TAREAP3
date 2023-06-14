package NUEVO;



//Nombres:
//André Portella
//Vania Vasquez
//Diego Cayetano
//Alvaro Chavez
//Daniel Garcia

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Hashtable;

import java.text.DecimalFormat;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import MODELO_P3.Estudiante;
import MODELO_P3.Laboratorio;
import MODELO_P3.Teorico;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ventana extends JFrame {

	private JPanel contentPane;
	private JTextField textDNI;
	private JTable table;
	private JTextField textName;
	private JTextField textCurso;
	private JRadioButton rdbtnMasculino;
	private JRadioButton rdbtnFemenino;
	private JComboBox comboBox;
	private JLabel lblT1content;
	private JLabel lblT2content;
	private JLabel lblEPcontent;
	private JLabel lblEFcontent;
	private JLabel lblNFcontent;
	private Hashtable<Integer, Estudiante> lista = new Hashtable<Integer, Estudiante>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
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
	public Ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 803, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDNI = new JLabel("DNI:");
		lblDNI.setBounds(21, 31, 46, 14);
		contentPane.add(lblDNI);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(21, 68, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblGenero = new JLabel("Genero:");
		lblGenero.setBounds(21, 93, 46, 14);
		contentPane.add(lblGenero);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(21, 134, 46, 14);
		contentPane.add(lblTipo);
		
		JLabel lblCurso = new JLabel("Curso:");
		lblCurso.setBounds(21, 179, 46, 14);
		contentPane.add(lblCurso);
		
		JButton btnGenerar = new JButton("Generar");
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generarNumerosAleatorios();
				calcularPromedio();
			}
		});
		btnGenerar.setBounds(21, 353, 89, 23);
		contentPane.add(btnGenerar);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dni = Integer.parseInt(textDNI.getSelectedText());
		        String nombre = textName.getText();
		        String genero = "";
		        String oh = textCurso.getText();
		        
		        if (rdbtnMasculino.isSelected()==true) {
		        	genero = "Masculino";
		        }
		        else if (rdbtnFemenino.isSelected()==true) {
		        	genero = "Femenino";
		        }
		     
		        double t1 = Double.parseDouble(lblT1content.getText());
		        double t2 = Double.parseDouble(lblT2content.getText());
		        double ep = Double.parseDouble(lblEPcontent.getText());
		        double ef = Double.parseDouble(lblEFcontent.getText());
		        double nf = Double.parseDouble(lblNFcontent.getText());

		        int tipo = comboBox.getSelectedIndex();
		        
		        switch(tipo) {
		            case 1:
		                lista.put(dni,new Teorico(dni,nombre,genero,oh, t1,t2,ep,ef,nf));
		                break;
		            case 2:
		                lista.put(dni,new Laboratorio(dni,nombre,genero,oh, t1,t2,ep,ef,nf));
		                break;
		        }
		        
		        cargar();
			}
		});
		btnAgregar.setBounds(171, 305, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		btnLimpiar.setBounds(139, 353, 89, 23);
		contentPane.add(btnLimpiar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModificar.setBounds(270, 305, 89, 23);
		contentPane.add(btnModificar);
		
		JButton btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				leer();
			}
		});
		btnCargar.setBounds(238, 353, 89, 23);
		contentPane.add(btnCargar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(369, 305, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		btnGuardar.setBounds(337, 353, 89, 23);
		contentPane.add(btnGuardar);
		
		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnListar.setBounds(468, 305, 89, 23);
		contentPane.add(btnListar);
		
		JButton btnOrdenar = new JButton("Ordenar");
		btnOrdenar.setBounds(436, 353, 89, 23);
		contentPane.add(btnOrdenar);
		
		JButton btnAprobados = new JButton("Aprobados");
		btnAprobados.setBounds(567, 305, 115, 23);
		contentPane.add(btnAprobados);
		
		JButton btnDesaprobados = new JButton("Desaprobados");
		btnDesaprobados.setBounds(535, 353, 115, 23);
		contentPane.add(btnDesaprobados);
		
		JButton btnMasculino = new JButton("Masculino");
		btnMasculino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Masculino();
			}
		});
		btnMasculino.setBounds(688, 305, 89, 23);
		contentPane.add(btnMasculino);
		
		JButton btnFemenino = new JButton("Femenino");
		btnFemenino.setBounds(660, 353, 89, 23);
		contentPane.add(btnFemenino);
		
		textDNI = new JTextField();
		textDNI.setBounds(77, 28, 86, 20);
		contentPane.add(textDNI);
		textDNI.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(200, 30, 577, 235);
		contentPane.add(scrollPane);
		
		table = new JTable();table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int fila = table.getSelectedRow();
				if (fila>=0) {
					int valuex = Integer.parseInt(table.getValueAt(fila, 0).toString());
					Estudiante m11 = buscar(valuex);
					textName.setText(m11.getName());
					textCurso.setText(m11.getCurso());
					textDNI.setText(String.valueOf(m11.getDNI()));
					if (m11.getGenero().equals("Masculino")) {
						rdbtnMasculino.setSelected(true);
						rdbtnFemenino.setSelected(false);
					}
					else if (m11.getGenero().equals("Femenino")) {
						rdbtnFemenino.setSelected(true);
						rdbtnMasculino.setSelected(false);
					}
					String type = m11.getTipo();
					comboBox.setSelectedItem(type);
					lblT1content.setText(String.valueOf(m11.getT1()));
					lblT2content.setText(String.valueOf(m11.getT2()));
					lblEPcontent.setText(String.valueOf(m11.getEp()));
					lblEFcontent.setText(String.valueOf(m11.getEf()));
					lblNFcontent.setText(String.valueOf(m11.getNf()));
				}
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblListadeEstudiantes = new JLabel("Lista de Estudiantes");
		lblListadeEstudiantes.setBounds(444, 11, 161, 14);
		contentPane.add(lblListadeEstudiantes);
		
		JLabel lblPromediodeCalificaciones = new JLabel("Promedio de Calificaciones:");
		lblPromediodeCalificaciones.setBounds(238, 276, 152, 14);
		contentPane.add(lblPromediodeCalificaciones);
		
		textName = new JTextField();
		textName.setBounds(77, 65, 86, 20);
		contentPane.add(textName);
		textName.setColumns(10);
		
		textCurso = new JTextField();
		textCurso.setBounds(77, 176, 86, 20);
		contentPane.add(textCurso);
		textCurso.setColumns(10);
		
		rdbtnMasculino = new JRadioButton("M");
		rdbtnMasculino.setBounds(73, 89, 46, 23);
		contentPane.add(rdbtnMasculino);
		
		rdbtnFemenino = new JRadioButton("F");
		rdbtnFemenino.setBounds(119, 89, 46, 23);
		contentPane.add(rdbtnFemenino);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Teorico", "Laboratorio"}));
		comboBox.setBounds(77, 130, 86, 22);
		contentPane.add(comboBox);
		
		JLabel lblT1 = new JLabel("T1:");
		lblT1.setBounds(37, 220, 46, 14);
		contentPane.add(lblT1);
		
		JLabel lblT2 = new JLabel("T2:");
		lblT2.setBounds(37, 245, 46, 14);
		contentPane.add(lblT2);
		
		JLabel lblEP = new JLabel("EP:");
		lblEP.setBounds(37, 270, 46, 14);
		contentPane.add(lblEP);
		
		JLabel lblEF = new JLabel("EF:");
		lblEF.setBounds(37, 295, 46, 14);
		contentPane.add(lblEF);
		
		JLabel lblNF = new JLabel("NF:");
		lblNF.setBounds(37, 326, 46, 14);
		contentPane.add(lblNF);
		
		lblT1content = new JLabel("0");
		lblT1content.setBounds(77, 220, 46, 14);
		contentPane.add(lblT1content);
		
		lblT2content = new JLabel("0");
		lblT2content.setBounds(77, 245, 46, 14);
		contentPane.add(lblT2content);
		
		lblEPcontent = new JLabel("0");
		lblEPcontent.setBounds(77, 270, 46, 14);
		contentPane.add(lblEPcontent);
		
		lblEFcontent = new JLabel("0");
		lblEFcontent.setBounds(77, 295, 46, 14);
		contentPane.add(lblEFcontent);
		
		lblNFcontent = new JLabel("0");
		lblNFcontent.setBounds(77, 328, 46, 14);
		contentPane.add(lblNFcontent);
		
		lista.put(7010390, new Laboratorio(7010390, "Firulais", "Masculino","Algoritmos",17.2,17,17,17,17));
		lista.put(7010391, new Laboratorio(7010391, "Firulais", "Femenino","Algoritmos",17.2,17,17,17,17));
		cargar();
		
	}
	void guardar() {
		JFileChooser fileChooser = new JFileChooser("."){
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void approveSelection(){
                File f = getSelectedFile();
                if(f.exists() && getDialogType() == SAVE_DIALOG){
                    int result = JOptionPane.showConfirmDialog(this,"El archivo existe, desea sobreescribir?","Verificar archivo",JOptionPane.YES_NO_CANCEL_OPTION);
                    switch(result){
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
		
		FileFilter filtro = new FileNameExtensionFilter("Archivos CSV (.csv)", "csv");
		fileChooser.setFileFilter(filtro);
		
		fileChooser.setDialogTitle("Especifique el archivo a guardar");
		int userSelection = fileChooser.showSaveDialog(this);
		
		try {
			if(userSelection == JFileChooser.APPROVE_OPTION) {
				File archivoGuardado = fileChooser.getSelectedFile();
				FileWriter archivo = new FileWriter(archivoGuardado.getAbsolutePath());
				BufferedWriter buffer = new BufferedWriter(archivo);
				buffer.append("Hola, Mundo,");
				buffer.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void cargar() {
		DefaultTableModel model = new DefaultTableModel(
			new String[] {"DNI", "Nombre", "Genero", "Curso","Tipo", "T1", "T2", "EP", "EF","NF"}, 0){
			private static final long serialVersionUID = 1L;
			Class<?>[] columnTypes = new Class[] {
					 Integer.class,
			           String.class,
			           String.class,
			           String.class,
			           String.class,
			           Double.class,
			           Double.class,
			           Double.class,
			           Double.class,
			           Double.class,
				};
				public Class<?> getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};

		Enumeration<Estudiante> e = lista.elements();

		while(e.hasMoreElements()) {
			Estudiante m = e.nextElement();
			model.addRow(new Object[] {
					m.getDNI(),
	        		m.getName(),
	        		m.getGenero(),
	        		m.getCurso(),
	        		m.getTipo(),
	        		m.getT1(),
	        		m.getT2(),
	        		m.getEp(),
	        		m.getEf(),
	        		m.getNf()
					});
		}
		table.setModel(model);
	}
	
	Estudiante buscar (int codigo) {
		Enumeration<Estudiante> e = lista.elements();
		while (e.hasMoreElements()) {
			Estudiante m = e.nextElement();
			if(m.getDNI()==codigo) {
				return m;
			}
		}
		return null;
	}
	void leer() {
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
    				
    				for(int i = 0; i < campos.length; i++) {
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

	void limpiar() {
		DefaultTableModel model = new DefaultTableModel(
		        new String[] {"DNI", "Nombre", "Genero", "Curso","Tipo", "T1", "T2", "EP", "EF","NF"}, 0) {
		        private static final long serialVersionUID = 1L;
		        Class<?>[] columnTypes = new Class[] {
		           Integer.class,
		           String.class,
		           String.class,
		           String.class,
		           String.class,
		           Double.class,
		           Double.class,
		           Double.class,
		           Double.class,
		           Double.class,
		        };
		        
		        public Class<?> getColumnClass(int columnIndex) {
		            return columnTypes[columnIndex];
		        }
		    };
		    model.addRow(new Object[] {
	        		"",
	        		"",
	        		"",
	        		"",
	        		"",
	        		"",
	        		"",
	        		"",
	        		"",
	        		"",
	        
	        
	        });
	}
	
	
	void Masculino() {
		
		DefaultTableModel model = new DefaultTableModel(
		        new String[] {"DNI", "Nombre", "Genero", "Curso","Tipo", "T1", "T2", "EP", "EF","NF"}, 0) {
		        private static final long serialVersionUID = 1L;
		        Class<?>[] columnTypes = new Class[] {
		           Integer.class,
		           String.class,
		           String.class,
		           String.class,
		           String.class,
		           Double.class,
		           Double.class,
		           Double.class,
		           Double.class,
		           Double.class,
		        };
		        
		        public Class<?> getColumnClass(int columnIndex) {
		            return columnTypes[columnIndex];
		        }
		    };
		
		Enumeration<Estudiante> e = lista.elements();

	    while(e.hasMoreElements()) {
	        Estudiante m = e.nextElement();
	        if (m.getGenero().equals("Masculino")) {
	        model.addRow(new Object[] {
	        		m.getDNI(),
	        		m.getName(),
	        		m.getGenero(),
	        		m.getCurso(),
	        		m.getTipo(),
	        		m.getT1(),
	        		m.getT2(),
	        		m.getEp(),
	        		m.getEf(),
	        		m.getNf()
	        
	        
	        });
	}
	
	
}
	}
	
	 private void generarNumerosAleatorios() {
	        // Generar números aleatorios en el rango de 0 a 20
	        int numeroT1 = (int) (Math.random() * 20);
	        int numeroT2 = (int) (Math.random() * 20);
	        int numeroEv1 = (int) (Math.random() * 20);
	        int numeroEF = (int) (Math.random() * 20);

	        // Mostrar los números generados en los JTextField
	        lblT1content.setText(Integer.toString(numeroT1));
	        lblT2content.setText(Integer.toString(numeroT2));
	        lblEPcontent.setText(Integer.toString(numeroEv1));
	        lblEFcontent.setText(Integer.toString(numeroEF));
	    }

	    private void calcularPromedio() {
	        // Obtener los valores de los JTextField
	        int t1 = Integer.parseInt(lblT1content.getText());
	        int t2 = Integer.parseInt(lblT2content.getText());
	        int ev1 = Integer.parseInt(lblEPcontent.getText());
	        int ef = Integer.parseInt(lblEFcontent.getText());

	        // Calcular el promedio
	        double promedio = 0.6 * ((t1 + t2) / 2.0) + 0.4 * ((ev1 + ef) / 2.0);

	        // Mostrar el promedio en el JLabel con dos decimales
	        DecimalFormat decimalFormat = new DecimalFormat("0.00");
	        String prom = String.valueOf(decimalFormat.format(promedio));
	        lblNFcontent.setText(prom);
	    }

}