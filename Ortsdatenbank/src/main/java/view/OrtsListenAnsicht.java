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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.AbstractOrt;
import model.OeffentlicherModusStrategy;
import model.Ort;
import model.OrtMitBesuchsdatum;
import model.OrtsListe;
import model.PrivatModusStrategy;
import model.Strategy;

public class OrtsListenAnsicht implements Observer {

	private ObservableList<AbstractOrt> tableViewItems = FXCollections.observableArrayList();
	private OrtsListe ortsListe;
	private Strategy strategie;
	private static final int SIZE_OF_OTHER_CONTROLS = 105;

	/////////////////////// UI Elements ////////////////////////
 	BorderPane borderPane = new BorderPane();
	Scene scene = new Scene(borderPane);

	TableView<AbstractOrt> table = new TableView<>();
	TableColumn<AbstractOrt, String> nameCol = new TableColumn<AbstractOrt, String>("Name");
	TableColumn<AbstractOrt, String> anschriftCol = new TableColumn<AbstractOrt, String>("Anschrift");
	TableColumn<AbstractOrt, Date> dateCol = new TableColumn<AbstractOrt, Date>("Zuletzt besucht am");

	Image image = new Image(
			"http://staticmap.openstreetmap.de/staticmap.php?center=51.7,9.5&zoom=5&size=300x405&maptype=mapnik", true);

	ImageView imageview = new ImageView();

	Button btnAdd = new Button("Ort hinzufügen");
	Button btnSave = new Button("Speichern");
	Button btnAddDate = new Button("Ort mit Datum hinzufügen");
	Button btnShowPlaces = new Button("Orte ausgeben");
	final Button btnDel = new Button("Ort löschen");

	final ToggleGroup group = new ToggleGroup();

	final RadioButton oeffentlicherModusRB = new RadioButton();
	final RadioButton privatModusRB = new RadioButton();
	HBox strategyHBox = new HBox();
	HBox hbox = new HBox();
	final OrtsListenAnsicht ortsListenAnsicht = this;

	public void show(final Stage primaryStage) {
		primaryStage.setTitle("My POIs");

		nameCol.setMinWidth(300);
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		anschriftCol.setMinWidth(300);
		anschriftCol.setCellValueFactory(new PropertyValueFactory<>("anschrift"));
		dateCol.setMinWidth(300);
		dateCol.setCellValueFactory(new PropertyValueFactory<>("datumDesBesuchs"));

		table.getColumns().addAll(nameCol, anschriftCol, dateCol);
		table.setItems(tableViewItems);
		
		imageview.setImage(image);

		table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				Image newSelectionimage = new Image(
						getMapURL(newSelection.getAnschrift(), (int) borderPane.getHeight() - SIZE_OF_OTHER_CONTROLS),
						false);
				imageview.setImage(newSelectionimage);
			}
		});

		borderPane.setRight(imageview);

		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10);
		hbox.setStyle("-fx-background-color: linear-gradient(#6699CC, #104E8B);");
		hbox.getChildren().addAll(btnAdd, btnDel, btnSave, btnAddDate, btnShowPlaces);

		borderPane.setCenter(table);
		borderPane.setBottom(hbox);
		borderPane.setTop(strategyHBox);

		oeffentlicherModusRB.setToggleGroup(group);
		oeffentlicherModusRB.setSelected(true);
		oeffentlicherModusRB.setText("Öffentlicher Modus");

		privatModusRB.setToggleGroup(group);
		privatModusRB.setText("Privater Modus");

		strategyHBox.setPadding(new Insets(15, 12, 15, 12));
		strategyHBox.setSpacing(2);
		strategyHBox.setStyle("-fx-background-color: linear-gradient(#ffff33, #808000);");
		strategyHBox.getChildren().addAll(oeffentlicherModusRB, privatModusRB, btnShowPlaces);

		this.strategie = new OeffentlicherModusStrategy();

		primaryStage.setMinWidth(1200);
		primaryStage.setMinHeight(500);

		// Button Actions and Handlers
		btnDel.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				AbstractOrt ort = table.getSelectionModel().getSelectedItem();
				if (ort == null)
					return;
				ortsListe.removeOrt(ort);
				Image image = new Image(
						"http://staticmap.openstreetmap.de/staticmap.php?center=51.7,9.5&zoom=5&size=300x405&maptype=mapnik",
						true);
				imageview.setImage(image);

				ort.setName(nameCol.getText());
				ort.setAnschrift(anschriftCol.getText());
				ortsListe.removeOrt(ort);
				ortsListenAnsicht.update(ortsListe, this);

			}
		});

		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				(new OrtsAnsicht(new Ort(), ortsListe, ortsListenAnsicht)).show(primaryStage);
			}
		});

		btnSave.setOnAction(new EventHandler<ActionEvent>() {
			@Override
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
			@Override
			public void handle(ActionEvent event) {
				(new OrtMitBesuchsdatumAnsicht(new OrtMitBesuchsdatum(), ortsListe, ortsListenAnsicht))
						.show(primaryStage);
			}
		});

		btnShowPlaces.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				strategie.ausgabeDerOrte(ortsListe.getListeVonOrten());
			}
		});

		table.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
					if (mouseEvent.getClickCount() == 2) {
						AbstractOrt ort = table.getSelectionModel().getSelectedItem();
						if (ort instanceof OrtMitBesuchsdatum) {
							new OrtMitBesuchsdatumAnsicht(ort, ortsListe, ortsListenAnsicht).show(primaryStage);
						} else {
							new OrtsAnsicht(ort, ortsListe, ortsListenAnsicht).show(primaryStage);
						}
					}
				}
			}
		});

		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				if (group.getSelectedToggle() != null) {
					if (group.getSelectedToggle().equals(oeffentlicherModusRB)) {
						strategie = new OeffentlicherModusStrategy();
						System.out.println("Öffentlicher Modus aktiviert");
					} else {
						strategie = new PrivatModusStrategy();
						System.out.println("Privater Modus aktiviert");
					}
				}
			}
		});

		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image(OrtsListenAnsicht.class.getResourceAsStream("icon.png")));
		updateDisplayedList();
		primaryStage.show();

		scene.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight,
					Number newSceneHeight) {
				String anschrift = table.getSelectionModel().getSelectedItem() != null
						? table.getSelectionModel().getSelectedItem().getAnschrift() : null;
				Image newSelectionimage = new Image(
						getMapURL(anschrift, (int) borderPane.getHeight() - SIZE_OF_OTHER_CONTROLS), false);
				imageview.setImage(newSelectionimage);
			}
		});
	}

	public void updateDisplayedList() {
		tableViewItems.clear();
		tableViewItems.addAll(ortsListe.getListeVonOrten());
	}

	public OrtsListenAnsicht(OrtsListe ortsListe) {
		this.ortsListe = ortsListe;
		ortsListe.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		updateDisplayedList();

	}

	public static String getMapURL(String anschrift, int height) {
		String geocoordinate = null;
		try {
			if (anschrift != null) {
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

				String encodedAnschrift = URLEncoder.encode(anschrift, "UTF-8").replace("+", " ");
				Document document = documentBuilder.parse(
						"https://nominatim.openstreetmap.org/search/" + encodedAnschrift + "?limit=1&format=xml");

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

		String url = geocoordinate != null
				? "http://staticmap.openstreetmap.de/staticmap.php?center=" + geocoordinate + "&markers="
						+ geocoordinate + ",red-pushpin&zoom=16&size=300x" + height + "&maptype=mapnik"
				: "http://staticmap.openstreetmap.de/staticmap.php?center=51.7,9.5&zoom=5&size=300x" + height
						+ "&maptype=mapnik";

		return url;

	}
}