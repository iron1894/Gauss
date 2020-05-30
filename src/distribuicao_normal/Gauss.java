/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribuicao_normal;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Igor
 */
public class Gauss {

    public Gauss(){
        
        this.lx = new ArrayList<>();
        this.ly = new ArrayList<>();
    }
    
    public void calc_dist_range(int min, int max, double step, double u, double s){
        
        double x = min;
        
        while(x < max){
            
            this.lx.add(x);
            this.ly.add(dist_normal(x, u, s));
            x = x + step;
        } 
    }
    
    public double dist_normal(double x, double u, double s){
        
        double alpha = 1.0/ (s * Math.sqrt(2.0 * Math.PI));
        
        double beta = (-1.0 / 2.0) * Math.pow(((x - u)/(s)), 2.0);
        
        return alpha * Math.exp(beta);
    }

    public void setLy(List<Double> ly) {
        this.ly = ly;
    }
    
    public void setLx(List<Double> lx){
        this.lx = lx;
    }

    public List<Double> getLy() {
        return ly;
    }
    
    public List<Double> getLx(){
        return lx;
    }
    
    public List<Double> lx;
    public List<Double> ly;
}
