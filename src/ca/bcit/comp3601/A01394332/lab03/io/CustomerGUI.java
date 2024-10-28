package ca.bcit.comp3601.A01394332.lab03.io;

import ca.bcit.comp3601.A01394332.lab03.data.Customer;
import ca.bcit.comp3601.A01394332.lab03.data.CustomerDetails;
import ca.bcit.comp3601.A01394332.lab03.data.util.Common;
import ca.bcit.comp3601.A01394332.lab03.database.CustomerDaoTester;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
    private static final int    WIDTH_CUSTOMER_DETAILS_FRAME;
    private static final int    HEIGHT_CUSTOMER_DETAILS_FRAME;

    ArrayList<Customer> customers;

    private final CustomerDaoTester daoTester;
    private final CustomerDataManager customerDataManager;

    final JFrame       frame;
    final JMenuBar     menuBar;

    JFrame   customersFrame;
    JFrame   customerDetailsFrame;

    final JMenu fileMenu;
    final JMenu customersMenu;
    final JMenu helpMenu;

    final JMenuItem dropMenuItem;
    final JMenuItem quitMenuItem;

    final JMenuItem countMenuItem;
    final JMenuItem listMenuItem;

    final JCheckBoxMenuItem byJoinDateCheckBoxMenuItem;

    final JMenuItem aboutMenuItem;

    boolean sortByJoinDate;

    DefaultListModel<String> model;

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

        WIDTH_CUSTOMER_DETAILS_FRAME = 600;
        HEIGHT_CUSTOMER_DETAILS_FRAME = 400;
    }

    public CustomerGUI(final CustomerDaoTester daoTester, final String fileName) throws Exception
    {
        this.daoTester = daoTester;

        customerDataManager = new CustomerDataManager(fileName);

        frame                = new JFrame(APP_NAME);
        menuBar              = new JMenuBar();

        fileMenu      = new JMenu(TITLE_FILE_MENU);
        customersMenu = new JMenu(TITLE_CUSTOMERS_MENU);
        helpMenu      = new JMenu(TITLE_HELP_MENU);

        dropMenuItem   = new JMenuItem(FILE_MENU_OPTION1);
        quitMenuItem   = new JMenuItem(FILE_MENU_OPTION2);

        countMenuItem              = new JMenuItem(CUSTOMERS_MENU_OPTION1);
        byJoinDateCheckBoxMenuItem = new JCheckBoxMenuItem(CUSTOMERS_MENU_SORT_OPTION);
        listMenuItem               = new JMenuItem(CUSTOMERS_MENU_OPTION2);

        aboutMenuItem  = new JMenuItem(HELP_MENU_OPTION1);

        sortByJoinDate = false;

        customers = daoTester.getCustomers();

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
        frame.setVisible(true);

        customerDetailsFrame = new JFrame("Customer Details");
        customerDetailsFrame.setSize(WIDTH_CUSTOMER_DETAILS_FRAME, HEIGHT_CUSTOMER_DETAILS_FRAME);
        customerDetailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        dropMenuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                int response = JOptionPane.
                        showConfirmDialog(frame,
                                          "Are you sure you want to delete all the customers?",
                                          "Confirm Deletion",
                                          JOptionPane.YES_NO_OPTION,
                                          JOptionPane.WARNING_MESSAGE);

                if (response == JOptionPane.YES_OPTION) {
                    try
                    {
                        daoTester.dropTables();
                        System.out.println("The customers were deleted correctly.");
                        System.out.println("Exiting the program!!");
                        exitProgram();
                    }
                    catch(SQLException ex)
                    {
                        throw new RuntimeException(ex);
                    }
                } else {
                    System.out.println("Deletion canceled.");
                }
            }
        });

        quitMenuItem.addActionListener( e-> exitProgram());

        countMenuItem.addActionListener( e ->
                                         {
                                             try
                                             {
                                                 JOptionPane.
                                                         showMessageDialog(frame,
                                                                           String.format("There are %d costumers.", daoTester.getIds().size()));
                                             }
                                             catch(SQLException ex)
                                             {
                                                 System.out.println(ex.getMessage());
                                             }
                                         });

        byJoinDateCheckBoxMenuItem.addActionListener( e-> {
            sortByJoinDate = !sortByJoinDate;
        });

        listMenuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                displayCustomersList();
            }
        });

        aboutMenuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                JOptionPane.showMessageDialog(frame, getGameInstructions(), "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    /**
     * Returns the game instructions.
     *
     * @return A string containing the game instructions.
     */
    private static String getGameInstructions()
    {
        return "Customers Management App\n" +
                "COMP 3601 - Assignment 1\n\n" +
                "Instructions:\n" +
                "1) Data Management:\n" +
                "   Each time the application is launched, the customer data is loaded from a text file into the database,\n" +
                "   ensuring that if the customer table is dropped, it will be recreated with the original data on the next start.\n\n" +
                "2) File Menu:\n" +
                "   -Drop: Opens a confirmation dialog to drop all customer data.\n" +
                "    If confirmed, the customer table will be deleted, and the program will exit.\n" +
                "    When restarted, the data will reload.\n" +
                "   -Quit: Closes the application.\n\n" +
                "3) Customers Menu:\n" +
                "   -Count: Shows the total number of customers in a dialog.\n" +
                "   -By Join Date: If selected, lists customers sorted by their join date in ascending order.\n" +
                "   -List: Opens a dialog displaying a list of all customers.\n" +
                "    Selecting a customer in the list opens a detail view where information can be modified (except the ID).\n" +
                "    Changes are saved to the database when 'OK' is pressed, or discarded if 'Cancel' is selected.\n\n" +
                "4) Help Menu:\n" +
                "   -About (F1): Opens this 'About' dialog to provide details about the application.";
    }

    private void displayCustomersList()
    {
        final JLabel    header;
        final JPanel    buttonPanel;
        final JButton             okButton;

        model = new DefaultListModel<>();
        JList<String> customerList = new JList<>(model);

        final ArrayList<Customer> customersCopy;
        customersCopy = new ArrayList<>();
        for(Customer customer : customers)
        {
            customersCopy.add(new Customer.Builder(customer.getId(), customer.getPhoneNumber())
                                      .firstName(customer.getFirstName())
                                      .lastName(customer.getLastName())
                                      .streetName(customer.getStreetName())
                                      .city(customer.getCity())
                                      .postalCode(customer.getPostalCode())
                                      .email(customer.getEmail())
                                      .joinDate(customer.getJoinDate())
                                      .build());
        }
        model.addElement(CustomerReport.getCustomerHeader());

        if(sortByJoinDate)
        {
            customersCopy.sort(new CompareByJoinedDate());
        }

        for(Customer customer : customersCopy)
        {
            model.addElement(customer.toString());
        }
        
        customerList.addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                String selectedCustomer = customerList.getSelectedValue();
                displayCustomerDetails(selectedCustomer);
            }
        });

        customersFrame  = new JFrame("Customers List");
        customersFrame.setSize(WIDTH/2, HEIGHT/2);
        customersFrame.setLayout(new BorderLayout());

        header = new JLabel("Customers", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 12));

        buttonPanel   = new JPanel();
        okButton      = new JButton("OK");

        buttonPanel.add(okButton);

        customersFrame.add(header, BorderLayout.NORTH);
        customersFrame.add(new JScrollPane(customerList));
        customersFrame.add(buttonPanel, BorderLayout.SOUTH);

        customersFrame.setVisible(true);

        okButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                customersFrame.dispose();
            }
        });
    }

    private void displayCustomerDetails(final String selectedCustomer)
    {
        customerDetailsFrame.dispose();
        customerDetailsFrame = new JFrame("Customer Details");
        customerDetailsFrame.setSize(WIDTH_CUSTOMER_DETAILS_FRAME, HEIGHT_CUSTOMER_DETAILS_FRAME);
        customerDetailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        final JPanel   customerDetailsPanel;
        final Customer customer;
        final String   customerId;

        final JButton  saveButton;
        final JButton  cancelButton;

        final TextField id          = new TextField(20);
        final TextField firstName   = new TextField(20);
        final TextField lastName    = new TextField(20);
        final TextField streetName  = new TextField(20);
        final TextField city        = new TextField(20);
        final TextField postalCode  = new TextField(20);
        final TextField email       = new TextField(20);
        final TextField joinDate    = new TextField(20);
        final TextField phoneNumber = new TextField(20);

        customerId = selectedCustomer.substring(0, CustomerDetails.CUSTOMER_ID.getLength());
        customer   = getCustomerFromId(customerId.trim());

        // Setting values to the text fields
        if(customer != null)
        {
            id.setText(customer.getId());
            firstName.setText(customer.getFirstName());
            lastName.setText(customer.getLastName());
            streetName.setText(customer.getStreetName());
            city.setText(customer.getCity());
            postalCode.setText(customer.getPostalCode());
            email.setText(customer.getEmail());
            phoneNumber.setText(customer.getPhoneNumber());
            joinDate.setText(customer.getJoinDate().toString());
        }

        id.setEditable(false);

        customerDetailsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill   = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridy = 0;
        customerDetailsPanel.add(new JLabel("ID"), c);

        c.gridx = 1;
        customerDetailsPanel.add(id, c);

        c.gridx = 0;
        c.gridy = 1;
        customerDetailsPanel.add(new JLabel("First Name"), c);

        c.gridx = 1;
        customerDetailsPanel.add(firstName, c);

        c.gridx = 0;
        c.gridy = 2;
        customerDetailsPanel.add(new JLabel("Last Name"), c);

        c.gridx = 1;
        customerDetailsPanel.add(lastName, c);

        c.gridx = 0;
        c.gridy = 3;
        customerDetailsPanel.add(new JLabel("Street"), c);

        c.gridx = 1;
        customerDetailsPanel.add(streetName, c);

        c.gridx = 0;
        c.gridy = 4;
        customerDetailsPanel.add(new JLabel("City"), c);

        c.gridx = 1;
        customerDetailsPanel.add(city, c);

        c.gridx = 0;
        c.gridy = 5;
        customerDetailsPanel.add(new JLabel("Postal Code"), c);

        c.gridx = 1;
        customerDetailsPanel.add(postalCode, c);

        c.gridx = 0;
        c.gridy = 6;
        customerDetailsPanel.add(new JLabel("Phone"), c);

        c.gridx = 1;
        customerDetailsPanel.add(phoneNumber, c);

        c.gridx = 0;
        c.gridy = 7;
        customerDetailsPanel.add(new JLabel("Email"), c);

        c.gridx = 1;
        customerDetailsPanel.add(email, c);

        c.gridx = 0;
        c.gridy = 8;
        customerDetailsPanel.add(new JLabel("Joined Date"), c);

        c.gridx = 1;
        customerDetailsPanel.add(joinDate, c);

        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");

        c.gridx = 0;
        c.gridy = 9;
        c.weightx = 1;
        customerDetailsPanel.add(saveButton, c);

        c.gridx = 1;
        customerDetailsPanel.add(cancelButton, c);

        customerDetailsFrame.add(customerDetailsPanel, BorderLayout.CENTER);
        customerDetailsFrame.setVisible(true);

        saveButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                final Customer customerUpdated;
                customerUpdated = new Customer.Builder(id.getText(), phoneNumber.getText())
                        .firstName(firstName.getText())
                        .lastName(lastName.getText())
                        .streetName(streetName.getText())
                        .city(city.getText())
                        .postalCode(postalCode.getText())
                        .email(email.getText())
                        .joinDate(LocalDate.parse(joinDate.getText()))
                        .build();

                try
                {
                    daoTester.update(customerUpdated);
                    customers = daoTester.getCustomers();
                }
                catch(Exception ex)
                {
                    System.out.println(ex.getMessage());
                }

                customerDetailsFrame.dispose();
                updateCustomersModel(customerUpdated);
                customerDataManager.writeCustomerToFile(customerUpdated);
            }
        });

        cancelButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                customerDetailsFrame.dispose();
            }
        });
    }

    private void updateCustomersModel(final Customer customer)
    {
        for(int i = 0; i < model.size(); i++)
        {
            String currentCustomer = model.getElementAt(i);
            if(currentCustomer.contains(customer.getId()))
            {
                model.set(i, customer.toString());
            }
        }
    }

    private Customer getCustomerFromId(final String customerId)
    {;
        for(final Customer customer : customers)
        {
            if(customer.getId().equals(customerId))
            {
                return customer;
            }
        }
        return null;
    }

    private void exitProgram()
    {
        frame.dispose();
    }
}
