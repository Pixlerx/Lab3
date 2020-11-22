package bsu.rfe.java.group8.Buben.var12B;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class GornerTableCellRenderer implements TableCellRenderer {
    private JPanel panel = new JPanel();
    private JLabel label = new JLabel();
    // Ищем ячейки, строковое представление которых совпадает с needle
    // (иголкой). Применяется аналогия поиска иголки в стоге сена, в роли
    // стога сена - таблица
    private String needle = null;
    private DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance();

    public GornerTableCellRenderer() {
        // Показывать только 5 знаков после запятой
        formatter.setMaximumFractionDigits(5);
        // Не использовать группировку (т.е. не отделять тысячи
        // ни запятыми, ни пробелами), т.е. показывать число как "1000",
        // а не "1 000" или "1,000"
        formatter.setGroupingUsed(false);
        // Установить в качестве разделителя дробной части точку, а не
        // запятую. По умолчанию, в региональных настройках
        // Россия/Беларусь дробная часть отделяется запятой
        DecimalFormatSymbols dottedDouble = formatter.getDecimalFormatSymbols();
        dottedDouble.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(dottedDouble);
        // Разместить надпись внутри панели
        panel.add(label);
        // Установить выравнивание надписи по левому краю панели
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        // Преобразовать double в строку с помощью форматировщика
        String formattedDouble = formatter.format(value);
        // Установить текст надписи равным строковому представлению числа
        label.setText(formattedDouble);
        int index = formattedDouble.length() - formattedDouble.indexOf(".") - 1;
        int indexDot = formattedDouble.indexOf(".");
        int count = 0;


        if (indexDot != -1) {
            for (int i = indexDot + 1; i < formattedDouble.length(); i++) {
                char letter = formattedDouble.charAt(i);
                if (letter == '1') {
                    count += 1;
                }
                if (letter == '3') {
                    count += 1;
                }
                if (letter == '5') {
                    count += 1;
                }
            }
        }

        if (count == formattedDouble.length() - formattedDouble.indexOf(".") - 1)
        {
            if (col==1 && needle!=null && needle.equals(formattedDouble))
                panel.setBackground(Color.RED);
            else panel.setBackground(Color.PINK);
        }
        else
        {
            if (col==1 && needle!=null && needle.equals(formattedDouble))
                panel.setBackground(Color.RED);
            else panel.setBackground(Color.WHITE);
        }

        return panel;
    }

    public void setNeedle(String needle) {
        this.needle = needle;
    }
}


