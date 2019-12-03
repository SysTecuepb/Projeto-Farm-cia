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
public class TelaProduto extends javax.swing.JInternalFrame {

    // usando a variável conexao dao
    Connection conexao = null;
    //criando variáveis especiais para conexão com o banco
    //Prepared Statement e ResultSet são frameworks do pacote java.sql
    //serve para preparar e executar as instruções SQL
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaProduto
     */
    public TelaProduto() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

//método para pesquisar fornecedor pelo nome com filtro
    private void pesquisar_fornecedor() {
        String sql = "select id_fornecedor as ID, nome_fornecedor as Nome, telefone_fornecedor as Fone from tb_fornecedor where nome_fornecedor like ?";
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

    //método para pesquisar fornecedor pelo nome com filtro
    private void pesquisar_produto() {
        String sql = "select * from tb_produto where nome_produto like ?";
        try {
            pst = conexao.prepareStatement(sql);
            // passando o conteúdo da caixa de pesquisa para o interroga
            //atenção ao "%" - continuação da String sql
            pst.setString(1, txtProPesquisar.getText() + "%");
            rs = pst.executeQuery();
            // usa a biblioteca rs2xml.jar para preencher a tabela
            tblProduto.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    //método para setar os campos do formulário com o conteúdo da tabela
    public void setar_campos_For() {
        int setar = tblFornecedor.getSelectedRow();
        txtForId.setText(tblFornecedor.getModel().getValueAt(setar, 0).toString());

    }

    //método para setar os campos do formulário com o conteúdo da tabela
    public void setar_campos_Pro() {
        int setar = tblProduto.getSelectedRow();
        txtProId.setText(tblProduto.getModel().getValueAt(setar, 0).toString());
        txtProCodigo.setText(tblProduto.getModel().getValueAt(setar, 1).toString());
        txtProNome.setText(tblProduto.getModel().getValueAt(setar, 2).toString());
        txtProValor.setText(tblProduto.getModel().getValueAt(setar, 3).toString());
        txtProQuant.setText(tblProduto.getModel().getValueAt(setar, 4).toString());
        txtForId.setText(tblProduto.getModel().getValueAt(setar, 0).toString());

        //desabilita o botão adicionar
        btnProAdicionar.setEnabled(false);

    }

    //método para adicionar produto
    private void adicionar() {
        String sql = "insert into tb_produto(codigo_produto, nome_produto, valor_produto, quantidade_produto, id_fornecedor_prod) values(?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtProCodigo.getText());
            pst.setString(2, txtProNome.getText());
            pst.setString(3, txtProValor.getText());
            pst.setString(4, txtProQuant.getText());
            pst.setString(5, txtForId.getText());

//Validação dos campos obrigatórios
            if ((txtProCodigo.getText().isEmpty()) || (txtProNome.getText().isEmpty()) || (txtProValor.getText().isEmpty()) || (txtProQuant.getText().isEmpty()) || (txtForId.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {

// atualiza a tabela produto com os dados do formulário
                // usada para confirmar a inserção dos dados na tabela
                int adicionado = pst.executeUpdate();
                // entendimento da lógica
                //System.out.println(adicionado);
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Produto Adicionado com Sucesso");

                    //limpam os campos
                    txtProCodigo.setText(null);
                    txtProNome.setText(null);
                    txtProValor.setText(null);
                    txtProQuant.setText(null);
                    txtForId.setText(null);

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //método para alterar dados do produto
    private void alterar() {
        String sql = "update tb_produto set codigo_produto=?,nome_produto=?,valor_produto=?,quantidade_produto=?,id_fornecedor_prod=? where id_produto=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtProCodigo.getText());
            pst.setString(2, txtProNome.getText());
            pst.setString(3, txtProValor.getText());
            pst.setString(4, txtProQuant.getText());
            pst.setString(5, txtProId.getText());
            pst.setString(6, txtForId.getText());

//Validação dos campos obrigatórios
            if ((txtProCodigo.getText().isEmpty()) || (txtProNome.getText().isEmpty()) || (txtProValor.getText().isEmpty()) || (txtProQuant.getText().isEmpty()) || (txtForId.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {

                // atualiza a tabela produto com os dados do formulário
                // usada para confirmar a alteração dos dados na tabela
                int adicionado = pst.executeUpdate();
                // entendimento da lógica
                //System.out.println(adicionado);
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do Produto Alterados com Sucesso");

                    //limpam os campos
                    txtProCodigo.setText(null);
                    txtProNome.setText(null);
                    txtProValor.setText(null);
                    txtProQuant.setText(null);
                    txtForId.setText(null);
                    btnProAdicionar.setEnabled(true);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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

        txtProNome = new javax.swing.JTextField();
        btnProRemover = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtProValor = new javax.swing.JTextField();
        btnProAdicionar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtProQuant = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtProPesquisar = new javax.swing.JTextField();
        txtProId = new javax.swing.JTextField();
        btnProAlterar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtProCodigo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduto = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        txtForPesquisar = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtForId = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblFornecedor = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Produtos");
        setPreferredSize(new java.awt.Dimension(640, 480));

        btnProRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/systec/icones/iconfinder_file_delete_48762.png"))); // NOI18N
        btnProRemover.setPreferredSize(new java.awt.Dimension(80, 80));
        btnProRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProRemoverActionPerformed(evt);
            }
        });

        jLabel2.setText("*Cód");

        btnProAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/systec/icones/iconfinder_file_add_48761.png"))); // NOI18N
        btnProAdicionar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnProAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProAdicionarActionPerformed(evt);
            }
        });

        jLabel5.setText("*Quantidade");

        jLabel1.setText("*ID");

        jLabel3.setText("*Nome");

        txtProPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProPesquisarKeyReleased(evt);
            }
        });

        txtProId.setEnabled(false);

        btnProAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/systec/icones/iconfinder_file_edit_48763.png"))); // NOI18N
        btnProAlterar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnProAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProAlterarActionPerformed(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/systec/icones/iconfinder_search_48796.png"))); // NOI18N

        jLabel7.setText("*Campos Obrigatórios");

        jLabel4.setText("*Valor");

        tblProduto.setModel(new javax.swing.table.DefaultTableModel(
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
        tblProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdutoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProduto);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Fornecedor"));

        txtForPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtForPesquisarKeyReleased(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/systec/icones/iconfinder_search_48796.png"))); // NOI18N

        jLabel10.setText("*ID");

        txtForId.setEnabled(false);

        tblFornecedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nome", "Fone"
            }
        ));
        tblFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFornecedorMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblFornecedor);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtForPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtForId))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel9)
                        .addComponent(txtForPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtForId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtProId, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtProCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtProNome, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtProValor, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtProQuant, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(btnProAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(131, 131, 131)
                        .addComponent(btnProAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(120, 120, 120)
                        .addComponent(btnProRemover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtProPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel7)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(txtProPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtProId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtProCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtProNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtProValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtProQuant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnProAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProRemover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        setBounds(0, 0, 836, 502);
    }// </editor-fold>//GEN-END:initComponents

    private void btnProRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProRemoverActionPerformed
        // chama método para remover cliente
        // remover();
    }//GEN-LAST:event_btnProRemoverActionPerformed

    private void btnProAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProAdicionarActionPerformed
        // método para adicionar clientes
        adicionar();
    }//GEN-LAST:event_btnProAdicionarActionPerformed

    private void txtProPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProPesquisarKeyReleased
        pesquisar_produto();

    }//GEN-LAST:event_txtProPesquisarKeyReleased

    private void btnProAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProAlterarActionPerformed
        // chama método para alterar cliente
        alterar();
    }//GEN-LAST:event_btnProAlterarActionPerformed

    private void tblProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutoMouseClicked
        // chama método setar os campos
        setar_campos_Pro();
    }//GEN-LAST:event_tblProdutoMouseClicked

    private void txtForPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtForPesquisarKeyReleased
        // chama método pesquisar fornecedor
        pesquisar_fornecedor();
    }//GEN-LAST:event_txtForPesquisarKeyReleased

    private void tblFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFornecedorMouseClicked
        // chama o método setar campos
        setar_campos_For();
    }//GEN-LAST:event_tblFornecedorMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnProAdicionar;
    private javax.swing.JButton btnProAlterar;
    private javax.swing.JButton btnProRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblFornecedor;
    private javax.swing.JTable tblProduto;
    private javax.swing.JTextField txtForId;
    private javax.swing.JTextField txtForPesquisar;
    private javax.swing.JTextField txtProCodigo;
    private javax.swing.JTextField txtProId;
    private javax.swing.JTextField txtProNome;
    private javax.swing.JTextField txtProPesquisar;
    private javax.swing.JTextField txtProQuant;
    private javax.swing.JTextField txtProValor;
    // End of variables declaration//GEN-END:variables
}
