/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.systec.telas;

import java.sql.*;
import br.com.systec.dao.ModuloConexao;
import javax.swing.JOptionPane;
//importa recursos da biblioteca rs2xml.jar
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Júnior
 */
public class TelaFuncionario extends javax.swing.JInternalFrame {

    // usando a variável conexao dao
    Connection conexao = null;
    //criando variáveis especiais para conexão com o banco
    //Prepared Statement e ResultSet são frameworks do pacote java.sql
    //serve para preparar e executar as instruções SQL
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaFuncionario
     */
    public TelaFuncionario() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    //método para adicionar funcionario
    private void adicionar() {
        String sql = "insert into tb_funcionario(cpf_funcionario, nome_funcionario, email_funcionario, telefone_funcionario, endereco_funcionario) values(?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtFunCpf.getText());
            pst.setString(2, txtFunNome.getText());
            pst.setString(3, txtFunEmail.getText());
            pst.setString(4, txtFunTel.getText());
            pst.setString(5, txtFunEnd.getText());

//Validação dos campos obrigatórios
            if ((txtFunCpf.getText().isEmpty()) || (txtFunNome.getText().isEmpty()) || (txtFunTel.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {

// atualiza a tabela funcionario com os dados do formulário
                // usada para confirmar a inserção dos dados na tabela
                int adicionado = pst.executeUpdate();
                // entendimento da lógica
                //System.out.println(adicionado);
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Funcionário Adicionado com Sucesso");

                    //limpam os campos
                    txtFunCpf.setText(null);
                    txtFunNome.setText(null);
                    txtFunEmail.setText(null);
                    txtFunTel.setText(null);
                    txtFunEnd.setText(null);

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //método para pesquisar funcionário pelo nome com filtro
    private void pesquisar_funcionario() {
        String sql = "select * from tb_funcionario where nome_funcionario like ?";
        try {
            pst = conexao.prepareStatement(sql);
            // passando o conteúdo da caixa de pesquisa para o interroga
            //atenção ao "%" - continuação da String sql
            pst.setString(1, txtFunPesquisar.getText() + "%");
            rs = pst.executeQuery();
            // usa a biblioteca rs2xml.jar para preencher a tabela
            tblFuncionario.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    //método para setar os campos do formulário com o conteúdo da tabela
    public void setar_campos() {
        int setar = tblFuncionario.getSelectedRow();
        txtFunId.setText(tblFuncionario.getModel().getValueAt(setar, 0).toString());
        txtFunCpf.setText(tblFuncionario.getModel().getValueAt(setar, 1).toString());
        txtFunNome.setText(tblFuncionario.getModel().getValueAt(setar, 2).toString());
        txtFunEmail.setText(tblFuncionario.getModel().getValueAt(setar, 3).toString());
        txtFunTel.setText(tblFuncionario.getModel().getValueAt(setar, 4).toString());
        txtFunEnd.setText(tblFuncionario.getModel().getValueAt(setar, 5).toString());

        //desabilita o botão adicionar
        btnFunAdicionar.setEnabled(false);

    }
    
    //método para alterar dados do funcionário
    private void alterar() {
        String sql = "update tb_funcionario set cpf_funcionario=?,nome_funcionario=?,email_funcionario=?,telefone_funcionario=?,endereco_funcionario=? where id_funcionario=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtFunCpf.getText());
            pst.setString(2, txtFunNome.getText());
            pst.setString(3, txtFunEmail.getText());
            pst.setString(4, txtFunTel.getText());
            pst.setString(5, txtFunEnd.getText());
            pst.setString(6, txtFunId.getText());

//Validação dos campos obrigatórios
            if ((txtFunCpf.getText().isEmpty()) || (txtFunNome.getText().isEmpty()) || (txtFunTel.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {

                // atualiza a tabela funcionário com os dados do formulário
                // usada para confirmar a alteração dos dados na tabela
                int adicionado = pst.executeUpdate();
                // entendimento da lógica
                //System.out.println(adicionado);
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do Funcionário Alterados com Sucesso");

                    //limpam os campos
                    txtFunCpf.setText(null);
                    txtFunNome.setText(null);
                    txtFunEmail.setText(null);
                    txtFunTel.setText(null);
                    txtFunEnd.setText(null);
                    btnFunAdicionar.setEnabled(true);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    // método responsável pela remoção do funcionário
    private void remover() {
        //confirma a remoção do funcionário
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover esse Funcionário", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tb_funcionario where id_funcionario=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtFunId.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Funcionário Removido com Sucesso");

                    //limpam os campos
                    txtFunCpf.setText(null);
                    txtFunNome.setText(null);
                    txtFunEmail.setText(null);
                    txtFunTel.setText(null);
                    txtFunEnd.setText(null);
                    btnFunAdicionar.setEnabled(true);

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        btnFunRemover = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtFunPesquisar = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFuncionario = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtFunId = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtFunCpf = new javax.swing.JTextField();
        txtFunNome = new javax.swing.JTextField();
        txtFunEmail = new javax.swing.JTextField();
        txtFunTel = new javax.swing.JTextField();
        txtFunEnd = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnFunAdicionar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnFunAlterar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Funcionários");
        setMinimumSize(new java.awt.Dimension(118, 34));
        setNormalBounds(new java.awt.Rectangle(0, 0, 118, 0));
        setPreferredSize(new java.awt.Dimension(640, 480));

        jLabel4.setText("Email");

        btnFunRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/systec/icones/iconfinder_file_delete_48762.png"))); // NOI18N
        btnFunRemover.setPreferredSize(new java.awt.Dimension(80, 80));
        btnFunRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFunRemoverActionPerformed(evt);
            }
        });

        jLabel5.setText("*Telefone");

        txtFunPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFunPesquisarKeyReleased(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/systec/icones/iconfinder_search_48796.png"))); // NOI18N

        tblFuncionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblFuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFuncionarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblFuncionario);

        jLabel1.setText("*ID");

        txtFunId.setEnabled(false);

        jLabel6.setText("Endereço");

        jLabel7.setText("*Campos Obrigatórios");

        jLabel2.setText("*CPF");

        btnFunAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/systec/icones/iconfinder_file_add_48761.png"))); // NOI18N
        btnFunAdicionar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnFunAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFunAdicionarActionPerformed(evt);
            }
        });

        jLabel3.setText("*Nome");

        btnFunAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/systec/icones/iconfinder_file_edit_48763.png"))); // NOI18N
        btnFunAlterar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnFunAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFunAlterarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFunId, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFunCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFunNome, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFunEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFunTel, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFunEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
            .addGroup(layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(btnFunAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(166, 166, 166)
                .addComponent(btnFunAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(162, 162, 162)
                .addComponent(btnFunRemover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtFunPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)))
                .addGap(104, 104, 104))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(txtFunPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtFunEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtFunId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtFunTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtFunEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtFunCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtFunNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFunAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFunAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFunRemover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );

        setBounds(0, 0, 823, 482);
    }// </editor-fold>//GEN-END:initComponents

    private void btnFunRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFunRemoverActionPerformed
        // chama método para remover cliente
        remover();
    }//GEN-LAST:event_btnFunRemoverActionPerformed

    private void txtFunPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFunPesquisarKeyReleased
        //chama o método pesquisar clientes
        pesquisar_funcionario();
    }//GEN-LAST:event_txtFunPesquisarKeyReleased

    private void tblFuncionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFuncionarioMouseClicked
        // chama método setar os campos
        setar_campos();
    }//GEN-LAST:event_tblFuncionarioMouseClicked

    private void btnFunAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFunAdicionarActionPerformed
        // método para adicionar clientes
        adicionar();
    }//GEN-LAST:event_btnFunAdicionarActionPerformed

    private void btnFunAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFunAlterarActionPerformed
        // chama método para alterar cliente
        alterar();
    }//GEN-LAST:event_btnFunAlterarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFunAdicionar;
    private javax.swing.JButton btnFunAlterar;
    private javax.swing.JButton btnFunRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblFuncionario;
    private javax.swing.JTextField txtFunCpf;
    private javax.swing.JTextField txtFunEmail;
    private javax.swing.JTextField txtFunEnd;
    private javax.swing.JTextField txtFunId;
    private javax.swing.JTextField txtFunNome;
    private javax.swing.JTextField txtFunPesquisar;
    private javax.swing.JTextField txtFunTel;
    // End of variables declaration//GEN-END:variables
}
