package flag;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Flag extends Application {

    private final int BANK = 235;
    private String answer[] = new String[BANK];
    private Image quocKi[] = new Image[BANK];
    private double width[] = new double[BANK];
    private boolean chon[] = new boolean[BANK];
    private int viTriDapAn;
    private Button button[] = new Button[4];
    private ImageView temp;
    private Random random;
    private int count;

    public void readAnswer() {
        BufferedReader ip = null;
        try {
            ip = new BufferedReader(new InputStreamReader(new FileInputStream("flagSource\\dapAn.txt"), "UTF-16"));
            for (int i = 0; i < BANK; i++) {
                answer[i] = ip.readLine();
            }
        } catch (Exception ex) {
            Logger.getLogger(Flag.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void readImage() {
        File file;
        for (int i = 1; i <= BANK; i++) {
            try {
                file = new File("flagSource\\" + i + ".gif");
                quocKi[i - 1] = new Image(file.toURI().toURL().toString());
                width[i - 1] = 200 * quocKi[i - 1].getWidth() / quocKi[i - 1].getHeight();
            } catch (Exception ex) {
                Logger.getLogger(Flag.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void playing(Stage stage) {
        count = -1;
        Pane pane = new Pane();

        temp = new ImageView();
        temp.setY(90);
        temp.setFitHeight(200);
        pane.getChildren().add(temp);

        Polygon taoHinh = new Polygon();
        ObservableList<Double> listButtonCoThuong = taoHinh.getPoints();
        listButtonCoThuong.add(0.0);
        listButtonCoThuong.add(35.0);
        listButtonCoThuong.add(43.25);
        listButtonCoThuong.add(0.0);
        listButtonCoThuong.add(256.75);
        listButtonCoThuong.add(0.0);
        listButtonCoThuong.add(300.0);
        listButtonCoThuong.add(35.0);
        listButtonCoThuong.add(256.75);
        listButtonCoThuong.add(70.0);
        listButtonCoThuong.add(43.25);
        listButtonCoThuong.add(70.0);

        for (int i = 0; i < 4; i++) {
            button[i] = new Button();
            button[i].setShape(taoHinh);
            button[i].setFont(new Font("Constantia", 20));
            button[i].setTextAlignment(TextAlignment.CENTER);
            button[i].setPrefSize(300, 70);
            button[i].setWrapText(true);
            pane.getChildren().add(button[i]);
        }

        Label question = new Label("Đây là quốc kì của quốc gia nào?");
        question.setFont(new Font("Constantia", 30));
        question.setAlignment(Pos.CENTER);
        question.setPrefSize(730, 90);
        question.setTranslateY(0);
        pane.getChildren().add(question);
        raDe();

        button[0].setStyle("-fx-color: orange");
        button[0].setTranslateX(50);
        button[0].setTranslateY(330);
        button[0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (viTriDapAn == 0) {
                    raDe();
                } else {
                    ketQua(stage);
                }
            }
        });

        button[1].setStyle("-fx-color: green");
        button[1].setTranslateX(390);
        button[1].setTranslateY(330);
        button[1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (viTriDapAn == 1) {
                    raDe();
                } else {
                    ketQua(stage);
                }
            }
        });

        button[2].setStyle("-fx-color: blue");
        button[2].setTranslateX(50);
        button[2].setTranslateY(440);
        button[2].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (viTriDapAn == 2) {
                    raDe();
                } else {
                    ketQua(stage);
                }
            }
        });

        button[3].setStyle("-fx-color: brown");
        button[3].setTranslateX(390);
        button[3].setTranslateY(440);
        button[3].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (viTriDapAn == 3) {
                    raDe();
                } else {
                    ketQua(stage);
                }
            }
        });

        Scene scene = new Scene(pane, 730, 550);
        stage.setScene(scene);
        stage.show();
    }

    public void raDe() {
        int dapAn = Math.abs(random.nextInt() % BANK);
        viTriDapAn = Math.abs(random.nextInt() % 4);
        int dapAnConLai;
        int bonDapAn[] = new int[4];
        chon[dapAn] = true;
        button[viTriDapAn].setText(answer[dapAn]);
        bonDapAn[viTriDapAn] = dapAn;
        for (int i = 0; i < 4; i++) {
            if (i != viTriDapAn) {
                do {
                    dapAnConLai = Math.abs(random.nextInt() % BANK);
                } while (chon[dapAnConLai]);
                chon[dapAnConLai] = true;
                button[i].setText(answer[dapAnConLai]);
                bonDapAn[i] = dapAnConLai;
            }
        }
        temp.setImage(quocKi[dapAn]);
        temp.setX(365 - width[dapAn] / 2);
        temp.setFitWidth(width[dapAn]);
        for (int i = 0; i < 4; i++) {
            chon[bonDapAn[i]] = false;
        }
        ++count;
    }

    public void ketQua(Stage stage) {
        Pane pane = new Pane();

        Label above = new Label("Số quốc kì bạn giải được là: ");
        above.setPrefSize(730, 100);
        above.setFont(new Font("Constantia", 20));
        above.setAlignment(Pos.CENTER);
        pane.getChildren().add(above);

        Label below = new Label("" + count);
        below.setPrefSize(730, 310);
        below.setTranslateY(100);
        below.setFont(new Font(".VnVogue", 50));
        below.setAlignment(Pos.CENTER);
        pane.getChildren().add(below);

        Polygon taoHinh1 = new Polygon();
        ObservableList<Double> listButtonCoThuong = taoHinh1.getPoints();
        listButtonCoThuong.add(0.0);
        listButtonCoThuong.add(20.0);
        listButtonCoThuong.add(25.0);
        listButtonCoThuong.add(0.0);
        listButtonCoThuong.add(275.0);
        listButtonCoThuong.add(0.0);
        listButtonCoThuong.add(300.0);
        listButtonCoThuong.add(20.0);
        listButtonCoThuong.add(275.0);
        listButtonCoThuong.add(40.0);
        listButtonCoThuong.add(25.0);
        listButtonCoThuong.add(40.0);

        Button tiep = new Button();
        tiep.setShape(taoHinh1);
        tiep.setTranslateY(410);
        tiep.setTranslateX(390);
        tiep.setPrefSize(300, 40);
        tiep.setFont(new Font("Constantia", 20));
        tiep.setText("Chơi tiếp");
        tiep.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    playing(stage);
                } catch (Exception ex) {
                    System.out.println("Something");
                }
            }
        });
        pane.getChildren().add(tiep);

        Button dung = new Button();
        dung.setShape(taoHinh1);
        dung.setTranslateY(410);
        dung.setTranslateX(50);
        dung.setPrefSize(300, 40);
        dung.setFont(new Font("Constantia", 20));
        dung.setText("Dừng lại");
        dung.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });
        pane.getChildren().add(dung);

        Scene scene = new Scene(pane, 730, 550);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage primaryStage) {
        readAnswer();
        readImage();
        random = new Random();
        primaryStage.setTitle("Guessing flags");
        playing(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
