import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Appointment {
    private Date date;
    private String name;

    public Appointment(Object object, String name) {
        this.date = (Date) object;
        this.name = name;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "Date: " + dateFormat.format(date) + "\nName: " + name + "\n";
    }
}

public class AppointmentBookingSystem extends JFrame {

    private List<Appointment> appointments = new ArrayList<>();

    private JTextField nameTextField;
    private JTextArea outputTextArea;

    public AppointmentBookingSystem() {
        setTitle("Appointment Booking System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Appointment Booking System");
        titleLabel.setBounds(80, 10, 250, 25);
        panel.add(titleLabel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 40, 50, 25);
        panel.add(nameLabel);

        nameTextField = new JTextField();
        nameTextField.setBounds(80, 40, 200, 25);
        panel.add(nameTextField);

        JLabel dateLabel = new JLabel("Select Date:");
        dateLabel.setBounds(20, 70, 80, 25);
        panel.add(dateLabel);

        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd HH:mm:ss"));
        dateSpinner.setBounds(120, 70, 160, 25);
        panel.add(dateSpinner);

        JButton bookButton = new JButton("Book Appointment");
        bookButton.setBounds(150, 100, 150, 25);
        panel.add(bookButton);

        outputTextArea = new JTextArea();
        outputTextArea.setBounds(20, 140, 340, 120);
        outputTextArea.setEditable(false);
        panel.add(outputTextArea);

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookAppointment(dateSpinner.getValue(), nameTextField.getText());
            }
        });
    }

    private void bookAppointment(Object object, String name) {
        if (object != null && !name.trim().isEmpty()) {
            Appointment appointment = new Appointment(object, name);
            appointments.add(appointment);

            JOptionPane.showMessageDialog(this, "Appointment booked successfully!");
            nameTextField.setText("");
            displayAppointments();
        } else {
            JOptionPane.showMessageDialog(this, "Please enter your name and select a valid date.");
        }
    }

    private void displayAppointments() {
        if (appointments.isEmpty()) {
            outputTextArea.setText("No appointments to display.");
        } else {
            StringBuilder output = new StringBuilder("Appointment List:\n");
            for (Appointment appointment : appointments) {
                output.append(appointment.toString()).append("\n");
            }
            outputTextArea.setText(output.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AppointmentBookingSystem();
            }
        });
    }
}