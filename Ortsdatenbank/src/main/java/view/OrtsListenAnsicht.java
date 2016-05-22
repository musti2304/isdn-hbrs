package view;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.AbstractOrt;
import model.Ort;
import model.OrtMitBesuchsdatum;
import model.OrtsListe;

public class OrtsListenAnsicht implements Observer {

    private OrtsListe ortsListe;
    private ObservableList<AbstractOrt> tableViewItems = FXCollections.observableArrayList();
    private static final int SIZE_OF_OTHER_CONTROLS = 52;

    public OrtsListenAnsicht(OrtsListe ortsListe) {
        this.ortsListe = ortsListe;
        ortsListe.addObserver(this);
    }

	@Override
	public void update(Observable o, Object arg) {
		updateDisplayedList();
		
	}
    
    public void show(final Stage primaryStage) {
        primaryStage.setTitle("My POIs");

        BorderPane borderPane = new BorderPane();
        
        TableView<AbstractOrt> table = new TableView<>();

        TableColumn<AbstractOrt, String> nameCol = new TableColumn<AbstractOrt, String>("Name");
        nameCol.setMinWidth(200);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<AbstractOrt, String> anschriftCol = new TableColumn<AbstractOrt, String>("Anschrift");
        anschriftCol.setMinWidth(200);
        anschriftCol.setCellValueFactory(new PropertyValueFactory<>("anschrift"));
        
        TableColumn<AbstractOrt, Date> dateCol = new TableColumn<AbstractOrt, Date>("Zuletzt besucht am");
        dateCol.setMinWidth(150);
        dateCol.setCellValueFactory(new PropertyValueFactory<>("datum"));
        
        // Die TableView: Komponente, die zwei TableColumns enthält
        table.getColumns().addAll(nameCol, anschriftCol, dateCol);
        
        table.setItems(tableViewItems);
        borderPane.setCenter(table);
        
        // Eine ImageView mit einem Image als Leaf
        Image image = new Image("http://staticmap.openstreetmap.de/staticmap.php?center=51.7,9.5&zoom=5&size=300x405&maptype=mapnik",true);
                
        ImageView imageview = new ImageView();
        imageview.setImage(image);
        
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Image newSelectionimage = new Image(getMapURL(newSelection.getAnschrift(), (int)borderPane.getHeight()- SIZE_OF_OTHER_CONTROLS), false);
                imageview.setImage(newSelectionimage);
            }
        });
        
        borderPane.setRight(imageview);
        

        Button btnAdd = new Button("Ort hinzufügen");
        Button btnSave = new Button("Speichern");
        Button btnAddDate = new Button("Ort mit Datum hinzufügen");

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: linear-gradient(#6699CC, #104E8B);");

        // Die HBox: Komponente, die zwei Buttons als Leafs enthält.
        hbox.getChildren().addAll(btnAdd, btnSave, btnAddDate);

        final OrtsListenAnsicht ortsListenAnsicht = this;

        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                (new OrtsAnsicht(new Ort(), ortsListe, ortsListenAnsicht)).show(primaryStage);
            }
        });

        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                FileOutputStream fos;
                ObjectOutputStream out;
                try {
                    fos = new FileOutputStream((new Date().getTime() + ".ser"));
                    out = new ObjectOutputStream(fos);
                    out.writeObject(ortsListe);
                    out.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        btnAddDate.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				(new OrtMitBesuchsdatumAnsicht(new OrtMitBesuchsdatum(), ortsListe, ortsListenAnsicht)).show(primaryStage);
			}
		});

        borderPane.setBottom(hbox);

        primaryStage.setMinWidth(920);
        primaryStage.setMinHeight(400);
        
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(OrtsListenAnsicht.class.getResourceAsStream("icon.png")));
        updateDisplayedList();
        primaryStage.show();
        
        
		scene.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
				String anschrift = table.getSelectionModel().getSelectedItem() != null
						? table.getSelectionModel().getSelectedItem().getAnschrift() : null;
				Image newSelectionimage = new Image(getMapURL(anschrift, (int) borderPane.getHeight() - SIZE_OF_OTHER_CONTROLS), false);
				imageview.setImage(newSelectionimage);
			}
		});
    }

    public void updateDisplayedList() {
        tableViewItems.clear();
        tableViewItems.addAll(ortsListe.getListeVonOrten());
    }
    
	public static String getMapURL(String anschrift, int height) {
		String geocoordinate = null;
		try {
			if (anschrift != null) {
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

				String encodedAnschrift = URLEncoder.encode(anschrift, "UTF-8").replace("+", " ");
				Document document = documentBuilder.parse("https://nominatim.openstreetmap.org/search/" + encodedAnschrift + "?limit=1&format=xml");

				Node node = document.getElementsByTagName("place").item(0);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					geocoordinate = element.getAttribute("lat") + "," + element.getAttribute("lon");
				}
			}
		} catch (SAXException e) {
		} catch (IOException e) {
		} catch (ParserConfigurationException e) {
		}
		
		String url = geocoordinate != null ? 
				"http://staticmap.openstreetmap.de/staticmap.php?center=" + geocoordinate + "&markers=" + 
				geocoordinate	+ ",red-pushpin&zoom=16&size=300x" + height + "&maptype=mapnik" :  
				"http://staticmap.openstreetmap.de/staticmap.php?center=51.7,9.5&zoom=5&size=300x" + height	+ "&maptype=mapnik";
		
		return url;
	}

}