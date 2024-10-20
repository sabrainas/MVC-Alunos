package br.edu.fatec.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import br.edu.fatec.dao.AlunoDAO;
import br.edu.fatec.dao.CursoDAO;
import br.edu.fatec.dao.DisciplinaDAO;
import br.edu.fatec.dao.NotasDAO;
import br.edu.fatec.model.Aluno;
import br.edu.fatec.model.Curso;
import br.edu.fatec.model.Disciplina;
import br.edu.fatec.model.Notas;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.util.List;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.ScrollPane;
import java.awt.Scrollbar;

public class TelaPrincipal extends JFrame {
	
	//====================Componente Formulario====================//
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTextField txtNome;
    private JTextField txtRa;
    private JTextField txtEmail;
    private JTextField txtCpf;
    private JTextField txtDataNascimento;
    private JTextField txtEndereco;
    private JTextField txtMunicipio;
    private JTextField txtCelular;
    private JTextField txtRaBusca;
    private JButton btnProximo;
    private JButton btnNovoCadastro;
    private JButton btnBuscar;
    private JButton btnSalvar;
    private JButton btnVoltar;
    private JComboBox<String> cmbBoxUf;
    private JComboBox<String> cmbBoxCurso;
    private JComboBox<String> cmbBoxCampus; 
    private JRadioButton rdMatutino;
    private JRadioButton rdVespertino;
    private JRadioButton rdNoturno;
    private JTabbedPane tabbedPane;
    private JLabel lblMensagem;
    private JLabel lblAluno;
    private JLabel lblCursoAluno;
    private JTextField txtNota1;
    private JTextField txtFaltas;
    //============================================//
    private Aluno aluno;
    private AlunoDAO alunoDao;
    private Curso curso;
    private CursoDAO cursoDao;
    private Notas notas;
    private NotasDAO notasDao;
    private JTextField txtRaBoletim;
    private JTextField txtNota2;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
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
	public TelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 816, 552);
		
		//============================ DADOS PESSOAIS ===============================//
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		mnArquivo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnArquivo.setForeground(new Color(0, 0, 0));
		menuBar.add(mnArquivo);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Editar");
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnArquivo.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Salvar");
		mntmNewMenuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnArquivo.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Sair");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mntmNewMenuItem_2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnArquivo.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu = new JMenu("Ajudar");
		mnNewMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Informações do Menu");
			}
		});
		mnNewMenu.setForeground(new Color(0, 0, 0));
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Sobre");
		mntmNewMenuItem_3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnNewMenu.add(mntmNewMenuItem_3);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 788, 469);
		contentPane.add(tabbedPane);
		
		JPanel dadosPessoais = new JPanel();
		tabbedPane.addTab("Dados Pessoais", null, dadosPessoais, null);
		dadosPessoais.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 29, 65, 17);
		dadosPessoais.add(lblNewLabel);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNome.setBounds(63, 20, 322, 37);
		dadosPessoais.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblRa = new JLabel("RA:");
		lblRa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRa.setBounds(416, 29, 65, 17);
		dadosPessoais.add(lblRa);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(10, 110, 65, 17);
		dadosPessoais.add(lblEmail);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCpf.setBounds(416, 110, 65, 17);
		dadosPessoais.add(lblCpf);
		
		JLabel lblDataDeNascimento = new JLabel("Data de nascimento:");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDataDeNascimento.setBounds(10, 189, 160, 17);
		dadosPessoais.add(lblDataDeNascimento);
		
		JLabel lblEndereo = new JLabel("Endereço:");
		lblEndereo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEndereo.setBounds(373, 189, 160, 17);
		dadosPessoais.add(lblEndereo);
		
		txtRa = new JTextField();
		txtRa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtRa.setColumns(10);
		txtRa.setBounds(451, 20, 322, 37);
		dadosPessoais.add(txtRa);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtEmail.setColumns(10);
		txtEmail.setBounds(63, 101, 322, 37);
		dadosPessoais.add(txtEmail);
		
		txtCpf = new JTextField();
		txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCpf.setColumns(10);
		txtCpf.setBounds(451, 101, 322, 37);
		dadosPessoais.add(txtCpf);
		
		txtDataNascimento = new JTextField();
		txtDataNascimento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtDataNascimento.setColumns(10);
		txtDataNascimento.setBounds(166, 179, 187, 37);
		dadosPessoais.add(txtDataNascimento);
		
		txtEndereco = new JTextField();
		txtEndereco.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(451, 179, 322, 37);
		dadosPessoais.add(txtEndereco);
		
		JLabel lblMunicpio = new JLabel("Município:");
		lblMunicpio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMunicpio.setBounds(10, 261, 92, 17);
		dadosPessoais.add(lblMunicpio);
		
		txtMunicipio = new JTextField();
		txtMunicipio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtMunicipio.setColumns(10);
		txtMunicipio.setBounds(91, 252, 262, 37);
		dadosPessoais.add(txtMunicipio);
		
		JLabel lblUf = new JLabel("UF:");
        lblUf.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblUf.setBounds(370, 263, 92, 17);
        dadosPessoais.add(lblUf);

        cmbBoxUf = new JComboBox<>(); 
        cmbBoxUf.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cmbBoxUf.setModel(new DefaultComboBoxModel(new String[] {"  ", "SP", "RJ", "BA", "MG", "ES", "RS", "PA", "RN", "AC", "AM"}));
        cmbBoxUf.setBounds(402, 255, 92, 37);
        dadosPessoais.add(cmbBoxUf);
		
		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCelular.setBounds(515, 263, 92, 17);
		dadosPessoais.add(lblCelular);
		
		txtCelular = new JTextField();
		txtCelular.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCelular.setColumns(10);
		txtCelular.setBounds(577, 254, 196, 37);
		dadosPessoais.add(txtCelular);
		
		JButton btnNovoCadastro = new JButton("Novo Cadastro");
		btnNovoCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllFieldsEnabled(true);
				txtRa.setText(null);
				txtNome.setText(null);
				txtEmail.setText(null);
				txtDataNascimento.setText(null);
				txtEndereco.setText(null);
				txtCelular.setText(null);
				txtCpf.setText(null);
				txtMunicipio.setText(null);
			}
		});
		
		btnNovoCadastro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNovoCadastro.setBounds(515, 322, 119, 37);
		dadosPessoais.add(btnNovoCadastro);
		
		btnProximo = new JButton("Próximo");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnProximo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnProximo.setBounds(654, 322, 119, 37);
		dadosPessoais.add(btnProximo);
		
		//============================== CURSO ===================================//
		JPanel dadosCurso = new JPanel();
		tabbedPane.addTab("Curso\r\n", null, dadosCurso, null);
		dadosCurso.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Curso:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(72, 24, 55, 35);
		dadosCurso.add(lblNewLabel_1);
		
		cmbBoxCurso = new JComboBox<>();
        cmbBoxCurso.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cmbBoxCurso.setModel(new DefaultComboBoxModel(new String[] {"  ", "Análise e Desenvolvimento de Sistemas", "Gestão Empresarial", "Logística"}));
        cmbBoxCurso.setBounds(137, 23, 560, 37);
        dadosCurso.add(cmbBoxCurso);
		
		JLabel lblNewLabel_1_1 = new JLabel("Campus:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(72, 109, 74, 35);
		dadosCurso.add(lblNewLabel_1_1);
		
		cmbBoxCampus = new JComboBox<>(); // Instanciado corretamente
        cmbBoxCampus.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cmbBoxCampus.setModel(new DefaultComboBoxModel(new String[] {"  ", "Guarulhos", "Carapicuíba", "São José dos Campos", "Sorocaba"}));
        cmbBoxCampus.setBounds(137, 108, 560, 37);
        dadosCurso.add(cmbBoxCampus); // Adicionado ao painel
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Período:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(72, 180, 74, 35);
		dadosCurso.add(lblNewLabel_1_1_1);
		
		rdMatutino = new JRadioButton("Matutino");
		rdMatutino.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdMatutino.setBounds(153, 189, 103, 21);
		dadosCurso.add(rdMatutino);
		
		rdVespertino = new JRadioButton("Vespertino");
		rdVespertino.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdVespertino.setBounds(354, 189, 103, 21);
		dadosCurso.add(rdVespertino);
		
		rdNoturno = new JRadioButton("Noturno");
		rdNoturno.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdNoturno.setBounds(594, 189, 103, 21);
		dadosCurso.add(rdNoturno);
		
		ButtonGroup grupoPeriodo = new ButtonGroup();
		grupoPeriodo.add(rdMatutino);
		grupoPeriodo.add(rdVespertino);
		grupoPeriodo.add(rdNoturno);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		        	//inicializa o DAO para realizar a consulta
		        	CursoDAO cursoDao = new CursoDAO();
		            // cria o objeto para pegar os dados da tela
		            aluno = new Aluno();
		            		            	            
		            // Defina os valores para o curso antes de salvá-lo
		            String nomeCurso = (String) cmbBoxCurso.getSelectedItem();
		            String campus = (String) cmbBoxCampus.getSelectedItem();
		            String periodo = null;
		            if (rdMatutino.isSelected()) {
		                periodo = rdMatutino.getText();
		            } else if (rdVespertino.isSelected()) {
		                periodo = rdVespertino.getText();
		            } else if (rdNoturno.isSelected()) {
		                periodo = rdNoturno.getText();
		            }
		            
		            Curso curso = cursoDao.consultar(nomeCurso, campus, periodo);

		            if(curso == null) {
		            	lblMensagem.setText("Curso não encontrado");
		            	return;
		            }
		            
		            // Defina os valores para o aluno
		            aluno.setRaAluno(Integer.parseInt(txtRa.getText()));
		            aluno.setNomeAluno(txtNome.getText());
		            aluno.setEmailAluno(txtEmail.getText());
		            aluno.setDataNascimento(txtDataNascimento.getText());
		            aluno.setEnderecoAluno(txtEndereco.getText());
		            aluno.setCelularAluno(txtCelular.getText());
		            aluno.setMunicipioAluno(txtMunicipio.getText());
		            aluno.setUfAluno((String) cmbBoxUf.getSelectedItem());
		            aluno.setCpfAluno(txtCpf.getText());

		            // Salvar o aluno associado ao curso
		            AlunoDAO alunoDao = new AlunoDAO();
		            alunoDao.salvar(aluno, curso.getIdCurso());

		            lblMensagem.setText("Salvo com Sucesso!");

		        } catch (Exception el) {
		            lblMensagem.setText("Erro ao salvar");
		            System.out.println(el.getMessage());
		        }
		    }
		});
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSalvar.setBounds(612, 282, 85, 35);
		dadosCurso.add(btnSalvar);
		
		lblMensagem = new JLabel("");
		lblMensagem.setForeground(new Color(0, 0, 0));
		lblMensagem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMensagem.setBounds(10, 338, 196, 21);
		dadosCurso.add(lblMensagem);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVoltar.setBounds(72, 282, 85, 35);
		dadosCurso.add(btnVoltar);
		
		//=================== NOTAS E FALTAS ===================//
		JPanel notasFaltas = new JPanel();
		tabbedPane.addTab("Notas e Faltas", null, notasFaltas, null);
		notasFaltas.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("RA:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(10, 43, 45, 13);
		notasFaltas.add(lblNewLabel_2);
		
		txtRaBusca = new JTextField();
		txtRaBusca.setBounds(53, 37, 316, 29);
		notasFaltas.add(txtRaBusca);
		txtRaBusca.setColumns(10);
						
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNome.setBounds(10, 107, 57, 29);
		notasFaltas.add(lblNome);
		
		JLabel lblAluno = new JLabel("");
		lblAluno.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAluno.setBounds(66, 107, 313, 29);
		notasFaltas.add(lblAluno);
		
		JLabel lblCurso = new JLabel("Curso:");
		lblCurso.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCurso.setBounds(389, 107, 57, 29);
		notasFaltas.add(lblCurso);
		
		JLabel lblCursoAluno = new JLabel("");
		lblCursoAluno.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCursoAluno.setBounds(439, 107, 334, 29);
		notasFaltas.add(lblCursoAluno);
		
		JLabel lblDisciplina = new JLabel("Disciplina:");
		lblDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDisciplina.setBounds(10, 180, 74, 29);
		notasFaltas.add(lblDisciplina);
		
		JComboBox cmbBoxDisciplina = new JComboBox();
		cmbBoxDisciplina.setModel(new DefaultComboBoxModel(new String[] {"  ", "Programação Orientada a Objetos"}));
		cmbBoxDisciplina.setBounds(94, 180, 504, 35);
		notasFaltas.add(cmbBoxDisciplina);
		
		JLabel lblSemestre = new JLabel("Semestre:");
		lblSemestre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSemestre.setBounds(10, 250, 74, 29);
		notasFaltas.add(lblSemestre);
		
		JComboBox cmbBoxSemestre = new JComboBox();
		cmbBoxSemestre.setModel(new DefaultComboBoxModel(new String[] {"  ", "2022-1", "2022-2", "2023-1", "2023-2", "2024-1", "2024-2"}));
		cmbBoxSemestre.setBounds(94, 244, 121, 35);
		notasFaltas.add(cmbBoxSemestre);
		
		JLabel lblNota1 = new JLabel("Nota 1:");
		lblNota1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNota1.setBounds(225, 250, 53, 29);
		notasFaltas.add(lblNota1);
		
		txtNota1 = new JTextField();
		txtNota1.setColumns(10);
		txtNota1.setBounds(288, 252, 45, 29);
		notasFaltas.add(txtNota1);
		
		JLabel lblFaltas = new JLabel("Faltas:");
		lblFaltas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFaltas.setBounds(608, 181, 74, 29);
		notasFaltas.add(lblFaltas);
		
		txtFaltas = new JTextField();
		txtFaltas.setColumns(10);
		txtFaltas.setBounds(658, 182, 85, 29);
		notasFaltas.add(txtFaltas);
						
		JButton btnBuscarRa = new JButton("Buscar");
		btnBuscarRa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AlunoDAO alunoDao = new AlunoDAO();
					int ra = Integer.parseInt(txtRaBusca.getText());
					aluno = alunoDao.consultar(ra);
					
					lblAluno.setText(aluno.getNomeAluno());
					//verifica se o aluno foi encontrado
					if (aluno != null) {
                        //mostra o aluno
						lblAluno.setText(aluno.getNomeAluno());
                        CursoDAO cursoDao = new CursoDAO();
                        
                        Curso curso = cursoDao.consultar(aluno.getRaAluno());
                        
                        if(curso != null) {
                        	lblCursoAluno.setText(curso.getCurso());
                        	
                        	List<Disciplina> disciplinas = cursoDao.getDisciplinas(curso.getIdCurso());
                        	cmbBoxDisciplina.removeAllItems();
                        	
                        	for(Disciplina disciplina : disciplinas) {
                        		cmbBoxDisciplina.addItem(disciplina.getNomeDisciplina());
                        	}
                        }else {
                        	lblCursoAluno.setText("Curso não encontrado");
                        }
                    } else {
                        lblAluno.setText("Aluno não encontrado.");
                        lblCursoAluno.setText("Curso não encontrado");
                    }
					
		        } catch (Exception ex) {
		            // Exibe outros erros no console ou em um label
		            System.out.println("Erro: " + ex.getMessage());
		        }
			}
		});
		btnBuscarRa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBuscarRa.setBounds(379, 34, 85, 35);
		notasFaltas.add(btnBuscarRa);
		
		JLabel lblMensagemNotas = new JLabel("");
		lblMensagemNotas.setForeground(Color.BLACK);
		lblMensagemNotas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMensagemNotas.setBounds(21, 345, 196, 21);
		notasFaltas.add(lblMensagemNotas);
		
		JButton btnSalvarNotas = new JButton("Salvar");
		btnSalvarNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//inicializa o DAO para realizar a consulta
		        	AlunoDAO alunoDao = new AlunoDAO();
		        	aluno = alunoDao.consultar(Integer.parseInt(txtRaBusca.getText()));
		            
		        	if(aluno != null) {
		        		notas = new Notas();
		        		// Defina os valores para notas
			            notas.setDisciplina((String) cmbBoxDisciplina.getSelectedItem());
			            notas.setSemestre((String) cmbBoxSemestre.getSelectedItem());
			            
			            double nota1 = Double.parseDouble(txtNota1.getText());
		                double nota2 = Double.parseDouble(txtNota2.getText());
		                notas.setNota(nota1);
		                notas.setNota2(nota2);
			            double media = (nota1 + nota2) / 2;
			            
			            notas.setMedia(media);
			            
			            notas.setFaltas(Integer.parseInt(txtFaltas.getText()));
			            notas.setRaAluno(aluno.getRaAluno());
			            
			            NotasDAO notasDao = new NotasDAO();
			            int idDisciplina = notasDao.buscarIdDisciplina(notas.getDisciplina());
			            notas.setIdDisciplina(idDisciplina);
			            
			            System.out.println("Tentando inserir notas com idDisciplina: " + notas.getIdDisciplina());
			            if(notas.getNota() < 0 || notas.getNota() > 10) {
			            	lblMensagemNotas.setText("Digite uma nota válida");
			            }else {
			            	notasDao.salvar(notas);
				            lblMensagemNotas.setText("Salvo com Sucesso!");
			            }
		        	}	
		        	

				} catch (Exception el) {
					lblMensagemNotas.setText("Erro ao salvar");
					System.out.println(el.getMessage());
				}
			}
		});
		btnSalvarNotas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSalvarNotas.setBounds(669, 309, 85, 35);
		notasFaltas.add(btnSalvarNotas);
		
		JLabel lblNota2 = new JLabel("Nota 2:");
		lblNota2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNota2.setBounds(343, 250, 57, 29);
		notasFaltas.add(lblNota2);
		
		txtNota2 = new JTextField();
		txtNota2.setColumns(10);
		txtNota2.setBounds(410, 252, 45, 29);
		notasFaltas.add(txtNota2);
		
		JLabel lblMedia = new JLabel("Média:");
		lblMedia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMedia.setBounds(560, 250, 57, 29);
		notasFaltas.add(lblMedia);
		
		JLabel lblMostrarMedia = new JLabel("");
		lblMostrarMedia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMostrarMedia.setBounds(608, 250, 165, 29);
		notasFaltas.add(lblMostrarMedia);
		
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					NotasDAO notasDao = new NotasDAO();
					int ra = Integer.parseInt(txtRaBusca.getText());
					List<Notas> listaNotas = notasDao.consultar(ra);
					System.out.println("Notas encontradas: " + listaNotas.size());
					
					String resultado = notasDao.calcularMedia(listaNotas);
					lblMostrarMedia.setText(resultado);
					
				}catch(Exception ex) {
					System.out.println("Erro " + ex.getMessage());
				}
			}
		});
		btnCalcular.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCalcular.setBounds(465, 247, 85, 35);
		notasFaltas.add(btnCalcular);
		
		//========================== BOLETIM =============================//
		JPanel dadosBoletim = new JPanel();
		tabbedPane.addTab("Boletim", null, dadosBoletim, null);
		dadosBoletim.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("RA:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(10, 23, 33, 26);
		dadosBoletim.add(lblNewLabel_3);
		
		txtRaBoletim = new JTextField();
		txtRaBoletim.setColumns(10);
		txtRaBoletim.setBounds(37, 23, 316, 29);
		dadosBoletim.add(txtRaBoletim);
		
				
		JLabel lblNomeAluno = new JLabel("");
		lblNomeAluno.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNomeAluno.setBounds(10, 90, 371, 26);
		dadosBoletim.add(lblNomeAluno);
		
		JLabel lblRaAluno = new JLabel("");
		lblRaAluno.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRaAluno.setBounds(391, 90, 371, 26);
		dadosBoletim.add(lblRaAluno);
		
		JLabel lblCursoBoletim = new JLabel("");
		lblCursoBoletim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCursoBoletim.setBounds(10, 135, 371, 26);
		dadosBoletim.add(lblCursoBoletim);
		
		JLabel lblDisciplinaBoletim = new JLabel("Disciplinas");
		lblDisciplinaBoletim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDisciplinaBoletim.setBounds(37, 181, 371, 26);
		lblDisciplinaBoletim.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
		dadosBoletim.add(lblDisciplinaBoletim);
		
		JLabel lblNotaBoletim = new JLabel("Notas");
		lblNotaBoletim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNotaBoletim.setBounds(430, 181, 85, 26);
		lblNotaBoletim.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
		dadosBoletim.add(lblNotaBoletim);
		
		JLabel lblFaltaBoletim = new JLabel("Faltas");
		lblFaltaBoletim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFaltaBoletim.setBounds(537, 181, 85, 26);
		lblFaltaBoletim.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
		dadosBoletim.add(lblFaltaBoletim);
		
		JLabel disciplinaBoletim = new JLabel("");
		disciplinaBoletim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		disciplinaBoletim.setBounds(37, 227, 371, 26);
		dadosBoletim.add(disciplinaBoletim);
		
		JLabel notasBoletim = new JLabel("");
		notasBoletim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		notasBoletim.setBounds(430, 227, 85, 26);
		dadosBoletim.add(notasBoletim);
		
		JLabel faltasBoletim = new JLabel("");
		faltasBoletim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		faltasBoletim.setBounds(537, 227, 85, 26);
		dadosBoletim.add(faltasBoletim);
		
		JButton btnBuscarBoletim = new JButton("Buscar");
		btnBuscarBoletim.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            AlunoDAO alunoDao = new AlunoDAO();
		            int ra = Integer.parseInt(txtRaBoletim.getText());
		            aluno = alunoDao.consultar(ra);
		            
		            lblNomeAluno.setText(aluno.getNomeAluno());
		            // verifica se o aluno foi encontrado
		            if (aluno != null) {
		                System.out.println("Aluno encontrado: " + aluno.getNomeAluno());
		                lblNomeAluno.setText("Aluno: " + aluno.getNomeAluno());
		                lblRaAluno.setText("RA: " + String.valueOf(aluno.getRaAluno()));
		                
		                CursoDAO cursoDao = new CursoDAO();
		                Curso curso = cursoDao.consultar(aluno.getRaAluno());
		                
		                if (curso != null) {
		                    System.out.println("Curso encontrado: " + curso.getCurso());
		                    lblCursoBoletim.setText("Curso: " + curso.getCurso());
		                    
		                    NotasDAO notasDao = new NotasDAO();
		                    List<Notas> listaNotas = notasDao.consultar(aluno.getRaAluno());
		                    
		                    if (listaNotas != null && !listaNotas.isEmpty()) {
		                        System.out.println("Notas encontradas: " + listaNotas.size());
		                        for (Notas notas : listaNotas) {
		                            System.out.println("Disciplina: " + notas.getDisciplina() + ", Nota: " + notas.getNota() + ", Faltas: " + notas.getFaltas());
		                  
		                            disciplinaBoletim.setText(notas.getDisciplina() + "\n");
		                            notasBoletim.setText(String.valueOf(notas.getNota()) + "\n");
		                            faltasBoletim.setText(String.valueOf(notas.getFaltas()) + "\n");
		                        }
		                    } else {
		                        System.out.println("Nenhuma nota encontrada para o aluno.");
		                        disciplinaBoletim.setText("Nenhuma nota encontrada.");
		                        notasBoletim.setText("Nenhuma nota encontrada.");
		                        faltasBoletim.setText("Nenhuma falta encontrada.");
		                    }
		                } else {
		                    lblCursoAluno.setText("Curso não encontrado");
		                }
		            } else {
		                lblAluno.setText("Aluno não encontrado.");
		                lblCursoAluno.setText("Curso não encontrado");
		            }
		        } catch (Exception ex) {
		            // Exibe outros erros no console ou em um label
		            System.out.println("Erro: " + ex.getMessage());
		            ex.printStackTrace();
		        }
		    }
		});

		btnBuscarBoletim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBuscarBoletim.setBounds(363, 19, 85, 35);
		dadosBoletim.add(btnBuscarBoletim);
		
		JLabel lblSituacao = new JLabel("Situação");
		lblSituacao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSituacao.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
		lblSituacao.setBounds(647, 181, 85, 26);
		dadosBoletim.add(lblSituacao);
		
		JLabel situacaoBoletim = new JLabel("");
		situacaoBoletim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		situacaoBoletim.setBounds(647, 227, 85, 26);
		dadosBoletim.add(situacaoBoletim);
		
		setAllFieldsEnabled(false);
	}
	private void setAllFieldsEnabled(boolean enabled) {
		txtNome.setEnabled(enabled);
		txtRa.setEnabled(enabled);
		txtEmail.setEnabled(enabled);
		txtCpf.setEnabled(enabled);
		txtDataNascimento.setEnabled(enabled);
		txtEndereco.setEnabled(enabled);
		txtMunicipio.setEnabled(enabled);
		txtCelular.setEnabled(enabled);
		cmbBoxUf.setEnabled(enabled);
		cmbBoxCurso.setEnabled(enabled);
		cmbBoxCampus.setEnabled(enabled);
		rdMatutino.setEnabled(enabled);
		rdVespertino.setEnabled(enabled);
		rdNoturno.setEnabled(enabled);
		btnProximo.setEnabled(enabled);
		btnVoltar.setEnabled(enabled);
		btnSalvar.setEnabled(enabled);
	}
}
