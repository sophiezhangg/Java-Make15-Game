/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject;

/**
 *
 * @author ying
 */
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Make15 implements MouseListener {

    private JFrame frame;
    private MyPanel panel;

    private ArrayList<Rectangle> listRect = new ArrayList<>(12);
    private Color backgroundColor = Color.BLACK;
    private Image[] image = new Image[13];
    private Image[] choice = new Image[3];
    private ArrayList<Integer> userList = new ArrayList<>();
    private ArrayList<Integer> compList = new ArrayList<>();
    private int[] available = new int[9];
    private ArrayList<Integer> newAvailable = new ArrayList<>();
    private int userSum = 0, compSum = 0, randomNumber, compListSum, userListSum, counter = 0, randomNum, sleep = 3;
    ;
    private boolean computerTurn = false, compWin = true, choice1 = false, choice2 = false, playAgain = false;
    private Random r = new Random();
    private ArrayList<Rectangle> choiceRect = new ArrayList<>(3);

    public Make15() {

        makeChoiceRectangles();
        choiceImages();

        makeRectangles();
        loadImages();

        frame = new JFrame("Make 15");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(800, 600);
        frame.setVisible(true);

        panel = new MyPanel();
        panel.addMouseListener(this);
        frame.add(panel);

    }

    public void makeChoiceRectangles() {
        choiceRect.add(null);
        Rectangle easy = new Rectangle(225, 300, 75, 75);
        choiceRect.add(easy);
        Rectangle hard = new Rectangle(400, 300, 75, 75);
        choiceRect.add(hard);
    }

    public void choiceImages() {
        choice[1] = new ImageIcon("Image/EASY.PNG").getImage();
        choice[2] = new ImageIcon("Image/HARD.PNG").getImage();
    }

    public void loadImages() {
        image[1] = new ImageIcon("Image/ONE.PNG").getImage();
        image[2] = new ImageIcon("Image/TWO.png").getImage();
        image[3] = new ImageIcon("Image/THREE.png").getImage();
        image[4] = new ImageIcon("Image/FOUR.PNG").getImage();
        image[5] = new ImageIcon("Image/FIVE.PNG").getImage();
        image[6] = new ImageIcon("Image/SIX.png").getImage();
        image[7] = new ImageIcon("Image/SEVEN.png").getImage();
        image[8] = new ImageIcon("Image/EIGHT.PNG").getImage();
        image[9] = new ImageIcon("Image/YOUWIN.PNG").getImage();
        image[10] = new ImageIcon("Image/YOULOSE.png").getImage();
        image[11] = new ImageIcon("Image/NOONEWINS.png").getImage();
        image[12] = new ImageIcon("Image/PLAYAGAIN.png").getImage();
    }

    public void makeRectangles() {

        listRect.add(null);
        Rectangle rect1 = new Rectangle(325, 50, 75, 75);
        listRect.add(rect1);
        Rectangle rect2 = new Rectangle(475, 125, 75, 75);
        listRect.add(rect2);
        Rectangle rect3 = new Rectangle(575, 250, 75, 75);
        listRect.add(rect3);
        Rectangle rect4 = new Rectangle(475, 375, 75, 75);
        listRect.add(rect4);
        Rectangle rect5 = new Rectangle(325, 475, 75, 75);
        listRect.add(rect5);
        Rectangle rect6 = new Rectangle(175, 375, 75, 75);
        listRect.add(rect6);
        Rectangle rect7 = new Rectangle(75, 250, 75, 75);
        listRect.add(rect7);
        Rectangle rect8 = new Rectangle(175, 125, 75, 75);
        listRect.add(rect8);
        Rectangle youWin = new Rectangle(275, 200, 75, 75);
        listRect.add(youWin);
        Rectangle youLose = new Rectangle(275, 200, 75, 75);
        listRect.add(youLose);
        Rectangle draw = new Rectangle(275, 200, 75, 75);
        listRect.add(draw);
        Rectangle playAgain = new Rectangle(275, 325, 150, 75);
        listRect.add(playAgain);

    }

    public void mouseClicked(MouseEvent event) {

    }

    public void mousePressed(MouseEvent event) {

        if (choiceRect.get(1).contains(event.getPoint())) {

            choice1 = true;
        } else if (choiceRect.get(2).contains(event.getPoint())) {
            choice2 = true;
        }

        if (compListSum != 15 && userListSum != 15) {
            for (int i = 1; i < available.length; i++) {

                if (available[i] == 0 && listRect.get(i).contains(event.getPoint())) {
                    available[i] = 1;
                    userList.add(i);
                    computerTurn = true;
                }

            }

        }

        if (choice1 == true) {
            if (computerTurn == true) {
                computerChoiceEasy();
                computerTurn = false;
            }
        } else if (choice2 == true) {
            if (computerTurn == true) {
                computerChoice();
                computerTurn = false;
            }
        }

        compListSum = 0;
        for (int i = 0; i < compList.size(); i++) {
            compListSum += compList.get(i);

        }

        userListSum = 0;
        for (int j = 0; j < userList.size(); j++) {

            userListSum += userList.get(j);
        }

        frame.repaint();

        if (compListSum == 15) {
            playAgain = true;
        }

        if (userListSum == 15) {

            playAgain = true;
        }
        counter = 0;
        for (int i = 0; i < available.length; i++) {

            if (available[i] != 0) {
                counter++;
            }

            if (counter == available.length - 1) {

                if (userListSum != 15 && compListSum != 15) {
                    playAgain = true;
                }

            }
        }
        if (playAgain == true) {
            for (int i = 1; i < listRect.size(); i++) {
                if (listRect.get(i).contains(event.getPoint())) {
                    if (i == 12) {
                        playAgain();
                    }

                }
            }
        }

    }

    public void computerChoiceEasy() {
        newAvailable.clear();
        for (int s = 1; s < available.length; s++) {
            if (available[s] == 0) {
                newAvailable.add(s);

            }

        }

        randomNum = newAvailable.get(r.nextInt(newAvailable.size()));;
        available[randomNum] = 2;
        compList.add(randomNum);

        frame.repaint();
    }

    public void computerChoice() {
        compSum = 0;
        for (int i = 0; i < compList.size(); i++) {//comp sum size = 0
            compSum += compList.get(i);
        }
        if (0 < 15 - compSum && 15 - compSum < available.length) // num = 15 - compsum < 9 & >0
        {
            if (available[15 - compSum] == 0) //num = availa
            {
                available[15 - compSum] = 2;// make it not av
                compList.add(15 - compSum);//add num to compli  

            } else // num no avai from cs
            {
                userSum = 0;
                for (int j = 0; j < userList.size(); j++) { // usersum 
                    userSum += userList.get(j);
                }
                if (0 < 15 - userSum && 15 - userSum < available.length) // num = 15 - user < 9
                {
                    if (available[15 - userSum] == 0) { // avail subtra index user is empt
                        available[15 - userSum] = 2; // make it taken
                        compList.add(15 - userSum); // add cs num

                    } else // if subtr user is taken as well, random
                    {
                        newAvailable.clear();
                        for (int s = 1; s < available.length; s++) {
                            if (available[s] == 0) {
                                newAvailable.add(s);
                            }
                        }
                        randomNumber = newAvailable.get(r.nextInt(newAvailable.size()));
                        available[randomNumber] = 2;
                        compList.add(randomNumber);
                    }
                } else //subtr user > 9
                {
                    newAvailable.clear();
                    for (int s = 1; s < available.length; s++) { //random
                        if (available[s] == 0) {
                            newAvailable.add(s);
                        }
                    }
                    randomNumber = newAvailable.get(r.nextInt(newAvailable.size()));;
                    available[randomNumber] = 2;
                    compList.add(randomNumber);

                    // set color (blue , rectangle list index)
                }
            }
        } else // cs sum subtra >= 9
        {
            userSum = 0;
            for (int j = 0; j < userList.size(); j++) { // user
                userSum += userList.get(j);
            }
            if (0 < 15 - userSum && 15 - userSum < available.length)// numb usersum < 9
            {
                if (available[15 - userSum] == 0) {
                    available[15 - userSum] = 2;
                    compList.add(15 - userSum);
                } else // random
                {
                    newAvailable.clear();
                    for (int s = 1; s < available.length; s++) {
                        if (available[s] == 0) {
                            newAvailable.add(s);
                        }
                    }
                    randomNumber = newAvailable.get(r.nextInt(newAvailable.size()));;
                    available[randomNumber] = 2;
                    compList.add(randomNumber);
                }

            } else { // user no work, cs no work
                newAvailable.clear();
                for (int s = 1; s < available.length; s++) {
                    if (available[s] == 0) {
                        newAvailable.add(s);
                    }
                }
                randomNumber = newAvailable.get(r.nextInt(newAvailable.size()));;
                available[randomNumber] = 2;
                compList.add(randomNumber);

            }
        }

        frame.repaint();

    }

    public void mouseReleased(MouseEvent event) {
    }

    public void mouseEntered(MouseEvent event) {
    }

    public void mouseExited(MouseEvent event) {
    }

    public void playAgain() {
        userList.clear();
        compList.clear();
        Arrays.fill(available, 0);
        newAvailable.clear();
        userSum = 0;
        compSum = 0;
        randomNumber = 0;
        compListSum = 0;
        userListSum = 0;
        counter = 0;
        randomNum = 0;
        computerTurn = false;
        compWin = true;
        choice1 = false;
        choice2 = false;

    }

    class MyPanel extends JPanel {

        public void paint(Graphics g) {
            Graphics2D g2D = (Graphics2D) g;

            if (choice1 == false && choice2 == false) {
                g.setColor(backgroundColor);
                String m = "Welcome to Make 15!";
                String n = "The rules are simple: take turns with the computer to select numbers that add up to exactly 15.";
                String o = "No more, no less. The player's box will turn magenta after clicked while the computer's box will turn blue.";
                String p = "The computer and the player will alternate between each choice. The first to get 15 wins.";
                g.drawString(m, 100, 50);
                g.drawString(n, 100, 150);
                g.drawString(o, 100, 175);
                g.drawString(p, 100, 200);

                String q = "Please pick a mode to play:";
                g.drawString(q, 100, 275);
                for (int i = 1; i < choice.length; i++) {
                    g2D.drawImage(choice[i], choiceRect.get(i).x, choiceRect.get(i).y, this);
                }
            }
            if (choice1 == true || choice2 == true) {

                g.setColor(backgroundColor);

                for (int i = 1; i < available.length; i++) {
                    g2D.drawImage(image[i], listRect.get(i).x, listRect.get(i).y, this);
                    if (available[i] == 1) {
                        g.setColor(Color.MAGENTA);
                    } else if (available[i] == 2) {
                        g.setColor(Color.BLUE);
                    }
                    g2D.draw(listRect.get(i));
                    g.setColor(backgroundColor);

                }

            }

            if (compListSum == 15) {

                g2D.drawImage(image[10], listRect.get(10).x, listRect.get(10).y, this);
                g2D.drawImage(image[12], listRect.get(12).x, listRect.get(12).y, this);
            }

            if (userListSum == 15) {

                g2D.drawImage(image[9], listRect.get(9).x, listRect.get(9).y, this);
                g2D.drawImage(image[12], listRect.get(12).x, listRect.get(12).y, this);
            }
            counter = 0;
            for (int i = 0; i < available.length; i++) {

                if (available[i] != 0) {
                    counter++;
                }

                if (counter == available.length - 1) {

                    if (userListSum != 15 && compListSum != 15) {

                        g2D.drawImage(image[11], listRect.get(11).x, listRect.get(11).y, this);
                        g2D.drawImage(image[12], listRect.get(12).x, listRect.get(12).y, this);
                    }
                }
            }

        }
    }

    public static void main(String args[]) {
        new Make15();
    }

}
