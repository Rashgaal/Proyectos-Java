package formularioSwing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class FormularioSwing {
	// funcionalidad modo oscuro/claro y borrar todo
	public static void main(String[] args) {
		// ventana general
		JFrame ventanaPrincipal = new JFrame("FORMULARIO DE VIAJE");
		ventanaPrincipal.getContentPane().setLayout(new BorderLayout());
		ventanaPrincipal.setSize(900, 700);
		ventanaPrincipal.setLocationRelativeTo(null);

		// panel superior titulo
		JPanel panelTitulo = new JPanel();
		ventanaPrincipal.getContentPane().add(panelTitulo, BorderLayout.NORTH);

		JLabel titulo = new JLabel("Formulario de viaje");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelTitulo.add(titulo);

		// Panel para el formulario
		JPanel panelFormulario = new JPanel(new GridLayout(11, 1));
		ventanaPrincipal.getContentPane().add(panelFormulario, BorderLayout.CENTER);

		// panel para nombre -- no puede estar vacio
		JPanel panelNombre = new JPanel();
		panelFormulario.add(panelNombre);

		JLabel nombreEtiqueta = new JLabel("*NOMBRE:");
		nombreEtiqueta.setToolTipText("Introduzca su nombre");
		panelNombre.add(nombreEtiqueta);

		JTextArea nombre = new JTextArea(1, 30);
		nombre.setToolTipText("Introduzca su nombre");
		panelNombre.add(nombre);

		// panel para apellidos
		JPanel panelApellidos = new JPanel();
		panelFormulario.add(panelApellidos);

		JLabel apellidosEtiqueta = new JLabel("*APELLIDOS:");
		apellidosEtiqueta.setToolTipText("Introduzca sus apellidos Ejemplo: Gómez Sanchez");
		panelApellidos.add(apellidosEtiqueta);

		JTextArea apellidos = new JTextArea(1, 30);
		apellidos.setToolTipText("Introduzca sus apellidos Ejemplo: Gómez Sanchez");
		panelApellidos.add(apellidos);

		// panel para genero

		JPanel panelGenero = new JPanel();
		panelFormulario.add(panelGenero);

		JLabel generoEtiqueta = new JLabel("*GÉNERO:");
		panelGenero.add(generoEtiqueta);

		JRadioButton masculino = new JRadioButton("Masculino");
		JRadioButton femenino = new JRadioButton("Femenino");
		JRadioButton noRespuesta = new JRadioButton("No contestar");

		ButtonGroup grupoGenero = new ButtonGroup();
		grupoGenero.add(masculino);
		grupoGenero.add(femenino);
		grupoGenero.add(noRespuesta);

		ActionListener listenerGenero = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JRadioButton botonGenero = (JRadioButton) e.getSource();
			}
		};
		masculino.addActionListener(listenerGenero);
		femenino.addActionListener(listenerGenero);
		noRespuesta.addActionListener(listenerGenero);

		panelGenero.add(masculino);
		panelGenero.add(femenino);
		panelGenero.add(noRespuesta);

		// panel para usuario --- no puede estar vacio

		JPanel panelUsuario = new JPanel();
		panelFormulario.add(panelUsuario);

		JLabel usuarioEtiqueta = new JLabel("*USUARIO");
		usuarioEtiqueta.setToolTipText("Introduzca su nombre de usuario");
		panelUsuario.add(usuarioEtiqueta);

		JTextArea usuario = new JTextArea(1, 30);
		usuario.setToolTipText("Introduzca su nombre de usuario");
		panelUsuario.add(usuario);

		// panel para contraseña --- JPasswordField

		JPanel panelContraseña = new JPanel();
		panelFormulario.add(panelContraseña);

		JLabel contraseñaEtiqueta = new JLabel("*CONTRASEÑA");
		contraseñaEtiqueta.setToolTipText("Introduzca su contraseña");
		panelContraseña.add(contraseñaEtiqueta);

		JPasswordField contraseña = new JPasswordField(20);
		contraseña.setToolTipText("Introduzca su contraseña");
		panelContraseña.add(contraseña);

		// panel para telefono --- que admita solo numeros

		JPanel panelTelefono = new JPanel();
		panelTelefono.setToolTipText("");
		panelFormulario.add(panelTelefono);

		JLabel telefonoEtiqueta = new JLabel("*TELÉFONO");
		telefonoEtiqueta.setToolTipText("Introduce tu número de teléfono");
		panelTelefono.add(telefonoEtiqueta);

		JTextArea telefono = new JTextArea(1, 9);
		telefono.setToolTipText("Introduce tu número de teléfono");
		panelTelefono.add(telefono);
		// esto de aqui abajo es para controlar que no admita letras solo numeros, aun
		// asi despues compruebo con ER que sean 9 digitos
		((AbstractDocument) telefono.getDocument()).setDocumentFilter(new DocumentFilter() {
			@Override
			public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
					throws BadLocationException {
				StringBuilder builder = new StringBuilder(string);
				for (int i = builder.length() - 1; i >= 0; i--) {
					char ch = builder.charAt(i);
					if (!Character.isDigit(ch)) {
						builder.deleteCharAt(i);
					}
				}
				string = builder.toString();
				super.insertString(fb, offset, string, attr);
			}

			@Override
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
					throws BadLocationException {
				if (text != null) {
					StringBuilder builder = new StringBuilder(text);
					for (int i = builder.length() - 1; i >= 0; i--) {
						char ch = builder.charAt(i);
						if (!Character.isDigit(ch)) {
							builder.deleteCharAt(i);
						}
					}
					text = builder.toString();
				}
				super.replace(fb, offset, length, text, attrs);
			}
		});

		// panel para desplegable de paises -- que por defecto aparezca "Elija su
		// destino"

		JPanel panelPaises = new JPanel();
		panelFormulario.add(panelPaises);

		JLabel paisesEtiqueta = new JLabel("*ELIGE UN PAÍS");
		paisesEtiqueta.setToolTipText("Elija el país al que desea viajar");
		panelPaises.add(paisesEtiqueta);

		String[] paisesOpciones = { "Elija su destino", "Alemania", "Australia", "Brasil", "Canadá", "Estados Unidos",
				"Francia", "India", "Japón", "México", "Sudáfrica" };
		JComboBox<String> comboPais = new JComboBox<>(paisesOpciones);
		comboPais.setToolTipText("Elija el país al que desea viajar");
		panelPaises.add(comboPais);

		// panel para vacunas --- comprobar que haya tres seleccionados
		JPanel vacunasCompleto = new JPanel();
		panelFormulario.add(vacunasCompleto);
		vacunasCompleto.setLayout(new GridLayout(2, 1));

		JPanel panelTituloVacunas = new JPanel();
		vacunasCompleto.add(panelTituloVacunas);
		JLabel vacunasEtiqueta = new JLabel("**VACUNAS");
		panelTituloVacunas.add(vacunasEtiqueta);

		JPanel panelVacunas = new JPanel();
		panelVacunas.setToolTipText("Marque al menos 3 vacunas");
		vacunasCompleto.add(panelVacunas);
		panelVacunas.setLayout(new GridLayout(2, 5));

		JCheckBox vacuna1 = new JCheckBox("Fluzone (gripe)");
		panelVacunas.add(vacuna1);
		JCheckBox vacuna2 = new JCheckBox("M-M-R II (rubeola)");
		panelVacunas.add(vacuna2);
		JCheckBox vacuna3 = new JCheckBox("Tripedia (tétanos)");
		panelVacunas.add(vacuna3);
		JCheckBox vacuna4 = new JCheckBox("IPOL (poliomielitis)");
		panelVacunas.add(vacuna4);
		JCheckBox vacuna5 = new JCheckBox("Recombivax HB (hepatitis B)");
		panelVacunas.add(vacuna5);
		JCheckBox vacuna6 = new JCheckBox("Menactra (meningitis)");
		panelVacunas.add(vacuna6);
		JCheckBox vacuna7 = new JCheckBox("ProQuad (varicela)");
		panelVacunas.add(vacuna7);
		JCheckBox vacuna8 = new JCheckBox("Vivotif (fiebre tifoidea)");
		panelVacunas.add(vacuna8);
		JCheckBox vacuna9 = new JCheckBox("Vaqta (hepatitis A)");
		panelVacunas.add(vacuna9);
		JCheckBox vacuna10 = new JCheckBox("YF-Vax (fiebre amarilla");
		panelVacunas.add(vacuna10);

		// panel para hueco
		JPanel panel = new JPanel();
		panelFormulario.add(panel);

		// panel de los botones
		JPanel panelBotones = new JPanel();
		panelFormulario.add(panelBotones);

		// boton enviar datos
		JButton botonEnviar = new JButton("ENVIAR");
		panelBotones.add(botonEnviar);
		// boton para borrar todo
		JButton botonBorrarDatos = new JButton("BORRAR TODO");
		panelBotones.add(botonBorrarDatos);

		// panel para modo oscuro --- setForeground
		JPanel panelVision = new JPanel();
		panelVision.setLayout(new GridLayout(4, 1));
		ventanaPrincipal.getContentPane().add(panelVision, BorderLayout.EAST);

		JRadioButton claro = new JRadioButton();
		claro.setToolTipText("Modo claro");
		claro.setVerticalAlignment(SwingConstants.BOTTOM);
		claro.setText("Modo claro");
		claro.setSelected(true);
		panelVision.add(claro);
		JRadioButton oscuro = new JRadioButton();
		oscuro.setToolTipText("Modo oscuro");
		oscuro.setVerticalAlignment(SwingConstants.TOP);
		oscuro.setText("Modo oscuro");
		panelVision.add(oscuro);

		ButtonGroup grupoVision = new ButtonGroup();
		grupoVision.add(claro);
		grupoVision.add(oscuro);

		// aviso de campos
		JPanel panelAvisos = new JPanel();
		panelFormulario.add(panelAvisos);
		panelAvisos.setLayout(new GridLayout(2, 0));

		JLabel aviso1 = new JLabel("* Los campos con un asterisco deben rellenarse obligatoriamente");
		aviso1.setHorizontalAlignment(SwingConstants.CENTER);
		panelAvisos.add(aviso1);

		JLabel aviso2 = new JLabel("** Debes marcar al menos tres vacunas");
		aviso2.setHorizontalAlignment(SwingConstants.CENTER);
		panelAvisos.add(aviso2);

		// ventana final correcto
		JFrame ventanaTodoCorrecto = new JFrame("FORMULARIO CORRECTO");
		ventanaTodoCorrecto.setSize(500, 200);
		ventanaTodoCorrecto.setLocationRelativeTo(null);

		// panel final
		JPanel mostrarFinal = new JPanel(new BorderLayout());
		ventanaTodoCorrecto.add(mostrarFinal);
		//colores iniciales "modo claro"
		ventanaPrincipal.getContentPane().setBackground(Color.WHITE);

		titulo.setForeground(Color.BLUE);
		nombreEtiqueta.setForeground(Color.BLUE);
		apellidosEtiqueta.setForeground(Color.BLUE);
		generoEtiqueta.setForeground(Color.BLUE);
		usuarioEtiqueta.setForeground(Color.BLUE);
		contraseñaEtiqueta.setForeground(Color.BLUE);
		telefonoEtiqueta.setForeground(Color.BLUE);
		paisesEtiqueta.setForeground(Color.BLUE);
		vacunasEtiqueta.setForeground(Color.BLUE);
		aviso1.setForeground(Color.BLUE);
		aviso2.setForeground(Color.BLUE);
		botonEnviar.setForeground(Color.BLUE);
		botonBorrarDatos.setForeground(Color.BLUE);
		panelBotones.setBackground(Color.WHITE);
		panelVision.setBackground(Color.WHITE);
		panelTitulo.setBackground(Color.WHITE);
		panelFormulario.setBackground(Color.WHITE);
		panelNombre.setBackground(Color.WHITE);
		nombre.setBackground(Color.cyan);
		panelApellidos.setBackground(Color.WHITE);
		apellidos.setBackground(Color.cyan);
		panelGenero.setBackground(Color.WHITE);
		panelUsuario.setBackground(Color.WHITE);
		usuario.setBackground(Color.cyan);
		panelContraseña.setBackground(Color.WHITE);
		contraseña.setBackground(Color.cyan);
		panelTelefono.setBackground(Color.WHITE);
		telefono.setBackground(Color.cyan);
		panelPaises.setBackground(Color.WHITE);
		comboPais.setBackground(Color.cyan);
		panelVacunas.setBackground(Color.WHITE);
		panelTituloVacunas.setBackground(Color.WHITE);
		vacunasCompleto.setBackground(Color.WHITE);
		panel.setBackground(Color.WHITE);
		panelAvisos.setBackground(Color.WHITE);
		claro.setBackground(Color.WHITE);
		oscuro.setBackground(Color.WHITE);
		masculino.setBackground(Color.WHITE);
		femenino.setBackground(Color.WHITE);
		noRespuesta.setBackground(Color.WHITE);
		vacuna1.setBackground(Color.WHITE);
		vacuna2.setBackground(Color.WHITE);
		vacuna3.setBackground(Color.WHITE);
		vacuna4.setBackground(Color.WHITE);
		vacuna5.setBackground(Color.WHITE);
		vacuna6.setBackground(Color.WHITE);
		vacuna7.setBackground(Color.WHITE);
		vacuna8.setBackground(Color.WHITE);
		vacuna9.setBackground(Color.WHITE);
		vacuna10.setBackground(Color.WHITE);
		vacuna1.setForeground(Color.BLUE);
		vacuna2.setForeground(Color.BLUE);
		vacuna3.setForeground(Color.BLUE);
		vacuna4.setForeground(Color.BLUE);
		vacuna5.setForeground(Color.BLUE);
		vacuna6.setForeground(Color.BLUE);
		vacuna7.setForeground(Color.BLUE);
		vacuna8.setForeground(Color.BLUE);
		vacuna9.setForeground(Color.BLUE);
		vacuna10.setForeground(Color.BLUE);
		masculino.setForeground(Color.BLUE);
		femenino.setForeground(Color.BLUE);
		noRespuesta.setForeground(Color.BLUE);
		// funcionalidad de botones

		botonEnviar.addActionListener(e -> {

			String comprobarNombre = nombre.getText().trim();
			String comprobarApellidos = apellidos.getText().trim();
			String comprobarUsuario = usuario.getText().trim();
			String comprobarContraseña = new String(contraseña.getPassword()).trim();
			String comprobarTelefono = telefono.getText().trim();
			String comprobarPais = (String) comboPais.getSelectedItem();

			String ERapellidos = "^[a-zA-ZáéíóúÁÉÍÓÚ]+\\s[a-zA-ZáéíóúÁÉÍÓÚ]+$";
			String ERvacia = "^.+$";
			String numeros = "^[0-9]{9}$";

			int vacunasSeleccionadas = 0;
			boolean faltaAlgo = false;
			StringBuilder mensajeError = new StringBuilder("Por favor, complete los campos correctamente:\n");

			if (!comprobarNombre.matches(ERvacia)) {
				faltaAlgo = true;
				mensajeError.append("- Nombre (No puede estar vacío)\n");
			}

			if (!comprobarApellidos.matches(ERapellidos)) {
				faltaAlgo = true;
				mensajeError.append("- Apellidos (deben ser dos palabras separadas por un espacio)\n");
			}

			if (!masculino.isSelected() && !femenino.isSelected() && !noRespuesta.isSelected()) {
				faltaAlgo = true;
				mensajeError.append("- Género (Tienes que marcar una opción)\n");
			}

			if (!comprobarUsuario.matches(ERvacia)) {
				faltaAlgo = true;
				mensajeError.append("- Usuario (No puede estar vacío)\n");
			}

			if (!comprobarContraseña.matches(ERvacia)) {
				faltaAlgo = true;
				mensajeError.append("- Contraseña (No puede estar vacío)\n");
			}

			if (!comprobarTelefono.matches(numeros)) {
				faltaAlgo = true;
				mensajeError.append("- Teléfono (deben ser 9 dígitos)\n");
			}

			if (comprobarPais.equals("Elija su destino")) {
				faltaAlgo = true;
				mensajeError.append("- País (Debes elegir un destino)\n");
			}

			if (vacuna1.isSelected())
				vacunasSeleccionadas++;
			if (vacuna2.isSelected())
				vacunasSeleccionadas++;
			if (vacuna3.isSelected())
				vacunasSeleccionadas++;
			if (vacuna4.isSelected())
				vacunasSeleccionadas++;
			if (vacuna5.isSelected())
				vacunasSeleccionadas++;
			if (vacuna6.isSelected())
				vacunasSeleccionadas++;
			if (vacuna7.isSelected())
				vacunasSeleccionadas++;
			if (vacuna8.isSelected())
				vacunasSeleccionadas++;
			if (vacuna9.isSelected())
				vacunasSeleccionadas++;
			if (vacuna10.isSelected())
				vacunasSeleccionadas++;

			if (vacunasSeleccionadas < 3) {
				faltaAlgo = true;
				mensajeError.append("- Vacunas (Debes seleccionar al menos 3)\n");
			}

			if (faltaAlgo) {
				JOptionPane.showMessageDialog(ventanaPrincipal, mensajeError.toString(), "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				ventanaPrincipal.setVisible(false);
				String mensajeFinal;

				if (masculino.isSelected()) {
					mensajeFinal = "<html><body><b>Bienvenido señor:</b><br>";
				} else if (femenino.isSelected()) {
					mensajeFinal = "<html><body><b>Bienvenida señora:</b><br>";
				} else {
					mensajeFinal = "<html><body><b>Bienvenide:</b><br>";
				}

				mensajeFinal += comprobarNombre + " " + comprobarApellidos + "<br>"
						+ "Estaremos en contacto a través del telefono: " + comprobarTelefono + "<br>"
						+ "Su nombre de usuario es: " + comprobarUsuario + "<br>"
						+ "Pronto tendrá la información sobre su viaje a: " + comprobarPais + "<br>";

				mensajeFinal += "Vacunas seleccionadas: ";
				if (vacuna1.isSelected())
					mensajeFinal += "Fluzone (gripe), ";
				if (vacuna2.isSelected())
					mensajeFinal += "M-M-R II (rubeola), ";
				if (vacuna3.isSelected())
					mensajeFinal += "Tripedia (tétanos), ";
				if (vacuna4.isSelected())
					mensajeFinal += "IPOL (poliomielitis), ";
				if (vacuna5.isSelected())
					mensajeFinal += "Recombivax HB (hepatitis B), ";
				if (vacuna6.isSelected())
					mensajeFinal += "Menactra (meningitis), ";
				if (vacuna7.isSelected())
					mensajeFinal += "ProQuad (varicela), ";
				if (vacuna8.isSelected())
					mensajeFinal += "Vivotif (fiebre tifoidea), ";
				if (vacuna9.isSelected())
					mensajeFinal += "Vaqta (hepatitis A), ";
				if (vacuna10.isSelected())
					mensajeFinal += "YF-Vax (fiebre amarilla), ";

				mensajeFinal = mensajeFinal.substring(0, mensajeFinal.length() - 2);

				mensajeFinal += "</body></html>";

				// Crear un JLabel para mostrar el mensaje
				JLabel ultimoMensaje = new JLabel(mensajeFinal);
				ultimoMensaje.setHorizontalAlignment(SwingConstants.CENTER);

				// Agregar el JLabel al JFrame
				mostrarFinal.add(ultimoMensaje, BorderLayout.CENTER);
				ventanaTodoCorrecto.setVisible(true);
			}
		});

		botonBorrarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				nombre.setText("");
				apellidos.setText("");
				masculino.setSelected(false);
				femenino.setSelected(false);
				noRespuesta.setSelected(false);
				usuario.setText("");
				contraseña.setText("");
				telefono.setText("");
				comboPais.setSelectedIndex(0);
				vacuna1.setSelected(false);
				vacuna2.setSelected(false);
				vacuna3.setSelected(false);
				vacuna4.setSelected(false);
				vacuna5.setSelected(false);
				vacuna6.setSelected(false);
				vacuna7.setSelected(false);
				vacuna8.setSelected(false);
				vacuna9.setSelected(false);
				vacuna10.setSelected(false);

			}
		});

		claro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (claro.isSelected()) {

					ventanaPrincipal.getContentPane().setBackground(Color.WHITE);

					titulo.setForeground(Color.BLUE);
					nombreEtiqueta.setForeground(Color.BLUE);
					apellidosEtiqueta.setForeground(Color.BLUE);
					generoEtiqueta.setForeground(Color.BLUE);
					usuarioEtiqueta.setForeground(Color.BLUE);
					contraseñaEtiqueta.setForeground(Color.BLUE);
					telefonoEtiqueta.setForeground(Color.BLUE);
					paisesEtiqueta.setForeground(Color.BLUE);
					vacunasEtiqueta.setForeground(Color.BLUE);
					aviso1.setForeground(Color.BLUE);
					aviso2.setForeground(Color.BLUE);
					botonEnviar.setForeground(Color.BLUE);
					botonBorrarDatos.setForeground(Color.BLUE);
					panelBotones.setBackground(Color.WHITE);
					panelVision.setBackground(Color.WHITE);
					panelTitulo.setBackground(Color.WHITE);
					panelFormulario.setBackground(Color.WHITE);
					panelNombre.setBackground(Color.WHITE);
					nombre.setBackground(Color.cyan);
					panelApellidos.setBackground(Color.WHITE);
					apellidos.setBackground(Color.cyan);
					panelGenero.setBackground(Color.WHITE);
					panelUsuario.setBackground(Color.WHITE);
					usuario.setBackground(Color.cyan);
					panelContraseña.setBackground(Color.WHITE);
					contraseña.setBackground(Color.cyan);
					panelTelefono.setBackground(Color.WHITE);
					telefono.setBackground(Color.cyan);
					panelPaises.setBackground(Color.WHITE);
					comboPais.setBackground(Color.cyan);
					panelVacunas.setBackground(Color.WHITE);
					panelTituloVacunas.setBackground(Color.WHITE);
					vacunasCompleto.setBackground(Color.WHITE);
					panel.setBackground(Color.WHITE);
					panelAvisos.setBackground(Color.WHITE);
					claro.setBackground(Color.WHITE);
					oscuro.setBackground(Color.WHITE);
					masculino.setBackground(Color.WHITE);
					femenino.setBackground(Color.WHITE);
					noRespuesta.setBackground(Color.WHITE);
					vacuna1.setBackground(Color.WHITE);
					vacuna2.setBackground(Color.WHITE);
					vacuna3.setBackground(Color.WHITE);
					vacuna4.setBackground(Color.WHITE);
					vacuna5.setBackground(Color.WHITE);
					vacuna6.setBackground(Color.WHITE);
					vacuna7.setBackground(Color.WHITE);
					vacuna8.setBackground(Color.WHITE);
					vacuna9.setBackground(Color.WHITE);
					vacuna10.setBackground(Color.WHITE);
					vacuna1.setForeground(Color.BLUE);
					vacuna2.setForeground(Color.BLUE);
					vacuna3.setForeground(Color.BLUE);
					vacuna4.setForeground(Color.BLUE);
					vacuna5.setForeground(Color.BLUE);
					vacuna6.setForeground(Color.BLUE);
					vacuna7.setForeground(Color.BLUE);
					vacuna8.setForeground(Color.BLUE);
					vacuna9.setForeground(Color.BLUE);
					vacuna10.setForeground(Color.BLUE);
					masculino.setForeground(Color.BLUE);
					femenino.setForeground(Color.BLUE);
					noRespuesta.setForeground(Color.BLUE);
				}
			}
		});

		oscuro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (oscuro.isSelected()) {

					ventanaPrincipal.getContentPane().setBackground(Color.BLACK);
					titulo.setForeground(Color.WHITE);
					nombreEtiqueta.setForeground(Color.WHITE);
					apellidosEtiqueta.setForeground(Color.WHITE);
					generoEtiqueta.setForeground(Color.WHITE);
					usuarioEtiqueta.setForeground(Color.WHITE);
					contraseñaEtiqueta.setForeground(Color.WHITE);
					telefonoEtiqueta.setForeground(Color.WHITE);
					paisesEtiqueta.setForeground(Color.WHITE);
					vacunasEtiqueta.setForeground(Color.WHITE);
					aviso1.setForeground(Color.WHITE);
					aviso2.setForeground(Color.WHITE);
					botonEnviar.setForeground(Color.BLACK);
					botonBorrarDatos.setForeground(Color.BLACK);
					panelBotones.setBackground(Color.BLACK);
					panelVision.setBackground(Color.BLACK);
					panelTitulo.setBackground(Color.BLACK);
					panelFormulario.setBackground(Color.BLACK);
					panelNombre.setBackground(Color.BLACK);
					nombre.setBackground(Color.WHITE);
					panelApellidos.setBackground(Color.BLACK);
					apellidos.setBackground(Color.WHITE);
					panelGenero.setBackground(Color.BLACK);
					panelUsuario.setBackground(Color.BLACK);
					usuario.setBackground(Color.WHITE);
					panelContraseña.setBackground(Color.BLACK);
					contraseña.setBackground(Color.WHITE);
					panelTelefono.setBackground(Color.BLACK);
					telefono.setBackground(Color.WHITE);
					panelPaises.setBackground(Color.BLACK);
					comboPais.setBackground(Color.WHITE);
					panelVacunas.setBackground(Color.BLACK);
					panelTituloVacunas.setBackground(Color.BLACK);
					vacunasCompleto.setBackground(Color.BLACK);
					panel.setBackground(Color.BLACK);
					panelAvisos.setBackground(Color.BLACK);
					claro.setBackground(Color.BLACK);
					oscuro.setBackground(Color.BLACK);
					masculino.setBackground(Color.BLACK);
					femenino.setBackground(Color.BLACK);
					noRespuesta.setBackground(Color.BLACK);
					vacuna1.setBackground(Color.BLACK);
					vacuna2.setBackground(Color.BLACK);
					vacuna3.setBackground(Color.BLACK);
					vacuna4.setBackground(Color.BLACK);
					vacuna5.setBackground(Color.BLACK);
					vacuna6.setBackground(Color.BLACK);
					vacuna7.setBackground(Color.BLACK);
					vacuna8.setBackground(Color.BLACK);
					vacuna9.setBackground(Color.BLACK);
					vacuna10.setBackground(Color.BLACK);
					vacuna1.setForeground(Color.WHITE);
					vacuna2.setForeground(Color.WHITE);
					vacuna3.setForeground(Color.WHITE);
					vacuna4.setForeground(Color.WHITE);
					vacuna5.setForeground(Color.WHITE);
					vacuna6.setForeground(Color.WHITE);
					vacuna7.setForeground(Color.WHITE);
					vacuna8.setForeground(Color.WHITE);
					vacuna9.setForeground(Color.WHITE);
					vacuna10.setForeground(Color.WHITE);
					masculino.setForeground(Color.WHITE);
					femenino.setForeground(Color.WHITE);
					noRespuesta.setForeground(Color.WHITE);
				}
			}
		});

		ventanaPrincipal.setVisible(true);
	}

}