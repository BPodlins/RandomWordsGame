package GUI;

import Websites.CzasownikiPage;
import Websites.PrzymiotnikPage;
import Websites.PrzyslowekPage;
import Websites.RzeczownikPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class MainFrame extends JFrame {
        public ArrayList<String> czasowniki = new ArrayList<>();
        public ArrayList<String> przymiotniki = new ArrayList<>();
        public ArrayList<String> przyslowki = new ArrayList<>();
        public ArrayList<String> rzeczowniki = new ArrayList<>();


        private CzasownikiPage czasownikiPage;
        private PrzymiotnikPage przymiotnikPage;
        private PrzyslowekPage przyslowekPage;
        private RzeczownikPage rzeczownikPage;

        private JPanel mainPanel;
        private JLabel backgroundImageLabel;
        private JLabel captionLabel;
        private JButton generateWordsButton;

        public MainFrame() {
                setSize(800, 600);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setLayout(new BorderLayout());
                setTitle("Gierka na (Z)Mule");

                mainPanel = new JPanel();
                mainPanel.setLayout(null);

                ImageIcon backgroundImageIcon = new ImageIcon("C:\\Projekty\\ProjektyWWolnymCzasie\\KalamburyGra\\Source\\power.jpg");
                Image backgroundImage = backgroundImageIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                backgroundImageIcon = new ImageIcon(backgroundImage);
                backgroundImageLabel = new JLabel(backgroundImageIcon);
                backgroundImageLabel.setBounds(0, 0, getWidth(), getHeight());

                captionLabel = new JLabel("Gierka na (Z)Mule");
                captionLabel.setBounds(300, 0, 200, 50);
                captionLabel.setHorizontalAlignment(JLabel.CENTER);

                generateWordsButton = new JButton("Generuj słowa");
                generateWordsButton.setBounds(320, 470, 200, 100);
                generateWordsButton.setSize(130, 50);

                mainPanel.add(captionLabel);
                mainPanel.add(generateWordsButton);
                mainPanel.add(backgroundImageLabel);

                add(mainPanel, BorderLayout.CENTER);

                setVisible(true);

                generateWordsButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                if (czasownikiPage == null || przymiotnikPage == null || przyslowekPage == null || rzeczownikPage == null) {
                                        czasownikiPage = new CzasownikiPage();
                                        czasowniki.addAll(czasownikiPage.getWord().getWords());
                                        przymiotnikPage = new PrzymiotnikPage();
                                        przymiotniki.addAll(przymiotnikPage.getWord().getWords());
                                        przyslowekPage = new PrzyslowekPage();
                                        przyslowki.addAll(przyslowekPage.getWord().getWords());
                                        rzeczownikPage = new RzeczownikPage();
                                        rzeczowniki.addAll(rzeczownikPage.getWord().getWords());
                                }

                                ImageIcon backgroundImageIcon = new ImageIcon("C:\\Projekty\\ProjektyWWolnymCzasie\\KalamburyGra\\Source\\ikonkahaha.jpg");
                                Image backgroundImage = backgroundImageIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                                backgroundImageIcon = new ImageIcon(backgroundImage);
                                backgroundImageLabel.setIcon(backgroundImageIcon);

                                generateWordsButton.setText("Losuj zdanie");
                                generateWordsButton.removeActionListener(this);

                                generateWordsButton.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                                mainPanel.remove(backgroundImageLabel);
                                                mainPanel.revalidate();
                                                mainPanel.repaint();

                                                JTextArea przymiotnik = new JTextArea(getRandomWord(przymiotniki));
                                                JTextArea rzeczownik = new JTextArea(getRandomWord(rzeczowniki));
                                                JTextArea przyslowek = new JTextArea(getRandomWord(przyslowki));
                                                JTextArea czasownik = new JTextArea(getRandomWord(czasowniki));
                                                JTextArea przymiotnik2 = new JTextArea(getRandomWord(przymiotniki));
                                                JTextArea rzeczownik2 = new JTextArea(getRandomWord(rzeczowniki));

                                                JPanel zdanie = new JPanel();
                                                zdanie.setLayout(new BoxLayout(zdanie, BoxLayout.X_AXIS));
                                                zdanie.add(przymiotnik);
                                                zdanie.add(rzeczownik);
                                                zdanie.add(przyslowek);
                                                zdanie.add(czasownik);
                                                zdanie.add(przymiotnik2);
                                                zdanie.add(rzeczownik2);

                                                zdanie.setAlignmentX(Component.CENTER_ALIGNMENT);
                                                zdanie.setAlignmentY(Component.CENTER_ALIGNMENT);

                                                mainPanel.add(zdanie, BorderLayout.CENTER);
                                        }

                                        public String getRandomWord(ArrayList<String> words) {
                                                Random random = new Random();
                                                return words.get(random.nextInt(words.size()));
                                        }
                                });
                        }
                });
        }
}