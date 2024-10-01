import javax.swing.event.DocumentListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.Timer;

public class RegistroPacientes extends JFrame {
    private JTextField txtCedula;
    private JComboBox<String> cmbCategoria;
    private JComboBox<String> cmbServicio;
    private JLabel lblContador;
    private JSlider sliderTiempo;
    private JLabel lblTiempoSlider;
    private ArrayList<Paciente> pacientes;
    private int contadorPacientes = 0;
    private JTable tablaPacientes;
    private DefaultTableModel modeloTabla;
    private JLabel lblPacienteAtendido;
    private JLabel lblProximoPaciente;
    private Timer timer;
    private boolean atencionAutomaticaActiva = false;

    public RegistroPacientes() {
        pacientes = new ArrayList<>();

        setTitle("Registro de Pacientes");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelPrincipal = new JPanel(new GridLayout(1, 2)); 
        add(panelPrincipal, BorderLayout.CENTER);

        JPanel panelFormulario = new JPanel(new GridLayout(8, 2));
        txtCedula = new JTextField();
        cmbCategoria = new JComboBox<>(new String[] {"Menor de 60", "Adulto Mayor", "Persona con Discapacidad"});
        cmbServicio = new JComboBox<>(new String[] {"Consulta General", "Consulta Especializada", "Prueba de Laboratorio", "Imágenes Diagnósticas"});
        lblContador = new JLabel("Personas registradas: 0");
        lblTiempoSlider = new JLabel("Tiempo por turno: 10 segundos"); 
        sliderTiempo = new JSlider(5, 60, 10);
        sliderTiempo.setPaintTicks(true);
        sliderTiempo.setPaintLabels(true);
        sliderTiempo.setMajorTickSpacing(5); 
        
        JButton btnRegistrar = new JButton("Registrar Paciente");

        txtCedula.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarNumerico();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validarNumerico();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validarNumerico();
            }

            private void validarNumerico() {
                String text = txtCedula.getText();
                if (!text.matches("\\d*")) {
                    JOptionPane.showMessageDialog(null, "El campo cédula solo debe contener valores numéricos.");
                    txtCedula.setText(text.replaceAll("[^\\d]", "")); 
                }
            }
        });

        
        panelFormulario.add(new JLabel("Cédula:"));
        panelFormulario.add(txtCedula);
        panelFormulario.add(new JLabel("Categoría:"));
        panelFormulario.add(cmbCategoria);
        panelFormulario.add(new JLabel("Servicio:"));
        panelFormulario.add(cmbServicio);
        panelFormulario.add(lblContador);
        panelFormulario.add(lblTiempoSlider);
        panelFormulario.add(sliderTiempo);
        panelFormulario.add(btnRegistrar);

        panelPrincipal.add(panelFormulario);

        JPanel panelDerecho = new JPanel(new BorderLayout());

        // Etiqueta para el paciente en atención y el próximo turno
        JPanel panelAtencion = new JPanel(new GridLayout(2, 1));
        lblPacienteAtendido = new JLabel("Paciente en atención: Ninguno", SwingConstants.CENTER);
        lblPacienteAtendido.setFont(new Font("Arial", Font.BOLD, 14));
        lblProximoPaciente = new JLabel("Próximo turno: Ninguno", SwingConstants.CENTER);
        lblProximoPaciente.setFont(new Font("Arial", Font.PLAIN, 12));
        panelAtencion.add(lblPacienteAtendido);
        panelAtencion.add(lblProximoPaciente);

        panelDerecho.add(panelAtencion, BorderLayout.NORTH);