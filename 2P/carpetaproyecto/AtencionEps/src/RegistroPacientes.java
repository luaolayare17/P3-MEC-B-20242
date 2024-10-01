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