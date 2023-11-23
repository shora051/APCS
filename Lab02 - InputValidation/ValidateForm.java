import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

/**
 * A class that validates user input, used as part of an input form
 */
public class ValidateForm
{
    public ValidateForm() {
        //no variables to initialize
    }
    
    //Number 2
    public boolean isAllAlpha(String str)
    {
       int falseCounter = 0;
       for(int i = 0; i < str.length(); i++)
       {
           if(Character.isLetter(str.charAt(i)))
           {
               continue;
           }       
           else
           {
               falseCounter++;
           }
       }
       if(falseCounter > 0)
       {
           return false;
       }
       else
       {
           return true;
       }
    }
    
    //Number 3
    public boolean isNumeric(String str)
    {
       int numCounter = 0;
       for(int i = 0; i < str.length(); i++)
       {
           if(Character.isDigit(str.charAt(i)))
           {
               numCounter++;
           }       
       }
       if(numCounter == str.length())
       {
           return true;
       }
       else
       {
           return false;
       }
    }
    /** validate the user's entered name */
    //Number 4
    public String checkName(String name) 
    {
        //TODO
        if((name.length() > 2) && (isAllAlpha(name) == true))
        {
            return "";
        }
        else
        {
            return "Invalid Name";
        }
    }

    /** validate the user's entered email */
    //Number 6
    public String checkEmail(String email) {
        //TODO
        if(email.contains("@") && email.contains("."))
        {
            if((email.indexOf("@") < email.indexOf(".")) && (email.indexOf("@")> 0) && (email.indexOf(".") < email.length()-2) )
            {
                return "";
            }
            else
            {
                return "Invalid Email";
            }
        }
        else
        {
            return "Invalid Email";
        }
    }

    /** validate the user's enter password */
    //Number 5
    public String checkPW(String pw) {
        //TODO
        int charCount = 0;
        int numCount2 = 0;
        int lowerCaseCount = 0;
        int upperCaseCount = 0;
        for(int i = 0; i < pw.length(); i++)
        {
            if(Character.isLetter(pw.charAt(i)))
            {
                if(Character.isUpperCase(pw.charAt(i)))
                {
                    upperCaseCount++;
                    charCount++;
                }
                else
                {
                    lowerCaseCount++;
                    charCount++;
                }
            }
            else if(Character.isDigit(pw.charAt(i)))
            {
                numCount2++;
                charCount++;
            }
            else
            {
                charCount++;
                continue;
            }
        }
        if((charCount >= 4)&&(numCount2 >=1) && (lowerCaseCount >= 1) && (upperCaseCount >= 1))
        {
            return "";
        }
        else
        {
            return "Invalid Password";
        }   
        
    }

    /** validate the user's entered zipcode */
    public String checkZip(String zip) {
        //TODO
        int digitCount = 0;
        for(int i = 0; i < zip.length(); i++)
        {
            if(Character.isDigit(zip.charAt(i)))
            {
                digitCount++;
            }
        }
        if((digitCount >= 3) && (digitCount <= 5) && (digitCount == zip.length()))
        {
            return "";
        }
        else
        {
            return "Invalid Zip Code";
        }
    }

    /** validate the user's entered birth year */
    //Number 8
    public String checkBirth(String date) 
    {
        //TODO
        int today = Calendar.getInstance().get(Calendar.YEAR);
        int year = 0;
        try {
            year = Integer.parseInt(date); //the code that can fail
        }
        catch (NumberFormatException ex) {
        //if an error occurred, "catch" it and prevent the program from crashing
        // "year" will stay at 0 if the try block didn't finish running
        }
        int diff = (today - year);
        if((diff > 0) && (diff < 125))
        {
            return "";
        }
        else
        {
            return "Invalid Birth";
        }
    }
    /** validate the user's entered phone number */
    //Number 9
    public String checkPhone(String phone) {
        //TODO
        int digits = 0;
        int notDigits = 0;
        phone = phone.replace("-", "");
        for(int i = 0; i < phone.length(); i ++)
        {
            if(Character.isDigit(phone.charAt(i)))
            {
                digits++;
            }
            else
            {
                 notDigits++;
            }
        }
        if((digits == phone.length()) && (notDigits == 0))
        {
            return "";
        }
        else
        {
            return "Invalid Phone Number";
        }
    }

    /** main method for testing / setting up the GUI */
    public static void main(String[] args)
    {
        /*
         * you can add other method calls here for testing
         */
        ValidateForm obj = new ValidateForm();
        String str1 = "Sahil";
        System.out.println("True example (isAllAlpha): " + obj.isAllAlpha(str1));
        String str2 = "12345";
        System.out.println("False example (isAllAlpha): " + obj.isAllAlpha(str2));
        String str3 = "356789";
        System.out.println("True example (isNumeric): " + (obj.isNumeric(str3)));
        String str4 = "Sahil";
        System.out.println("False example (isNumeric): " + obj.isNumeric(str4));
        
        
        //set up the GUI, you don't need to understand this code
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                //the frame is the GUI, it uses a ValidateForm object
                TextComponentFrame frame = new TextComponentFrame(new ValidateForm());
                frame.setVisible(true);
            }
        });
    }
}










/**
 * A frame (GUI) with simple text components to simulate a web form
 */
class TextComponentFrame extends JFrame 
{
    static final int DEFAULT_WIDTH  = 300;
    static final int DEFAULT_HEIGHT = 400;

    ValidateForm validater;

    public TextComponentFrame(ValidateForm v)
    {
        validater = v;

        initGUI();
        
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null); //center on screen
    }

    /** initialize the GUI components, e.g. buttons and text fields */
    private void initGUI()
    {
        setTitle("Subscription Form");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        final JTextField personName = new JTextField();
        final JTextField emailField = new JTextField();
        final JTextField zipCode    = new JTextField();
        final JTextField birthDate  = new JTextField();

        MaskFormatter mfor = null;
        try {
            mfor = new MaskFormatter("###-###-####"); //for the phone number field
        }
        catch (ParseException e) {}

        final JFormattedTextField phoneNumber   = new JFormattedTextField(mfor);
        final JPasswordField      passwordField = new JPasswordField();

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(6, 6)); //dimensions of layout
        northPanel.add(new JLabel("Name :  ", SwingConstants.RIGHT));
        northPanel.add(personName);
        northPanel.add(new JLabel("Password :  ", SwingConstants.RIGHT));
        northPanel.add(passwordField);
        northPanel.add(new JLabel("Email : ", SwingConstants.RIGHT));
        northPanel.add(emailField);
        northPanel.add(new JLabel("Zip Code (US) : ", SwingConstants.RIGHT));
        northPanel.add(zipCode);
        northPanel.add(new JLabel("Year of Birth: ", SwingConstants.RIGHT));
        northPanel.add(birthDate);
        northPanel.add(new JLabel("Phone Number: ", SwingConstants.RIGHT));
        northPanel.add(phoneNumber);

        add(northPanel, BorderLayout.NORTH);

        final JTextArea textArea = new JTextArea(8, 40);
        JScrollPane scrollPane   = new JScrollPane(textArea);

        add(scrollPane, BorderLayout.CENTER);

        // add button to listen for events, append text into the text area

        JPanel  southPanel   = new JPanel();
        JButton submitButton = new JButton("Submit");
        southPanel.add(submitButton);
        submitButton.addActionListener(new ActionListener() //make anonymous action listener
        {
            /** this method is called in response to an event, in this case the "Submit" button pressed */
            public void actionPerformed(ActionEvent event)
            {
                String name     = personName.getText() ;
                String email    = emailField.getText();
                String zip      = zipCode.getText();
                String birth    = birthDate.getText();
                String phone    = phoneNumber.getText(); 
                String password = new String(passwordField.getPassword());

                String result = ""; 

                result += validater.checkName(name);
                result += validater.checkPW(password);
                result += validater.checkEmail(email);
                result += validater.checkZip(zip);
                result += validater.checkBirth(birth);
                result += validater.checkPhone(phone);

                if (result.length() == 0)
                    result = "Input accepted!";

                textArea.setText(result);
            }
        });

        add(southPanel, BorderLayout.SOUTH);
    }
}