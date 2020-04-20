import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BoardView extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel viewPane;

    private Board model;

    public BoardView() {



        int[][] testBoard = {
                {4,2,3},
                {1,0,6},
                {7,8,5}
        };
        model = new Board(testBoard);

        Dimension size = model.getSize();
        viewPane.setLayout(new GridLayout(size.height, size.width));
        for(int i = 0; i < size.height; i++) {
            for (int j = 0; j < size.width; j++) {
                Button b = new Button(model.get(i,j));
                viewPane.add(b);
            }
        }

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        // LEFT
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model = model.next(Board.LEFT);
                updateView();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        // RIGHT
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model = model.next(Board.RIGHT);
                updateView();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        // UP
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model = model.next(Board.UP);
                updateView();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        // DOWN
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model = model.next(Board.DOWN);
                updateView();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    private void updateView(){
        Dimension size = model.getSize();

        int component = 0;

        for(int i = 0; i < size.height; i++) {
            for (int j = 0; j < size.width; j++) {
                Button b = (Button) viewPane.getComponent(component++);
                b.setLabel(model.get(i,j));
            }
        }

    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        BoardView dialog = new BoardView();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }


}
