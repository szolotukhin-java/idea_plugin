package net.seesharpsoft.intellij.plugins.csv.editor.table.swing;

import com.intellij.ui.table.JBTable;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class TableRowUtilities {
    private static JTable createRowHeadersTable(final JTable userTable, int startingNumber) {
        final JBTable rowHeadersTable = new JBTable(new RowHeadersTableModel(userTable.getModel().getRowCount(), startingNumber));

        // this is where you set the width of the row headers
        rowHeadersTable.createDefaultColumnsFromModel();

        // make the rows look and behave like headers
        rowHeadersTable.setBackground(rowHeadersTable.getTableHeader().getBackground());
        rowHeadersTable.setForeground(rowHeadersTable.getTableHeader().getForeground());
        rowHeadersTable.setFont(rowHeadersTable.getTableHeader().getFont());
        rowHeadersTable.setRowHeight(userTable.getRowHeight());
        rowHeadersTable.getTableHeader().setReorderingAllowed(false);
        rowHeadersTable.setRowSelectionAllowed(true);
        rowHeadersTable.setCellSelectionEnabled(true);
        rowHeadersTable.setFocusable(true);
        rowHeadersTable.setDragEnabled(true);
        rowHeadersTable.setSelectionMode(userTable.getSelectionModel().getSelectionMode());
        rowHeadersTable.getEmptyText().setText("");

        return rowHeadersTable;
    }

    public static JTable addNumberColumn(final JTable userTable, int startingNumber) {
        Container parentContainer = userTable.getParent();

        if (parentContainer instanceof JViewport) {
            Container parentParentContainer = parentContainer.getParent();

            if (parentParentContainer instanceof JScrollPane) {
                final JScrollPane scrollPane = (JScrollPane) parentParentContainer;

                // Make certain we are the viewPort's view and not, for  example, the rowHeaderView of the scrollPane - an implementor of fixed columns might do this.
                JViewport viewport = scrollPane.getViewport();

                if (viewport == null || !Objects.equals(viewport.getView(), userTable)) {
                    return null;
                }

                JTableHeader tableHeader = userTable.getTableHeader();
                scrollPane.setColumnHeaderView(tableHeader);

                final JTable rowHeadersTable = createRowHeadersTable(userTable, startingNumber);
//                adjustComponents(scrollPane, userTable, rowHeadersTable);
                return rowHeadersTable;
            }
        }
        return null;
    }

    /**
     * Table Model for the row number column. It just has one column (the numbers)
     */
    private static final class RowHeadersTableModel extends AbstractTableModel {
        private ArrayList<Integer> numbersList = new ArrayList<Integer>();
        private int startNumber;

        /**
         * Initialize model
         *
         * @param maxNumber      determined by JTable row size
         * @param startingNumber usually zero or 1
         */
        RowHeadersTableModel(int maxNumber, int startingNumber) {
            // start at starting number and then go to row count (plus starting number amount)
            this.startNumber = startingNumber;
            int j = 0;
            for (int i = startingNumber; i < maxNumber + startNumber; i++) {
                numbersList.add(j + startNumber);
                j++;
            }
        }

        public int getRowCount() {
            return numbersList != null ? numbersList.size() : 0;
        }

        public int getMaxIntValue() {
            if (numbersList != null && numbersList.size() != 0) {
                Integer integer = (Integer) getValueAt(numbersList.size() - 1, 0);
                return integer.intValue();
            }
            return 0;
        }

        public int getColumnCount() {
            return 1;
        }

        public String getColumnName(int columnIndex) {
            return "";
        }

        public Class getColumnClass(int columnIndex) {
            return Integer.class;
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            return numbersList.get(rowIndex);
        }

        public void addNumber() {
            if (numbersList.isEmpty()) {
                numbersList.add(0, startNumber);
            } else {
                Integer maxNum = numbersList.get(numbersList.size() - 1);
                numbersList.add(numbersList.size(), maxNum.intValue() + 1);
            }
            this.fireTableDataChanged();
        }

        public void removeNumber() {
            numbersList.remove(numbersList.size() - 1);
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            // value is generated and should not be manipulated
        }
    }
}
