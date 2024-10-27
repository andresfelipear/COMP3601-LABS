package ca.bcit.comp3601.A01394332.lab03.io;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * CustomerGUI
 *
 * @author Andres Arevalo
 * @version 1.0
 */
public class CustomerGUI
{
    private static final String TITLE_FILE_MENU;
    private static final String TITLE_CUSTOMERS_MENU;
    private static final String TITLE_HELP_MENU;
    private static final String FILE_MENU_OPTION1;
    private static final String FILE_MENU_OPTION2;
    private static final String CUSTOMERS_MENU_OPTION1;
    private static final String CUSTOMERS_MENU_OPTION2;
    private static final String HELP_MENU_OPTION1;
    private static final String CUSTOMERS_MENU_SORT_OPTION;
    private static final String APP_NAME;
    private static final int    WIDTH;
    private static final int    HEIGHT;


    final JFrame       frame;
    final JMenuBar     menuBar;
    final JPanel       panel;
    final JPanel       userPanel;

    final JMenu fileMenu;
    final JMenu customersMenu;
    final JMenu helpMenu;

    final JMenuItem dropMenuItem;
    final JMenuItem quitMenuItem;

    final JMenuItem countMenuItem;
    final JMenuItem listMenuItem;

    final JCheckBoxMenuItem byJoinDateCheckBoxMenuItem;

    final JMenuItem aboutMenuItem;

    static
    {
        APP_NAME                   = "Customers GUI";
        TITLE_FILE_MENU            = "File";
        TITLE_CUSTOMERS_MENU       = "Customers";
        TITLE_HELP_MENU            = "Help";
        FILE_MENU_OPTION1          = "Drop";
        FILE_MENU_OPTION2          = "Quit";
        CUSTOMERS_MENU_OPTION1     = "Count";
        CUSTOMERS_MENU_OPTION2     = "List";
        HELP_MENU_OPTION1          = "About";
        CUSTOMERS_MENU_SORT_OPTION = "By Join Date";

        WIDTH            = 800;
        HEIGHT           = 600;
    }

    public static void main(String[] args)
    {
        new CustomerGUI();
    }

    public CustomerGUI()
    {
        frame     = new JFrame(APP_NAME);
        menuBar   = new JMenuBar();
        panel     = new JPanel();
        userPanel = new JPanel();

        fileMenu      = new JMenu(TITLE_FILE_MENU);
        customersMenu = new JMenu(TITLE_CUSTOMERS_MENU);
        helpMenu      = new JMenu(TITLE_HELP_MENU);

        dropMenuItem   = new JMenuItem(FILE_MENU_OPTION1);
        quitMenuItem   = new JMenuItem(FILE_MENU_OPTION2);

        countMenuItem              = new JMenuItem(CUSTOMERS_MENU_OPTION1);
        byJoinDateCheckBoxMenuItem = new JCheckBoxMenuItem(CUSTOMERS_MENU_SORT_OPTION);
        listMenuItem               = new JMenuItem(CUSTOMERS_MENU_OPTION2);

        aboutMenuItem  = new JMenuItem(HELP_MENU_OPTION1);

        createAndShowGUI();
    }

    private void createAndShowGUI()
    {
        fileMenu.add(dropMenuItem);
        fileMenu.add(quitMenuItem);

        customersMenu.add(countMenuItem);
        customersMenu.add(byJoinDateCheckBoxMenuItem);
        customersMenu.add(listMenuItem);

        helpMenu.add(aboutMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(customersMenu);
        menuBar.add(helpMenu);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setJMenuBar(menuBar);
        frame.add(panel);
        frame.setVisible(true);

        dropMenuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                int response = JOptionPane.
                        showConfirmDialog(frame,
                                          "Are you sure you want to delete this customer?",
                                          "Confirm Deletion",
                                          JOptionPane.YES_NO_OPTION,
                                          JOptionPane.WARNING_MESSAGE);

                if (response == JOptionPane.YES_OPTION) {
                    // Code to delete the customer
                    System.out.println("Customer deleted.");
                } else {
                    System.out.println("Deletion canceled.");
                }
            }
        });
    }
}
