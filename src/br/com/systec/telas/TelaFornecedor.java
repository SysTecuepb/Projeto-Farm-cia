/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.systec.telas;

import java.sql.*;
import br.com.systec.dal.ModuloConexao;
import javax.swing.JOptionPane;
//importa recursos da biblioteca rs2xml.jar
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Júnior
 */
public class TelaFornecedor extends javax.swing.JInternalFrame {

    // usando a variável conexao dao
    Connection conexao = null;
    //criando variáveis especiais para conexão com o banco
    //Prepared Statement e ResultSet são frameworks do pacote java.sql
    //serve para preparar e executar as instruções SQL
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaFornecedor
     */
    public TelaFornecedor() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    //método para adicionar fornecedor
    private void adicionar() {
        String sql = "insert into tb_fornecedor(cnpj_fornecedor, nome_fornecedor, email_fornecedor, telefone_fornecedor, endereco_fornecedor) values(?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtForCnpj.getText());
            pst.setString(2, txtForNome.getText());
            pst.setString(3, txtForEmail.getText());
            pst.setString(4, txtForTel.getText());
            pst.setString(5, txtForEnd.getText());

//Validação dos campos obrigatórios
            if ((txtForCnpj.getText().isEmpty()) || (txtForNome.getText().isEmpty()) || (txtForTel.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {

// atualiza a tabela fornecedor com os dados do formulário
                // usada para confirmar a inserção dos dados na tabela
                int adicionado = pst.executeUpdate();
                // entendimento da lógica
                //System.out.println(adicionado);
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Fornecedor Adicionado com Sucesso");

                    //limpam os campos
                    txtForCnpj.setText(null);
                    txtForNome.setText(null);
                    txtForEmail.setText(null);
                    txtForTel.setText(null);
                    txtForEnd.setText(null);

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //método para pesquisar fornecedor pelo nome com filtro
    private void pesquisar_fornecedor() {
        String sql = "select * from tb_fornecedor where nome_fornecedor like ?";
        try {
            pst = conexao.prepareStatement(sql);
            // passando o conteúdo da caixa de pesquisa para o interroga
            //atenção ao "%" - continuação da String sql
            pst.setString(1, txtForPesquisar.getText() + "%");
            rs = pst.executeQuery();
            // usa a biblioteca rs2xml.jar para preencher a tabela
            tblFornecedor.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    //método para setar os campos do formulário com o conteúdo da tabela
    public void setar_campos() {
        int setar = tblFornecedor.getSelectedRow();
        txtForId.setText(tblFornecedor.getModel().getValueAt(setar, 0).toString());
        txtForCnpj.setText(tblFornecedor.getModel().getValueAt(setar, 1).toString());
        txtForNome.setText(tblFornecedor.getModel().getValueAt(setar, 2).toString());
        txtForEmail.setText(tblFornecedor.getModel().getValueAt(setar, 3).toString());
        txtForTel.setText(tblFornecedor.getModel().getValueAt(setar, 4).toString());
        txtForEnd.setText(tblFornecedor.getModel().getValueAt(setar, 5).toString());

        //desabilita o botão adicionar
        btnForAdicionar.setEnabled(false);

    }
    
    //método para alterar dados do fornecedor
    private void alterar() {
        String sql = "update tb_fornecedor set cnpj_fornecedor=?,nome_fornecedor=?,email_fornecedor=?,telefone_fornecedor=?,endereco_fornecedor=? where id_fornecedor=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtForCnpj.getText());
            pst.setString(2, txtForNome.getText());
            pst.setString(3, txtForEmail.getText());
            pst.setString(4, txtForTel.getText());
            pst.setString(5, txtForEnd.getText());
            pst.setString(6, txtForId.getText());

//Validação dos campos obrigatórios
            if ((txtForCnpj.getText().isEmpty()) || (txtForNome.getText().isEmpty()) || (txtForTel.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {

                // atualiza a tabela fornecedor com os dados do formulário
                // usada para confirmar a alteração dos dados na tabela
                int adicionado = pst.executeUpdate();
                // entendimento da lógica
                //System.out.println(adicionado);
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do Fornecedor Alterados com Sucesso");

                    //limpam os campos
                    txtForCnpj.setText(null);
                    txtForNome.setText(null);
                    txtForEmail.setText(null);
                    txtForTel.setText(null);
                    txtForEnd.setText(null);
                    btnForAdicionar.setEnabled(true);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    // método responsável pela remoção do fornecedor
    private void remover() {
        //confirma a remoção do fornecedor
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover esse Fornecedor", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tb_fornecedor where id_fornecedor=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtForId.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Fornecedor Removido com Sucesso");

                    //limpam os campos
                    txtForCnpj.setText(null);
                    txtForNome.setText(null);
                    txtForEmail.setText(null);
                    txtForTel.setText(null);
                    txtForEnd.setText(null);
                    btnForAdicionar.setEnabled(true);

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

        txtForEnd = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFornecedor = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btnForAdicionar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtForId = new javax.swing.JTextField();
        btnForAlterar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtForCnpj = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtForNome = new javax.swing.JTextField();
        btnForRemover = new javax.swing.JButton();
        txtForEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtForTel = new javax.swing.JTextField();
        txtForPesquisar = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Fornecedores");
        setMinimumSize(new java.awt.Dimension(118, 34));
        setNormalBounds(new java.awt.Rectangle(0, 0, 118, 0));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/systec/icones/iconfinder_search_48796.png"))); // NOI18N

        jLabel7.setText("*Campos Obrigatórios");

        tblFornecedor.setModel(new javax.swing.table.DefaultTableModel(
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
        tblFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFornecedorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblFornecedor);

        jLabel2.setText("*CNPJ");

        btnForAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/systec/icones/iconfinder_file_add_48761.png"))); // NOI18N
        btnForAdicionar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnForAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnForAdicionarActionPerformed(evt);
            }
        });

        jLabel1.setText("*ID");

        jLabel3.setText("*Nome");

        txtForId.setEnabled(false);

        btnForAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/systec/icones/iconfinder_file_edit_48763.png"))); // NOI18N
        btnForAlterar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnForAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnForAlterarActionPerformed(evt);
            }
        });

        jLabel6.setText("Endereço");

        jLabel4.setText("Email");

        btnForRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/systec/icones/iconfinder_file_delete_48762.png"))); // NOI18N
        btnForRemover.setPreferredSize(new java.awt.Dimension(80, 80));
        btnForRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnForRemoverActionPerformed(evt);
            }
        });

        jLabel5.setText("*Telefone");

        txtForPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtForPesquisarKeyReleased(evt);
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
                    .addComponent(txtForId, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtForCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtForNome, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtForEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtForTel, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtForEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
            .addGroup(layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(btnForAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(166, 166, 166)
                .addComponent(btnForAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(162, 162, 162)
                .addComponent(btnForRemover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtForPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(txtForPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtForEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtForId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtForTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtForEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtForCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtForNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnForAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnForAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnForRemover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFornecedorMouseClicked
        // chama método setar os campos
        setar_campos();
    }//GEN-LAST:event_tblFornecedorMouseClicked

    private void btnForAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnForAdicionarActionPerformed
        // método para adicionar clientes
        adicionar();
    }//GEN-LAST:event_btnForAdicionarActionPerformed

    private void btnForAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnForAlterarActionPerformed
        // chama método para alterar cliente
        alterar();
    }//GEN-LAST:event_btnForAlterarActionPerformed

    private void btnForRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnForRemoverActionPerformed
        // chama método para remover cliente
        remover();
    }//GEN-LAST:event_btnForRemoverActionPerformed

    private void txtForPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtForPesquisarKeyReleased
        //chama o método pesquisar clientes
        pesquisar_fornecedor();
    }//GEN-LAST:event_txtForPesquisarKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnForAdicionar;
    private javax.swing.JButton btnForAlterar;
    private javax.swing.JButton btnForRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblFornecedor;
    private javax.swing.JTextField txtForCnpj;
    private javax.swing.JTextField txtForEmail;
    private javax.swing.JTextField txtForEnd;
    private javax.swing.JTextField txtForId;
    private javax.swing.JTextField txtForNome;
    private javax.swing.JTextField txtForPesquisar;
    private javax.swing.JTextField txtForTel;
    // End of variables declaration//GEN-END:variables
}
