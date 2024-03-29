/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.systec.telas;

import br.com.systec.dal.ModuloConexao;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import java.sql.*;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Júnior
 */
public class TelaPrincipal extends javax.swing.JFrame {

    // usando a variável conexao dao
    Connection conexao = null;

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Desktop = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        Menu = new javax.swing.JMenuBar();
        menCad = new javax.swing.JMenu();
        menCadCli = new javax.swing.JMenuItem();
        menCadFun = new javax.swing.JMenuItem();
        menCadFor = new javax.swing.JMenuItem();
        menCadPro = new javax.swing.JMenuItem();
        menCadVen = new javax.swing.JMenuItem();
        menCadUsu = new javax.swing.JMenuItem();
        menRel = new javax.swing.JMenu();
        menRelCli = new javax.swing.JMenuItem();
        menRelFun = new javax.swing.JMenuItem();
        menRelFor = new javax.swing.JMenuItem();
        menRelPro = new javax.swing.JMenuItem();
        menRelVen = new javax.swing.JMenuItem();
        menAju = new javax.swing.JMenu();
        menAjuSob = new javax.swing.JMenuItem();
        menOpc = new javax.swing.JMenu();
        menOpcSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Systec Farmácia");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        Desktop.setPreferredSize(new java.awt.Dimension(640, 480));

        javax.swing.GroupLayout DesktopLayout = new javax.swing.GroupLayout(Desktop);
        Desktop.setLayout(DesktopLayout);
        DesktopLayout.setHorizontalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        DesktopLayout.setVerticalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 503, Short.MAX_VALUE)
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/systec/icones/computador-pc-com-monitor_318-50338.jpg"))); // NOI18N

        lblUsuario.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblUsuario.setText("Usuário");

        lblData.setFont(new java.awt.Font("Tahoma", 2, 24)); // NOI18N
        lblData.setText("Data");

        menCad.setText("Cadastro");
        menCad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadActionPerformed(evt);
            }
        });

        menCadCli.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        menCadCli.setText("Cliente");
        menCadCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadCliActionPerformed(evt);
            }
        });
        menCad.add(menCadCli);

        menCadFun.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK));
        menCadFun.setText("Funcionário");
        menCadFun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadFunActionPerformed(evt);
            }
        });
        menCad.add(menCadFun);

        menCadFor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK));
        menCadFor.setText("Fornecedor");
        menCadFor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadForActionPerformed(evt);
            }
        });
        menCad.add(menCadFor);

        menCadPro.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        menCadPro.setText("Produto");
        menCadPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadProActionPerformed(evt);
            }
        });
        menCad.add(menCadPro);

        menCadVen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK));
        menCadVen.setText("Venda");
        menCad.add(menCadVen);

        menCadUsu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK));
        menCadUsu.setText("Usuário");
        menCadUsu.setEnabled(false);
        menCadUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadUsuActionPerformed(evt);
            }
        });
        menCad.add(menCadUsu);

        Menu.add(menCad);

        menRel.setText("Relatórios");
        menRel.setEnabled(false);

        menRelCli.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        menRelCli.setText("Cliente");
        menRelCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menRelCliActionPerformed(evt);
            }
        });
        menRel.add(menRelCli);

        menRelFun.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        menRelFun.setText("Funcionario");
        menRelFun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menRelFunActionPerformed(evt);
            }
        });
        menRel.add(menRelFun);

        menRelFor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        menRelFor.setText("Fornecedor");
        menRelFor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menRelForActionPerformed(evt);
            }
        });
        menRel.add(menRelFor);

        menRelPro.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        menRelPro.setText("Produto");
        menRelPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menRelProActionPerformed(evt);
            }
        });
        menRel.add(menRelPro);

        menRelVen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        menRelVen.setText("Vendas");
        menRel.add(menRelVen);

        Menu.add(menRel);

        menAju.setText("Ajuda");
        menAju.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menAjuActionPerformed(evt);
            }
        });

        menAjuSob.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.ALT_MASK));
        menAjuSob.setText("Sobre");
        menAjuSob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menAjuSobActionPerformed(evt);
            }
        });
        menAju.add(menAjuSob);

        Menu.add(menAju);

        menOpc.setText("Opções");
        menOpc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menOpcActionPerformed(evt);
            }
        });

        menOpcSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        menOpcSair.setText("Sair");
        menOpcSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menOpcSairActionPerformed(evt);
            }
        });
        menOpc.add(menOpcSair);

        Menu.add(menOpc);

        setJMenuBar(Menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lblUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 226, Short.MAX_VALUE)
                        .addComponent(lblData, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUsuario)
                            .addComponent(lblData))
                        .addGap(35, 35, 35)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Desktop, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        setSize(new java.awt.Dimension(866, 672));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menOpcSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menOpcSairActionPerformed
        //exibe uma caixa de dialogo
        int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            System.exit(0);
    }//GEN-LAST:event_menOpcSairActionPerformed
    }
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // substituem a lebel lbl_Data pela data atual
        // do sistema ao iniciar o form
        Date data = new Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
        lblData.setText(formatador.format(data));
    }//GEN-LAST:event_formWindowActivated

    private void menOpcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menOpcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menOpcActionPerformed

    private void menAjuSobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menAjuSobActionPerformed
        // chama a tela sobre
        TelaSobre sobre = new TelaSobre();
        sobre.setVisible(true);
    }//GEN-LAST:event_menAjuSobActionPerformed

    private void menAjuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menAjuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menAjuActionPerformed

    private void menCadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadActionPerformed

    }//GEN-LAST:event_menCadActionPerformed

    private void menCadUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadUsuActionPerformed
        //abrir o form TelaUsuario dentro do desktop pane
        TelaUsuario usuario = new TelaUsuario();
        usuario.setVisible(true);
        Desktop.add(usuario);
    }//GEN-LAST:event_menCadUsuActionPerformed

    private void menCadCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadCliActionPerformed
        // chama TelaCliente
        TelaCliente cliente = new TelaCliente();
        cliente.setVisible(true);
        Desktop.add(cliente);
    }//GEN-LAST:event_menCadCliActionPerformed

    private void menCadFunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadFunActionPerformed

        // chama TelaFuncionario
        TelaFuncionario funcionario = new TelaFuncionario();
        funcionario.setVisible(true);
        Desktop.add(funcionario);
    }//GEN-LAST:event_menCadFunActionPerformed

    private void menCadForActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadForActionPerformed
        // chama TelaFornecedor
        TelaFornecedor fornecedor = new TelaFornecedor();
        fornecedor.setVisible(true);
        Desktop.add(fornecedor);
    }//GEN-LAST:event_menCadForActionPerformed

    private void menCadProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadProActionPerformed
        // chama a TelaProduto
        TelaProduto produto = new TelaProduto();
        produto.setVisible(true);
        Desktop.add(produto);
    }//GEN-LAST:event_menCadProActionPerformed

    private void menRelCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menRelCliActionPerformed
        // gera relatório cliente
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a Emissão desse relatório?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            //imprimindo relatório com o framework JasperReport
            try {
                // usando a classe JasperPrint para preparar a impressão de um relatório
                JasperPrint print = JasperFillManager.fillReport("E:\\Users\\Júnior\\Documents\\NetBeansProjects\\hidedelegate\\farmacia\\prjfarmacia\\src\\br\\com\\systec\\relatorios\\Clientes.jasper", null, conexao);
                //exibe o relatório través da classe JasperViewer
                JasperViewer.viewReport(print, false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
    }//GEN-LAST:event_menRelCliActionPerformed

    private void menRelFunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menRelFunActionPerformed
        // gera relatório funcionário
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a Emissão desse relatório?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            //imprimindo relatório com o framework JasperReport
            try {
                // usando a classe JasperPrint para preparar a impressão de um relatório
                JasperPrint print = JasperFillManager.fillReport("E:\\Users\\Júnior\\Documents\\NetBeansProjects\\hidedelegate\\farmacia\\prjfarmacia\\src\\br\\com\\systec\\relatorios\\Funcionarios.jasper", null, conexao);
                //exibe o relatório través da classe JasperViewer
                JasperViewer.viewReport(print, false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
    }//GEN-LAST:event_menRelFunActionPerformed

    private void menRelForActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menRelForActionPerformed
        // gera relatório fornecedor
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a Emissão desse relatório?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            //imprimindo relatório com o framework JasperReport
            try {
                // usando a classe JasperPrint para preparar a impressão de um relatório
                JasperPrint print = JasperFillManager.fillReport("E:\\Users\\Júnior\\Documents\\NetBeansProjects\\hidedelegate\\farmacia\\prjfarmacia\\src\\br\\com\\systec\\relatorios\\Fornecedores.jasper", null, conexao);
                //exibe o relatório través da classe JasperViewer
                JasperViewer.viewReport(print, false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
    }//GEN-LAST:event_menRelForActionPerformed

    private void menRelProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menRelProActionPerformed
        // gera relatório produto
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a Emissão desse relatório?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            //imprimindo relatório com o framework JasperReport
            try {
                // usando a classe JasperPrint para preparar a impressão de um relatório
                JasperPrint print = JasperFillManager.fillReport("E:\\Users\\Júnior\\Documents\\NetBeansProjects\\hidedelegate\\farmacia\\prjfarmacia\\src\\br\\com\\systec\\relatorios\\Produtos.jasper", null, conexao);
                //exibe o relatório través da classe JasperViewer
                JasperViewer.viewReport(print, false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
    }//GEN-LAST:event_menRelProActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane Desktop;
    private javax.swing.JMenuBar Menu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblData;
    public static javax.swing.JLabel lblUsuario;
    private javax.swing.JMenu menAju;
    private javax.swing.JMenuItem menAjuSob;
    private javax.swing.JMenu menCad;
    private javax.swing.JMenuItem menCadCli;
    private javax.swing.JMenuItem menCadFor;
    private javax.swing.JMenuItem menCadFun;
    private javax.swing.JMenuItem menCadPro;
    public static javax.swing.JMenuItem menCadUsu;
    private javax.swing.JMenuItem menCadVen;
    private javax.swing.JMenu menOpc;
    private javax.swing.JMenuItem menOpcSair;
    public static javax.swing.JMenu menRel;
    private javax.swing.JMenuItem menRelCli;
    private javax.swing.JMenuItem menRelFor;
    private javax.swing.JMenuItem menRelFun;
    private javax.swing.JMenuItem menRelPro;
    private javax.swing.JMenuItem menRelVen;
    // End of variables declaration//GEN-END:variables
}
