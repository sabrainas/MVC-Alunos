package br.edu.fatec.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.text.MaskFormatter;

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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

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
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import javax.swing.JSeparator;
import javax.swing.JFormattedTextField;
import java.awt.Panel;
import java.awt.TextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;

public class TelaPrincipal extends JFrame {
	
	//====================Componente Formulario====================//
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTextField txtNome;
    private JTextField txtRa;
    private JTextField txtEmail;
    private JTextField txtEndereco;
    private JTextField txtMunicipio;
    private JTextField txtRaBusca;
    private JFormattedTextField formatedTxtCpf;
    private JFormattedTextField formatedTxtCelular;
    private JFormattedTextField formatedTxtDataNascimento;
    private JButton btnProximo;
    private JButton btnNovoCadastro;
    private JButton btnBuscar;
    private JButton btnSalvar;
    private JButton btnVoltar;
    private JButton btnAlterarDados;
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
    private JTextField txtRaBoletim;
    private JTextField txtNota2;
    private JMenuItem mnEditarNotas;
    private JTable table;
    private JTable tableBoletim;
    //====================	CLASSES ========================//
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

	public TelaPrincipal() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 816, 552);
		
		//============================ DADOS PESSOAIS ===============================//
		
		//INSTANCIANDO COMPONENTES DO FORMULARIO 
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuAluno = new JMenu("Aluno");
		menuAluno.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuAluno.setForeground(new Color(0, 0, 0));
		menuBar.add(menuAluno);
		
		JMenuItem mnEditarAluno = new JMenuItem("Alterar");
		mnEditarAluno.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
		mnEditarAluno.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuAluno.add(mnEditarAluno);
		
		JMenuItem mnConsultar = new JMenuItem("Consultar");
		mnConsultar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
		mnConsultar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuAluno.add(mnConsultar);
		
		JMenuItem mnExcluir = new JMenuItem("Excluir");
		mnExcluir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
		mnExcluir.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuAluno.add(mnExcluir);
		
		// BOTÃO MENU SALVAR ALUNO
		JMenuItem mnSalvarAluno = new JMenuItem("Salvar");
		mnSalvarAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		            // Verifica se os campos obrigatórios estão preenchidos
		            if (txtRa.getText().trim().isEmpty()) {
		            	lblMensagem.setText("O campo RA é obrigatório.");
		                return; 
		            }
		            if (txtNome.getText().trim().isEmpty()) {
		            	lblMensagem.setText("O campo Nome é obrigatório.");
		                return;
		            }
		            if (txtEmail.getText().trim().isEmpty()) {
		            	lblMensagem.setText("O campo Email é obrigatório.");
		                return;
		            }
		            if (formatedTxtDataNascimento.getText().trim().isEmpty()) {
		            	lblMensagem.setText("O campo Data de Nascimento é obrigatório.");
		                return;
		            }
		            if (txtEndereco.getText().trim().isEmpty()) {
		            	lblMensagem.setText("O campo Endereço é obrigatório.");
		                return;
		            }
		            if (formatedTxtCelular.getText().trim().isEmpty()) {
		            	lblMensagem.setText("O campo Celular é obrigatório.");
		                return;
		            }
		            if (txtMunicipio.getText().trim().isEmpty()) {
		            	lblMensagem.setText("O campo Município é obrigatório.");
		                return;
		            }
		            if (cmbBoxUf.getSelectedItem() == null || ((String) cmbBoxUf.getSelectedItem()).trim().isEmpty()) {
		            	lblMensagem.setText("O campo UF é obrigatório.");
		                return;
		            }
		            if (formatedTxtCpf.getText().trim().isEmpty()) {
		            	lblMensagem.setText("O campo CPF é obrigatório.");
		                return;
		            }
		            if(cmbBoxCurso.getSelectedItem() == null || ((String) cmbBoxCurso.getSelectedItem()).trim().isEmpty()) {
		            	lblMensagem.setText("O campo Curso é obrigatório");
		            	return;
		            }
		            if(cmbBoxCampus.getSelectedItem() == null || ((String) cmbBoxCampus.getSelectedItem()).trim().isEmpty()) {
		            	lblMensagem.setText("O campo Campus é obrigatório");
		            	return;
		            }
		            String periodo = null;
		            if (!rdMatutino.isSelected() && !rdVespertino.isSelected() && !rdNoturno.isSelected()) {
		                lblMensagem.setText("O campo Período é obrigatório");
		                return;
		            } 
		        	//inicializa o DAO para realizar a consulta
		        	CursoDAO cursoDao = new CursoDAO();
		            // cria o objeto para pegar os dados da tela
		            aluno = new Aluno();
		            		            	            
		            // Defina os valores para o curso antes de salvá-lo
		            String nomeCurso = (String) cmbBoxCurso.getSelectedItem();
		            String campus = (String) cmbBoxCampus.getSelectedItem();
		            
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
		            aluno.setDataNascimento(formatedTxtDataNascimento.getText());
		            aluno.setEnderecoAluno(txtEndereco.getText());
		            aluno.setCelularAluno(formatedTxtCelular.getText());
		            aluno.setMunicipioAluno(txtMunicipio.getText());
		            aluno.setUfAluno((String) cmbBoxUf.getSelectedItem());
		            aluno.setCpfAluno(formatedTxtCpf.getText());

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
		mnSalvarAluno.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		mnSalvarAluno.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuAluno.add(mnSalvarAluno);
		
		JSeparator separator = new JSeparator();
		menuAluno.add(separator);
		
		JMenuItem mnSairAluno = new JMenuItem("Sair");
		mnSairAluno.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
		mnSairAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "Você realmente deseja sair?", "Sair", JOptionPane.YES_NO_OPTION);
		        if (confirm == JOptionPane.YES_OPTION) {
		            System.exit(0); 
		        }
			}
		});
		mnSairAluno.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuAluno.add(mnSairAluno);
		
		
		JMenu mnNewMenu = new JMenu("Ajudar");
		mnNewMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Informações do Menu");
			}
		});
		
		JMenu menuNotasFaltas = new JMenu("Notas e Faltas");
		menuNotasFaltas.setForeground(new Color(0, 0, 0));
		menuNotasFaltas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(menuNotasFaltas);
		
		mnEditarNotas = new JMenuItem("Alterar");

		mnEditarNotas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuNotasFaltas.add(mnEditarNotas);
		
		JMenuItem mnSalvarNotas = new JMenuItem("Salvar");
		mnSalvarNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnSalvarNotas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuNotasFaltas.add(mnSalvarNotas);
		
		JMenuItem mnConsultarNotas = new JMenuItem("Consultar");
		
		mnConsultarNotas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuNotasFaltas.add(mnConsultarNotas);
		
		JMenuItem mnExcluirNotas = new JMenuItem("Excluir");

		mnExcluirNotas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuNotasFaltas.add(mnExcluirNotas);
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
		lblEndereo.setBounds(373, 189, 79, 17);
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
								
		txtEndereco = new JTextField();
		txtEndereco.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(451, 179, 322, 37);
		dadosPessoais.add(txtEndereco);
		
		formatedTxtDataNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
		formatedTxtDataNascimento.setBounds(166, 179, 195, 37);
		dadosPessoais.add(formatedTxtDataNascimento);
		
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
		
		formatedTxtCelular = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
		formatedTxtCelular.setBounds(578, 252, 195, 37);
		dadosPessoais.add(formatedTxtCelular);
				
		btnProximo = new JButton("");
		btnProximo.setIcon(new ImageIcon("C:\\Users\\wrmi\\Documents\\sabrainas\\FATEC\\PROGRAMAÇÃO ORIENTADA A OBJETOS\\MVC\\src\\br\\edu\\fatec\\view\\seta-direita (1).png"));
		btnProximo.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnProximo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnProximo.setBounds(654, 322, 119, 37);
		dadosPessoais.add(btnProximo);
		
		JLabel lblMensagemAluno = new JLabel("");
		lblMensagemAluno.setForeground(Color.BLACK);
		lblMensagemAluno.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMensagemAluno.setBounds(10, 389, 624, 21);
		dadosPessoais.add(lblMensagemAluno);
		
		formatedTxtCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		formatedTxtCpf.setBounds(451, 101, 322, 37);
		dadosPessoais.add(formatedTxtCpf);
		
		
		JButton btnBuscarAluno = new JButton("Consultar");
		btnBuscarAluno.setIcon(new ImageIcon("C:\\Users\\wrmi\\Documents\\sabrainas\\FATEC\\PROGRAMAÇÃO ORIENTADA A OBJETOS\\MVC\\src\\br\\edu\\fatec\\view\\procurar.png"));
		btnBuscarAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setAllFieldsEnabled(true);
				
				try {
					
                    int ra = Integer.parseInt(txtRa.getText());
                    AlunoDAO alunoDao = new AlunoDAO();
                    Aluno aluno = alunoDao.consultar(ra);
                    
                    if (aluno != null) {
                        txtNome.setText(aluno.getNomeAluno());
                        txtEmail.setText(aluno.getEmailAluno());
                        formatedTxtCpf.setText(aluno.getCpfAluno());
                        formatedTxtDataNascimento.setText(aluno.getDataNascimento());
                        txtEndereco.setText(aluno.getEnderecoAluno());
                        txtMunicipio.setText(aluno.getMunicipioAluno());
                        cmbBoxUf.setSelectedItem((String) aluno.getUfAluno());
                        formatedTxtCelular.setText(aluno.getCelularAluno());
                        
                        CursoDAO cursoDao = new CursoDAO();
                        Curso curso = cursoDao.consultar(aluno.getRaAluno());
                        if(curso != null) {
                        	cmbBoxCurso.setSelectedItem((String) curso.getCurso());
                        	cmbBoxCampus.setSelectedItem((String) curso.getCampus());
                        	
                        	if (curso.getPeriodo().equals(rdMatutino.getText())) {
                                rdMatutino.setSelected(true);
                            } else if (curso.getPeriodo().equals(rdVespertino.getText())) {
                                rdVespertino.setSelected(true);
                            } else if (curso.getPeriodo().equals(rdNoturno.getText())) {
                                rdNoturno.setSelected(true);
                            }
                        	
                        }
                        
                    } else {
                        lblMensagemAluno.setText("Aluno não encontrado");
                    }
                } catch (Exception ex) {
                	lblMensagemAluno.setText("Erro ao consultar");
                }
				
			}
		});
		btnBuscarAluno.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBuscarAluno.setBounds(10, 322, 119, 37);
		dadosPessoais.add(btnBuscarAluno);
		
		mnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllFieldsEnabled(true);
				
				try {
					
                    int ra = Integer.parseInt(txtRa.getText());
                    AlunoDAO alunoDao = new AlunoDAO();
                    Aluno aluno = alunoDao.consultar(ra);
                    
                    if (aluno != null) {
                        txtNome.setText(aluno.getNomeAluno());
                        txtEmail.setText(aluno.getEmailAluno());
                        formatedTxtCpf.setText(aluno.getCpfAluno());
                        formatedTxtDataNascimento.setText(aluno.getDataNascimento());
                        txtEndereco.setText(aluno.getEnderecoAluno());
                        txtMunicipio.setText(aluno.getMunicipioAluno());
                        cmbBoxUf.setSelectedItem((String) aluno.getUfAluno());
                        formatedTxtCelular.setText(aluno.getCelularAluno());
                        
                        CursoDAO cursoDao = new CursoDAO();
                        Curso curso = cursoDao.consultar(aluno.getRaAluno());
                        if(curso != null) {
                        	cmbBoxCurso.setSelectedItem((String) curso.getCurso());
                        	cmbBoxCampus.setSelectedItem((String) curso.getCampus());
                        	
                        	if (curso.getPeriodo().equals(rdMatutino.getText())) {
                                rdMatutino.setSelected(true);
                            } else if (curso.getPeriodo().equals(rdVespertino.getText())) {
                                rdVespertino.setSelected(true);
                            } else if (curso.getPeriodo().equals(rdNoturno.getText())) {
                                rdNoturno.setSelected(true);
                            }
                        	
                        }
                        
                    } else {
                        lblMensagemAluno.setText("Aluno não encontrado");
                    }
                } catch (Exception ex) {
                	lblMensagemAluno.setText("Erro ao consultar");
                }
			}
		});
		
		mnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        try {
		            // Obtém o RA do campo de texto
		            int raAluno = Integer.parseInt(txtRa.getText());
		            
		            // Consulta o aluno no banco de dados usando o RA
		            AlunoDAO alunoDao = new AlunoDAO();
		            Aluno aluno = alunoDao.consultar(raAluno); // Aqui você obtém o aluno
		            
		            // Verifica se o aluno foi encontrado
		            if (aluno != null) {
		                // Obtém o nome do aluno
		                String nomeAluno = aluno.getNomeAluno();
		                
		                // Exibe um diálogo de confirmação com o nome do aluno
		                int confirmarExclusao = JOptionPane.showConfirmDialog(null, 
		                    "Tem certeza que deseja excluir o aluno " + nomeAluno + "?", 
		                    "Confirmação da Exclusão", 
		                    JOptionPane.YES_NO_OPTION);
		                
		                // Se o usuário confirmar a exclusão
		                if (confirmarExclusao == JOptionPane.YES_OPTION) {
		                    // Exclui o aluno
		                    alunoDao.excluir(raAluno);
		                    
		                    // Exibe mensagem de sucesso
		                    lblMensagemAluno.setText("Aluno " + nomeAluno + " excluído com sucesso!");
		                } else {
		                	lblMensagemAluno.setText("Exclusão do aluno " + nomeAluno + " cancelada.");
		                }
		            } else {
		            	lblMensagemAluno.setText("Aluno com RA " + raAluno + " não encontrado.");
		            }
		        } catch (NumberFormatException nfe) {
		        	lblMensagemAluno.setText("RA inválido");
		            System.out.println("Erro ao converter RA: " + nfe.getMessage());
		        } catch (Exception ex) {
		        	lblMensagemAluno.setText("Erro ao excluir o aluno");
		            System.out.println(ex.getMessage());
		        }
			}
		});
		
		btnAlterarDados = new JButton("Alterar");
		btnAlterarDados.setIcon(new ImageIcon("C:\\Users\\wrmi\\Documents\\sabrainas\\FATEC\\PROGRAMAÇÃO ORIENTADA A OBJETOS\\MVC\\src\\br\\edu\\fatec\\view\\troca.png"));
		btnAlterarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					aluno = new Aluno();
		            aluno.setRaAluno(Integer.parseInt(txtRa.getText()));
		            aluno.setNomeAluno(txtNome.getText());
		            aluno.setEmailAluno(txtEmail.getText());
		            aluno.setDataNascimento(formatedTxtDataNascimento.getText());
		            aluno.setEnderecoAluno(txtEndereco.getText());
		            aluno.setCelularAluno(formatedTxtCelular.getText());
		            aluno.setMunicipioAluno(txtMunicipio.getText());
		            aluno.setUfAluno((String) cmbBoxUf.getSelectedItem());
		            aluno.setCpfAluno(formatedTxtCpf.getText());
                    
		            AlunoDAO alunoDao = new AlunoDAO();
		            alunoDao.atualizar(aluno);

		            lblMensagemAluno.setText("Dados atualizados com sucesso!");
                } catch (Exception ex) {
                    lblMensagemAluno.setText("Erro ao atualizar os dados");
                    System.out.println(ex.getMessage());
                }
			}
		});
		btnAlterarDados.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAlterarDados.setBounds(155, 322, 119, 37);
		dadosPessoais.add(btnAlterarDados);
		
		mnEditarAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					aluno = new Aluno();
		            // Verifica se os campos obrigatórios estão preenchidos
		            if (txtRa.getText().trim().isEmpty()) {
		                lblMensagemAluno.setText("O campo RA é obrigatório.");
		                return; // Sai do método para evitar a continuação do processo
		            }
		            if (txtNome.getText().trim().isEmpty()) {
		                lblMensagemAluno.setText("O campo Nome é obrigatório.");
		                return;
		            }
		            if (txtEmail.getText().trim().isEmpty()) {
		                lblMensagemAluno.setText("O campo Email é obrigatório.");
		                return;
		            }
		            if (formatedTxtDataNascimento.getText().trim().isEmpty()) {
		                lblMensagemAluno.setText("O campo Data de Nascimento é obrigatório.");
		                return;
		            }
		            if (txtEndereco.getText().trim().isEmpty()) {
		                lblMensagemAluno.setText("O campo Endereço é obrigatório.");
		                return;
		            }
		            if (formatedTxtCelular.getText().trim().isEmpty()) {
		                lblMensagemAluno.setText("O campo Celular é obrigatório.");
		                return;
		            }
		            if (txtMunicipio.getText().trim().isEmpty()) {
		                lblMensagemAluno.setText("O campo Município é obrigatório.");
		                return;
		            }
		            if (cmbBoxUf.getSelectedItem() == null || ((String) cmbBoxUf.getSelectedItem()).trim().isEmpty()) {
		                lblMensagemAluno.setText("O campo UF é obrigatório.");
		                return;
		            }
		            if (formatedTxtCpf.getText().trim().isEmpty()) {
		                lblMensagemAluno.setText("O campo CPF é obrigatório.");
		                return;
		            }					
		            aluno.setNomeAluno(txtNome.getText());
		            aluno.setEmailAluno(txtEmail.getText());
		            aluno.setDataNascimento(formatedTxtDataNascimento.getText());
		            aluno.setEnderecoAluno(txtEndereco.getText());
		            aluno.setCelularAluno(formatedTxtCelular.getText());
		            aluno.setMunicipioAluno(txtMunicipio.getText());
		            aluno.setUfAluno((String) cmbBoxUf.getSelectedItem());
		            aluno.setCpfAluno(formatedTxtCpf.getText());
                    
		            AlunoDAO alunoDao = new AlunoDAO();
		            alunoDao.atualizar(aluno);

		            lblMensagemAluno.setText("Dados atualizados com sucesso!");
                } catch (Exception ex) {
                    lblMensagemAluno.setText("Erro ao atualizar os dados");
                    System.out.println(ex.getMessage());
                }
			}
		});
		
		
		
		JButton btnExcluirAluno = new JButton("Excluir");
		btnExcluirAluno.setIcon(new ImageIcon("C:\\Users\\wrmi\\Documents\\sabrainas\\FATEC\\PROGRAMAÇÃO ORIENTADA A OBJETOS\\MVC\\src\\br\\edu\\fatec\\view\\excluir.png"));
		btnExcluirAluno.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // Obtém o RA do campo de texto
		            int raAluno = Integer.parseInt(txtRa.getText());
		            
		            // Consulta o aluno no banco de dados usando o RA
		            AlunoDAO alunoDao = new AlunoDAO();
		            Aluno aluno = alunoDao.consultar(raAluno); // Aqui você obtém o aluno
		            
		            // Verifica se o aluno foi encontrado
		            if (aluno != null) {
		                // Obtém o nome do aluno
		                String nomeAluno = aluno.getNomeAluno();
		                
		                // Exibe um diálogo de confirmação com o nome do aluno
		                int confirmarExclusao = JOptionPane.showConfirmDialog(null, 
		                    "Tem certeza que deseja excluir o aluno " + nomeAluno + "?", 
		                    "Confirmação da Exclusão", 
		                    JOptionPane.YES_NO_OPTION);
		                
		                // Se o usuário confirmar a exclusão
		                if (confirmarExclusao == JOptionPane.YES_OPTION) {
		                    // Exclui o aluno
		                    alunoDao.excluir(raAluno);
		                    
		                    // Exibe mensagem de sucesso
		                    lblMensagemAluno.setText("Aluno " + nomeAluno + " excluído com sucesso!");
		                } else {
		                	lblMensagemAluno.setText("Exclusão do aluno " + nomeAluno + " cancelada.");
		                }
		            } else {
		            	lblMensagemAluno.setText("Aluno com RA " + raAluno + " não encontrado.");
		            }
		        } catch (NumberFormatException nfe) {
		        	lblMensagemAluno.setText("RA inválido");
		            System.out.println("Erro ao converter RA: " + nfe.getMessage());
		        } catch (Exception ex) {
		        	lblMensagemAluno.setText("Erro ao excluir o aluno");
		            System.out.println(ex.getMessage());
		        }
		    }
		});
		btnExcluirAluno.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnExcluirAluno.setBounds(300, 322, 119, 37);
		dadosPessoais.add(btnExcluirAluno);
				
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
		btnSalvar.setIcon(new ImageIcon("C:\\Users\\aluno_lab3\\Downloads\\salve-.png"));
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSalvar.setBounds(612, 282, 103, 35);
		dadosCurso.add(btnSalvar);
		btnSalvar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // Verifica se os campos obrigatórios estão preenchidos
		            if (txtRa.getText().trim().isEmpty()) {
		            	lblMensagem.setText("O campo RA é obrigatório.");
		                return; // Sai do método para evitar a continuação do processo
		            }
		            if (txtNome.getText().trim().isEmpty()) {
		            	lblMensagem.setText("O campo Nome é obrigatório.");
		                return;
		            }
		            if (txtEmail.getText().trim().isEmpty()) {
		            	lblMensagem.setText("O campo Email é obrigatório.");
		                return;
		            }
		            if (formatedTxtDataNascimento.getText().trim().isEmpty()) {
		            	lblMensagem.setText("O campo Data de Nascimento é obrigatório.");
		                return;
		            }
		            if (txtEndereco.getText().trim().isEmpty()) {
		            	lblMensagem.setText("O campo Endereço é obrigatório.");
		                return;
		            }
		            if (formatedTxtCelular.getText().trim().isEmpty()) {
		            	lblMensagem.setText("O campo Celular é obrigatório.");
		                return;
		            }
		            if (txtMunicipio.getText().trim().isEmpty()) {
		            	lblMensagem.setText("O campo Município é obrigatório.");
		                return;
		            }
		            if (cmbBoxUf.getSelectedItem() == null || ((String) cmbBoxUf.getSelectedItem()).trim().isEmpty()) {
		            	lblMensagem.setText("O campo UF é obrigatório.");
		                return;
		            }
		            if (formatedTxtCpf.getText().trim().isEmpty()) {
		            	lblMensagem.setText("O campo CPF é obrigatório.");
		                return;
		            }
		            if(cmbBoxCurso.getSelectedItem() == null || ((String) cmbBoxCurso.getSelectedItem()).trim().isEmpty()) {
		            	lblMensagem.setText("O campo Curso é obrigatório");
		            	return;
		            }
		            if(cmbBoxCampus.getSelectedItem() == null || ((String) cmbBoxCampus.getSelectedItem()).trim().isEmpty()) {
		            	lblMensagem.setText("O campo Campus é obrigatório");
		            	return;
		            }
		            String periodo = null;
		            if (!rdMatutino.isSelected() && !rdVespertino.isSelected() && !rdNoturno.isSelected()) {
		                lblMensagem.setText("O campo Período é obrigatório");
		                return;
		            } 
		        	//inicializa o DAO para realizar a consulta
		        	CursoDAO cursoDao = new CursoDAO();
		            // cria o objeto para pegar os dados da tela
		            aluno = new Aluno();
		            		            	            
		            // Defina os valores para o curso antes de salvá-lo
		            String nomeCurso = (String) cmbBoxCurso.getSelectedItem();
		            String campus = (String) cmbBoxCampus.getSelectedItem();
		            
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
		            aluno.setDataNascimento(formatedTxtDataNascimento.getText());
		            aluno.setEnderecoAluno(txtEndereco.getText());
		            aluno.setCelularAluno(formatedTxtCelular.getText());
		            aluno.setMunicipioAluno(txtMunicipio.getText());
		            aluno.setUfAluno((String) cmbBoxUf.getSelectedItem());
		            aluno.setCpfAluno(formatedTxtCpf.getText());

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
		
		JButton btnNovoCadastro = new JButton("Novo");
		btnNovoCadastro.setIcon(new ImageIcon("C:\\Users\\wrmi\\Documents\\sabrainas\\FATEC\\PROGRAMAÇÃO ORIENTADA A OBJETOS\\MVC\\src\\br\\edu\\fatec\\view\\salve-.png"));
		btnNovoCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllFieldsEnabled(true);
				txtRa.setText(null);
				txtNome.setText(null);
				txtEmail.setText(null);
				formatedTxtDataNascimento.setText(null);
				txtEndereco.setText(null);
				formatedTxtCelular.setText(null);
				formatedTxtCpf.setText(null);
				cmbBoxUf.setSelectedIndex(-1);
				txtMunicipio.setText(null);
				cmbBoxCurso.setSelectedIndex(-1);
				cmbBoxCampus.setSelectedIndex(-1);
			}
		});
		btnNovoCadastro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNovoCadastro.setBounds(515, 322, 119, 37);
		dadosPessoais.add(btnNovoCadastro);
								
		lblMensagem = new JLabel("");
		lblMensagem.setForeground(new Color(0, 0, 0));
		lblMensagem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMensagem.setBounds(10, 338, 581, 21);
		dadosCurso.add(lblMensagem);
		
		btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon("C:\\Users\\wrmi\\Documents\\sabrainas\\FATEC\\PROGRAMAÇÃO ORIENTADA A OBJETOS\\MVC\\src\\br\\edu\\fatec\\view\\seta-esquerda.png"));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVoltar.setBounds(72, 282, 125, 35);
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
						
		
		
		JLabel lblMensagemNotas = new JLabel("");
		lblMensagemNotas.setForeground(Color.BLACK);
		lblMensagemNotas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMensagemNotas.setBounds(10, 384, 733, 21);
		notasFaltas.add(lblMensagemNotas);
		
		JButton btnSalvarNotas = new JButton("Salvar");
		btnSalvarNotas.setIcon(new ImageIcon("C:\\Users\\wrmi\\Documents\\sabrainas\\FATEC\\PROGRAMAÇÃO ORIENTADA A OBJETOS\\MVC\\src\\br\\edu\\fatec\\view\\salve-.png"));
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
		btnSalvarNotas.setBounds(649, 309, 105, 35);
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
					double nota1 = Double.parseDouble(txtNota1.getText());
		            double nota2 = Double.parseDouble(txtNota2.getText());
		            
		            // Exemplo de cálculo (soma, neste caso)
		            double resultadoMedia = (nota1 + nota2) / 2;
		            
					
					String resultado = notasDao.calcularMedia(listaNotas);
					lblMostrarMedia.setText(resultado);
					lblMostrarMedia.setText(String.valueOf(resultadoMedia));
				}catch(Exception ex) {
					System.out.println("Erro " + ex.getMessage());
				}
			}
		});
		btnCalcular.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCalcular.setBounds(465, 247, 85, 35);
		notasFaltas.add(btnCalcular);
		
		JButton btnBuscarRa = new JButton("Buscar");
		btnBuscarRa.setIcon(new ImageIcon("C:\\Users\\wrmi\\Documents\\sabrainas\\FATEC\\PROGRAMAÇÃO ORIENTADA A OBJETOS\\MVC\\src\\br\\edu\\fatec\\view\\procurar.png"));
		btnBuscarRa.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            AlunoDAO alunoDao = new AlunoDAO();
		            int ra = Integer.parseInt(txtRaBusca.getText());
		            aluno = alunoDao.consultar(ra);
		            
		            
		            if (aluno != null) {
		                lblAluno.setText(aluno.getNomeAluno());
		                
		                CursoDAO cursoDao = new CursoDAO();
		                Curso curso = cursoDao.consultar(aluno.getRaAluno());
		                
		                if (curso != null) {
		                    lblCursoAluno.setText(curso.getCurso());
		                    
		                    List<Disciplina> disciplinas = cursoDao.getDisciplinas(curso.getIdCurso());
		                    cmbBoxDisciplina.removeAllItems();
		                    
		                    for (Disciplina disciplina : disciplinas) {
		                        cmbBoxDisciplina.addItem(disciplina.getNomeDisciplina());
		                    }
		                    
		                    NotasDAO notasDao = new NotasDAO();
		                    List<Notas> listaNotas = notasDao.consultar(aluno.getRaAluno());
		                    
		                    if (listaNotas != null && !listaNotas.isEmpty()) {
		                        for (Notas notas : listaNotas) {
		                            cmbBoxSemestre.setSelectedItem(notas.getSemestre());
		                            txtFaltas.setText(String.valueOf(notas.getFaltas()));
		                            txtNota1.setText(String.valueOf(notas.getNota()));
		                            txtNota2.setText(String.valueOf(notas.getNota2()));
		                            lblMostrarMedia.setText(String.valueOf(notas.getMedia()));
		                            lblMensagemNotas.setText(null);
		                        }
		                    } else {
		                        
		                        cmbBoxSemestre.setSelectedIndex(-1);
		                        txtFaltas.setText("");
		                        txtNota1.setText("");
		                        txtNota2.setText("");
		                        lblMostrarMedia.setText("");
		                        lblMensagemNotas.setText("Nenhuma nota encontrada.");
		                    }
		                } else {
		                    lblCursoAluno.setText("Curso não encontrado");
		                }
		            } else {
		                lblAluno.setText("Aluno não encontrado.");
		                lblCursoAluno.setText("Curso não encontrado");
		                
		                cmbBoxDisciplina.removeAllItems();
		                cmbBoxSemestre.setSelectedIndex(-1);
		                txtFaltas.setText("");
		                txtNota1.setText("");
		                txtNota2.setText("");
		                lblMostrarMedia.setText("");
		                lblMensagemNotas.setText("");
		            }
		        } catch (Exception ex) {
		            System.out.println("Erro: " + ex.getMessage());
		            ex.printStackTrace(); 
		        }
		    }
		});

		btnBuscarRa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBuscarRa.setBounds(379, 34, 121, 35);
		notasFaltas.add(btnBuscarRa);
		
		mnConsultarNotas.addActionListener(new ActionListener() {
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
                        	
                        	NotasDAO notasDao = new NotasDAO();
                        	List<Notas> listaNotas = notasDao.consultar(aluno.getRaAluno());
                        	
                        	if(listaNotas != null && !listaNotas.isEmpty()) {
                        		int somaNotas = 0;
                        		for(Notas notas : listaNotas) {
                        			cmbBoxSemestre.setSelectedItem(notas.getSemestre());
                        			txtFaltas.setText(String.valueOf(notas.getFaltas()));
                        			txtNota1.setText(String.valueOf(notas.getNota()));
                        			txtNota2.setText(String.valueOf(notas.getNota2()));
                        			lblMostrarMedia.setText(String.valueOf(notas.getMedia()));
                        			lblMensagemNotas.setText(null);
                        		}
                        	}else {
                        		cmbBoxSemestre.setSelectedIndex(-1);
                                txtFaltas.setText("");
                                txtNota1.setText("");
                                txtNota2.setText("");
                                lblMostrarMedia.setText("");
                        	}
                        }else {
                        	lblCursoAluno.setText("Curso não encontrado");
                        }
                    } else {
                        lblAluno.setText("Aluno não encontrado.");
                        lblCursoAluno.setText("Curso não encontrado");
                    }
					
		        } catch (Exception ex) {
		            
		            System.out.println("Erro: " + ex.getMessage());
		        }
			}
		});
		
		mnExcluirNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					AlunoDAO alunoDao = new AlunoDAO();
					NotasDAO notasDao = new NotasDAO();
					int raAluno = Integer.parseInt(txtRaBusca.getText());
					
					Aluno aluno = alunoDao.consultar(raAluno);
										
					if(aluno != null) {
						String nomeAluno = aluno.getNomeAluno();
						List<Notas> listaNotas = notasDao.consultar(raAluno);

						if(listaNotas != null && !listaNotas.isEmpty()) {
							for(Notas nota : listaNotas) {
								notasDao.excluir(nota);
							}
			                int confirmarExclusao = JOptionPane.showConfirmDialog(null, 
			                    "Tem certeza que deseja excluir notas do aluno " + nomeAluno + "?", 
			                    "Confirmação da Exclusão", 
			                    JOptionPane.YES_NO_OPTION);
			                
			                if (confirmarExclusao == JOptionPane.YES_OPTION) {
			                	for (Notas nota : listaNotas) {
		                            notasDao.excluir(nota); 
		                        }
			                    			                    
			                	lblMensagemNotas.setText("Notas excluídas com sucesso para o aluno " + nomeAluno + "!");
			                } else {
			                	lblMensagemNotas.setText("Exclusão de notas do aluno " + nomeAluno + " cancelada.");
			                }
						}else {
							lblMensagemNotas.setText("Nehuma nota encontrada para excluir");
						}
					}
					
				}catch(Exception ex) {
					lblMensagemNotas.setText("Erro ao excluir notas");
					System.out.println(ex.getMessage());
				}
			}
		});
		
		cmbBoxDisciplina.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            String disciplinaSelecionada = (String) cmbBoxDisciplina.getSelectedItem();
		            NotasDAO notasDao = new NotasDAO();
		            
		            Notas notas = notasDao.consultarNotasPorDisciplina(aluno.getRaAluno(), disciplinaSelecionada);
		            
		            if (notas != null) {
		                txtFaltas.setText(String.valueOf(notas.getFaltas()));
		                txtNota1.setText(String.valueOf(notas.getNota()));
		                txtNota2.setText(String.valueOf(notas.getNota2()));
		                lblMostrarMedia.setText(String.valueOf(notas.getMedia()));
		                cmbBoxSemestre.setSelectedItem(notas.getSemestre());
		            } else {
		                txtFaltas.setText("");
		                txtNota1.setText("");
		                txtNota2.setText("");
		                lblMostrarMedia.setText("");
		                cmbBoxSemestre.setSelectedIndex(-1);
		            }
		        } catch (Exception ex) {
		            System.out.println("Erro: " + ex.getMessage());
		        }
		    }
		});
		
		JButton btnExcluirNotas = new JButton("Excluir");
		btnExcluirNotas.setIcon(new ImageIcon("C:\\Users\\wrmi\\Documents\\sabrainas\\FATEC\\PROGRAMAÇÃO ORIENTADA A OBJETOS\\MVC\\src\\br\\edu\\fatec\\view\\excluir.png"));
		btnExcluirNotas.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            AlunoDAO alunoDao = new AlunoDAO();
		            NotasDAO notasDao = new NotasDAO();
		            int raAluno = Integer.parseInt(txtRaBusca.getText());
		            
		            Aluno aluno = alunoDao.consultar(raAluno);
		            
		            if (aluno != null) {
		                String nomeAluno = aluno.getNomeAluno();
		                List<Notas> listaNotas = notasDao.consultar(raAluno);

		                if (listaNotas != null && !listaNotas.isEmpty()) {
		                    int confirmarExclusao = JOptionPane.showConfirmDialog(null, 
		                        "Tem certeza que deseja excluir notas do aluno " + nomeAluno + "?", 
		                        "Confirmação da Exclusão", 
		                        JOptionPane.YES_NO_OPTION);
		                    
		                    if (confirmarExclusao == JOptionPane.YES_OPTION) {
		                        for (Notas nota : listaNotas) {
		                            notasDao.excluir(nota);
		                        }
		                        lblMensagemNotas.setText("Notas excluídas com sucesso para o aluno " + nomeAluno + "!");
		                    } else {
		                        lblMensagemNotas.setText("Exclusão de notas do aluno " + nomeAluno + " cancelada.");
		                    }
		                } else {
		                    lblMensagemNotas.setText("Nenhuma nota encontrada para excluir");
		                }
		            } else {
		                lblMensagemNotas.setText("Aluno não encontrado.");
		            }
		        } catch (Exception ex) {
		            lblMensagemNotas.setText("Erro ao excluir notas");
		            System.out.println(ex.getMessage());
		        }
		    }
		});

		btnExcluirNotas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExcluirNotas.setBounds(534, 309, 105, 35);
		notasFaltas.add(btnExcluirNotas);
		
		mnEditarNotas.addActionListener(new ActionListener() {
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
		
		tableBoletim = new JTable();
		tableBoletim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableBoletim.setSurrendersFocusOnKeystroke(true);
		tableBoletim.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Disciplina", "Média", "Faltas", "Situação"
			}
		));
		tableBoletim.getColumnModel().getColumn(0).setPreferredWidth(143);
		tableBoletim.getColumnModel().getColumn(1).setPreferredWidth(80);
		tableBoletim.getColumnModel().getColumn(2).setPreferredWidth(80);
		tableBoletim.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableBoletim.setRowHeight(30);
		JScrollPane scrollPane = new JScrollPane(tableBoletim);
		scrollPane.setBounds(37, 246, 682, 120); 
		dadosBoletim.add(scrollPane);
		
		JButton btnBuscarBoletim = new JButton("Buscar");
		btnBuscarBoletim.setIcon(new ImageIcon("C:\\Users\\wrmi\\Documents\\sabrainas\\FATEC\\PROGRAMAÇÃO ORIENTADA A OBJETOS\\MVC\\src\\br\\edu\\fatec\\view\\procurar.png"));
		btnBuscarBoletim.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            AlunoDAO alunoDao = new AlunoDAO();
		            int ra = Integer.parseInt(txtRaBoletim.getText());
		            aluno = alunoDao.consultar(ra);
		            
		            // Verifica se o aluno foi encontrado
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
		                    
		                    DefaultTableModel tableModel = (DefaultTableModel) tableBoletim.getModel();
		                    tableModel.setRowCount(0);
		                    
		                    int somaNotas = 0;

		                    if (listaNotas != null && !listaNotas.isEmpty()) {
		                        System.out.println("Notas encontradas: " + listaNotas.size());

		                        for (Notas notas : listaNotas) {
		                            String disciplina = notas.getDisciplina();
		                            System.out.println("Disciplina: " + notas.getDisciplina() + ", Nota: " + notas.getNota() + ", Faltas: " + notas.getFaltas());
		                            
		                            String situacao = notas.getMedia() >= 6 ? "Aprovado" : "Reprovado";
		                            
		                            tableModel.addRow(new Object[] {
		                                disciplina,
		                                notas.getMedia(),
		                                notas.getFaltas(),
		                                situacao
		                            });
		                            somaNotas += notas.getMedia();
		                        }
		                        System.out.println("Table Model Row Count: " + tableModel.getRowCount());

		                    } else {
		                        System.out.println("Nenhuma nota encontrada para o aluno.");
		                        tableModel.addRow(new Object[]{"Nenhuma nota encontrada.", "", "", ""});
		                    }
		                    
		                } else {
		                    lblCursoAluno.setText("Curso não encontrado");
		                }
		            } else {
		                
		                lblNomeAluno.setText("Aluno não encontrado.");
		                lblRaAluno.setText(""); 
		                lblCursoBoletim.setText("Curso não encontrado");
		                
		                DefaultTableModel tableModel = (DefaultTableModel) tableBoletim.getModel();
		                tableModel.setRowCount(0);
		            }
		        } catch (Exception ex) {
		            
		            System.out.println("Erro: " + ex.getMessage());
		            ex.printStackTrace();
		        }

		        dadosBoletim.revalidate();
		        dadosBoletim.repaint();
		    }
		});


		btnBuscarBoletim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBuscarBoletim.setBounds(363, 19, 115, 35);
		dadosBoletim.add(btnBuscarBoletim);
			
		
		setAllFieldsEnabled(false);
	}
	private void setAllFieldsEnabled(boolean enabled) {
		txtNome.setEnabled(enabled);
		txtEmail.setEnabled(enabled);
		formatedTxtDataNascimento.setEnabled(enabled);
		txtEndereco.setEnabled(enabled);
		txtMunicipio.setEnabled(enabled);
		formatedTxtCelular.setEnabled(enabled);
		cmbBoxUf.setEnabled(enabled);
		formatedTxtCpf.setEnabled(enabled);
		cmbBoxCurso.setEnabled(enabled);
		cmbBoxCampus.setEnabled(enabled);
		rdMatutino.setEnabled(enabled);
		rdVespertino.setEnabled(enabled);
		rdNoturno.setEnabled(enabled);
		btnProximo.setEnabled(enabled);
		btnVoltar.setEnabled(enabled);
		btnSalvar.setEnabled(enabled);
		btnAlterarDados.setEnabled(enabled);
	}
}
