package br.edu.fatec.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.edu.fatec.dao.AlunoDAO;
import br.edu.fatec.dao.CursoDAO;
import br.edu.fatec.dao.NotasDAO;
import br.edu.fatec.model.Aluno;
import br.edu.fatec.model.Curso;
import br.edu.fatec.model.Notas;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
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
    private JTextField txtNota;
    private JTextField txtFaltas;
    //============================================//
    private Aluno aluno;
    private AlunoDAO alunoDao;
    private Curso curso;
    private CursoDAO cursoDao;
    private Notas notas;
    private NotasDAO notasDao;
	
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
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Dados Pessoais", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 29, 65, 17);
		panel.add(lblNewLabel);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNome.setBounds(63, 20, 322, 37);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblRa = new JLabel("RA:");
		lblRa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRa.setBounds(416, 29, 65, 17);
		panel.add(lblRa);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(10, 110, 65, 17);
		panel.add(lblEmail);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCpf.setBounds(416, 110, 65, 17);
		panel.add(lblCpf);
		
		JLabel lblDataDeNascimento = new JLabel("Data de nascimento:");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDataDeNascimento.setBounds(10, 189, 160, 17);
		panel.add(lblDataDeNascimento);
		
		JLabel lblEndereo = new JLabel("Endereço:");
		lblEndereo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEndereo.setBounds(373, 189, 160, 17);
		panel.add(lblEndereo);
		
		txtRa = new JTextField();
		txtRa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtRa.setColumns(10);
		txtRa.setBounds(451, 20, 322, 37);
		panel.add(txtRa);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtEmail.setColumns(10);
		txtEmail.setBounds(63, 101, 322, 37);
		panel.add(txtEmail);
		
		txtCpf = new JTextField();
		txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCpf.setColumns(10);
		txtCpf.setBounds(451, 101, 322, 37);
		panel.add(txtCpf);
		
		txtDataNascimento = new JTextField();
		txtDataNascimento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtDataNascimento.setColumns(10);
		txtDataNascimento.setBounds(166, 179, 187, 37);
		panel.add(txtDataNascimento);
		
		txtEndereco = new JTextField();
		txtEndereco.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(451, 179, 322, 37);
		panel.add(txtEndereco);
		
		JLabel lblMunicpio = new JLabel("Município:");
		lblMunicpio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMunicpio.setBounds(10, 261, 92, 17);
		panel.add(lblMunicpio);
		
		txtMunicipio = new JTextField();
		txtMunicipio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtMunicipio.setColumns(10);
		txtMunicipio.setBounds(91, 252, 262, 37);
		panel.add(txtMunicipio);
		
		JLabel lblUf = new JLabel("UF:");
        lblUf.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblUf.setBounds(370, 263, 92, 17);
        panel.add(lblUf);

        cmbBoxUf = new JComboBox<>(); 
        cmbBoxUf.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cmbBoxUf.setModel(new DefaultComboBoxModel(new String[] {"  ", "SP", "RJ", "BA", "MG", "ES", "RS", "PA", "RN", "AC", "AM"}));
        cmbBoxUf.setBounds(402, 255, 92, 37);
        panel.add(cmbBoxUf);
		
		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCelular.setBounds(515, 263, 92, 17);
		panel.add(lblCelular);
		
		txtCelular = new JTextField();
		txtCelular.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCelular.setColumns(10);
		txtCelular.setBounds(577, 254, 196, 37);
		panel.add(txtCelular);
		
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
		panel.add(btnNovoCadastro);
		
		btnProximo = new JButton("Próximo");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnProximo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnProximo.setBounds(654, 322, 119, 37);
		panel.add(btnProximo);
		
		//============================== CURSO ===================================//
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Curso\r\n", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Curso:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(72, 24, 55, 35);
		panel_1.add(lblNewLabel_1);
		
		cmbBoxCurso = new JComboBox<>();
        cmbBoxCurso.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cmbBoxCurso.setModel(new DefaultComboBoxModel(new String[] {"  ", "Análise e Desenvolvimento de Sistemas", "Gestão Empresarial", "Logística"}));
        cmbBoxCurso.setBounds(137, 23, 560, 37);
        panel_1.add(cmbBoxCurso);
		
		JLabel lblNewLabel_1_1 = new JLabel("Campus:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(72, 109, 74, 35);
		panel_1.add(lblNewLabel_1_1);
		
		cmbBoxCampus = new JComboBox<>(); // Instanciado corretamente
        cmbBoxCampus.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cmbBoxCampus.setModel(new DefaultComboBoxModel(new String[] {"  ", "Guarulhos", "Carapicuíba", "São José dos Campos", "Sorocaba"}));
        cmbBoxCampus.setBounds(137, 108, 560, 37);
        panel_1.add(cmbBoxCampus); // Adicionado ao painel
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Período:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(72, 180, 74, 35);
		panel_1.add(lblNewLabel_1_1_1);
		
		rdMatutino = new JRadioButton("Matutino");
		rdMatutino.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdMatutino.setBounds(153, 189, 103, 21);
		panel_1.add(rdMatutino);
		
		rdVespertino = new JRadioButton("Vespertino");
		rdVespertino.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdVespertino.setBounds(354, 189, 103, 21);
		panel_1.add(rdVespertino);
		
		rdNoturno = new JRadioButton("Noturno");
		rdNoturno.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdNoturno.setBounds(594, 189, 103, 21);
		panel_1.add(rdNoturno);
		
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
		panel_1.add(btnSalvar);
		
		lblMensagem = new JLabel("");
		lblMensagem.setForeground(new Color(0, 0, 0));
		lblMensagem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMensagem.setBounds(10, 338, 196, 21);
		panel_1.add(lblMensagem);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVoltar.setBounds(72, 282, 85, 35);
		panel_1.add(btnVoltar);
		
		//=================== NOTAS E FALTAS ===================//
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Notas e Faltas", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("RA:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(10, 43, 45, 13);
		panel_2.add(lblNewLabel_2);
		
		txtRaBusca = new JTextField();
		txtRaBusca.setBounds(53, 37, 316, 29);
		panel_2.add(txtRaBusca);
		txtRaBusca.setColumns(10);
						
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNome.setBounds(10, 107, 57, 29);
		panel_2.add(lblNome);
		
		JLabel lblAluno = new JLabel("");
		lblAluno.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAluno.setBounds(66, 107, 313, 29);
		panel_2.add(lblAluno);
		
		JLabel lblCurso = new JLabel("Curso:");
		lblCurso.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCurso.setBounds(389, 107, 57, 29);
		panel_2.add(lblCurso);
		
		JLabel lblCursoAluno = new JLabel("");
		lblCursoAluno.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCursoAluno.setBounds(439, 107, 334, 29);
		panel_2.add(lblCursoAluno);
		
		JLabel lblDisciplina = new JLabel("Disciplina:");
		lblDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDisciplina.setBounds(10, 180, 74, 29);
		panel_2.add(lblDisciplina);
		
		JComboBox cmbBoxDisciplina = new JComboBox();
		cmbBoxDisciplina.setModel(new DefaultComboBoxModel(new String[] {"  ", "Programação Orientada a Objetos"}));
		cmbBoxDisciplina.setBounds(94, 180, 570, 35);
		panel_2.add(cmbBoxDisciplina);
		
		JLabel lblSemestre = new JLabel("Semestre:");
		lblSemestre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSemestre.setBounds(10, 250, 74, 29);
		panel_2.add(lblSemestre);
		
		JComboBox cmbBoxSemestre = new JComboBox();
		cmbBoxSemestre.setModel(new DefaultComboBoxModel(new String[] {"  ", "2022-1", "2022-2", "2023-1", "2023-2", "2024-1", "2024-2"}));
		cmbBoxSemestre.setBounds(94, 244, 203, 35);
		panel_2.add(cmbBoxSemestre);
		
		JLabel lblNota = new JLabel("Nota:");
		lblNota.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNota.setBounds(332, 250, 74, 29);
		panel_2.add(lblNota);
		
		txtNota = new JTextField();
		txtNota.setColumns(10);
		txtNota.setBounds(379, 250, 144, 29);
		panel_2.add(txtNota);
		
		JLabel lblFaltas = new JLabel("Faltas:");
		lblFaltas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFaltas.setBounds(561, 250, 74, 29);
		panel_2.add(lblFaltas);
		
		txtFaltas = new JTextField();
		txtFaltas.setColumns(10);
		txtFaltas.setBounds(610, 250, 144, 29);
		panel_2.add(txtFaltas);
						
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
                        
                        Curso curso = cursoDao.consultar(ra);
                        
                        if(curso != null) {
                        	lblCursoAluno.setText(curso.getCurso());
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
		panel_2.add(btnBuscarRa);
		
		JLabel lblMensagemNotas = new JLabel("");
		lblMensagemNotas.setForeground(Color.BLACK);
		lblMensagemNotas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMensagemNotas.setBounds(21, 345, 196, 21);
		panel_2.add(lblMensagemNotas);
		
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
			            notas.setNota(Double.parseDouble(txtNota.getText()));
			            notas.setFaltas(Integer.parseInt(txtFaltas.getText()));
			            notas.setRaAluno(aluno.getRaAluno());
			            
			            NotasDAO notasDao = new NotasDAO();
			            notasDao.salvar(notas);
			            lblMensagemNotas.setText("Salvo com Sucesso!");
		        	}	
		        	

				} catch (Exception el) {
					lblMensagemNotas.setText("Erro ao salvar");
					System.out.println(el.getMessage());
				}
			}
		});
		btnSalvarNotas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSalvarNotas.setBounds(669, 309, 85, 35);
		panel_2.add(btnSalvarNotas);
		
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
