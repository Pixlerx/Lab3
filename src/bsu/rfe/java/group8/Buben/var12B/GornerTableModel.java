package bsu.rfe.java.group8.Buben.var12B;

import javax.swing.table.AbstractTableModel;


public class GornerTableModel extends AbstractTableModel {
    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;
    private double result[] = new double[1];

    public GornerTableModel(Double from, Double to, Double step, Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }

    public Double getFrom() {
        return from;
    }

    public Double getTo() {
        return to;
    }

    public Double getStep() {
        return step;
    }

    public int getColumnCount() {
        // В данной модели два столбца
        return 2;
    }

    public int getRowCount() {
        // Вычислить количество точек между началом и концом отрезка
        // исходя из шага табулирования
        return new Double(Math.ceil((to - from) / step)).intValue() + 1;
    }

    public Object getValueAt(int row, int col)
    {
        double x = from + step * row;
        switch (col)
        {
            case 0:
                return x;
            case 1:
            {
                result[0] = 0.0;
                for (int i = 0; i < coefficients.length; i++)
                {
                    result[0] += Math.pow(x, coefficients.length - 1 - i) * coefficients[i];
                }
                return result[0];
            }
            default:
            {
                result[0] = 0.0;
                for (int i = 0; i < coefficients.length; i++)
                    result[0] += Math.pow(x, coefficients.length - 1 - i) * coefficients[i];

                int temp = 0;
                boolean flag = true;
                for (int j = 2; j <= result[0] - 1; j++)
                {
                    temp = (int)result[0] % j;
                    if (temp == 0)
                    {
                        flag = false;
                        break;
                    }
                }
                if (flag==true) return true;
                else return false;
            }
        }
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:// Название 1-го столбца
                return "Значение X";
            default:
                // Название 2-го столбца
                return "Значение многочлена";
        }
    }

    public Class<?> getColumnClass(int col) {
        // И в 1-ом и во 2-ом столбце находятся значения типа Double
        return Double.class;
    }
}
