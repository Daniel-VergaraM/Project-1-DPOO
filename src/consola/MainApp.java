package consola;

import javax.swing.*;
import javax.swing.border.Border;

import controller.Controller;
import controller.ProfessorController;
import controller.StudentController;
import persistencia.CentralPersistencia;
import professorGUI.ShowProfessorWindow;
import studentGUI.StudentGUI;
import users.Professor;
import users.Student;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApp {

    private Controller controller;
    private CentralPersistencia persistir;
    
    public MainApp() {
        this.controller = new Controller();
        initUI();
    }

    private void initUI() {
        // Crear el marco principal
        JFrame frame = new JFrame("Ingreso de usuarios");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        Color colorFondo = new Color(255, 192, 203); 
        // Panel de inicio
        JPanel panelSup = new JPanel();
        panelSup.setLayout(new BorderLayout());
        panelSup.setBackground(colorFondo);
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(2, 2));
        loginPanel.setBackground(Color.WHITE);

        JLabel welcomeLabel = new JLabel("Hola Usuario, por favor ingrese sus credenciales");
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Iniciar Sesión");
        JButton createUserButton = new JButton("Crear Usuario");
        JPanel paneInfe = new JPanel();
        loginButton.setBackground(Color.pink);
        loginButton.setForeground(Color.WHITE);

        createUserButton.setBackground(Color.pink);
        createUserButton.setForeground(Color.WHITE);
        
        paneInfe.setLayout(new GridLayout(1,2));
        // Añadir componentes al panel de inicio
        panelSup.add(welcomeLabel, BorderLayout.NORTH);
        frame.add(panelSup);
        loginPanel.add(new JLabel("Usuario:"));
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Contraseña:"));
        loginPanel.add(passwordField);
        
       
        paneInfe.add(loginButton);
        paneInfe.add(createUserButton);

        // Agregar funcionalidad al botón "Iniciar Sesión"
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            Controller newController = controller.login(username, password);
            if (newController != null) {
                controller = newController;
                if (controller instanceof ProfessorController) {
                    JOptionPane.showMessageDialog(frame, "Bienvenido Profesor");
                    new ShowProfessorWindow((ProfessorController)controller);
                } else if (controller instanceof StudentController) {
                    JOptionPane.showMessageDialog(frame, "Bienvenido Estudiante");
                    new StudentGUI((StudentController) controller);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Credenciales incorrectas. Inténtelo de nuevo.");
            }
        });

        // Agregar funcionalidad al botón "Crear Usuario"
        createUserButton.addActionListener(e -> openCreateUserDialog(frame));

        // Configurar y mostrar el marco principal
        frame.add(loginPanel, BorderLayout.CENTER);
        frame.add(paneInfe, BorderLayout.SOUTH);
        frame.add(panelSup, BorderLayout.NORTH);

        frame.setVisible(true);
    }

    private void openCreateUserDialog(JFrame parentFrame) {
        // Crear el cuadro de diálogo para crear un usuario
        JDialog createUserDialog = new JDialog(parentFrame, "Crear Usuario", true);
        createUserDialog.setSize(300, 200);
        createUserDialog.setLayout(new GridLayout(5, 1));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JComboBox<String> userTypeComboBox = new JComboBox<>(new String[]{"Profesor", "Estudiante"});
        JButton createButton = new JButton("Crear");

        createUserDialog.add(new JLabel("Usuario:"));
        createUserDialog.add(usernameField);
        createUserDialog.add(new JLabel("Contraseña:"));
        createUserDialog.add(passwordField);
        createUserDialog.add(new JLabel("Tipo de Usuario:"));
        createUserDialog.add(userTypeComboBox);
        createUserDialog.add(createButton);

        createButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String userType = userTypeComboBox.getSelectedIndex() == 0 ? "PROFESSOR" : "STUDENT";

            controller.registerUser(username, password, userType);
            if(userType == "PROFESSOR") {
            	Professor profe = new Professor(username, password);
            	controller.setCurrentUser(profe);
            	controller.registerProfessor(username, password);
          
            }
            else{
            	Student estudiante = new Student(username, password);
            	controller.setCurrentUser(estudiante);
            	controller.registerStudent(username, password);
            	 
            }
            JOptionPane.showMessageDialog(createUserDialog, "Usuario creado exitosamente.");
            createUserDialog.dispose();
        });
        
        createUserDialog.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainApp::new);
    }
}
