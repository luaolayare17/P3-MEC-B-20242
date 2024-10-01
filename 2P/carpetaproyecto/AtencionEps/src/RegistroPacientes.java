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
        
        // *** Título para la cola de usuarios ***
        JLabel lblColaTitulo = new JLabel("Cola de usuarios", SwingConstants.CENTER);
        lblColaTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        panelDerecho.add(lblColaTitulo, BorderLayout.CENTER);

        // *** TABLA DE PACIENTES ***
        String[] columnas = {"Cédula", "Hora de Registro"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaPacientes = new JTable(modeloTabla);
        panelDerecho.add(new JScrollPane(tablaPacientes), BorderLayout.SOUTH);

        panelPrincipal.add(panelDerecho);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarPaciente();
            }
        });

        sliderTiempo.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int tiempoSimulado = sliderTiempo.getValue();
                lblTiempoSlider.setText("Tiempo por turno: " + tiempoSimulado + " segundos");
            }
        });

        timer = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atenderSiguientePaciente();
            }
        });
    }

    private void registrarPaciente() {
        String cedula = txtCedula.getText();
        String categoria = (String) cmbCategoria.getSelectedItem();
        String servicio = (String) cmbServicio.getSelectedItem();
        LocalTime horaLlegada = LocalTime.now();

        if (cedula.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa la cédula.");
            return;
        }
    
        private void atenderSiguientePaciente() {
            if (pacientes.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay pacientes en la cola.");
                timer.stop();
                atencionAutomaticaActiva = false;
                return;
            }
    
            Paciente pacienteAtendido = pacientes.remove(0);
            lblPacienteAtendido.setText("<html>Paciente en atención:<br>"
                + "Cédula: " + pacienteAtendido.getCedula() + "<br>"
                + "Categoría: " + pacienteAtendido.getCategoria() + "<br>"
                + "Servicio: " + pacienteAtendido.getServicio() + "<br>"
                + "Hora de llegada: " + pacienteAtendido.getHoraLlegada() + "</html>");
    
            modeloTabla.removeRow(0);
    
            contadorPacientes--;
            lblContador.setText("Personas registradas: " + contadorPacientes);
    
            if (!pacientes.isEmpty()) {
                Paciente proximoPaciente = pacientes.get(0);
                lblProximoPaciente.setText("<html>Próximo turno:<br>"
                    + "Cédula: " + proximoPaciente.getCedula() + "<br>"
                    + "Categoría: " + proximoPaciente.getCategoria() + "<br>"
                    + "Servicio: " + proximoPaciente.getServicio() + "</html>");
            } else {
                lblProximoPaciente.setText("Próximo turno: Ninguno");
            }
        }
    
        private void iniciarAtencionAutomatica() {
            int tiempoSimulado = sliderTiempo.getValue();
            JOptionPane.showMessageDialog(this, "¡Inicia la atención automática! Tiempo ajustado a " + tiempoSimulado + " segundos por turno.");
            timer.setDelay(tiempoSimulado * 1000);
            timer.start();
            atencionAutomaticaActiva = true;
        }
    
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                RegistroPacientes frame = new RegistroPacientes();
                frame.setVisible(true);
            });
        }
    }
    
    class Paciente {
        private String cedula;
        private String categoria;
        private String servicio;
        private LocalTime horaLlegada;
    
        public Paciente(String cedula, String categoria, String servicio, LocalTime horaLlegada) {
            this.cedula = cedula;
            this.categoria = categoria;
            this.servicio = servicio;
            this.horaLlegada = horaLlegada;
        }
    
        public String getCedula() {
            return cedula;
        }
    
        public String getCategoria() {
            return categoria;
        }
    
        public String getServicio() {
            return servicio;
        }
    
        public LocalTime getHoraLlegada() {
            return horaLlegada;
        }
    }
    