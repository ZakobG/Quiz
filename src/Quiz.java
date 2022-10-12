import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quiz extends main implements ActionListener {

    // Setting questions, answers and options
    String[] questions = {
            "Jaká společnost založila Javu?",
            "Kdy byla Java vytvořena?",
            "Pod koho spadá Java nyní?",
            "Jak se Java původně jmenovala?"
    };

    String[][] options = {
            {"Oracle", "Microsoft", "Sun Microsystems", "Virtual Dating Assistants"},
            {"1985", "1976", "1993", "1996"},
            {"bell laboratories of AT&T", "Oracle", "JetBrains", "Apple Inc."},
            {"Sassafras", "Cucumber Tree", "Oak", "Butternut"}
    };

    char[] answers = {
            'C',
            'D',
            'B',
            'C'
    };

    char guess;
    char answer;
    int index;
    int correct_guesses = 0;
    int total_questions = questions.length;
    int result;
    int seconds = 10;

    JFrame frame = new JFrame();
    JTextField textfield = new JTextField();
    JTextArea textarea = new JTextArea();
    JButton buttA = new JButton();
    JButton buttB = new JButton();
    JButton buttC = new JButton();
    JButton buttD = new JButton();
    JLabel answer_labelA = new JLabel();
    JLabel answer_labelB = new JLabel();
    JLabel answer_labelC = new JLabel();
    JLabel answer_labelD = new JLabel();
    JLabel seconds_left = new JLabel();
    JTextField number_right = new JTextField();
    JTextField percentage = new JTextField();

    // Setting timer huehue

    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            seconds--;
            seconds_left.setText(String.valueOf(seconds));

            if (seconds <= 0) {
                displayAnswer();
            }
        }
    });

    public Quiz() {

        // Just setting GUI lol

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(650, 650);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.pink);
        frame.setLayout(null);

        textfield.setBounds(0, 0, 650, 50);
        textfield.setBackground(Color.white);
        textfield.setForeground(Color.black);
        textfield.setFont(new Font(Font.SERIF, Font.ITALIC, 30));
        textfield.setBorder(BorderFactory.createBevelBorder(1));
        textfield.setHorizontalAlignment(JTextField.CENTER);
        textfield.setEditable(false);

        textarea.setBounds(0, 50, 650, 50);
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        textarea.setBackground(Color.white);
        textarea.setForeground(Color.black);
        textarea.setFont(new Font(Font.SERIF, Font.ITALIC, 25));
        textarea.setBorder(BorderFactory.createBevelBorder(1));
        textarea.setEditable(false);

        buttA.setBounds(0, 100, 100, 100);
        buttA.setFont(new Font(Font.SERIF, Font.ITALIC, 35));
        buttA.setFocusable(false);
        buttA.addActionListener(this);
        buttA.setBackground(Color.white);
        buttA.setText("A");

        buttB.setBounds(0, 200, 100, 100);
        buttB.setFont(new Font(Font.SERIF, Font.ITALIC, 35));
        buttB.setFocusable(false);
        buttB.addActionListener(this);
        buttB.setBackground(Color.white);
        buttB.setText("B");

        buttC.setBounds(0, 300, 100, 100);
        buttC.setFont(new Font(Font.SERIF, Font.ITALIC, 35));
        buttC.setFocusable(false);
        buttC.addActionListener(this);
        buttC.setBackground(Color.white);
        buttC.setText("C");

        buttD.setBounds(0, 400, 100, 100);
        buttD.setFont(new Font(Font.SERIF, Font.ITALIC, 35));
        buttD.setFocusable(false);
        buttD.addActionListener(this);
        buttD.setBackground(Color.white);
        buttD.setText("D");

        answer_labelA.setBounds(100, 100, 525, 100);
        answer_labelA.setBackground(Color.pink);
        answer_labelA.setForeground(Color.black);
        answer_labelA.setFont(new Font(Font.SERIF, Font.ITALIC, 35));
        answer_labelA.setBorder(BorderFactory.createBevelBorder(1));

        answer_labelB.setBounds(100, 200, 525, 100);
        answer_labelB.setBackground(Color.pink);
        answer_labelB.setForeground(Color.black);
        answer_labelB.setFont(new Font(Font.SERIF, Font.ITALIC, 35));
        answer_labelB.setBorder(BorderFactory.createBevelBorder(1));

        answer_labelC.setBounds(100, 300, 525, 100);
        answer_labelC.setBackground(Color.pink);
        answer_labelC.setForeground(Color.black);
        answer_labelC.setFont(new Font(Font.SERIF, Font.ITALIC, 35));
        answer_labelC.setBorder(BorderFactory.createBevelBorder(1));

        answer_labelD.setBounds(100, 400, 525, 100);
        answer_labelD.setBackground(Color.pink);
        answer_labelD.setForeground(Color.black);
        answer_labelD.setFont(new Font(Font.SERIF, Font.ITALIC, 35));
        answer_labelD.setBorder(BorderFactory.createBevelBorder(1));

        seconds_left.setBounds(0, 510, 100, 100);
        seconds_left.setBackground(Color.white);
        seconds_left.setForeground(Color.black);
        seconds_left.setFont(new Font(Font.SERIF, Font.BOLD, 60));
        seconds_left.setBorder(BorderFactory.createBevelBorder(1));
        seconds_left.setOpaque(true);
        seconds_left.setHorizontalAlignment(JTextField.CENTER);
        seconds_left.setBorder(BorderFactory.createBevelBorder(1));
        seconds_left.setText(String.valueOf(seconds));

        number_right.setBounds(225, 225, 200, 100);
        number_right.setBackground(Color.pink);
        number_right.setForeground(Color.green);
        number_right.setFont(new Font(Font.SERIF, Font.ITALIC, 50));
        number_right.setBorder(BorderFactory.createBevelBorder(1));
        number_right.setHorizontalAlignment(JTextField.CENTER);
        number_right.setEditable(false);

        percentage.setBounds(225, 325, 200, 100);
        percentage.setBackground(Color.pink);
        percentage.setForeground(Color.green);
        percentage.setFont(new Font(Font.SERIF, Font.ITALIC, 50));
        percentage.setBorder(BorderFactory.createBevelBorder(1));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);

        frame.add(seconds_left);
        frame.add(answer_labelA);
        frame.add(answer_labelB);
        frame.add(answer_labelC);
        frame.add(answer_labelD);
        frame.add(buttA);
        frame.add(buttB);
        frame.add(buttC);
        frame.add(buttD);
        frame.add(textarea);
        frame.add(textfield);

        nextQuestion();
    }

    public void nextQuestion() {

        // Setting questions bby :3

        if (index >= total_questions) {
            results();
        } else {
            textfield.setText("Question " + (index + 1));
            textarea.setText(questions[index]);
            answer_labelA.setText(options[index][0]);
            answer_labelB.setText(options[index][1]);
            answer_labelC.setText(options[index][2]);
            answer_labelD.setText(options[index][3]);

            timer.start();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        buttA.setEnabled(false);
        buttB.setEnabled(false);
        buttC.setEnabled(false);
        buttD.setEnabled(false);

        // Shitload of ifs cuz I'm lazy to think smort

        if (e.getSource() == buttA) {
            answer = 'A';
            if (answer == answers[index]) {
                correct_guesses++;
            }
        }
        if (e.getSource() == buttB) {
            answer = 'B';
            if (answer == answers[index]) {
                correct_guesses++;
            }
        }
        if (e.getSource() == buttC) {
            answer = 'C';
            if (answer == answers[index]) {
                correct_guesses++;
            }
        }
        if (e.getSource() == buttD) {
            answer = 'D';
            if (answer == answers[index]) {
                correct_guesses++;
            }
        }
        displayAnswer();
    }

    public void displayAnswer() {

        timer.stop();

        buttA.setEnabled(false);
        buttB.setEnabled(false);
        buttC.setEnabled(false);
        buttD.setEnabled(false);

        // Again shitload of ifs cuz smort != good

        if (answers[index] != 'A') {
            answer_labelA.setForeground(Color.red);
        }
        if (answers[index] != 'B') {
            answer_labelB.setForeground(Color.red);
        }
        if (answers[index] != 'C') {
            answer_labelC.setForeground(Color.red);
        }
        if (answers[index] != 'D') {
            answer_labelD.setForeground(Color.red);
        }

        // Timer for labels to get original color

        Timer pause = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                answer_labelA.setForeground(Color.black);
                answer_labelB.setForeground(Color.black);
                answer_labelC.setForeground(Color.black);
                answer_labelD.setForeground(Color.black);

                answer = ' ';
                seconds = 10;
                seconds_left.setText(String.valueOf(seconds));

                buttA.setEnabled(true);
                buttB.setEnabled(true);
                buttC.setEnabled(true);
                buttD.setEnabled(true);

                index++;

                nextQuestion();
            }
        });
        pause.setRepeats(false);
        pause.start();
    }

    public void results() {

        // Setting result and percentage display

        buttA.setEnabled(false);
        buttB.setEnabled(false);
        buttC.setEnabled(false);
        buttD.setEnabled(false);

        result = (int) ((correct_guesses / (double) total_questions) * 100);

        textfield.setText("Results");
        textarea.setText("");
        answer_labelA.setText("");
        answer_labelB.setText("");
        answer_labelC.setText("");
        answer_labelD.setText("");

        number_right.setText(correct_guesses + "/" + total_questions);
        percentage.setText(result + "%");

        frame.add(percentage);
        frame.add(number_right);
    }

}
