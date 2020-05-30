/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribuicao_normal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author Igor
 */
public class GUI {
    
    public GUI(){
        
    }
    
    public void create_GUI(int sizeX, int sizeY, int posX, int posY){
        
        create_frame(sizeX, sizeY, posX, posY);
    }
    
    public Dimension create_dimension(int sizeX, int sizeY){
        
        Dimension dimension = new Dimension();
        dimension.width = sizeX;
        dimension.height = sizeY;
        
        return dimension;
    }
    
    public void create_frame(int sizeX, int sizeY, int posX, int posY){
        
        this.frame = new JFrame();
        this.frame.setLayout(null);
        this.frame.setResizable(false);
        this.frame.setTitle("Distribução Normal");
        
        this.frame.setSize(create_dimension(sizeX, sizeY));
        this.frame.setLocation(new Point(posX, posY));
        
        create_panel_info(300, 300, 10, 10);
        create_chart_panel(660, 450, 320, 10);

        this.frame.setVisible(true);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void create_panel_info(int sizeX, int sizeY, int posX, int posY){
        
        this.panel_info = new JPanel();
        this.panel_info.setLayout(null);
        this.panel_info.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        this.panel_info.setBackground(new Color(200, 200, 200));
        
        create_label_info();
        create_text_box_info();
        create_buttons_info();
        
        this.panel_info.setSize(new Dimension(sizeX, sizeY));
        this.panel_info.setLocation(new Point(posX, posY));
        this.panel_info.setVisible(true);
        this.frame.getContentPane().add(this.panel_info);
    }
    
    public void create_label_info(){
        
        JLabel label_title = new JLabel();
        label_title.setText("Parâmetros de entrada: ");
        label_title.setBounds(10, 10, 200, 20);
        this.panel_info.add(label_title);
        
        JLabel label_media = new JLabel();
        label_media.setText("Valor da Média: ");
        label_media.setBounds(10, 40, 200, 20);
        this.panel_info.add(label_media);
        
        JLabel label_desvio_padrao = new JLabel();
        label_desvio_padrao.setText("Desvio Padrão: ");
        label_desvio_padrao.setBounds(10, 70, 200, 20);
        this.panel_info.add(label_desvio_padrao);
    }
    
    public void create_text_box_info(){
        
        text_field_media = new JTextField();
        text_field_media.setBounds(100, 40, 100, 20);
        text_field_media.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        this.panel_info.add(text_field_media);
        
        text_field_desvio_padrao = new JTextField();
        text_field_desvio_padrao.setBounds(100, 70, 100, 20);
        text_field_desvio_padrao.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        this.panel_info.add(text_field_desvio_padrao);
    }
    
    public void create_buttons_info(){
        
        JButton button_run = new JButton();
        button_run.setBounds(10, 270, 100, 20);
        button_run.setText("Processar");
        
        button_run.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
                
                String str_media = GUI.text_field_media.getText();
                String str_desv = GUI.text_field_desvio_padrao.getText();
                
                if(str_desv.length() > 0 && str_media.length() > 0){
                    
                    double media = Double.valueOf(str_media);
                    double desv = Double.valueOf(str_desv);
                    
                    List<Double> lx = new ArrayList<>();
                    List<Double> ly = new ArrayList<>();

                    Gauss g = new Gauss();
                    g.calc_dist_range(-1000, 1000, 0.1, media, desv);

                    lx = g.lx;
                    ly = g.ly;

                    Plot plot = new Plot();
                    JFreeChart line_chart = plot.create_line_chart("Distribuição normal", "x", "y(x)", "distribuição", lx, ly);

                    GUI.chartPanel.setChart(line_chart);
                    GUI.chartPanel.repaint();
                    GUI.chartPanel.updateUI();
                }
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
                
                
            }
        });
        
        
        this.panel_info.add(button_run);
    }

    public void create_chart_panel(int sizeX, int sizeY, int posX, int posY){
        
        List<Double> x = new ArrayList<>();
        List<Double> y = new ArrayList<>();
        
        Plot plot = new Plot();
        JFreeChart line_chart = plot.create_line_chart("Distribuição normal", "x", "y(x)", "distribuição", x, y);
        
        GUI.chartPanel = new ChartPanel(line_chart);
        GUI.chartPanel.setBounds(posX, posY, sizeX, sizeY);
        GUI.chartPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        GUI.chartPanel.setDomainZoomable(true);
        GUI.chartPanel.setRangeZoomable(true);
        
        this.frame.getContentPane().add(GUI.chartPanel);
    }
    
    public JPanel panel_info;
    public JFrame frame;
    public JSlider sliderChart;
    
    public static ChartPanel chartPanel;
    
    public static JTextField text_field_media;
    public static JTextField text_field_desvio_padrao;
    
}
