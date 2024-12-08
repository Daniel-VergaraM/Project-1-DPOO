package studentGUI;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Scanner;

import controller.StudentController;
import learningpath.LearningPath;
import learningpath.activity.ExamActivity;
import learningpath.activity.ResourceActivity;
import learningpath.question.MultipleOptionQuestion;
import learningpath.question.OpenQuestion;
import persistencia.CentralPersistencia;
import users.Professor;
import users.Student;

public class StudentGUI {
	
		private final StudentController studentController;
		protected Scanner scanner;
		private static Student studentOwn;
		public LinkedList<String> arrayIntereses = new LinkedList<String>();
		public String interes = "Programacion";
		public LinkedList<String> arrayEtiquetas = new LinkedList<String>(); //Para mostrar que es funcional
		public String tag1 = "TAG";
		public String tag2 = "Tag2";
		public Professor ivan = new Professor( "1234", "contraseña");
		LinkedList<MultipleOptionQuestion> MOQuestions = null;
		public LinkedList<OpenQuestion> questions = null;
		public ExamActivity examenFinal= new ExamActivity("aaa",  "Miedo",  "Medir los conocimientos", 60,  true,	 questions, MOQuestions); 
		public ResourceActivity actividad1 = new ResourceActivity("Titulo Ingenioso", "En esta actividad tienes que...", "El objetivo es....", 20, true, "");
		public LearningPath learningPathPrueba = new LearningPath("Sistemas", "Ingeniería en sistemas",  arrayIntereses, 4,  arrayEtiquetas,ivan);
		public CentralPersistencia persistir = new CentralPersistencia();    private final PanelManager panelManager;
	
		
		
    public StudentGUI(StudentController studentController) {
        this.studentController = studentController;
        this.panelManager = new PanelManager();
        createAndShowGUI(studentController);
    }

    private void createAndShowGUI(StudentController studentController) {
        JFrame frame = new JFrame("Portal del Estudiante");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(new BorderLayout());
		this.learningPathPrueba.addActivity(actividad1);
		this.learningPathPrueba.addActivity(examenFinal);
        JPanel panelSup = new JPanel();
        panelSup.setLayout(new BorderLayout());
        panelSup.setBackground(Color.PINK);  
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(7,1) );
        JLabel welcomeLabel = new JLabel("Bienvenido estudiante");
        welcomeLabel.setForeground(Color.white);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelSup.add(welcomeLabel, BorderLayout.NORTH);
        JButton btnVerLearningPaths = new JButton("Ver los learningPaths");
        JButton btnInscribirLearningPath = new JButton("Inscribir learningpaths");
        JButton btnVerActividades = new JButton("Ver actividades learningPath");
        JButton btnGenerarResena = new JButton("Generar una reseña para un LearningPath");
        JButton btnCalificarActividad = new JButton("Calificar una actividad");
        JButton btnConsultarProgreso = new JButton("Consultar el progreso de un LearningPath");
        btnVerLearningPaths.setBackground(new Color(245, 245, 220));
        btnInscribirLearningPath.setBackground(new Color(245, 245, 220));	
        btnVerActividades.setBackground(new Color(245, 245, 220));
        btnGenerarResena.setBackground(new Color(245, 245, 220));
        btnCalificarActividad.setBackground(new Color(245, 245, 220));
        btnConsultarProgreso.setBackground(new Color(245, 245, 220));
               mainPanel.add(btnVerLearningPaths);
        mainPanel.add(btnInscribirLearningPath);
        mainPanel.add(btnVerActividades);
        mainPanel.add(btnGenerarResena);
        mainPanel.add(btnCalificarActividad);
        mainPanel.add(btnConsultarProgreso);
        btnVerLearningPaths.addActionListener(e -> mostrarLearningPaths(studentController));
        btnInscribirLearningPath.addActionListener(e -> inscribirLearningPath(studentController));
        btnVerActividades.addActionListener(e -> verActividadesLearningPath(studentController));
        btnGenerarResena.addActionListener(e -> generarResena(studentController));
        btnCalificarActividad.addActionListener(e -> calificarActividad(studentController));
        btnConsultarProgreso.addActionListener(e -> consultarProgreso(studentController));

        panelManager.addPanel("main", mainPanel);
        frame.add(panelSup, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void mostrarLearningPaths(StudentController studentCont) {
        JOptionPane.showMessageDialog(null, "Estos son los LearningPaths: ");
        new LearningPathGUI(studentCont);
    }

    private void inscribirLearningPath(StudentController studentCont) {
        JOptionPane.showMessageDialog(null, "A continuación ingrese el ID para inscribir su learningPath");
        new LearningPathInscribirGUI(studentCont);

    }

    private void verActividadesLearningPath(StudentController studentCont) {
        JOptionPane.showMessageDialog(null, "Estas son las actividades de los learning Paths");
        new ViewActivitiesGUI(studentCont);
    }

    private void generarResena(StudentController studentCont) {
        JOptionPane.showMessageDialog(null, "Sea lo mas respetuoso posible recuerde que esto puede tener efecto en su historia académica");
        new AgregarReviewGUI(studentCont);
    }

    private void calificarActividad(StudentController studentCont) {
        JOptionPane.showMessageDialog(null, "A continuación califique las actividades: ");
        new CalificarActividadGUI(studentCont);
    }
    private void consultarProgreso(StudentController studentCont) {
        JOptionPane.showMessageDialog(null, "A continuación el progreso de los LearningPaths");
        new ConsultarProgresoGUI(studentCont);
    }
}
